package article;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Article;
import pages.ArticlePage;
import pages.CreateNewArticlePage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_Article_002 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		objArticle = new Article();
		txtTitle = Common.getUniqueString("Title");
		categoryValue = "- - Joomla!";
		txtArticleText = "New Article";
		archivedStatus = "Archived";
	}
		
	
	@Test(description = "User can move an article to the archive")
	public void TC_Article_005(){

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
		
		objArticlePage.archiveArticle(txtTitle);
		
		AssertTrue(objArticlePage.isMessageArticleDisplayed(objArticlePage.messageArchiveText));
		
		objArticlePage.selectStatus(archivedStatus);
		
		objArticlePage.searchArticle(txtTitle);
		AssertTrue(objArticlePage.isArticleExist(txtTitle));
		
		objArticlePage.deleteArticle(txtTitle);
	}
	
		
	
	
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logOut(driver);
		closeBrowser(driver);
	}

private WebDriver driver;
private LoginPage objLoginPage;
private HomePage objHomePage;
private ArticlePage objArticlePage;
private CreateNewArticlePage objCreateNewArticlePage;
private Article objArticle;
private String txtTitle;
private String categoryValue;
private String txtArticleText;
private String archivedStatus;

		
}
