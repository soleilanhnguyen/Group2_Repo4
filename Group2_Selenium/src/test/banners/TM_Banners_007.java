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
import pages.EditBannerPage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_Banners_007 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		windowHelpTitle = "Joomla! Help";
		clientName = Common.getUniqueString("Client");
		contactName = Common.getUniqueString("Client");
		contactEmail = Common.getUniqueString("Client") + "@yahoo.com";
		contactStatus = "Published";
		titleCategory = Common.getUniqueString("title");
		name = Common.getUniqueString("name");
		name2 = Common.getUniqueString("name2");
		objBanner = new Banner();

	}

	@Test(description = "Verify that user can browse New Banner help page in New banner page")
	public void TC_Banner_013() {

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
		objEditBannerPage = objCreateNewBannerPage
				.createBannerBySave(objBanner);

		AssertTrue(objEditBannerPage
				.isMessageSaveBannerDisplayed(objEditBannerPage.successfullySaveBanner));

		objBanner.setBannerName(name2);
		objEditBannerPage = objCreateNewBannerPage
				.createBannerBySaveAsCopy(objBanner);

		AssertTrue(objEditBannerPage
				.isMessageSaveBannerDisplayed(objEditBannerPage.successfullySaveBanner));

		objBannerPage = objEditBannerPage.cancel();

		objBannerPage.searchBanner(name);

		AssertTrue(objBannerPage.isBannerExist(name));

		objBannerPage.searchBanner(name2);

		AssertTrue(objBannerPage.isBannerExist(name2));
	}

	@Test(description = "Verify that user can browse New Banner help page in New banner page")
	public void TC_Banners_012() {

		objCreateNewBannerPage = objBannerPage.selectNewbutton();
		objCreateNewBannerPage.openHelp();

		this.driver = objCreateNewBannerPage.getNewBannerPageDriver();

		String windownBannerTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		driver.switchTo().window(windownBannerTitle);

		objCreateNewBannerPage.cancel();

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
	private CreateNewBannerPage objCreateNewBannerPage;
	private String windowHelpTitle;
	private BannerClientsPage objBannerClientsPage;
	private CreateNewBannerClients objCreateNewBannerClients;
	private CategoryBanner objCategoryBannerPage;
	private CreateCategoryBannerPage objCreateCategoryBannerPage;
	private EditBannerPage objEditBannerPage;
	private Banner objBanner;
	private String clientName;
	private String contactName;
	private String contactEmail;
	private String titleCategory;
	private String name;
	private String name2;
	private String contactStatus;

}
