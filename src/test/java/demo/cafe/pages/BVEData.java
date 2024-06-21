package demo.cafe.pages;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.cafe.common.LocatorUtilities;
import demo.cafe.common.TestData_UAT;
import demo.cafe.common.logging;

public class BVEData {
	@SuppressWarnings("finally")
	public static int BVEData(WebDriver driver, int column, Row row_num, int i, Cell cell) throws Exception {
		try {
			String notfound;
			TimeUnit.SECONDS.sleep(1);
			WebElement element = driver
					.findElement(By.xpath("//section[@class='TabMain row rightTab']//a[contains(text(),'BVE')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			WebDriverWait wait5 = new WebDriverWait(driver, 60);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='row LOBRow']//ul[contains(@class,'bveServiceSummary')]/li[1]/p[1]")));
			TimeUnit.SECONDS.sleep(5);
			WebDriverWait wait6 = new WebDriverWait(driver, 120);
			wait6.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//p[contains(@class,'refreshIcon dhcpRefresh')]")));
			// String notfound = null;
			boolean flag1 = false;
			int service_trunk_number = driver
					.findElements(By
							.xpath("//div[@class='row LOBRow']//ul[contains(@class,'bveServiceSummary')]//p[contains(@class,'billingValue')]"))
					.size();
			boolean enter_group_id = driver
					.findElements(
							By.xpath("//h1[contains(text(),' Enter Group ID ')]//following::input[@id='groupID']"))
					.size() > 0;
			if (enter_group_id) {
				String value = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[contains(@class,'bveServiceSummary')]//p[contains(@class,'billingValue')]"))
						.get(0).getText();
				cell = row_num.createCell(column);
				cell.setCellValue(value);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + value);
				column = column + 1;
				flag1 = true;
			} else if (service_trunk_number > 0) {
				for (int k = 0; k < service_trunk_number; k++) {
					String value = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[contains(@class,'bveServiceSummary')]//p[contains(@class,'billingValue')]"))
							.get(k).getText();
					if (value.equals("")) {
						value = "Not Present in BVE LOB";
					}
					cell = row_num.createCell(column);
					cell.setCellValue(value);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + value);
					column = column + 1;
				}
			}

			if (flag1) {
				for (int k = 1; k < service_trunk_number; k++) {
					String value = "Enter Group ID text present, no parameter value present";
					cell = row_num.createCell(column);
					cell.setCellValue(value);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + value);
					column = column + 1;
				}
			}

			TimeUnit.SECONDS.sleep(3);

			int make_model = driver.findElements(By.xpath("//div[@id='diagnostics']//ul[@class='vtabs']/li/span[2]"))
					.size();
			String diago = "";
			boolean flag = false;

			if (make_model > 0) {
				for (int d = 0; d < make_model; d++) {
					String diago_device = driver
							.findElements(By.xpath("//div[@id='diagnostics']//ul[@class='vtabs']/li/span[2]")).get(d)
							.getText();
					diago = diago + "\n" + diago_device;
					flag = true;
				}

				if (flag) {
					cell = row_num.createCell(column);
					cell.setCellValue(diago);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + diago);
					column = column + 1;
				}
			} else {
				notfound = "Make/Model not present for BVE LOB";
				cell = row_num.createCell(column);
				cell.setCellValue(notfound);
				logging.LoggerInfo("Make/Model is not displayed for BVE LOB");
				column = column + 1;
			}
			TimeUnit.SECONDS.sleep(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return column; 
	}

}