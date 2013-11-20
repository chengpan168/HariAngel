package com.eden.basic;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-test.xml")
public class BaseTestcase {
	@BeforeClass
	public static void Before(){
		PropertyConfigurator.configureAndWatch("log4j.properties") ;
	}
	@Autowired
    public ApplicationContext applicationContext;
	
	@Test
	public void testSpring() {
		System.out.println("spring start success ... ") ;
	}
	
	public static void log(Object log) {
		System.out.println(log) ;
	}
}
