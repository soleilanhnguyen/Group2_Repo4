package article;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.ArticlePage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;

import common.Constant;

public class TM_Article_005 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		windowHelpTitle = "Joomla! Help";
	}

	@Test(description = "Verify user can access article's help section")
	public void TC_Article_008() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objArticlePage = objHomePage.gotoArticlePage(driver);

		objArticlePage.openHelp();

		this.driver = objArticlePage.getArticlePageDriver();

		String windownArticleTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		driver.switchTo().window(windownArticleTitle);
				}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private ArticlePage objArticlePage;
	private String windowHelpTitle;

}
