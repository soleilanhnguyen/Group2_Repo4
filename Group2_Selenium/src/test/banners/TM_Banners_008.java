package banners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Banner;
import pages.BannerClientsPage;
import pages.BannersPage;
import pages.CategoryBanner;
import pages.CreateCategoryBannerPage;
import pages.CreateNewBannerClients;
import pages.CreateNewBannerPage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;

import common.Common;
import common.Constant;

public class TM_Banners_008 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		clientName = Common.getUniqueString("Client");
		contactName = Common.getUniqueString("Client");
		contactEmail = Common.getUniqueString("Client") + "@yahoo.com";
		contactStatus = "Published";
		titleCategory = Common.getUniqueString("title");
		objBanner = new Banner();
		quantity1 = "5";
		quantity2 = "10";

	}

	@Test(description = "Verify that user cannot create a new banner without entering the name of the banner")
	public void TC_JOOMLA_Banner_014() {
		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objBannerClientsPage = objHomePage.gotoBannerClient(driver);
		objCreateNewBannerClients = objBannerClientsPage.clickNewButton();

		objCreateNewBannerClients.typeANewBannerClients(clientName,
				contactName, contactEmail, contactStatus);

		objBannerClientsPage = objCreateNewBannerClients.clickSaveCloseButton();

		objBannerClientsPage.verifyBannerClientSavedSuccessfully();

		objBannerClientsPage.searchBannerClient(clientName);
		AssertTrue(objBannerClientsPage.isBannerClientExist(clientName));

		objCategoryBannerPage = objHomePage.gotoCategoryBannerPage(driver);

		objCreateCategoryBannerPage = objCategoryBannerPage.selectNewbutton();

		objCategoryBannerPage = objCreateCategoryBannerPage
				.createNewCategoryBanner(titleCategory);

		AssertTrue(objCategoryBannerPage
				.isMessageCategoryBannerDisplayed(objCategoryBannerPage.successfullycreatecategorybanner));

		objBannerPage = objHomePage.gotoBannerPage(driver);

		objCreateNewBannerPage = objBannerPage.selectNewbutton();

		objBanner.setBannerCategory(titleCategory);
		objBanner.setBannerClient(clientName);
		objCreateNewBannerPage.createBannerBySaveAndClose(objBanner);

		AssertTrue(objBannerPage.isNameTextboxTurnRed());

		objCreateNewBannerPage.cancel();

	}

	@Test(description = "Verify that user can change the quantity of items displayed in banner table")
	public void TC_JOOMLA_Banner_015() {

		objBannerPage.isQuantityChangeable(quantity1, quantity2);

	}

	@Test(description = "Verify that user can sort items displayed in banner table by ID")
	public void TC_JOOMLA_Banner_016() {

		objBannerPage.selectQuantity("All");

		objBannerPage.clickIDColumn();

		AssertTrue(objBannerPage.isSortedAscending());

		objBannerPage.clickIDColumn();

		AssertTrue(objBannerPage.isSortedDescending());

	}

	@Test(description = "Verify that user can access Banner clients page while browsing Banner page")
	public void TC_JOOMLA_Banner_017() {

		objBannerClientsPage = objBannerPage.gotoClientPage();
		AssertTrue(objBannerClientsPage.isClientPageDisplayed());

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logOut(driver);
		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private BannersPage objBannerPage;
	private BannerClientsPage objBannerClientsPage;
	private CreateNewBannerPage objCreateNewBannerPage;
	private CreateNewBannerClients objCreateNewBannerClients;
	private CategoryBanner objCategoryBannerPage;
	private CreateCategoryBannerPage objCreateCategoryBannerPage;
	private Banner objBanner;
	private String clientName;
	private String contactName;
	private String contactEmail;
	private String titleCategory;

	private String contactStatus;
	private String quantity1;
	private String quantity2;

}
