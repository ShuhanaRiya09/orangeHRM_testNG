package pages;

import config.EmployeeModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrangeHRMAllMenuPage {
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> leftMenubar;
    @FindBy(className = "oxd-button")
    public List<WebElement> addButton;    //save search,
    @FindBy(className = "oxd-input")
    public List<WebElement> textField;
    @FindBy(className = "oxd-switch-input")
    WebElement btnSwitch;

    @FindBy(className = "oxd-autocomplete-text-input")
    WebElement inputName;
    @FindBy(className = "orangehrm-left-space")
    WebElement searchEmp;

    WebDriver driver;


    public OrangeHRMAllMenuPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver,this);
    }

    public void createNewEmployee(EmployeeModel model){
        leftMenubar.get(1).click();
        addButton.get(2).click(); //click add button
        btnSwitch.click();
        textField.get(1).sendKeys(model.getFirstname());
        textField.get(3).sendKeys(model.getLastname());
        //textField.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        textField.get(4).sendKeys(model.getUserid());
        textField.get(5).sendKeys(model.getUsername());
        textField.get(6).sendKeys(model.getPassword());
        textField.get(7).sendKeys(model.getPassword());
        textField.get(5).sendKeys(model.getUsername());
        textField.get(6).sendKeys(model.getPassword());
        textField.get(7).sendKeys(model.getPassword()); //confirm password
        addButton.get(1).click();//save data
    }

    public void searchByEmployeeId(String employeeId){
        leftMenubar.get(1).click();   //click PIM
        textField.get(1).sendKeys(employeeId);  // employeeId
        searchEmp.click(); //click search
    }

    public void searchByEmployeeName(String firstname) throws InterruptedException {
        leftMenubar.get(8).click(); //click Directory
        Actions action = new Actions(driver);
        action.click(inputName);
        action.sendKeys(firstname).perform();
        Thread.sleep(5000);
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        searchEmp.click(); //click search
    }


}
