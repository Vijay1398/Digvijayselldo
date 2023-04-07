package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class EditTeamFormPage extends SetUp {

	By nameInputField = By.xpath("//input[@id='team_name']");
	By locationInputField = By.xpath("//input[@id='team_location']");
	By teamAccessibleInputField = By.id("s2id_autogen9");
	By teamAccessible_dd = By.xpath("//div[@class='select2-result-label']");
	By saveButton = By.xpath("//input[@name='commit'][@value='Save']");

	WebDriver driver = null;
	WebDriverWait wait;

	public EditTeamFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void changeTeamName(String teamname) {
		driver.findElement(nameInputField).clear();
		driver.findElement(nameInputField).sendKeys(teamname);
	}

	public void changeLocation(String location) {
		driver.findElement(locationInputField).clear();
		driver.findElement(locationInputField).sendKeys(location);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
}
