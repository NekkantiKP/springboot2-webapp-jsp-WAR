package net.guides.springboot2.springboot2webappjsp.controllers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;

public class Sanitizer {

	public static final Encoder esapiEncoder=ESAPI.encoder();
	public static String Sanitize(String s) {
		try {
			String sanitizeString = esapiEncoder.encodeForHTML(s);
			return sanitizeString;
		} catch (Exception e) {
			e.printStackTrace();
			return s;
		}
	}

	public static Object Sanitize(Object obj) throws IllegalAccessException {
		try {
			StringBuffer buffer = new StringBuffer();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					f.setAccessible(true);
					Object value = f.get(obj);
					buffer.append(" ");
					buffer.append(f.getName());
					buffer.append("=");
					if(f.getType().getName().equalsIgnoreCase("java.lang.String")) {
						buffer.append("" + Sanitize((String) value));
					}else {
						buffer.append("" + value);
					}
					buffer.append(",");
				}
			}
			return buffer.toString();
		} catch (SecurityException e) {
			return obj;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return obj;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return obj;
		}
	}
}
