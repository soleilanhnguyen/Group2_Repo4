package categorymanager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.ArticlePage;
import pages.CategoryManagerPage;
import pages.CreateNewArticlePage;
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
		categoryTitle1 = Common.getUniqueString("MOVE");
		article1 = Common.getUniqueString("article1");
		article2 = Common.getUniqueString("article2");
		categoryTitle2 = Common.getUniqueString("MOVE2");
		categoryTitle3 = Common.getUniqueString("COPY");
		Common.getUniqueString("COPY");
		textsearch= Common.getUniqueString("");
		levelValue="Special";
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
	
	@Test(description = "TC_CategoryManager_014: User can move may articles to another category")
	public void TC_CategoryManager_014() {

		objCreateNewCategory.clickSaveNewButton();
		
		objCreateNewCategory.typeCategoryName(categoryTitle3);
		
		objCategoryManagerPage = objCreateNewCategory.clickSaveCloseButton();
		
		objArticlePage = objCategoryManagerPage.clickArticle();		
		
		objCreateNewArticlePage = objArticlePage.createNewArticleByNewIcon();
		
		objCreateNewArticlePage.typeTitle(article1);
		
		objCreateNewArticlePage.typeArticleText(article1);
		
		objCreateNewArticlePage.clickSaveAndNew();
		
		objCreateNewArticlePage.typeTitle(article2);
		
		objCreateNewArticlePage.typeArticleText(article2);
		
		objArticlePage=objCreateNewArticlePage.clickSaveAndClose();
		
		objArticlePage.searchArticle(textsearch);
		
		objArticlePage.clickOnCheckBoxArticle(article1);
		
		objArticlePage.clickOnCheckBoxArticle(article2);
		
		objArticlePage.selectCategoryForMoveOrCopy(categoryTitle3);
		
		objArticlePage.clickOnbuttonProcess();
		
		String catArticle1 = objArticlePage.getCategoryOfArticle(article1);
		
		String catArticle2 = objArticlePage.getCategoryOfArticle(article2);
		
		AssertTrue(objArticlePage.isMsgProcessCompeletedSuccessfullyDisplayed());
		
		AssertTrue(catArticle1.equals(categoryTitle3));
		
		AssertTrue(catArticle2.equals(categoryTitle3));
		
	}
	
	@Test(description = "TC_CategoryManager_015: User can copy may articles to another category")
	public void TC_CategoryManager_015() {

		objArticlePage.clickOnCheckBoxArticle(article1);
		
		objArticlePage.clickOnCheckBoxArticle(article2);
		
		objArticlePage.selectCategoryForMoveOrCopy(categoryTitle1);
		
		objArticlePage.selectCopyCheckbox();
		
		objArticlePage.clickOnbuttonProcess();
		
		AssertTrue(objCategoryManagerPage.isMsgProcessCompeletedSuccessfullyDisplayed());
		
		objArticlePage.searchArticle(textsearch,categoryTitle1);
		
		String catArticle3 = objArticlePage.getCategoryOfArticle(article1);
		
		String catArticle4 = objArticlePage.getCategoryOfArticle(article2);
		
		AssertTrue(catArticle3.equals(categoryTitle1));
		
		AssertTrue(catArticle4.equals(categoryTitle1));
	}
	
	@Test(description = "TC_CategoryManager_016: User can change access level to may articles")
	public void TC_CategoryManager_016() {

		objArticlePage.searchArticle(textsearch,categoryTitle1);
		
		objArticlePage.clickOnCheckBoxArticle(article1);
		
		objArticlePage.clickOnCheckBoxArticle(article2);
		
		objArticlePage.selectSetLevelAccessOption(levelValue);
		
		objArticlePage.clickOnbuttonProcess();
		
		AssertTrue(objArticlePage.isMsgProcessCompeletedSuccessfullyDisplayed());
		
		String catArticle5 = objArticlePage.getAcessOfArticle(article1);
		
		String catArticle6 = objArticlePage.getAcessOfArticle(article2);
		
		AssertTrue(catArticle5.equals(levelValue));
		
		AssertTrue(catArticle6.equals(levelValue));
		
		objArticlePage.deleteArticle(article1);
		
		objArticlePage.deleteArticle(article2);
		
		objCategoryManagerPage = objArticlePage.clickButtonCategories();
		
		objCategoryManagerPage.searchCategoryName(categoryTitle1);

		objCategoryManagerPage.deleteCategory(categoryTitle1);
		
	}
	
	@Test(description = "TC_CategoryManager_017: User cannot create a new Category without entering the title of the category")
	public void TC_CategoryManager_017() {
	
	 objCreateNewCategory = objCategoryManagerPage.clickOnNewbutton();
	 
	 objCreateNewCategory.clickSaveAsButton();
	 
	 objCreateNewCategory.closePopup();
	 
	 String color = objCreateNewCategory.getColorOfTitle();	 
	 
	 AssertTrue(color.equalsIgnoreCase("#FF0000"));
 
 	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		//closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private CategoryManagerPage objCategoryManagerPage;
	private CreateNewCategory objCreateNewCategory;
	private CreateNewArticlePage objCreateNewArticlePage;
	private ArticlePage objArticlePage;
	private String categoryTitle1;
	private String categoryTitle2;
	private String categoryTitle3;
	private String textsearch;
	private String levelValue;
	private String article1;
	private String article2;
}
