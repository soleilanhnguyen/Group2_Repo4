package contacts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.ContactsPage;
import pages.CreateNewContactPage;
import pages.HomePage;
import pages.LoginPage;
import abtract.AbstractTest;
import common.Common;
import common.Constant;

public class TM_Contact_004 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		contactName1 = Common.getUniqueString("test_contact006");
		contactName2 = Common.getUniqueString("test_contact014Order1");
		contactName3 = Common.getUniqueString("test_contact014Order2");
		contactName4 = Common.getUniqueString("test_contact016");
		textsearch= Common.getUniqueString("");
		categoryValue1 = "- Sample Data-Contact";
		statusPublishedtext ="Published";
		option ="All";
	}

	@Test(description = "TC_Contact_006:Verify user can check in an article")
	public void TC_Contact_006(String browser) {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objContactsPage = objHomePage.gotoContactPage(driver);

		objCreateNewContactPage = objContactsPage.clickOnNewbutton();

		objCreateNewContactPage.typeContactName(contactName1);

		objCreateNewContactPage.selectCategoryinDroplist(categoryValue1);
		
		objCreateNewContactPage.selectStatus(statusPublishedtext);
		
		objCreateNewContactPage.clickSavebutton();

		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());
		
		objContactsPage.searchContactName(contactName1);

		AssertTrue(objContactsPage.isContactExist(contactName1));
		
		driver.close();
		
		driver = openUrl(browser, Constant.url);

		objLoginPage = new LoginPage(driver);

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);
		
		objContactsPage = objHomePage.gotoContactPage(driver);
		
		objContactsPage.searchContactName(contactName1);
		
		objContactsPage.clickOnCheckContactPage(contactName1);
		
		objContactsPage.clickCheckinButton();
		
		AssertTrue(objContactsPage.isContactCheckinSuccessfully());

		AssertTrue(objContactsPage.isContactCheckin(contactName1));
		
		objContactsPage.deleteContact(contactName1);

	}
	
	@Test(description = "TC_Contact_014:Verify user can change the order of contacts using the Ordering column")
	public void TC_Contact_014() {
		
		objCreateNewContactPage = objContactsPage.clickOnNewbutton();
		
		objCreateNewContactPage.typeContactName(contactName2);
		
		objCreateNewContactPage.clickSaveCloseButton();
		
		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());
		
		objContactsPage.searchContactName(contactName2);
		
		objContactsPage.selectDisplayDropdown(option);

		AssertTrue(objContactsPage.isContactExist(contactName2));
		
		objCreateNewContactPage = objContactsPage.clickOnNewbutton();
		
		objCreateNewContactPage.typeContactName(contactName3);
		
		objCreateNewContactPage.clickSaveCloseButton();
		
		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());
		
		objContactsPage.searchContactName(contactName3);
		
		objContactsPage.selectDisplayDropdown(option);

		AssertTrue(objContactsPage.isContactExist(contactName3));
		
		objContactsPage.searchContactName(textsearch);
		
		objContactsPage.selectDisplayDropdown(option);
		
		objContactsPage.clickOnOrderingColumn();
		
		objContactsPage.clickOnCheckContactPage(contactName2);
		
		objContactsPage.clickDownArrowOrderingColumn(contactName2);
		
		AssertTrue(objContactsPage.isContactLast(contactName2))	;
		
		objContactsPage.deleteContact(contactName2);
		
		objContactsPage.deleteContact(contactName3);

	}

	@Test(description = "TC_Contact_016:User can change the feature property of contacts using the Featured column")
	public void TC_Contact_016() {
		
		objCreateNewContactPage = objContactsPage.clickOnNewbutton();
		
		objCreateNewContactPage.typeContactName(contactName4);
		
		objCreateNewContactPage.clickSaveCloseButton();
		
		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());
		
		objContactsPage.searchContactName(contactName4);
		
		objContactsPage.selectDisplayDropdown(option);

		AssertTrue(objContactsPage.isContactExist(contactName4));
		
		objContactsPage.clickOnCheckContactPage(contactName4);
	
		
		objContactsPage.clickOnFeatureIcon(contactName4);

		AssertTrue(objContactsPage.isCorrectFeatureIconDisplayed(
				objContactsPage.featuredState, contactName4));

		objContactsPage.clickOnFeatureIcon(contactName4);

		AssertTrue(objContactsPage.isCorrectFeatureIconDisplayed(
				objContactsPage.unfeaturedState, contactName4));
		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logOut(driver);

		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private ContactsPage objContactsPage;
	private CreateNewContactPage objCreateNewContactPage;
	private String categoryValue1;
	private String contactName1;
	private String contactName2;
	private String contactName3;
	private String contactName4;
	private String option;
	private String statusPublishedtext;
	private String textsearch;
}
