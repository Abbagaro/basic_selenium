package amazon;

import base.BasePage;
import org.testng.annotations.Test;

public class TestBestDeals extends BasePage {
    @Test
    public void testBestDeals(){
        BestDeals bestDeals = new BestDeals();
        bestDeals.clickOnBestDeal();

    }
}
