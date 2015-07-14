package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewContactPage extends AbstractPage{
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Create Constructor "/"
	 */
	
	public CreateNewContactPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @param typeContactName
	 * @description: Type Contact Name into Contact Textbox.
	 */
	
	public void typeContactName(String typeContactName){
		type(TXT_NAME, typeContactName);
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @paramenter: categoryValue
	 * @description: Select a Category in Droplist "/"
	 */
	
	public void selectCategoryinDroplist(String categoryValue) {
		if (categoryValue != null)
			selectDropDownListItemByText(DROPLIST_CATEGORY, categoryValue);

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
	 * @description: Click Save and Close button => Return to Contact Page
	 */
	public ContactsPage clickSaveCloseButton(){
		click(BTN_SAVE_CLOSE);
		return new ContactsPage(driver);
	}
	
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@id='jform_name']")
	private WebElement TXT_NAME;

	
	@FindBy(xpath = "//li[@id='toolbar-save']/a/span")
	private WebElement BTN_SAVE_CLOSE;
	
	@FindBy(xpath = "//select[@id='jform_catid']")
	private WebElement DROPLIST_CATEGORY;
	
	
	@FindBy(xpath = "//select[@id='jform_published']")
	WebElement DROPLIST_STATUS;
	
 
}