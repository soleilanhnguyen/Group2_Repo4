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

public class TM_CategoryManager_004 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		categoryTitle1 = Common.getUniqueString("text_category1");
		categoryTitle2 = Common.getUniqueString("text_category2");
	}

	@Test(description = "TC_CategoryManager_013: User can creat a new category by using Save as Copy button")
	public void TC_CategoryManager_013() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objCategoryManagerPage = objHomePage.gotoCategoryManagerPage(driver);

		objCreateNewCategory = objCategoryManagerPage.clickOnNewbutton();

		objCreateNewCategory.typeCategoryName(categoryTitle1);

		objCreateNewCategory.clickSaveButton();

		AssertTrue(objCategoryManagerPage.isCategorySavedSucessfyllyDisplayed());

		objCreateNewCategory.typeCategoryName(categoryTitle2);

		objCreateNewCategory.clickSaveAsButton();

		AssertTrue(objCategoryManagerPage.isCategorySavedSucessfyllyDisplayed());

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
	private String categoryTitle1;
	private String categoryTitle2;

}
