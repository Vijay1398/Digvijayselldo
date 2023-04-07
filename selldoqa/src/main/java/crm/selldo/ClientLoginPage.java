package crm.selldo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.SetUp;

public class ClientLoginPage extends SetUp {

	By SelectAClientField = By.id("s2id_input-search-field");
	By SelectAClientFieldInput = By.xpath(".//*[@id='select2-drop']/div/input");
	By ClientName = By.xpath(".//*[@id='select2-drop']/ul/li[1]/div");
	By SearchButton = By.id("search_clients");
	By Login = By.xpath("//a[@title='Login as client']");
	By CreateClientButton = By.id("btn-add-client");
	By Logout = By.id("btn-logout");
	By Clients = By.id("link-clients-list");
	By ActionBar = By.cssSelector("i.fa.fa-ellipsis-v");
	By userAccount = By.cssSelector("i#user-account-icon");

	WebDriver driver = null;

	public ClientLoginPage(WebDriver driver) {

		this.driver = driver;// Calling Browser
	}

	public void clientLogin(String clientName) throws InterruptedException {

		// Clicking on Select a Client Field
		driver.findElement(SelectAClientField).click();
		System.out.println("Clicking on Select a Client Field");

		Thread.sleep(3000);

		// Typing Client name in Select a Client Field
		driver.findElement(SelectAClientFieldInput).sendKeys(clientName);
		System.out.println("Typing Client name in Select a Client Field");

		Thread.sleep(3000);

		// Selecting Client name from dropdown
		driver.findElement(ClientName).click();
		System.out.println("Selecting Client name from dropdown");

		Thread.sleep(3000);

		// Clicking on Search button
		driver.findElement(SearchButton).click();
		System.out.println("Clicking on Search button");

		Thread.sleep(3000);
		
		//Clicking on action bar
		//driver.findElement(ActionBar).click();

		// Clicking on Login link
		driver.findElement(Login).click();
		System.out.println("Clicking on Login link");
	}

	public void clickOnCreateClientButton() {
		driver.findElement(CreateClientButton).click();
	}

	public void clickOnClientsLink() {
		driver.findElement(Clients).click();
	}
	
	public void superAdminlogout() {
		driver.findElement(Logout).click();
	}

	public void logout() {
		driver.findElement(userAccount).click();
		driver.findElement(Logout).click();
	}
}
