package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class SmartBearOrdersPage {
    public SmartBearOrdersPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#ctl00_menu>li")
    public List<WebElement> menuItems;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAll;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    public WebElement uncheckAll;

    @FindBy(css = ".SampleTable td:nth-child(1)>input")
    public List<WebElement> allCheckboxes;
}
