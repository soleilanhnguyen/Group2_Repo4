package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateWebLinkPage extends AbstractPage {

	/**
	 * @author Dung Pham 06/28/2015
	 * @param driver
	 * 
	 */
	public CreateWebLinkPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham
	 * @param weblinkTitle
	 * @param weblinkURL
	 * @param weblinkStatus
	 */
	public void typeANewWeblink(String weblinkTitle, String weblinkURL,
			String weblinkStatus, String weblinkAlias) {
		type(TXT_TITLE, weblinkTitle);
		type(TXT_URL, weblinkURL);
		type(TXT_ALIAS, weblinkAlias);
		selectOptionFromStatusDropdownList(weblinkStatus);
	}

	/**
	 * author Dung Pham 07/13/2015
	 */
	public void clickSaveAsCopyButton() {
		click(BTN_SAVEASCOPY);
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
	 * @author Dung Pham 07/04/2015
	 * @description verify the text "Weblink successfully saved" display after
	 *              creating new weblink successfully
	 */
	public void verifyWebLinkSavedSuccessfully() {
		Assert.assertTrue(POPUP_MESSAGE.getText().contains(
				textWeblinkSavedSuccessfully));

	}

	/**
	 * author Dung Pham 07/04/2015
	 */
	public void clickSaveButton() {
		click(BTN_SAVE);
	}

	/**
	 * author Dung Pham 07/04/2015
	 */
	public WeblinksPage clickCloseButton() {
		click(BTN_CLOSE);
		return new WeblinksPage(driver);
	}

	/**
	 * @author Dung Pham
	 * @param weblinkTitle
	 * @param weblinkURL
	 * @param weblinkStatus
	 */
	public void typeANewWeblink(String weblinkTitle, String weblinkURL,
			String weblinkStatus) {
		type(TXT_TITLE, weblinkTitle);
		type(TXT_URL, weblinkURL);
		selectOptionFromStatusDropdownList(weblinkStatus);
	}

	/**
	 * @author Dung Pham
	 */
	public void selectOptionFromStatusDropdownList(String options) {
		selectOptionFromDropdownList(OPT_STATUS, options);

	}

	/**
	 * @author Dung Pham 06/29/2015
	 * @return WeblinksPage
	 */
	public WeblinksPage clickSaveCloseButton() {
		click(BTN_SAVE_CLOSE);
		return new WeblinksPage(driver);
	}

	private WebDriver driver;

	@FindBy(xpath = "//input[@id='jform_title']")
	private WebElement TXT_TITLE;

	@FindBy(xpath = "//input[@id='jform_url']")
	private WebElement TXT_URL;

	@FindBy(xpath = "//li[@id='toolbar-save']/a/span")
	private WebElement BTN_SAVE_CLOSE;

	@FindBy(xpath = "//li[@id='toolbar-apply']/a/span")
	private WebElement BTN_SAVE;

	@FindBy(xpath = "//li[@id='toolbar-save-copy']/a/span")
	private WebElement BTN_SAVEASCOPY;

	@FindBy(xpath = "//li[@id='toolbar-cancel']/a/span")
	private WebElement BTN_CLOSE;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul/li")
	private WebElement POPUP_MESSAGE;

	@FindBy(xpath = "//select[@id='jform_state']")
	private WebElement OPT_STATUS;

	@FindBy(xpath = "//input[@id='jform_alias']")
	private WebElement TXT_ALIAS;

	private String textWeblinkSavedSuccessfully = "Weblink successfully saved";
}
