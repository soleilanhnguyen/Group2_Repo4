package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import common.Constant;

public class BrowserManager {

	/**
	 * @author Ha Nguyen
	 * @param browserName
	 * @return
	 */
	public WebDriver launchBrowser(String browserName) {
		try {
			if (browserName.equals(Constant.FIRE_FOX)) {
				driver = new FirefoxDriver();
			} else if (browserName.equals(Constant.CHROME)) {
				System.setProperty("webdriver.chrome.driver",
						"src/main/resources/chromedriver.exe");
				driver = new ChromeDriver();
			} else {
				System.setProperty("webdriver.ie.driver",
						"src/main/resources/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (Exception ex) {
			System.out.println(ex);

		}
		return driver;

	}

	protected void closeBrowser(WebDriver driver) {
		try {
			driver.quit();
			System.gc();
			if (driver.toString().toLowerCase().contains("chrome")) {
				String cmd = "taskkill /IM chromedriver.exe /F";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
				
				String cmd2 = "taskkill /IM chrome.exe /F";
				Process process2 = Runtime.getRuntime().exec(cmd2);
				process2.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private WebDriver driver = null;
}
