package crm.selldo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utility.SetUp;

public class EditUserFormPage extends SetUp {

	By FirstName = By.xpath("//input[@id='user_first_name']");
	By LastName = By.xpath("//input[@id='user_last_name']");
	By Phone = By.xpath("//input[@class='phone_number form-control user_primary_phone']");
	By Email = By.xpath("//input[@id='user_email']");
	By DepartmentDropdown = By.xpath("//select[@id='user_department']");
	By RoleDropdown = By.xpath("//select[@id='user_role']");
	By TeamDropdown = By.xpath("//select[@id='user_team_id']");

	By SaveButton = By.xpath("//input[@class='btn btn-primary']");

	WebDriver driver = null;

	public EditUserFormPage(WebDriver driver) {

		this.driver = driver;
	}

	public void changeFirstName(String firstname) {
		driver.findElement(FirstName).clear();
		driver.findElement(FirstName).sendKeys(firstname);
	}

	public void changeLastName(String lastname) {
		driver.findElement(LastName).clear();
		driver.findElement(LastName).sendKeys(lastname);
	}

	public void changePhone(String phone) {
		driver.findElement(Phone).clear();
		driver.findElement(Phone).sendKeys(phone);
	}

	public void changeEmail(String email) {
		driver.findElement(Email).clear();
		driver.findElement(Email).sendKeys(email);
	}

	// Selecting Department

	public void changeDepartmentToPresales() {
		Select oSelect = new Select(driver.findElement(DepartmentDropdown));
		oSelect.selectByVisibleText("Pre Sales");
	}

	public void changeDepartmentToSales() {
		Select oSelect = new Select(driver.findElement(DepartmentDropdown));
		oSelect.selectByVisibleText("Sales");
	}

	public void changeDepartmentToPostSales() {
		Select oSelect = new Select(driver.findElement(DepartmentDropdown));
		oSelect.selectByVisibleText("Post Sales");
	}

	// Selecting Role

	public void changeRoleToPresales() {
		Select oSelect = new Select(driver.findElement(RoleDropdown));
		oSelect.selectByVisibleText("Pre Sales");
	}

	public void changeRoleToSales() {
		Select oSelect = new Select(driver.findElement(RoleDropdown));
		oSelect.selectByVisibleText("Sales");
	}

	public void changeRoleToPostSales() {
		Select oSelect = new Select(driver.findElement(RoleDropdown));
		oSelect.selectByVisibleText("Post Sales");
	}

	public void changeRoleToManager() {
		Select oSelect = new Select(driver.findElement(RoleDropdown));
		oSelect.selectByVisibleText("Manager");
	}

	public void changeRoleToAdmin() {
		Select oSelect = new Select(driver.findElement(RoleDropdown));
		oSelect.selectByVisibleText("Admin");
	}

	// Selecting Team

	public void changeTeamByIndex() {
		Select oSelect = new Select(driver.findElement(TeamDropdown));
		oSelect.selectByIndex(1);
	}

	public void changeTeamByText() {
		Select oSelect = new Select(driver.findElement(TeamDropdown));
		oSelect.selectByVisibleText("TeamX");
	}

//	public void selectTeam() {
//		Select select = new Select(driver.findElement(TeamDropdown));
//		select.deselectAll();
//		WebElement option = select.getFirstSelectedOption();
//	}

	// public void selectTeam(){
	// Select dropDown = new Select(driver.findElement(TeamDropdown));
	// List <WebElement> elementCount = dropDown.getOptions();
	// int itemSize = elementCount.size();
	// for(int i = 0; i < itemSize ; i++){
	// String optionsValue = elementCount.get(i).getText();
	// System.out.println(optionsValue);
	// }
	// }
	//
	// public void selectTeam(){
	// driver.findElement(TeamDropdown).click();
	// Select oSelect = new Select(driver.findElement(TeamDropdown));
	// for (WebElement ele : oSelect)
	// {
	// System.out.println("Values " + ele.getAttribute("class"));
	//
	// if (ele.getAttribute("class").contains("hidden")) {
	//
	// ele.oSelect.selectByVisibleText("TeamX");}
	//
	// else {
	//
	// ele.click();
	// break;
	// }
	// }
	// }

	public void clickOnSaveButton() {
		driver.findElement(SaveButton).click();
	}

}
