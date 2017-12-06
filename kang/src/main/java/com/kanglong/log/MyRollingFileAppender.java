package com.kanglong.log;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

public class MyRollingFileAppender extends FileAppender {
	/**日期模板数字**/
	static final int TOP_OF_TROUBLE = -1;
	static final int TOP_OF_MINUTE = 0;
	static final int TOP_OF_HOUR = 1;
	static final int HALF_DAY = 2;
	static final int TOP_OF_DAY = 3;
	static final int TOP_OF_WEEK = 4;
	static final int TOP_OF_MONTH = 5;
	
	private String datePattern = "'.'yyyy-MM-dd";
	private String scheduledFilename;
	private long nextCheck = System.currentTimeMillis() - 1;
	SimpleDateFormat sdf;
	RollingCalendar rc = new RollingCalendar();
	Date now = new Date();
	int checkPeriod = TOP_OF_TROUBLE;
	static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
	
	/**文件大小设置**/
	 protected long maxFileSize = 10*1024*1024;
	 protected int  maxBackupIndex  = 1;
	 private long nextRollover = 0;
/*	public MyRollingFileAppender(Layout layout,String filename, boolean append) throws IOException {
		super(layout, filename, append);
	}
	public  MyRollingFileAppender(Layout layout, String filename) throws IOException {
	    super(layout, filename);
	  }*/
	public MyRollingFileAppender(Layout layout, String filename,
			String datePattern) throws IOException {
		super(layout, filename, true);
		this.datePattern = datePattern;
		activateOptions();
	}
	public void activateOptions() {
		if (datePattern != null && fileName != null) {
			now.setTime(System.currentTimeMillis());
			sdf = new SimpleDateFormat(datePattern);
			fileName += sdf.format(new Date());
			activateOptionMethod();
			int type = computeCheckPeriod();
			printPeriodicity(type);
			rc.setType(type);
			File file = new File(fileName);
			scheduledFilename = fileName
					+ sdf.format(new Date(file.lastModified()));

		} else {
			activateOptionMethod();
			LogLog.error("Either File or DatePattern options are not set for appender ["
					+ name + "].");
		}
	}
	private void activateOptionMethod() {
		if(fileName != null) {
		      try {
			setFile(fileName, fileAppend, bufferedIO, bufferSize);
		      }
		      catch(java.io.IOException e) {
			errorHandler.error("setFile("+fileName+","+fileAppend+") call failed.",
					   e, ErrorCode.FILE_OPEN_FAILURE);
		      }
		    } else {
		      //LogLog.error("File option not set for appender ["+name+"].");
		      LogLog.warn("File option not set for appender ["+name+"].");
		      LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
		    }
	}
	void printPeriodicity(int type) {
		switch (type) {
		case TOP_OF_MINUTE:
			LogLog.debug("Appender [" + name + "] to be rolled every minute.");
			break;
		case TOP_OF_HOUR:
			LogLog.debug("Appender [" + name
					+ "] to be rolled on top of every hour.");
			break;
		case HALF_DAY:
			LogLog.debug("Appender [" + name
					+ "] to be rolled at midday and midnight.");
			break;
		case TOP_OF_DAY:
			LogLog.debug("Appender [" + name + "] to be rolled at midnight.");
			break;
		case TOP_OF_WEEK:
			LogLog.debug("Appender [" + name
					+ "] to be rolled at start of week.");
			break;
		case TOP_OF_MONTH:
			LogLog.debug("Appender [" + name
					+ "] to be rolled at start of every month.");
			break;
		default:
			LogLog.warn("Unknown periodicity for appender [" + name + "].");
		}
	}
	int computeCheckPeriod() {
		RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone,
				Locale.getDefault());
		// set sate to 1970-01-01 00:00:00 GMT
		Date epoch = new Date(0);
		if (datePattern != null) {
			for (int i = TOP_OF_MINUTE; i <= TOP_OF_MONTH; i++) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						datePattern);
				simpleDateFormat.setTimeZone(gmtTimeZone); // do all date
															// formatting in GMT
				String r0 = simpleDateFormat.format(epoch);
				rollingCalendar.setType(i);
				Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
				String r1 = simpleDateFormat.format(next);
				// System.out.println("Type = "+i+", r0 = "+r0+", r1 = "+r1);
				if (r0 != null && r1 != null && !r0.equals(r1)) {
					return i;
				}
			}
		}
		return TOP_OF_TROUBLE; // Deliberately head for trouble...
	}
	void rollOver() throws IOException {

		/* Compute filename, but only if datePattern is specified */
		if (datePattern == null) {
			errorHandler.error("Missing DatePattern option in rollOver().");
			return;
		}

		String datedFilename = fileName + sdf.format(now);
		// It is too early to roll over because we are still within the
		// bounds of the current interval. Rollover will occur once the
		// next interval is reached.
		if (scheduledFilename.equals(datedFilename)) {
			return;
		}

		// close current file, and rename it to datedFilename
		this.closeFile();

		File target = new File(scheduledFilename);
		if (target.exists()) {
			target.delete();
		}

		File file = new File(fileName);
		boolean result = file.renameTo(target);
		if (result) {
			LogLog.debug(fileName + " -> " + scheduledFilename);
		} else {
			LogLog.error("Failed to rename [" + fileName + "] to ["
					+ scheduledFilename + "].");
		}

		try {
			// This will also close the file. This is OK since multiple
			// close operations are safe.
			this.setFile(fileName, true, this.bufferedIO, this.bufferSize);
		} catch (IOException e) {
			errorHandler.error("setFile(" + fileName + ", true) call failed.");
		}
		scheduledFilename = datedFilename;
	}


	
	protected void subAppend(LoggingEvent event) {
		long n = System.currentTimeMillis();
		if (n >= nextCheck) {
			now.setTime(n);
			nextCheck = rc.getNextCheckMillis(now);
			try {
				rollOver();
			} catch (IOException ioe) {
				if (ioe instanceof InterruptedIOException) {
					Thread.currentThread().interrupt();
				}
				LogLog.error("rollOver() failed.", ioe);
			}
		}
		super.subAppend(event);
		if(fileName != null && qw != null) {
			//时间文件处理
			
			//文件大小回滚处理
			long size = ((CountingQuietWriter) qw).getCount();
	        if (size >= maxFileSize && size >= nextRollover) {
	            rollSizeOver();
	        }
		}
	}
	
	  public void rollSizeOver() {
	    File target;
	    File file;

	    if (qw != null) {
	        long size = ((CountingQuietWriter) qw).getCount();
	        LogLog.debug("rolling over count=" + size);
	        //   if operation fails, do not roll again until
	        //      maxFileSize more bytes are written
	        nextRollover = size + maxFileSize;
	    }
	    LogLog.debug("maxBackupIndex="+maxBackupIndex);

	    boolean renameSucceeded = true;
	    // If maxBackups <= 0, then there is no file renaming to be done.
	    if(maxBackupIndex > 0) {
	      // Delete the oldest file, to keep Windows happy.
	      file = new File(fileName + '.' + maxBackupIndex);
	      if (file.exists())
	       renameSucceeded = file.delete();

	      // Map {(maxBackupIndex - 1), ..., 2, 1} to {maxBackupIndex, ..., 3, 2}
	      for (int i = maxBackupIndex - 1; i >= 1 && renameSucceeded; i--) {
		file = new File(fileName + "." + i);
		if (file.exists()) {
		  target = new File(fileName + '.' + (i + 1));
		  LogLog.debug("Renaming file " + file + " to " + target);
		  renameSucceeded = file.renameTo(target);
		}
	      }

	    if(renameSucceeded) {
	      // Rename fileName to fileName.1
	      target = new File(fileName + "." + 1);

	      this.closeFile(); // keep windows happy.

	      file = new File(fileName);
	      LogLog.debug("Renaming file " + file + " to " + target);
	      renameSucceeded = file.renameTo(target);
	      //
	      //   if file rename failed, reopen file with append = true
	      //
	      if (!renameSucceeded) {
	          try {
	            this.setFile(fileName, true, bufferedIO, bufferSize);
	          }
	          catch(IOException e) {
	              if (e instanceof InterruptedIOException) {
	                  Thread.currentThread().interrupt();
	              }
	              LogLog.error("setFile("+fileName+", true) call failed.", e);
	          }
	      }
	    }
	    }

	    //
	    //   if all renames were successful, then
	    //
	    if (renameSucceeded) {
	    try {
	      // This will also close the file. This is OK since multiple
	      // close operations are safe.
	      this.setFile(fileName, false, bufferedIO, bufferSize);
	      nextRollover = 0;
	    }
	    catch(IOException e) {
	        if (e instanceof InterruptedIOException) {
	            Thread.currentThread().interrupt();
	        }
	        LogLog.error("setFile("+fileName+", false) call failed.", e);
	    }
	    }
	  }
		public long getMaximumFileSize() {
			return maxFileSize;
		}
		public void setMaximumFileSize(long maxFileSize) {
			this.maxFileSize = maxFileSize;
		}
	
	public String getScheduledFilename() {
		return scheduledFilename;
	}
	public void setScheduledFilename(String scheduledFilename) {
		this.scheduledFilename = scheduledFilename;
	}
	
	public int getMaxBackupIndex() {
		return maxBackupIndex;
	}
	public void setMaxBackupIndex(int maxBackupIndex) {
		this.maxBackupIndex = maxBackupIndex;
	}
	public MyRollingFileAppender() {
		super();
	}
	public void setMaxFileSize(String value) {
		maxFileSize = OptionConverter.toFileSize(value, maxFileSize + 1);
	}

	protected void setQWForFiles(Writer writer) {
		this.qw = new CountingQuietWriter(writer, errorHandler);
	}
	/**
	 * The <b>DatePattern</b> takes a string in the same format as expected by
	 * {@link SimpleDateFormat}. This options determines the rollover schedule.
	 */
	public void setDatePattern(String pattern) {
		datePattern = pattern;
	}

	/** Returns the value of the <b>DatePattern</b> option. */
	public String getDatePattern() {
		return datePattern;
	}

}

