package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[text()='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage proceedToCheckout() {
        proceedToCheckoutBtn.click();
        return new CheckoutPage(driver);
    }
}
