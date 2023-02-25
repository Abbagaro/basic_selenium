package MyAccount;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {


    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id= "password")
    public WebElement passwordField;

    @FindBy (name = "login")
    public WebElement logInButton;

    @FindBy(id = "reg_email")
    WebElement registrEmailAddressField;
    @FindBy(id = "reg_password")
    WebElement registerPasswordField;
    @FindBy(xpath = "//button[@name='register']")
    WebElement registerButton;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
    public void inputUsername(String username){

        sendKeysToElement(usernameField,username);
    }
    public void inputPassword(String password){

        sendKeysToElement(passwordField,password);
    }

    public void clickLogInButton(){

        clickOnElement(logInButton);
    }

    public MyAccountPage logIn(String email, String password){

        inputUsername(email);
        inputPassword(password);
        clickLogInButton();

        return new MyAccountPage();
    }
    public void inputRegistrationEmail(String email){
        sendKeysToElement(registrEmailAddressField,email);
    }
    public void inputRegistrationPassword(String password){
        sendKeysToElement(registerPasswordField,password);
    }
    public void clickRegisterButton(){
        clickOnElement(registerButton);
    }
    public MyAccountPage doRegistration(String email, String password){
        inputRegistrationEmail(email);
        inputRegistrationPassword(password);
        clickRegisterButton();
        return new MyAccountPage();
    }







}
