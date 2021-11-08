package com.sprite.cloud.automation.framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static com.sprite.cloud.automation.framework.base.constants.BrowserTypes.*;
import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
import static com.sprite.cloud.automation.framework.base.constants.PlatformTypes.WEB;

public class TestBase {

    public static final Properties PROPERTIES = new Properties();
    private static final Logger LOG = LoggerFactory.getLogger(TestBase.class);
    public static WebDriver driver;
    public static String ENVIRONMENT;

    // TODO: Add to properties file
    private static final String remoteDriverUrl = "http://selenium-router:4444/wd/hub";

    private static final int implicitWaitTimeout = 20;
    private static final int scriptTimeout = 30;
    private static final int pageLoadTimeout = 60;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"environment"})
    public void setupGlobal(@Optional(QA) String environment) {
        LOG.info("Running against environment: " + environment);
        PROPERTIES.setProperty("environment", environment);
        TestBase.ENVIRONMENT = environment;
    }

    @Parameters({"platformType", "browserName"})
    @BeforeClass(alwaysRun = true)
    public void setupDriver(@Optional(WEB) String platformType, @Optional(CHROME) String browserName) throws Exception {
        LOG.info("platformType: " + platformType);
        LOG.info("browserName: " + browserName);
        if (platformType.toUpperCase().equals(WEB)) {
            switch (browserName.toUpperCase()) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("start-maximized");
                    chromeOptions.addArguments("disable-infobars");
                    chromeOptions.addArguments("--disable-extensions");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case CHROME_REMOTE:
                    ChromeOptions chromeRemoteOptions = new ChromeOptions();
                    driver = new RemoteWebDriver(new URL(remoteDriverUrl), chromeRemoteOptions);
                    break;
                case FIREFOX_REMOTE:
                    FirefoxOptions firefoxRemoteOptions = new FirefoxOptions();
                    driver = new RemoteWebDriver(new URL(remoteDriverUrl), firefoxRemoteOptions);
                    break;
                default:
                    throw new Exception("Unknown browser - " + browserName + "\n it either does not exist or needs to be set up.");
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeout));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(scriptTimeout));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
            driver.manage().window().maximize();
            LOG.info("Completed setting up Selenium Driver for browser: " + browserName);
        } else {
            LOG.info("API only tests");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriver() {
        if (driver != null) driver.quit();
        LOG.info("Selenium Driver closed");
    }

}
