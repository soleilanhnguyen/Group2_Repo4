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
		
		weblinkTitle3 = Common.getUniqueString("TM_Weblink_001_3");
		weblinkURL3 = "http://www.joomla.org";
		weblinkStatus3 = "Published";
		weblinkCategory3 = "- Sample Data-Weblinks";
		
		
		optCategory = "Sample Data-Weblinks";
		optStatus = "Published";

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

		AssertTrue(objWeblinksPage.isTextSavedSuccessfullyDisplayOnPopup());

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

		AssertTrue(objCreateWebLinkPage.isTextSavedSuccessfullyDisplayOnPopup());

		objWeblinksPage = objCreateWebLinkPage.clickCloseButton();

		objWeblinksPage.searchWeblink(webLinkTitle2);

		AssertTrue(objWeblinksPage.isWeblinkExist(webLinkTitle2));

	}

	@Test(description = "Verify user can access weblink's help section")
	public void TC_Weblink_008() {

		objWeblinksPage.clickHelpButton();

		String windownWeblinkTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		handleMultipleWindows(driver, windownWeblinkTitle);

	}

	@Test(description = "Verify user can search for weblinks using the filter text field")
	public void TC_Weblink_009() {

		objWeblinksPage.searchWeblink("");
		
		objWeblinksPage.searchWeblink(webLinkTitle2);

		AssertTrue(objWeblinksPage.isWeblinkExist(webLinkTitle2));

	}
	
	@Test(description = "Verify user can search for weblinks using the filter dropdown lists")
	public void TC_Weblink_010() {
		
		objCreateWebLinkPage = objWeblinksPage.clickNewButton();
		
		objCreateWebLinkPage.typeANewWeblinkWithCategory(weblinkTitle3, weblinkURL3, weblinkStatus3, weblinkCategory3);
		
		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();
		
		objWeblinksPage.searchWeblink("");
		
		objWeblinksPage.selectDisplayDropdown("All");

		objWeblinksPage.selectOptionFromCategoryDropdown(optCategory);

		objWeblinksPage.selectOptionFromStatusDropdown(optStatus);

		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle3));

	}
	
	@Test(description = "Verify user can sort the weblinks table by ID column")
	public void TC_Weblink_011() {

		objWeblinksPage.searchWeblink("");
		
		objWeblinksPage.selectDisplayDropdown("All");

		objWeblinksPage.clickIDColumn();

		AssertTrue(objWeblinksPage.isSortedAscending());

		objWeblinksPage.clickIDColumn();

		AssertTrue(objWeblinksPage.isSortedDescending());

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
	
	private String weblinkTitle3;
	private String weblinkURL3;
	private String weblinkStatus3;
	private String weblinkCategory3;
	
	private String optStatus;
	private String optCategory;

}
