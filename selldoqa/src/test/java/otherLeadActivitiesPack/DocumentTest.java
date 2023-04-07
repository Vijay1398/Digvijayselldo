package otherLeadActivitiesPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import adminPages.CreateDocumentsPage;
import adminPages.DocumentsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class DocumentTest extends SetUp {

	final static Logger logger = Logger.getLogger(DocumentTest.class);

	@BeforeTest

	public void sales_presalesLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		LoginPage login = new LoginPage(driver);

		logger.info("Logging in to client page.......");

		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		login.login(p.getProperty("superadmin_name") + p.getProperty("superadmin_email"), p.getProperty("password"));

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLogin.clientLogin(p.getProperty("client_name"));

	}

	@Test

	public void searchingFileTest() throws Exception {
		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("DocumentTest");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Documents Icon.......");
		adminDashboardPage.clickOnDocument();

		CreateDocumentsPage createDocumentPage = new CreateDocumentsPage(driver);

		// Create Folder
		getExtTest().log(Status.INFO, "Creating new folder.......");
		createDocumentPage.createNewFolder(p.getProperty("folderName"));

		getExtTest().log(Status.INFO, "Opening folder.......");
		createDocumentPage.openFolder(p.getProperty("folderName"));

		DocumentsPage documentPage = new DocumentsPage(driver);
		String fileName = p.getProperty("fileName");

		// To Upload File
		getExtTest().log(Status.INFO, "Uploading New file.......");
		documentPage.upload();
		
		Thread.sleep(2000);

		SoftAssert assertion = new SoftAssert();

		// Delete File
		getExtTest().log(Status.INFO, "Deleting File.......");
		documentPage.deleteFile(fileName);

		getExtTest().log(Status.INFO, "Validating Back to Folder Button.......");
		documentPage.backToFolder();

		String expectedText = p.getProperty("expectedText");
		Assert.assertEquals(driver.findElement(By.cssSelector("#new_folder_form input[value=\"Create New Folder\"]"))
				.getAttribute("value").trim(), expectedText, "Back to Folder Not working");
		assertion.assertAll();

	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		logger.info("browser closed.......");
		driver.close();

	}

}
