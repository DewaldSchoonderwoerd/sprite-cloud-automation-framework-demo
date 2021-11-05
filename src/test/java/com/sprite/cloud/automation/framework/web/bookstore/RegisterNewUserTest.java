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
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.*;

public class RegisterNewUserTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterNewUserTest.class);

    private String url;

    private RegistrationPage registrationPage;

    @BeforeClass(alwaysRun = true)
    @Parameters({"environment"})
    private void setup(@Optional(QA) String environment){
        LOG.info("Navigating to URL: " + url);

        registrationPage = new RegistrationPage(driver);
    }

    @Test(groups = {WIP})
    public void registerUser(){
        registrationPage.goTo();

        registrationPage.selectReCaptcha();
    }

}
