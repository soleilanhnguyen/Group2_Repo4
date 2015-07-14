package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryBanner extends AbstractPage{
	public CategoryBanner(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CreateCategoryBannerPage selectNewbutton() {

		click(BTN_NEW);
		return new CreateCategoryBannerPage(driver);

	}
	
	/**
	 * @author Anh Nguyen
	 * @param message
	 * @description verify that message displays correctly
	 * 
	 */
	public boolean isMessageCategoryBannerDisplayed(String messageText) {
		waitForControl(driver, MESSAGE_SUCCESS, timeout);
		String message = MESSAGE_SUCCESS.getText();
		boolean isShow = false;
		if (message.equals(messageText))
			isShow = true;
		return isShow;
	}
	
	private WebDriver driver;
	public String successfullycreatecategorybanner = "Category successfully saved";
	
	@FindBy(xpath = "//li[@id='toolbar-new']/a/span")
	WebElement BTN_NEW;
	@FindBy(xpath = ".//*[@id='system-message']/dd/ul/li")
	WebElement MESSAGE_SUCCESS;
}
