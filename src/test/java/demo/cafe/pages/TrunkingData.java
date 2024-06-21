package demo.cafe.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.cafe.common.LocatorUtilities;
import demo.cafe.common.TestData_UAT;
import demo.cafe.common.logging;

public class TrunkingData {
	@SuppressWarnings({ "finally" })
	public static int trunking_data(WebDriver driver, int column, Row row_num, int i, Cell cell) throws Exception {
		try {
			String notfound;
			TimeUnit.SECONDS.sleep(3);
			WebElement element = driver
					.findElement(By.xpath("//section[@class='TabMain row rightTab']//a[contains(text(),'TRUNKING')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			WebDriverWait wait5 = new WebDriverWait(driver, 180);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']/li[1]/p[1]")));
			TimeUnit.SECONDS.sleep(5);
			WebDriverWait waittr = new WebDriverWait(driver, 240);
			waittr.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
					"//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']//parent::div//parent::div//parent::div/div[@class='widgetLoaderWrap show']")));
			TimeUnit.SECONDS.sleep(5);
			boolean service_trunk_number = driver
					.findElements(By
							.xpath("//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']//p[@class='billingValue']"))
					.size() > 4;
			if (service_trunk_number) {
				String myaccountbusiness = driver
						.findElement(By
								.xpath("//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='My Account for Business']/parent::p/parent::li/p[2]"))
						.getText();
				cell = row_num.createCell(column);
				if (myaccountbusiness.equals("")) {
					myaccountbusiness = "Not Present in Trunk LOB";
				}
				cell.setCellValue(myaccountbusiness);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + myaccountbusiness);
				column = column + 1;

				String switch_lob_s = driver
						.findElement(By
								.xpath("//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Switch:']/parent::p/parent::li/p[2]"))
						.getText();
				cell = row_num.createCell(column);
				if (switch_lob_s.equals("")) {
					switch_lob_s = "Not Present in Trunk LOB";
				}
				cell.setCellValue(switch_lob_s);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + switch_lob_s);
				column = column + 1;

				String transport_s = driver
						.findElement(By.xpath("//*[text()='Transport:']/parent::p/parent::li/p[2]/span")).getText();
				cell = row_num.createCell(column);
				if (transport_s.equals("")) {
					transport_s = "Not Present in Trunk LOB";
				}
				cell.setCellValue(transport_s);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + transport_s);
				column = column + 1;

				String product_s = driver
						.findElement(By
								.xpath("//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Product:']/parent::p/parent::li/p[2]"))
						.getText();
				cell = row_num.createCell(column);
				if (product_s.equals("")) {
					product_s = "Not Present in Trunk LOB";
				}
				cell.setCellValue(product_s);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + product_s);
				column = column + 1;

				String TN_s = driver
						.findElement(By
								.xpath("//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Total TNs:']/parent::p/parent::li/p[2]"))
						.getText();
				cell = row_num.createCell(column);
				if (TN_s.equals("")) {
					TN_s = "Not Present in Trunk LOB";
				}
				cell.setCellValue(TN_s);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + TN_s);
				column = column + 1;
				// TimeUnit.SECONDS.sleep(15);
				driver.findElement(By
						.xpath("//div[@class='row LOBRow']//ul[@id='trunkingServiceSummary']//parent::div//parent::div//parent::div//parent::div//parent::div/h2//a"))
						.click();
				String parentWindowHandle = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				Iterator<String> iterator = windows.iterator();
				for (int k = 0; k < 2; k++) {
					driver.switchTo().window(iterator.next());
					TimeUnit.SECONDS.sleep(5);
					logging.LoggerInfo("Page Title is :" + driver.getTitle());
				}
				// TimeUnit.SECONDS.sleep(5);
				ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript("return document.readyState")
								.equals("complete");
					}
				};
				WebDriverWait wait12 = new WebDriverWait(driver, 240);
				wait12.until(pageLoadCondition);
				/*
				 * WebDriverWait wait = new WebDriverWait(driver, 120);
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
				 * xpath("//h1[text()='TRUNK GROUP DETAILS']")));
				 */

				int trunkheader = driver.findElements(By.xpath("//*[@class='trunkingDetailsPopup']//h1")).size();
				String childwindowhandle = driver.getWindowHandle();
				boolean trunkheaderpresent = driver.findElements(By.xpath("//*[@class='trunkingDetailsPopup']//h1"))
						.size() > 0;
				if (trunkheaderpresent) {
					String trunkheaders = "";
					for (int p = 0; p < trunkheader; p++) {
						String trunkstring = driver.findElements(By.xpath("//*[@class='trunkingDetailsPopup']//h1"))
								.get(p).getText();
						trunkheaders = trunkheaders + "\n" + trunkstring;

					}
					cell = row_num.createCell(column);
					cell.setCellValue(trunkheaders);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + trunkheaders);
					column = column + 1;
				} else {
					notfound = "Trunk Header not present";
					cell = row_num.createCell(column);
					cell.setCellValue(notfound);
					logging.LoggerInfo("Trunk Header is not displayed for Trunk LOB");
					column = column + 1;
				}

				boolean E99_trunk_present = driver
						.findElements(By.xpath("//*[@id='tooltipIcon' and @title='E911 Details']")).size() > 0;

				if (E99_trunk_present) {
					int E99_trunk_size = driver
							.findElements(By.xpath("//*[@id='tooltipIcon' and @title='E911 Details']")).size();
					for (int q = 0; q < E99_trunk_size; q++) {
						driver.findElements(By.xpath("//*[@id='tooltipIcon' and @title='E911 Details']")).get(q)
								.click();
						TimeUnit.SECONDS.sleep(2);
					}
					driver.findElements(By.xpath("//*[@id='tooltipIcon' and @title='E911 Details']")).get(0).click();
					TimeUnit.SECONDS.sleep(5);

					boolean E99_details_check = driver
							.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
							.size() > 0;
					boolean E99_deta_heading = driver
							.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[1]/b"))
							.size() > 0;
					if (E99_details_check && E99_deta_heading) {
						int E99_details_size = driver
								.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
								.size();
						String e99 = "";
						boolean status = false;
						for (int j = 0; j < E99_details_size; j++) {
							String E99_details_heading = driver
									.findElements(
											By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[1]/b"))
									.get(j).getText();
							String E99_details = driver
									.findElements(
											By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
									.get(j).getText();
							e99 = e99 + "\n" + E99_details_heading + "  " + E99_details;
							status = true;
						}
						if (status) {
							cell = row_num.createCell(column);
							cell.setCellValue(e99);
							logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + e99);
							column = column + 1;
						}
					} else {
						notfound = "E911 not present";
						cell = row_num.createCell(column);
						cell.setCellValue(notfound);
						logging.LoggerInfo("E911 is not displayed for Trunk LOB");
						column = column + 1;
					}
				} else {
					notfound = "E911 not present";
					cell = row_num.createCell(column);
					cell.setCellValue(notfound);
					logging.LoggerInfo("E911 is not displayed for Trunk LOB");
					column = column + 1;
				}

				TimeUnit.SECONDS.sleep(1);

				boolean LRN_Details = driver.findElements(By.xpath("//*[@id='tooltipIcon' and @title='LRN Details']"))
						.size() > 0;
				if (LRN_Details) {
					int LRN_trunk_size = driver
							.findElements(By.xpath("//*[@id='tooltipIcon' and @title='LRN Details']")).size();
					for (int q = 0; q < LRN_trunk_size; q++) {
						driver.findElements(By.xpath("//*[@id='tooltipIcon' and @title='LRN Details']")).get(q).click();
						TimeUnit.SECONDS.sleep(2);
					}
					driver.findElements(By.xpath("//*[@id='tooltipIcon' and @title='LRN Details']")).get(0).click();
					TimeUnit.SECONDS.sleep(5);
					boolean LRN_details_check = driver
							.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
							.size() > 0;
					boolean LRN_deta_heading = driver
							.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[1]/b"))
							.size() > 0;
					if (LRN_details_check && LRN_deta_heading) {
						int LRN_details_size = driver
								.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
								.size();
						String e99 = "";
						boolean status = false;
						for (int j = 0; j < LRN_details_size; j++) {
							String E99_details_heading = driver
									.findElements(
											By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[1]/b"))
									.get(j).getText();
							String E99_details = driver
									.findElements(
											By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
									.get(j).getText();
							e99 = e99 + "\n" + E99_details_heading + "  " + E99_details;
							status = true;
						}
						if (status) {
							cell = row_num.createCell(column);
							cell.setCellValue(e99);
							logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + e99);
							column = column + 1;
						}
					} else {
						notfound = "LRN not present";
						cell = row_num.createCell(column);
						cell.setCellValue(notfound);
						logging.LoggerInfo("LRN is not displayed for Trunk LOB");
						column = column + 1;
					}
				} else {
					notfound = "LRN not present";
					cell = row_num.createCell(column);
					cell.setCellValue(notfound);
					logging.LoggerInfo("LRN is not displayed for Trunk LOB");
					column = column + 1;
				}

				boolean pri_heading = driver.findElements(By.xpath("//h1[text()='PRI TRUNK CONFIGURATION']"))
						.size() > 0;
				if (pri_heading) {
					boolean pri_trunking = driver
							.findElements(
									By.xpath("//a[@id='tooltipIcon' and @title='PRI Trunk Configuration Details']"))
							.size() > 0;
					if (pri_trunking) {
						driver.findElements(
								By.xpath("//*[@id='tooltipIcon' and @title='PRI Trunk Configuration Details']")).get(0)
								.click();
						WebDriverWait wait1 = new WebDriverWait(driver, 180);
						wait1.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@class='tooltipContent']//div[@class='show']//li[1]/p[1]/b")));

						int E99_details_size = driver
								.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
								.size();
						boolean E99_details_check = driver
								.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
								.size() > 0;
						boolean E99_deta_heading = driver
								.findElements(By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[1]/b"))
								.size() > 0;
						if (E99_details_check && E99_deta_heading) {
							String e99 = "";
							boolean status = false;
							for (int j = 0; j < E99_details_size; j++) {
								String E99_details_heading = driver
										.findElements(By
												.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[1]/b"))
										.get(j).getText();
								String E99_details = driver
										.findElements(
												By.xpath("//div[@class='tooltipContent']//div[@class='show']//li/p[2]"))
										.get(j).getText();
								e99 = e99 + "\n" + E99_details_heading + "  " + E99_details;
								status = true;
							}
							if (status) {
								cell = row_num.createCell(column);
								cell.setCellValue(e99);
								logging.LoggerInfo(
										"Value Inserted in Row " + i + " and Column " + column + " is " + e99);
								column = column + 1;
							}
						}
					}
				} else {
					notfound = "PRI Trunk Config not present";
					cell = row_num.createCell(column);
					cell.setCellValue(notfound);
					logging.LoggerInfo("PRI Trunk Config is not displayed for Trunk LOB");
					column = column + 1;
				}
				driver.switchTo().window(childwindowhandle).close();
				TimeUnit.SECONDS.sleep(1);
				driver.switchTo().window(parentWindowHandle);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return column;
		
	}

}
