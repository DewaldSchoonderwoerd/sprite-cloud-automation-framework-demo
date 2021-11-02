package com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class BookDetailPage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(BookDetailPage.class);

    public BookDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "")
    private WebElement isbnText;

    @FindBy(xpath = "")
    private WebElement titleText;

    @FindBy(xpath = "")
    private WebElement subTitleText;

    @FindBy(xpath = "")
    private WebElement authorText;

    @FindBy(xpath = "")
    private WebElement publisherText;

    @FindBy(xpath = "")
    private WebElement totalPagesText;

    @FindBy(xpath = "")
    private WebElement descriptionText;

    @FindBy(xpath = "")
    private WebElement webSiteText;

    @FindBy(xpath = "//button[contains(text(),'Back To Book Store')]")
    private WebElement backToBookStoreButton;

    @FindBy(xpath = "//button[contains(text(),'Add To Your Collection')]")
    private WebElement addToYourCollectionButton;

    public void addBookToCollection(){
        clickOn(addToYourCollectionButton);

        waitForAlertToBePresent();
        driver.switchTo().alert().accept();

        waitForElement(addToYourCollectionButton);
    }

    public void goBackToBookStore(){
        clickOn(backToBookStoreButton);
    }
}
