package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {
    private final WebDriver driver;

    @FindBy(xpath = "//table/tbody/tr[4]/td[2]")
    private WebElement totalValue;

    @FindBy(css = "a.account")
    private WebElement userAccountBtn;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTotalValue() {
        return totalValue.getText();
    }

    public UserAccountPage goToUserAccountPage() {
        userAccountBtn.click();
        return new UserAccountPage(driver);
    }
}
