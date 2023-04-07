package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class AutomationDashboardPage extends SetUp {

	By campaignSetupTab = By.cssSelector("i.ion-gear-b.text-secondary");
	By searchCampaignSetup = By.xpath("//a[@class='select2-choice select2-default']");
	By clickCampaignSetup = By.xpath("//*[@id=\"select2-drop\"]/ul/li/div");

	WebDriver driver = null;

	WebDriverWait wait;

	public AutomationDashboardPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void cickOnCampaignSetUp() {
		driver.findElement(campaignSetupTab).click();
	}

	public void searchCampaignSetup(){
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(searchCampaignSetup)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.sendKeys("Campaign Setup");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(driver.findElement(clickCampaignSetup)).click();
	}

}
