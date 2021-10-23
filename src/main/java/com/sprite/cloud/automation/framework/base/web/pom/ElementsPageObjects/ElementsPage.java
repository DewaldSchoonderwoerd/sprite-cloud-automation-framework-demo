package com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebAutomationException;
import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementsPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(WebAutomationException.class);

    public ElementsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'Text Box')]")
    WebElement optionTextBox;

    @FindBy(xpath = "//span[contains(text(),'Check Box')]")
    WebElement optionCheckBox;

    @FindBy(xpath = "//span[contains(text(),'Radio Button')]")
    WebElement optionRadioButton;

    @FindBy(xpath = "//span[contains(text(),'Web Tables')]")
    WebElement optionWebTables;

    @FindBy(xpath = "//span[contains(text(),'Buttons')]")
    WebElement optionButtons;

    @FindBy(xpath = "//*[contains(text(),'Links')]\"")
    WebElement optionLinks;

    @FindBy(xpath = "//span[contains(text(),'Broken Links - Images')]")
    WebElement optionBrokenLinksImages;

    @FindBy(xpath = "//span[contains(text(),'Upload and Download')]")
    WebElement optionUploadAndDownload;

    @FindBy(xpath = "//span[contains(text(),'Dynamic Properties')]")
    WebElement optionDynamicProperties;

    public void clickOnOption(String optionText){
        clickElementByExactText(optionText);
    }

}
