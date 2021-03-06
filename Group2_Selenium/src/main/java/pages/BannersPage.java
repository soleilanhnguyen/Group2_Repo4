package pages;

import java.util.List;

import org.openqa.selenium.By;
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

	public boolean isNameTextboxTurnRed() {

		boolean rightborder = isBorderRed("border-right-color");
		boolean topborder = isBorderRed("border-top-color");
		boolean bottomborder = isBorderRed("border-bottom-color");
		boolean leftborder = isBorderRed("border-left-color");

		if (rightborder == true && topborder == true && bottomborder == true
				&& leftborder == true)
			return true;

		else
			return false;

	}

	public boolean isQuantityChangeable(String quantity1, String quantity2) {
		boolean isChangeable = false;

		selectQuantity(quantity1);
		int countRow1 = getNumberofRow(tableXpath);

		selectQuantity(quantity2);

		int countRow2 = getNumberofRow(tableXpath);

		if (countRow1 != countRow2)
			isChangeable = true;

		return isChangeable;
	}

	public void selectQuantity(String quantity) {

		selectDropDownListItemByText(DDL_QUANTITY, quantity);

	}

	public int getNumberofRow(String tableXpath) {

		int countRow = driver.findElements(By.xpath(tableXpath)).size();

		return countRow;
	}

	public boolean isBorderRed(String border) {

		boolean isRed = false;
		String rgb[] = TXT_NAME.getCssValue(border)
				.replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
		if (rgb[0].equals("255") && rgb[1].equals("0") && rgb[2].equals("0"))
			isRed = true;
		return isRed;

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

	public BannersPage selectClient(String client) {

		selectDropDownListItemByText(DDL_CLIENT, client);
		return new BannersPage(driver);
	}

	public BannersPage selectCategory(String category) {

		selectDropDownListItemByText(DDL_CATEGORY, category);
		return new BannersPage(driver);
	}

	public boolean isSortedAscending() {

		List<WebElement> cells = TBL_BANNER.findElements(By
				.xpath("tr/td[13]/a"));

		for (int i = 1; i < (cells.size()); i++) {
			if (Integer.parseInt(cells.get(i - 1).getText()) > Integer
					.parseInt(cells.get(i).getText()))
				return false;
		}

		return true;

	}
	
	public boolean isSortedDescending() {

		List<WebElement> cells = TBL_BANNER.findElements(By
				.xpath("tr/td[13]/a"));

		for (int i = 1; i < (cells.size()); i++) {
			if (Integer.parseInt(cells.get(i - 1).getText()) < Integer
					.parseInt(cells.get(i).getText()))
				return false;
		}

		return true;

	}
	
	public void clickIDColumn() {
		click(ID_COLUMN_ICON);
	}
	
	public BannerClientsPage gotoClientPage(){
		
		click(MNU_CLIENTS);
		return new BannerClientsPage(driver);
	}
	
	private WebDriver driver;
	public String successfullyCreateBanner = "Banner successfully saved";
	public String successfullyUnpublishBanner = "1 banner successfully unpublished";
	private String initialBannerLink = "//a[contains(text(),'%s')]";
	private String linkBanner = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	public String messageCheckinText = "1 banner successfully checked in";
	private String linkCheckIn = "//td[a[contains(text(),'%s')]]/../td/a/span[@class='state checkedout']";
	private String tableXpath = ".//*[@id='adminForm']/table/tbody/tr";

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

	@FindBy(xpath = "//select[@name='filter_client_id']")
	WebElement DDL_CLIENT;

	@FindBy(xpath = "//select[@name='filter_category_id']")
	WebElement DDL_CATEGORY;

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

	@FindBy(xpath = ".//*[@id='jform_name']")
	WebElement TXT_NAME;

	@FindBy(xpath = ".//*[@id='limit']")
	WebElement DDL_QUANTITY;
	
	@FindBy(xpath = "//table[@class='adminlist']/tbody")
	WebElement TBL_BANNER;
	
	@FindBy(xpath = ".//*[@id='adminForm']/table/thead/tr/th[13]/a")
	private WebElement ID_COLUMN_ICON;

	@FindBy(xpath = ".//*[@id='submenu']/li[3]/a")
	WebElement MNU_CLIENTS;
	
}
