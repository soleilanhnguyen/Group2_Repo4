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

public class TM_Weblink_003 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_003");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Published";

		optionArchive = "Archived";
	}

	@Test(description = "Verify user can unpublish a published web link")
	public void TC_Weblink_004() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objWeblinksPage = objHomePage.gotoWebLink(driver);

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle, weblinkURL,
				weblinkStatus);

		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		AssertTrue(objWeblinksPage.isTextSavedSuccessfullyDisplayOnPopup());

		objWeblinksPage.searchWeblink(weblinkTitle);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickUnpublishButton();

		AssertTrue(objWeblinksPage.isWeblinkUnpublished(weblinkTitle));

		AssertTrue(objWeblinksPage.isTextUnpublishedSuccessfullyDisplayOnPopup());

	}

	@Test(description = "Verify user can move a web link to the archive")
	public void TC_Weblink_005() {

		objWeblinksPage.searchWeblink(weblinkTitle);

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickArchiveButton();

		AssertTrue(objWeblinksPage.isTextArchivedSuccessfullyDisplayOnPopup());

		objWeblinksPage.selectOptionFromStatusDropdown(optionArchive);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

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

	private String optionArchive;
}
