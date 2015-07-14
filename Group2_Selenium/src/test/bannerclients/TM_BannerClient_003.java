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

public class TM_BannerClient_003 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		clientName = Common.getUniqueString("TC_BannerClient_003_Name");
		contactName = Common.getUniqueString("TC_BannerClient_003_Contact");
		contactEmail = Common.getUniqueString("TC_BannerClient_003_Name")
				+ "@gmail.com";
		contactStatus = "Unpublished";
		option = "Unpublished";

	}

	@Test(description = "Verify that user can search a client by using filter dropdown lists")
	public void TC_BannerClient_009() {
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
		// Step 9: Select "Unpublished" in Status drop downlist
		objCreateNewBannerClients.typeANewBannerClients(clientName,
				contactName, contactEmail, contactStatus);

		// Step 10: Click on 'Save & Close' button
		objBannerClientsPage = objCreateNewBannerClients.clickSaveCloseButton();

		// Step 11: A message : "Client successfully saved" shows and new client
		// is created
		// VP: 1. _ A message : "Client successfully saved" shows.
		AssertTrue(objBannerClientsPage.isBannerClientSavedSuccessfully());

		// VP: 2. _ New client is created and matched with entered data
		objBannerClientsPage.searchBannerClient(clientName);
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName));

		// Step 12: Select "Unpublished" in Status drop downlist
		objBannerClientsPage.selectOptionFromStatusDropdown(option);

		// Step 13: Recently created client displays
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

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
