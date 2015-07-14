package contacts;

import org.openqa.selenium.WebDriver;
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

public class TM_Contact_001 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		contactName1 = Common.getUniqueString("test_contact1");
		contactName2 = Common.getUniqueString("test_contact2");
		categoryValue1 = "- Sample Data-Contact";
		categoryValue2 = "- - Park Site";
		buttonArchive = "archive";
		optionArchive = "Archived";
	}

	@Test(description = "TC_Contact_001:User can create new contact with valid information")
	public void TC_Contact_001() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objContactsPage = objHomePage.gotoContactPage(driver);

		objCreateNewContactPage = objContactsPage.clickOnNewbutton();

		objCreateNewContactPage.typeContactName(contactName1);

		objCreateNewContactPage.selectCategoryinDroplist(categoryValue1);

		objCreateNewContactPage.clickSaveCloseButton();

		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());

		objContactsPage.searchContactName(contactName1);

		AssertTrue(objContactsPage.isContactExist(contactName1));

	}

	@Test(description = "TC_Contact_002: User can edit a contact")
	public void TC_Contact_002() {

		objContactsPage.clickOnCheckContactPage(contactName1);

		objContactsPage.clickEditButton();

		objCreateNewContactPage.typeContactName(contactName2);

		objCreateNewContactPage.selectCategoryinDroplist(categoryValue2);

		objCreateNewContactPage.clickSaveCloseButton();

		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());

		objContactsPage.searchContactName(contactName2);

		AssertTrue(objContactsPage.isContactExist(contactName2));
	}

	@Test(description = "TC_Contact_005: User can move a contact to the archive")
	public void TC_Contact_005() {

		objContactsPage.clickOnCheckContactPage(contactName2);

		objContactsPage.clickButtonOnTopRightToolbar(buttonArchive);

		AssertTrue(objContactsPage.isMsgContactArchivedSucessfyllyDisplayed());

		objContactsPage.selectOptionFromStatusDropdown(optionArchive);

		AssertTrue(objContactsPage.isContactExist(contactName2));

		objContactsPage.deleteContact(contactName2);

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

	private WebDriver driver;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private ContactsPage objContactsPage;
	private CreateNewContactPage objCreateNewContactPage;
	private String categoryValue1;
	private String categoryValue2;
	private String contactName1;
	private String contactName2;
	private String buttonArchive;
	private String optionArchive;
}
