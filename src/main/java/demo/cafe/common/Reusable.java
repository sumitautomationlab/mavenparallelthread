package demo.cafe.common;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusable {
	static WebDriver driver = null;
	static String url;
	static String envn;
	static String mode;
	static String username;

	public static WebDriver Initialize() throws Exception {
		WebDriver driver = null;
		envn = DriverInitialize.getApplicationenvn();
		if (envn.contains("UAT")) {
			url = DriverInitialize.getApplicationUATURL();
		}

		if (envn.contains("PROD")) {
			url = DriverInitialize.getApplicationPRODURL();
		}

		mode = DriverInitialize.getApplicationmode();
		// username = DriverInitialize.getApplicationUsername();
		if (mode.equals("Headless")) {
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32 (3)\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			driver.get(url);
			TimeUnit.SECONDS.sleep(10);
		}
		if (mode.equals("Head")) {
			driver = DriverInitialize.getChromeDriver();
			driver.get(url);
			TimeUnit.SECONDS.sleep(10);
		}
		driver = login_sso(driver);
		String url1 = driver.getCurrentUrl();
		TimeUnit.SECONDS.sleep(10);
		boolean checkbox = driver.findElements(By.xpath("//label[@for='surveyOptOut']//em")).size() > 0;
		if (checkbox) {
			driver.findElement(By.xpath("//label[@for='surveyOptOut']//em")).click();
			TimeUnit.SECONDS.sleep(5);
			driver.findElement(By.xpath("//button[contains(@class,'confirm PrimaryBtn')]")).click();
		}
		WebDriverWait wait = new WebDriverWait(driver, 600);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Commercial Customer Search']")));
		url1 = driver.getCurrentUrl();
		logging.LoggerInfo("Login URL is " + url1);

		return driver;

	}

	public static WebDriver Close() throws Exception {
		driver.close();
		return driver;
	}

	public static String decrypt(String encodedBytes) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));
		return (new String(decodedBytes));
	}

	public static WebDriver login_sso(WebDriver driver) throws Exception {
		TimeUnit.SECONDS.sleep(5);
		String password = "Mamba@123";
		String email = "rmehro001c@cable.comcast.com";
		String login_url = driver.getCurrentUrl();
		WebDriverWait login_wait = new WebDriverWait(driver, 600);
		if (login_url.contains("login.microsoftonline.com")) {
			login_wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@type='email' and @name='loginfmt']")));
			TimeUnit.SECONDS.sleep(5);
			driver.findElement(By.xpath("//input[@type='email' and @name='loginfmt']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			login_wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='idA_PWD_ForgotPassword']")));
			TimeUnit.SECONDS.sleep(5);
			driver.findElement(By.xpath("//input[@type='password' and @name='passwd']")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
		} else {
			String sso_url = driver.getCurrentUrl();
			sso_url = sso_url.substring(8);
			sso_url = "https://" + "rmehro001c" + ":" + password + "@" + sso_url;
			driver.get(sso_url);
			TimeUnit.SECONDS.sleep(5);
		}

		return driver;
	}
}