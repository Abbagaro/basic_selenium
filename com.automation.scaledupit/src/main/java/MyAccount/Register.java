package MyAccount;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Register extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    public WebElement emailField;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordField;
    @FindBy(xpath = "//button[@name='login']")
    WebElement clickLogin;
    @FindBy(id = "reg_email")
    WebElement registrEmailAddressField;
    @FindBy(id = "reg_password")
    WebElement registerPasswordField;
    @FindBy(xpath = "//button[@name='register']")
    WebElement registerButton;



    public Register(){
        PageFactory.initElements(driver,this);
    }

    public void inputEmail(String email){

        sendKeysToElement(emailField, email);
    }
    public void inputPassword(String password){
        sendKeysToElement(passwordField,password);
    }
    public void clickLoginButton(){
        clickOnElement(clickLogin);
    }
    public HomePage login(String email,String password){
        inputEmail(email);
        inputPassword(password);
        clickLoginButton();

        return new HomePage();
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
    public Register doRegistration(String email, String password){
        inputRegistrationEmail(email);
        inputRegistrationPassword(password);
        clickRegisterButton();
        return new Register();
    }


}
