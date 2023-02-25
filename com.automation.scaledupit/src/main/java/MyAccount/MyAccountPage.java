package MyAccount;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends BasePage {

    public MyAccountPage() {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//strong[1]")
    public WebElement accountName;



    public String getAccountName(){

        return getTrimmedElementText(accountName);

    }
}
