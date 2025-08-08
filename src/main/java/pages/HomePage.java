package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='Samsung']")
    WebElement samsungButton;

    @FindBy(xpath = "//div[@class='sort']//select")
    WebElement dropdown;

    @FindBy(className = "MuiIconButton-label")
    WebElement addFavorite;

    @FindBy(id = "favourites")
    WebElement checkFavItems;

    @FindBy(className = "shelf-item__buy-btn")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[normalize-space()='+']")
    WebElement increaseQuantityButton;

    @FindBy(xpath = "//button[normalize-space()='-']")
    WebElement decreaseQuantityButton;

    @FindBy(xpath = "//div[@class='buy-btn']")
    WebElement checkoutButton;

    @FindBy(xpath = "//strong[normalize-space()='Orders']")
    WebElement myOrdersButton;

    @FindBy(id = "logout")
    WebElement logoutButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectSamsung() {
        samsungButton.click();
    }

    public void sortByLowestPrice() {
        Select select = new Select(dropdown);
        select.selectByValue("lowestprice");
    }

    public void addToFavorites() {
        addFavorite.click();
    }

    public void checkFavorites() {
        checkFavItems.click();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void increaseQuantity() {
        increaseQuantityButton.click();
    }

    public void decreaseQuantity() {
        decreaseQuantityButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public void viewMyOrders() {
        myOrdersButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    // Getter method for addFavorite button
    public WebElement getAddFavoriteButton() {
        return addFavorite;
    }

    // Getter method for addToCart button
    public WebElement getAddToCartButton() {
        return addToCartButton;
    }
}
