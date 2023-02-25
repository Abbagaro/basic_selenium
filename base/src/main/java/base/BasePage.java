package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.BaseConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.DriverEventListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.Database;
import utils.ExcelData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.*;

public class BasePage {

    Map<Object, String> dbConfig = BaseConfig.databaseConfig();
    public static final String DATA_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "test" + File.separator + "resources" + File.separator + "test_data.xlsx";
    public static ExcelData excel;
    public static Database db;
    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static WebDriverWait syncWait;
    public static Wait<WebDriver> fluentWait;
    public static ExtentReports extent;
    public static JavascriptExecutor jsDriver;

    public BasePage() {
        dataInit();
        databaseInit();
    }

    // region Hooks
    @BeforeSuite(alwaysRun = true)
    public void reportSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    public void reportInit(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();

        ExtentTestManager.startTest(methodName);
        ExtentTestManager.getTest().assignCategory(className);
    }

    public void databaseInit() {
        String host = dbConfig.get(BaseConfig.DBProperties.HOST);
        String user = dbConfig.get(BaseConfig.DBProperties.USER);
        String password = dbConfig.get(BaseConfig.DBProperties.PASSWORD);
        String className = dbConfig.get(BaseConfig.DBProperties.DRIVER_CLASS);

        db = new Database(host, user, password, className);
    }

    public void dataInit() {
        excel = new ExcelData(DATA_PATH);
    }

    @Parameters({"driverConfigEnabled", "browser", "url"})
    @BeforeMethod (alwaysRun = true)
    //https://automation.scaledupit.com/
    public void driverSetup(@Optional("true") String driverConfigEnabled, @Optional("chrome") String browser, @Optional("https://amazon.com/") String url) {
        if (Boolean.parseBoolean(driverConfigEnabled)) {
            driverInit(browser);
            driver.get(url);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
    }

    @Parameters({"driverConfigEnabled"})
    @AfterMethod (alwaysRun = true)
    public void cleanUp(@Optional("true") String driverConfigEnabled) {
        if (Boolean.parseBoolean(driverConfigEnabled)) {
            driver.close();
            driver.quit();
        }
    }

    @Parameters({"driverConfigEnabled"})
    @AfterMethod(alwaysRun = true)
    public void afterEachTestMethod(ITestResult testResult, @Optional("true") String driverConfigEnabled) {
        ExtentTest test = ExtentTestManager.getTest();
        String testName = testResult.getName();
        int testStatus = testResult.getStatus();

        test.getTest().setStartedTime(getTime(testResult.getStartMillis()));
        test.getTest().setEndedTime(getTime(testResult.getEndMillis()));

        for (String group : testResult.getMethod().getGroups()) {
            test.assignCategory(group);
        }
        if (testStatus == ITestResult.FAILURE) {
            if (Boolean.parseBoolean(driverConfigEnabled)) {
                captureScreenshot(driver, testName);
            }
            test.log(LogStatus.FAIL, "TEST CASE FAILED: " + testName);
            test.log(LogStatus.FAIL, testResult.getThrowable());

        } else if (testStatus == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "TEST CASE SKIPPED: " + testName);
        } else if (testStatus == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "TEST CASE PASSED: " + testName);
        }

