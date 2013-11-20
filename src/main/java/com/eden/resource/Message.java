package com.eden.resource;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.eden.fxmvc.context.AppContext;

@Component
public class Message {
	private static ResourceBundleMessageSource messageSource = null ;
	
	public static String getMsg(String key) {
		if(StringUtils.isBlank(key)) return null ;
		try {
			if(messageSource == null) {
				messageSource = AppContext.getBean("messageSource") ;
			}
			return messageSource.getMessage(key , null, Locale.CHINA) ;
		} catch (NoSuchMessageException e) {
			e.printStackTrace();
			return null ;
		}
		
	}
}
