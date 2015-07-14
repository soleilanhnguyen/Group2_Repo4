package banners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BannerClientsPage;
import pages.BannersPage;
import pages.CategoryBanner;
import pages.CreateCategoryBannerPage;
import pages.CreateNewBannerClients;
import pages.CreateNewBannerPage;
import pages.EditBannerPage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_Banners_002 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		clientName = Common.getUniqueString("Client");
		contactName = Common.getUniqueString("Client");
		contactEmail = Common.getUniqueString("Client") + "@yahoo.com";
		titleCategory = Common.getUniqueString("title");
		name = Common.getUniqueString("name");
		editBannerName = Common.getUniqueString("editname");
		archivedStatus = "Archived";
		contactStatus = "Published";
	}

	@Test(description = "Verify that user can edit a banner")
	public void TC_Banner_002() {
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

		objCreateNewBannerPage.typeBannerName(name);
		objCreateNewBannerPage.selectCategory(titleCategory);
		objCreateNewBannerPage.selectClient(clientName);
		objEditBannerPage = objCreateNewBannerPage.createBannerBySave();

		AssertTrue(objEditBannerPage
				.isMessageSaveBannerDisplayed(objEditBannerPage.successfullySaveBanner));

		objEditBannerPage.editBannerName(editBannerName);
		objBannerPage = objEditBannerPage.createBannerBySaveAndClose();

		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(editBannerName));

	}

	@Test(description = "Verify that user can edit a banner")
	public void TC_Banner_005() {

		objBannerPage.archiveBanner(editBannerName);
		objBannerPage.selectStatus(archivedStatus);
		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(editBannerName));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
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
	private EditBannerPage objEditBannerPage;
	private String clientName;
	private String contactName;
	private String contactEmail;
	private String titleCategory;
	private String editBannerName;
	private String name;
	private String archivedStatus;
	private String contactStatus;
}
