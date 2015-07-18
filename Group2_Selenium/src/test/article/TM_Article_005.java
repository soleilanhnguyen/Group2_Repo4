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

public class TM_Article_005 extends AbstractTest {
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
	@Test(description = "Verify user can check in an article")
	public void TC_Article_006(String browser) throws Exception {
		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);
		objArticlePage = objHomePage.gotoArticlePage(driver);

		objCreateNewArticlePage = objArticlePage.createNewArticleByNewIcon();

		objArticle.setTitle(txtTitle);
		objArticle.setCategory(categoryValue);
		objArticle.setArticleText(txtArticleText);
		objArticle.setStatus(unpublishedStatus);
		objArticlePage = objCreateNewArticlePage
				.createArticleBySave(objArticle);

		driver.close();
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);
		objArticlePage = objHomePage.gotoArticlePage(driver);

		objArticlePage.searchArticle(txtTitle);

		objArticlePage.checkInArticle(txtTitle);
		
		AssertTrue(objArticlePage.isMessageArticleDisplayed(objArticlePage.messageCheckinText));
		AssertTrue(objArticlePage.isArticleCheckIn(txtTitle));

	}

	@Test(description = "Verify user can access article's help section")
	public void TC_Article_008() {

		objArticlePage.openHelp();

		this.driver = objArticlePage.getArticlePageDriver();

		String windownArticleTitle = driver.getWindowHandle();

		AssertTrue(checkWindownExist(driver, windowHelpTitle));

		driver.close();

		driver.switchTo().window(windownArticleTitle);
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
