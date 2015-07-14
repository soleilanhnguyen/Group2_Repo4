package banners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import abtract.AbstractTest;
import pages.BannerClientsPage;
import pages.BannersPage;
import pages.CategoryBanner;
import pages.CreateCategoryBannerPage;
import pages.CreateNewBannerClients;
import pages.CreateNewBannerPage;
import pages.HomePage;
import pages.LoginPage;
import common.Common;
import common.Constant;

public class TM_Banners_003 extends AbstractTest {
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
		unplishedStatus = "Unpublished";
		trashedStatus = "Trashed";
		windowHelpTitle = "Joomla! Help";
		contactStatus = "Published";
	}

	@Test(description = "Verify that user can create a new banner with unpublished status")
	public void TC_Banner_003() {
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
		objCreateNewBannerPage.selectStatus(unplishedStatus);
		objCreateNewBannerPage.createBannerBySaveAndClose();

		AssertTrue(objBannerPage
				.isMessageBannerDisplayed(objBannerPage.successfullyCreateBanner));
		objBannerPage.selectStatus(unplishedStatus);

		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(name));

	}

	@Test(description = "Verify that user can send a banner to trash")
	public void TC_Banner_006() {

		objBannerPage.moveBannerToTrash(name);
		objBannerPage.selectStatus(trashedStatus);

		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(name));
	}
	
	@Test(description = "Verify that user can browse Banner help page")
	public void TC_Banner_007(){
		objHomePage.gotoBannerPage(driver);
		objBannerPage.openHelp();
		this.driver = objBannerPage.getBannerPageDriver();

		String windownBannerTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		driver.switchTo().window(windownBannerTitle);
		
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
	private String windowHelpTitle;
	private String clientName;
	private String contactName;
	private String contactEmail;
	private String titleCategory;
	private String name;
	private String unplishedStatus;
	private String trashedStatus;
	private String contactStatus;
}
