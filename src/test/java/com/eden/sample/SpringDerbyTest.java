package com.eden.sample;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.derby.tools.ij;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class SpringDerbyTest {
	@BeforeClass
	public static void Before(){
		PropertyConfigurator.configureAndWatch("log4j.properties") ;
	}
	@Autowired
    private ApplicationContext applicationContext;
//	@Autowired
//	private DataSource dataSource ;
	
	
	@Test
	public void testGetConn(){
//		try {
//			System.out.println(dataSource.getConnection()) ;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void ijTest(){
//		ij.runScript(dataSource.getConnection() , arg1, arg2, arg3, arg4) ;
	}
}
