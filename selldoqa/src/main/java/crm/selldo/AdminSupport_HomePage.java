package crm.selldo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utility.SetUp;

public class AdminSupport_HomePage extends SetUp {

	By Users = By.xpath("//a[@href='/client/users']");
	By Teams = By.xpath("//a[@href='/client/teams']");
	By SettingIcon = By.xpath("//*[@class='fa fa-cog fa-lg']");
	By SignIn = By.xpath("//a[text()='Sign in']");
	By LoginAs = By.xpath("//h4[text()='Login as']/following::span[1]");
	By LoginAsInputField = By.xpath(".//*[@id='select2-drop']/div/input");
	By UserFromDropdown = By.xpath("//div[@class='select2-result-label']");
	By LoginButton = By.xpath("//a[text()='Login']");
	By Logout = By.xpath("//a[text()='Logout']");
	By userAccountIcon = By.cssSelector("i#user-account-icon");

	WebDriver driver = null;

	public AdminSupport_HomePage(WebDriver driver) {

	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;
	}

	// Validating that admin/support manager logged in successfully by comparing
	// page title after login
	public void verifyingClientLogin() {
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.getTitle(), "Sell.Do - Market Smarter. Sell Faster.", "Title Not Matched");
		System.out.println("Completed verification");
		assertion.assertAll();
	}

/*	// Logging out of the application
	public void loggingOut() throws InterruptedException {
		driver.findElement(SettingIcon).click();// Clicking on Setting icon at
												// the upper right corner
		driver.findElement(Logout).click();// Clicking on logout from the
											// dropdown
	}*/
	
	public void loggingOut() throws InterruptedException {
	driver.findElement(userAccountIcon).click();
	driver.findElement(Logout).click();
	
	}

	// Clicking on Users link at the header
	public void clickOnUsers() {
		driver.findElement(Users).click();
	}

	// Signing in as a User
	public void signInAsUser(String userName) throws InterruptedException {
		driver.findElement(SettingIcon).click();// Clicking on Setting icon at
												// the upper right corner
		driver.findElement(SignIn).click();// Clicking on Sign in field
		driver.findElement(LoginAs).click();
		driver.findElement(LoginAsInputField).sendKeys(userName);
		driver.findElement(UserFromDropdown).click();
		driver.findElement(LoginButton).click();
	}

	public void clickOnTeams() {
		driver.findElement(Teams).click();
	}
}