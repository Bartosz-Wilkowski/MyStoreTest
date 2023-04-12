package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewAddressPage {
    private final WebDriver driver;
    @FindBy(id = "field-alias")
    private WebElement aliasInput;

    @FindBy(id = "field-address1")
    private WebElement addressInput;

    @FindBy(id = "field-city")
    private WebElement cityNameInput;

    @FindBy(id = "field-postcode")
    private WebElement zipPostalCodeInput;

    @FindBy(id = "field-id_country")
    private WebElement countryDropDownList;

    @FindBy(id = "field-phone")
    private WebElement phoneNumberInput;

    @FindBy(css = "button.form-control-submit")
    private WebElement saveAddressBtn;

    public NewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void provideUserAddressData(String alias, String address, String city, String zipPostalCode, String country, String phone) {
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.clear();
        addressInput.sendKeys(address);

        cityNameInput.clear();
        cityNameInput.sendKeys(city);

        zipPostalCodeInput.clear();
        zipPostalCodeInput.sendKeys(zipPostalCode);

        Select selectCountry = new Select(countryDropDownList);
        selectCountry.selectByVisibleText(country);

        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phone);
    }

    public void saveNewAddress() {
        saveAddressBtn.click();
    }
}
