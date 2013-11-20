package com.eden.constant;

import java.io.FileInputStream;
import java.util.Properties;

import com.eden.fxmvc.util.LogUtils;
import com.eden.fxmvc.util.TypeConvertUtil;

public class ConstantMsg {
	private static Properties prop = new Properties() ;
	static {
		try {
			prop.load(new FileInputStream("msg.properties")) ;
		} catch (Exception e) {
			LogUtils.log4Error("intialize message properties error", e) ;
		}
	}
	
	public static final String PAGE_CURR_ERR = "页码输入错误";

	public static String getMessage(String key) {
		return TypeConvertUtil.toStr(prop.get(key) );
	}
	
	public Properties getProp() {
		return prop;
	}
	
}
