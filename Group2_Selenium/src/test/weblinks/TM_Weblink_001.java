package weblinks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import abtract.AbstractTest;
import pages.CreateWebLinkPage;
import pages.HomePage;
import pages.LoginPage;
import pages.WeblinksPage;
import common.Constant;
import common.Common;

public class TM_Weblink_001 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_001");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Published";

		webLinkTitle2 = Common.getUniqueString("TM_Weblink_001_edit");
		webLinkUrl2 = "http://www.google.com";
		windowHelpTitle = "Joomla! Help";
		button = "help";
	}

	@Test(description = "Verify user can create new web link with valid information")
	public void TC_Weblink_001() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objWeblinksPage = objHomePage.gotoWebLink(driver);

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle, weblinkURL,
				weblinkStatus);

		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		AssertTrue(objWeblinksPage.isWebLinkSavedSuccessfully());

		objWeblinksPage.searchWeblink(weblinkTitle);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));
	}

	@Test(description = "Verify user can edit a web link")
	public void TC_Weblink_002() {

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objCreateWebLinkPage = objWeblinksPage.clickEditButton();

		objCreateWebLinkPage.typeANewWeblink(webLinkTitle2, webLinkUrl2,
				weblinkStatus);

		objCreateWebLinkPage.clickSaveButton();

		AssertTrue(objCreateWebLinkPage.isWebLinkSavedSuccessfully());

		objWeblinksPage = objCreateWebLinkPage.clickCloseButton();
		objWeblinksPage.searchWeblink(webLinkTitle2);

		AssertTrue(objWeblinksPage.isWeblinkExist(webLinkTitle2));

	}

	@Test(description = "Verify user can access weblink's help section")
	public void TC_Weblink_008() {

		objWeblinksPage.clickButtonOnTopRightToolbar(button);

		this.driver = objWeblinksPage.getWeblinksPageDriver();

		String windownWeblinkTitle = driver.getWindowHandle();

		checkWindownExist(driver, windowHelpTitle);

		driver.close();

		driver.switchTo().window(windownWeblinkTitle);

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private WeblinksPage objWeblinksPage;
	private CreateWebLinkPage objCreateWebLinkPage;

	private String weblinkTitle;
	private String weblinkURL;
	private String weblinkStatus;

	private String webLinkTitle2;
	private String webLinkUrl2;
	private String windowHelpTitle;
	private String button;
	private boolean windownHelpExist;
	private String windownWeblinkTitle;

}
