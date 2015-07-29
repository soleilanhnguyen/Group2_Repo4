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

public class TM_Weblink_004 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_006");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Published";

		weblinkTitle2 = Common.getUniqueString("Test Weblink Order 1");
		weblinkURL2 = "http://www.joomla.org";
		weblinkStatus2 = "Published";

		webLinkTitle3 = Common.getUniqueString("Test Weblink Order 2");
		webLinkUrl3 = "http://www.joomla.org";
		weblinkStatus3 = "Published";
		
		weblinkTitle4 = Common.getUniqueString("TM_Weblink_015");
		weblinkTitle4Edit = weblinkAlias4 = Common
				.getUniqueString("TM_Weblink_015_edit");
		weblinkURL4 = "http://www.joomla.org";
		weblinkURL4Edit = "http://www.google.com";
		weblinkStatus4 = "Published";
		
		
	}

	@Parameters("browser")
	@Test(description = "Verify user can check in a web link")
	public void TC_JOOMLA_WEBLINKS_006(String browser) {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objWeblinksPage = objHomePage.gotoWebLink(driver);

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle, weblinkURL,
				weblinkStatus);

		objCreateWebLinkPage.clickSaveButton();

		AssertTrue(objCreateWebLinkPage.isTextSavedSuccessfullyDisplayOnPopup());

		driver.close();

		driver = openUrl(browser, Constant.url);

		objLoginPage = new LoginPage(driver);

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objWeblinksPage = objHomePage.gotoWebLink(driver);

		objWeblinksPage.searchWeblink(weblinkTitle);

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		objWeblinksPage.clickCheckinButton();

		AssertTrue(objWeblinksPage.isTextCheckinSuccessfullyDisplayOnPopup());

		AssertTrue(objWeblinksPage.isWeblinkCheckin(weblinkTitle));

	}

	@Test(description = "Verify user can paging the weblinks using the paging control")
	public void TC_JOOMLA_WEBLINKS_012() {

		objWeblinksPage.searchWeblink("");

		objWeblinksPage.selectDisplayDropdown("5");

		AssertTrue(objWeblinksPage.is5WeblinksDisplaying());

		objWeblinksPage.selectDisplayDropdown("All");
		
		AssertTrue(objWeblinksPage.isAllWeblinksDisplaying());
	}

	@Test(description = "Verify user can change the order of weblinks using the Ordering column")
	public void TC_JOOMLA_WEBLINKS_014() {

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle2, weblinkURL2,
				weblinkStatus2);

		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		AssertTrue(objWeblinksPage.isTextSavedSuccessfullyDisplayOnPopup());

		objWeblinksPage.selectDisplayDropdown("All");

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle2));

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(webLinkTitle3, webLinkUrl3,
				weblinkStatus3);

		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		AssertTrue(objWeblinksPage.isTextSavedSuccessfullyDisplayOnPopup());

		objWeblinksPage.selectDisplayDropdown("All");

		AssertTrue(objWeblinksPage.isWeblinkExist(webLinkTitle3));

		objWeblinksPage.selectDisplayDropdown("All");

		objWeblinksPage.clickOrderingColumn();

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle2);

		objWeblinksPage.clickDownArrowOrderingColumn(weblinkTitle2);

		AssertTrue(objWeblinksPage.isWeblinkLast(weblinkTitle2));

	}

	@Test(description = "User can change the status of weblinks using the Status column")
	public void TC_JOOMLA_WEBLINKS_015() {

		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle4, weblinkURL4,
				weblinkStatus4);

		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		AssertTrue(objWeblinksPage.isTextSavedSuccessfullyDisplayOnPopup());

		objWeblinksPage.searchWeblink(weblinkTitle4);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle4));

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle4);

		objWeblinksPage.clickOnStatusWebLink(weblinkTitle4);

		AssertTrue(objWeblinksPage.isWeblinkUnpublished(weblinkTitle4));

		AssertTrue(objWeblinksPage.isTextUnpublishedSuccessfullyDisplayOnPopup());

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle4);

		objWeblinksPage.clickOnStatusWebLink(weblinkTitle4);

		AssertTrue(objWeblinksPage.isWeblinkPublished(weblinkTitle4));

		AssertTrue(objWeblinksPage.isTextPublishedSuccessfullyDisplayOnPopup());

	}

	@Test(description = "Verify user can create a copied version of an existed weblink")
	public void TC_JOOMLA_WEBLINKS_016() {

		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle4);

		objCreateWebLinkPage = objWeblinksPage.clickEditButton();

		objCreateWebLinkPage.typeANewWeblink(weblinkTitle4Edit, weblinkURL4Edit,
				weblinkStatus4, weblinkAlias4);

		objCreateWebLinkPage.clickSaveAsCopyButton();

		AssertTrue(objCreateWebLinkPage.isTextSavedSuccessfullyDisplayOnPopup());

		objWeblinksPage = objCreateWebLinkPage.clickCloseButton();

		objWeblinksPage.searchWeblink(weblinkTitle4Edit);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle4Edit));

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

	private String weblinkTitle2;
	private String weblinkURL2;
	private String weblinkStatus2;

	private String webLinkTitle3;
	private String webLinkUrl3;
	private String weblinkStatus3;
	
	private String weblinkTitle4;
	private String weblinkURL4;
	private String weblinkTitle4Edit;
	private String weblinkURL4Edit;
	private String weblinkStatus4;
	private String weblinkAlias4;

}
