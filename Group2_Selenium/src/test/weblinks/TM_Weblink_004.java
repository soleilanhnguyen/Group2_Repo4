package weblinks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import abtract.AbstractTest;
import pages.CreateWebLinkPage;
import pages.HomePage;
import pages.LoginPage;
import pages.WeblinksPage;
import common.Common;
import common.Constant;

public class TM_Weblink_004 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_005");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Published";
		optCategory = "Sample Data-Weblinks";
		optStatus = "Published";
	}

	@Test(description = "Verify user can search for weblinks using the filter text field")
	public void TC_Weblink_009() {

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

		objWeblinksPage.searchWeblink(weblinkTitle);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

	}

	@Test(description = "Verify user can search for weblinks using the filter dropdown lists")
	public void TC_Weblink_010() {

		objWeblinksPage.selectOptionFromCategoryDropdown(optCategory);

		objWeblinksPage.selectOptionFromStatusDropdown(optStatus);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		objWeblinksPage.deleteWeblink(weblinkTitle);

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
	private String optStatus;
	private String optCategory;

}
