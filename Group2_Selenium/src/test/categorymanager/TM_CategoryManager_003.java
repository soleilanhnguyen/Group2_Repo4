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

public class TM_CategoryManager_003 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		categoryTitle1 = Common.getUniqueString("text_category1");
		categoryTitle2 = Common.getUniqueString("text_category2");
		Common.getUniqueString("");
		textUnpublished = "Unpublished";
		optionAcccess = "Registered";
		optionLanguage = "English (UK)";
		optionUnpublished = "Unpublished";
	}

	@Test(description = "TC_CategoryManger_010: User can create many categories by using Save & New button")
	public void TC_JOOMLA_CategoryManager_010() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objCategoryManagerPage = objHomePage.gotoCategoryManagerPage(driver);

		objCreateNewCategory = objCategoryManagerPage.clickOnNewbutton();

		objCreateNewCategory.typeCategoryName(categoryTitle1);

		objCreateNewCategory.selectStatus(textUnpublished);

		objCreateNewCategory.selectAccess(optionAcccess);

		objCreateNewCategory.selectLanguage(optionLanguage);

		objCreateNewCategory.clickSaveNewButton();

		AssertTrue(objCategoryManagerPage.isCategorySavedSucessfyllyDisplayed());

		objCreateNewCategory.typeCategoryName(categoryTitle2);

		objCreateNewCategory.clickSaveCloseButton();

		AssertTrue(objCategoryManagerPage.isCategorySavedSucessfyllyDisplayed());

		objCategoryManagerPage.searchCategoryName(categoryTitle1);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle1));

		objCategoryManagerPage.searchCategoryName(categoryTitle2);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle2));
	}

	@Test(description = "TC_CategoryManager_009: User can search a category by using filter dropdown lists")
	public void TC_JOOMLA_CategoryManger_009() {

		objCategoryManagerPage
				.selectOptionFromStatusDropdown(optionUnpublished);

		objCategoryManagerPage
				.selectAccessOptionFromAccessDropdown(optionAcccess);

		objCategoryManagerPage
				.selectLanguageOptionFromLanguageDropdown(optionLanguage);

		objCategoryManagerPage.searchCategoryName(categoryTitle1);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle1));

		objCategoryManagerPage.deleteCategory(categoryTitle1);

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
	private String textUnpublished;
	private String optionAcccess;
	private String optionLanguage;
	private String optionUnpublished;

}
