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
		redColor = "rgba(255, 0, 0, 1)";
		contactEmailIncorrect = "Email";

	}

	@Test(description = "Verify that user can create a new client")
	public void TC_BannerClient_001() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objBannerClientsPage = objHomePage.gotoBannerClient(driver);

		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.typeANewBannerClients(clientName,
				contactName, contactEmail, contactStatus);

		objBannerClientsPage = objCreateNewBannerClients.clickSaveCloseButton();

		AssertTrue(objBannerClientsPage.isBannerClientSavedSuccessfully());

		objBannerClientsPage.searchBannerClient(clientName);

		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName));
	}

	@Test(description = "Verify that user can publish a client")
	public void TC_BannerClient_003() {

		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientName);

		objBannerClientsPage.clickPublishButton();

		AssertTrue(objBannerClientsPage.isBannerClientPublishedSuccessfully());

		AssertTrue(objBannerClientsPage.isBannerClientPublished(clientName));

	}

	@Test(description = "Verify that user can archive a client")
	public void TC_BannerClient_005() {

		objBannerClientsPage.clickOnCheckBoxWebBannerClient(clientName);

		objBannerClientsPage.clickArchiveButton();

		AssertTrue(objBannerClientsPage.isBannerClientArchivedSuccessfully());

		objBannerClientsPage.selectOptionFromStatusDropdown(option);

		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName));

	}

	@Test(description = "Verify that user can browse Banner client help page")
	public void TC_BannerClient_007() {

		objBannerClientsPage.clickHelpButton();

		String windownBannerClientsPage = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, bannerclientsHelpTitle));

		driver.close();

		handleMultipleWindows(driver, windownBannerClientsPage);

	}

	@Test(description = "Verify that user can not create a new client without entering the name of the client")
	public void TC_BannerClient_014() {

		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.typeANewBannerClients("", contactName,
				contactEmail, contactStatus);

		objBannerClientsPage = objCreateNewBannerClients.clickSaveCloseButton();

		AssertTrue(objCreateNewBannerClients.getColorClientName().contains(
				redColor));

		AssertFalse(objCreateNewBannerClients.isBannerClientSavedSuccessfully());

		objBannerClientsPage = objCreateNewBannerClients.clickCancelButton();
	}

	@Test(description = "Verify that user can not create a new client after entering invalid email address")
	public void TC_BannerClient_015() {
		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.typeANewBannerClients(clientName,
				contactName, contactEmailIncorrect, contactStatus);

		objBannerClientsPage = objCreateNewBannerClients.clickSaveCloseButton();

		AssertTrue(objCreateNewBannerClients.getColorEmail().contains(redColor));

		AssertFalse(objCreateNewBannerClients.isBannerClientSavedSuccessfully());

		objBannerClientsPage = objCreateNewBannerClients.clickCancelButton();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logOut(driver);
		closeBrowser(driver);
	}

	private String bannerclientsHelpTitle;
	private String option;
	private String redColor;

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private BannerClientsPage objBannerClientsPage;
	private CreateNewBannerClients objCreateNewBannerClients;

	private String clientName;
	private String contactName;
	private String contactEmail;
	private String contactStatus;
	private String contactEmailIncorrect;

}
