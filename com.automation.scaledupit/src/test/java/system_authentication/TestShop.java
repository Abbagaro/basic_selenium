package system_authentication;

import MyAccount.HomePage;
import MyAccount.ItemPage;
import MyAccount.Shop;
import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class TestShop extends BasePage {

    @Test(groups = {"smoke", "authentication"})

    public void TestShop(){
        HomePage homePage= new HomePage();

        Shop shop =  homePage.setClickShopButton();
        ItemPage itemPage =shop.productButton();
        itemPage.clicAddToCartButton();

        String expected="View cart"+"\n“Bag Collection” has been added to your cart.";

        Assert.assertEquals(itemPage.getAddToCartMessage(),expected);




    }

}
