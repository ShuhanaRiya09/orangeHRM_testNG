package testrunners;

import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.UserInfo;
import utils.Utils;


import java.io.IOException;



public class UserLoginTestRunner extends Setup {

    LoginPage loginPage;
    UserInfo userInfo;


        @Test(priority = 1, description = "User try to login with wrong creds")
        public void userLoginWithWrongCreds() throws IOException, ParseException {
            loginPage = new LoginPage(driver);
            JSONArray empList = Utils.readJSONList("./src/test/resources/employees.json");
            JSONObject empObj = (JSONObject) empList.get(empList.size()-1);

            String username = (String) empObj.get("username");
            String password = "4353df1s";
            loginPage.doLogin(username,password);

            String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
            Assert.assertTrue(textActual.contains("Invalid credentials"));
        }

        @Test(priority = 2, description = "New user can login successfully", groups = "smoke")
        public void userLogin() throws IOException, ParseException {
            loginPage = new LoginPage(driver);
            JSONArray empList = Utils.readJSONList("./src/test/resources/employees.json");
            JSONObject empObj = (JSONObject) empList.get(empList.size() - 1);
            String username = (String) empObj.get("username");
            String password = (String) empObj.get("password");
            loginPage.doLogin(username, password);


        }

        //@Test(priority = 3, description = "Save gender")
        public void saveUserGenderInfo() throws InterruptedException {
            userInfo = new UserInfo(driver);
            userInfo.menuItems.get(2).click();

            Utils.scroll(driver,0,700);

            Thread.sleep(3000);
            userInfo.selectGender();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.className("oxd-toast-container")).getText().contains("Successfully Updated"));

            //Allure.description("Gender Save Successfully");
        }

        //@Test(priority = 4,description = "Save blood group O+")
        public void saveUserBloodInfo() throws InterruptedException {
            userInfo = new UserInfo(driver);
            userInfo.menuItems.get(2).click();

            Thread.sleep(2000);

            Utils.scroll(driver,0,700);

            Thread.sleep(3000);
            userInfo.selectBloodType();
            Thread.sleep(5000);

            String message =driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
            Assert.assertTrue(message.contains("O+"));

            //Allure.description("Blood group Save Successfully");
        }

       // @Test(priority = 5, description = "User successfully logs out", groups = "smoke")
        public void Logout(){
            loginPage = new LoginPage(driver);
            loginPage.doLogout();
            String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
            String textExcepted ="Login";
            Assert.assertEquals(textActual,textExcepted);

            //Allure.description("User Logs out Successfully");
        }
}