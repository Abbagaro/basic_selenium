package TestAmazon;

import MyAccount.HomePage;
import base.BasePage;
import org.testng.annotations.Test;

public class TestAmazonBasics extends BasePage {
   // @Test
   /* public void testNavigateToBedRoom(){
        HomePage homePage = new HomePage();
        Climic amazonBasics =homePage.navigatToAmazonBasics();
        amazonBasics.bathRoom.click();
       // homePage.navigateToAmazoneHomePage();
    }*/
    @Test
    public void testAccessToGiftCardPage(){
        HomePage homepage = new HomePage();
       // homepage.navigateToGigtCardPage();
    }
}
