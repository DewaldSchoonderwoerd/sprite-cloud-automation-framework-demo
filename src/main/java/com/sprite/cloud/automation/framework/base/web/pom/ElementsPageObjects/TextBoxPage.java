package com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

public class TextBoxPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(TextBoxPage.class);

    public TextBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement fullnameField;

    @FindBy(xpath = "//input[@id='userEmail']")
    private WebElement emailField;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    private WebElement currentAddressField;

    @FindBy(xpath = "//textarea[@id='permanentAddress']")
    private WebElement permanentAddressField;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//p[@id='name']")
    private WebElement submitNameText;

    @FindBy(xpath = "//p[@id='email']")
    private WebElement submitEmailText;

    @FindBy(xpath = "//p[@id='currentAddress']")
    private WebElement submitCurrentAddressText;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    private WebElement submitPermanentAddressText;


    public void completeFields(String fullName, String email, String currentAddress, String permanentAddress){
       // Fills in the fields on all text box
        sendKeys(fullnameField, fullName);
        sendKeys(emailField, email);
        sendKeys(currentAddressField, currentAddress);
        sendKeys(permanentAddressField, permanentAddress);

        clickElement(submitButton);

        // Finds the text values in the submitted text fields
        String submittedName = getText(submitNameText).split(":")[1];
        String submittedEmail = getText(submitEmailText).split(":")[1];
        String submittedCurrentAddress = getText(submitCurrentAddressText).split(":")[1];
        String submittedPermanentAddress = getText(submitPermanentAddressText).split(":")[1];

        // Asserts the submitted values against the entry values
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(submittedName, fullName);
        softAssert.assertEquals(submittedEmail, email);
        softAssert.assertEquals(submittedCurrentAddress, currentAddress);
        softAssert.assertEquals(submittedPermanentAddress, permanentAddress);
        softAssert.assertAll();
    }
}
