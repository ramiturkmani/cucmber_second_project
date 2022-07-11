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

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productDropdown;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement nameInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement streetInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement cityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement stateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipInput;

    @FindBy(css = ".RadioList input")
    public List<WebElement> cardType;

    @FindBy(css = ".RadioList label")
    public List<WebElement> cardTypeText;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumberInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement cardExpInput;

    @FindBy(className = "btn_light")
    public WebElement processButton;

    @FindBy(css = ".content>h2")
    public WebElement tableName;

    @FindBy(css = ".SampleTable tr:nth-child(2)")
    public WebElement mostRecentOrder;

    @FindBy(css = ".SampleTable tr:nth-child(2)>td")
    public List<WebElement> mostRecentOrderInfo;

    @FindBy(className = "btnDeleteSelected")
    public WebElement deleteSelectedButton;

    @FindBy(className = "SampleTable")
    public WebElement wholeTable;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement emptyOrderListMessage;
}
