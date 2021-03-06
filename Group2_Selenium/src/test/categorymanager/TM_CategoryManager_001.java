package categorymanager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.CategoryManagerPage;
import pages.CreateNewCategory;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_CategoryManager_001 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		categoryTitle1 = Common.getUniqueString("text_category1");
		categoryTitle2 = Common.getUniqueString("text_category2");
		buttonArchive = "archive";
		optionArchive = "Archived";
		optionTrashed = "Trashed";
		buttonHelp = "help";
		windowHelpTitle = "Joomla! Help";

	}

	@Test(description = "TC_CategoryManager_001: Verify that user can create a new category with valid information")
	public void TC_JOOMLA_CategoryManager_001() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objCategoryManagerPage = objHomePage.gotoCategoryManagerPage(driver);

		objCreateNewCategory = objCategoryManagerPage.clickOnNewbutton();

		objCreateNewCategory.typeCategoryName(categoryTitle1);

		objCreateNewCategory.clickSaveCloseButton();

		AssertTrue(objCategoryManagerPage.isCategorySavedSucessfyllyDisplayed());

		objCategoryManagerPage.searchCategoryName(categoryTitle1);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle1));
	}

	@Test(description = "TC_CategoryManager_002: Verify that user can edit a category")
	public void TC_JOOMLA_CategoryManager_002() {

		objCategoryManagerPage.clickOnCheckCategory(categoryTitle1);

		objCategoryManagerPage.clickEditButton();

		objCreateNewCategory.typeCategoryName(categoryTitle2);

		objCreateNewCategory.clickSaveCloseButton();

		AssertTrue(objCategoryManagerPage.isCategorySavedSucessfyllyDisplayed());

		objCategoryManagerPage.searchCategoryName(categoryTitle2);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle2));

	}

	@Test(description = "TC_CategoryManager_005: Verify that user can archive a category")
	public void TC_JOOMLA_CategoryManager_005() {

		objCategoryManagerPage.clickOnCheckCategory(categoryTitle2);

		objCategoryManagerPage.clickButtonOnTopRightToolbar(buttonArchive);

		AssertTrue(objCategoryManagerPage
				.isMsgCategorytArchivedSucessfyllyDisplayed());

		objCategoryManagerPage.selectOptionFromStatusDropdown(optionArchive);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle2));

	}

	@Test(description = "TC_CategoryManager_006: Verify that User can send a category to trash")
	public void TC_JOOMLA_CategoryManager_006() {

		objCategoryManagerPage.deleteCategory(categoryTitle2);

		AssertTrue(objCategoryManagerPage
				.isMsgCategoryTrashedSucessfyllyDisplayed());

		objCategoryManagerPage.selectOptionFromStatusDropdown(optionTrashed);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle2));
	}
	
	@Test(description = "TC_CategoryManager_007 User can access contact's help section")
	public void TC_JOOMLA_CategoryManager_007() {

		objCategoryManagerPage.clickButtonOnTopRightToolbar(buttonHelp);

		this.driver = objCategoryManagerPage.getCategoryManagerPageDriver();

		String windownCategoryManagerTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		driver.switchTo().window(windownCategoryManagerTitle);
	}

	@Test(description = "User can browse New Category help page")
	public void TC_JOOMLA_CategoryManager_011() {

		objCreateNewCategory = objCategoryManagerPage.clickOnNewbutton();

		objCreateNewCategory.clickHelpButton();

		this.driver = objCategoryManagerPage.getCategoryManagerPageDriver();

		String windownCategoryManagerTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		driver.switchTo().window(windownCategoryManagerTitle);
		
		objCreateNewCategory.clickCancelButton();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logOut(driver);
		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private CategoryManagerPage objCategoryManagerPage;
	private CreateNewCategory objCreateNewCategory;
	private String categoryTitle1;
	private String categoryTitle2;
	private String buttonArchive;
	private String optionArchive;
	private String optionTrashed;
	private String buttonHelp;
	private String windowHelpTitle;
	

}
