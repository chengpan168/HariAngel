package com.eden.basic;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;

public class FxBaseTestcase extends BaseTestcase {
	@BeforeClass
	public static void Before(){
		PropertyConfigurator.configureAndWatch("log4j.properties") ;
	}
}
