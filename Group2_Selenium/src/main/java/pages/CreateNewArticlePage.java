package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewArticlePage extends AbstractPage {
	public CreateNewArticlePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @description type to Title field
	 * @param title
	 * @author anh.nguyen
	 */
	public void typeTitle(String title) {
		if (title != null)
			type(TXT_TITLE, title);
	}

	/**
	 * @description select a category
	 * @param category
	 * @author anh.nguyen
	 */
	public void selectCategory(String category) {
		if (category != null)
			selectDropDownListItemByText(DDL_CATEGORY, category);
	}

	/**
	 * @description type value into ArticleText
	 * @param articleText
	 * @author anh.nguyen
	 */
	public void typeArticleText(String articleText) {
		if (articleText != null) {
			driver.switchTo().frame(iFrame);
			type(TXT_ARTICLETEXT, articleText);
			driver.switchTo().defaultContent();
		}
	}

	/**
	 * @description type Alias value
	 * @param alias
	 * @author anh.nguyen
	 */
	public void typeAlias(String alias) {
		if (alias != null)
			type(TXT_ALIAS, alias);
	}

	/**
	 * @description select status
	 * @param status
	 * @author anh.nguyen
	 */
	public void selectStatus(String status) {
		if (status != null)
			selectDropDownListItemByText(DDL_STATUS, status);

	}

	/**
	 * @description select access
	 * @param access
	 * @author anh.nguyen
	 */
	public void selectAccess(String access) {
		if (access != null)
			selectDropDownListItemByValue(DDL_FEATURE, access);
	}

	/**
	 * @description select featured
	 * @param featured
	 * @author anh.nguyen
	 */
	public void selectFeature(String featured) {
		if (featured != null)
			selectDropDownListItemByValue(DDL_FEATURE, featured);
	}

	/**
	 * @description select language
	 * @param language
	 * @author anh.nguyen
	 */
	public void selectLanguage(String language) {
		if (language != null)
			selectLanguage(language);
	}

	/**
	 * @description enter value into create article form
	 * @param article
	 * @author anh.nguyen
	 */
	public void enterValueInToCreateArticleForm(Article article) {
		String title = article.getTitle();
		String category = article.getCategory();
		String articleText = article.getArticleText();
		String alias = article.getAlias();
		String status = article.getStatus();
		String access = article.getAccess();
		String featured = article.getFeature();
		String language = article.getLanguage();

		typeTitle(title);
		typeAlias(alias);
		selectCategory(category);
		selectStatus(status);
		selectAccess(access);
		selectFeature(featured);
		selectLanguage(language);
		typeArticleText(articleText);

	}

	
	/**
	 * @description Click on Save and Close button
	 * @return ArticlePage
	 * @author Ha Nguyen
	 */
	public ArticlePage clickSaveAndClose (){
			click(BTN_SAVEANDCLOSE);
		return new ArticlePage(driver); 
	}

	/**
	 * @description Click Save and New
	 * @return CreateNewArticlePage
	 * @author Ha Nguyen
	 */
	public CreateNewArticlePage clickSaveAndNew (){
		click(BTN_SAVEANDNEW);
		return new CreateNewArticlePage(driver);
	}
	
	/**
	 * @description create a new article by Save and Close button
	 * @param article
	 * @return ArticlePage
	 * @author anh.nguyen
	 */
	public ArticlePage createArticleBySaveAndClose(Article article) {
		enterValueInToCreateArticleForm(article);
		click(BTN_SAVEANDCLOSE);
		return new ArticlePage(driver);
	}

	/**
	 * @description create a new article by Save and Close button
	 * @param article
	 * @return ArticlePage
	 * @author anh.nguyen 7/17
	 */
	public ArticlePage createArticleBySave(Article article) {
		enterValueInToCreateArticleForm(article);
		click(BTN_SAVE);
		return new ArticlePage(driver);
	}
	
	
	
	private WebDriver driver;

	@FindBy(xpath = ".//*[@id='jform_title']")
	WebElement TXT_TITLE;

	@FindBy(xpath = ".//*[@id='jform_alias']")
	WebElement TXT_ALIAS;

	@FindBy(xpath = ".//*[@id='jform_catid']")
	WebElement DDL_CATEGORY;

	@FindBy(xpath = ".//*[@id='jform_state']")
	WebElement DDL_STATUS;

	@FindBy(xpath = ".//*[@id='jform_access']")
	WebElement DDL_ACCESS;

	@FindBy(xpath = ".//*[@id='jform_featured']")
	WebElement DDL_FEATURE;

	@FindBy(xpath = ".//*[@id='jform_language']")
	WebElement DDL_LANGUAGE;

	@FindBy(id = "tinymce")
	WebElement TXT_ARTICLETEXT;

	@FindBy(xpath = ".//*[@id='toolbar-save']/a")
	WebElement BTN_SAVEANDCLOSE;

	@FindBy(tagName = "iframe")
	WebElement iFrame;
	
	@FindBy(xpath = ".//*[@id='toolbar-apply']/a")
	WebElement BTN_SAVE;
	
	@FindBy(xpath = "//li[@id='toolbar-save-new']/a/span")
	WebElement BTN_SAVEANDNEW;
	
}
