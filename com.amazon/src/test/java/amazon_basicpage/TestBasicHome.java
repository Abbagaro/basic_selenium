package amazon_basicpage;

import base.BasePage;
import org.testng.annotations.Test;

public class TestBasicHome extends BasePage {

    @Test
    public void testAccessBasicHome() {
        AmnHomePage amnHomePage = new AmnHomePage();
        amnHomePage.navigateToBasicHome();
    }

    @Test
    public void testAmnHomePage() {
        AmnHomePage amnHomePage = new AmnHomePage();
        amnHomePage.navigateToAmnHomePage();
    }
}