package system_authentication;

import MyAccount.HomePage;
import MyAccount.LoginPage;
import MyAccount.MyAccountPage;
import MyAccount.Register;
import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TestRegister extends BasePage {
    @Test//(groups = {"smoke","login"})

    public void testRegister(){

        HomePage homePage = new HomePage();

        LoginPage loginPage = homePage.clickOnLoginButton();

        MyAccountPage myAccountPage = loginPage.doRegistration ("bbagaro@gmail.com","1Ayaa2ja222");

       // Assert.assertEquals(myAccountPage.getAccountName(),"Hello bbagaro (not bbagaro ");

    }

}
