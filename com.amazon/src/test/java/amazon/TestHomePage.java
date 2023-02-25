package amazon;

import base.BasePage;
import org.testng.annotations.Test;

public class TestHomePage extends BasePage {

    @Test
    public void testAccessToAmazonBasic(){
        HomePage homePage = new HomePage();
        homePage.navigateToAmazonBasic();
    }
    @Test
    public void testAmazonHomePage(){
        HomePage homePage = new HomePage();
        homePage.navigateToAmazonHomePage();

    }
    @Test
    public void testAmazonOutlet(){
        HomePage homePage = new HomePage();
        homePage.navigateToAmazonOutlets();
    }
    @Test
    public void testBeauty_PersonalCare(){
        HomePage homePage = new HomePage();
        homePage.navigateToBeautyPersonalCare();


    }
}
