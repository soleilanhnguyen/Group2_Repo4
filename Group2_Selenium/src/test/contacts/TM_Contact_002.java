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

public class TM_Contact_002 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		contactName = Common.getUniqueString("test_contact1");
		categoryValue = "- Sample Data-Contact";
		statusUnpublishedtext = "Unpublished";
		buttonPublished = "publish";
		buttonunPublished = "unpublish";
		contactStatusOfTable1 = "Published";
		contactStatusOfTable2 = "Unpublished";
	}

	@Test(description = "TC_Contact_003:Verify user can publish an unpublished contact")
	public void TC_JOOMLA_Contact_003() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objContactsPage = objHomePage.gotoContactPage(driver);

		objCreateNewContactPage = objContactsPage.clickOnNewbutton();

		objCreateNewContactPage.typeContactName(contactName);

		objCreateNewContactPage.selectCategoryinDroplist(categoryValue);

		objCreateNewContactPage.selectStatus(statusUnpublishedtext);

		objCreateNewContactPage.clickSaveCloseButton();

		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());

		objContactsPage.searchContactName(contactName);

		AssertTrue(objContactsPage.isContactExist(contactName));

		objContactsPage.clickOnCheckContactPage(contactName);

		objContactsPage.clickButtonOnTopRightToolbar(buttonPublished);

		AssertTrue(objContactsPage.isMsgContactPublishedSucessfyllyDisplayed());

		AssertTrue(objContactsPage.isContactPublishedOrNot(contactName,
				contactStatusOfTable1));

	}

	@Test(description = "TC_Contact_004: User can unpublish a published contact")
	public void TC_JOOMLA_Contact_004() {

		objContactsPage.clickOnCheckContactPage(contactName);

		objContactsPage.clickButtonOnTopRightToolbar(buttonunPublished);

		AssertTrue(objContactsPage
				.isMsgContactUnpublishedSucessfyllyDisplayed());

		AssertTrue(objContactsPage.isContactPublishedOrNot(contactName,
				contactStatusOfTable2));
	}

	@Test(description = "TC_Contact_015:User can change the status of contacts using the Status column")
	public void TC_JOOMLA_Contact_015() {

		objContactsPage.clickOnCheckContactPage(contactName);

		objContactsPage.clickOnStatusContact(contactName);

		AssertTrue(objContactsPage.isContactPublishedOrNot(contactName,
				contactStatusOfTable1));

		AssertTrue(objContactsPage.isMsgContactPublishedSucessfyllyDisplayed());
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
	private String buttonPublished;
	private String buttonunPublished;
	private String contactStatusOfTable1;
	private String contactStatusOfTable2;
	private String statusUnpublishedtext;
}
