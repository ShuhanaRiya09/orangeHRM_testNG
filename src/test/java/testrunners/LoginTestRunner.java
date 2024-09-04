package testrunners;

import config.Setup;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;


public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Admin tries to login with wrong creds")
    public void LoginWithWrongCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("admin","12345");
        String textActual=driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected="Invalid credentials";
        Assert.assertTrue(textActual.contains(textExpected));

    }
    @Test(priority = 2, description = "Admin tries to login with correct creds")
    public void LoginWithCorrectCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("admin","admin123");

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());
        softAssert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        softAssert.assertAll();
        Allure.description("Admin login successfully");

    }


}
