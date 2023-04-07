package postSales;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TaskPage {
	
	//....... Add Task ..........
	
			By addaTask = By.xpath("//a[contains(text(),'Add A Task')]");
			By addaTitle = By.xpath("//input[@name='task[title]']");
			By addaDescription = By.xpath("//textarea[@name='task[description]']");
			By DueDateField = By.cssSelector("input[name='task[due_date]']");
			By ScheduleOnTimeField = By.cssSelector("		");
			By selectToday = By.cssSelector("div.datepicker-days > table > tbody > tr > td.active.day");
			By wholeCalender = By.xpath(
					"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top']//tr//td");
			By moveToNextMonth = By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'»')]");
			By selectDate  = By.xpath("//body[1]/div[29]/div[1]/table[1]/tbody[1]/tr[1]/td[4]");
			By saveButton = By.xpath("//button[@class='btn btn-primary btn-sm pull-right save']");
			By feedTab = By.xpath("//a[text()='Feed'][@href='#tab-activity']");
			By more_d = By.xpath("//div[@class='col-sm-6 col-lg-6 pr-0']//a[@class='nav-link dropdown-toggle'][contains(text(),'More')]");
			By SelectUserDropdown = By.xpath("//span[text()='Select sales']");
			By SelectUserTextField = By.xpath(".//*[@id='select2-drop']/div/input");
			By UserFromDropdown = By.xpath("//div[@class='select2-result-label']");
			
			By UploadattachmentButton = By.xpath("//a[contains(text(), 'Upload attachment')]");
		    By AmuraFolder = By.xpath("//a[@id='amura_folder']");
		    By AttachmentFile = By.xpath("//div[@id=\"asset_uploading_5d42e1910c739221f5e51d7e\"]");
		    By AttachmentNewFile = By.xpath("//*[@id='asset_uploading_5d42e1910c739221f5e51d7e']/div[2]/a[2]");
		    By saveAttachment = By.xpath("//button[@class='btn btn-primary add_attachments']");
		    
		    By PriorityField = By.xpath("//*[@name='task[priority]']");    
		   //By Priority_dd = By.xpath("//*[@id='add_new_task_form_container']/div/div/div/div/div[2]/form/div[5]/select/option");
		
		    By Priority_dd = By.xpath("//option[contains(text(),'High')]");
		    
		WebDriver driver = null;

		public TaskPage(WebDriver driver) {

			this.driver = driver;
		}
		
		
		// Click Add A Task Link
		public void addingTask() throws Exception {
			Thread.sleep(2000);
			driver.findElement(addaTask).click();
			Thread.sleep(2000);
			
		}

		
		// Selecting Task title and adding the title
		public void inputTasktitle(String title) {
			driver.findElement(addaTitle).sendKeys(title);
		}
		
		
		// Selecting Task title and adding the title
			public void inputTaskDescription(String taskdescription) {
				driver.findElement(addaDescription).sendKeys(taskdescription);
			}
		
		
		// Selecting current date from calendar
		public void selectDate() throws Exception {
			Thread.sleep(2000);
			driver.findElement(DueDateField).click();
			Thread.sleep(2000);
			driver.findElement(moveToNextMonth).click();
			Thread.sleep(2000);
			driver.findElement(selectDate).click();
			Thread.sleep(2000);
			WebElement date = driver.findElement(selectDate);
			
			String Data = date.getText();
			
		
			
			System.out.println(Data); 
			Thread.sleep(2000);
			
			//driver.findElement(selectToday).click();
		}

		// Clicking on Schedule On Time field
		
		public String selectSalesUser(String salesUserName) throws Exception
		{
			driver.findElement(By.xpath("//span[@class='select2-chosen']"));  // Clicking on User // dropdown
			
			String selesUserName = driver.findElement(By.xpath("//span[@class='select2-chosen']")).getText();
			
			 return salesUserName;
						
			
		}
		
		// Clicking on Schedule On Time field
		public void clickOnUploadAttachments()throws Exception {
			Thread.sleep(4000);
			
			 WebElement Element = driver.findElement(UploadattachmentButton);
			 JavascriptExecutor executor = (JavascriptExecutor)driver;
			 executor.executeScript("arguments[0].click();", Element);
			 
			
			//driver.findElement(By.linkText("Upload attachments")).click();
		        Thread.sleep(4000);
		        //driver.findElement(AmuraFolder).click();
		        
		        WebElement Element1 = driver.findElement(AmuraFolder);
				 JavascriptExecutor executor1 = (JavascriptExecutor)driver;
				 executor1.executeScript("arguments[0].click();", Element1);
		        
				 JavascriptExecutor executor2 = (JavascriptExecutor)driver;
				 WebElement Element2 = driver.findElement(AttachmentFile);			
				 executor2.executeScript("arguments[0].click();", Element2);
				 	 
				
				 JavascriptExecutor executor3 = (JavascriptExecutor)driver;
				 WebElement Element3 = driver.findElement(AttachmentNewFile);
				 executor3.executeScript("arguments[0].click();", Element3);
				 		 
				 WebElement Element4 = driver.findElement(saveAttachment);
				 executor2.executeScript("arguments[0].click();", Element4);
				 			 
				 Thread.sleep(3000);
		
		}
		
		// Clicking on Priority Field 
		public void selectPriority() throws Exception {
			
			JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			
			 WebElement Element2 = driver.findElement(PriorityField);
			 executor1.executeScript("arguments[0].click();", Element2);
			 
		    driver.findElement(Priority_dd).click();
		    }
		
		
		
		// Clicking on Schedule On Time field
		public void clickOnSaveButton() {
			driver.findElement(saveButton).click();
		}

		// Clicking on More Tab
		public void clickFeedTab() {
			driver.findElement(more_d).click();
			driver.findElement(feedTab).click();
		}
		
		

	

}
