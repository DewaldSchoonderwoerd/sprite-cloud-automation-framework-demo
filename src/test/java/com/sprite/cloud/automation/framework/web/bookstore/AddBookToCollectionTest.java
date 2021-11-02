package com.sprite.cloud.automation.framework.web.bookstore;

import com.github.javafaker.Faker;
import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.api.service.toolsqa.UserService;
import com.sprite.cloud.automation.framework.base.utilities.RandomValues;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.BookDetailPage;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.BookStorePage;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.LoginPage;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.ProfilePage;
import com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.modals.DeleteBookModal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;

public class AddBookToCollectionTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(AddBookToCollectionTest.class);

    private String username;
    private String password;

    private LoginPage loginPage;
    private ProfilePage profilePage;
    private BookStorePage bookStorePage;
    private BookDetailPage bookDetailPage;
    private DeleteBookModal deleteBookModal;

    @BeforeClass
    @Parameters({"environment"})
    private void setup(@Optional(QA) String environment){
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        bookStorePage = new BookStorePage(driver);
        bookDetailPage = new BookDetailPage(driver);
        deleteBookModal = new DeleteBookModal(driver);

        username = new Faker().funnyName().name();
        password = RandomValues.randomPassword();

        UserService.createNewUser(username, password);
    }

    @Test(groups = {SMOKE, REGRESSION})
    public void AddNewBookToCollection(){
        String bookTitle = "Git Pocket Guide";

        loginPage.goTo();
        loginPage.login(username, password);

        profilePage.goToBookStore();

        bookStorePage.selectBookByTitle(bookTitle);

        bookDetailPage.addBookToCollection();
        bookDetailPage.goBackToBookStore();

        profilePage.goToPage();

        profilePage.entryExistsOnTable(bookTitle);
        profilePage.removeEntryOnTable(bookTitle);

        deleteBookModal.clickOnOk();

        profilePage.entryDoesNotExistsOnTable(bookTitle);
    }


}
