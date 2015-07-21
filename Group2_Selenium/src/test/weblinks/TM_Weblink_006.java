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

public class TM_Weblink_006 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_006");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Published";

	}

	@Parameters("browser")
	@Test(description = "Verify user can check in a web link")
	public void TC_Weblink_006(String browser) {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objWeblinksPage = objHomePage.gotoWebLink(driver);

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle, weblinkURL,
				weblinkStatus);

		objCreateWebLinkPage.clickSaveButton();

		AssertTrue(objCreateWebLinkPage.isWebLinkSavedSuccessfully());

		driver.close();

		driver = openUrl(browser, Constant.url);

		objLoginPage = new LoginPage(driver);

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objWeblinksPage = objHomePage.gotoWebLink(driver);

		objWeblinksPage.searchWeblink(weblinkTitle);

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickCheckinButton();

		AssertTrue(objWeblinksPage.isWebLinkCheckinSuccessfully());

		AssertTrue(objWeblinksPage.isWeblinkCheckin(weblinkTitle));

	}

	@Test(description = "Verify user can paging the weblinks using the paging control")
	public void TC_Weblink_012() {

		objWeblinksPage.searchWeblink("");

		objWeblinksPage.selectDisplayDropdown("5");

		AssertTrue(objWeblinksPage.is5WeblinksDisplaying());

		objWeblinksPage.selectDisplayDropdown("All");
		// AssertTrue(objWeblinksPage.isAllWeblinksDisplaying());
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

}
