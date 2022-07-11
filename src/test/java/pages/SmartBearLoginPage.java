package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class SmartBearLoginPage {
    public SmartBearLoginPage (){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "ctl00_MainContent_username")
    public WebElement usernameInputBox;

    @FindBy(id = "ctl00_MainContent_password")
    public WebElement passwordInputBox;

    @FindBy(css = "input[type='submit']")
    public WebElement loginButton;

    @FindBy(className = "error")
    public WebElement loginErrorMessage;
}
