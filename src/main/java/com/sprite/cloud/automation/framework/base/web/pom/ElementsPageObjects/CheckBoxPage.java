package com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class CheckBoxPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(CheckBoxPage.class);

    public CheckBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/span[1]/label[1]/span[1]/*[1]")
    private WebElement homeCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[1]/span[1]/label[1]/span[1]/*[1]")
    private WebElement desktopCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[1]/ol[1]/li[1]/span[1]/label[1]/span[1]/*[1]")
    private WebElement notesCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[1]/ol[1]/li[2]/span[1]/label[1]/span[1]/*[1]")
    private WebElement commandsCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/span[1]/label[1]/span[1]/*[1]")
    private WebElement documentsCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[1]/span[1]/label[1]/span[1]/*[1]")
    private WebElement workspaceCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[1]/ol[1]/li[1]/span[1]/label[1]/span[1]/*[1]")
    private WebElement reactCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[1]/ol[1]/li[2]/span[1]/label[1]/span[1]/*[1]")
    private WebElement angularCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[1]/ol[1]/li[3]/span[1]/label[1]/span[1]/*[1]")
    private WebElement veuCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[2]/span[1]/label[1]/span[1]/*[1]")
    private WebElement officeCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[2]/ol[1]/li[1]/span[1]/label[1]/span[1]/*[1]")
    private WebElement publicCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[2]/ol[1]/li[2]/span[1]/label[1]/span[1]/*[1]")
    private WebElement privateCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[2]/ol[1]/li[3]/span[1]/label[1]/span[1]/*[1]")
    private WebElement classifiedCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[2]/ol[1]/li[4]/span[1]/label[1]/span[1]/*[1]")
    private WebElement generalCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[3]/span[1]/label[1]/span[1]/*[1]")
    private WebElement downloadsCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[3]/span[1]/label[1]/span[1]/*[1]")
    private WebElement wordFileCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[3]/ol[1]/li[2]/span[1]/label[1]/span[1]/*[1]")
    private WebElement excelFileCheckBox;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/span[1]/button[1]/*[1]")
    private WebElement homeToggleButton;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[1]/span[1]/button[1]/svg[1]/path[1]")
    private WebElement documentsToggleButton;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[1]/span[1]/button[1]/svg[1]/path[1]")
    private WebElement workSpaceToggleButton;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[2]/ol[1]/li[2]/span[1]/button[1]/*[1]")
    private WebElement officeToggleButton;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[1]/ol[1]/li[3]/span[1]/button[1]/*[1]")
    private WebElement downloadsToggleButton;


    public void selectCheckBox(String checkBoxText){
        switch (checkBoxText.trim().toUpperCase()){
            case "HOME" :
                clickOnCheckBox(homeCheckBox);
                break;
            case "DESKTOP" :
                clickOnCheckBox(desktopCheckBox);
                break;
            case "NOTES" :
                clickOnCheckBox(notesCheckBox);
                break;
            case "COMMANDS" :
                clickOnCheckBox(commandsCheckBox);
                break;
            default:
                Assert.fail("Checkbox " + checkBoxText + " does not exist");
        }
    }

    private void clickOnCheckBox(WebElement webElement){
        boolean isChecked = webElement.isSelected();

        if (!isChecked)
            webElement.click();

        Assert.assertTrue(webElement.isSelected(), webElement.getText() + " is not checked");
    }
}
