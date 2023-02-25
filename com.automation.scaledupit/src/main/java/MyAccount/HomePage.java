package MyAccount;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private static final String URL ="https://automation.scaledupit.com";

    @FindBy(xpath = "//ul[@id='menu-primary-menu']//*[text()='Login/Register']")
    public WebElement loginOrRegisterButton;

    @FindBy(xpath = "//a[@class='my-account']")
    public WebElement myAccountButton;
    @FindBy(linkText = "Shop")
    public WebElement clickShopButton;


    public HomePage() {

        PageFactory.initElements(driver, this);

    }

    public HomePage clickMyAccountButton() {

        clickOnElement(myAccountButton);

        return new HomePage();

    }

    public LoginPage clickOnLoginButton() {
        clickOnElement(loginOrRegisterButton);
        return new LoginPage();
    }

    public Shop clickOnShopButton() {
        clickOnElement(clickShopButton);
        return new Shop();

    }
}