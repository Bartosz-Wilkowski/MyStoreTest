package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='user-info']/a")
    private WebElement signInBtn;

    @FindBy(xpath = "//a[text()='Hummingbird printed sweater']")
    private WebElement hummingbirdPrintedSweaterBtn;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthPage signInWithPage() {
        signInBtn.click();
        return new AuthPage(driver);
    }

    public ProductPage selectHummingbirdPrintedSweater() {
        hummingbirdPrintedSweaterBtn.click();
        return new ProductPage(driver);
    }


}
