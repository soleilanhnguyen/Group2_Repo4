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

		weblinkStatusOfTable = "Published";
		textNeedToBeTested = "1 weblink successfully published";
		button = "publish";
		buttonTrash = "trash";
		textTrashSuccessfully = "1 weblink successfully trashed";
		optionTrash = "Trashed";

	}

	@Test(description = "Verify user can publish an unpublished web link")
	public void TC_Weblink_003() {
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
		// Step 9: Select 'Unpublished' item from 'Status' dropdown list
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

		// Step 13: Click on 'Publish' icon of the top right toolbar
		objWeblinksPage.clickButtonOnTopRightToolbar(button);

		// Step 14: Verify the web link is published successfully
		// VP 1: The icon of the selected item is showed as 'Publish'.

		AssertTrue(objWeblinksPage.isWeblinkIsPublishedOrNot(weblinkTitle,
				weblinkStatusOfTable));

		// VP 2: The "1 weblink successfully published" message is displayed
		AssertTrue(objWeblinksPage
				.isTextDisPlayOnPopupMessage(textNeedToBeTested));

	}

	@Test(description = "Verify user can move a web link to trash section")
	public void TC_Weblink_007() {

		// Step 12: Check on the recently added weblink's checkbox
		objWeblinksPage.searchWeblink(weblinkTitle);
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		// Step 13: Click on 'Trash' icon of the top right toolbar
		objWeblinksPage.clickButtonOnTopRightToolbar(buttonTrash);

		// Step 14: Verify the confirm message is displayed
		// VP : The "1 weblink successfully trashed" message is displayed
		AssertTrue(objWeblinksPage
				.isTextDisPlayOnPopupMessage(textTrashSuccessfully));

		// Step 15: Select 'Trash' item of 'Status' dropdown list
		objWeblinksPage.selectOptionFromStatusDropdown(optionTrash);

		// Step 16: Verify the deleted weblink is displayed on the table grid
		// VP: The deleted weblink is displayed on the table grid
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

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
	private String button;
	private String buttonTrash;
	private String textTrashSuccessfully;
	private String optionTrash;

}
