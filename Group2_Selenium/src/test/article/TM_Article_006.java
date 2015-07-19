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

import common.Common;
import common.Constant;

import abtract.AbstractTest;

public class TM_Article_006 extends AbstractTest {
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		windowHelpTitle = "Joomla! Help";
		objArticle = new Article();
		txtTitle = Common.getUniqueString("Title");
		categoryValue = "- - Joomla!";
		txtArticleText = "New Article";
		unpublishedStatus = "Unpublished";
	}

	@Parameters("browser")
	@Test(description = "Verify user can change the feature property of articles using the Featured column")
	public void TC_Article_016(String browser) throws Exception {
		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);
		objArticlePage = objHomePage.gotoArticlePage(driver);

		objCreateNewArticlePage = objArticlePage.createNewArticleByNewIcon();

		objArticle.setTitle(txtTitle);
		objArticle.setCategory(categoryValue);
		objArticle.setArticleText(txtArticleText);
		objArticle.setStatus(unpublishedStatus);
		objArticlePage = objCreateNewArticlePage
				.createArticleBySaveAndClose(objArticle);
		
		AssertTrue(objArticlePage
				.isMessageArticleDisplayed(objArticlePage.messageText));

		objArticlePage.searchArticle(txtTitle);

		AssertTrue(objArticlePage.isArticleExist(txtTitle));

		objArticlePage.clickOnFeatureIcon(txtTitle);

		AssertTrue(objArticlePage.isCorrectFeatureIconDisplayed(objArticlePage.featuredState,
				txtTitle));

		objArticlePage.clickOnFeatureIcon(txtTitle);

		AssertTrue(objArticlePage.isCorrectFeatureIconDisplayed(objArticlePage.unfeaturedState,
				txtTitle));
		
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
	private String windowHelpTitle;
	private CreateNewArticlePage objCreateNewArticlePage;
	private Article objArticle;
	private String txtTitle;
	private String categoryValue;
	private String txtArticleText;
	private String unpublishedStatus;
}
