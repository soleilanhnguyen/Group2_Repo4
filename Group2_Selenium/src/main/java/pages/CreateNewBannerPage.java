package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewBannerPage extends AbstractPage {
	public CreateNewBannerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void typeBannerName(String bannername) {
		if (bannername != null)
			type(TXT_NAME, bannername);
	}

	public void selectCategory(String category) {
		String bannercategory = "- " + category;
		if (category != null)
			selectDropDownListItemByText(DDL_CATEGORY, bannercategory);
	}

	public void selectClient(String client) {
		if (client != null)
			selectDropDownListItemByText(DDL_CLIENT, client);
	}

	public void selectStatus(String status) {
		if (status != null)
			selectDropDownListItemByText(DDL_STATUS, status);
	}

	public BannersPage createBannerBySaveAndClose(Banner banner) {

		enterValueIntoCreateNewBannerForm(banner);
		click(BTN_SAVEANDCLOSE);
		return new BannersPage(driver);
	}
	
	public CreateNewBannerPage createBannerBySaveAndNew(Banner banner) {

		enterValueIntoCreateNewBannerForm(banner);
		click(BTN_SAVEANDNEW);
		return new CreateNewBannerPage(driver);
	}

	public EditBannerPage createBannerBySave(Banner banner) {
		enterValueIntoCreateNewBannerForm(banner);
		click(BTN_SAVE);
		return new EditBannerPage(driver);
	}
	
	public EditBannerPage createBannerBySaveAsCopy(Banner banner) {
		enterValueIntoCreateNewBannerForm(banner);
		click(BTN_SAVEASCOPY);
		return new EditBannerPage(driver);
	}

	public void enterValueIntoCreateNewBannerForm(Banner banner) {
		String bannerName = banner.getBannerName();
		String bannerCategory = banner.getBannerCategory();
		String bannerClient = banner.getBannerClient();
		String bannerStatus = banner.getBannerStatus();

		typeBannerName(bannerName);
		selectCategory(bannerCategory);
		selectClient(bannerClient);
		selectStatus(bannerStatus);

	}
	
	public boolean isMessageBannerDisplayed(String messageText) {
		waitForControl(driver, MESSAGE_SUCCESS, timeout);
		String message = MESSAGE_SUCCESS.getText();
		boolean isShow = false;
		if (message.equals(messageText))
			isShow = true;
		return isShow;
	}

	public void openHelp() {
		click(BTN_HELP);

	}
	
	public void cancel()
	{
		click(BTN_CANCEL);
		
	}
	public WebDriver getNewBannerPageDriver() {
		return this.driver;
	}
	
	private WebDriver driver;

	@FindBy(xpath = ".//*[@id='jform_name']")
	WebElement TXT_NAME;
	
	@FindBy(xpath = ".//*[@id='system-message']/dd/ul/li")
	WebElement MESSAGE_SUCCESS;

	@FindBy(xpath = ".//*[@id='jform_catid']")
	WebElement DDL_CATEGORY;

	@FindBy(xpath = ".//*[@id='jform_cid']")
	WebElement DDL_CLIENT;

	@FindBy(xpath = ".//*[@id='toolbar-save']/a")
	WebElement BTN_SAVEANDCLOSE;
	
	@FindBy(xpath = ".//*[@id='toolbar-save-new']/a")
	WebElement BTN_SAVEANDNEW;
	
	@FindBy(xpath = ".//*[@id='toolbar-save-copy']/a")
	WebElement BTN_SAVEASCOPY;

	@FindBy(xpath = ".//*[@id='toolbar-apply']/a")
	WebElement BTN_SAVE;

	@FindBy(xpath = ".//*[@id='jform_state']")
	WebElement DDL_STATUS;
	
	@FindBy(xpath = ".//*[@id='toolbar-help']/a/span")
	WebElement BTN_HELP;
	
	@FindBy(xpath = ".//*[@id='toolbar-cancel']/a/span")
	WebElement BTN_CANCEL;
}
