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
		// Step 1: Navigate to the URL: http://localhost/Joomla/administrator
		// Step 2: Enter valid username into Username field
		// Step 3: Enter valid password into Password field
		// Step 4: Click on 'Log in' button
		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		// Step 5: Select Components > Weblinks
		objWeblinksPage = objHomePage.gotoWebLink(driver);

		// Step 6: Click on 'New' icon of the top right toolbar
		objCreateWebLinkPage = objWeblinksPage.clickNewButton();

		// Step 7: Enter a title on 'Title' field
		// Step 8: Enter valid URL into 'URL' text field
		objCreateWebLinkPage.typeANewWeblink(weblinkTitle, weblinkURL,
				weblinkStatus);

		// Step 9: Click on 'Save & Close' icon of the top right toolbar
		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		// Step 10: Verify the web link is saved successfully
		// VP: 1. "Weblink successfully saved" message is displayed
		AssertTrue(objWeblinksPage.isWebLinkSavedSuccessfully());

		// Pre Condition: search weblink in order to find easily
		objWeblinksPage.searchWeblink(weblinkTitle);

		// VP: 2. Created weblink is displayed on the weblinks table
		// objWeblinksPage.verifyWebLinkExist(weblinkTitle);
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));
	}

	@Test(description = "Verify user can edit a web link")
	public void TC_Weblink_002() {

		// Step 11: Check on the recently added article's checkbox
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		// Step 12: Click on 'Edit' icon of the top right toolbar
		objCreateWebLinkPage = objWeblinksPage.clickEditButton();

		// Step 13: Enter a new title on 'Title' field
		// Step 14: Enter new valid URL into 'URL' text field
		objCreateWebLinkPage.typeANewWeblink(webLinkTitle2, webLinkUrl2,
				weblinkStatus);

		// Step 15: Click on 'Save' icon of the top right toolbar
		objCreateWebLinkPage.clickSaveButton();

		// Step 16: Verify the web link is saved successfully
		// VP1: 1. "Weblink successfully saved" message is displayed
		AssertTrue(objCreateWebLinkPage.isWebLinkSavedSuccessfully());

		// VP2: 2. Edited weblink is displayed on the weblinks table
		objWeblinksPage = objCreateWebLinkPage.clickCloseButton();
		objWeblinksPage.searchWeblink(webLinkTitle2);

		AssertTrue(objWeblinksPage.isWeblinkExist(webLinkTitle2));
		// Post Condition: Delete the weblink
		objWeblinksPage.deleteWeblink(webLinkTitle2);

	}

	@Test(description = "Verify user can access weblink's help section")
	public void TC_Weblink_008() {
		// Step 7: Click on 'Help' icon of the top right toolbar
		objWeblinksPage.clickButtonOnTopRightToolbar(button);

		// Step 8: Verify the weblink's help window is displayed
		// VP: The weblink's help window is displayed
		this.driver = objWeblinksPage.getWeblinksPageDriver();

		String windownWeblinkTitle = driver.getWindowHandle();

		checkWindownExist(driver, windowHelpTitle);

		// close help //back main Window
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
