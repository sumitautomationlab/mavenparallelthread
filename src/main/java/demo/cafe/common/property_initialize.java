package demo.cafe.common;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class property_initialize {
	
	public static Properties applicationProps;
	public static Properties locatorProps;
	public static void loadapplicationProperty() {
		String propertiesFileLocation = System.getProperty("user.dir")
				+ "//src//main//resources//application.properties";
		try {
			applicationProps = new Properties();
			FileInputStream propfile = new FileInputStream(propertiesFileLocation);
			applicationProps.load(propfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void loadlocatorProperty() {
			String propertiesFileLocation = System.getProperty("user.dir")
					+ "//src//main//resources//locators.properties";
			try {
				locatorProps = new Properties();
				FileInputStream propfile = new FileInputStream(propertiesFileLocation);
				locatorProps.load(propfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
