package amazon_basicpage;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmnHomePage extends BasePage {

    @FindBy(linkText = "Amazon Home")
    public WebElement amnHomePage;
    @FindBy(css = "Amazon.Basics")
    public WebElement basicHome;
    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[5]/a")
    public WebElement bathRoom;

    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[2]/a")
    public WebElement bedRoom;

    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[6]/a")
    public WebElement dailyLiving;

    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[7]/a")
    public WebElement garage;
    @FindBy(xpath = "//body/div[@id='a-page']/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/nav[1]/ul[1]/li[4]/a[1]")
    public WebElement livingRoom;

    @FindBy(xpath = "//*[@id=\"Header-xdprxhxktu\"]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/nav/ul/li[3]/a")
    public WebElement kitchen;


    public AmnHomePage() {
        PageFactory.initElements(driver, this);
    }

    public AmnHomePage navigateToAmnHomePage() {
        clickOnElement(amnHomePage);
        return new AmnHomePage();
    }

    public BasicHome navigateToBasicHome() {
        clickOnElement(basicHome);
        return new BasicHome();
    }

    public BathRoom navigateToBathRoom() {
        clickOnElement(bathRoom);
        return new BathRoom();
    }

    public BedRoom navigateToBedRoom() {
        clickOnElement(bedRoom);
        return new BedRoom();
    }

    public DailyLiving navigateToDailyRoom() {
        clickOnElement(dailyLiving);
        return new DailyLiving();
    }

    public Garage navigateToGarage() {
        clickOnElement(garage);
        return new Garage();
    }

    public Kitchen navigateToKitchen() {
        clickOnElement(kitchen);
        return new Kitchen();
    }
    public LivingRoom navigateTolivingRoom(){
        clickOnElement(livingRoom);
        return new LivingRoom();
    }
}
