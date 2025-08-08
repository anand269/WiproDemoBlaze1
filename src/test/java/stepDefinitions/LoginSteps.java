package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class LoginSteps {

    WebDriver driver;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://bstackdemo.com/?signin=true");
        driver.findElement(By.id("signin")).click();
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("react-select-2-input"));
        usernameField.sendKeys(username);
        usernameField.sendKeys(org.openqa.selenium.Keys.RETURN);

        WebElement passwordField = driver.findElement(By.id("react-select-3-input"));
        passwordField.sendKeys(password);
        passwordField.sendKeys(org.openqa.selenium.Keys.RETURN);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        WebElement errorMsg = driver.findElement(By.className("api-error"));
        if (errorMsg.isDisplayed()) {
            System.out.println("Error message displayed: Login failed");
        } else {
            System.out.println("Error message not displayed");
        }
        driver.quit();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        
        List<WebElement> errorMsgs = driver.findElements(By.className("api-error"));
        if (errorMsgs.isEmpty()) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }
        driver.quit();
    }

}
