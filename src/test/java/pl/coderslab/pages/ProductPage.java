package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private final WebDriver driver;

    @FindBy(css = ".discount-percentage")
    private WebElement discountPercentage;

    @FindBy(id = "group_1")
    private WebElement sizeDropDownList;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//a[text()='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDiscountText() {
        return discountPercentage.getText();
    }

    public void selectSize(String size) {
        Select selectSize = new Select(sizeDropDownList);
        selectSize.selectByVisibleText(size);
    }

    public void selectQuantity(int quantity) {
        quantityInput.sendKeys(Integer.toString(quantity));
    }

    public void addItemsToCart() {
        addToCartBtn.click();
    }

    public CartPage proceedToCheckout() {
        proceedToCheckoutBtn.click();
        return new CartPage(driver);
    }


}
