package amazon_basicpage;

import base.BasePage;
import org.testng.annotations.Test;

public class TestamnHome extends BasePage {

    @Test
    public void testAccessBasicHome() {
        AmnHomePage amnHomePage = new AmnHomePage();
        amnHomePage.navigateToBasicHome();
    }

    @Test
    public void testAmnHomePage() {
        AmnHomePage amnHomePage  = new AmnHomePage();
        amnHomePage.navigateToAmnHomePage();

    }
    @Test
    public void TestBathRoom(){
        AmnHomePage amnHomePage  = new AmnHomePage();
        amnHomePage.navigateToBathRoom();

    }
    @Test
    public void TestBedRoom(){
        AmnHomePage amnHomePage  = new AmnHomePage();
        amnHomePage.navigateToBedRoom();
    }
    @Test
    public void TestDailyroom(){
        AmnHomePage amnHomePage  = new AmnHomePage();
        amnHomePage.navigateToDailyRoom();
    }
    @Test
    public void TestGarage(){
        AmnHomePage amnHomePage  = new AmnHomePage();
        amnHomePage.navigateToGarage();
    }
    @Test
    public void TestKitchen(){
        AmnHomePage amnHomePage  = new AmnHomePage();
        amnHomePage.navigateToKitchen();
    }
    @Test
    public void TestLivingRoom(){
        AmnHomePage amnHomePage  = new AmnHomePage();
        amnHomePage.navigateTolivingRoom();
    }
}
