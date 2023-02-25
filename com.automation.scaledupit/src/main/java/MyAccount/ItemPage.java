package MyAccount;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BasePage {
    @FindBy(xpath = "//button[@name='add-to-cart']")
    public WebElement addToCart;
    @FindBy(xpath = "//div[@role='alert']")
    public WebElement addToCartMessage;
    public ItemPage() {
        PageFactory.initElements(driver,this);
    }
    public String  getAddToCartMessage(){
       return getTrimmedElementText(addToCartMessage);


    }
    public void clicAddToCartButton(){
        clickOnElement(addToCart);
    }


}

