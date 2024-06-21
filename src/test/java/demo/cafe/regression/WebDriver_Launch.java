package demo.cafe.regression;

import demo.cafe.pages.Account_Excel_Merge;
import demo.cafe.pages.Account_Search;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WebDriver_Launch {
	private WebDriver driver1;
	ArrayList<String> accounts_uat = new ArrayList<String>();
	String final_time;

	@BeforeTest
	public void tearUp() throws Exception {
		driver1 = null;
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver1.quit();
	}

	@Test
	public void accounts_1() throws Exception {
		WebDr
		WebDriver driver = new ChromeDriver();
	}
}