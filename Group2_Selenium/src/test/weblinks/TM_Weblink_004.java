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

public class TM_Weblink_004 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		weblinkTitle = Common.getUniqueString("TM_Weblink_005");
		weblinkURL = "http://www.joomla.org";
		weblinkStatus = "Published";
		optCategory = "Sample Data-Weblinks";
		optStatus = "Published";
	}

	@Test(description = "Verify user can search for weblinks using the filter text field")
	public void TC_Weblink_009() {
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

		// Step 12: Enter a title on 'Filter' text field
		// Step 13: Click on 'Search' button

		objWeblinksPage.searchWeblink(weblinkTitle);

		// VP: Verify the titles of displayed weblinks are partially matched
		// with the entered keyword
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

	}

	@Test(description = "Verify user can search for weblinks using the filter dropdown lists")
	public void TC_Weblink_010() {
		// Step 13: Select an item from the 'Category' filter dropdown list
		objWeblinksPage.selectOptionFromCategoryDropdown(optCategory);

		// Step 14:Select an item from the 'Status' filter dropdown list
		objWeblinksPage.selectOptionFromStatusDropdown(optStatus);

		// VP: Verify the property of displayed weblinks are matched with the
		// selected criteria from the filter dropdown lists
		AssertTrue(objWeblinksPage.isWeblinkExist(weblinkTitle));

		// Post Condition: Delete weblink
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
	private String optStatus;
	private String optCategory;

}
