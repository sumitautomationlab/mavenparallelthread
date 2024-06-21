package demo.cafe.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/*import org.openqa.selenium.firefox.FirefoxDriver;*/
import com.github.windpapi4j.WinDPAPI;

public class DriverInitialize {
	public static WebDriver driver = null;

	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static String getApplicationUATURL() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationURL = property_initialize.applicationProps.getProperty("application.Url.UAT");
		return applicationURL;
	}

	public static String getApplicationPRODURL() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationURL = property_initialize.applicationProps.getProperty("application.Url.PROD");
		return applicationURL;
	}

	public static String getApplicationUsername() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationUsername = property_initialize.applicationProps.getProperty("application.Username");
		return applicationUsername;
	}

	public static String getApplicationPassword() throws Exception {
		// WinDPAPI winDPAPI = null;
		property_initialize.loadapplicationProperty();
		String applicationPassword = property_initialize.applicationProps.getProperty("application.Password");
		// applicationPassword=Reusable.decrypt(applicationPassword);
		return applicationPassword;
	}

	public static String getApplicationenvn() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationUsername = property_initialize.applicationProps.getProperty("application.envn");
		return applicationUsername;
	}

	public static String getApplicationmode() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationUsername = property_initialize.applicationProps.getProperty("application.mode");
		return applicationUsername;
	}

	public static String getGEN2LOB() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationUsername = property_initialize.applicationProps.getProperty("application.GEN2.LOB");
		return applicationUsername;
	}

	public static String getGEN3LOB() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationUsername = property_initialize.applicationProps.getProperty("application.GEN3.LOB");
		return applicationUsername;
	}
	
	public static String getComparisonLOB() throws Exception {
		property_initialize.loadapplicationProperty();
		String applicationUsername = property_initialize.applicationProps.getProperty("comparison.LOB");
		return applicationUsername;
	}
	
	public static String getAnalyticsFile() throws Exception {
		property_initialize.loadapplicationProperty();
		String analytics_file = property_initialize.applicationProps.getProperty("analytics.file");
		return analytics_file;
	}
}