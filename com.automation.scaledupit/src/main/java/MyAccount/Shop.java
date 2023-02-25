package MyAccount;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Shop extends BasePage {

    @FindBy(xpath = "//*[@id=\"main\"]/ul/li[1]/a[1]/img")
    public WebElement clickProduct;


    public Shop(){
        PageFactory.initElements(driver,this);
    }


    public ItemPage productButton(){
        clickOnElement(clickProduct);
        return new ItemPage();
    }
   /* public void cartButton(){
       clickOnElement(clickShopButton);
    }*/


}
