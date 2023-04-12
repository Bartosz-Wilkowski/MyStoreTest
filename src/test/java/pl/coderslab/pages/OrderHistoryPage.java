package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
    private final WebDriver driver;

    @FindBy(xpath = "//table/tbody/tr[1]/td[2]")
    private WebElement latestOrderTotalPriceValue;

    @FindBy(xpath = "//table/tbody/tr[1]/td[4]")
    private WebElement latestOrderStatus;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLatestOrderTotalPriceValue() {
        return latestOrderTotalPriceValue.getText();
    }

    public String getLatestOrderStatus() {
        return latestOrderStatus.getText();
    }
}
