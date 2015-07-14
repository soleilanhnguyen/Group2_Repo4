package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BannerClientsPage extends AbstractPage {

	/**
	 * @author Dung Pham
	 * @param driver
	 */
	public BannerClientsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham
	 * @return
	 */
	public WebDriver getBannerClientsPageDriver() {
		return this.driver;
	}

	/**
	 * @author DungPham 07/12/2015
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

	private WebDriver driver;

	@FindBy(xpath = "//li[@id='toolbar-new']/a/span")
	private WebElement BTN_NEW;

	@FindBy(xpath = "//dl[@id='system-message']/dd/ul")
	private WebElement POPUP_MESSAGE;

	@FindBy(xpath = "//input[@id='filter_search']")
	private WebElement TXT_SEARCH;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement BTN_SEARCH;

	@FindBy(xpath = "//*[@id='filter-bar']/div[2]/select[1]")
	private WebElement OPT_STATUS;

	private String initialStringBannerClient = "//a[contains(text(),'%s')]";
	private String initialStringButtonOnTopRightBar = "//li[@id='toolbar-%s']/a/span";
	private String textBannerClientSavedSuccessfully = "Client successfully saved";
	private String initialCheckBoxClient = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";
	private String initialStringStatusOfClient = "//a[contains(text(),'%s')]/../following-sibling::td/a/span/span";

}
