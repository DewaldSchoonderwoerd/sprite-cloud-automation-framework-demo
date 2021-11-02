package com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sprite.cloud.automation.framework.base.TestBase.environment;

public class LoginPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='login']")
    private WebElement loginButton;

    public void goTo(){
        String baseUrl = WebPropertyUtils.getWebUrl("tools", environment);

        driver.navigate().to(baseUrl + "/login");
        LOG.info("Navigating to URL: " + baseUrl);
    }

    public void login(String userName, String password){
        LOG.info("Attempting a valid login - user name: " + userName + ", password: " + password);
        sendKeys(userNameField, userName);
        sendKeys(passwordField, password);

        clickOn(loginButton);
        waitUntilInvisibilityOfElement(loginButton);

        LOG.info("Login was successful - " + userName + ", password: " + password);
    }
}
