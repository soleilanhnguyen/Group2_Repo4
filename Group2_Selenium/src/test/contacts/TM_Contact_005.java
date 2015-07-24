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

public class TM_Contact_005 extends AbstractTest {

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		driver = openUrl(browser, Constant.url);
		objLoginPage = new LoginPage(driver);
		contactName1 = Common.getUniqueString("test_contact1");
		categoryValue1 = "- Sample Data-Contact";
		statusPublishedtext = "Published";
		featureValue ="Yes";
		contactFeatureOfTable1 ="Unfeatured";
		contactFeatureOfTable2 ="Featured";
	}

	@Test(description = "TC_Contact_016:User can change the feature property of contacts using the Featured column")
	public void TC_Contact_016() {

		objHomePage = objLoginPage.login(Constant.adminUsername,
				Constant.adminPassword);

		objContactsPage = objHomePage.gotoContactPage(driver);

		objCreateNewContactPage = objContactsPage.clickOnNewbutton();

		objCreateNewContactPage.typeContactName(contactName1);

		objCreateNewContactPage.selectCategoryinDroplist(categoryValue1);
		
		objCreateNewContactPage.selectStatus(statusPublishedtext);
		
		objCreateNewContactPage.selectFeature(featureValue);

		objCreateNewContactPage.clickSaveCloseButton();

		AssertTrue(objContactsPage.isMsgContactSavedSucessfyllyDisplayed());

		objContactsPage.searchContactName(contactName1);

		AssertTrue(objContactsPage.isContactExist(contactName1));

		objContactsPage.clickOnCheckContactPage(contactName1);
		
		objContactsPage.clickOnFeatureContact(contactName1);
		
		AssertTrue(objContactsPage.isContactFeatureOrNot(contactName1, contactFeatureOfTable1));
		
		objContactsPage.clickOnFeatureContact(contactName1);
		
		AssertTrue(objContactsPage.isContactFeatureOrNot(contactName1, contactFeatureOfTable2));
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
	private String categoryValue1;
	private String contactName1;
	private String statusPublishedtext;
	private String featureValue;
	private String contactFeatureOfTable1;
	private String contactFeatureOfTable2;
}
