package bannerclients;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BannerClientsPage;
import pages.CreateNewBannerClients;
import pages.EditBannerClient;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_BannerClient_002 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		clientName = Common.getUniqueString("TC_BannerClient_002_Name");
		contactName = Common.getUniqueString("TC_BannerClient_002_Contact");
		contactEmail = Common.getUniqueString("TC_BannerClient_002_Name")
				+ "@gmail.com";
		contactStatus = "Unpublished";
		button = "unpublish";
		buttonTrash = "trash";
		option = "Trashed";

		clientNameEdited = Common
				.getUniqueString("TC_BannerClient_002_Name_Edited");
		contactNameEdited = Common
				.getUniqueString("TC_BannerClient_002_Contact_Edited");
		contactEmailEdited = Common
				.getUniqueString("TC_BannerClient_002_Name_Edited")
				+ "@gmail.com";
		contactStatusEdited = "Published";
		textNeedToBeTested = "1 client successfully unpublished";
		textNeedToBeTestedTrashSuccessful = "1 client successfully trashed";
		clientStatusOfTable = "Unpublished";

	}

	@Test(description = "Verify that user can edit a client")
	public void TC_BannerClient_002() {
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

		// Step 9: Click on 'Save' button
		objEditBannerClient = objCreateNewBannerClients.clickSaveButton();

		// Step 10: A message : "Client successfully saved" shows and new client
		// is created
		// VP: 1. _ A message : "Client successfully saved" shows.
		AssertTrue(objEditBannerClient.isBannerClientSavedSuccessfully());

		// VP2: Edit client page display
		AssertTrue(objEditBannerClient.isEditBannerPageDisplayed());

		// Step 10: Enter valid Client Name to Client Name textbox
		// Step 11: Enter valid Contact Name to Contact Name textbox
		// Step 12: Enter valid Contact Email to Contact Email textbox
		objEditBannerClient.editABannerClients(clientNameEdited,
				contactNameEdited, contactEmailEdited, contactStatusEdited);

		// Step 13: Click "Save & Close" button
		objBannerClientsPage = objEditBannerClient.clickSaveCloseButton();

		// VP: A message : "Client successfully saved" shows and Client is
		// edited
		// VP: A message : "Client successfully saved" shows
		AssertTrue(objBannerClientsPage.isBannerClientSavedSuccessfully());

		// VP: Client is edited

		// Pre Condition: search weblink in order to find easily
		objBannerClientsPage.searchBannerClient(clientNameEdited);
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientNameEdited));
	}

	@Test(description = "Verify that user can search a client  by using filter textbox")
	public void TC_BannerClient_008() {
		// Step 11: Enter valid Client Name to Filter textbox
		// Step 12: Click "Search" button
		objBannerClientsPage.searchBannerClient(clientNameEdited);

		// VP: Recently created client displays
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientNameEdited));

	}

	@Test(description = "Verify that user can unpublish a client")
	public void TC_BannerClient_004() {
		// Step 11: Check the Client recently created
		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientNameEdited);

		// Step 12: Click "Unpublish" button in the right top corner
		objBannerClientsPage.clickButtonOnTopRightToolbar(button);

		// VP: A message : "1 client successfully unpublished" shows and Client
		// is unpublished
		// VP1: _ A message : "1 client successfully unpublished" shows.
		AssertTrue(objBannerClientsPage
				.isTextDisPlayOnPopupMessage(textNeedToBeTested));

		// VP2: _ Client is unpublished
		AssertTrue(objBannerClientsPage.isClientIsPublishedOrNot(
				clientNameEdited, clientStatusOfTable));

	}

	@Test(description = "Verify that user can send a client to trash")
	public void TC_BannerClient_006() {
		// Step 11: Check the Client recently created
		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientNameEdited);

		// Step 12: Click "Trash" button in the right top corner
		objBannerClientsPage.clickButtonOnTopRightToolbar(buttonTrash);

		// Step 13: A message : "1 client successfully sent to trash" shows
		objBannerClientsPage
				.verifyTextDisPlayOnPopupMessage(textNeedToBeTestedTrashSuccessful);

		// Step 14: Select "Trashed" in Status dropdown list
		objBannerClientsPage.selectOptionFromStatusDropdown(option);

		// Step 15: Client is sent to trash
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientNameEdited));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private BannerClientsPage objBannerClientsPage;
	private CreateNewBannerClients objCreateNewBannerClients;
	private EditBannerClient objEditBannerClient;

	private String clientName;
	private String contactName;
	private String contactEmail;
	private String contactStatus;

	private String clientNameEdited;
	private String contactNameEdited;
	private String contactEmailEdited;
	private String contactStatusEdited;
	private String button;
	private String buttonTrash;
	private String textNeedToBeTested;
	private String textNeedToBeTestedTrashSuccessful;
	private String clientStatusOfTable;
	private String option;
}
