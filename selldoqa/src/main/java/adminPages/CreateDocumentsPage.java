package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.SetUp;

public class CreateDocumentsPage extends SetUp {
	
	By folderNameField = By.cssSelector("#new_folder_form input.form-control");
	By createFolderButton = By.cssSelector("#new_folder_form input[value=\"Create New Folder\"]");
	
	
	WebDriver driver = null;
	WebDriverWait wait;
	
	public CreateDocumentsPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}
	
	
	public void createNewFolder(String folderName) {
		driver.findElement(folderNameField).sendKeys(folderName);
		driver.findElement(createFolderButton).click();
	}
	
	public void openFolder(String folderName) {
		
		driver.findElement(By.cssSelector("#" + folderName +"_folder > div.folder-name")).click();
		
		}

}
