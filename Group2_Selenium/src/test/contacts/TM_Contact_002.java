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
		buttonunPublished ="unpublish";
		contactStatusOfTable1 = "Published";
		contactStatusOfTable2 = "Unpublished";
		buttonTrash="trash";
		optionTrash ="Trashed";
	}

	@Test(description = "TC_Contact_003:Verify user can publish an unpublished contact")
	public void TC_Contact_003() {

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
		
		objContactsPage.verifyContactIsPublishedOrNot(contactName, contactStatusOfTable1);
		
		AssertTrue(objContactsPage.isMsgContactPublishedSucessfyllyDisplayed());
	}
	
	@Test(description = "TC_Contact_004: User can unpublish a published contact")
	public void TC_Contact_004() {

		objContactsPage.clickOnCheckContactPage(contactName);
				
		objContactsPage.clickButtonOnTopRightToolbar(buttonunPublished);
			
		objContactsPage.verifyContactIsPublishedOrNot(contactName, contactStatusOfTable2);
			
		AssertTrue(objContactsPage.isMsgContactUnpublishedSucessfyllyDisplayed());
	}

	
	@Test(description = "TC_Contact_007: User can move a contact to trash section")
	public void TC_Contact_007() {
		
		objContactsPage.clickOnCheckContactPage(contactName);
		
		objContactsPage.clickButtonOnTopRightToolbar(buttonTrash);
		
		AssertTrue(objContactsPage.isMsgContactTrashedSucessfyllyDisplayed());
		
		objContactsPage.selectOptionFromStatusDropdown(optionTrash);
		
		AssertTrue(objContactsPage.isContactExist(contactName));

	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
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
	private String buttonTrash;
	private String optionTrash;
}
