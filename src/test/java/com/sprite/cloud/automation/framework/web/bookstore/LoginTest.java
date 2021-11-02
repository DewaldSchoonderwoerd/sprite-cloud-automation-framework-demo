package com.sprite.cloud.automation.framework.web.bookstore;

import com.github.javafaker.Faker;
import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.api.models.bookstore.account.v1.postAccount.response.PostAccountResponse;
import com.sprite.cloud.automation.framework.base.api.service.toolsqa.UserService;
import com.sprite.cloud.automation.framework.base.utilities.RandomValues;
import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.LoginPage;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.ProfilePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;

public class LoginTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(LoginTest.class);

    private String username;
    private String password;

    private LoginPage loginPage;
    private ProfilePage profilePage;

    @BeforeClass
    private void setup() {
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        username = new Faker().funnyName().name();
        password = RandomValues.randomPassword();

        UserService.createNewUser(username, password);
    }

    @Test(groups = {SMOKE, REGRESSION})
    public void LogIn() {
        loginPage.goTo();
        loginPage.login(username, password);

        profilePage.isVisible();
    }


}
