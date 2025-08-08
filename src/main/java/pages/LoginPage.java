package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private WebDriver driver;

	By signInButton = By.id("signin");
	By usernameField = By.id("react-select-2-input");
	By passwordField = By.id("react-select-3-input");
	By loginButton = By.id("login-btn");
	By errorMsg = By.className("api-error");
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	 public WebElement getSignInButton() {
	        return driver.findElement(signInButton);
	    }

	    public WebElement getUsernameField() {
	        return driver.findElement(usernameField);
	    }

	    public WebElement getPasswordField() {
	        return driver.findElement(passwordField);
	    }

	    public WebElement getLoginButton() {
	        return driver.findElement(loginButton);
	    }

	    public WebElement getErrorMessage() {
	        return driver.findElement(errorMsg);
	    }

	public void login(String username, String password) {
		driver.findElement(signInButton).click();
		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(usernameField).sendKeys(Keys.RETURN);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(passwordField).sendKeys(Keys.RETURN);
		driver.findElement(loginButton).click();
	}
}