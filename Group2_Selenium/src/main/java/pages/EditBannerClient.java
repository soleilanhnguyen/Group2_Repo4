package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EditBannerClient extends AbstractPage {

	/**
	 * @author Dung Pham
	 * @param driver
	 * @description the contructor of EditBannerClient page
	 */
	public EditBannerClient(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description click Save & Copy button
	 */
	public void clickSaveCopyButton() {
		click(BTN_SAVECOPY);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return BannerClientsPage
	 * @description click Close button
	 */
	public BannerClientsPage clickCloseButton() {
		click(BTN_CLOSE);

		return new BannerClientsPage(driver);

	}

	/**
	 * @author Dung Pham 07/12/2015
	 * @description check the text banner client saved displaying on pop-up
	 */
	public boolean isTextBannerClientSavedSuccessfullyDisplayed() {
		try {
			return POPUP_MESSAGE.getText().contains(
					textBannerClientSavedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham
	 * @description click Save button
	 */
	public void clickSaveButton() {
		click(BTN_SAVE);

	}

	/**
	 * @author Dung Pham
	 * 
	 */
	public void verifyBannerClientSavedSuccessfully() {
		Assert.assertTrue(POPUP_MESSAGE.getText().contains(
				textBannerClientSavedSuccessfully));

	}

	/**
	 * @author Dung Pham 07/10
	 * @return
	 * @description: check the current window is Edit Banner Client page or not
	 */
	public boolean isEditBannerPageDisplayed() {
		try {
			boolean bHeader = HEADER_PAGE.getText().contains(headerPage);

			return bHeader;

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 07/10
	 * @param clientNameEdited
	 * @param contactNameEdited
	 * @param contactEmailEdited
	 * @description Edit an exist banner client
	 */
	public void editABannerClients(String clientNameEdited,
			String contactNameEdited, String contactEmailEdited,
			String contactStatusEdited) {
		type(TXT_CLIENTNAME, clientNameEdited);
		type(TXT_CONTACTNAME, contactNameEdited);
		type(TXT_CONTACTEMAIL, contactEmailEdited);
		selectOptionFromDropdownList(OPT_STATUS, contactStatusEdited);

	}

	/**
	 * @author Dung Pham 07/10/2015
	 * @return
	 * @description click Save & Close button
	 */
	public BannerClientsPage clickSaveCloseButton() {
		click(BTN_SAVECLOSE);
		return new BannerClientsPage(driver);
	}

	WebDriver driver;

	@FindBy(xpath = "//div[@id='toolbar-box']/div/div[2]/h2")
	private WebElement HEADER_PAGE;

	@FindBy(xpath = "//li[@id='toolbar-apply']/a")
	private WebElement BTN_SAVE;

	@FindBy(xpath = "//li[@id='toolbar-save']/a")
	private WebElement BTN_SAVECLOSE;

	@FindBy(xpath = "//li[@id='toolbar-save-copy']/a")
	private WebElement BTN_SAVECOPY;

	@FindBy(xpath = "//li[@id='toolbar-cancel']/a")
	private WebElement BTN_CLOSE;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul")
	private WebElement POPUP_MESSAGE;

	@FindBy(xpath = "//input[@id='jform_name']")
	private WebElement TXT_CLIENTNAME;

	@FindBy(xpath = "//input[@id='jform_contact']")
	private WebElement TXT_CONTACTNAME;

	@FindBy(xpath = "//input[@id='jform_email']")
	private WebElement TXT_CONTACTEMAIL;

	@FindBy(xpath = "//select[@id='jform_state']")
	private WebElement OPT_STATUS;

	private String textBannerClientSavedSuccessfully = "Client successfully saved";
	private String headerPage = "Banner Manager: Edit Client";
}
