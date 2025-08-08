package Test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import base.BaseClass;
import io.restassured.response.Response;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelReadUtils;

public class MainTest extends BaseClass {

    @Test
    public void TestingFunctionalities() throws InterruptedException, IOException {
        test = extent.createTest("Check Ecommerce Functionality");
        LoginPage loginpage = new LoginPage(driver);
        HomePage homepage = new HomePage(driver);
        CheckoutPage checkoutpage = new CheckoutPage(driver);

        test.info("Logging in with valid credentials");
        loginpage.login(config.getProperty("username"), config.getProperty("password"));
        waitForPageLoad(); // Wait for page to load after login
        takeScreenshot("Login Successful");
        test.pass("Login successful");

        test.info("Selecting Samsung category");
        homepage.selectSamsung();
        waitForPageLoad(); // Wait for page to load after selecting category
        test.pass("Samsung category selected");

        test.info("Sorting by lowest price");
        homepage.sortByLowestPrice();
        waitForPageLoad(); // Wait for page to load after sorting
        test.pass("Sorted by lowest price");

        test.info("Adding item to favorites");
        homepage.addToFavorites();
        waitForElementToBeClickable(homepage.getAddFavoriteButton()); // Wait for element to be clickable
        test.pass("Item added to favorites");

        test.info("Checking favorite items");
        homepage.checkFavorites();
        waitForPageLoad(); // Wait for page to load after checking favorites
        driver.navigate().back();
        test.pass("Favorite items checked");

        test.info("Adding item to cart");
        homepage.addToCart();
        waitForElementToBeClickable(homepage.getAddToCartButton()); // Wait for element to be clickable
        test.pass("Item added to cart");

        test.info("Increasing item quantity");
        homepage.increaseQuantity();
        test.pass("Item quantity increased");

        test.info("Decreasing item quantity");
        homepage.decreaseQuantity();
        test.pass("Item quantity decreased");

        test.info("Proceeding to checkout");
        homepage.proceedToCheckout();
        waitForPageLoad(); // Wait for page to load after proceeding to checkout
        test.pass("Proceeded to checkout");

        test.info("Filling in shipping details");
        checkoutpage.fillShippingDetails(config.getProperty("firstname"), config.getProperty("lastname"),
                config.getProperty("address"), config.getProperty("state"), config.getProperty("postalCode"));
        takeScreenshot("Shipping Details Filled");
        test.pass("Shipping details filled");

        test.info("Downloading receipt");
        checkoutpage.downloadReceipt();
        test.pass("Receipt downloaded");

        test.info("Continuing shopping");
        checkoutpage.continueShopping();
        test.pass("Continued shopping");

        test.info("Viewing my orders");
        homepage.viewMyOrders();
        waitForPageLoad(); // Wait for page to load after viewing orders
        takeScreenshot("My Orders");
        test.pass("My orders viewed");

        driver.navigate().back();
        waitForPageLoad(); // Wait for page to load after navigating back
        test.pass("Navigated back");

        test.info("Logging out");
        homepage.logout();
        test.pass("Logged out");
    }
    
    @Test
    public void testInvalidLogin() throws IOException {
        test = extent.createTest("Check Invalid Credentials");
        LoginPage loginPage = new LoginPage(driver);

        // Read credentials from Excel
        String excelPath = "D:\\Automation\\AutomationData.xlsx";
        String sheetName = "Sheet1";
        ExcelReadUtils.openExcel(excelPath, sheetName);

        String username = ExcelReadUtils.getCellValue(1, 0); // Assuming row 1 (0-indexed) is for credentials
        String password = ExcelReadUtils.getCellValue(1, 1); // Column 0 is for username, column 1 is for password

        ExcelReadUtils.closeExcel();

        test.info("Attempting login with invalid credentials");
        loginPage.getSignInButton().click();
        loginPage.getUsernameField().sendKeys(username);
        loginPage.getUsernameField().sendKeys(Keys.RETURN);
        loginPage.getPasswordField().sendKeys(password);
        loginPage.getPasswordField().sendKeys(Keys.RETURN);
        loginPage.getLoginButton().click();

        test.fail("Entered Invalid Credentials");

        // Optionally take screenshot or perform other actions after logging in
        takeScreenshot("Invalid Login Attempt");
    }
    
    @Test
    public void testGetStatusCode() {
        
        test = extent.createTest("Check API Status Code");
        
        String baseUri = config.getProperty("BaseUrl");
        String endpoint = "/orders"; 
        
        test.info("Sending GET request to " + baseUri + endpoint);
        
        Response response = given()
                                .baseUri(baseUri)
                            .when()
                                .get(endpoint)
                            .then()
                                .extract().response();

       
        int statusCode = response.getStatusCode();

          if (statusCode == 200) {
            test.pass("API responded with status code 200");
        } else {
            test.fail("API responded with status code " + statusCode);
        }
    }


    public void takeScreenshot(String stepDescription) throws IOException {
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "C:\\Users\\HP\\eclipse-workspace\\Wipro2\\ScreenShots\\" + stepDescription + "-" + System.currentTimeMillis() + ".png";
        Files.copy(f, new File(screenshotPath));
        test.addScreenCaptureFromPath(screenshotPath, stepDescription);
    }

    // Custom methods for synchronization
    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
