package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import pages.SmartBearLoginPage;
import pages.SmartBearOrdersPage;
import utils.Driver;
import utils.Waiter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SmartBearSteps {
    WebDriver driver;
    SmartBearLoginPage smartBearLoginPage;
    SmartBearOrdersPage smartBearOrdersPage;
    String name = "John Doe", street = "1234 Banana St", city = "Minion", state = "IL", zip = "60845",
            cardNumber = "1234567890123456", cardExp = "08/24";
    ArrayList<String> fullOrder;

    @Before
    public void setup(){
        driver = Driver.getDriver();
        smartBearLoginPage = new SmartBearLoginPage();
        smartBearOrdersPage = new SmartBearOrdersPage();
        fullOrder = new ArrayList<>();
        fullOrder.add(name);
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String username) {
        smartBearLoginPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearLoginPage.passwordInputBox.sendKeys(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearLoginPage.loginButton.click();
    }

    @Then("user should see {string} Message")
    public void userShouldSeeMessage(String string) {
        Assert.assertEquals(string, smartBearLoginPage.loginErrorMessage.getText());
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable dataTable) {
        for (int i = 0; i < dataTable.asList().size(); i++) {
            Assert.assertEquals(dataTable.asList().get(i), smartBearOrdersPage.menuItems.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String button) {
        switch(button){
            case "Check All":
                smartBearOrdersPage.checkAll.click();
                break;
            case "Uncheck All":
                smartBearOrdersPage.uncheckAll.click();
                break;
            case "Process":
                smartBearOrdersPage.processButton.click();
                break;
            case "Delete Selected":
                smartBearOrdersPage.deleteSelectedButton.click();
                break;
            default:
                throw new NotFoundException("Button not properly defined in feature file!");
        }
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for (WebElement checkbox : smartBearOrdersPage.allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (WebElement checkbox : smartBearOrdersPage.allCheckboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String menuItem) {
        switch(menuItem){
            case "View all orders":
                smartBearOrdersPage.menuItems.get(0).click();
                break;
            case "Order":
                smartBearOrdersPage.menuItems.get(2).click();
                break;
            default:
                throw new NotFoundException("Menu item not properly defined in feature file!");
        }
    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String product) {
        Select select = new Select(smartBearOrdersPage.productDropdown);
        select.selectByVisibleText(product);
        fullOrder.add(product);
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int quantity) {
        smartBearOrdersPage.quantityInput.sendKeys(Keys.COMMAND + "a");
        smartBearOrdersPage.quantityInput.sendKeys(String.valueOf(quantity));
        fullOrder.add(String.valueOf(quantity));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        fullOrder.add(formatter.format(date));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        smartBearOrdersPage.nameInput.sendKeys(name);
        smartBearOrdersPage.streetInput.sendKeys(street);
        smartBearOrdersPage.cityInput.sendKeys(city);
        smartBearOrdersPage.stateInput.sendKeys(state);
        smartBearOrdersPage.zipInput.sendKeys(zip);
        fullOrder.add(street);
        fullOrder.add(city);
        fullOrder.add(state);
        fullOrder.add(zip);
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        smartBearOrdersPage.cardType.get(0).click();
        smartBearOrdersPage.cardNumberInput.sendKeys(cardNumber);
        smartBearOrdersPage.cardExpInput.sendKeys(cardExp);
        fullOrder.add(smartBearOrdersPage.cardTypeText.get(0).getText());
        fullOrder.add(cardNumber);
        fullOrder.add(cardExp);
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String tableName) {
        Assert.assertEquals(tableName, smartBearOrdersPage.tableName.getText());
        Assert.assertTrue(smartBearOrdersPage.mostRecentOrder.isDisplayed());
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {
        for (int i = 1; i < smartBearOrdersPage.mostRecentOrderInfo.size()-1; i++) {
            Assert.assertEquals(fullOrder.get(i-1), smartBearOrdersPage.mostRecentOrderInfo.get(i).getText());
        }
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String tableName) {
        Assert.assertEquals(tableName, smartBearOrdersPage.tableName.getText());
        try {
            Assert.assertTrue(smartBearOrdersPage.wholeTable.isDisplayed());
        }
        catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    @And("validate user sees {string} message")
    public void validateUserSeesMessage(String message) {
        Assert.assertEquals(message, smartBearOrdersPage.emptyOrderListMessage.getText());
    }
}
