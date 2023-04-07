package adminPages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class DocumentsPage extends SetUp {

	By backButton = By.cssSelector("#back-button");
	By createFolderButton = By.cssSelector("#new_folder_form input[value=\"Create New Folder\"]");
	By searchFileField = By.cssSelector("input[placeholder=\"Search by file name\"]");
	By searchButton = By.cssSelector("a.btn.btn-primary.asset_search_term_btn");
	By uploadButton = By.cssSelector("div.btn.btn-outline-primary.fileinput-button");
	//By uploadButton = By.xpath("//span[text()='Upload']");
	

	WebDriver driver = null;
	WebDriverWait wait;

	public DocumentsPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void backToFolder() {

		driver.findElement(backButton).click();
	}

	public void searchFile(String fileName) throws Exception {

		driver.findElement(searchFileField).sendKeys(fileName);
		driver.findElement(searchButton).click();
		Thread.sleep(1000);
	}

	public void uploadFile() {

		driver.findElement(uploadButton).click();
	}

	public void deleteFile(String fileName) throws InterruptedException {

		Thread.sleep(3000);
		Actions actions = new Actions(driver);

		WebElement fileDoc = driver.findElement(By.cssSelector("div.asset-icon.asset-icon-image"));
		actions.moveToElement(fileDoc);
		actions.build().perform();
		driver.findElement(By.cssSelector("a[title=\"Delete\"]")).click();
		driver.switchTo().alert().accept();

	}

	public void upload() throws Exception {
	
     	String claimZIP = System.getProperty("user.dir") + "/DataFile/" + "google-new-logo.png";
		WebElement browse = driver.findElement(By.xpath("//input[@type='file']"));
		// pass the path of the file to be uploaded using Sendkeys method
		browse.sendKeys(claimZIP);

		
		

	}

}
