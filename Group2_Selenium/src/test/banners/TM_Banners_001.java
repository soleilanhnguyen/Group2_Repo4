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

public class TM_Banners_001 extends AbstractTest {

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
		name = Common.getUniqueString("name");
		unpublishedStatus = "Unpublished";
		objBanner = new Banner();
	}

	@Test(description = "Verify that user can create new banner")
	public void TC_JOOMLA_Banner_001() {
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

		objBanner.setBannerName(name);
		objBanner.setBannerCategory(titleCategory);
		objBanner.setBannerClient(clientName);
		objCreateNewBannerPage.createBannerBySaveAndClose(objBanner);

		AssertTrue(objBannerPage
				.isMessageBannerDisplayed(objBannerPage.successfullyCreateBanner));

		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(name));

	}

	@Test(description = "Verify that user can search a banner by using filter textbox")
	public void TC_JOOMLA_Banner_008() {
		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(name));

	}

	@Test(description = "Verify that user can unpublish a banner")
	public void TC_JOOMLA_Banner_004() {

		objBannerPage.unPublishedBanner(name);

		AssertTrue(objBannerPage
				.isMessageBannerDisplayed(objBannerPage.successfullyUnpublishBanner));

		objBannerPage.selectStatus(unpublishedStatus);

		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(name));
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
	private String name;
	private String unpublishedStatus;
	private String contactStatus;

}
