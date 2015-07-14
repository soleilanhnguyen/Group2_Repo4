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

		weblinkStatusPublish = "Published";
		weblinkStatusUnpublish = "Unpublished";
		textPublish = "1 weblink successfully published";
		textUnpublish = "1 weblink successfully unpublished";
	}

	@Test(description = "User can change the status of weblinks using the Status column")
	public void TC_Weblink_015() {
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

		// VP: 2. Created weblink is displayed on the weblinks table
		objWeblinksPage.searchWeblink(weblinkTitle);
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		// Step 12: Check on the recently added web link's checkbox
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		// Step 13: Click on the status icon of the selected weblink in the
		// Status column
		objWeblinksPage.clickOnStatusWebLink(weblinkTitle);

		// Step 14: Verify the web link is unpublished successfully
		// VP1: The icon of the selected item is showed as 'Unpublish'.
		AssertTrue(objWeblinksPage.isWeblinkIsPublishedOrNot(weblinkTitle,
				weblinkStatusUnpublish));

		// VP2: The "1 weblink successfully unpublished" message is displayed
		AssertTrue(objWeblinksPage.isTextDisPlayOnPopupMessage(textUnpublish));

		// Step 15: Check on the recently added web link's checkbox
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		// Step 16: Click on the status icon of the selected weblink in the
		// Status column
		objWeblinksPage.clickOnStatusWebLink(weblinkTitle);

		// Step 17: Verify the web link is published successfully
		// VP1: The icon of the selected item is showed as 'Publish'.
		AssertTrue(objWeblinksPage.isWeblinkIsPublishedOrNot(weblinkTitle,
				weblinkStatusPublish));

		// VP2: The "1 weblink successfully published" message is displayed
		AssertTrue(objWeblinksPage.isTextDisPlayOnPopupMessage(textPublish));

	}

	@Test(description = "Verify user can create a copied version of an existed weblink")
	public void TC_Weblink_016() {
		// Step 11: Check on the recently added article's checkbox
		objWeblinksPage.clickOnCheckBoxWebLink(weblinkTitle);

		// Step 12: Click on 'Edit' icon of the top right toolbar
		objCreateWebLinkPage = objWeblinksPage.clickEditButton();

		// Step 13: Enter a new title on 'Title' field
		// Step 14: Enter new valid URL into 'URL' text field
		objCreateWebLinkPage.typeANewWeblink(weblinkTitleEdit, weblinkURLEdit,
				weblinkStatus, weblinkAlias);

		// Step 15: Click on 'Save as Copy' icon of the top right toolbar
		objCreateWebLinkPage.clickSaveAsCopyButton();

		// Step 16: Verify the web link is saved successfully
		// VP1: 1. "Weblink successfully saved" message is displayed
		AssertTrue(objCreateWebLinkPage.isWebLinkSavedSuccessfully());

		objWeblinksPage = objCreateWebLinkPage.clickCloseButton();

		// VP2: 2. The copied weblink is displayed on the weblinks table
		objWeblinksPage.searchWeblink(weblinkTitleEdit);
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitleEdit));

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

	private String weblinkStatusUnpublish;
	private String weblinkStatusPublish;
	private String weblinkTitle;
	private String weblinkURL;
	private String weblinkTitleEdit;
	private String weblinkURLEdit;
	private String weblinkStatus;
	private String textUnpublish;
	private String textPublish;
	private String weblinkAlias;

}
