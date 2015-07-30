package abtract;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pages.AbstractPage;
import pages.BrowserManager;

public class AbstractTest {

	/**
	 * @param strBrowser
	 * @param strUrl
	 * @return driver
	 * @author Anh Nguyen
	 * @description Open a URL
	 */
	public WebDriver openUrl(String strBrowser, String strUrl) {
		BrowserManager bm = new BrowserManager();
		WebDriver driver = bm.launchBrowser(strBrowser);
		driver.get(strUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	/**
	 * @param driver
	 * @author Anh Nguyen
	 * @description: Close browser
	 */
	protected void closeBrowser(WebDriver driver) {
		try {
			driver.close();
			if (driver.toString().toLowerCase().contains("chrome")) {
				String cmd = "taskkill /F /IM chromedriver.exe";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
				
				String cmd2 = "taskkill /F /IM chrome.exe";
				Process process2 = Runtime.getRuntime().exec(cmd2);
				process2.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @description Log out
	 * @param driver
	 * @author anh.nguyen
	 */
	public void logOut(WebDriver driver) {
		PageFactory.initElements(driver, this);
		AbstractPage.click(LINK_LOGOUT);

	}

	/**
	 * @author Dung Pham 07/06/2015
	 * @param driver
	 * @param windowTitle
	 * @return
	 */
	public boolean checkWindownExist(WebDriver driver, String windowTitle) {
		try {
			handleMultipleWindows(driver, windowTitle);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 07/07/2015
	 * @param varBoolean
	 * @description Use this method to verify True/ False
	 */
	public void AssertTrue(boolean varBoolean) {
		Assert.assertTrue(varBoolean);
	}

	
	
	
	
	
	/**
	 * @author Dung Pham 07/06/2015
	 * @param driver
	 * @param windowTitle
	 */
	public void handleMultipleWindows(WebDriver driver, String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				return;
			}
		}
	}

	/**
	 * @author Ha Nguyen
	 * @param varBoolean
	 * @description Use this method to verify False
	 */
	public void AssertFalse(boolean varBoolean) {
		Assert.assertFalse(varBoolean);
	}

	/**
	 * @author Ha Nguyen
	 * @description Use this method to get log
	 */

	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	protected final Log log;

	@FindBy(xpath = ".//*[@id='module-status']/span[5]/a")
	WebElement LINK_LOGOUT;

}
