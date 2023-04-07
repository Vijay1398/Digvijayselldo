package adminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class ExportPage extends SetUp {

	By exportLeadsTab = By.id("export_leads");
	By exportTasksTab = By.id("export_tasks");
	By exportUsersTab = By.id("export_users");
	By exportUserLogsTab = By.id("export_user_logs");
	By exportVirtualNumbersTab = By.id("export_virtual_numbers");
	By exportBookingsTab = By.id("export_booking_details");
	By exportCampaignsTab = By.id("export_campaigns");
	By exportInvoicesTab = By.id("export_invoices");
	By exportCallsTab = By.id("export_calls");
	By exportSiteVisitsTab = By.id("export_site_visits");
	By exportFollowupsTab = By.id("export_followups");
	By exportPartnersTab = By.id("export_partners");
	By exportProjectUnitsTab = By.id("export_project_units");
	By exportProjectsTab = By.id("export_lead_projects");
	By duration = By.id("input-duration");
	By role = By.cssSelector("li.select2-search-field input");
	By projectField = By.xpath("//span[text()='Project']");
	By project_dd = By.xpath("//input[@class='select2-input']/following::ul[@class='select2-results']//li[3]");
	
	public By exportStatus = By.xpath("/html/body/div[6]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/table/tbody/tr[2]/td[4]/span");
	///html[1]/body[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[4]/span[1]
	public By userLogExportStatus = By.xpath("/html/body/div[6]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/table/tbody/tr[2]/td[4]/span");
	////body[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[4]
	By calenderRange_ExportLead = By.xpath("//div[@class='ranges']//ul//li");
	By roleDropDown = By.xpath("//div[@id='select2-drop']//ul//li");
	By email = By.xpath("//input[@id='s2id_autogen4']");
	By selectEmail= By.xpath(".//*[@id='select2-drop']/ul/li[1]/div");
	//By email = By.xpath("//input[@value='Export']/preceding::input[1]");
	By campaignSpan = By.xpath("//label[text()='Campaign']/following::input[1]");
	By selectcampaign = By.cssSelector("#select2-drop ul li:nth-child(1) div");
	By campaign_All = By.xpath("//ul[@class='select2-results']//li");
	By invoicesSpan = By.xpath("//span[text()='invoice']");
	By selectInvoice = By.cssSelector("#select2-drop ul li:nth-child(1) div");
	By invoice_All = By.xpath("//ul[@class='select2-results']//li");
	By invoiceTypeSpan = By.xpath("//span[text()='Type']");
	By invoiceType_All = By.xpath("//ul[@class='select2-results']//li");
	By exportButton = By.id("btn-export");
	By NextButton = By.id("btn-next");
	By exportHistoryTab = By.id("btn-export-history");
	By funnelIcon = By.id("funnel-export");
	By createdAtField = By.xpath("//input[@name='filters[created_at]']");
	By createdAtField_All = By.xpath("//div[@class='daterangepicker dropdown-menu opensleft']//ul//li");
	By exportTypeSpan = By.id("s2id_options-export-type");
	By exportTypeSpan_All = By.xpath("//ul[@class='select2-results']//li");
	By applyButton = By.id("btn-apply");
	By clearAllLink = By.id("link-clear-all");
	By crossExportIcon = By.xpath("//*[@id=\"modal-remote-form-inner\"]/div/div/div[1]/button/i");
			//.cssSelector("button[class='close'] i[class='ion-android-close']");
	
	By crossFilterIcon = By.xpath("//input[@value='Apply']/preceding::i[@class='ion-android-close'][1]");
	By clickToExpandLink = By.xpath("//a[text()='Home']/following::b[1]");
	By selectProject = By.cssSelector("div#select2-drop ul li:nth-child(17)");
	public By exportLabel = By.cssSelector("h6#export_type");
	public By emailIdField = By.cssSelector("div.table-filter-container.pt-2 table tbody tr:nth-child(2) td:nth-child(6) span");
	

	WebDriver driver = null;
	WebDriverWait wait;

	public ExportPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnExportLeadsTab() {
		driver.findElement(exportLeadsTab).click();
	}
	
	public void clickOnExportTasksTab() {
		driver.findElement(exportTasksTab).click();
	}

	public void clickOnExportUsersTab() {
		driver.findElement(exportUsersTab).click();
	}
	
	public void clickOnExportUserLogsTab() {
		driver.findElement(exportUserLogsTab).click();
	}

	public void clickOnExportVirtualNumbersTab() {
		driver.findElement(exportVirtualNumbersTab).click();
	}

	public void clickOnExportBookingsTab() {
		driver.findElement(exportBookingsTab).click();
	}

	public void clickOnExportCampaignsTab() {
		driver.findElement(exportCampaignsTab).click();
	}

	public void clickOnExportInvoicesTab() {
		driver.findElement(exportInvoicesTab).click();
	}

	public void clickOnExportCallsTab() {
		driver.findElement(exportCallsTab).click();
	}

	public void clickOnExportSiteVisitsTab() {
		driver.findElement(exportSiteVisitsTab).click();
	}

	public void clickOnExportFollowupsTab() {
		driver.findElement(exportFollowupsTab).click();
	}
	
	public void clickOnExportChannelPartnersTab() {
		driver.findElement(exportFollowupsTab).click();
	}

	public void clickOnExportHistoryTab() {
		driver.findElement(exportHistoryTab).click();
	}
	
	public void clickOnExportPartnersTab() {
		driver.findElement(exportPartnersTab).click();
	}
	
	public void clickOnExportProjectUnitsTab() {
		driver.findElement(exportProjectUnitsTab).click();
	}
	
	public void clickOnExportProjectsTab() {
		driver.findElement(exportProjectsTab).click();
	}

	public void selectDuration() {
		driver.findElement(duration).click();

		List<WebElement> list = driver.findElements(calenderRange_ExportLead);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Last 7 Days

			if (ele.getAttribute("innerHTML").contains("Today")) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Last 7 Days");

				// break the loop or come out of loop

				break;
			}
		}
	}
	
	public void selectRole() {
		driver.findElements(role).get(0).click();

		List<WebElement> list = driver.findElements(roleDropDown);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Last 7 Days

			if (ele.getAttribute("innerHTML").contains("Sales")) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Sales Role");

				// break the loop or come out of loop

				break;
			}
		}
	}

	public void enterEmail(String em) {
		driver.findElement(email).sendKeys(em);
		driver.findElement(selectEmail).click();
	}

	public void selectInvoice(String em) {
		driver.findElement(invoicesSpan).click();
		driver.findElement(selectInvoice).click();
	}

	public void selectInvoice() {
		driver.findElement(invoicesSpan).click();
		List<WebElement> list = driver.findElements(invoice_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("ADPL-23886097720170421 (01/01/2017 - 22/04/2017)")) {
				ele.click();
				System.out.println("Clicked on ADPL-23886097720170421 (01/01/2017 - 22/04/2017)");
				break;
			}
		}
	}

	public void selectInvoiceType_MarketingActivity() {
		driver.findElement(invoiceTypeSpan).click();
		List<WebElement> list = driver.findElements(invoiceType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Marketing Activity")) {
				ele.click();
				System.out.println("Clicked on Marketing Activity");
				break;
			}
		}
	}

	public void selectInvoiceType_Sms() {
		driver.findElement(invoiceTypeSpan).click();
		List<WebElement> list = driver.findElements(invoiceType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Sms")) {
				ele.click();
				System.out.println("Clicked on Sms");
				break;
			}
		}
	}

	public void selectInvoiceType_Email() {
		driver.findElement(invoiceTypeSpan).click();
		List<WebElement> list = driver.findElements(invoiceType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Email")) {
				ele.click();
				System.out.println("Clicked on Email");
				break;
			}
		}
	}

	public void selectInvoiceType_Call() {
		driver.findElement(invoiceTypeSpan).click();
		List<WebElement> list = driver.findElements(invoiceType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Call")) {
				ele.click();
				System.out.println("Clicked on Call");
				break;
			}
		}
	}

	public void clickOnExportButton() {
		
		driver.findElement(exportButton).click();
		
	}
	
	public void clickOnNextButton() {
		driver.findElement(NextButton).click();
				
	}

	public void clickOnFunnelIcon() {
		driver.findElement(funnelIcon).click();
	}

	public void selectCreatedAtDateRange() {
		driver.findElement(createdAtField).click();

		List<WebElement> list = driver.findElements(createdAtField_All);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Last 7 Days

			if (ele.getAttribute("innerHTML").contains("Today")) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Today");

				// break the loop or come out of loop

				break;
			}
		}
	}

	public void selectExportType_ExportLeads() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Leads")) {
				ele.click();
				System.out.println("Clicked on Leads");
				break;
			}
		}
	}
	
	public void selectExportType_ExportTasks() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Tasks")) {
				ele.click();
				System.out.println("Clicked on Leads");
				break;
			}
		}
	}

	public void selectExportType_ExportUsers() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Users")) {
				ele.click();
				System.out.println("Clicked on Users");
				break;
			}
		}
	}
	
	public void selectExportType_ExportUserLogs() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("User Logs")) {
				ele.click();
				System.out.println("Clicked on Users");
				break;
			}
		}
	}

	public void selectExportType_ExportCalls() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Calls")) {
				ele.click();
				System.out.println("Clicked on Calls");
				break;
			}
		}
	}

	public void selectExportType_ExportSiteVisits() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Site visit")) {
				ele.click();
				System.out.println("Clicked on SiteVisits");
				break;
			}
		}
	}

	public void selectExportType_ExportFollowups() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Followups")) {
				ele.click();
				System.out.println("Clicked on Followups");
				break;
			}
		}
	}

	public void selectExportType_ExportBookings() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Booking Detail")) {
				ele.click();
				System.out.println("Clicked on Bookings");
				break;
			}
		}
	}

	public void selectExportType_ExportVirtualNumbers() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("VirtualNumbers")) {
				ele.click();
				System.out.println("Clicked on VirtualNumbers");
				break;
			}
		}
	}

	public void selectExportType_ExportItemisedBillSms() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Itemized Bill(Sms)")) {
				ele.click();
				System.out.println("Clicked on Itemized Bill(Sms)");
				break;
			}
		}
	}

	public void selectExportType_ExportItemisedBillEmail() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Itemized Bill(Email)")) {
				ele.click();
				System.out.println("Clicked on Itemized Bill(Email)");
				break;
			}
		}
	}

	public void selectExportType_ExportItemisedBillCall() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Itemized Bill(Call)")) {
				ele.click();
				System.out.println("Clicked on Itemized Bill(Call)");
				break;
			}
		}
	}

	public void selectExportType_ExportItemisedBillMarketingActivity() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Itemized Bill(Marketing Activity)")) {
				ele.click();
				System.out.println("Clicked on Itemized Bill(Marketing Activity)");
				break;
			}
		}
	}

	public void selectExportType_ExportCampaigns() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Campaign/SRD")) {	
				ele.click();
				System.out.println("Clicked on Campaign");
				break;
			}
		}
	}
	
	public void selectExportType_ExportPartners() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Channel Partner")) {
				ele.click();
				System.out.println("Clicked on Partners");
				break;
			}
		}
	}
	
	
	public void selectExportType_ExportProjectUnits() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Project Unit")) {
				ele.click();
				System.out.println("Clicked on Project Unit");
				break;
			}
		}
	}
	
	public void selectExportType_ExportProjects() {
		driver.findElement(exportTypeSpan).click();
		List<WebElement> list = driver.findElements(exportTypeSpan_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Project")) {
				driver.findElement(selectProject).click();
				//ele.click();
				System.out.println("Clicked on Project");
				break;
			}
		}
	}
	
	public void scrollToBottom() {
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void selectCampaign() {
		driver.findElement(campaignSpan).click();
		driver.findElement(selectcampaign).click();
	
	}

	public void clickOnApplyButton() {
		driver.findElement(applyButton).click();
	}
	
	public void clickOnFilterCrossIcon() {
		driver.findElement(crossFilterIcon).click();
	}
	
	public void clickOnExportCrossIcon() {
		
		//This is used to select the cross icon to close the model
		
		WebElement element = driver.findElement(crossExportIcon);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript ("arguments[0].click();" , element);
	    
		//driver.findElement(crossExportIcon).click();
	}
	
	public void clickOnClickToExpandLink() {
		driver.findElement(clickToExpandLink).click();
	}
	
	public void selectProject(){
		driver.findElement(projectField).click();
		driver.findElement(project_dd).click();
	}

}
