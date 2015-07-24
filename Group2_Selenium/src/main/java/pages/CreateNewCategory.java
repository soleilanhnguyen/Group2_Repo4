package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Common;

public class CreateNewCategory extends AbstractPage{
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Create Constructor "/"
	 */
	
	public CreateNewCategory(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @Parameter titleCategory
	 * @description: Type Title Category"/"
	 */
	
	public void typeCategoryName(String titleCategory){
		type(TXT_CATEGORYTITLE, titleCategory);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click Save and Close button "/"
	 */
	public CategoryManagerPage clickSaveButton(){
		click(BTN_SAVE);
		return new CategoryManagerPage(driver);
	}
		
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click Save As button "/"
	 */
	public CreateNewCategory clickSaveAsButton(){
		click(BTN_SAVE_AS);
		return new CreateNewCategory(driver);
	}
	
	
	public String getColorOfTitle()
	{
		String hex = Common.convertRgbaToHex(getControlCss(TXT_CATEGORYTITLE, "color"));
		return hex;		
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click Save and Close button "/"
	 */
	public CategoryManagerPage clickSaveCloseButton(){
		click(BTN_SAVE_CLOSE);
		return new CategoryManagerPage(driver);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click Save and New button "/"
	 */
	public CreateNewCategory clickSaveNewButton(){
		click(BTN_SAVE_NEW);
		return new CreateNewCategory(driver);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click Cancel button "/"
	 */
	public CategoryManagerPage clickCancelButton(){
		click(BTN_CANCEL);
		return new CategoryManagerPage(driver);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click Help button "/"
	 */
	public CreateNewCategory clickHelpButton(){
		click(BTN_HELP);
		return new CreateNewCategory(driver);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @paramenter: status
	 * @description: Select a Staus in Droplist "/"
	 */
	
	public void selectStatus(String status) {
		if (status != null)
			selectDropDownListItemByText(DROPLIST_STATUS, status);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Close warning pop up
	 */
	
	public void closePopup()
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		driver.switchTo().defaultContent();
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @paramenter: option
	 * @description: Select a Access option in Droplist "/"
	 */
	
	public void selectAccess(String option) {
		if (option != null)
			selectDropDownListItemByText(DROPLIST_ACCESS, option);

	}
	
	/**
	 * 
	 * @author Ha Nguyen 7/9
	 * @paramenter: option
	 * @description: Select a Language option in Droplist "/"
	 */
	
	public void selectLanguage(String option) {
		if (option != null)
			selectDropDownListItemByText(DROPLIST_LANGUAGE, option);
	}
	
	public WebDriver getNewCategroyPageDriver() {
		return this.driver;
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
	
	
	
	private WebDriver driver;
	
	private String textCategorySaved = "Category successfully saved";
	
	@FindBy(xpath = "//input[@id='jform_title']")
	private WebElement TXT_CATEGORYTITLE;

	
	@FindBy(xpath = "//li[@id='toolbar-save']/a/span")
	private WebElement BTN_SAVE_CLOSE;

	@FindBy(xpath = "//li[@id='toolbar-apply']/a/span")
	private WebElement BTN_SAVE;
	
	@FindBy(xpath = "//li[@id='toolbar-save-new']/a/span")
	private WebElement BTN_SAVE_NEW;
	
	@FindBy(xpath = "//li[@id='toolbar-cancel']/a/span")
	private WebElement BTN_CANCEL;
	
	@FindBy(xpath = "//li[@id='toolbar-save']/a/span")
	private WebElement BTN_SAVE_AS;
	
	@FindBy(xpath = "//select[@id='jform_published']")
	private WebElement DROPLIST_STATUS;
	
	@FindBy(xpath = "//select[@id='jform_access']")
	private WebElement DROPLIST_ACCESS;
	
	@FindBy(xpath = "//select[@id='jform_language']")
	private WebElement DROPLIST_LANGUAGE;

	@FindBy(xpath = "//li[@id='toolbar-help']/a/span")
	private WebElement BTN_HELP;
	
	@FindBy(xpath = "//dl[@id='system-message']/dd/ul/li")
	private WebElement POPUP_MESSAGE;

}