package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetUp {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> exTest = new ThreadLocal<ExtentTest>();

	public WebDriver driver = null;

	public void mysetUp() throws Exception {

		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(
		System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fi);
		

		//new File(System.getProperty("user.dir") + "/src/main/java/com/qa/testdata/FreeCrmTestData.xlsx"))
		
		System.out.println(p.getProperty("browser"));

		if (p.getProperty("browser").contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", "//home//amura//SeleniumStuffs//geckodriver");
			driver = new FirefoxDriver();
		} else if (p.getProperty("browser").contains("chrome")) {

			//System.setProperty("webdriver.chrome.driver", "/home/yash/Documents/chromedriver_linux64/chromedriver");
			//home//amura//SeleniumStuffs//chromedriver");
			
			// To run on local comment above and uncomment below			
			//System.setProperty("webdriver.chrome.driver","/home/yash/Documents/chromedriver_linux64/chromedriver");
			
			
			System.setProperty("webdriver.chrome.driver","/home/digvijay/chromedriver_linux64/chromedriver");
			

				
			//To upload file in headless mode
			System.setProperty("java.awt.headless", "true");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--window-size=1920,1200", "--no-sandbox");

			// To run scripts in headless mode on jenkins
			//options.addArguments("--headless", "window-size=1920,1200", "--no-sandbox");
			
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver( capabilities);
			
		} else {
			Internetexplorer();
		}
		try {
			driver.get(p.getProperty("url"));
		} catch (TimeoutException e2) {
			// TODO Auto-generated catch block
			// Log.error("Catching timeout exception");
			driver.navigate().refresh();
		}
		driver.manage().window().maximize();
	}

	public void setExtentTest(ExtentTest et) throws Exception{

		exTest.set(et);
	}

	public ExtentTest getExtTest() throws Exception{

		return exTest.get();
	}

	private void Internetexplorer() {
		// TODO Auto-generated method stub
	}

	// Method to load properties file
	public Properties loadPropertyFile() throws Exception {
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
		return property;
	}

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {

		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fi);
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-report/AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Linux");
		extent.setSystemInfo("Host Name", "selldoTest-ThinkCentre-A85");
		extent.setSystemInfo("Environment", p.getProperty("url"));
		extent.setSystemInfo("User Name", "amura");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {

			String screenShotPath = CaptureScreenshot.captureScreenshot(driver, result.getName());
			System.out.println(screenShotPath);
			exTest.get().log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			exTest.get().fail(result.getThrowable());
			exTest.get().fail("Failure Snapshot below: " + exTest.get().addScreenCaptureFromPath(screenShotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			exTest.get().log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			exTest.get().log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			exTest.get().skip(result.getThrowable());
		}
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception{
		extent.flush();

	}
}
