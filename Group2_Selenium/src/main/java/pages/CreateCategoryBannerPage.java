package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCategoryBannerPage extends AbstractPage {
	public CreateCategoryBannerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

public CategoryBanner createNewCategoryBanner(String title){
	type(TXT_TITLE, title);
	click(BTN_SAVEANDCLOSE);
	return new CategoryBanner(driver);
}
	
	
	private WebDriver driver;
	@FindBy(xpath = ".//*[@id='jform_title']")
	WebElement TXT_TITLE;
	@FindBy(xpath = ".//*[@id='toolbar-save']/a")
	WebElement BTN_SAVEANDCLOSE;
}
