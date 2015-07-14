package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArticlePage extends AbstractPage {
	public ArticlePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CreateNewArticlePage createNewArticleByNewIcon() {

		click(BTN_NEW);
		return new CreateNewArticlePage(driver);

	}

	public EditArticlePage editArticleByEditIcon() {

		click(BTN_EDIT);
		return new EditArticlePage(driver);

	}

	public ArticlePage searchArticle(String title) {
		TXT_FILTER.clear();
		TXT_FILTER.sendKeys(title);
		BTN_SUBMIT.click();
		return new ArticlePage(driver);
	}

	/**
	 * @author Anh Nguyen
	 * @param message
	 * @description verify that message displays correctly
	 * 
	 */
	public boolean isMessageArticleDisplayed(String messageText) {
		waitForControl(driver, MESSAGE_SUCCESS, timeout);
		String message = MESSAGE_SUCCESS.getText();
		boolean isShow = false;
		if (message.equals(messageText))
			isShow = true;
		return isShow;
	}

	public boolean isCorrectStatusIconDisplayed(String status, String title) {
		WebElement element = findElementByXPath(driver, statusIcon, title);
		String temp = element.getAttribute("innerHTML");
		boolean isShow = false;
		if (status.equals(temp))
			isShow = true;
		return isShow;

	}

	public void clickOnStatusIcon(String title) {
		clickOnCheckBoxArticle(title);
		WebElement element = findElementByXPath(driver, linkStatusIcon, title);
		click(element);
	}

	public boolean isArticleExist(String title) {
		try {
			WebElement element = findElementByXPath(driver, initialArticleLink,
					title);
			waitForControl(driver, element, timeout);
			return element.isDisplayed();

		} catch (Exception e) {

			return false;
		}

	}

	public void clickOnCheckBoxArticle(String title) {
		WebElement webElement = findElementByXPath(driver, linkArticle, title);
		click(webElement);
	}

	public ArticlePage deleteArticle(String title) {

		searchArticle(title);
		clickOnCheckBoxArticle(title);
		click(BTN_DELETE);
		return new ArticlePage(driver);

	}

	public ArticlePage archiveArticle(String title) {
		searchArticle(title);
		clickOnCheckBoxArticle(title);
		click(BTN_ARCHIVE);
		return new ArticlePage(driver);

	}

	public ArticlePage moveArticleToTrash(String title) {
		searchArticle(title);
		clickOnCheckBoxArticle(title);
		click(BTN_TRASH);
		return new ArticlePage(driver);
	}

	public ArticlePage unpublishArticle(String title) {
		searchArticle(title);
		clickOnCheckBoxArticle(title);
		click(BTN_UNPUBLISH);
		return new ArticlePage(driver);
	}

	public void openHelp() {
		click(BTN_HELP);

	}

	public ArticlePage publishArticle(String title) {
		searchArticle(title);
		clickOnCheckBoxArticle(title);
		click(BTN_PUBLISH);
		return new ArticlePage(driver);
	}

	public WebDriver getArticlePageDriver() {
		return this.driver;
	}

	public ArticlePage selectStatus(String status) {

		selectDropDownListItemByText(DDL_STATUS, status);
		return new ArticlePage(driver);
	}

	private WebDriver driver;
	public String messageText = "Article successfully saved";
	public String messageTrashText = "1 article trashed.";
	public String messageArchiveText = "1 article archived.";
	public String messageUnpublishText = "1 article unpublished.";
	public String messagePublishText = "1 article published.";
	private String initialArticleLink = "//a[contains(text(),'%s')]";
	private String statusIcon = "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";
	private String linkStatusIcon = "//a[contains(text(),'%s')]/../following-sibling::td/a";
	private String linkArticle = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	
	@FindBy(xpath = "//li[@id='toolbar-new']/a/span")
	WebElement BTN_NEW;

	@FindBy(xpath = ".//*[@id='system-message']/dd/ul/li")
	WebElement MESSAGE_SUCCESS;

	@FindBy(xpath = "//input[@id='filter_search']")
	WebElement TXT_FILTER;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement BTN_SUBMIT;

	@FindBy(xpath = "//li[@id='toolbar-edit']/a/span")
	WebElement BTN_EDIT;

	@FindBy(xpath = ".//*[@id='toolbar-trash']/a/span")
	WebElement BTN_DELETE;

	@FindBy(xpath = ".//*[@id='toolbar-archive']/a/span")
	WebElement BTN_ARCHIVE;

	@FindBy(xpath = "//select[@name='filter_published']")
	WebElement DDL_STATUS;

	@FindBy(xpath = ".//*[@id='toolbar-trash']/a/span")
	WebElement BTN_TRASH;

	@FindBy(xpath = ".//*[@id='toolbar-unpublish']/a/span")
	WebElement BTN_UNPUBLISH;

	@FindBy(xpath = ".//*[@id='toolbar-publish']/a/span")
	WebElement BTN_PUBLISH;

	@FindBy(xpath = ".//*[@id='toolbar-help']/a/span")
	WebElement BTN_HELP;
}
