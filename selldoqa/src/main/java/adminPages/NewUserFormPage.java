package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utility.SetUp;

public class NewUserFormPage extends SetUp {

	By firstName = By.xpath("//input[@id='user_first_name']");
	By lastName = By.xpath("//input[@id='user_last_name']");
	By phone = By.xpath("//input[@class='phone_number form-control user_primary_phone']");
	By email = By.xpath("//input[@id='user_email']");
	By professionalDetailsTab = By.xpath("//a[text()='Professional Details']");
	By departmentDropdown = By.xpath("//select[@id='user_department']");
	By roleDropdown = By.xpath("//select[@id='user_role']");
	By teamDropdown = By.xpath("//span[text()='Please Select a Team']");
	By managerTeamDropdown = By.xpath("//span[text()='Select teams']");
	By team_dd = By.xpath(".//*[@id='select2-drop']/ul/li[50]/div");
	By SourceFrom_dd = By.xpath(".//*[@id='select2-drop']/ul/li[2]/div");
	// By teamDropdown = By.xpath("//select[@id='user_team_id']");
	By saveButton = By.xpath("//input[@class='btn btn-primary']");

	WebDriver driver = null;

	public NewUserFormPage(WebDriver driver) {

		this.driver = driver;
	}

	public void enterFirstName(String firstname) {
		driver.findElement(firstName).sendKeys(firstname);
	}

	public void enterLastName(String lastname) {
		driver.findElement(lastName).sendKeys(lastname);
	}

	public void enterPhone(String ph) {
		driver.findElement(phone).sendKeys(ph);
	}

	public void enterEmail(String em) {
		driver.findElement(email).sendKeys(em);
	}

	public void clickOnProfessionalDetailsTab() {
		driver.findElement(professionalDetailsTab).click();
	}

	// Methods for Selecting Department

	public void selectDepartment_Presales() {
		Select oSelect = new Select(driver.findElement(departmentDropdown));
		oSelect.selectByVisibleText("Pre Sales");
	}

	public void selectDepartment_Sales() {
		Select oSelect = new Select(driver.findElement(departmentDropdown));
		oSelect.selectByVisibleText("Sales");
	}

	public void selectDepartment_PostSales() {
		Select oSelect = new Select(driver.findElement(departmentDropdown));
		oSelect.selectByVisibleText("Post Sales");
	}

	// Methods for Selecting Role

	public void selectRole_Presales() {
		Select oSelect = new Select(driver.findElement(roleDropdown));
		oSelect.selectByVisibleText("Pre Sales");
	}

	public void selectRole_Sales() {
		Select oSelect = new Select(driver.findElement(roleDropdown));
		oSelect.selectByVisibleText("Sales");
	}

	public void selectRole_PostSales() {
		Select oSelect = new Select(driver.findElement(roleDropdown));
		oSelect.selectByVisibleText("Post Sales");
	}

	public void selectRole_Manager() {
		Select oSelect = new Select(driver.findElement(roleDropdown));
		oSelect.selectByVisibleText("Manager");
	}

	public void selectRole_Admin() {
		Select oSelect = new Select(driver.findElement(roleDropdown));
		oSelect.selectByVisibleText("Admin");
	}

	// Methods for Selecting Team

	public void selectTeamByIndex() {
		Select oSelect = new Select(driver.findElement(teamDropdown));
		oSelect.selectByIndex(1);
	}

	public void selectTeam() {

		driver.findElement(teamDropdown).click();
		driver.findElement(team_dd).click();
	}
	
	public void selectTeamForManager() {

		driver.findElement(managerTeamDropdown).click();
		driver.findElement(team_dd).click();
	}
	
	

	public void selectTeamByText() {
		Select oSelect = new Select(driver.findElement(teamDropdown));
		oSelect.selectByVisibleText("Team#1");
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
}
