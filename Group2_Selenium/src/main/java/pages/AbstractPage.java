package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.Common;

public class AbstractPage {

	/**
	 * @author Dung Pham 04/07/2015
	 * @param element
	 * @param option
	 * @description select option from dropdown list
	 */
	public void selectOptionFromDropdownList(WebElement element, String option){
		Select dropdown=new Select(element);
		dropdown.selectByVisibleText(option);
	}
	
	/**
	 * @description: type to a textfield
	 * @param element
	 * @param value
	 * @author anh.nguyen
	 */
	public void type(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * @description click a button
	 * @param element
	 * @author anh.nguyen
	 */
	public static void click(WebElement element) {
		element.click();
	}

	// Dung Pham create 06/27/2015
	// Move mouse hover on the web element
	public void mouseHoverOnWebElement(WebDriver driver, WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).perform();
	}

	// Dung Pham create 06/27/2015
	// Move mouse hover on the web element, then click on this web element
	public void mouseHoverOnWebElementandClick(WebDriver driver, WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).perform();
		action.click().perform();
	}

	// Dung Pham create 06/29/2015
	// verify the web element exist
	public void verifyWebElementExist(WebElement webElementLinkText) {
		try {
			Assert.assertTrue(webElementLinkText.isDisplayed());
		} catch (Exception e) {
			System.out.println("Element with link text " + webElementLinkText
					+ "does not exist");
		}
	}
	
	 /**
	  * @author Dung Pham 07/07/2015 
	  * @param driver
	  * @param initialString
	  * @param control
	  * @return WebElement
	  */
	 public WebElement findElementByXPath(WebDriver driver, String initialString,
	   String control) {
	  String xpathControl = String.format(initialString, control);
	  return driver.findElement(By.xpath(xpathControl));
	 }
	
/**
 * @description wait for control
 * @param driver
 * @param element
 * @param timeout
 * @author anh.nguyen
 */
	 public void waitForControl(WebDriver driver, WebElement element,long timeout){
		 		 
		 try {WebDriverWait wait = new WebDriverWait(driver, timeout);
				wait.until(ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {
				System.out.println("Element is not existed");
			}
	 }
	 
	 
	 
	/**
	 * @description Perform a click
	 * @param driver
	 * @author anh.nguyen
	 */

	public void clickAction(WebDriver driver) {
		Actions action = new Actions(driver);
		action.click().perform();
	}

	/**
	 * @description: Select a menu item in menu
	 * @param itemPath
	 * @param driver
	 * @author anh.nguyen
	 */
	public void selectMenuItem(String itemPath, WebDriver driver) {

		String temp[] = Common.splitString(itemPath);

		for (int i = 0; i < temp.length; i++) {
			WebElement e;
			
if(temp[i].equals("CategoriesBanner")){
	 e = driver.findElement(By.xpath(".//*[@id='menu-com-banners']/li[2]/a"));
	
}
else{
			 e = driver.findElement(By.xpath("//a[contains(text(),'"
					+ temp[i] + "')]"));
}
			mouseHoverOnWebElement(driver, e);

		}
		clickAction(driver);

	}

	/**
	 * @description select a drop down list item by value
	 * @param ddlName
	 * @param value
	 * @author anh.nguyen
	 */
	public void selectDropDownListItemByValue(WebElement ddlName, String value) {
		Select select = new Select(ddlName);
		select.selectByValue(value);
	}
	
	/**
	 * @description select a drop down list item by text
	 * @param ddlName
	 * @param value
	 * @author anh.nguyen
	 */
	public void selectDropDownListItemByText(WebElement ddlName, String text) {
		Select select = new Select(ddlName);
		select.selectByVisibleText(text);;
	}
		
	
	/**
	 * @description  get Attribute of ddl
	 * @param ddlName
	 * @param att
	 * @author Ha Nguyen
	  */
	
	public String getAttribute(WebElement ddlName, String att)
	{		
		return ddlName.getAttribute(att);
	}
	
	/**
	 * @description  get Css of ddl
	 * @param ddlName
	 * @param att
	 * @author Ha Nguyen
	  */
	
	public String getControlCss(WebElement ddlName, String css)
	{		
		return ddlName.getCssValue(css);
	}
	
	public final static int timeout = 15;
	
}
