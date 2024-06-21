package demo.cafe.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Objects;

import demo.cafe.common.DriverInitialize;
import demo.cafe.common.LocatorUtilities;
import demo.cafe.common.Reusable;
import demo.cafe.common.TestData_UAT;
import demo.cafe.common.logging;

public class Account_Search {

	public static String AccountSearch_Legacy_UAT(WebDriver driver, int start, int end) throws Exception {
		// Current System time
		int trunk = 0;
		int internet = 0;
		int ethernet = 0;
		int bve = 0;
		int voice = 0;
		String Legacy_Account = null;
		ArrayList<String> notfoundaccount_uat = new ArrayList<String>();
		String date_time;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MMM_dd hh_mm_ss aa");
		Date date = new Date();
		date_time = formatter.format(date);
		// Creating new Excel for Legacy
		TestData_UAT.TestData_Create_UAT(date_time);
		File file = new File(".\\Test_Result\\Test_Data_Legacy_" + date_time + ".xlsx");
		int flag = TestData_UAT.TestData_Legacy_Excel("Cafe_UAT_Legacy_Account", date_time);
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		Row row_num;
		Cell cell;
		try {
			int column = 0;
			String notfound;
			// URL entered
			driver = Reusable.Initialize();
			String title = driver.getTitle();
			if (Objects.equal(title, "CAFE"))
				logging.LoggerInfo("Landing to cafe UAT customer search Page");
			int row_count = AccountLoad.rowcount(driver);
			logging.LoggerInfo("Total number of account Legacy account present: " + row_count);
			WebDriverWait pageload = new WebDriverWait(driver, 120);
			pageload.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
			TimeUnit.SECONDS.sleep(1);
			int j = 0;
			for (int i = start; i <= end; i++) {
				try {
					column = 0;
					logging.LoggerInfo("Account Search in Legacy Envn::");

					String Orion_Account_site = AccountLoad.Accountno_ORION_site(driver, i);
					String compare_value = AccountLoad.Accountno_ORION(driver, i);
					String Product_type = AccountLoad.Product_type(driver, i);
					Legacy_Account = AccountLoad.Accountno_Legacy(driver, i);

					boolean cafe_search = driver
							.findElements(By.xpath("//input[@placeholder='Commercial Customer Search']")).size() > 0;

					if (cafe_search) {
						LocatorUtilities.getLocator("account_search_textbox", driver).sendKeys(Legacy_Account);
						TimeUnit.SECONDS.sleep(1);
						LocatorUtilities.getLocator("account_search_textbox", driver).sendKeys(Keys.ENTER);
					} else {
						LocatorUtilities.getLocator("account_search_textbox_2nd", driver).sendKeys(Legacy_Account);
						TimeUnit.SECONDS.sleep(1);
						LocatorUtilities.getLocator("account_search_textbox_2nd", driver).sendKeys(Keys.ENTER);
					}
					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
					TimeUnit.SECONDS.sleep(1);

					boolean search_size_true = driver
							.findElements(By.xpath("//div[contains(@class,'universalSearchTable')]//td[1]")).size() > 1;
					if (search_size_true) {
						TimeUnit.SECONDS.sleep(1);
						driver.findElements(By.xpath("//div[contains(@class,'universalSearchTable')]//td[1]")).get(0)
								.click();
						// TimeUnit.SECONDS.sleep(5);
					}

					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));

					boolean account_success = driver
							.findElements(By.xpath("//div[@id='headerAccountNumber']//em[contains(text(),'')]"))
							.size() > 0;

					boolean popup_success = driver.findElements(By.xpath("//button[@id='btnOK']")).size() > 0;

					if (popup_success) {
						driver.findElement(By.xpath("//button[@id='btnOK']")).click();
					}
					if (account_success) {
						logging.LoggerInfo("Account Search is completed");
						TimeUnit.SECONDS.sleep(1);
					} else {
						logging.LoggerInfo("Account " + Legacy_Account
								+ " is not visiable in UAT or taking more than 1.5 mins to appear");
						String msg = "Account " + Legacy_Account
								+ " is not visiable in UAT or taking more than 1.5 mins to appear";
						notfoundaccount_uat.add(msg);
						TimeUnit.SECONDS.sleep(2);
						driver.navigate().refresh();
						TimeUnit.SECONDS.sleep(2);
						pageload.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//input[@placeholder='Commercial Customer Search']")));
						pageload.until(ExpectedConditions.invisibilityOfElementLocated(
								By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
						continue;
					}

					boolean customer_name = driver.findElements(By.xpath("//div[@class='accountAddress']/h1/span/em"))
							.size() > 0;
					boolean ENT_product = driver.findElements(By.xpath("//h1[text()='ENT']")).size() > 0;
					boolean SMB_product = driver.findElements(By.xpath("//h1[text()='SMB']")).size() > 0;
					boolean MIX_product = driver.findElements(By.xpath("//h1[text()='MIX']")).size() > 0;
					boolean NAT_product = driver.findElements(By.xpath("//h1[text()='NAT']")).size() > 0;
					boolean MIXED_PREM = driver.findElements(By.xpath("//h1[text()='MIXED-PREM']")).size() > 0;
					boolean RESI = driver.findElements(By.xpath("//h1[text()='RESI']")).size() > 0;
					boolean STRAT = driver.findElements(By.xpath("//h1[text()='STRAT']")).size() > 0;
					boolean NATSTRAT = driver.findElements(By.xpath("//h1[text()='NAT-STRAT']")).size() > 0;
					boolean ERATE = driver.findElements(By.xpath("//h1[text()='ERATE']")).size() > 0;
					boolean MIXED_GOV = driver.findElements(By.xpath("//h1[text()='MIXED-GOV']")).size() > 0;
					
					row_num = sheet.createRow(j);

					cell = row_num.createCell(column);
					cell.setCellValue(compare_value);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + compare_value);
					column = column + 1;

					cell = row_num.createCell(column);
					cell.setCellValue(Orion_Account_site);
					logging.LoggerInfo(
							"Value Inserted in Row " + i + " and Column " + column + " is " + Orion_Account_site);
					column = column + 1;

					cell = row_num.createCell(column);
					cell.setCellValue(Legacy_Account);
					logging.LoggerInfo(
							"Value Inserted in Row " + i + " and Column " + column + " is " + Legacy_Account);
					column = column + 1;

					if (customer_name) {
						String Account_Address_1 = LocatorUtilities.getLocator("account_address_1", driver).getText();
						cell = row_num.createCell(column);
						cell.setCellValue(Account_Address_1);
						logging.LoggerInfo(
								"Value Inserted in Row " + i + " and Column " + column + " is " + Account_Address_1);
						column = column + 1;
					} else {
						notfound = "Customer name is not present";
						cell = row_num.createCell(column);
						cell.setCellValue(notfound);
						logging.LoggerInfo("Customer name is not displayed");
						column = column + 1;
					}

					if (ENT_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("ENT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is ENT");
						column = column + 1;
					} else if (SMB_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("SMB");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is SMB");
						column = column + 1;
					} else if (MIX_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("MIX");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is MIX");
						column = column + 1;
					} else if (NAT_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("NAT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT");
						column = column + 1;
					} else if (MIXED_PREM) {
						cell = row_num.createCell(column);
						cell.setCellValue("MIXED-PREM");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is MIXED-PREM");
						column = column + 1;
					} else if (RESI) {
						cell = row_num.createCell(column);
						cell.setCellValue("RESI");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is RESI");
						column = column + 1;
					} else if (STRAT) {
						cell = row_num.createCell(column);
						cell.setCellValue("STRAT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is STRAT");
						column = column + 1;
					}else if (NATSTRAT) {
						cell = row_num.createCell(column);
						cell.setCellValue("NAT-STRAT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT-STRAT");
						column = column + 1;
					}else if (ERATE) {
						cell = row_num.createCell(column);
						cell.setCellValue("ERATE");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT-STRAT");
						column = column + 1;
					}else if (MIXED_GOV) {
						cell = row_num.createCell(column);
						cell.setCellValue("MIXED-GOV");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT-STRAT");
						column = column + 1;
					}else {
						notfound = "Account Type is not Present";
						cell = row_num.createCell(column);
						cell.setCellValue(notfound);
						logging.LoggerInfo("Account Type is not displayed");
						column = column + 1;
					}
					// Lob Count
					TimeUnit.SECONDS.sleep(5);
					List<WebElement> lob_size = driver
							.findElements(By.xpath("//section[@class='TabMain row rightTab']/ul/li"));
					String lobs = "";
					if (lob_size.size() > 0) {
						for (int l = 0; l < lob_size.size(); l++) {
							String lob = lob_size.get(l).getText();
							lobs = lobs + "\n" + lob;
							if (lob.equals("TRUNKING")) {
								trunk = 1;
							}

							if (lob.equals("INTERNET")) {
								internet = 1;
							}

							if (lob.equals("ETHERNET")) {
								ethernet = 1;
							}

							if (lob.equals("BVE")) {
								bve = 1;
							}

							if (lob.equals("VOICE")) {
								voice = 1;
							}
						}

						cell = row_num.createCell(column);
						cell.setCellValue(lobs);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + lobs);
						column = column + 1;
					}
					
					cell = row_num.createCell(column);
					cell.setCellValue(Product_type);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + Product_type);
					column = column + 1;
					TimeUnit.SECONDS.sleep(3);

					String GEN2LOB = DriverInitialize.getGEN2LOB();

					if (GEN2LOB.contains("Trunk")) {
						if (trunk == 1) {
							column = TrunkingData.trunking_data(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Trunk LOB not present";
							for (int t = 0; t<9; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN2LOB.contains("Internet")) {
						if (internet == 1) {
							column = InternetData.Internet_data(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Internet LOB not present";
							for (int t = 0; t<10; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN2LOB.contains("Ethernet")) {
						if (ethernet == 1) {
							column = EthernetData.Ethernet_data(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Ethernet LOB not present";
							for (int t = 0; t<2; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN2LOB.contains("BVE")) {
						if (bve == 1) {
							column = BVEData.BVEData(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "BVE LOB not present";
							for (int t = 0; t<9; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN2LOB.contains("Voice")) {
						if (voice == 1) {
							column = VoiceData.VoiceData(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Voice LOB not present";
							for (int t = 0; t<12; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}
					logging.LoggerInfo("Last Column value is " +column);
					column = 0;
					j = j + 1;
					trunk = 0;
					internet = 0;
					ethernet = 0;
					bve = 0;
					voice = 0;

					TimeUnit.SECONDS.sleep(2);
					driver.findElement(By.xpath("//*[@id='returnToSearchScreen']")).click();
					TimeUnit.SECONDS.sleep(2);
					WebDriverWait wait = new WebDriverWait(driver, 120);
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//input[@placeholder='Commercial Customer Search']")));
					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));

				} catch (Throwable e) {
					e.printStackTrace();
					logging.LoggerInfo("Account " + Legacy_Account
							+ " is not visiable in UAT or taking more than 55 secs to appear");
					String msg = "Account " + Legacy_Account
							+ " is not visiable in UAT or taking more than 55 secs to appear";
					notfoundaccount_uat.add(msg);
					TimeUnit.SECONDS.sleep(2);
					driver.navigate().refresh();
					TimeUnit.SECONDS.sleep(2);
					WebDriverWait wait = new WebDriverWait(driver, 120);
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//input[@placeholder='Commercial Customer Search']")));
					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
				}
			}
			FileOutputStream output = new FileOutputStream(file);
			wb.write(output);
			output.close();
			logging.LoggerInfo("No Found accounts " + notfoundaccount_uat);
			String parentWindowHandle = driver.getWindowHandle();
			driver.switchTo().window(parentWindowHandle).close();
			return date_time;

		} catch (Exception e) {
			e.printStackTrace();
			FileOutputStream output = new FileOutputStream(file);
			wb.write(output);
			output.close();
			logging.LoggerInfo("No Found accounts " + notfoundaccount_uat);
			logging.LoggerInfo("Error while loading UAT accounts in excel for driver" + e);
			return date_time;
		}
	}

	public static String AccountSearch_Legacy_ORION(WebDriver driver, int start, int end) throws Exception {
		// Current System time
		int trunk = 0;
		int internet = 0;
		int ethernet = 0;
		int bve = 0;
		int voice = 0;
		String Orion_Account_site = null;
		String date_time;
		ArrayList<String> notfoundaccount_orion = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MMM_dd hh_mm_ss aa");
		Date date = new Date();
		date_time = formatter.format(date);
		date_time = "ORION_" + date_time;
		// Creating new Excel for Legacy
		TestData_UAT.TestData_Create_UAT(date_time);
		File file = new File(".\\Test_Result\\Test_Data_Legacy_" + date_time + ".xlsx");
		int flag = TestData_UAT.TestData_Legacy_Excel("Cafe_UAT_Legacy_Account", date_time);
		FileInputStream fls = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		XSSFSheet sheet = wb.getSheetAt(flag);
		Row row_num;
		Cell cell;
		try {
			int column = 0;
			String notfound;
			// URL entered
			driver = Reusable.Initialize();
			String title = driver.getTitle();
			if (Objects.equal(title, "CAFE"))
				logging.LoggerInfo("Landing to cafe Orion customer search Page");
			int row_count = AccountLoad.rowcount(driver);
			logging.LoggerInfo("Total number of account Orion account present: " + row_count);
			WebDriverWait pageload = new WebDriverWait(driver, 240);
			pageload.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
			TimeUnit.SECONDS.sleep(1);
			int j = 0;
			for (int i = start; i <= end; i++) {
				try {
					column = 0;
					logging.LoggerInfo("Account Search in Orion Envn::");
					Orion_Account_site = AccountLoad.Accountno_ORION_site(driver, i);
					String compare_value = AccountLoad.Accountno_ORION(driver, i);
					String Product_type = AccountLoad.Product_type(driver, i);
					String Legacy_Account = AccountLoad.Accountno_Legacy(driver, i);
					boolean cafe_search = driver
							.findElements(By.xpath("//input[@placeholder='Commercial Customer Search']")).size() > 0;
					if (cafe_search) {
						pageload.until(ExpectedConditions.invisibilityOfElementLocated(
								By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
						LocatorUtilities.getLocator("account_search_textbox", driver).sendKeys(Orion_Account_site);
						TimeUnit.SECONDS.sleep(1);
						LocatorUtilities.getLocator("account_search_textbox", driver).sendKeys(Keys.ENTER);
					} else {
						pageload.until(ExpectedConditions.invisibilityOfElementLocated(
								By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
						LocatorUtilities.getLocator("account_search_textbox_2nd", driver).sendKeys(Orion_Account_site);
						TimeUnit.SECONDS.sleep(1);
						LocatorUtilities.getLocator("account_search_textbox_2nd", driver).sendKeys(Keys.ENTER);
					}

					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
					TimeUnit.SECONDS.sleep(1);

					boolean search_size_true = driver
							.findElements(By.xpath("//div[contains(@class,'universalSearchTable')]//td[1]")).size() > 1;
					if (search_size_true) {
						driver.findElements(By.xpath("//div[contains(@class,'universalSearchTable')]//td[1]")).get(0)
								.click();
						TimeUnit.SECONDS.sleep(1);
					}

					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
					TimeUnit.SECONDS.sleep(1);

					boolean account_success = driver
							.findElements(By.xpath("//div[@id='headerAccountNumber']//em[contains(text(),'')]"))
							.size() > 0;

					boolean popup_success = driver.findElements(By.xpath("//button[@id='btnOK']")).size() > 0;
					if (popup_success) {
						driver.findElement(By.xpath("//button[@id='btnOK']")).click();
					}

					if (account_success) {
						logging.LoggerInfo("Account Search is completed");
					} else {
						logging.LoggerInfo("Account Site " + Orion_Account_site
								+ " is not visiable in ORION or taking more than 1.5 mins to appear");
						String msg = "Account Site" + Orion_Account_site
								+ " is not visiable in ORION or taking more than 1.5 mins to appear";
						notfoundaccount_orion.add(msg);
						TimeUnit.SECONDS.sleep(2);
						driver.navigate().refresh();
						TimeUnit.SECONDS.sleep(2);
						pageload.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//input[@placeholder='Commercial Customer Search']")));
						pageload.until(ExpectedConditions.invisibilityOfElementLocated(
								By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));
						continue;
					}

					boolean customer_name = driver.findElements(By.xpath("//div[@class='accountAddress']/h1/span/em"))
							.size() > 0;
					boolean ENT_product = driver.findElements(By.xpath("//h1[text()='ENT']")).size() > 0;
					boolean SMB_product = driver.findElements(By.xpath("//h1[text()='SMB']")).size() > 0;
					boolean MIX_product = driver.findElements(By.xpath("//h1[text()='MIX']")).size() > 0;
					boolean NAT_product = driver.findElements(By.xpath("//h1[text()='NAT']")).size() > 0;
					boolean MIXED_PREM = driver.findElements(By.xpath("//h1[text()='MIXED-PREM']")).size() > 0;
					boolean RESI = driver.findElements(By.xpath("//h1[text()='RESI']")).size() > 0;
					boolean STRAT = driver.findElements(By.xpath("//h1[text()='STRAT']")).size() > 0;
					boolean NATSTRAT = driver.findElements(By.xpath("//h1[text()='NAT-STRAT']")).size() > 0;
					boolean ERATE = driver.findElements(By.xpath("//h1[text()='ERATE']")).size() > 0;
					boolean MIXED_GOV = driver.findElements(By.xpath("//h1[text()='MIXED-GOV']")).size() > 0;
					
					row_num = sheet.createRow(j);

					cell = row_num.createCell(column);
					cell.setCellValue(compare_value);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + compare_value);
					column = column + 1;

					cell = row_num.createCell(column);
					cell.setCellValue(Orion_Account_site);
					logging.LoggerInfo(
							"Value Inserted in Row " + i + " and Column " + column + " is " + Orion_Account_site);
					column = column + 1;

					cell = row_num.createCell(column);
					cell.setCellValue(Legacy_Account);
					logging.LoggerInfo(
							"Value Inserted in Row " + i + " and Column " + column + " is " + Legacy_Account);
					column = column + 1;

					if (customer_name) {
						String Account_Address_1 = LocatorUtilities.getLocator("account_address_1", driver).getText();
						cell = row_num.createCell(column);
						cell.setCellValue(Account_Address_1);
						logging.LoggerInfo(
								"Value Inserted in Row " + i + " and Column " + column + " is " + Account_Address_1);
						column = column + 1;
					} else {
						notfound = "Customer name is not present";
						cell = row_num.createCell(column);
						cell.setCellValue(notfound);
						logging.LoggerInfo("Customer name is not displayed");
						column = column + 1;
					}

					if (ENT_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("ENT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is ENT");
						column = column + 1;
					} else if (SMB_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("SMB");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is SMB");
						column = column + 1;
					} else if (MIX_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("MIX");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is MIX");
						column = column + 1;
					} else if (NAT_product) {
						cell = row_num.createCell(column);
						cell.setCellValue("NAT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT");
						column = column + 1;
					} else if (MIXED_PREM) {
						cell = row_num.createCell(column);
						cell.setCellValue("MIXED-PREM");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is MIXED-PREM");
						column = column + 1;
					} else if (RESI) {
						cell = row_num.createCell(column);
						cell.setCellValue("RESI");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is RESI");
						column = column + 1;
					} else if (STRAT) {
						cell = row_num.createCell(column);
						cell.setCellValue("STRAT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is STRAT");
						column = column + 1;
					}else if (NATSTRAT) {
						cell = row_num.createCell(column);
						cell.setCellValue("NAT-STRAT");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT-STRAT");
						column = column + 1;
					}else if (ERATE) {
						cell = row_num.createCell(column);
						cell.setCellValue("ERATE");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT-STRAT");
						column = column + 1;
					}else if (MIXED_GOV) {
						cell = row_num.createCell(column);
						cell.setCellValue("MIXED-GOV");
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is NAT-STRAT");
						column = column + 1;
					}else {
						notfound = "Account Type is not Present";
						cell = row_num.createCell(column);
						cell.setCellValue(notfound);
						logging.LoggerInfo("Account Type is not displayed");
						column = column + 1;
					}
					// Lob Count
					List<WebElement> lob_size = driver
							.findElements(By.xpath("//section[@class='TabMain row rightTab']/ul/li"));
					String lobs = "";
					TimeUnit.SECONDS.sleep(5);
					if (lob_size.size() > 0) {
						for (int l = 0; l < lob_size.size(); l++) {
							String lob = lob_size.get(l).getText();
							lobs = lobs + "\n" + lob;
							// lob_size.get(l).click();
							// TimeUnit.SECONDS.sleep(4);
							if (lob.equals("TRUNKING")) {
								trunk = 1;
							}

							if (lob.equals("INTERNET")) {
								internet = 1;
							}

							if (lob.equals("ETHERNET")) {
								ethernet = 1;
							}

							if (lob.equals("BVE")) {
								bve = 1;
							}

							if (lob.equals("VOICE")) {
								voice = 1;
							}
						}

						cell = row_num.createCell(column);
						cell.setCellValue(lobs);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + lobs);
						column = column + 1;
					}
					
					cell = row_num.createCell(column);
					cell.setCellValue(Product_type);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + Product_type);
					column = column + 1;

					String GEN3LOB = DriverInitialize.getGEN3LOB();
					TimeUnit.SECONDS.sleep(3);
					if (GEN3LOB.contains("Trunk")) {
						if (trunk == 1) {
							column = TrunkingData.trunking_data(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Trunk LOB not present";
							for (int t = 0; t<9; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN3LOB.contains("Internet")) {
						if (internet == 1) {
							column = InternetData.Internet_data(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Internet LOB not present";
							for (int t = 0; t<10; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN3LOB.contains("Ethernet")) {
						if (ethernet == 1) {
							column = EthernetData.Ethernet_data(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Ethernet LOB not present";
							for (int t = 0; t<2; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN3LOB.contains("BVE")) {
						if (bve == 1) {
							column = BVEData.BVEData(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "BVE LOB not present";
							for (int t = 0; t<9; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}

					if (GEN3LOB.contains("Voice")) {
						if (voice == 1) {
							column = VoiceData.VoiceData(driver, column, row_num, i, cell);
							logging.LoggerInfo("Column value is " + column);
							TimeUnit.SECONDS.sleep(1);
						} else {
							String data = "Voice LOB not present";
							for (int t = 0; t<12; t++) {
								cell = row_num.createCell(column);
								cell.setCellValue(data);
								//logging.LoggerInfo(data);
								column = column + 1;
							}
						}
					}
					logging.LoggerInfo("Last Column value is " +column);
					
					column = 0;
					j = j + 1;
					trunk = 0;
					internet = 0;
					ethernet = 0;
					bve = 0;
					voice = 0;

					TimeUnit.SECONDS.sleep(2);
					driver.findElement(By.xpath("//*[@id='returnToSearchScreen']")).click();
					TimeUnit.SECONDS.sleep(2);
					WebDriverWait wait = new WebDriverWait(driver, 120);
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//input[@placeholder='Commercial Customer Search']")));
					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));

				} catch (Throwable e) {
					logging.LoggerInfo("Account Site " + Orion_Account_site
							+ " is not visiable in ORION or taking more than 55 secs to appear");
					String msg = "Account Site" + Orion_Account_site
							+ " is not visiable in ORION or taking more than 55 secs to appear";
					notfoundaccount_orion.add(msg);
					TimeUnit.SECONDS.sleep(2);
					driver.navigate().refresh();
					TimeUnit.SECONDS.sleep(2);
					WebDriverWait wait = new WebDriverWait(driver, 120);
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//input[@placeholder='Commercial Customer Search']")));
					pageload.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//div[@class='maskLoaderOuterWrapper animate']")));

				}
			}
			FileOutputStream output = new FileOutputStream(file);
			wb.write(output);
			output.close();
			logging.LoggerInfo("No Found accounts " + notfoundaccount_orion);
			String parentWindowHandle = driver.getWindowHandle();
			driver.switchTo().window(parentWindowHandle).close();
			return date_time;

		} catch (Exception e) {
			e.printStackTrace();
			FileOutputStream output = new FileOutputStream(file);
			wb.write(output);
			output.close();
			logging.LoggerInfo("No Found accounts " + notfoundaccount_orion);
			logging.LoggerInfo("Error while loading ORION accounts in excel for driver" + e);
			return date_time;
		}
	}
}