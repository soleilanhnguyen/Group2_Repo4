package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BannersPage extends AbstractPage {
	public BannersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CreateNewBannerPage selectNewbutton() {

		click(BTN_NEW);
		return new CreateNewBannerPage(driver);

	}

	public boolean isMessageBannerDisplayed(String messageText) {
		waitForControl(driver, MESSAGE_SUCCESS, timeout);
		String message = MESSAGE_SUCCESS.getText();
		boolean isShow = false;
		if (message.equals(messageText))
			isShow = true;
		return isShow;
	}

	public boolean isBannerCheckIn(String name) {

		try {

			WebElement element = findElementByXPath(driver, linkCheckIn, name);
			element.isDisplayed();
			return false;
		} catch (NoSuchElementException ex) {
			return true;
		}

	}
	
	
	public void clickOnCheckBoxBanner(String name) {
		WebElement webElement = findElementByXPath(driver, linkBanner, name);
		click(webElement);
	}

	public BannersPage unPublishedBanner(String name) {

		searchBanner(name);
		clickOnCheckBoxBanner(name);
		click(BTN_UNPUBLISH);
		return new BannersPage(driver);
	}
	
	
	public BannersPage archiveBanner(String name) {
		searchBanner(name);
		clickOnCheckBoxBanner(name);
		click(BTN_ARCHIVE);
		return new BannersPage(driver);

	}

	public BannersPage moveBannerToTrash(String name) {
		searchBanner(name);
		clickOnCheckBoxBanner(name);
		click(BTN_TRASH);
		return new BannersPage(driver);

	}
	
	public void openHelp() {
		click(BTN_HELP);

	}
	public BannersPage searchBanner(String name) {
		TXT_FILTER.clear();
		TXT_FILTER.sendKeys(name);
		BTN_SUBMIT.click();
		return new BannersPage(driver);
	}
	
	public BannersPage checkInBanner(String name) {

		searchBanner(name);
		clickOnCheckBoxBanner(name);
		click(BTN_CHECKIN);
		return new BannersPage(driver);
	}
	
	
	
	public WebDriver getBannerPageDriver() {
		return this.driver;
	}

	public boolean isBannerExist(String name) {
		try {
			WebElement element = findElementByXPath(driver, initialBannerLink,
					name);
			waitForControl(driver, element, timeout);
			return element.isDisplayed();

		} catch (Exception e) {

			return false;
		}

	}

	public BannersPage selectStatus(String status) {

		selectDropDownListItemByText(DDL_STATUS, status);
		return new BannersPage(driver);
	}

	private WebDriver driver;
	public String successfullyCreateBanner = "Banner successfully saved";
	public String successfullyUnpublishBanner = "1 banner successfully unpublished";
	private String initialBannerLink = "//a[contains(text(),'%s')]";
	private String linkBanner = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	public String messageCheckinText = "1 banner successfully checked in";
	private String linkCheckIn = "//td[a[contains(text(),'%s')]]/../td/a/span[@class='state checkedout']";
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

	@FindBy(xpath = "//select[@name='filter_state']")
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

}
