package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private final WebDriver driver;

    @FindBy(css = ".address-alias")
    private WebElement addressOfDeliveryAlias;

    @FindBy(css = ".address")
    private WebElement addressOfDelivery;

    @FindBy(name = "confirm-addresses")
    private WebElement confirmAddressBtn;

    @FindBy(id = "delivery_option_8")
    private WebElement selfPickUpDeliveryBtn;

    @FindBy(name = "confirmDeliveryOption")
    private WebElement confirmDeliveryOption;

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckBtn;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement agreeToTermsOfServiceBtn;

    @FindBy(xpath = "//button[contains(text(), 'Place order')]")
    private WebElement placeOrderBtn;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTextFromAddressAlias() {
        return addressOfDeliveryAlias.getText();
    }

    public String getTextFromAddress() {
        return addressOfDelivery.getText();
    }

    public void confirmAddressOnCheckout() {
        confirmAddressBtn.click();
    }

    public void selectSelfPickUpDelivery() {
        if (!selfPickUpDeliveryBtn.isSelected()) {
            selfPickUpDeliveryBtn.click();
        }
        if (!confirmDeliveryOption.isSelected()) {
            confirmDeliveryOption.click();
        }
    }

    public void selectPayByCheckPayment() {
        payByCheckBtn.click();
    }

    public void agreeToTermsOfService() {
        agreeToTermsOfServiceBtn.click();
    }

    public OrderConfirmationPage placeOrder() {
        placeOrderBtn.click();
        return new OrderConfirmationPage(driver);
    }


}
