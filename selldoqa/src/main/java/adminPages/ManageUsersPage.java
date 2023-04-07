package adminPages;

import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import utility.SetUp;

public class ManageUsersPage extends SetUp {

	By newUserButton = By.xpath("//a[text()='New User']");
	public By funnelIcon = By.xpath("//i[@class='ion-funnel']");
	By crossIcon = By.xpath("//div[@id=\"s2id_search_params_status\"]/a/abbr");
	By searchInputField = By.id("search_params_search_string");
	By applyButton = By.id("btn-apply-users");
	By clearAll = By.id("link-users-clear-all");
	By actionBar = By.xpath("//th[text()='Actions']/following::i[1]");
	By edit = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");
	
	//Locale locale = new Locale(langauge; "en-IND");
	//Faker faker = new Faker(locale);
	
	/*//Generate 
	Faker faker = new Faker();
    public String firstName= faker.firstName();
    public String lastName= faker.lastName();
    public String phoneNumber = faker.phoneNumber();
    */

	WebDriver driver = null;
	WebDriverWait wait;

	public ManageUsersPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewUserButton() {
		driver.findElement(newUserButton).click();
	}

	/*public void searchUser(CharSequence[] phone_SearchObj) {
		driver.findElement(funnelIcon).click();
		driver.findElement(searchInputField).sendKeys(phone_SearchObj);
		driver.findElement(applyButton).click();
	}*/

	
	public void searchUser(String emailID) {
	driver.findElement(funnelIcon).click();
	driver.findElement(crossIcon).click();
	driver.findElement(searchInputField).sendKeys(emailID);
	driver.findElement(applyButton).click();
}
	public void resettingFilter() {
		driver.findElement(funnelIcon).click();
		driver.findElement(clearAll).click();
	}

	public void clickOnActionBar() {
		driver.findElement(actionBar).click();
	}

	public void selectEdit() {
		driver.findElement(actionBar).click();
		driver.findElement(edit).click();
	}
}
