package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactsPage extends AbstractPage {
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Create Constructor "/"
	 */
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click on New Icon button.
	 */

	public CreateNewContactPage clickOnNewbutton() {
		click(BTN_NEW);
		return new CreateNewContactPage(driver);
	}

	
	/**
	 * 
	 * @author Ha Nguyen
	 * @param contactNameValue
	 * @description: Delete a Contact
	 */

	public ContactsPage deleteContact(String contactNameValue){
		
		searchContactName(contactNameValue);
		clickOnCheckContactPage(contactNameValue);
		click(BTN_DELETE);
		return new ContactsPage(driver);
		
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Verify A message : "Contact successfully saved" shows.
	 */
	
	public boolean isMsgContactSavedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textContactSavedSuccessfully))
			isShow = true;
		return isShow;
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: VP: The "1 contact archived" message is displayed
	 */
	
	public boolean isMsgContactArchivedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textArchiveSuccessfully))
			isShow = true;
		return isShow;
	}

	
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: The "1 contact successfully published" message is displayed
	 */
	
	public boolean isMsgContactPublishedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textPublishSuccessfully))
			isShow = true;
		return isShow;
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: VP: The "1 contact successfully unpublished" message is displayed
	 */
	
	public boolean isMsgContactUnpublishedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textUnpublishSuccessfully))
			isShow = true;
		return isShow;
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: VP:  The "1 contact successfully trashed" message is displayed
	 */
	
	public boolean isMsgContactTrashedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textTrashSuccessfully))
			isShow = true;
		return isShow;
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @param contactNameValue
	 * @description: Verify Contact Exist in Contact table
	 */
	
	public boolean isContactExist(String contactNameValue) {
		try {
			WebElement element = findElementByXPath(driver, initialContactString, contactNameValue);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @param contactNameValue
	 * @description: Check on the recently added Contact's checkbox"
	 */
	public void clickOnCheckContactPage(String contactNameValue) {
		WebElement webElement = findElementByXPath(driver,
				initialCheckBoxContact, contactNameValue);
		click(webElement);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click on Edit button
	 */

	public CreateNewContactPage clickEditButton() {
		click(BTN_EDIT);
		return new CreateNewContactPage(driver);
	}

	/**
	 * @author Ha Nguyen
	 * @param contactNameValue
	 * @description type the Contact into the search text box, then pressing
	 *              Search button
	 */
	public void searchContactName(String contactNameValue) {

		type(TXT_SEARCH, contactNameValue);
		click(BTN_SEARCH);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @param button
	 * @description: click on a button on top right toolbar
	 */
	
	public void clickButtonOnTopRightToolbar(String button) {
		if (button != "options") {
			driver.findElement(
					By.xpath(beginningButtonOnTopRightBar + button
							+ endingButtonOnTopRightBar)).click();
		} else
			// else: use for options button
			driver.findElement(By.xpath("toolbar-popup-options")).click();
	}

	/**
	 * @author Ha Nguyen
	 * @description: Check the status Published or Unpublished
	 * @param contactNameValue
	 * @param status
	 */
	public void verifyContactIsPublishedOrNot(String contactNameValue,
			String contactStatusOfTable) {
		// get webElement of span which is besides Contact
		WebElement webElement = driver.findElement(By
				.xpath(beginningStatusxOfContact + contactNameValue
						+ endingStatusOfContact));
		String text = webElement.getAttribute("innerHTML");
		// check the text with "Published" or "Unpublished"
		Assert.assertTrue(text.equals(contactStatusOfTable));
	}

	
	/**
	 * @author Ha Nguyen
	 * @param option
	 * @description Select an option in Option dropdownlist
	 */
	public void selectOptionFromStatusDropdown(String option) {
		selectOptionFromDropdownList(OPT_STATUS, option);
	}

	/**
	 * @author Ha Nguyen create 07/06/2015
	 * @param option
	 * @description Select an option in Category dropdownlist
	 */
	public void selectOptionFromCategoryDropdown(String option) {
		selectOptionFromDropdownList(OPT_CATEGORY, option);
	}

	private WebDriver driver;
	
	private String textContactSavedSuccessfully = "Contact successfully saved";
	private String textTrashSuccessfully ="1 contact successfully trashed";
	private String textPublishSuccessfully = "1 contact successfully published";
	private String textUnpublishSuccessfully ="1 contact successfully unpublished";
	private String textArchiveSuccessfully ="1 contact successfully archived";
	private String initialContactString = "//a[contains(text(),'%s')]";
	private String initialCheckBoxContact = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	private String beginningButtonOnTopRightBar = "//li[@id='toolbar-";
	private String endingButtonOnTopRightBar = "']/a/span";
	private String beginningStatusxOfContact = "//a[contains(text(),'";
	private String endingStatusOfContact = "')]/../following-sibling::td/a/span/span";

	@FindBy(xpath = "//li[@id='toolbar-new']/a/span")
	private WebElement BTN_NEW;

	@FindBy(xpath = "//li[@id='toolbar-edit']/a/span")
	private WebElement BTN_EDIT;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul/li")
	private WebElement POPUP_MESSAGE;

	@FindBy(xpath = "//input[@id='filter_search']")
	private WebElement TXT_SEARCH;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement BTN_SEARCH;
	
	@FindBy(xpath = ".//*[@id='toolbar-trash']/a/span")
	WebElement BTN_DELETE;
	
	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[1]")
	WebElement OPT_STATUS;
	
	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[2]")
	WebElement OPT_CATEGORY;
}
