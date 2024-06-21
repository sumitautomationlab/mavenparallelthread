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

public class EthernetData {
	@SuppressWarnings("finally")
	public static int Ethernet_data(WebDriver driver, int column, Row row_num, int i, Cell cell) throws Exception {
		try {
			TimeUnit.SECONDS.sleep(2);
			WebElement element = driver
					.findElement(By.xpath("//section[@class='TabMain row rightTab']//a[contains(text(),'ETHERNET')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			WebDriverWait wait5 = new WebDriverWait(driver, 600);
			wait5.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='EthernetTable']//thead//th[1]")));
			TimeUnit.SECONDS.sleep(1);
			boolean ether = driver.findElements(By.xpath("//*[@class='ethernetProductFilter']/parent::div//em"))
					.size() > 0;
			if (ether) {
				String business = driver.findElement(By.xpath("//*[@class='ethernetProductFilter']/parent::div//em"))
						.getText();
				cell = row_num.createCell(column);
				cell.setCellValue(business);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + business);
				column = column + 1;
			} else {
				String busi = "My Account for Business Not Set Up";
				cell = row_num.createCell(column);
				cell.setCellValue(busi);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + busi);
				column = column + 1;
			}

			String gen_product;
			boolean product = driver.findElements(By.xpath("//div[@id='EthernetTable']//thead//th[1]")).size() > 0;

			if (product) {
				TimeUnit.SECONDS.sleep(1);
				String product_value = "";
				String product_head = driver.findElement(By.xpath("//div[@id='EthernetTable']//thead//th[1]"))
						.getText();
				boolean product_value_true = driver
						.findElements(By.xpath("//div[@id='EthernetTable']//tbody//td[1]//h2")).size() > 0;
				if (product_value_true) {
					product_value = driver.findElement(By.xpath("//div[@id='EthernetTable']//tbody//td[1]//h2"))
							.getText();
				} else {
					product_value = "No EVC detail(s) found";
				}
				gen_product = product_head + "  " + product_value;
				cell = row_num.createCell(column);
				cell.setCellValue(gen_product);
				logging.LoggerInfo("Value Inserted in Row " + i + " and Column " + column + " is " + gen_product);
				column = column + 1;
			}
			// TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
		} 
			
		return column;
		
	}

}