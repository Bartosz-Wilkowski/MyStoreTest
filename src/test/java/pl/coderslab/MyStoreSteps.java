package pl.coderslab;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.coderslab.pages.*;

import java.io.File;
import java.time.Duration;

public class MyStoreSteps {

    private WebDriver driver;

    private AddressesPage addressesPage;

    private UserAccountPage userAccountPage;

    String totalValue;

    @Given("User is on main page")
    public void userIsOnMainPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
    }


    @And("User go to login page")
    public void userGoToLoginPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signInWithPage();
    }


    @And("User log in using {string} and {string}")
    public void userLogInUsingAnd(String email, String password) {
        AuthPage authPage = new AuthPage(driver);
        authPage.loginAs(email, password);
    }


    @And("User go to addresses page by addresses tile")
    public void userGoToAddressesPage() {
        userAccountPage = new UserAccountPage(driver);
        userAccountPage.goToUsersAddressesPageByTile();
    }

    @And("User go to addresses page by footer")
    public void userGoToAddressesPageByFooter() {
        userAccountPage = new UserAccountPage(driver);
        userAccountPage.goToUsersAddressesPageByFooter();
    }


    @And("User can see there is already one address")
    public void userCanSeeThereIsAlreadyOneAddress() {
        addressesPage = new AddressesPage(driver);
        Assertions.assertTrue(addressesPage.firstAddressIsVisible(), "There should be one address already");
    }


    @When("User create new address")
    public void userCreateNewAddress() {
        addressesPage = new AddressesPage(driver);
        addressesPage.createNewAddress();
    }


    @And("User enter new address {string}, {string}, {string}, {string}, {string}, {string}")
    public void userEnterNewAddress(String alias, String address, String city, String zipPostalCode, String country, String phone) {
        NewAddressPage newAddressPage = new NewAddressPage(driver);
        newAddressPage.provideUserAddressData(alias, address, city, zipPostalCode, country, phone);
        newAddressPage.saveNewAddress();
    }


    @Then("User can see new address")
    public void userCanSeeNewAddress() {
        Assertions.assertTrue(addressesPage.newAddressIsVisible(), "New address tile should be visible");
        Assertions.assertEquals("Address successfully added!", addressesPage.getTextAddressSuccessAlert());
    }

    @And("User verify created address {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userVerifyCreatedAddress(String alias, String address, String city, String zipPostalCode, String country, String phone, String firstName, String lastName) {
        String expectedAddress = firstName + " " + lastName + "\n" + address + "\n" + city + "\n" + zipPostalCode + "\n" + country + "\n" + phone;
        Assertions.assertEquals(alias, addressesPage.getTextFromNewAddressAlias(), "Aliases should be the same");
        Assertions.assertEquals(expectedAddress, addressesPage.getNewAddressAsText(), "Addresses should be the same");
    }


    @And("User remove new address")
    public void userRemovesNewAddress() {
        addressesPage.removeNewAddress();
    }


    @And("User can see the address was removed")
    public void userCanSeeTheAddressWasRemoved() {
        Assertions.assertFalse(addressesPage.newAddressIsVisible(), "New address tile should not be visible");
        Assertions.assertEquals(addressesPage.getTextAddressSuccessAlert(), "Address successfully deleted!");
    }


    @And("User log out")
    public void userLogsOut() {
        addressesPage.userLogOut();
    }

    @And("User close the browser")
    public void userCloseTheBrowser() {
        driver.quit();
    }


    private ProductPage productPage;

    private CheckoutPage checkoutPage;

    @And("User go to main page")
    public void userGoToMainPage() {
        userAccountPage = new UserAccountPage(driver);
        userAccountPage.returnToMainPage();
    }

    @And("User choose Hummingbird Printed Sweater on main page")
    public void userChooseHummingBirdPrintedSweater() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectHummingbirdPrintedSweater();
        productPage = new ProductPage(driver);
    }

    @And("User check discount {string}")
    public void userCheckDiscount(String discount) {
        Assertions.assertEquals(discount, productPage.getDiscountText(), "Discount should be" + discount);
    }

    @And("User select size {string}")
    public void userSelectSize(String selectedSize) {
        productPage.selectSize(selectedSize);
    }

    @And("User select {int} items")
    public void userSelectItems(int quantity) {
        productPage.selectQuantity(quantity);
    }

    @And("User add products to cart")
    public void userAddProductsToCart() {
        productPage.addItemsToCart();
    }

    @And("User go to checkout")
    public void userGoToCheckout() {
        productPage.proceedToCheckout();
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
    }

    @And("User confirm address on checkout {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userConfirmAddressOnCheckout(String alias, String address, String city, String zipPostalCode, String country, String phone, String firstName, String lastName) {
        String expectedAddress = firstName + " " + lastName + "\n" + address + "\n" + city + "\n" + zipPostalCode + "\n" + country + "\n" + phone;
        Assertions.assertEquals(alias, checkoutPage.getTextFromAddressAlias(), "Aliases should be the same");
        Assertions.assertEquals(expectedAddress, checkoutPage.getTextFromAddress(), "Addresses should be the same");
        checkoutPage.confirmAddressOnCheckout();
    }

    @And("User select type of delivery")
    public void userSelectTypeOfDelivery() {
        checkoutPage.selectSelfPickUpDelivery();
    }

    @And("User select type of payment")
    public void userSelectTypeOfPayment() {
        checkoutPage.selectPayByCheckPayment();
        checkoutPage.agreeToTermsOfService();
    }

    @And("User confirm order")
    public void userConfirmOrder() {
        checkoutPage.placeOrder();
    }

    @Then("User take a screenshot")
    public void userTakeAScreenshot() throws Exception {
        this.takeSnapShot(driver, "C:\\Users\\Bartek\\Pictures\\Selenium\\test.png");
    }

    @And("User go to order history details")
    public void userGoToOrderHistoryDetails() {
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        totalValue = orderConfirmationPage.getTotalValue();
        orderConfirmationPage.goToUserAccountPage();
        userAccountPage = new UserAccountPage(driver);
        userAccountPage.goToOrderHistoryAndDetails();
    }

    @And("User can see status of the order")
    public void userCanSeeStatusOfTheOrder() {
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        Assertions.assertEquals(totalValue, orderHistoryPage.getLatestOrderTotalPriceValue(), "Total price should be the same");
        Assertions.assertEquals("Awaiting check payment", orderHistoryPage.getLatestOrderStatus(), "Order status should be awaiting check payment");
    }

    public static void takeSnapShot(WebDriver driver, String fileWithPath) throws Exception {
//Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
//Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//Move image file to new destination
        File DestFile = new File(fileWithPath);
//Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

}



