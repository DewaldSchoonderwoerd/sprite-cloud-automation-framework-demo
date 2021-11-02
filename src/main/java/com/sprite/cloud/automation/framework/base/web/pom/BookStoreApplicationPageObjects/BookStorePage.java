package com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookStorePage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(BookStorePage.class);

    public BookStorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='searchBox']")
    private WebElement searchField;

    @FindBy(xpath = "//span[@id='basic-addon2']")
    private WebElement searchButton;

    public void selectBookByTitle(String bookTitle){
        sendKeys(searchField, bookTitle);
        clickOn(searchButton);

        clickElementByExactText(bookTitle);
    }

}
