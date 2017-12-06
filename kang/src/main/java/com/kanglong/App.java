package com.kanglong;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.kanglong.dao.Api;
import com.mapper.ApiMapper;

/**
 * Hello world!
 *
 */
public class App { 
	private final static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args ) throws IOException
    {
    	logger.info("start .....");
    	String resource = "com/dao/mybatis-config.xml";
    	InputStream  inputStream = Resources.getResourceAsStream(resource);
    	SqlSessionFactory sqlSessionFactory = null;
    	sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;
        try {
        	 sqlSession = sqlSessionFactory.openSession();
        	 ApiMapper apiManager = sqlSession.getMapper(ApiMapper.class);
        	 Api api = apiManager.seleteApiById(73002L);
        	 System.out.println( api.getId() );
        	 logger.info("end...........API ID="+ api.getId());
             sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
    	System.out.println( "Hello World!" );
        
    }
}
