package com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects.modals;

import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteBookModal extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteBookModal.class);

    public DeleteBookModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='example-modal-sizes-title-sm']")
    private WebElement titleText;

    @FindBy(xpath = "//div[contains(text(),'Do you want to delete this book?')]")
    private WebElement descriptionText;

    @FindBy(xpath = "//button[@id='closeSmallModal-ok']")
    private WebElement okButton;

    @FindBy(xpath = "//button[@id='closeSmallModal-cancel']")
    private WebElement cancelButton;

    public void clickOnOk(){
        LOG.info("Clicking on the OK button");

        clickOn(okButton);

        waitUntilInvisibilityOfElement(titleText);
    }
}
