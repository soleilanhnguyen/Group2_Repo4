package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import common.Constant;

public class WeblinksPage extends AbstractPage {

	/**
	 * @author Dung Pham 06/28/2015
	 * @param driver
	 * @description this is contructor of page WeblinksPage
	 */
	public WeblinksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param weblinkTitle
	 * @return boolean
	 * @description check the status of weblink is published
	 */
	public boolean isWeblinkPublished(String weblinkTitle) {
		try {
			WebElement element = findElementByXPath(driver,
					initialStringStatusxOfWeblink, weblinkTitle);

			String text = element.getAttribute("innerHTML");

			return text.equals(weblinkStatusPublished);

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check the popup text to make sure weblink is published
	 */
	public boolean isTextPublishedSuccessfullyDisplayOnPopup() {

		try {
			return POPUP_MESSAGE.getText().contains(
					textWeblinkPublishedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description click Trash button
	 */
	public void clickTrashButton() {
		click(BTN_TRASH);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check the popup text to make sure weblink is trashed
	 */
	public boolean isTextTrashedSuccessfullyDisplayOnPopup() {

		try {
			return POPUP_MESSAGE.getText().contains(
					textWeblinkTrashedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description click Unpublish button
	 */
	public void clickUnpublishButton() {
		click(BTN_UNPUBLISH);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param weblinkTitle
	 * @return boolean
	 * @description check the status weblink is unpublished
	 */
	public boolean isWeblinkUnpublished(String weblinkTitle) {
		try {
			WebElement element = findElementByXPath(driver,
					initialStringStatusxOfWeblink, weblinkTitle);

			String text = element.getAttribute("innerHTML");

			return text.equals(weblinkStatusUnpublished);

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check the text of popup to make sure weblink is unpublished
	 */
	public boolean isTextUnpublishedSuccessfullyDisplayOnPopup() {

		try {
			return POPUP_MESSAGE.getText().contains(
					textWeblinkUnpublishedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description clich Archive button
	 */
	public void clickArchiveButton() {
		click(BTN_ARCHIVE);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check the text of popup to make sure weblink is archived
	 */
	public boolean isTextArchivedSuccessfullyDisplayOnPopup() {

		try {
			return POPUP_MESSAGE.getText().contains(
					textWeblinkArchivedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description click Check in button
	 */
	public void clickCheckinButton() {
		click(BTN_CHECKIN);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check the text of popup to make sure weblink is checked in
	 */
	public boolean isTextCheckinSuccessfullyDisplayOnPopup() {

		try {
			return POPUP_MESSAGE.getText().contains(
					textWeblinkCheckinSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param weblinkTitle
	 * @return boolean
	 * @description check the lock icon does not display beside weblink
	 */
	public boolean isWeblinkCheckin(String weblinkTitle) {

		try {

			WebElement element = findElementByXPath(driver, initialLinkCheckIn,
					weblinkTitle);
			element.isDisplayed();
			return false;
		} catch (NoSuchElementException ex) {
			return true;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param option
	 * @description select a option from display dropdown
	 */
	public void selectDisplayDropdown(String option) {
		waitForControl(driver, OPT_DISPLAY, Constant.longTimeOut);
		selectOptionFromDropdownList(OPT_DISPLAY, option);
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check the 5 weblinks are displays
	 */
	public boolean is5WeblinksDisplaying() {
		try {
			List<WebElement> rows = TBL_WEBLINK.findElements(By.tagName("tr"));

			boolean result = (rows.size() == 5) ? true : false;

			return result;
		} catch (Exception e) {

			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check all weblink display > 100
	 */
	public boolean isAllWeblinksDisplaying() {
		try {
			List<WebElement> rows = TBL_WEBLINK.findElements(By.tagName("tr"));

			boolean result = (rows.size() > 100) ? true : false;
			return result;

		} catch (Exception e) {

			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description click ID column
	 */
	public void clickIDColumn() {
		click(ID_COLUMN_ICON);
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check all weblinks are sort ascending
	 */
	public boolean isWeblinkSortedAscending() {

		waitForControl(driver, TBL_WEBLINK, Constant.longTimeOut);

		List<WebElement> cells = TBL_WEBLINK.findElements(By
				.xpath("tr/td[9]/a"));

		for (int i = 1; i < (cells.size()); i++) {
			if (Integer.parseInt(cells.get(i - 1).getText()) > Integer
					.parseInt(cells.get(i).getText()))
				return false;
		}

		return true;

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check all weblinks are sort descending
	 */
	public boolean isWeblinkSortedDescending() {

		waitForControl(driver, TBL_WEBLINK, Constant.longTimeOut);

		List<WebElement> cells = TBL_WEBLINK.findElements(By
				.xpath("tr/td[9]/a"));

		for (int i = 1; i < (cells.size()); i++) {
			if (Integer.parseInt(cells.get(i - 1).getText()) < Integer
					.parseInt(cells.get(i).getText()))
				return false;
		}

		return true;

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description click Ordering button column
	 */
	public void clickOrderingColumn() {
		click(ID_COLUMN_ORDERING);
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param weblinkTitle
	 * @description click the down arrow in ordering column
	 */
	public void clickDownArrowOrderingColumn(String weblinkTitle) {

		WebElement webElement = findElementByXPath(driver,
				initialDownArrowOrderingColumn, weblinkTitle);
		click(webElement);
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param weblinkTitle
	 * @return boolean
	 * @description check the weblink is the last of table
	 */
	public boolean isWeblinkLast(String weblinkTitle) {

		List<WebElement> rows = TBL_WEBLINK.findElements(By.tagName("tr"));

		int rowsInt = rows.size();

		try {

			return TBL_WEBLINK
					.findElement(By.xpath("tr[" + rowsInt + "]/td[2]"))
					.getText().contains(weblinkTitle);

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 07/18/2015
	 * @description click Publish button
	 */
	public void clickPublishButton() {
		click(BTN_PUBLISH);

	}

	/**
	 * @author Dung Pham 07/18/2015
	 * @description click Help button
	 */
	public void clickHelpButton() {
		click(BTN_HELP);

	}

	/**
	 * @author Dung Pham 07/13/2015
	 * @description click on Status button at the table
	 */
	public void clickOnStatusWebLink(String weblinkTitle) {
		WebElement webElement = findElementByXPath(driver,
				initialStatusWeblink, weblinkTitle);
		click(webElement);
	}

	/**
	 * @author Dung Pham 07/12/2015
	 * @param textNeedToBeTested
	 * @description Test the text displaying on the pop up, the text is correct
	 *              or not
	 */
	public boolean isTextDisPlayOnPopupMessage(String textNeedToBeTested) {
		try {
			return POPUP_MESSAGE.getText().contains(textNeedToBeTested);
		} catch (Exception e) {

			return false;
		}

	}

	/**
	 * @author Dung Pham 07/12/2015
	 * @param weblinkTitle
	 * @param status
	 * @description check the Weblink is Published or not
	 */
	public boolean isWeblinkIsPublishedOrNot(String weblinkTitle,
			String weblinkStatusOfTable) {
		// get webelement of span which is besides weblinkTitle

		try {
			WebElement element = findElementByXPath(driver,
					initialStringStatusxOfWeblink, weblinkTitle);

			String text = element.getAttribute("innerHTML");
			// check the text with "Published" or "Unpublished"
			return text.equals(weblinkStatusOfTable);

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @author Dung Pham 07/12/2015
	 * @description check the text "Weblink successfully saved" display after
	 *              creating new weblink successfully
	 */
	public boolean isTextSavedSuccessfullyDisplayOnPopup() {

		try {

			waitForControl(driver, POPUP_MESSAGE, Constant.longTimeOut);

			return POPUP_MESSAGE.getText().contains(
					textWeblinkSavedSuccessfully);
		} catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}

	/**
	 * @author Dung Pham 07/07/2015
	 * @param weblinkTitle
	 * @return true/false
	 * @description check the web link exist on the table
	 */
	public boolean isWeblinkExist(String weblinkTitle) {
		try {
			WebElement element = findElementByXPath(driver,
					initialWeblinkString, weblinkTitle);
			waitForControl(driver, element, Constant.longTimeOut);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham
	 * @param weblinkTitle
	 * @return
	 * @description delete a weblink
	 */
	public WeblinksPage deleteWeblink(String weblinkTitle) {
		searchWeblink(weblinkTitle);
		clickOnCheckBoxWebLink(weblinkTitle);
		clickButtonOnTopRightToolbar("trash");
		return this;
	}

	/**
	 * @author Dung Pham 07/06/2015
	 * @return
	 * @description return the driver of WeblinkPage
	 */
	public WebDriver getWeblinksPageDriver() {
		return this.driver;
	}

	/**
	 * @author Dung Pham create 07/02/2015
	 * @param option
	 * @description select option from Status select at the top right screen
	 */
	public void selectOptionFromStatusDropdown(String option) {
		selectOptionFromDropdownList(OPT_STATUS, option);
	}

	/**
	 * @author Dung Pham create 07/06/2015
	 * @param option
	 * @description select option from Category Dropdown at the top right screen
	 */
	public void selectOptionFromCategoryDropdown(String option) {
		selectOptionFromDropdownList(OPT_CATEGORY, option);
	}

	/**
	 * @author Dung Pham 07/02/2015
	 * @param textNeedToBeTested
	 * @description Test the text displaying on the pop up, the text is correct
	 *              or not
	 */
	public void verifyTextDisPlayOnPopupMessage(String textNeedToBeTested) {
		Assert.assertTrue(POPUP_MESSAGE.getText().contains(textNeedToBeTested));

	}

	/**
	 * @author Dung Pham 07/02/2015
	 * @param weblinkTitle
	 * @param status
	 */
	public void verifyWeblinkIsPublishedOrNot(String weblinkTitle,
			String weblinkStatusOfTable) {
		// get webelement of span which is besides weblinkTitle

		WebElement element = findElementByXPath(driver,
				initialStringStatusxOfWeblink, weblinkTitle);

		String text = element.getAttribute("innerHTML");
		// check the text with "Published" or "Unpublished"
		Assert.assertTrue(text.equals(weblinkStatusOfTable));
	}

	/**
	 * @author Dung Pham 07/04/2015
	 * @param weblinkTitle
	 * @description type the weblink into the search text box, then pressing
	 *              Search button
	 */
	public void searchWeblink(String weblinkTitle) {

		waitForControl(driver, TXT_SEARCH, Constant.longTimeOut);

		waitForControl(driver, BTN_SEARCH, Constant.longTimeOut);

		type(TXT_SEARCH, weblinkTitle);

		click(BTN_SEARCH);

		waitForControl(driver, TBL_WEBLINK, Constant.longTimeOut);
	}

	/**
	 * @author Dung Pham 04/07/2915
	 * @description click on a button on top right toolbar. Use to click New,
	 *              Edit,.. button
	 * @param String
	 *            button
	 * @description click the Button on the Top right toolbar
	 */
	public void clickButtonOnTopRightToolbar(String button) {
		if (button != "options") {

			WebElement element = findElementByXPath(driver,
					initialStringButtonOnTopRightBar, button);
			click(element);

		} else
			// else: use for options button
			driver.findElement(By.xpath("toolbar-popup-options")).click();
	}

	/**
	 * @author Dung Pham 06/28/2015
	 * @description Create a new web link by clicking on icon New
	 * @return object of CreateWebLinkPage
	 * @description click on New button
	 */
	public CreateWebLinkPage clickNewButton() {

		waitForControl(driver, BTN_NEW, Constant.longTimeOut);

		click(BTN_NEW);

		return new CreateWebLinkPage(driver);
	}

	/**
	 * @author Dung Pham 06/28/2015
	 * @description verify the text "Weblink successfully saved" display after
	 *              creating new weblink successfully
	 */
	public void verifyWebLinkSavedSuccessfully() {
		Assert.assertTrue(POPUP_MESSAGE.getText().contains(
				textWeblinkSavedSuccessfully));

	}

	/**
	 * @author Dung Pham 06/29/2015
	 * @param weblinkTitle
	 * @description Select the check box of WebLink
	 */
	public void clickOnCheckBoxWebLink(String weblinkTitle) {
		WebElement webElement = findElementByXPath(driver,
				initialCheckBoxWeblink, weblinkTitle);
		click(webElement);
	}

	/**
	 * @author Dung Pham 06/28/2015
	 * @return
	 * @Description click on Edit button
	 */
	public CreateWebLinkPage clickEditButton() {
		click(BTN_EDIT);
		return new CreateWebLinkPage(driver);
	}

	private WebDriver driver;

	private String initialWeblinkString = "//a[contains(text(),'%s')]";
	private String initialCheckBoxWeblink = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	private String initialStatusWeblink = "//a[contains(text(),'%s')]/../following-sibling::td/a/span";
	private String initialStringButtonOnTopRightBar = "//li[@id='toolbar-%s']/a/span";
	private String initialStringStatusxOfWeblink = "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";
	private String initialLinkCheckIn = "//td[a[contains(text(),'%s')]]/../td/a/span[@class='state checkedout']";
	private String initialDownArrowOrderingColumn = "//td[a[contains(text(),'%s')]]/../td//span[@class='state downarrow']";
	private String initialCellID = "//table[@class='adminlist']/tbody/tr[%s]/td[9]/a";

	private String weblinkStatusPublished = "Published";
	private String weblinkStatusUnpublished = "Unpublished";
	private String textWeblinkSavedSuccessfully = "Weblink successfully saved";
	private String textWeblinkPublishedSuccessfully = "1 weblink successfully published";
	private String textWeblinkTrashedSuccessfully = "1 weblink successfully trashed";
	private String textWeblinkUnpublishedSuccessfully = "1 weblink successfully unpublished";
	private String textWeblinkArchivedSuccessfully = "1 weblink successfully archived";
	private String textWeblinkCheckinSuccessfully = "1 weblink successfully checked in";

	@FindBy(xpath = "//li[@id='toolbar-new']/a/span")
	private WebElement BTN_NEW;

	@FindBy(xpath = "//li[@id='toolbar-help']/a/span")
	private WebElement BTN_HELP;

	@FindBy(xpath = "//li[@id='toolbar-trash']/a/span")
	private WebElement BTN_TRASH;

	@FindBy(xpath = "//li[@id='toolbar-checkin']/a/span")
	private WebElement BTN_CHECKIN;

	@FindBy(xpath = "//li[@id='toolbar-archive']/a/span")
	private WebElement BTN_ARCHIVE;

	@FindBy(xpath = "//li[@id='toolbar-publish']/a/span")
	private WebElement BTN_PUBLISH;

	@FindBy(xpath = "//li[@id='toolbar-unpublish']/a/span")
	private WebElement BTN_UNPUBLISH;

	@FindBy(xpath = "//li[@id='toolbar-edit']/a/span")
	private WebElement BTN_EDIT;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul/li")
	private WebElement POPUP_MESSAGE;

	@FindBy(xpath = "//input[@id='filter_search']")
	private WebElement TXT_SEARCH;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement BTN_SEARCH;

	@FindBy(xpath = "//select[@name='filter_published']")
	private WebElement OPT_STATUS;

	@FindBy(xpath = "//select[@id='limit' and @name='limit']")
	private WebElement OPT_DISPLAY;

	@FindBy(xpath = "//table[@class='adminlist']/tbody")
	private WebElement TBL_WEBLINK;

	@FindBy(xpath = "//table[@class='adminlist']/thead/tr/th[9]/a")
	private WebElement ID_COLUMN_ICON;

	@FindBy(xpath = "//table[@class='adminlist']/thead/tr/th[5]/a")
	private WebElement ID_COLUMN_ORDERING;

	@FindBy(xpath = "//select[@name='filter_category_id']")
	private WebElement OPT_CATEGORY;

}
