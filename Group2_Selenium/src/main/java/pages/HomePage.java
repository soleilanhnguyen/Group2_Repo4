package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
	/**
	 * @description the contructor of Home page
	 * @param driver
	 */
	public HomePage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Dung Pham
	 * @param driver
	 * @return BannerClientsPage
	 * @description goto Banner Client Page
	 */
	public BannerClientsPage gotoBannerClient(WebDriver driver) {

		selectMenuItem(bannerclientPath, driver);
		return new BannerClientsPage(driver);
	}

	/**
	 * @author Anh Nguyen
	 * @param driver
	 * @return
	 */
	public WeblinksPage gotoWebLink(WebDriver driver) {

		selectMenuItem(weblinkPath, driver);

		return new WeblinksPage(driver);
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public ArticlePage gotoArticlePage(WebDriver driver) {

		selectMenuItem(articlePath, driver);

		return new ArticlePage(driver);
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public BannersPage gotoBannerPage(WebDriver driver) {

		selectMenuItem(bannerPath, driver);

		return new BannersPage(driver);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Go to Category Manager Page "/"
	 */

	public CategoryManagerPage gotoCategoryManagerPage(WebDriver driver) {

		selectMenuItem(categorymanagertPath, driver);

		return new CategoryManagerPage(driver);
	}

	/**
	 * 
	 * @author Ha Nguyen
	 * @description: Go to Contact Page "/"
	 */

	public ContactsPage gotoContactPage(WebDriver driver) {

		selectMenuItem(contactPath, driver);

		return new ContactsPage(driver);
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public CategoryBanner gotoCategoryBannerPage(WebDriver driver) {

		selectMenuItem(categorybannerPath, driver);

		return new CategoryBanner(driver);
	}

	private String bannerclientPath = "Components/Banners/Clients";
	private String weblinkPath = "Components/Weblinks/Links";
	private String articlePath = "Content/Article Manager";
	private String bannerPath = "Components/Banners/Banners";
	private String categorybannerPath = "Components/Banners/CategoriesBanner";
	private String contactPath = "Components/Contact/Contact";
	private String categorymanagertPath = "Content/Category Manager";

	@FindBy(xpath = "//a[contains(text(),'Components')]")
	private WebElement MNU_COMPONENTS;

	@FindBy(xpath = "//a[contains(text(),'Weblinks')]")
	private WebElement MNU_COMPONENTS_WEBLINKS;

	@FindBy(xpath = "//a[contains(text(),'Weblinks')]")
	private WebElement MNU_COMPONENTS_WEBLINKS_LINKS;

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	private WebElement MNU_COMPONENTS_CONTACTS;

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	private WebElement MNU_COMPONENTS_CONTACTS_CONTACTS;

}
