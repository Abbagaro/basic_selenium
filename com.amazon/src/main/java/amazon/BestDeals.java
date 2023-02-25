package amazon;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BestDeals extends BasePage {

    @FindBy(xpath = "//a[@href='/gp/bestsellers/?ref_=nav_cs_bestsellers']")
    WebElement bestDeals;

    public BestDeals(){
        PageFactory.initElements(driver,this);
    }
    public void clickOnBestDeal(){
        bestDeals.click();
    }

}

