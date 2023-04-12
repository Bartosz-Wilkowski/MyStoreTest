package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddressesPage {
    private final WebDriver driver;

    @FindBy(xpath = "//div/address")
    private List<WebElement> addresses;

    @FindBy(xpath = "//a[@data-link-action='add-address']")
    private WebElement createNewAddressBtn;

    @FindBy(css = ".alert-success")
    private WebElement addressSuccessAlert;

    @FindBy(xpath = "(//a[@data-link-action='delete-address'])[2]")
    private WebElement deleteNewAddressBtn;

    @FindBy(xpath = "(//article[@class='address'])[2]/div/h4")
    private WebElement newAddressAlias;

    @FindBy(css = ".logout")
    private WebElement logoutBtn;

    public AddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean firstAddressIsVisible() {
        return addresses.size() > 0;
    }

    public boolean newAddressIsVisible() {
        return addresses.size() > 1;
    }


    public void createNewAddress() {
        createNewAddressBtn.click();
    }

    public String getTextAddressSuccessAlert() {
        return addressSuccessAlert.getText();
    }

    public String getNewAddressAsText() {
        WebElement address = addresses.get(1);
        return address.getText();
    }

    public String getTextFromNewAddressAlias() {
        return newAddressAlias.getText();
    }

    public void removeNewAddress() {
        deleteNewAddressBtn.click();
    }

    public void userLogOut() {
        logoutBtn.click();
    }


}
