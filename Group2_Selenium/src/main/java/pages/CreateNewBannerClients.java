package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewBannerClients extends AbstractPage {

	/**
	 * @author Dung Pham
	 * @param driver
	 */
	public CreateNewBannerClients(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham 7/20/2015
	 * @return String
	 * @description get the color of txt client name
	 */
	public String getColorClientName() {
		return TXT_CLIENTNAME.getCssValue("color");
	}

	/**
	 * @author Dung Pham 7/20/2015
	 * @return String
	 * @description get the color of email field
	 */
	public String getColorEmail() {
		return TXT_CONTACTEMAIL.getCssValue("color");
	}

	/**
	 * @author Dung Pham 7/20/2015
	 * @description click Help button
	 */
	public void clickHelpButton() {
		click(BTN_HELP);

	}

	/**
	 * @author Dung Pham 7/20/2015
	 */
	public void clickSaveNewButton() {
		click(BTN_SAVENEW);

	}

	/**
	 * @author Dung Pham 7/20/2015
	 * @return boolean
	 * @description check the CreateNewBannerClientPage is displaying or not
	 */
	public boolean isCreateNewBannerClientPageDisplayed() {
		try {
			boolean bHeader = HEADER_PAGE.getText().contains(headerPage);

			return bHeader;

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/20/2015
	 * @return boolean
	 * @description check the text of popup to make sure the banner client is
	 *              saved
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
	 * @author Dung Pham 7/20/2015
	 * @return BannerClientsPage
	 */
	public BannerClientsPage clickCancelButton() {
		click(BTN_CANCEL);
		return new BannerClientsPage(driver);
	}

	/**
	 * @author Dung Pham
	 * @param clientName
	 * @param contactName
	 * @param contactEmail
	 * @param contactStatus
	 */
	public void typeANewBannerClients(String clientName, String contactName,
			String contactEmail, String contactStatus) {
		type(TXT_CLIENTNAME, clientName);
		type(TXT_CONTACTNAME, contactName);
		type(TXT_CONTACTEMAIL, contactEmail);
		selectOptionFromDropdownList(OPT_STATUS, contactStatus);
	}

	/**
	 * @author Dung Pham
	 * @return
	 */
	public BannerClientsPage clickSaveCloseButton() {
		click(BTN_SAVECLOSE);
		return new BannerClientsPage(driver);
	}

	/**
	 * @author Dung Pham
	 * @return
	 */
	public EditBannerClient clickSaveButton() {
		click(BTN_SAVE);
		return new EditBannerClient(driver);
	}

	WebDriver driver;

	@FindBy(xpath = "//input[@id='jform_name']")
	private WebElement TXT_CLIENTNAME;

	@FindBy(xpath = "//input[@id='jform_contact']")
	private WebElement TXT_CONTACTNAME;

	@FindBy(xpath = "//input[@id='jform_email']")
	private WebElement TXT_CONTACTEMAIL;

	@FindBy(xpath = "//li[@id='toolbar-save']/a")
	private WebElement BTN_SAVECLOSE;

	@FindBy(xpath = "//li[@id='toolbar-save-new']/a")
	private WebElement BTN_SAVENEW;

	@FindBy(xpath = "//li[@id='toolbar-apply']/a")
	private WebElement BTN_SAVE;

	@FindBy(xpath = "//li[@id='toolbar-help']/a")
	private WebElement BTN_HELP;

	@FindBy(xpath = "//li[@id='toolbar-cancel']/a")
	private WebElement BTN_CANCEL;

	@FindBy(xpath = "//select[@id='jform_state']")
	private WebElement OPT_STATUS;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul")
	private WebElement POPUP_MESSAGE;

	//@FindBy(xpath = "//div[@id='toolbar-box']/div/div[2]/h2")
	@FindBy(xpath = "//div[contains(@class,'pagetitle')]/h2")
	private WebElement HEADER_PAGE;

	private String textBannerClientSavedSuccessfully = "Client successfully saved";
	private String headerPage = "Banner Manager: New Client";
}
