package crm.selldo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utility.SetUp;

public class ReassignLeadPage extends SetUp {

	By TeamDropdown = By.xpath("//button[text()='Reassign']/preceding::span[1]");
	By TeamTextField = By.xpath(".//*[@id='select2-drop']/div/input");
	By TeamFromDropdown = By.cssSelector("#select2-drop > ul > li > div > span");
	By UserDropdown = By.xpath("//span[text()='Select User']");
	By UserTextField = By.xpath(".//*[@id='select2-drop']/div/input");
	By UserFromDropdown = By.cssSelector("#select2-drop > ul > li > div");
	By ReassignButton = By.xpath("//button[text()='Reassign']");
	By teamsList = By.cssSelector("#select2-drop > ul li div");

	WebDriver driver = null;

	public ReassignLeadPage(WebDriver driver) {

		this.driver = driver;
	}

	// Selecting Team of user to which lead is to be reassigned
	public void selectTeam(String teamName) {
		driver.findElement(TeamDropdown).click();// Clicking on Team dropdown
		driver.findElement(TeamTextField).sendKeys(teamName);// Typing team name
		driver.findElement(TeamFromDropdown).click();
	}

	// Selecting User to which lead is to be reassigned
	public void selectUser(String userName) {
		driver.findElement(UserDropdown).click();// Clicking on User dropdown
		driver.findElement(UserTextField).sendKeys(userName);// Typing User name
		driver.findElement(UserFromDropdown).click();
	}

	// Clicking on Reassign button
	public void clickOnReassignButton() {
		driver.findElement(ReassignButton).click();
	}

}
