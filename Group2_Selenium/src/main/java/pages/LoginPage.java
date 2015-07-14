package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends AbstractPage{
	
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

		if (!lblLogin.equals(LBL_PAGETITLE.getText())) {
			throw new IllegalStateException("This is not the login page");
		}
	}

	// Type username
	//Author: Anh Nguyen
	public void typeUsername(String username) {

		type(TXT_USERNAME, username);

	}

	// Type password
	//Author: Anh Nguyen
	public void typePassword(String password) {

		type(TXT_PASSWORD, password);
	}

	// Click login button
	//Author: Anh Nguyen
	public void clickLoginButton() {
		click(BTN_LOGIN);

	}

	// Login to Joomla
	//Author: Anh Nguyen
	public HomePage login(String username, String password) {
		typeUsername(username);
		typePassword(password);
		clickLoginButton();
		return new HomePage(driver);
	}

	private WebDriver driver;
	private String lblLogin = "Joomla! Administration Login";

	@FindBy(xpath = ".//*[@id='element-box']/div/h1")
	private WebElement LBL_PAGETITLE;

	@FindBy(xpath = "//input[@id='mod-login-username']")
	private WebElement TXT_USERNAME;

	@FindBy(xpath = "//input[@id='mod-login-password']")
	private WebElement TXT_PASSWORD;

	@FindBy(xpath = "//a[contains(text(),'Log in')]")
	private WebElement BTN_LOGIN;
}
