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

public class InternetData {
	@SuppressWarnings("finally")
	public static int Internet_data(WebDriver driver, int column, Row row_num, int i, Cell cell) throws Exception {
		try {
			String notfound;
			TimeUnit.SECONDS.sleep(1);
			WebElement element = driver
					.findElement(By.xpath("//section[@class='TabMain row rightTab']//a[contains(text(),'INTERNET')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			WebDriverWait wait5 = new WebDriverWait(driver, 60);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']/li[1]/p[1]")));
			TimeUnit.SECONDS.sleep(2);
			int int_service_summary = driver
					.findElements(By
							.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']/li[contains(@class,'row')]"))
					.size();
			boolean status = false;
			if (int_service_summary > 0) {
				boolean my_account = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='My Account for Business']"))
						.size() > 0;
				boolean package_data = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Package']"))
						.size() > 0;
				boolean Download_Speed = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Download Speed']"))
						.size() > 0;
				boolean Upload_Speed = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Upload Speed']"))
						.size() > 0;
				boolean Transport = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Transport']"))
						.size() > 0;
				boolean Gateway_Static_IP = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Gateway Static IP']"))
						.size() > 0;
				boolean Usable_Static_IP_Range = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Usable Static IP Range']"))
						.size() > 0;
				boolean Add_ons = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Add ons']"))
						.size() > 0;
				boolean IPv6_Prefix_Delegation = driver
						.findElements(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='IPv6 Prefix Delegation']"))
						.size() > 0;
				String data;
				if (my_account) {
					/*
					 * boolean valid = driver .findElements(By
					 * .xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//*[text()='My Account for Business']/parent::p/parent::li//em"
					 * )) .size() > 0;
					 */
					boolean not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='My Account for Business']/parent::p/parent::li/p[2]"))
							.size() > 0;
					if (not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='My Account for Business']/parent::p/parent::li/p[2]"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "My Account for Business not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "My Account for Business not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (package_data) {
					boolean valid_not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Package']/parent::p/parent::li/p[2]"))
							.size() > 0;
					if (valid_not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Package']/parent::p/parent::li/p[2]"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "Package not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "Package not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (Download_Speed) {
					boolean valid_not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Download Speed']/parent::p/parent::li/p[2]"))
							.size() > 0;
					if (valid_not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Download Speed']/parent::p/parent::li/p[2]"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "Download Speed not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "Download Speed not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (Upload_Speed) {
					boolean valid_not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Upload Speed']/parent::p/parent::li/p[2]"))
							.size() > 0;
					if (valid_not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Upload Speed']/parent::p/parent::li/p[2]"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "Upload Speed not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "Upload Speed not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (Transport) {
					boolean valid_not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Transport']/parent::p/parent::li/p[2]"))
							.size() > 0;
					if (valid_not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Transport']/parent::p/parent::li/p[2]"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "Transport not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "Transport not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (Gateway_Static_IP) {
					boolean valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Gateway Static IP']/following::ul[contains(@class,'gateWayIpIndicator')]//em"))
							.size() > 0;
					boolean not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Gateway Static IP']/following::ul[text()='None']"))
							.size() > 0;
					if (valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Gateway Static IP']/following::ul[contains(@class,'gateWayIpIndicator')]//em"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else if (not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Gateway Static IP']/following::ul[text()='None']"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "Gateway Static IP not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "Gateway Static IP not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (Usable_Static_IP_Range) {
					boolean valid_not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Usable Static IP Range']/parent::p/parent::li//em"))
							.size() > 0;
					if (valid_not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Usable Static IP Range']/parent::p/parent::li//em"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "Usable Static IP Range not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "Usable Static IP Range not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (Add_ons) {
					boolean valid_not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Add ons']/parent::p/parent::li/p[2]"))
							.size() > 0;
					if (valid_not_valid) {
						data = driver
								.findElement(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='Add ons']/parent::p/parent::li/p[2]"))
								.getText();
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "Add ons not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "Add ons not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

				if (IPv6_Prefix_Delegation) {
					boolean valid_not_valid = driver
							.findElements(By
									.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='IPv6 Prefix Delegation']/following::a[@id='prefixDelegationIcon']"))
							.size() > 0;
					if (valid_not_valid) {
						driver.findElement(By
								.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='IPv6 Prefix Delegation']/following::a[@id='prefixDelegationIcon']"))
								.click();
						TimeUnit.SECONDS.sleep(3);
						WebDriverWait wait6 = new WebDriverWait(driver, 120);
						wait6.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//span[@id='refresh_prefixDelegationIcon']")));
						TimeUnit.SECONDS.sleep(3);
						boolean IPv6_Prefix_Delegation_valid = driver
								.findElements(By
										.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='IPv6 Prefix Delegation']/parent::p/parent::li/p[2]//em"))
								.size() > 0;

						if (IPv6_Prefix_Delegation_valid) {
							data = driver
									.findElement(By
											.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='IPv6 Prefix Delegation']/parent::p/parent::li/p[2]//em"))
									.getText();
						} else {
							data = driver
									.findElement(By
											.xpath("//div[@class='row LOBRow']//ul[@id='internetServiceSummary']//li[not(contains(@class,'hide'))]//*[text()='IPv6 Prefix Delegation']/parent::p/parent::li/p[2]"))
									.getText();
						}

						if (data.equals("")) {
							data = "Not Found";
						}
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					} else {
						data = "IPv6 Prefix Delegation not Found";
						cell = row_num.createCell(column);
						cell.setCellValue(data);
						logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
						column = column + 1;
					}
				} else {
					data = "IPv6 Prefix Delegation not Found";
					cell = row_num.createCell(column);
					cell.setCellValue(data);
					logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + data);
					column = column + 1;
				}

			} else {
				notfound = "Internet Service Summary not present for Internet LOB";
				cell = row_num.createCell(column);
				cell.setCellValue(notfound);
				logging.LoggerInfo("Internet Service Summary is not displayed for Internet LOB");
				column = column + 1;
			}

			TimeUnit.SECONDS.sleep(1);

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
				notfound = "Make/Model not present for Internet LOB";
				cell = row_num.createCell(column);
				cell.setCellValue(notfound);
				logging.LoggerInfo("Make/Model is not displayed for Internet LOB");
				column = column + 1;
			}
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return column;
	}

}
