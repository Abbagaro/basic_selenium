package amazon;




import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(linkText = "Amazon")
    public WebElement homePage;
    @FindBy(xpath = "//a[contains(text(),'Amazon Basics')]")
    public WebElement amazonBasics;
    @FindBy(xpath = "//*[@id=\"nav-xshop\"]/a[4]")
    public WebElement amazonOutlet;
    @FindBy(xpath = "//*[@id=\"nav-xshop\"]/a[8]")
    public WebElement coupons;
    @FindBy(xpath = "//*[@id=\"nav-xshop\"]/a[10]")
    public WebElement pharmacyPage;
    @FindBy(xpath = "//*[@id=\"nav-xshop\"]/a[7]")
    public WebElement uniqueFinds;
    @FindBy(xpath = "//*[@id=\"nav-xshop\"]/a[9]")
    public WebElement beautyPersonalCare;

    public HomePage(){
        PageFactory.initElements(driver,this);
    }
    public HomePage navigateToHomepage(){
        clickOnElement(homePage);
        return new HomePage().navigateToHomepage();
    }
    public AmazonBasics navigateToAmazonBasic(){
        clickOnElement(amazonBasics);
        return new AmazonBasics();
    }

    public AmazonOutlet navigateToAmazonOutlets() {
        clickOnElement(amazonOutlet);
        return  new AmazonOutlet();

    }
    public Coupons navigateToCoupons(){
        clickOnElement(coupons);
        return new Coupons();
    }
    public Pharmacy navigareToPharmacyPage(){
        clickOnElement(pharmacyPage);
        return new Pharmacy();
    }
    public UniqueFinds navigateToUniqueFinds(){
        clickOnElement(uniqueFinds);
        return new UniqueFinds();
    }
    public BeautyPersonalCare navigateToBeautyPersonalCare(){
        clickOnElement(beautyPersonalCare);
        return new BeautyPersonalCare();

    }

    public AmazonBasics navigateToAmazonHomePage(){
        clickOnElement(amazonBasics);
        return new AmazonBasics();
    }

}
