package crm.selldo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class TaskPage extends SetUp{
	
	// ...................Task Page........................
	
	By editOption = By.xpath("//a[@class='btn btn-light task-edit-dropdown']");
	By editButton2 = By.xpath("//a[@class='dropdown-item edit_todo_task']");
	//By statusField = By.cssSelector("body.modal-open:nth-child(2) div.page-content-wrapper:nth-child(7) div.container-fluid div.row.page-container.lead-details-page:nth-child(4) div.state_details.col-lg-7 div.card div.card-body div.tab-content div.tab-pane.fade.show.active div.modal.right.modal-from-right.form-container-modal.fixed-top-modal.show div.modal-dialog.modal-lead div.modal-content div.modal-body form:nth-child(2) div.form-group.status-select:nth-child(6) > select.select2.form-control");
	By statusField = By.xpath("//*[@id=\"form_container\"]/div/div/div/div/div[2]/form/div[6]/select/option[2]");
	By addTaskButton = By.xpath("//a[contains(text(),'Add A Task')]");
	
	
	By addTaskTitle = By.xpath("//input[contains(@name,'task[title]')]");
	By addTaskDescription = By.xpath("//textarea[contains(@name,'task[description]')]");
	
	//#form_container > div > div > div > div > div.modal-body > form > div.form-group.status-select > select > option:nth-child(2)
	By completeOption = By.xpath("//option[contains(text(),'Completed')]");
	By saveButton = By.xpath("//button[@class='btn btn-primary btn-sm pull-right save']");
	
	public By taskStatus = By.xpath("//label[text()='STATUS']/following::span[1]");
	
	
	
	WebDriver driver = null;

	WebDriverWait wait;

	public TaskPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver = driver;// Calling Browser	
	}
	
	public void AddTask(){
		driver.findElement(addTaskButton).click();
				
	}
	
	public void AddTaskTitle(String taskTitle){
		driver.findElement(addTaskTitle).sendKeys(taskTitle);
				
	}
	
	public void addTaskDescription(String taskDescription){
		driver.findElement(addTaskDescription).sendKeys(taskDescription);
				
	}
	
	public void selectTask(){
		driver.findElement(editOption).click();
		driver.findElement(editButton2).click();
		
	}
	
	public void OptionSelect() {
		driver.findElement(statusField).click();
	}
		
	
	public void clickOnSaveButton() throws Exception {
		Thread.sleep(5000);
		driver.findElement(saveButton).click();
	}	
}
