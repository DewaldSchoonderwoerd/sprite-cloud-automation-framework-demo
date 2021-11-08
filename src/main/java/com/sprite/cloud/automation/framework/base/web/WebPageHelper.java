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

    public void waitTillElementInvisible(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            LOG.info("failed to check web element invisibility");
        }
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
            WebElement webElement = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(webElement.isEnabled());
        } catch (Exception e) {
            LOG.error("Element is NOT enabled" + e.getMessage());
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

    public boolean verifyElementPresent(WebElement element) {
        try {
            LOG.info("Verifying Element : " + element);
            return (wait.until(ExpectedConditions.visibilityOf(element))).isDisplayed();
        } catch (Exception e) {
            LOG.error(element + " not displayed");
            return false;
        }
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

    private boolean isElementDisplayedOrEnabled(WebElement element) {
        if (element.isEnabled()) {
            LOG.info("Element is enabled [" + element + "]");
            return true;
        }
        LOG.warn("Element not enabled [" + element + "]");
        return false;
    }

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

    public void waitUntilInvisibilityOfElement(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[contains(@class,'loading-text')]"))));
            LOG.info(webElement + " was dismissed!!!");
        } catch (Exception e) {
            LOG.info(webElement + " error " + e.getMessage());
        }
    }

    public By getByTypeAndValue(String type, String value) {
        String inputType = type.toLowerCase();
        By by = By.id(value);

        if (inputType.equalsIgnoreCase(ID)) {
            by = By.id(value);
            LOG.info("finding element by id => " + value);

        } else if (inputType.equalsIgnoreCase(XPATH)) {
            by = By.xpath(value);
            LOG.info("finding element by xpath => " + value);

        } else if (inputType.equalsIgnoreCase(TEXT)) {
            by = By.xpath("//*[.='" + value + "']");
            LOG.info("finding element by text => " + value);

        } else if (inputType.equalsIgnoreCase(CONTAINS)) {
            by = By.xpath("//*[contains(.,'" + value + "')]");
            LOG.info("finding element by contains => " + value);
        } else if (inputType.equalsIgnoreCase(STARTS_WITH)) {
            by = By.xpath("//*[starts-with(text(),'" + value + "')]");
            LOG.info("finding element by starts-with => " + value);
        }
        return by;
    }
}
