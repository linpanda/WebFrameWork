package com.code.common.conutil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetTCproperties {

	private static final String path = "testcon.properties";// 从src的根目录开始
	private static final String encode = "UTF-8";
	private static Properties props = new Properties();
	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) throws Exception {
		return new String(props.getProperty(key).getBytes("ISO8859-1"), encode);
	}

	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}

}