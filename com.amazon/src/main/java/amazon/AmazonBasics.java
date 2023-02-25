package amazon;
import amazon_basicpage.*;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonBasics extends BasePage {

    private static final String URL = "https://www.amazon.com";

    @FindBy(linkText = "data-testid")
    public WebElement bedroom;

    @FindBy(linkText = "nav-item")
    public WebElement kitchen;

    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[4]/a")
    public WebElement livingRoom;

    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[4]/a")
    public WebElement bathRoom;

    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[6]")
    public WebElement dailyLiving;


    public AmazonBasics() {
        PageFactory.initElements(driver, this);
    }

   /* public BedRoom navigateToBedRoom() {
        clickOnElement(bedroom);
        return new BedRoom();*/

    public Kitchen navigateToKitchen(){
        clickOnElement(kitchen);
        return new Kitchen();
    }
    public LivingRoom navigateToLivingRoom(){
        clickOnElement(livingRoom);
        return new LivingRoom();
    }

    public DailyLiving navigateToDailyLiving() {
        clickOnElement(dailyLiving);
        return new DailyLiving();

    }
    }