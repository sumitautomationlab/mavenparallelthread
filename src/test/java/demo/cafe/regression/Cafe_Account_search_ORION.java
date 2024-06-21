package demo.cafe.regression;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import demo.cafe.pages.Account_Excel_Merge;
import demo.cafe.pages.Account_Search;

public class Cafe_Account_search_ORION {
	private WebDriver driver1;
	private WebDriver driver2;
	private WebDriver driver3;
	private WebDriver driver4;
	private WebDriver driver5;
	private WebDriver driver6;
	private WebDriver driver7;
	private WebDriver driver8;
	private WebDriver driver9;
	private WebDriver driver10;
	private WebDriver driver11;
	
	ArrayList<String> accounts_orion = new ArrayList<String>();
	String final_time;
	@BeforeTest
	public void tearUp() throws Exception {
		driver1 = null;
		driver2 = null;
		driver3 = null;
		driver4 = null;
		driver5 = null;
		driver6 = null;
		driver7 = null;
		driver8 = null;
		driver9 = null;
		driver10 = null;
		driver11 = null;
	}
	@AfterTest
	public void tearDown() throws Exception {
		try {
			// Current Date
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MMM_dd hh_mm_ss aa");
			Date date = new Date();
			final_time = formatter.format(date);
			final_time = "ORION_"+final_time;
			Account_Excel_Merge.AccountExcelMergeOrion(final_time , accounts_orion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void accounts_11() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(250);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver11, 200, 253);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void accounts_10() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(225);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver10, 361, 417);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void accounts_9() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(200);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver9, 321, 360);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void accounts_8() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(175);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver8, 281, 320);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void accounts_7() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(150);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver7, 241, 280);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void accounts_6() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(125);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver6, 201, 240);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void accounts_5() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(100);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver5, 161, 200);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void accounts_4() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(75);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver4, 121, 160);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void accounts_3() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(50);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver3, 81, 120);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void accounts_2() throws Exception {
		try {
			TimeUnit.SECONDS.sleep(25);
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver2, 41, 80);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void accounts_1() throws Exception {
		try {
			String excel_time = Account_Search.AccountSearch_Legacy_ORION(driver1, 0, 40);
			accounts_orion.add(excel_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}