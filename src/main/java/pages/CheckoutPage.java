package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    By firstNameField = By.id("firstNameInput");
    By lastNameField = By.id("lastNameInput");
    By addressField = By.id("addressLine1Input");
    By stateField = By.id("provinceInput");
    By postalCodeField = By.id("postCodeInput");
    By submitButton = By.id("checkout-shipping-continue");
    By downloadReceiptButton = By.id("downloadpdf");
    By continueShoppingButton = By.xpath("//button[@class='button button--tertiary optimizedCheckout-buttonSecondary']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillShippingDetails(String firstName, String lastName, String address, String state, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(postalCodeField).sendKeys(postalCode);
        driver.findElement(submitButton).click();
    }

    public void downloadReceipt() {
        driver.findElement(downloadReceiptButton).click();
    }

    public void continueShopping() {
        driver.findElement(continueShoppingButton).click();
    }
}
