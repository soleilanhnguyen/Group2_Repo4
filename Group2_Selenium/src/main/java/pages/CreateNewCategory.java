package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	public CategoryManagerPage clickSaveAsButton(){
		click(BTN_SAVE_AS);
		return new CategoryManagerPage(driver);
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
	public CategoryManagerPage clickSaveNewButton(){
		click(BTN_SAVE_NEW);
		return new CategoryManagerPage(driver);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click Cancle button "/"
	 */
	public CategoryManagerPage clickCancelButton(){
		click(BTN_CANCEL);
		return new CategoryManagerPage(driver);
	}
	
	
	/**
	 * 
	 * @author Ha Nguyen 7/8
	 * @paramenter: status
	 * @description: Select a Staus in Droplist "/"
	 */
	
	public void selectStatus(String status) {
		if (status != null)
			selectDropDownListItemByText(DROPLIST_STATUS, status);

	}
	
	/**
	 * 
	 * @author Ha Nguyen 7/8
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
	
	
	private WebDriver driver;
	
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
	
	@FindBy(xpath = "//li[@id='toolbar-save-copy']/a/span")
	private WebElement BTN_SAVE_AS;
	
	@FindBy(xpath = "//select[@id='jform_published']")
	private WebElement DROPLIST_STATUS;
	
	@FindBy(xpath = "//select[@id='jform_access']")
	private WebElement DROPLIST_ACCESS;
	
	@FindBy(xpath = "//select[@id='jform_language']")
	private WebElement DROPLIST_LANGUAGE;


}