        ExtentTestManager.endTest();
        extent.flush();
    }

    // endregion

    // region Selenium API
    public WebElement getVisibleElement(By by) {

        WebElement element;

        element = driver.findElement(by);

        try {
            fluentWait.until(ExpectedConditions.visibilityOf(element));
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e2) {
            element = driver.findElement(by);
        }
        return element;
    }

    public WebElement getClickableElement(By by) {
        WebElement element;

        element = driver.findElement(by);

        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e2) {
            element = driver.findElement(by);
        }
        return element;
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);

        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }

    public String getTrimmedElementText(WebElement element) {
        String text = "";
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        text = element.getText().trim();

        if (text.equals("")) {
            text = element.getAttribute("innerHTML").trim();
        }

        return text;
    }

    public void clickOnElement(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeysToElement(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public void clearSendKeysToElement(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(keys);
    }

    public void selectFromDropdownByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void selectFromDropdownByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectFromDropdownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void clickOnElementFromList(List<WebElement> elements, int index) {

        try {
            clickOnElement(elements.get(index));
        } catch (IndexOutOfBoundsException e) {
            clickOnElement(elements.get(elements.size()-1));
        }

    }

    public void jsClickOnElementFromList(List<WebElement> elements, int index) {

        try {
            jsClickOnElement(elements.get(index));
        } catch (IndexOutOfBoundsException e) {
            jsClickOnElement(elements.get(elements.size() - 1));
        }

    }

    public void safeClickOnElementFromList(List<WebElement> elements, int index) {

        try {
            safeClickOnElement(elements.get(index));
        } catch (IndexOutOfBoundsException e) {
            safeClickOnElement(elements.get(elements.size() - 1));
        }

    }

    public void pressEnter(){

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public boolean isElementVisible(WebElement element) {
        try {
            fluentWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isElementInvisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    public void switchToParentFrame() {
        driver.switchTo().defaultContent();
    }

    public void switchToFrameByElement(WebElement frame) {
        webDriverWait.until(ExpectedConditions.visibilityOf(frame));
        driver.switchTo().frame(frame);
    }

    public void switchToTab() {
        String parentHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    // region JavaScriptExecutor Methods
    // TODO - Unit test and refactor as needed
    public String jsGetTrimmedElementText(WebElement element) {
        jsDriver = (JavascriptExecutor) (driver);
        String query = "arguments[0].getPropertyValue('innerHTML');";

        return jsDriver.executeScript(query, element).toString();
    }

    // TODO - Unit test and refactor as needed
    public WebElement findElementByXPathJS(String xPath) {
        jsDriver = (JavascriptExecutor) (driver);
        String query = String.format("document.getElement(By.xpath(\"%s\")", xPath);
        return (WebElement) (jsDriver.executeScript(query));
    }

    public void jsClickOnElement(WebElement element) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].click();", element);
    }

    public void jsScroll(int numOfPixels) {

        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("window.scrollBy(0," + numOfPixels + ")", "");

    }

    public void safeClickOnElement(WebElement element) {
        try {
            clickOnElement(element);
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            System.out.println("Unable to click element - trying again with javascript click");
            jsClickOnElement(element);
        } catch (TimeoutException | ElementNotVisibleException e) {
            System.out.println("Unable to locate element - trying again with javascript click");
            jsClickOnElement(element);
        }
    }

    public WebElement setElementAttributeValue(String attribute, String value, By by) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", driver.findElement(by));

        return driver.findElement(by);
    }

    public String getElementAttributeValue(String attribute, WebElement element){

        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attribute);

    }

    // endregion

    // endregion

    // region Helper Methods
    private static void driverInit(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            String fileDownloadPath = "C:" + File.separator + "Users" + File.separator + "gutii" + File.separator + "Downloads";
            Map<String, Object> prefsMap = new HashMap<String, Object>();
            prefsMap.put("profile.default_content_settings.popups", 0);
            prefsMap.put("download.default_directory", fileDownloadPath);

            ChromeOptions option = new ChromeOptions();
            option.setExperimentalOption("prefs", prefsMap);
            option.addArguments("--test-type");
            option.addArguments("--disable-extensions");

            driver = new ChromeDriver(option);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        WebDriverListener listener = new DriverEventListener();
        driver = new EventFiringDecorator(listener).decorate(driver);

    }

    private static void captureScreenshot(WebDriver driver, String testName) {
        String absPath = System.getProperty("user.dir");
        String screenshotFileName = "screenshot_" + testName + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File(absPath + File.separator + "src" + File.separator + "test"
                + File.separator + "reports" + File.separator + screenshotFileName);

        try {
            FileHandler.copy(screenshot, screenshotFile);
            System.out.println("SCREENSHOT TAKEN");
        } catch (Exception e) {
            System.out.println("ERROR TAKING SCREENSHOT: " + e.getMessage());
        }
    }

    private static void captureFullScreenshot(WebDriver driver, String testName) {
        String absPath = System.getProperty("user.dir");
        String fullScreenshotFileName = "full_screenshot_" + testName + ".png";

        File fullScreenshot = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
        File fullScreenshotFile = new File(absPath + File.separator + "src" + File.separator + "test"
                + File.separator + "reports" + File.separator + fullScreenshotFileName);

        try {
            FileHandler.copy(fullScreenshot, fullScreenshotFile);
            System.out.println("FULL SCREENSHOT TAKEN");
        } catch (Exception e) {
            System.out.println("ERROR TAKING FULL SCREENSHOT: " + e.getMessage());
        }
    }

    private static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    // endregion

}
