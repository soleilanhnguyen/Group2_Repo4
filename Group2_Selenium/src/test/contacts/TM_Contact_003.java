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

public class TM_Contact_003 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		contactName = Common.getUniqueString("test_contact1");
		contactName1 = Common.getUniqueString("test_contact2");
		categoryValue = "- Sample Data-Contact";
		statusPublishedtext = "Published";
		optCategory = "Sample Data-Contact";
		optStatus = "Published";
	}

	@Test(description = "TC_Contact_010:User can search for contacts using the filter dropdown lists")
	public void TC_Contacta_010() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objContactsPage = objHomePage.gotoContactPage(driver);

		objCreateNewContactPage = objContactsPage.clickOnNewbutton();

		objCreateNewContactPage.typeContactName(contactName);

		objCreateNewContactPage.selectCategoryinDroplist(categoryValue);

		objCreateNewContactPage.selectStatus(statusPublishedtext);

		objCreateNewContactPage.clickSaveCloseButton();

		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());

		objContactsPage.selectOptionFromCategoryDropdown(optCategory);

		objContactsPage.selectOptionFromStatusDropdown(optStatus);

		objContactsPage.searchContactName(contactName);

		AssertTrue(objContactsPage.isContactExist(contactName));

		objContactsPage.deleteContact(contactName);
	}

	@Test(description = "TC_Contact_009:User can search for contacts using the filter text field")
	public void TC_Contact_009() {

		objCreateNewContactPage = objContactsPage.clickOnNewbutton();

		objCreateNewContactPage.typeContactName(contactName1);

		objCreateNewContactPage.selectCategoryinDroplist(categoryValue);

		objCreateNewContactPage.clickSaveCloseButton();

		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());

		objContactsPage.searchContactName(contactName1);

		AssertTrue(objContactsPage.isContactExist(contactName1));

		objContactsPage.deleteContact(contactName1);

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
	private String categoryValue;
	private String contactName;
	private String statusPublishedtext;
	private String optStatus;
	private String optCategory;
	private String contactName1;

}
