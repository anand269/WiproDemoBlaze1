package Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class PracticePageLinkDemo extends BaseClass{
	@Test 
	public void practicePage() throws InterruptedException
	{
		invokeBrowser("chrome");
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		//identify the footer section
		WebElement footer = driver.findElement(By.xpath("//div[@id='gf-BIG']"));
		List<WebElement> footerLinks = driver.findElements(By.xpath("//div[@id='gf-BIG']//a"));
		System.out.println(footerLinks.size());//total no of links in footer section
		
		//print all the links
		for(WebElement link: footerLinks)
		{
			System.out.println(link.getText());			
		}		
		
		//To find the total no of radio buttons
		List<WebElement> radioBtns = driver.findElements(By.name("radioButton"));
		System.out.println(radioBtns.size()); //3
		//print all the radio btns
		for(WebElement radioBtn: radioBtns)
		{
			System.out.println(radioBtn.getAttribute("value"));			
			radioBtn.click();
			System.out.println("Displayed : "+radioBtn.isDisplayed());
			System.out.println("Enabled : "+radioBtn.isEnabled());
			System.out.println("Selected : "+radioBtn.isSelected());
		}
		
		//Implement the above logic for check boxes
		
		//Select drop down
		WebElement selBox = driver.findElement(By.xpath("//select[@name='dropdown-class-example']"));
		Select sel = new Select(selBox);
		sel.selectByIndex(1);
		Thread.sleep(2000);
		sel.selectByValue("option3");
		Thread.sleep(2000);
		
	//	JavascriptExecutor js =(JavascriptExecutor)driver; js.executeScript("window.scrollBy(0,300)");
		driver.findElement(By.id("checkBoxOption3")).click();		
		closeBrowser();
	}

}
