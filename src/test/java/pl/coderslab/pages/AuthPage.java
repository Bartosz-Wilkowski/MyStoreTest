package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {
    private final WebDriver driver;

    @FindBy(id = "field-email")
    private WebElement loginEmailInput;

    @FindBy(id = "field-password")
    private WebElement loginPasswordInput;

    @FindBy(id = "submit-login")
    private WebElement loginBtn;


    public AuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UserAccountPage loginAs(String email, String password) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);

        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(password);

        loginBtn.click();
        return new UserAccountPage(driver);
    }


}
