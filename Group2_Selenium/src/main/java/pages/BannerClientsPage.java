package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BannerClientsPage extends AbstractPage {

	/**
	 * @author Dung Pham
	 * @param driver
	 * @description This is a contructor of page
	 */
	public BannerClientsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description Click on Check In Button
	 */
	public void clickCheckinButton() {
		click(BTN_CHECKIN);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description Check the text of Pop-up to make sure that banner is checked
	 *              in successfully
	 */
	public boolean isBannerClientCheckinSuccessfully() {

		try {
			return POPUP_MESSAGE.getText().contains(
					textBannerClientCheckinSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param clientName
	 * @return boolean
	 * @description Check the status of banner to make sure it is checked in
	 */
	public boolean isBannerClientCheckin(String clientName) {

		try {

			WebElement element = findElementByXPath(driver, initialLinkCheckIn,
					clientName);
			element.isDisplayed();
			return false;
		} catch (NoSuchElementException ex) {
			return true;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description Click on Publish button
	 */
	public void clickPublishButton() {
		click(BTN_PUBLISH);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description Check the text of popup to make sure client is published
	 *              successfully
	 */
	public boolean isBannerClientPublishedSuccessfully() {
		try {
			return POPUP_MESSAGE.getText().contains(
					textBannerClientPublishedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param clientName
	 * @return boolean
	 * @description Check the status of banner client to make sure it is
	 *              published
	 */
	public boolean isBannerClientPublished(String clientName) {
		try {
			WebElement element = findElementByXPath(driver,
					initialStringStatusOfClient, clientName);

			String text = element.getAttribute("innerHTML");

			return text.equals(statusPublished);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description Click Publish Button
	 */
	public void clickUnpublishButton() {
		click(BTN_UNPUBLISH);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description Check the text of popup to make sure banner client is
	 *              unpublished
	 */
	public boolean isBannerClientUnpublishedSuccessfully() {
		try {
			return POPUP_MESSAGE.getText().contains(
					textBannerClientUnpublishedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @param clientName
	 * @return boolean
	 * @description Check the status of banner client to make sure it is
	 *              unpublished
	 */
	public boolean isBannerClientUnpublished(String clientName) {
		try {
			WebElement element = findElementByXPath(driver,
					initialStringStatusOfClient, clientName);

			String text = element.getAttribute("innerHTML");

			return text.equals(statusUnpublished);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description Click Archive button
	 */
	public void clickArchiveButton() {
		click(BTN_ARCHIVE);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description Check the text of popup to make sure banner client is
	 *              archived
	 */
	public boolean isBannerClientArchivedSuccessfully() {
		try {
			return POPUP_MESSAGE.getText().contains(
					textBannerClientArchivedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description Click Trash button
	 */
	public void clickTrashButton() {
		click(BTN_TRASH);

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @return boolean
	 * @description check the text of pop up to make sure banner client is
	 *              trashed
	 */
	public boolean isBannerClientTrashedSuccessfully() {
		try {
			return POPUP_MESSAGE.getText().contains(
					textBannerClientTrashedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 7/19/2015
	 * @description click Help button
	 */
	public void clickHelpButton() {
		click(BTN_HELP);

	}

	/**
	 * @author Dung Pham
	 * @return WebDriver
	 */
	public WebDriver getBannerClientsPageDriver() {
		return this.driver;
	}

	/**
	 * @author DungPham 07/12/2015
	 * @return boolean
	 * @description check the text of popup to make sure banner client is saved
	 */
	public boolean isBannerClientSavedSuccessfully() {
		try {
			return POPUP_MESSAGE.getText().contains(
					textBannerClientSavedSuccessfully);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 07/12/2015
	 * @param clientName
	 * @param clientStatusOfTable
	 */
	public boolean isClientIsPublishedOrNot(String clientName,
			String clientStatusOfTable) {
		try {
			WebElement element = findElementByXPath(driver,
					initialStringStatusOfClient, clientName);

			String text = element.getAttribute("innerHTML");

			// check the text with "Published" or "Unpublished"
			return text.equals(clientStatusOfTable);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 07/12/2015
	 * @param textNeedToBeTested
	 */
	public boolean isTextDisPlayOnPopupMessage(String textNeedToBeTested) {
		try {
			return POPUP_MESSAGE.getText().contains(textNeedToBeTested);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Dung Pham 07/11/2015
	 * @param option
	 */
	public void selectOptionFromStatusDropdown(String option) {
		selectOptionFromDropdownList(OPT_STATUS, option);
	}

	/**
	 * @author Dung Pham 07/11/2015
	 * @param clientName
	 */
	public void clickOnCheckBoxWebBannerClient(String clientName) {
		WebElement webElement = findElementByXPath(driver,
				initialCheckBoxClient, clientName);
		click(webElement);

	}

	/**
	 * @author Dung Pham 07/11/2015
	 * @param button
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
	 * @author Dung Pham 07/11/2015
	 * @param textNeedToBeTested
	 */
	public void verifyTextDisPlayOnPopupMessage(String textNeedToBeTested) {
		Assert.assertTrue(POPUP_MESSAGE.getText().contains(textNeedToBeTested));
	}

	/**
	 * @author Dung Pham 07/11/2015
	 * @param clientName
	 * @param clientStatusOfTable
	 */
	public void verifyClientIsPublishedOrNot(String clientName,
			String clientStatusOfTable) {

		WebElement element = findElementByXPath(driver,
				initialStringStatusOfClient, clientName);

		String text = element.getAttribute("innerHTML");

		// check the text with "Published" or "Unpublished"
		Assert.assertTrue(text.equals(clientStatusOfTable));

	}

	/**
	 * @author Dung Pham 07/06/2015
	 * @description Create a new banner client by clicking on icon New
	 * @return object of CreateNewBannerClients
	 */
	public CreateNewBannerClients clickNewButton() {
		click(BTN_NEW);
		return new CreateNewBannerClients(driver);
	}

	/**
	 * @author DungPham 07/06/2015
	 */
	public void verifyBannerClientSavedSuccessfully() {
		Assert.assertTrue(POPUP_MESSAGE.getText().contains(
				textBannerClientSavedSuccessfully));

	}

	/**
	 * @author Dung Pham
	 * @param clientName
	 */
	public void searchBannerClient(String clientName) {
		type(TXT_SEARCH, clientName);
		click(BTN_SEARCH);
	}

	/**
	 * @author Dung Pham
	 * @param clientName
	 * @return
	 */
	public boolean isBannerClientExist(String clientName) {
		try {
			WebElement element = findElementByXPath(driver,
					initialStringBannerClient, clientName);

			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author Anh Nguyen
	 * @return
	 */
	public boolean isClientPageDisplayed() {

		try{
		String bodyText = driver.findElement(By.tagName("h2")).getText();
		bodyText.contains(labelClient);
			return true;
		}catch(NoSuchElementException ex){
			
			return false;
		}

	}

	private WebDriver driver;

	@FindBy(xpath = "//li[@id='toolbar-new']/a/span")
	private WebElement BTN_NEW;

	@FindBy(xpath = "//li[@id='toolbar-publish']/a/span")
	private WebElement BTN_PUBLISH;

	@FindBy(xpath = "//li[@id='toolbar-unpublish']/a/span")
	private WebElement BTN_UNPUBLISH;

	@FindBy(xpath = "//li[@id='toolbar-archive']/a/span")
	private WebElement BTN_ARCHIVE;

	@FindBy(xpath = "//li[@id='toolbar-trash']/a/span")
	private WebElement BTN_TRASH;

	@FindBy(xpath = "//li[@id='toolbar-help']/a/span")
	private WebElement BTN_HELP;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul")
	private WebElement POPUP_MESSAGE;

	@FindBy(xpath = "//input[@id='filter_search']")
	private WebElement TXT_SEARCH;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement BTN_SEARCH;

	@FindBy(xpath = "//select[@name='filter_state']")
	private WebElement OPT_STATUS;

	@FindBy(xpath = "//li[@id='toolbar-checkin']/a/span")
	private WebElement BTN_CHECKIN;

	private String initialStringBannerClient = "//a[contains(text(),'%s')]";
	private String initialStringButtonOnTopRightBar = "//li[@id='toolbar-%s']/a/span";
	private String initialCheckBoxClient = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	private String initialStringStatusOfClient = "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";
	private String initialLinkCheckIn = "//td[a[contains(text(),'%s')]]/../td/a/span[@class='state checkedout']";

	private String textBannerClientSavedSuccessfully = "Client successfully saved";
	private String textBannerClientPublishedSuccessfully = "1 client successfully published";
	private String textBannerClientUnpublishedSuccessfully = "1 client successfully unpublished";
	private String textBannerClientArchivedSuccessfully = "1 client successfully archived";
	private String textBannerClientTrashedSuccessfully = "1 client successfully trashed";
	private String textBannerClientCheckinSuccessfully = "1 client successfully checked in";

	private String statusPublished = "Published";
	private String statusUnpublished = "Unpublished";
	private String labelClient = "Banner Manager: Clients";
}
