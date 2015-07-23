package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
		type(TXT_FILTER, title);
		click(BTN_SUBMIT);
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

	public boolean isCorrectFeatureIconDisplayed(String status, String title) {

		WebElement element = findElementByXPath(driver, featureIcon, title);
		String temp = element.getAttribute("alt");
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

	public void clickOnFeatureIcon(String title) {
		clickOnCheckBoxArticle(title);
		WebElement element = findElementByXPath(driver, linkFeatureIcon, title);
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

	public boolean isArticlePublicAccess(String title) {

		WebElement element = findElementByXPath(driver, accessStatus, title);
		String temp = element.getText();
		boolean isPublic = false;
		if (temp.equals("Public"))
			isPublic = true;
		return isPublic;

	}

	public boolean isArticleCheckIn(String title) {

		try {

			WebElement element = findElementByXPath(driver, linkCheckIn, title);
			element.isDisplayed();
			return false;
		} catch (NoSuchElementException ex) {
			return true;
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

	public ArticlePage checkInArticle(String title) {

		searchArticle(title);
		clickOnCheckBoxArticle(title);
		click(BTN_CHECKIN);
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

	/**
	 * @author Ha Nguyen
	 * @description Click on Button Process
	 * 
	 */
	public void clickOnbuttonProcess() {
		click(BTN_PROCESS);
	}

	/**
	 * @author Ha Nguyen
	 * @param categoryOption
	 * @description Select option for Move or Copy
	 * 
	 */

	public void selectCategoryForMoveOrCopy(String categoryOption) {
		selectOptionFromDropdownList(OPT_MOVECOPY, categoryOption);
	}

	/**
	 * @author Ha Nguyen
	 * @param articleName
	 * @description Get category of Article
	 * 
	 */
	public String getCategoryOfArticle(String articleName) {
		String control = "(//a[contains(text(),'%s')]/../../td)[5]";
		control = String.format(control, articleName);
		return driver.findElement(By.xpath(control)).getText().trim();

	}

	/**
	 * @author Ha Nguyen
	 * @param articleName
	 * @description Get Access of Article
	 * 
	 */

	public String getAcessOfArticle(String articleName) {
		String control = "(//a[contains(text(),'%s')]/../../td)[7]";
		control = String.format(control, articleName);
		return driver.findElement(By.xpath(control)).getText().trim();

	}

	/**
	 * @author Ha Nguyen
	 * @description Verify the message "Batch process completed successfully."
	 *              display
	 * 
	 */
	public boolean isMsgProcessCompeletedSuccessfullyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textProcessSuccessfully))
			isShow = true;
		return isShow;
	}

	/**
	 * @author Ha Nguyen
	 * @description Select the copy Check box
	 * 
	 */

	public void selectCopyCheckbox() {
		click(CHB_COPY);
	}

	/**
	 * @author Ha Nguyen
	 * @param title
	 *            , category
	 * @description Search Article by title and category
	 * 
	 */

	public ArticlePage searchArticle(String title, String category) {
		TXT_FILTER.clear();
		TXT_FILTER.sendKeys(title);
		selectDropDownListItemByText(OPT_FILTER_CATEGORY, category);
		BTN_SUBMIT.click();
		return new ArticlePage(driver);
	}

	/**
	 * @author Ha Nguyen
	 * @param levelValue
	 * @description Select Level Access Option
	 * 
	 */
	public void selectSetLevelAccessOption(String levelValue) {
		selectOptionFromDropdownList(OPT_SETACCESS, levelValue);
	}

	/**
	 * @author Ha Nguyen
	 * @description click on button Category
	 * 
	 */
	public CategoryManagerPage clickButtonCategories() {
		click(BTN_CATEGORIES);
		return new CategoryManagerPage(driver);
	}

	private WebDriver driver;
	public String messageText = "Article successfully saved";
	public String messageTrashText = "1 article trashed.";
	public String messageArchiveText = "1 article archived.";
	public String messageCheckinText = "1 article successfully checked in";
	public String messageUnpublishText = "1 article unpublished.";
	public String messagePublishText = "1 article published.";
	private String textProcessSuccessfully = "Batch process completed successfully.";
	private String initialArticleLink = "//a[contains(text(),'%s')]";
	private String statusIcon = "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";
	private String featureIcon = linkFeatureIcon = "//a[contains(text(),'%s')]/../following-sibling::td[2]/a/img";
	private String linkStatusIcon = "//a[contains(text(),'%s')]/../following-sibling::td/a";
	private String linkArticle = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	private String linkCheckIn = "//td[a[contains(text(),'%s')]]/../td/a/span[@class='state checkedout']";
	private String linkFeatureIcon = "//a[contains(text(),'%s')]/../following-sibling::td[2]/a";
	private String accessStatus = "//a[contains(text(),'%s')]/../following-sibling::td[5]";
	public String checkInState = "checkin";
	public String notCheckInState = "notcheckin";
	public String featuredState = "Featured article";
	public String unfeaturedState = "Unfeatured article";
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

	@FindBy(xpath = ".//*[@id='toolbar-checkin']/a/span")
	WebElement BTN_CHECKIN;

	@FindBy(xpath = "//select[@id='batch-category-id']")
	WebElement OPT_MOVECOPY;

	@FindBy(xpath = "//button[contains(text(),'Process')]")
	WebElement BTN_PROCESS;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul/li")
	private WebElement POPUP_MESSAGE;

	@FindBy(id = "batch[move_copy]c")
	private WebElement CHB_COPY;

	@FindBy(name = "filter_category_id")
	private WebElement OPT_FILTER_CATEGORY;

	@FindBy(xpath = "//*[@id='batch-access']")
	WebElement OPT_SETACCESS;

	@FindBy(xpath = "//*[@id='submenu']/li[2]/a")
	WebElement BTN_CATEGORIES;

}
