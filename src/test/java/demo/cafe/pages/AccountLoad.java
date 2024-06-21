package demo.cafe.pages;


import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import demo.cafe.common.LocatorUtilities;
import demo.cafe.common.TestData_UAT;
import demo.cafe.common.logging;


public class AccountLoad 
{	
	public static int rowcount(WebDriver driver) throws Exception 
	{	
		
		int sheet_count = TestData_UAT.TestData_Excel("Account_Number");
		int row_count = TestData_UAT.getRow(sheet_count);
		return row_count;
	}
	
	public static String Accountno_Legacy(WebDriver driver, int row) throws Exception 
	{
		int sheet_count = TestData_UAT.TestData_Excel("Account_Number");
		String Account = TestData_UAT.getTestData(sheet_count, row, 0);
		logging.LoggerInfo("Legacy Account Number is: "+Account);
		return Account;
	}
	
	public static String Accountno_ORION_site(WebDriver driver, int row) throws Exception 
	{
		int sheet_count = TestData_UAT.TestData_Excel("Account_Number");
		String Account = TestData_UAT.getTestData_str(sheet_count, row, 1);
		logging.LoggerInfo("Orion Site Account Number is: "+Account);
		return Account;
	}
	
	public static String Accountno_ORION(WebDriver driver, int row) throws Exception 
	{
		int sheet_count = TestData_UAT.TestData_Excel("Account_Number");
		String Account = TestData_UAT.getTestData(sheet_count, row, 2);
		logging.LoggerInfo("Orion Account Number is: "+Account);
		return Account;
	}
	
	public static String Product_type (WebDriver driver, int row) throws Exception 
	{
		int sheet_count = TestData_UAT.TestData_Excel("Account_Number");
		String Account = TestData_UAT.getTestData(sheet_count, row, 3);
		logging.LoggerInfo("Product Type is: "+Account);
		return Account;
	}
	
	public static void WindowWithTitle(String title, WebDriver driver) {
		String wName = "";
		boolean isSwitched = false;
		try {
			SwithWindow: for (int i = 1; i <= 120; i++) {
				Set<String> windows = driver.getWindowHandles();
				Iterator<String> iterator = windows.iterator();
				while (iterator.hasNext()) {
					Thread.sleep(1000);
					wName = driver.switchTo().window(iterator.next()).getTitle();
					logging.LoggerInfo("\n window name[ " + wName + " ]");
					if (wName.equals(title)) {
						Thread.sleep(2000);
						isSwitched = true;
						logging.LoggerInfo("\n Swithched window after[" + (i) + " seconds]");
						break SwithWindow;
					}
				}
			}
			if (!isSwitched) {
				throw new RuntimeException("Unable to switch to window");
			}
		} catch (Exception e) {
			String eMsg = "Error occured while switching to [" + title + "]window: " + e.getMessage();
			logging.LoggerInfo(eMsg);
			throw new RuntimeException(eMsg);
		}
	}
	
}
