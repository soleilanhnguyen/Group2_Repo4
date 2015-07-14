package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WeblinksPage extends AbstractPage {

	/**
	 * @author Dung Pham 06/28/2015
	 * @param driver
	 */
	public WeblinksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham 07/13/2015
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
			// TODO: handle exception
			return false;
		}

	}

	/**
	 * @author Dung Pham 07/12/2015
	 * @param weblinkTitle
	 * @param status
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
	public boolean isWebLinkSavedSuccessfully() {

		try {
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
	 */
	public boolean isWeblinkExist(String weblinkTitle) {
		try {
			WebElement element = findElementByXPath(driver,
					initialWeblinkString, weblinkTitle);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham
	 * @param weblinkTitle
	 * @return
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
	 */
	public WebDriver getWeblinksPageDriver() {
		return this.driver;
	}

	/**
	 * @author Dung Pham create 07/02/2015
	 * @param option
	 */
	public void selectOptionFromStatusDropdown(String option) {
		selectOptionFromDropdownList(OPT_STATUS, option);
	}

	/**
	 * @author Dung Pham create 07/06/2015
	 * @param option
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
		type(TXT_SEARCH, weblinkTitle);
		click(BTN_SEARCH);
	}

	/**
	 * @author Dung Pham 04/07/2915
	 * @description click on a button on top right toolbar. Use to click New,
	 *              Edit,.. button
	 * @param String
	 *            button
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
	 */
	public CreateWebLinkPage clickNewButton() {
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

	@FindBy(xpath = "//li[@id='toolbar-new']/a/span")
	private WebElement BTN_NEW;

	@FindBy(xpath = "//li[@id='toolbar-edit']/a/span")
	private WebElement BTN_EDIT;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul/li")
	private WebElement POPUP_MESSAGE;

	@FindBy(xpath = "//input[@id='filter_search']")
	private WebElement TXT_SEARCH;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement BTN_SEARCH;

	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[1]")
	private WebElement OPT_STATUS;

	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[2]")
	private WebElement OPT_CATEGORY;

	private String textWeblinkSavedSuccessfully = "Weblink successfully saved";
}
