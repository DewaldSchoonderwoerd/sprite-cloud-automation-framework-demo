package com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicPropertiesPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DynamicPropertiesPage.class);

    public DynamicPropertiesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement fullnameField;

}
