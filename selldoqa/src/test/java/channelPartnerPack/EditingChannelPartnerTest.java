package channelPartnerPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.EditPartnerFormPage;
import adminPages.PartnersPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class EditingChannelPartnerTest extends SetUp {

	final static Logger logger = Logger.getLogger(EditingChannelPartnerTest.class);

	// Description:

	@BeforeTest

	public void sales_presalesLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		LoginPage login = new LoginPage(driver);

		logger.info("Logging in to client page.......");
		login.login(property.getProperty("superadmin_name") + property.getProperty("superadmin_email"),
				property.getProperty("password"));

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLogin.clientLogin(property.getProperty("Other_client_name"));

	}

	@Test

	public void editingChannelPartnerTest() throws Exception {

		test = extent.createTest("editingChannelPartnerTest");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SettingsPage settingsPage = new SettingsPage(driver);

		PartnersPage PartnersPage = new PartnersPage(driver);

		EditPartnerFormPage editPartnerFormPage = new EditPartnerFormPage(driver);

		test.log(Status.INFO, "Clicking on Partners tab.......");
		settingsPage.clickOnPartnersTab();

		test.log(Status.INFO, "Clicking on Manage Partners tab.......");
		settingsPage.clickOnManagePartnersTab();

		test.log(Status.INFO, "Selecting Edit from action bar.......");
		PartnersPage.selectEdit();

		Thread.sleep(2000);

		test.log(Status.INFO, "Changing Channel Partner name.......");
		editPartnerFormPage.changePartnerName(property.getProperty("ChannelPartnerName_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing date of joining from calender......");
		editPartnerFormPage.selectDateOfJoining();

		test.log(Status.INFO, "Changing Source of recruitment.......");
		editPartnerFormPage
				.changeSourceOfRecruitment(property.getProperty("SourceOfRecruitment_editingChannelPartnerTest"));

		test.log(Status.INFO, "Clicking on Contact Details tab.......");
		editPartnerFormPage.clickOnContactDetailsTab();

		test.log(Status.INFO, "Changing Salutation from dropdown.......");
		editPartnerFormPage.changeSalutation();

		test.log(Status.INFO, "Changing First name of partner.......");
		editPartnerFormPage.changeFirstName(property.getProperty("FirstName_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing Last name of partner.......");
		editPartnerFormPage.changeLastName(property.getProperty("LastName_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing Phone number.......");
		editPartnerFormPage.changePhone(" " + property.getProperty("Phone_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing Email.......");
		editPartnerFormPage.changeEmail(property.getProperty("Email_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing Alternate phone number.......");
		editPartnerFormPage.changeAlternatePhone(property.getProperty("AlternatePhone_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing Alternate email......");
		editPartnerFormPage.changeAlternateEmail(property.getProperty("AlternateEmail_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing Designation......");
		editPartnerFormPage.changeDesignation(property.getProperty("Designation_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing PAN number.......");
		editPartnerFormPage.changePanNumber(property.getProperty("PanNumber_editingChannelPartnerTest"));

		test.log(Status.INFO, "Clicking on Specialization tab.......");
		editPartnerFormPage.clickOnSpecialization();

		Thread.sleep(2000);

		test.log(Status.INFO, "Changing Channel Partner type......");
		editPartnerFormPage.changeChannelPartnerType();

		test.log(Status.INFO, "Changing Rera number.......");
		editPartnerFormPage.changeReraNumber();

		test.log(Status.INFO, "Changing Property type.......");
		editPartnerFormPage.changePropertyType();

		test.log(Status.INFO, "Changing Minimum Budget.......");
		editPartnerFormPage.changeMinBudget(property.getProperty("MinBudget_editingChannelPartnerTest"));

		test.log(Status.INFO, "Changing Maximum Budget.......");
		editPartnerFormPage.changeMaxBudget(property.getProperty("MaxBudget_editingChannelPartnerTest"));

		test.log(Status.INFO, "Clicking on Save button.....");
		editPartnerFormPage.clickOnSaveButton();

		test.log(Status.INFO, "Capturing Channel Partner name appeared at the top of the list......");
		String channelPartnerNameAfterSearch = driver
				.findElement(By.xpath("//th[text()='Channel Partner']/following::td[1]")).getAttribute("innerHTML");
		System.out.println(channelPartnerNameAfterSearch);
	}

}
