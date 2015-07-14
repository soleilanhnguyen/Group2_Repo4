package article;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Article;
import pages.ArticlePage;
import pages.CreateNewArticlePage;
import pages.EditArticlePage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_Article_001 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		objArticle = new Article();
		txtTitle = Common.getUniqueString("Title");
		txtEditTitle = Common.getUniqueString("EditTitle");
		categoryValue = "- - Joomla!";
		editCategoryValue = "20";
		txtArticleText = "New Article";
		txteditArticleText = "Edit Article";
		publishedstatus = "Unpublished";
	}

	@Test(description = "Verify user can create new article with valid information")
	public void TC_Article_001() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objArticlePage = objHomePage.gotoArticlePage(driver);		
		
		objCreateNewArticlePage = objArticlePage.createNewArticleByNewIcon();
		
		objArticle.setTitle(txtTitle);
		objArticle.setCategory(categoryValue);
		objArticle.setArticleText(txtArticleText);
		objArticlePage = objCreateNewArticlePage
				.createArticleBySaveAndClose(objArticle);
		
		AssertTrue(objArticlePage.isMessageArticleDisplayed(objArticlePage.messageText));
		
		objArticlePage.searchArticle(txtTitle);
		
		AssertTrue(objArticlePage.isArticleExist(txtTitle));

	}

	@Test(description = "Verify user can edit an article")
	public void TC_Article_002() {

		objArticlePage.clickOnCheckBoxArticle(txtTitle);
		
		objEditArticlePage = objArticlePage.editArticleByEditIcon();
		
		objArticle.setTitle(txtEditTitle);
		objArticle.setCategory(editCategoryValue);
		objArticle.setArticleText(txteditArticleText);
		objEditArticlePage.editArticleBySaveAndClose(objArticle);
		
		AssertTrue(objArticlePage.isMessageArticleDisplayed(objArticlePage.messageText));
		
		AssertTrue(objArticlePage.isArticleExist(txtTitle));
		
		objArticlePage.deleteArticle(txtTitle);
	}

	@Test(description = "User can search for articles using the filter text field")
	public void TC_Article_009() {

		objArticlePage = objHomePage.gotoArticlePage(driver);

		objCreateNewArticlePage = objArticlePage.createNewArticleByNewIcon();

		objArticle.setTitle(txtTitle);
		objArticle.setCategory(categoryValue);
		objArticle.setStatus(publishedstatus);
		objArticle.setArticleText(txtArticleText);
		objArticlePage = objCreateNewArticlePage
				.createArticleBySaveAndClose(objArticle);

		AssertTrue(objArticlePage.isMessageArticleDisplayed(objArticlePage.messageText));

		objArticlePage.searchArticle(txtTitle);
		
		AssertTrue(objArticlePage.isArticleExist(txtTitle));

		objArticlePage.deleteArticle(txtTitle);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private ArticlePage objArticlePage;
	private CreateNewArticlePage objCreateNewArticlePage;
	private EditArticlePage objEditArticlePage;
	private Article objArticle;
	private String txtTitle;
	private String categoryValue;
	private String editCategoryValue;
	private String txtArticleText;
	private String txteditArticleText;
	private String txtEditTitle;
	private String publishedstatus;

}
