package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EditAritcalCategory extends AbstractPage {

	/**
	 * @author Ha Nguyen
	 * @param driver
	 */
	public EditAritcalCategory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Ha Nguyen
	 * @Description Click on Save as Copy button
	 */
	public void clickSaveAsCopyButton() {
		click(BTN_SAVECOPY);

	}

	/**
	 * @author Ha Nguyen
	 * @return CategoryManager Page
	 */
	public CategoryManagerPage clickCloseButton() {
		click(BTN_CLOSE);
		return new CategoryManagerPage(driver);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Verify A message : "Category successfully saved" shows.
	 */
	
	public boolean isCategorySavedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textCategorySaved))
			isShow = true;
		return isShow;
	}
	
	/**
	 * @author Ha Nguyen
	 * @Description Click on Save button
	 */
	public void clickSaveButton() {
		click(BTN_SAVE);

	}
	
	/**
	 * @author: Ha Nguyen
	 * 
	 * @return
	 * @description: check the current window is Edit Artical Category page or not
	 */
	public boolean isEditArticalCategoryDisplayed() {
		try {
			boolean bHeader = HEADER_PAGE.getText().contains(headerPage);
			return bHeader;

		} catch (Exception e) {
			return false;
		}

	}

	
	/**
	 * @author Ha Nguyen
	 * @Description: Click Save and Close button
	 */
	public CategoryManagerPage clickSaveCloseButton() {
		click(BTN_SAVECLOSE);
		return new CategoryManagerPage(driver);
	}

	WebDriver driver;

	@FindBy(xpath = "//div[@id='toolbar-box']/div/div[2]/h2")
	private WebElement HEADER_PAGE;

	@FindBy(xpath = "//li[@id='toolbar-apply']/a")
	private WebElement BTN_SAVE;

	@FindBy(xpath = "//li[@id='toolbar-save']/a")
	private WebElement BTN_SAVECLOSE;

	@FindBy(xpath = "//li[@id='toolbar-save-copy']/a/span")
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

	private String headerPage = "Category Manager: Edit An Articles Category";
	private String textCategorySaved ="Category successfully saved";
}
