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

		option = "Trashed";

		clientNameEdited = Common
				.getUniqueString("TC_BannerClient_002_Name_Edited");
		contactNameEdited = Common
				.getUniqueString("TC_BannerClient_002_Contact_Edited");
		contactEmailEdited = Common
				.getUniqueString("TC_BannerClient_002_Name_Edited")
				+ "@gmail.com";
		contactStatusEdited = "Published";

	}

	@Test(description = "Verify that user can edit a client")
	public void TC_JOOMLA_BANNERS_CLIENTS_002() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objBannerClientsPage = objHomePage.gotoBannerClient(driver);

		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.typeANewBannerClients(clientName,
				contactName, contactEmail, contactStatus);

		objEditBannerClient = objCreateNewBannerClients.clickSaveButton();

		AssertTrue(objEditBannerClient.isTextBannerClientSavedSuccessfullyDisplayed());

		AssertTrue(objEditBannerClient.isEditBannerPageDisplayed());

		objEditBannerClient.editABannerClients(clientNameEdited,
				contactNameEdited, contactEmailEdited, contactStatusEdited);

		objBannerClientsPage = objEditBannerClient.clickSaveCloseButton();

		AssertTrue(objBannerClientsPage.isTextBannerClientSavedSuccessfullyDisplayed());

		objBannerClientsPage.searchBannerClient(clientNameEdited);

		AssertTrue(objBannerClientsPage.isBannerClientExist(clientNameEdited));

	}

	@Test(description = "Verify that user can unpublish a client")
	public void TC_JOOMLA_BANNERS_CLIENTS_004() {

		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientNameEdited);

		objBannerClientsPage.clickUnpublishButton();

		AssertTrue(objBannerClientsPage.isTextBannerClientUnpublishedSuccessfullyDisplayed());

		AssertTrue(objBannerClientsPage
				.isBannerClientUnpublished(clientNameEdited));

	}

	@Test(description = "Verify that user can send a client to trash")
	public void TC_JOOMLA_BANNERS_CLIENTS_006() {

		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientNameEdited);

		objBannerClientsPage.clickTrashButton();

		AssertTrue(objBannerClientsPage.isTextBannerClientTrashedSuccessfullyDisplayed());

		objBannerClientsPage.selectOptionFromStatusDropdown(option);

		AssertTrue(objBannerClientsPage.isBannerClientExist(clientNameEdited));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logOut(driver);
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

	private String option;
}
