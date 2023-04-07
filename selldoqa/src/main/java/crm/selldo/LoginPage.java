package crm.selldo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.SetUp;

public class LoginPage extends SetUp{
	
	By Email = By.id("user_email");
	By Password = By.id("user_password");
	By SignIn = By.xpath("//*[@type='submit']");

	WebDriver driver = null;

	public LoginPage(WebDriver driver) {

		this.driver = driver;// Calling browser
	}

	public void login(String myusername, String mypassword) {
		driver.findElement(Email).sendKeys(myusername);// Taking email
		System.out.println("Taking email");
		driver.findElement(Password).sendKeys(mypassword);// Taking password
		System.out.println("Taking password");
		driver.findElement(SignIn).click();// Clicking on Sign in button
		System.out.println("Clicking on Sign In button");
	}

}
