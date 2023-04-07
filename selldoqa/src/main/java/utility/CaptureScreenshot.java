package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	
	public static String captureScreenshot(WebDriver driver,String screenShotName) throws Exception
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"/ErrorScreenshots/"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);        
                     
        return dest;
    }
	
	/*public static String captureScreenshot(WebDriver driver,String screenshotName)
	{
		
		String imagePath= null;
	 
	try 
	{
	TakesScreenshot ts=(TakesScreenshot)driver;
	 
	File source=ts.getScreenshotAs(OutputType.FILE);
	 
	//FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));
	 
	FileUtils.copyFile(source, new File("//home//amura//Screenshots//"+screenshotName+".png"));
	System.out.println("Screenshot taken");
	 imagePath= "//home//amura//Screenshots//"+screenshotName+".png";
	} 
	catch (Exception e)
	{
	 
	System.out.println("Exception while taking screenshot "+e.getMessage());
	}
	return imagePath;
	
	}*/

}