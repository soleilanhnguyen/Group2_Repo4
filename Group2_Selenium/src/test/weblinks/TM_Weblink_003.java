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

		weblinkStatusOfTable = "Unpublished";
		textNeedToBeTested = "1 weblink successfully unpublished";
		textArchiveSuccessfully = "1 weblink successfully archived";
		button = "unpublish";
		buttonArchive = "archive";
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

		AssertTrue(objWeblinksPage.isWebLinkSavedSuccessfully());

		objWeblinksPage.searchWeblink(weblinkTitle);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickButtonOnTopRightToolbar(button);

		AssertTrue(objWeblinksPage.isWeblinkIsPublishedOrNot(weblinkTitle,
				weblinkStatusOfTable));

		AssertTrue(objWeblinksPage
				.isTextDisPlayOnPopupMessage(textNeedToBeTested));

	}

	@Test(description = "Verify user can move a web link to the archive")
	public void TC_Weblink_005() {

		objWeblinksPage.searchWeblink(weblinkTitle);
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickButtonOnTopRightToolbar(buttonArchive);

		AssertTrue(objWeblinksPage
				.isTextDisPlayOnPopupMessage(textArchiveSuccessfully));

		objWeblinksPage.selectOptionFromStatusDropdown(optionArchive);

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

	private String weblinkStatusOfTable;
	private String textNeedToBeTested;
	private String textArchiveSuccessfully;
	private String button;
	private String buttonArchive;
	private String optionArchive;
}
