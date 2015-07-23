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

public class TM_Weblink_007 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("Test Weblink Order 1");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Published";

		webLinkTitle2 = Common.getUniqueString("Test Weblink Order 2");
		webLinkUrl2 = "http://www.joomla.org";
		windowHelpTitle = "Joomla! Help";

	}

	@Test(description = "Verify user can change the order of weblinks using the Ordering column")
	public void TC_Weblink_014() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objWeblinksPage = objHomePage.gotoWebLink(driver);

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle, weblinkURL,
				weblinkStatus);

		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		AssertTrue(objWeblinksPage.isWebLinkSavedSuccessfully());

		objWeblinksPage.selectDisplayDropdown("All");

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(webLinkTitle2, webLinkUrl2,
				weblinkStatus);

		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		AssertTrue(objWeblinksPage.isWebLinkSavedSuccessfully());

		objWeblinksPage.selectDisplayDropdown("All");

		AssertTrue(objWeblinksPage.isWeblinkExist(webLinkTitle2));
		
		objWeblinksPage.selectDisplayDropdown("All");
		
		objWeblinksPage.clickOrderingColumn();
		
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);
		
		objWeblinksPage.clickDownArrowOrderingColumn(weblinkTitle);
		
		AssertTrue(objWeblinksPage.isWeblinkLast(weblinkTitle));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logOut(driver);

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

}
