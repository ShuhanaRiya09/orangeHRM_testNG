package testrunners;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OrangeHRMAllMenuPage;
import utils.Utils;

import java.io.IOException;

public class OrangeHRMAllMenupageTestRunner extends Setup {

    OrangeHRMAllMenuPage pimPage;
    OrangeHRMAllMenuPage dashBoardPage;
    OrangeHRMAllMenuPage directoryPage;


    @BeforeTest
    public void Login(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");

    }
    @Test(priority = 1)
    public void createNewEmployee() throws IOException, ParseException {
        pimPage = new OrangeHRMAllMenuPage(driver);
//        pimPage.leftMenubar.get(1).click();
//        pimPage.addButton.get(2).click();
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String userid = String.valueOf(faker.random().nextInt(1000,9999));
        String username= faker.name().username();


        // Generate a random password with specific criteria
        String password = faker.internet().password(8, 12, true, true, true);

        EmployeeModel model=new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUserid(userid);
        model.setUsername(username);
        model.setPassword(password);

        pimPage.createNewEmployee(model);

        Utils.saveEmployeeInfo(model);

        String headerTitleExpected = driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]")).getText();
        String headerTitleActual = "Personal Details";
        Assert.assertTrue(headerTitleExpected.contains(headerTitleActual));
        Allure.description("User created successfully");


    }

//    public void searchByEmployeeId(String employeeId){
//        dashBoardPage=new OrangeHRMAllMenuPage(driver);
//        leftMenubar.get(1).click();   //click PIM
//        textField.get(1).sendKeys(employeeId);  // employeeId
//        searchEmp.click();
//    }

    @Test(priority = 2)
    public void searchByEmployeeName() throws InterruptedException, IOException, ParseException {
        Thread.sleep(5000);
        directoryPage= new OrangeHRMAllMenuPage(driver);
        JSONArray empArray = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String firstName = empObj.get("firstName").toString();
        Thread.sleep(5000);
        directoryPage.searchByEmployeeName(firstName);
        String messageActual = driver.findElement(By.className("orangehrm-directory-card-header")).getText();
        Thread.sleep(2000);
        System.out.println(messageActual);
        Assert.assertTrue(messageActual.contains(firstName));

        Allure.description("Admin Successfully Search User by Employee Name");

    }

    @Test(priority = 3, description = "Admin successfully logs out", groups = "smoke")
    public void Logout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExcepted ="Login";
        Assert.assertEquals(textActual,textExcepted);

        Allure.description("Admin Successfully Logs out");
    }



}
