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
		// Step 9: Select 'Published' item from 'Status' dropdown list
		objCreateWebLinkPage.typeANewWeblink(weblinkTitle, weblinkURL,
				weblinkStatus);

		// Step 10: Click on 'Save & Close' icon of the top right toolbar
		objWeblinksPage = objCreateWebLinkPage.clickSaveCloseButton();

		// Step 11: Verify the web link is saved successfully
		// VP: 1. "Weblink successfully saved" message is displayed
		AssertTrue(objWeblinksPage.isWebLinkSavedSuccessfully());

		// Pre Condition: search weblink in order to find easily
		objWeblinksPage.searchWeblink(weblinkTitle);

		// VP: 2. Created weblink is displayed on the weblinks table
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		// Step 12: Check on the recently added article's checkbox
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		// Step 13: Click on 'Unpublish' icon of the top right toolbar
		objWeblinksPage.clickButtonOnTopRightToolbar(button);

		// Step 14: Verify the web link is published successfully
		// VP 1: The icon of the selected item is showed as 'Unpublish'.
		AssertTrue(objWeblinksPage.isWeblinkIsPublishedOrNot(weblinkTitle,
				weblinkStatusOfTable));

		// VP 2: The "1 weblink successfully unpublished" message is displayed
		AssertTrue(objWeblinksPage
				.isTextDisPlayOnPopupMessage(textNeedToBeTested));

	}

	@Test(description = "Verify user can move a web link to the archive")
	public void TC_Weblink_005() {

		// Step 12: Check on the recently added weblink's checkbox
		objWeblinksPage.searchWeblink(weblinkTitle);
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		// Step 13: Click on 'Archive' icon of the top right toolbar
		objWeblinksPage.clickButtonOnTopRightToolbar(buttonArchive);

		// Step 14: Verify the confirm message is displayed
		// VP : The "1 weblink successfully archived" message is displayed
		AssertTrue(objWeblinksPage
				.isTextDisPlayOnPopupMessage(textArchiveSuccessfully));

		// Step 15: Select 'Archived' item of 'Status' dropdown list
		objWeblinksPage.selectOptionFromStatusDropdown(optionArchive);

		// Step 16: Verify the archived web link is displayed on the table grid
		// VP: The archived web link is displayed on the table grid
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		// Post Condition: Delete the weblink
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
