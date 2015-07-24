package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CategoryManagerPage extends AbstractPage {
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Create Constructor "/"
	 */
	public CategoryManagerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Click on New Button "/"
	 */

	public CreateNewCategory clickOnNewbutton() {
		click(BTN_NEW);
		return new CreateNewCategory(driver);
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
		if (message.equals(textCategorySavedSuccessfully))
			isShow = true;
		return isShow;
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: VP: The "1 category successfully archived" message is displayed
	 */
	
	public boolean isMsgCategorytArchivedSucessfyllyDisplayed() {
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
	 * @description: The "1 category successfully published" message is displayed
	 */
	
	public boolean isMsgCategoryPublishedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textkPublishSuccessfully))
			isShow = true;
		return isShow;
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: VP: The "1 category successfully unpublished" message is displayed
	 */
	
	public boolean isMsgCategorytUnpublishedSucessfyllyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textcheckunPublishedSuccessfully))
			isShow = true;
		return isShow;
	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @description: VP:  The "1 "contact successfully trashed" message is displayed
	 */
	
	public boolean isMsgCategoryTrashedSucessfyllyDisplayed() {
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
	 * @description: VP:  The "Batch process completed successfully message" is displayed
	 */
	
	public boolean isMsgProcessCompeletedSuccessfullyDisplayed() {
		waitForControl(driver, POPUP_MESSAGE, timeout);
		String message = POPUP_MESSAGE.getText();
		boolean isShow = false;
		if (message.equals(textProcessSuccessfully))
			isShow = true;
		return isShow;
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Verify New category is created and matched with entered
	 *               data"
	 */
	public boolean isCategoryExist(String categoryNameValue){
	
		try {
			WebElement element = findElementByXPath(driver,initialCategoryString, categoryNameValue);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}
	
	/**
	 * 
	 * @author Ha Nguyen
	 * @param categoryNameValue
	 * @description: Enter categoryNameValue then click on Search button
	 *             
	 */
	
	public void searchCategoryName(String categoryNameValue) {
		type(TXT_SEARCH, categoryNameValue);
		click(BTN_SEARCH);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @param categoryNameValue
	 * @description: Check on the recently added Category checkbox"
	 */
	public void clickOnCheckCategory(String categoryNameValue) {
		WebElement webElement = findElementByXPath(driver,
				initialCheckBoxCategory, categoryNameValue);
		click(webElement);
	}
		/**
		 * 
		 * @author Ha Nguyen
		 * @description: Click on edit Button
		 */

	 public CreateNewCategory clickEditButton(){
	 click(BTN_EDIT);
	 return new CreateNewCategory(driver);
	 }

		/**
		 * 
		 * @author Ha Nguyen
		 * @param categoryValue
		 * @description: Select category then click on button Delete
		 */

		public CategoryManagerPage deleteCategory(String categoryValue){
			
			searchCategoryName(categoryValue);
			clickOnCheckCategory(categoryValue);
			click(BTN_DELETE);
			return new CategoryManagerPage(driver);
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
		 * @author Ha Nguyen 7/15
		 * @param categoryTitle, contactStatusOfTable
		 * @param status
		 */
		public boolean isCategoryPublishedOrNot(String categoryTitle,
				String contactStatusOfTable) {
			// Get webElement of span which is besides Category
			try {
				WebElement element = findElementByXPath(driver,
						initialStringStatusxOfCategory, categoryTitle);

				String text = element.getAttribute("innerHTML");
				// Check the text with "Published" or "Unpublished"
				
				return text.equals(contactStatusOfTable);

			} catch (Exception e) {
				return false;
			}
		}
	 
		/**
		 * @author Ha Nguyen 7/9
		 * @param option
		 * @description Select an option in Option dropdownlist
		 */
		public void selectOptionFromStatusDropdown(String option) {
			selectOptionFromDropdownList(OPT_STATUS, option);
		}
		
		/**
		 * @author Ha Nguyen create 07/06/2015
		 * @param option
		 * @description Select an Level in Level dropdownlist
		 */
		public void selectLevelFromLevelDropdown(String levelValue) {
			selectOptionFromDropdownList(OPT_LEVEL, levelValue);
		}
		
		
		/**
		 * 
		 * @author Ha Nguyen
		 * @description: Select Copy label
		 */

	 public CategoryManagerPage selectCopy(){
	 click(LABEL_COPY);
	 return new CategoryManagerPage(driver);
	 }
	 
		/**
		 * 
		 * @author Ha Nguyen
		 * @description: Click on button Process
		 */

	 public CategoryManagerPage clickOnbuttonProcess(){
	 click(BTN_PROCESS);
	 return new CategoryManagerPage(driver);
	 }
		
	 /**
		 * @author Ha Nguyen create 7/16
		 * @param levelValue
		 * @description Select Set Access Option in Access dropdownlist
		 */
		public void selectSetLevelAccessOption(String levelValue){
		 selectOptionFromDropdownList(OPT_SETACCESS, levelValue);
	 }
	 
		/**
		 * @author Ha Nguyen create 07/06/2015
		 * @param levelValue
		 * @description Select an Access Option in Access dropdownlist
		 */
		public void selectAccessOptionFromAccessDropdown(String levelValue) {
			selectOptionFromDropdownList(OPT_ACCESS, levelValue);
		}
		
		/**
		 * @author Ha Nguyen create 07/06/2015
		 * @param languageOption
		 * @description Select Category for Move/Copy 
		 */
		public void selectCategoryForMoveOrCopy(String categoryOption) {
			selectOptionFromDropdownList(OPT_MOVECOPY, categoryOption);
		}
		
		/**
		 * @author Ha Nguyen create 07/06/2015
		 * @param languageOption
		 * @description Select an language Option in Language dropdownlist
		 */
		public void selectLanguageOptionFromLanguageDropdown(String languageOption) {
			selectOptionFromDropdownList(OPT_LANGUAGE, languageOption);
		}
		
		/**
		 * @author HaNguyen 7/15
		 * @return
		 */
		public WebDriver getCategoryManagerPageDriver() {
			return this.driver;
		}
		
		public ArticlePage clickArticle()
		{
			click(BTN_ARTICLES);
			return new ArticlePage(driver);
		}
		
	private WebDriver driver;
	
	private String initialStringButtonOnTopRightBar ="//li[@id='toolbar-%s']/a/span";
	private String initialCategoryString = "//a[contains(text(),'%s')]";
	private String initialCheckBoxCategory = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	private String initialStringStatusxOfCategory = "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";
	private String textCategorySavedSuccessfully = "Category successfully saved";
	private String textArchiveSuccessfully = "1 category successfully archived";
	private String textTrashSuccessfully = "1 category successfully trashed";
	private String textkPublishSuccessfully = "1 category successfully published";
	private String textcheckunPublishedSuccessfully ="1 category successfully unpublished";
	private String textProcessSuccessfully ="Batch process completed successfully.";

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
	
	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[2]")
	WebElement OPT_STATUS;

	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[1]")
	WebElement OPT_LEVEL;
	
	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[3]")
	WebElement OPT_ACCESS;

	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[4]")
	WebElement OPT_LANGUAGE;
	
	@FindBy(xpath = "//select[@id='batch-category-id']")
	WebElement OPT_MOVECOPY;
	
	@FindBy(xpath = "//button[contains(text(),'Process')]")
	WebElement BTN_PROCESS;
	
	@FindBy(xpath = "//label[contains(text(),'Copy')]")
	WebElement LABEL_COPY;
	
	@FindBy(xpath = "//*[@id='batch-access']")
	WebElement OPT_SETACCESS;
	
	@FindBy(xpath="//a[text()='Articles']")
	WebElement BTN_ARTICLES;
	

}
