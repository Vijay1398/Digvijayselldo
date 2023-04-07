package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewTeamFormPage extends SetUp {

	By nameInputField = By.id("team_name");
	By locationInputField = By.id("team_location");
	By teamHierarchySpan = By.xpath("//span[text()='Select Team Hierarchy']");
	By teamHierarchyInputField = By.xpath("//*[@id='select2-drop']/div/input");
	By teamHierarchy_dd = By.xpath("//div[@class='select2-result-label']");
	By teamAccessibleInputField = By.id("s2id_autogen9");
	By teamAccessible_dd = By.xpath("//div[@class='select2-result-label']");
	By saveButton = By.xpath("//input[@name='commit'][@value='Save']");

	WebDriver driver = null;
	WebDriverWait wait;

	public NewTeamFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterTeamName(String teamname) {
		driver.findElement(nameInputField).sendKeys(teamname);
	}

	public void enterLocation(String location) {
		driver.findElement(locationInputField).sendKeys(location);
	}

	public void selectTeamHierarchy() {
		driver.findElement(teamHierarchySpan).click();
		driver.findElement(teamHierarchy_dd).click();
	}
	
	public void selectAccessibleTeams() {
		driver.findElement(teamAccessibleInputField).click();
		driver.findElement(teamAccessible_dd).click();
	}
	
	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
}
