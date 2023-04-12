package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountPage {
    private final WebDriver driver;

    @FindBy(id = "addresses-link")
    private WebElement addressesTileBtn;

    @FindBy(xpath = "//a[@title = 'Addresses']")
    private WebElement addressesFooterBtn;

    @FindBy(xpath = "//a[@data-link-action='add-address']")
    private WebElement createNewAddressBtn;

    @FindBy(xpath = "//div[@id='_desktop_logo']/a")
    private WebElement storeLogo;

    @FindBy(xpath = "//a[@id='history-link']/span")
    private WebElement orderHistoryAndDetailsBtn;

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToUsersAddressesPageByTile() {
        addressesTileBtn.click();
    }

    public void goToUsersAddressesPageByFooter() {
        addressesFooterBtn.click();
    }

    public OrderHistoryPage goToOrderHistoryAndDetails() {
        orderHistoryAndDetailsBtn.click();
        return new OrderHistoryPage(driver);
    }

    public MainPage returnToMainPage() {
        storeLogo.click();
        return new MainPage(driver);
    }

}
