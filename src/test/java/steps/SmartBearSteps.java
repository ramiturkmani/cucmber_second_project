package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.SmartBearLoginPage;
import pages.SmartBearOrdersPage;
import utils.Driver;

public class SmartBearSteps {
    WebDriver driver;
    SmartBearLoginPage smartBearLoginPage;
    SmartBearOrdersPage smartBearOrdersPage;

    @Before
    public void setup(){
        driver = Driver.getDriver();
        smartBearLoginPage = new SmartBearLoginPage();
        smartBearOrdersPage = new SmartBearOrdersPage();
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
            default:
                throw new NotFoundException("Button not propely defind in feature file!");
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
}
