package bannerclients;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import abtract.AbstractTest;
import pages.BannerClientsPage;
import pages.CreateNewBannerClients;
import pages.CreateWebLinkPage;
import pages.EditBannerClient;
import pages.HomePage;
import pages.LoginPage;
import pages.WeblinksPage;
import common.Common;
import common.Constant;

public class TM_BannerClient_004 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		clientName = Common.getUniqueString("TC_BannerClient_004_Name");
		contactName = Common.getUniqueString("TC_BannerClient_004_Contact");
		contactEmail = Common.getUniqueString("TC_BannerClient_004_Name")
				+ "@gmail.com";
		contactStatus = "Published";

		clientName2 = Common.getUniqueString("TC_BannerClient_0042_Name");
		contactName2 = Common.getUniqueString("TC_BannerClient_0042_Contact");
		contactEmail2 = Common.getUniqueString("TC_BannerClient_0042_Name")
				+ "@gmail.com";
		contactStatus2 = "Published";

		clientName3 = Common.getUniqueString("TC_BannerClient_0043_Name");
		contactName3 = Common.getUniqueString("TC_BannerClient_0043_Contact");
		contactEmail3 = Common.getUniqueString("TC_BannerClient_0043_Name")
				+ "@gmail.com";
		contactStatus3 = "Published";

		clientName4 = Common.getUniqueString("TC_BannerClient_0044_Name");
		contactName4 = Common.getUniqueString("TC_BannerClient_0044_Contact");
		contactEmail4 = Common.getUniqueString("TC_BannerClient_0044_Name")
				+ "@gmail.com";
		contactStatus4 = "Published";
		
		clientName5 = Common.getUniqueString("TC_BannerClient_0045_Name");
		contactName5 = Common.getUniqueString("TC_BannerClient_0045_Contact");
		contactEmail5 = Common.getUniqueString("TC_BannerClient_0045_Name")
				+ "@gmail.com";
		contactStatus5 = "Published";
		
		windowHelpTitle = "Joomla! Help";

	}

	@Parameters("browser")
	@Test(description = "Verify user can check in a web link")
	public void TC_BannerClient_010(String browser) {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objBannerClientsPage = objHomePage.gotoBannerClient(driver);

		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.typeANewBannerClients(clientName,
				contactName, contactEmail, contactStatus);

		objCreateNewBannerClients.clickSaveButton();

		AssertTrue(objCreateNewBannerClients.isBannerClientSavedSuccessfully());

		driver.close();

		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objBannerClientsPage = objHomePage.gotoBannerClient(driver);

		objBannerClientsPage.searchBannerClient(clientName);

		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientName);

		objBannerClientsPage.clickCheckinButton();

		AssertTrue(objBannerClientsPage.isBannerClientCheckinSuccessfully());
		AssertTrue(objBannerClientsPage.isBannerClientCheckin(clientName));

	}

	@Test(description = "Verify that user can create many clients by using Save& New button")
	public void TC_BannerClient_011() {

		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.typeANewBannerClients(clientName2,
				contactName2, contactEmail2, contactStatus2);

		objCreateNewBannerClients.clickSaveNewButton();

		AssertTrue(objCreateNewBannerClients.isBannerClientSavedSuccessfully());

		AssertTrue(objCreateNewBannerClients
				.isCreateNewBannerClientPageDisplayed());

		objCreateNewBannerClients.typeANewBannerClients(clientName3,
				contactName3, contactEmail3, contactStatus3);

		objBannerClientsPage = objCreateNewBannerClients.clickSaveCloseButton();

		AssertTrue(objBannerClientsPage.isBannerClientSavedSuccessfully());

		objBannerClientsPage.searchBannerClient(clientName2);
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName2));

		objBannerClientsPage.searchBannerClient(clientName3);
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName3));
	}

	@Test(description = "Verify that user can browse New Client help ")
	public void TC_BannerClient_012() {

		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.clickHelpButton();

		String windownCreateNewBannerClientsTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		handleMultipleWindows(driver, windownCreateNewBannerClientsTitle);

	}
	
	@Test(description = "Verify that user can creat a new client by using Save as Copy button")
	public void TC_BannerClient_013(){
		
		objCreateNewBannerClients.typeANewBannerClients(clientName4,
				contactName4, contactEmail4, contactStatus4);
		
		objEditBannerClient = objCreateNewBannerClients.clickSaveButton();
		
		objEditBannerClient.editABannerClients(clientName5, contactName5, contactEmail5, contactStatus5);
		
		objEditBannerClient.clickSaveCopyButton();
		
		objBannerClientsPage = objEditBannerClient.clickCloseButton();
		
		objBannerClientsPage.searchBannerClient(clientName4);
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName4));
		
		objBannerClientsPage.searchBannerClient(clientName5);
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName5));
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

	private String clientName2;
	private String contactName2;
	private String contactEmail2;
	private String contactStatus2;

	private String clientName3;
	private String contactName3;
	private String contactEmail3;
	private String contactStatus3;
	
	private String clientName4;
	private String contactName4;
	private String contactEmail4;
	private String contactStatus4;
	
	private String clientName5;
	private String contactName5;
	private String contactEmail5;
	private String contactStatus5;
	
	private String windowHelpTitle;
}
