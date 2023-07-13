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


    @And("User goes to login page")
    public void userGoToLoginPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signInWithPage();
    }


    @And("User logs in using {string} and {string}")
    public void userLogInUsingAnd(String email, String password) {
        AuthPage authPage = new AuthPage(driver);
        authPage.loginAs(email, password);
    }


    @And("User goes to addresses page by addresses tile")
    public void userGoToAddressesPage() {
        userAccountPage = new UserAccountPage(driver);
        userAccountPage.goToUsersAddressesPageByTile();
    }

    @And("User goes to addresses page by footer")
    public void userGoToAddressesPageByFooter() {
        userAccountPage = new UserAccountPage(driver);
        userAccountPage.goToUsersAddressesPageByFooter();
    }


    @And("User can see there is already one address")
    public void userCanSeeThereIsAlreadyOneAddress() {
        addressesPage = new AddressesPage(driver);
        Assertions.assertTrue(addressesPage.firstAddressIsVisible(), "There should be one address already");
    }


    @When("User creates new address")
    public void userCreateNewAddress() {
        addressesPage = new AddressesPage(driver);
        addressesPage.createNewAddress();
    }


    @And("User enters new address {string}, {string}, {string}, {string}, {string}, {string}")
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

    @Then("User can see first address")
    public void userCanSeeFirstAddress() {
        Assertions.assertTrue(addressesPage.firstAddressIsVisible(), "New address tile should be visible");
        Assertions.assertEquals("Address successfully added!", addressesPage.getTextAddressSuccessAlert());
    }

    @And("User verifies created address {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userVerifyCreatedAddress(String alias, String address, String city, String zipPostalCode, String country, String phone, String firstName, String lastName) {
        String expectedAddress = firstName + " " + lastName + "\n" + address + "\n" + city + "\n" + zipPostalCode + "\n" + country + "\n" + phone;
        Assertions.assertEquals(alias, addressesPage.getTextFromNewAddressAlias(), "Aliases should be the same");
        Assertions.assertEquals(expectedAddress, addressesPage.getNewAddressAsText(), "Addresses should be the same");
    }

    @And("User verifies first address {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userVerifyFirstAddress(String alias, String address, String city, String zipPostalCode, String country, String phone, String firstName, String lastName) {
        String expectedAddress = firstName + " " + lastName + "\n" + address + "\n" + city + "\n" + zipPostalCode + "\n" + country + "\n" + phone;
        Assertions.assertEquals(alias, addressesPage.getTextFromFirstAddressAlias(), "Aliases should be the same");
        Assertions.assertEquals(expectedAddress, addressesPage.getFirstAddressAsText(), "Addresses should be the same");
    }

    @And("User removes new address")
    public void userRemovesNewAddress() {
        addressesPage.removeNewAddress();
    }


    @And("User can see the address was removed")
    public void userCanSeeTheAddressWasRemoved() {
        Assertions.assertFalse(addressesPage.newAddressIsVisible(), "New address tile should not be visible");
        Assertions.assertEquals(addressesPage.getTextAddressSuccessAlert(), "Address successfully deleted!");
    }


    @And("User logs out")
    public void userLogsOut() {
        addressesPage.userLogOut();
    }

    @And("User closes the browser")
    public void userCloseTheBrowser() {
        driver.quit();
    }


    private ProductPage productPage;

    private CheckoutPage checkoutPage;

    @And("User goes to main page")
    public void userGoToMainPage() {
        userAccountPage = new UserAccountPage(driver);
        userAccountPage.returnToMainPage();
    }

    @And("User chooses Hummingbird Printed Sweater on main page")
    public void userChooseHummingBirdPrintedSweater() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectHummingbirdPrintedSweater();
        productPage = new ProductPage(driver);
    }

    @And("User checks discount {string}")
    public void userCheckDiscount(String discount) {
        Assertions.assertEquals(discount, productPage.getDiscountText(), "Discount should be" + discount);
    }

    @And("User selects size {string}")
    public void userSelectSize(String selectedSize) {
        productPage.selectSize(selectedSize);
    }

    @And("User selects {int} items")
    public void userSelectItems(int quantity) {
        productPage.selectQuantity(quantity);
    }

    @And("User adds products to cart")
    public void userAddProductsToCart() {
        productPage.addItemsToCart();
    }

    @And("User goes to checkout")
    public void userGoToCheckout() {
        productPage.proceedToCheckout();
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
    }

    @And("User confirms address on checkout {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userConfirmAddressOnCheckout(String alias, String address, String city, String zipPostalCode, String country, String phone, String firstName, String lastName) {
        String expectedAddress = firstName + " " + lastName + "\n" + address + "\n" + city + "\n" + zipPostalCode + "\n" + country + "\n" + phone;
        Assertions.assertEquals(alias, checkoutPage.getTextFromAddressAlias(), "Aliases should be the same");
        Assertions.assertEquals(expectedAddress, checkoutPage.getTextFromAddress(), "Addresses should be the same");
        checkoutPage.confirmAddressOnCheckout();
    }

    @And("User selects type of delivery")
    public void userSelectTypeOfDelivery() {
        checkoutPage.selectSelfPickUpDelivery();
    }

    @And("User selects type of payment")
    public void userSelectTypeOfPayment() {
        checkoutPage.selectPayByCheckPayment();
        checkoutPage.agreeToTermsOfService();
    }

    @And("User confirms order")
    public void userConfirmOrder() {
        checkoutPage.placeOrder();
    }

    @Then("User takes a screenshot")
    public void userTakeAScreenshot() throws Exception {
        this.takeSnapShot(driver, "C:\\Users\\Bartek\\Pictures\\Selenium\\test.png");
    }

    @And("User goes to order history details")
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



