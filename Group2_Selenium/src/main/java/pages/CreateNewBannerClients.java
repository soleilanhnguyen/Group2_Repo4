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

	@FindBy(xpath = "//li[@id='toolbar-apply']/a")
	private WebElement BTN_SAVE;

	@FindBy(xpath = "//select[@id='jform_state']")
	private WebElement OPT_STATUS;
}
