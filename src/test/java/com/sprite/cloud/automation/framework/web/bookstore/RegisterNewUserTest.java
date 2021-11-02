package com.sprite.cloud.automation.framework.web.bookstore;

import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.RegistrationPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;

public class RegisterNewUserTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterNewUserTest.class);

    private String url;

    private RegistrationPage registrationPage;

    @BeforeClass
    @Parameters({"environment"})
    private void setup(@Optional(QA) String environment){
        url = WebPropertyUtils.getWebUrl("tools", environment);

        driver.navigate().to(url + "/checkbox");
        LOG.info("Navigating to URL: " + url);

        registrationPage = new RegistrationPage(driver);
    }

    @Test(groups = {SMOKE, REGRESSION})
    public void yomama(){
        driver.navigate().to(url + "/register");

        registrationPage.selectReCaptcha();
    }

}