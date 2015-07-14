package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditBannerPage extends AbstractPage {
	public EditBannerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isMessageSaveBannerDisplayed(String messageText) {
		waitForControl(driver, MESSAGE_SUCCESS, timeout);
		String message = MESSAGE_SUCCESS.getText();
		boolean isShow = false;
		if (message.equals(messageText))
			isShow = true;
		return isShow;
	}
	
	public void editBannerName(String editName){
		TXT_NAME.clear();
		type(TXT_NAME, editName);
		
	}
	
	public BannersPage createBannerBySaveAndClose() {
		click(BTN_SAVEANDCLOSE);
		return new BannersPage(driver);
	}
	
	
	
	private WebDriver driver;
	public String successfullySaveBanner = "Banner successfully saved";
	
	@FindBy(xpath = ".//*[@id='system-message']/dd/ul/li")
	WebElement MESSAGE_SUCCESS;

	@FindBy(xpath = ".//*[@id='jform_name']")
	WebElement TXT_NAME;
	
	@FindBy(xpath = ".//*[@id='toolbar-save']/a")
	WebElement BTN_SAVEANDCLOSE;
}
