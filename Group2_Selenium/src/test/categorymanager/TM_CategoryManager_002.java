package categorymanager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.CategoryManagerPage;
import pages.ContactsPage;
import pages.CreateNewCategory;
import pages.CreateNewContactPage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_CategoryManager_002 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		categoryTitle = Common.getUniqueString("text_category1");
		statustext = "Unpublished";
		buttonPublished = "publish";
		buttonunPublished = "unpublish";
		categoryStatusOfTable1 = "Published";
		categoryStatusOfTable2 = "Unpublished";
	}

	@Test(description = "TC_CategoryManager_003: Verify that user can publish a category")
	public void TC_CategoryManager_003() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objCategoryManagerPage = objHomePage.gotoCategoryManagerPage(driver);

		objCreateNewCategory = objCategoryManagerPage.clickOnNewbutton();

		objCreateNewCategory.typeCategoryName(categoryTitle);

		objCreateNewCategory.selectStatus(statustext);

		objCreateNewCategory.clickSaveCloseButton();

		AssertTrue(objCategoryManagerPage.isCategorySavedSucessfyllyDisplayed());

		objCategoryManagerPage.searchCategoryName(categoryTitle);

		AssertTrue(objCategoryManagerPage.isCategoryExist(categoryTitle));

		objCategoryManagerPage.clickOnCheckCategory(categoryTitle);

		objCategoryManagerPage.clickButtonOnTopRightToolbar(buttonPublished);

		AssertTrue(objCategoryManagerPage
				.isMsgCategoryPublishedSucessfyllyDisplayed());

		objCategoryManagerPage.verifyCategoryIsPublishedOrNot(categoryTitle,
				categoryStatusOfTable1);
	}

	@Test(description = "TC_CategoryManager_004: User can unpublish a category")
	public void TC_CategoryManager_004() {

		objCategoryManagerPage.clickOnCheckCategory(categoryTitle);

		objCategoryManagerPage.clickButtonOnTopRightToolbar(buttonunPublished);

		AssertTrue(objCategoryManagerPage
				.isMsgCategorytUnpublishedSucessfyllyDisplayed());

		objCategoryManagerPage.verifyCategoryIsPublishedOrNot(categoryTitle,
				categoryStatusOfTable2);

		objCategoryManagerPage.deleteCategory(categoryTitle);

	}

	@Test(description = "TC_CategoryManager_012: User can cancel adding action while adding a new create")
	public void TC_CategoryManager_012() {

		objCreateNewCategory = objCategoryManagerPage.clickOnNewbutton();

		objCreateNewCategory.typeCategoryName(categoryTitle);

		objCreateNewCategory.clickCancelButton();

		objCategoryManagerPage.searchCategoryName(categoryTitle);

		AssertFalse(objCategoryManagerPage.isCategoryExist(categoryTitle));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}
	
	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private CategoryManagerPage objCategoryManagerPage;
	private CreateNewCategory objCreateNewCategory;
	private String buttonPublished;
	private String buttonunPublished;
	private String categoryStatusOfTable1;
	private String categoryStatusOfTable2;
	private String statustext;
	private String categoryTitle;
}
