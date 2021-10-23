package com.sprite.cloud.automation.framework.web;

import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
import com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects.CheckBoxPage;
import com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects.ElementsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;

public class CheckBoxTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(CheckBoxTest.class);

    private String url;

    private ElementsPage elementsPage;
    private CheckBoxPage checkBoxPage;

    @BeforeClass
    @Parameters({"environment"})
    private void setup(@Optional(QA) String environment){
        url = WebPropertyUtils.getWebUrl("tools", environment);

        driver.navigate().to(url + "/checkbox");
        LOG.info("Navigating to URL: " + url);

        elementsPage = new ElementsPage(driver);
        checkBoxPage = new CheckBoxPage(driver);
    }


    @Test(groups = {SMOKE, REGRESSION})
    private void textBoxTest() {
        driver.navigate().to(url + "/elements");

        elementsPage.clickOnOption("Check Box");

        checkBoxPage.selectCheckBox("Home");
    }

}
