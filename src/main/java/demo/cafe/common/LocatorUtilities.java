package demo.cafe.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocatorUtilities {
	public static WebElement getLocator(String locator, WebDriver driver) throws Exception {
		property_initialize.loadlocatorProperty();
		WebElement element = null;
		String type,path;
		int len=0,separate=0;
		String locatorvalue = property_initialize.locatorProps.getProperty(locator);
		separate=locatorvalue.lastIndexOf(';');
		len=locatorvalue.length();
		path=locatorvalue.substring(0,separate);
		type=locatorvalue.substring(separate+1,len);
		type.toUpperCase();
		Locator locator1 = Locator.valueOf(type);
		
		switch (locator1)
		{
			case XPATH : 
				 element = driver.findElement(By.xpath(path));
				 break;
			case CSS :
				 element = driver.findElement(By.cssSelector(path));
				 break;
			case ID : 
				 element = driver.findElement(By.id(path));
				 break;
			case CLASS :
				 element = driver.findElement(By.className(path));
				 break;
			case NAME : 
				 element = driver.findElement(By.name(path));
				 break;
			case LINKTEXT :
				 element = driver.findElement(By.linkText(path));
				 break;
			default:
				System.out.println("No locator found!");
		}
		return element;
	}
}
