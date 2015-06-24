package com.wonders.stpt.util;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader extends Properties {
	private static PropertiesReader reader = new PropertiesReader();

	public PropertiesReader() {
		InputStream is = null;
		try {
			is = super.getClass().getResourceAsStream("/config.properties");
			load(is);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String GetProptery(String key) {
		String value = "";
		try {
			if (reader.getProperty(key) != null) {
				value = reader.getProperty(key);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}
}

