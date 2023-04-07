package crm.selldo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.SetUp;

public class PushToSalesPage extends SetUp {

	By SelectTeamDropdown = By.xpath("//span[text()='Select team']");
	By SelectTeamTextField = By.xpath(".//*[@id='select2-drop']/div/input");
	By TeamFromDropdown = By.xpath(".//*[@id='select2-drop']/ul/li/div");
	By SelectUserDropdown = By.xpath("//span[text()='Select sales']");
	By SelectUserTextField = By.xpath(".//*[@id='select2-drop']/div/input");
	By UserFromDropdown = By.xpath("//div[@class='select2-result-label']");
	By PushButton = By.xpath("//button[text()='Push to Sales']");

	WebDriver driver = null;

	public PushToSalesPage(WebDriver driver) {

		this.driver = driver;
	}

	// Selecting Team of user to which lead is to be pushed
	public void selectTeam(String teamName) throws InterruptedException {
		driver.findElement(SelectTeamDropdown).click();
		// Clicking on Team dropdown
		System.out.println("a");
		Thread.sleep(3000);
		driver.findElement(SelectTeamTextField).sendKeys(teamName);// Typing
																	// team name
		System.out.println("b");
		driver.findElement(TeamFromDropdown).click();// Selecting Team from
														// dropdown
		System.out.println("c");
	}

	// Selecting User to which lead is to be pushed
	public void selectUser(String userName) throws InterruptedException {
		driver.findElement(SelectUserDropdown).click();// Clicking on User
														// dropdown
		System.out.println("d");
		driver.findElement(SelectUserTextField).sendKeys(userName);// Typing
																	// team name
		System.out.println("e");
		Thread.sleep(2000);
		driver.findElement(UserFromDropdown).click();// Selecting User from
														// dropdown
		System.out.println("f");
	}

	// Clicking on Push button
	public void clickOnPushButton() {
		driver.findElement(PushButton).click();
		;
	}
}
