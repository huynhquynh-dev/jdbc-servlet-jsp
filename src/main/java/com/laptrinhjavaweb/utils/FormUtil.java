package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	
/* 
 * Hàm này dùng để lấy tất vả các dữ liệu nhận được từ parameter và chuyển về dạng json	
 */
	public static <T> T toModel(Class<T> tClass, HttpServletRequest req) {
		
		T object = null;
		
		try {
			// Mục đích hàm là lấy dữ liệu từ client trả về
			// truyền vào dữ liệu dạng json cho đối tượng object
			
			//Khởi tạo đối tượng
			object = tClass.newInstance();
			
			BeanUtils.populate(object, req.getParameterMap());
		} 
		catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {

			System.out.println(e.getMessage());
		}
		return object;
	}
}
