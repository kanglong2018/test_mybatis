package com.kanglong;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	 public static void main(String[] args) {
	        
	        Logger logger = Logger.getLogger(Log4jTest.class.getName());
	        
	        //使用默认的配置信息，不需要写log4j.properties
	      //  BasicConfigurator.configure();
	        //设置日志输出级别为info，这将覆盖配置文件中设置的级别
	        /*logger.setLevel(Level.INFO);*/
	        //下面的消息将被输出
	        int i = 1000;
	        for (;i>0;i--) {
	        	logger.debug("Hello this is an debug message");
	 	        logger.info("Hello this is an info message");
	 	        logger.error("Hello this is an error message");
	        }
	 }

}
