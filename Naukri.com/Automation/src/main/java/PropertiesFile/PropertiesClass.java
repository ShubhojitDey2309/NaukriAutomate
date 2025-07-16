package PropertiesFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesClass {

	public static String propertyValue(String value) throws IOException {

		String result = null;
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("C:\\Selenium\\Properties\\config.properties");
			prop.load(input);
			result = prop.getProperty(value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
