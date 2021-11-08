package com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sprite.cloud.automation.framework.base.TestBase.ENVIRONMENT;

public class RegistrationPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationPage.class);

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstnameField;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastnameField;

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
    private WebElement recaptcha;

    public void clickOnOption(String optionText){
        clickElementByExactText(optionText);
    }

    public void goTo(){
        String baseUrl = WebPropertyUtils.getWebUrl("tools", ENVIRONMENT);

        driver.navigate().to(baseUrl + "/register");
        LOG.info("Navigating to URL: " + baseUrl);
    }

    public void selectReCaptcha(){
//        TODO: @WIP
//        driver.switchTo().frame("//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/form[1]/div[6]/div[1]/iframe[1]");

//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark")));
//        element.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recaptcha));

        driver.findElement(By.cssSelector("div.recaptcha-checkbox-checkmark")).click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark"))).click();
    }
}
