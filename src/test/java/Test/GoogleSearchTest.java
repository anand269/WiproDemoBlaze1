package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class GoogleSearchTest extends BaseClass{

	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentSparkReporter sparkReporter;
	@BeforeTest
	public void setup()
	{		
		//invoking the browser
		invokeBrowser("chrome");
		if(extent==null)
		{
			extent = new ExtentReports();
			sparkReporter = new ExtentSparkReporter("./ExtentReport.html");
			extent.attachReporter(sparkReporter);
		}
		extent.createTest("Google search test");
		driver.get("https://www.google.com/");
		
	}
	
	@Test
	public void googleTest()
	{
		test.log(Status.INFO, "Navigated to google page");
		driver.findElement(By.name("q")).sendKeys("Extent reports in TestNG", Keys.ENTER);
		test.log(Status.INFO, "Extent Reports");
		
		String title = driver.getTitle();
		if(title.contains("Extent Reports"))
		{
			test.log(Status.PASS, "Search result page loaded successfully");			
		}
		else
		{
			test.log(Status.FAIL,"Search result page did not match");
		}
	}
	
	@AfterTest
	public void close()
	{
		driver.close();
	}
}
