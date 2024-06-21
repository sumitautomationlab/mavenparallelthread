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

public class VoiceData {
	@SuppressWarnings("finally")
	public static int VoiceData(WebDriver driver, int column, Row row_num, int i, Cell cell) throws Exception {
		try {
			String notfound;
			TimeUnit.SECONDS.sleep(1);
			WebElement element = driver
					.findElement(By.xpath("//section[@class='TabMain row rightTab']//a[contains(text(),'VOICE')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			WebDriverWait wait5 = new WebDriverWait(driver, 130);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='row LOBRow']//div[contains(@id,'voiceServiceSummary')]//li[1]//p[1]")));
			TimeUnit.SECONDS.sleep(5);

			int service_trunk_number = driver
					.findElements(By
							.xpath("//div[@class='row LOBRow']//div[contains(@id,'voiceServiceSummary')]//p[contains(@class,'billingValue')]"))
					.size();

			if (service_trunk_number > 0) {
				for (int k = 0; k < service_trunk_number; k++) {
					String value = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//div[contains(@id,'voiceServiceSummary')]//p[contains(@class,'billingValue')]"))
							.get(k).getText();
					if (value.equals("")) {
						value = "Not Present in Voice LOB";
					}
					cell = row_num.createCell(column);
					cell.setCellValue(value);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + value);
					column = column + 1;
				}
			}

			int TN_details = driver
					.findElements(By
							.xpath("//div[@class='row LOBRow']//div[contains(@id,'voiceServiceSummary')]//td//span/em"))
					.size();
			String diago_1 = "";
			boolean flag1 = false;

			if (TN_details > 0) {
				for (int d = 0; d < TN_details; d++) {
					String diago_device = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//div[contains(@id,'voiceServiceSummary')]//td//span/em"))
							.get(d).getText();
					diago_1 = diago_1 + "\n" + diago_device;
					flag1 = true;
				}

				if (flag1) {
					cell = row_num.createCell(column);
					cell.setCellValue(diago_1);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + diago_1);
					column = column + 1;
				}
			} else {
				notfound = "TN details not present for Voice LOB";
				cell = row_num.createCell(column);
				cell.setCellValue(notfound);
				logging.LoggerInfo("TN details is not displayed for Voice LOB");
				column = column + 1;
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
				notfound = "Make/Model not present for Voice LOB";
				cell = row_num.createCell(column);
				cell.setCellValue(notfound);
				logging.LoggerInfo("Make/Model is not displayed for Voice LOB");
				column = column + 1;
			}

			TimeUnit.SECONDS.sleep(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return column;
	}

}