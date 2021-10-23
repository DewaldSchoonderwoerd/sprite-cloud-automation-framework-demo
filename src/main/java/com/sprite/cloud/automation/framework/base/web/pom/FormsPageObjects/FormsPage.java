package com.sprite.cloud.automation.framework.base.web.pom.FormsPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormsPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(FormsPage.class);

    public FormsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnOption(String optionText){
        clickElementByExactText(optionText);
    }
}
