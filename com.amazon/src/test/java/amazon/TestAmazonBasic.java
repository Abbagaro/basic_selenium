package amazon;


import base.BasePage;
import org.checkerframework.checker.units.qual.A;
import org.testng.annotations.Test;

public class TestAmazonBasic extends BasePage {

    @Test
    public void testAccessToAmazonBasic() {
        HomePage homePage = new HomePage();
        homePage.navigateToAmazonBasic();
    }

    @Test
    public void testAmazonHomePage() {
        HomePage homePage = new HomePage();
        homePage.navigateToAmazonHomePage();

    }
}