class RollingCalendar extends GregorianCalendar {
	private static final long serialVersionUID = -3560331770601814177L;

	int type = MyRollingFileAppender.TOP_OF_TROUBLE;

	RollingCalendar() {
		super();
	}

	RollingCalendar(TimeZone tz, Locale locale) {
		super(tz, locale);
	}

	void setType(int type) {
		this.type = type;
	}

	public long getNextCheckMillis(Date now) {
		return getNextCheckDate(now).getTime();
	}

	public Date getNextCheckDate(Date now) {
		this.setTime(now);

		switch (type) {
		case MyRollingFileAppender.TOP_OF_MINUTE:
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.MINUTE, 1);
			break;
		case MyRollingFileAppender.TOP_OF_HOUR:
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.HOUR_OF_DAY, 1);
			break;
		case MyRollingFileAppender.HALF_DAY:
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			int hour = get(Calendar.HOUR_OF_DAY);
			if (hour < 12) {
				this.set(Calendar.HOUR_OF_DAY, 12);
			} else {
				this.set(Calendar.HOUR_OF_DAY, 0);
				this.add(Calendar.DAY_OF_MONTH, 1);
			}
			break;
		case MyRollingFileAppender.TOP_OF_DAY:
			this.set(Calendar.HOUR_OF_DAY, 0);
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.DATE, 1);
			break;
		case MyRollingFileAppender.TOP_OF_WEEK:
			this.set(Calendar.DAY_OF_WEEK, getFirstDayOfWeek());
			this.set(Calendar.HOUR_OF_DAY, 0);
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.WEEK_OF_YEAR, 1);
			break;
		case MyRollingFileAppender.TOP_OF_MONTH:
			this.set(Calendar.DATE, 1);
			this.set(Calendar.HOUR_OF_DAY, 0);
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.MONTH, 1);
			break;
		default:
			throw new IllegalStateException("Unknown periodicity type.");
		}
		return getTime();
	}
}

