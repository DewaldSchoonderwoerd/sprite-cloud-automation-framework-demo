package com.sprite.cloud.automation.framework.base.web;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(WebPageHelper.class);

    public static final String ID = "ID";
    public static final String XPATH = "xpath";
    public static final String TEXT = "text";
    public static final String CONTAINS = "contains";
    public static final String STARTS_WITH = "starts-with";

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final int DRIVER_DEFAULT_WAIT_TIMEOUT = 20;
    protected static final int SLEEP_IN_BETWEEN_POLLS = 500;

    public WebPageHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_DEFAULT_WAIT_TIMEOUT), Duration.ofMillis(SLEEP_IN_BETWEEN_POLLS));
    }

    public WebPageHelper(WebDriver driver, long timeOutInSeconds, long sleepInMillis) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds), Duration.ofMillis(sleepInMillis));
    }


    public String getTitle() {
        LOG.info("Title : " + driver.getTitle());
        return driver.getTitle();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        delay(1);
    }

    public void scrollToElement(String txt) {
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + txt + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        delay(1);
    }

    public void scrollToElementClickByText(String txt) {
        WebElement element = getElementByType("text", txt);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

    }

    public void scrollDown(int n) {
        for (int i = 0; i <= n; i++) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,200)");
        }
    }

    public void scrollUp(int n) {
        for (int i = 0; i <= n; i++) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(200,0)");
        }
    }

    public void scrollToTop() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public void scrollToBottom() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void waitForPageLoad() {
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    public void waitForAlertToBePresent() {
        wait.ignoring(NoAlertPresentException.class)
            .until(ExpectedConditions.alertIsPresent());
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_DEFAULT_WAIT_TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException ex) {
            LOG.error("Element Not found within Time " + ex.getMessage());
        }
    }

    public void waitTillLoadingOver(int waitSec, String id) {
        LOG.info("Wait till loading is over for " + waitSec + " sec");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSec));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    public String getCurrentURL() {
        String URL = driver.getCurrentUrl();
        LOG.info("current URL is- " + URL);
        return URL;
    }

    public void sendKeys(WebElement element, String value) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    public void clickOn(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public WebElement select(String text) throws InterruptedException {
        WebElement element = getElementWithText(text);
        clickOn(element);
        Thread.sleep(5000);
        return element;
    }

    public WebElement getElementWithText(String text) {
        WebElement el = null;
        try {
            el = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        } catch (Exception e) {
            LOG.error("Element not found.. caught in exception " + e.getMessage());
        }
        return el;
    }

    public Boolean isCorrectURLDisplayed(String url) {
        return getCurrentURL().equalsIgnoreCase(url);
    }

    public void minimizeWindow() {
        Point p = new Point(0, 3000);
        driver.manage().window().setPosition(p);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }


    public void waitForTheElementToAppear(WebElement el) {
        try {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(30)).
                    pollingEvery(Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOf(el));
        } catch (Exception e) {
            LOG.info("failed to load the web element");
        }
    }

    public void waitAndCheckIfElementClickable(WebElement el) {
        try {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(30)).
                    pollingEvery(Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOf(el));
            wait.until(ExpectedConditions.elementToBeClickable(el));
            LOG.info("Element is clickable");
        } catch (Exception e) {
            LOG.info("failed to check web element clickable");
        }

    }

    public void waitTimer(long s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitTillElementInvisible(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            LOG.info("failed to check web element invisibility");
        }
    }

    public void waitForLoadingSpinner(WebElement webElement) {
        LOG.info("Waiting for Loading spinner");
        //waitUntilInvisibilityOfElement(webElement);
        waitUntilInvisibilityOfElementLocated("text", "Loading");
    }

    public void mouseMovementToElement(WebElement element) {
        Actions action = new Actions(driver);
        try {
            action.moveToElement(element).build().perform();
        } catch (Exception ex) {
            LOG.error("Element Not found" + ex.getMessage());
        }
    }

    public void clearField(WebElement element) {
        WebElement ele = findElementWhenReady(element);
        try {
            ele.clear();
        } catch (Exception e) {
            Assert.fail(element + " not showing");
        }
    }

    public void emptyField(WebElement element) {
        WebElement ele = findElementWhenReady(element);
        try {
            ele.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        } catch (Exception e) {
            Assert.fail(element + " not showing");
        }
    }

    public void verifyElementDisable(WebElement element) {
        try {
            WebElement webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(!webElement.isEnabled());
        } catch (Exception e) {
            LOG.error("Element is enabled" + e.getMessage());
        }
    }

    public void verifyElementEnable(WebElement element) {
        try {
            WebElement webElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(webElement.isEnabled());
        } catch (Exception e) {
            LOG.error("Element is NOT enabled" + e.getMessage());
        }
    }

    public void type(WebElement element, String value) {
        WebElement ele = findElementWhenReady(element);
        try {
            ele.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            ele.sendKeys(value);
            LOG.info(String.format("SendKey with value %1$s", value));
        } catch (Exception e) {
            Assert.fail(element + " element not displayed");
        }
    }

    public boolean isImageDisplayed(String imageName) {
        WebElement image = driver.findElement(By.xpath("//img[contains(@alt,'" + imageName + "')]"));
        new WebDriverWait(driver, 30).
                until(ExpectedConditions.visibilityOf(image));
        Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
        return ImagePresent;
    }

    public void verifyDropDown(WebElement dropDown) {
        int count = 1;
        Select s = new Select(dropDown);
        List<WebElement> op = s.getOptions();
        for (WebElement opt : op) {
            String options = opt.getText();
            LOG.info("dropDown options" + count + " : " + options);
            count++;
        }
    }

    public void selectDropDownByText(WebElement dropDownElement, String text) {
        WebElement ele = findElementWhenReady(dropDownElement);
        try {
            Select select = new Select(ele);
            select.selectByVisibleText(text);
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }


    public void verifyElementColor(WebElement element, String expected) {
        WebElement ele = findElementWhenReady(element);
        try {
            String color = ele.getCssValue("background-color");

            String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

            int hexValue1 = Integer.parseInt(hexValue[0]);
            hexValue[1] = hexValue[1].trim();
            int hexValue2 = Integer.parseInt(hexValue[1]);
            hexValue[2] = hexValue[2].trim();
            int hexValue3 = Integer.parseInt(hexValue[2]);

            String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);

            Assert.assertEquals(actualColor, expected, "color not matching ..!");
        } catch (Exception e) {
            Assert.fail(element + " color  not displayed");
        }

    }

    public void launchApplication(String url) {
        driver.manage().deleteAllCookies();
        driver.get(url);
    }


    public void waitForAjaxToComplete(long... second) {
        try {
            (new WebDriverWait(driver, 40))
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver d) {
                            JavascriptExecutor js =
                                    (JavascriptExecutor) driver;
                            return (Boolean) js
                                    .executeScript("return !!window.jQuery && window.jQuery.active == 0");

                        }
                    });
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    public void scrollAndClickUsingJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        executor.executeScript("arguments[0].click();", element);
    }


    public void clickElement(WebElement element) {
        try {
            findElementWhenReady(element).click();
        } catch (Exception e) {
            Assert.fail("{ " + element + "} not displayed");
        }
    }


    public boolean isVisible(WebElement element) {
        try {
            if (findElementWhenReady(element) != null) {
                return element.isDisplayed();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVisible(By by) {
        try {
            if (findElementWhenReady(by) != null) {
                return findElementWhenReady(by).isDisplayed();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyElementPresent(WebElement element) {
        try {
            LOG.info("Verifying Element : " + element);
            return (wait.until(ExpectedConditions.visibilityOf(element))).isDisplayed();
        } catch (Exception e) {
            LOG.error(element + " not displayed");
            return false;
        }
    }


    public boolean verifyElementPresent(WebElement element, int waitSec) {
        try {
            LOG.info("Verifying -> " + element);
            return ((new WebDriverWait(driver, Duration.ofSeconds(waitSec))).
                    until(ExpectedConditions.visibilityOf(element))).isDisplayed();
        } catch (Exception e) {
            LOG.error(element + " not displayed");
            return false;
        }
    }


    public String getText(WebElement element) {
        return findElementWhenReady(element).getText();
    }

    protected WebElement findElementWhenReady(WebElement element) {
        try {
            return wait.until((Function<WebDriver, WebElement>) driver -> {
                try {
                    if (isElementDisplayedOrEnabled(element)) {
                        return element;
                    }
                } catch (Exception e) {
                    LOG.warn("Not Able to match any element :" + element);
                }
                return null;
            });
        } catch (Exception e) {
            LOG.error("Exception on : " + element);
        }
        return null;
    }

    protected WebElement findElementWhenReady(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return driver.findElement(by);
        } catch (Exception e) {
            LOG.error("Exception on : " + by);
        }
        return null;
    }

    protected WebElement findElementWhenDisplayed(WebElement el) {
        try {
            return wait.until((Function<WebDriver, WebElement>) driver -> {
                try {
                    if (isElementDisplayed(el)) {
                        return el;
                    }
                } catch (Exception e) {
                    LOG.warn("Not Able to match any element :" + el);
                }
                return null;
            });
        } catch (Exception e) {
            LOG.error("Exception on : " + el);
        }
        return null;
    }

    /**
     * Determines the element ready state using rendered web element isDisplayed()
     * or element isEnabled() based on the driver type.
     */
    private boolean isElementDisplayedOrEnabled(WebElement element) {
        if (element.isEnabled()) {
            LOG.info("Element is enabled [" + element + "]");
            return true;
        }
        LOG.warn("Element not enabled [" + element + "]");
        return false;
    }

    /**
     * Determines the element is displayed state using rendered web element isDisplayed()
     */
    private boolean isElementDisplayed(WebElement el) {
        if (el.isEnabled()) {
            LOG.info("Element is displayed [" + el + "]");
            return true;
        }
        LOG.warn("Element not displayed [" + el + "]");
        return false;
    }

    /**
     * to navigate back
     */
    public void goBack() {
        driver.navigate().back();
    }

    public void navigateByUrl(String url) {
        driver.navigate().to(url);
    }

    public void navigateForward() {
        driver.navigate().forward();
    }


    /**
     * get xpath by text
     */
    public WebElement getElementByText(String text) {
        try {
            return getElementByType(CONTAINS, text);
        } catch (Exception e) {
            LOG.error("Element is not displayed " + e.getMessage());
        }
        return null;
    }

    public WebElement getElementByExactText(String text) {
        try {
            return getElementByType(TEXT, text);
        } catch (Exception e) {
            LOG.error("Element is not displayed " + e.getMessage());
        }
        return null;
    }

    public void clickElementByText(String text) {
        WebElement ele = getElementByText(text);
        if (ele != null) {
            ele.click();
        } else {
            Assert.fail("element is not displayed using text :" + text);
        }
    }

    public WebElement clickElementByExactText(String text) {
        WebElement ele = getElementByExactText(text);
        if (ele != null) {
            ele.click();
        } else {
            Assert.fail("element is not displayed using text :" + text);
        }

        return ele;
    }

    public void assertAllElementsIsVisible(List<WebElement> elements) {
        for (WebElement ele : elements) {
            LOG.info("Checking visibility of element for " + ele);
            Assert.assertTrue(verifyElementPresent(ele), "Element : " + ele + " not visible");
        }
    }

    public WebElement getElementByType(String type, String value) {
        try {
            return findElementWhenReady(getByTypeAndValue(type, value));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement getElementByXpath(String value) {
        return getElementByType("XPATH", value);
    }

    public By getByTypeAndValue(String type, String value) {
        String typpe = type.toLowerCase();
        By by = By.id(value);

        if (typpe.equalsIgnoreCase(ID)) {
            by = By.id(value);
            LOG.info("finding element by id => " + value);

        } else if (typpe.equalsIgnoreCase(XPATH)) {
            by = By.xpath(value);
            LOG.info("finding element by xpath => " + value);

        } else if (typpe.equalsIgnoreCase(TEXT)) {
            by = By.xpath("//*[.='" + value + "']");
            LOG.info("finding element by text => " + value);

        } else if (typpe.equalsIgnoreCase(CONTAINS)) {
            by = By.xpath("//*[contains(.,'" + value + "')]");
            LOG.info("finding element by contains => " + value);
        } else if (typpe.equalsIgnoreCase(STARTS_WITH)) {
            by = By.xpath("//*[starts-with(text(),'" + value + "')]");
            LOG.info("finding element by starts-with => " + value);
        }
        return by;
    }

    public boolean isVisibleByType(String type, String value) {
        try {
            return getElementByType(type, value).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void delay(double sl) {
        if ((int) sl > 60) {
            return;
        }
        long inMilliSec = (long) (sl * 1000);
        try {
            LOG.info("Wait " + sl + " sec");
            Thread.sleep(inMilliSec);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }
    }

    public void waitUntilInvisibilityOfElementLocated(String type, String value) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(getByTypeAndValue(type, value)));
            LOG.info(value + " was dismissed!!!");
        } catch (Exception e) {
            LOG.info(value + " error " + e.getMessage());
        }
    }

    public void waitUntilInvisibilityOfElement(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[contains(@class,'loading-text')]"))));
            LOG.info(webElement + " was dismissed!!!");
        } catch (Exception e) {
            LOG.info(webElement + " error " + e.getMessage());
        }
    }

    public byte[] attachScreenshotPNG() {
        if (driver != null) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } else {
            LOG.info("null driver");
            return null;
        }
    }

    public void clickElementWithCoordinates(WebElement element) {
        try {
            Point location = element.getLocation();
            int xPosition = location.getX();
            int yPosition = location.getY();
            Actions action = new Actions(driver);
            action.moveByOffset(xPosition, yPosition).click().build().perform();
        } catch (Exception e) {
            LOG.error(element + " not clickable " + e.getMessage());
        }
    }

    public void clickAndHoldElements(WebElement source, WebElement target) {
        try {
            Actions action = new Actions(driver);
            action.clickAndHold(source).moveToElement(target).click().build().perform();
        } catch (Exception e) {
            LOG.error(source + " not clickable " + e.getMessage());
        }
    }

    public void moveToElementAndClickByActions(WebElement source, WebElement target) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(source).moveToElement(target).click(target).build().perform();
        } catch (Exception e) {
            LOG.error(source + " not clickable " + e.getMessage());
        }
    }

    public void clickUsingActions(WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).click(element).perform();
    }
}
