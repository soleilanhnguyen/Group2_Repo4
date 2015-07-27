package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

			WebElement element = findElementByXPath(driver,
					initialStringButtonOnTopRightBar, button);
			click(element);

		} else
			// else: use for options button
			driver.findElement(By.xpath("toolbar-popup-options")).click();
	}
	/**
	 * @author Ha Nguyen
	 * @param contactNameValue, contactStatusOfTable
	 * @Description: Verify Contact Publish or not
	 */
	public boolean isContactPublishedOrNot(String contactNameValue,
			String contactStatusOfTable) {
		// Get webElement of span which is besides Contact Name
		try {
			WebElement element = findElementByXPath(driver,
					initialStringStatusOfContact, contactNameValue);

			String text = element.getAttribute("innerHTML");
			// Check the text with "Published" or "Unpublished"
			return text.equals(contactStatusOfTable);

		} catch (Exception e) {
			return false;
		}
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
	
	
	/**
	 * @author HaNguyen 7/15
	 * @return
	 */
	public WebDriver getContactsPageDriver() {
		return this.driver;
	}
	
	
	/**
	 * @author Ha Nguyen
	 * @param contactNameValue
	 * @Description: Click on status icon in table of contact
	 */
	public void clickOnStatusContact(String contactNameValue) {
		WebElement webElement = findElementByXPath(driver,
				initialStatusContact, contactNameValue);
		click(webElement);
	}
	
	/**
	 * @author Ha Nguyen
	 * @param contactNameValue
	 * @Description: Click on feature icon in table of Contact
	 */
	public void clickOnFeatureContact(String contactNameValue) {
		WebElement webElement = findElementByXPath(driver,
				initialFeatureContact, contactNameValue);
		click(webElement);
	}
	
	/**
	 * @author Ha Nguyen
	 * @param contactNameValue, contactFeatureOfTable
	 * @Description: Verify Contact Feature or not
	 */
	public boolean isContactFeatureOrNot(String contactNameValue,
			String contactFeatureOfTable) {
		// Get webElement of span which is besides Contact Name
		try {
			WebElement element = findElementByXPath(driver,
					initialStringFeatureOfContact, contactNameValue);

			String text = element.getAttribute("innerHTML");
			// Check the text with "Published" or "Unpublished"
			return text.equals(contactFeatureOfTable);

		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @author Ha Nguyen
	 * @Description: Click on Checkin button
	 */
	public void clickCheckinButton() {
		click(BTN_CHECKIN);
	}
	
	
	/**
	 * @author Ha Nguyen
	 * @return boolean
	 * @description check the text of popup to make sure weblink is checked in
	 */
	public boolean isContactCheckinSuccessfully() {

		try {
			return POPUP_MESSAGE.getText().contains(
					textContactCheckinSuccessfully);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @author Ha Nguyen
	 * @param ContactName
	 * @return boolean
	 * @description check the lock icon does not display beside Contact
	 */
	public boolean isContactCheckin(String ContactName) {

		try {

			WebElement element = findElementByXPath(driver, initialLinkCheckIn,
					ContactName);
			element.isDisplayed();
			return false;
		} catch (NoSuchElementException ex) {
			return true;
		}

	}
	
	/**
	 * @author Ha Nguyen
	 * @param option
	 * @description select a option from display dropdown
	 */
	public void selectDisplayDropdown(String option) {
		selectOptionFromDropdownList(OPT_DISPLAY, option);
	}
	
	
	/**
	 * @author Ha Nguyen
	 * @description Click on Ordering Column
	 */
	public void clickOnOrderingColumn() {
		click(ID_COLUMN_ORDERING);
	}

	/**
	 * @author Ha Nguyen
	 * @param contactName
	 * @description click the down arrow in ordering column
	 */
	public void clickDownArrowOrderingColumn(String contactName) {

		WebElement webElement = findElementByXPath(driver,
				initialDownArrowOrderingColumn, contactName);
		click(webElement);
	}
	
	
	/**
	 * @author Ha Nguyen
	 * @param contactName
	 * @return boolean
	 * @description check the contact is the last of table
	 */
	public boolean isContactLast(String contactName) {

		List<WebElement> rows = TBL_CONTACT.findElements(By.tagName("tr"));

		int rowsInt = rows.size();

		try {

			return TBL_CONTACT
					.findElement(By.xpath("tr[" + rowsInt + "]/td[2]"))
					.getText().contains(contactName);

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Ha Nguyen
	 * @param status, contactName
	 * @return boolean
	 * @description check the contact is the last of table
	 */
		
	public boolean isCorrectFeatureIconDisplayed(String status, String contactName) {

	WebElement element = findElementByXPath(driver, featureIcon, contactName);
	String temp = element.getAttribute("alt");
	boolean isShow = false;
	if (status.equals(temp))
		isShow = true;
	return isShow;

}
	
	/**
	 * @author Ha Nguyen
	 * @param contactName
	 * @description check the contact is the last of table
	 */
		
	
	public void clickOnFeatureIcon(String contactName) {
		clickOnCheckContactPage(contactName);
		WebElement element = findElementByXPath(driver, linkFeatureIcon, contactName);
		click(element);
	}
	
	
	private WebDriver driver;
	
	private String textContactSavedSuccessfully = "Contact successfully saved";
	private String textTrashSuccessfully ="1 contact successfully trashed";
	private String textPublishSuccessfully = "1 contact successfully published";
	private String textUnpublishSuccessfully ="1 contact successfully unpublished";
	private String textArchiveSuccessfully ="1 contact successfully archived";
	private String textContactCheckinSuccessfully ="1 contact successfully checked in";
	private String initialContactString = "//a[contains(text(),'%s')]";
	private String initialCheckBoxContact = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	private String initialStringButtonOnTopRightBar = "//li[@id='toolbar-%s']/a/span";
	private String initialStringStatusOfContact = "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";
	private String initialStatusContact = "//a[contains(text(),'%s')]/../following-sibling::td/a/span";
	private String initialStringFeatureOfContact =  "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";
	private String initialFeatureContact = "//a[contains(text(),'%s')]/../following-sibling::td/a/span";
	private String initialLinkCheckIn="//td[a[contains(text(),'%s')]]/../td/a/span[@class='state checkedout']";
	private String initialDownArrowOrderingColumn ="//td[a[contains(text(),'%s')]]/../td//span[@class='state downarrow']";
	private String featureIcon = "//a[contains(text(),'%s')]/../following-sibling::td[3]/a/img";
	private String linkFeatureIcon = "//a[contains(text(),'%s')]/../following-sibling::td[3]/a";
	public String featuredState = "Featured";
	public String unfeaturedState = "Unfeatured contact";
	
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
	
	@FindBy(xpath = "//li[@id='toolbar-checkin']/a/span")
	private WebElement BTN_CHECKIN;
	
	@FindBy(xpath = "//select[@id='limit' and @name='limit']")
	private WebElement OPT_DISPLAY;
	
	@FindBy(xpath = "//table[@class='adminlist']/thead/tr/th[7]/a")
	private WebElement ID_COLUMN_ORDERING;
	
	@FindBy(xpath = "//table[@class='adminlist']/tbody")
	private WebElement TBL_CONTACT;
}
