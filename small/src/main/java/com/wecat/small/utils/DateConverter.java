/**
 * 
 */
package com.wecat.small.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;






/**
 * @author Administrator
 * 自定义参数绑定  日期字符串转换成Data
 */
public class DateConverter implements Converter<String, Date>{

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/* （非 Javadoc）
	 */
	@Override
	public Date convert(String strings) {
		
		if ("".equals(strings) || strings == null) {
            return null;
        }
        try {
            return simpleDateFormat.parse(strings);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
	}

	

} 
