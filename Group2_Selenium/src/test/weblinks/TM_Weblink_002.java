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

public class TM_Weblink_002 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_002");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Unpublished";
		optionTrash = "Trashed";

	}

	@Test(description = "Verify user can publish an unpublished web link")
	public void TC_JOOMLA_WEBLINKS_003() {

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

		objWeblinksPage.clickPublishButton();

		AssertTrue(objWeblinksPage.isWeblinkPublished(weblinkTitle));

		AssertTrue(objWeblinksPage.isTextPublishedSuccessfullyDisplayOnPopup());

	}

	@Test(description = "Verify user can move a web link to trash section")
	public void TC_JOOMLA_WEBLINKS_007() {

		objWeblinksPage.searchWeblink(weblinkTitle);
		
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickTrashButton();

		AssertTrue(objWeblinksPage.isTextTrashedSuccessfullyDisplayOnPopup());

		objWeblinksPage.selectOptionFromStatusDropdown(optionTrash);

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
	private String optionTrash;

}
