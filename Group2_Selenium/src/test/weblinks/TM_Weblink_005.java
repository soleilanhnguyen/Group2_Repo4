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

public class TM_Weblink_005 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_001");
		weblinkTitleEdit = weblinkAlias = Common
				.getUniqueString("TM_Weblink_001_edit");
		weblinkURL = "http://www.joomla.org";
		weblinkURLEdit = "http://www.google.com";
		weblinkStatus = "Published";

	}

	@Test(description = "User can change the status of weblinks using the Status column")
	public void TC_Weblink_015() {

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

		objWeblinksPage.clickOnStatusWebLink(weblinkTitle);

		AssertTrue(objWeblinksPage.isWeblinkUnpublished(weblinkTitle));

		AssertTrue(objWeblinksPage.isWeblinkUnpublishedSuccessfully());

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickOnStatusWebLink(weblinkTitle);

		AssertTrue(objWeblinksPage.isWeblinkPublished(weblinkTitle));

		AssertTrue(objWeblinksPage.isWeblinkPublishedSuccessfully());

	}

	@Test(description = "Verify user can create a copied version of an existed weblink")
	public void TC_Weblink_016() {

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objCreateWebLinkPage = objWeblinksPage.clickEditButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitleEdit, weblinkURLEdit,
				weblinkStatus, weblinkAlias);

		objCreateWebLinkPage.clickSaveAsCopyButton();

		AssertTrue(objCreateWebLinkPage.isWebLinkSavedSuccessfully());

		objWeblinksPage = objCreateWebLinkPage.clickCloseButton();

		objWeblinksPage.searchWeblink(weblinkTitleEdit);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitleEdit));

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
	private String weblinkTitleEdit;
	private String weblinkURLEdit;
	private String weblinkStatus;
	private String weblinkAlias;

}
