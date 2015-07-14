package bannerclients;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BannerClientsPage;
import pages.CreateNewBannerClients;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_BannerClient_001 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		clientName = Common.getUniqueString("TC_BannerClient_001_Name");
		contactName = Common.getUniqueString("TC_BannerClient_001_Contact");
		contactEmail = Common.getUniqueString("TC_BannerClient_001_Name")
				+ "@gmail.com";
		contactStatus = "Unpublished";
		option = "Archived";
		bannerclientsHelpTitle = "Joomla! Help";

		button = "publish";
		buttonArchieve = "archive";
		buttonHelp = "help";
		textNeedToBeTested = "1 client successfully published";
		textNeedToBeTestedArchieve = "1 client successfully archived";
		clientStatusOfTable = "Published";

	}

	@Test(description = "Verify that user can create a new client")
	public void TC_BannerClient_001() {
		// Step 1: Navigate Joomla login page
		// Step 2: Login with User Name : "lctp" and password : "lctp"
		// Step 3: Click "Login in" button
		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		// Step 4: Select Components -> Banners -> Clients
		objBannerClientsPage = objHomePage.gotoBannerClient(driver);

		// Step 5: Click "New" button in the right top corner
		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		// Step 6: Enter valid Client Name to Client Name textbox
		// Step 7: Enter valid Contact Name to Contact Name textbox
		// Step 8: Enter valid Contact Email to Contact Email textbox
		objCreateNewBannerClients.typeANewBannerClients(clientName,
				contactName, contactEmail, contactStatus);

		// Step 9: Click on 'Save & Close' button
		objBannerClientsPage = objCreateNewBannerClients.clickSaveCloseButton();

		// Step 10: A message : "Client successfully saved" shows and new client
		// is created
		// VP: 1. _ A message : "Client successfully saved" shows.
		AssertTrue(objBannerClientsPage.isBannerClientSavedSuccessfully());

		// Pre Condition: search weblink in order to find easily
		objBannerClientsPage.searchBannerClient(clientName);

		// VP: 2. _ New client is created and matched with entered data
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName));
	}

	@Test(description = "Verify that user can publish a client")
	public void TC_BannerClient_003() {

		// Step 12: Check the Client recently created
		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientName);

		// Step 13: Click "Publish" button in the right top corner
		objBannerClientsPage.clickButtonOnTopRightToolbar(button);

		// Step 14: A message : "1 client successfully published" shows and
		// Client is published
		// VP1: _ A message : "1 client successfully published" shows.
		AssertTrue(objBannerClientsPage
				.isTextDisPlayOnPopupMessage(textNeedToBeTested));

		// VP2: _ Client is published
		AssertTrue(objBannerClientsPage.isClientIsPublishedOrNot(clientName,
				clientStatusOfTable));

	}

	@Test(description = "Verify that user can archive a client")
	public void TC_BannerClient_005() {
		// Step 11: Check the Client recently created
		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientName);

		// Step 12: Click "archive" button in the right top corner
		objBannerClientsPage.clickButtonOnTopRightToolbar(buttonArchieve);

		// Step 13: A message : "1 client successfully archived" shows
		objBannerClientsPage
				.verifyTextDisPlayOnPopupMessage(textNeedToBeTestedArchieve);

		// Step 14: Select "Archive" in Status dropdown list
		objBannerClientsPage.selectOptionFromStatusDropdown(option);

		// Step 15: Client is archived
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName));

	}

	@Test(description = "Verify that user can browse Banner client help page")
	public void TC_BannerClient_007() {
		// Step 6: Click "Help" button in the right top corner
		objBannerClientsPage.clickButtonOnTopRightToolbar(buttonHelp);

		// VP: "Banner client help" page appears
		this.driver = objBannerClientsPage.getBannerClientsPageDriver();

		String windownBannerClientsPage = driver.getWindowHandle();

		checkWindownExist(driver, bannerclientsHelpTitle);

		// close help //back main Window
		driver.close();

		handleMultipleWindows(driver, windownBannerClientsPage);

		// driver.switchTo().window(windownBannerClientsPage);

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

	private String button;
	private String buttonArchieve;
	private String buttonHelp;
	private String textNeedToBeTested;
	private String textNeedToBeTestedArchieve;
	private String clientStatusOfTable;
	private String bannerclientsHelpTitle;
	private String option;

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private BannerClientsPage objBannerClientsPage;
	private CreateNewBannerClients objCreateNewBannerClients;

	private String clientName;
	private String contactName;
	private String contactEmail;
	private String contactStatus;

}
