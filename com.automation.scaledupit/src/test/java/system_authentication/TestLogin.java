package system_authentication;

import MyAccount.HomePage;
import MyAccount.LoginPage;
import MyAccount.MyAccountPage;
import base.BasePage;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends BasePage {

    @Test (groups = {"smoke", "authentication"})
    public void testLogin(){

        HomePage homePage = new HomePage();

        LoginPage loginPage = homePage.clickOnLoginButton();

        MyAccountPage myAccountPage = loginPage.logIn ("1oromia@gmail.com","1ayaa2ja");

        Assert.assertEquals(myAccountPage.getAccountName(), "1oromia");

    }
}
