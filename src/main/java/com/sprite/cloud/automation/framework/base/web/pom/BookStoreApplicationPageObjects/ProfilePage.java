package com.sprite.cloud.automation.framework.base.web.pom.BookStoreApplicationPageObjects;

import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.web.WebPageHelper;
import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

import static com.sprite.cloud.automation.framework.base.TestBase.environment;

public class ProfilePage extends WebPageHelper {

    private static final Logger LOG = LoggerFactory.getLogger(ProfilePage.class);

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Log out')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//input[@id='searchBox']")
    private WebElement searchField;

    @FindBy(xpath = "//span[@id='basic-addon2']")
    private WebElement searchButton;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/form[1]/div[6]/div[1]/iframe[1]")
    private WebElement pageField;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/span[2]/select[1]")
    private WebElement rowsDropDownMenu;

    @FindBy(xpath = "//button[@id='gotoStore']")
    private WebElement goToBookStoreButton;

    @FindBy(xpath = "//button[contains(text(),'Delete Account')]")
    private WebElement deleteAccountButton;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[3]/button[1]")
    private WebElement deleteAllBooksButton;

    public void goToPage(){
        String baseUrl = WebPropertyUtils.getWebUrl("tools", environment);

        driver.navigate().to(baseUrl + "/profile");
        LOG.info("Navigating to URL: " + baseUrl);
    }

    public void isVisible() {
        isVisible(logoutButton);
        getCurrentURL();
        Assert.assertEquals(getCurrentURL(), "https://demoqa.com/profile", "Unexpected page redirect");
    }

    public void logOut() {
        clickOn(logoutButton);
    }

    public void goToBookStore() {
        LOG.info("Navigating to bookstore");
        clickOn(goToBookStoreButton);
    }

    public void entryExistsOnTable(String bookTitle){
        List<WebElement> table = driver.findElements(By.xpath("//div[@class='rt-tr-group' and @role='rowgroup']"));

        for (WebElement currentRowElement : table){
            List<WebElement> columns = currentRowElement.findElements(By.xpath("//div[@class='rt-td' and @role='gridcell']"));
            for (WebElement currentColumn : columns){
                if (currentColumn.getText().equalsIgnoreCase(bookTitle)){
                    LOG.info("Found book in collection table: " + bookTitle);
                    return;
                }
            }
        }

        Assert.fail("Unable to find book entry " + bookTitle + " in collection table");
    }

    public void removeEntryOnTable(String bookTitle){
        List<WebElement> table = driver.findElements(By.xpath("//div[@class='rt-tr-group' and @role='rowgroup']"));

        for (WebElement currentRowElement : table){
            List<WebElement> columns = currentRowElement.findElements(By.xpath("//div[@class='rt-td' and @role='gridcell']"));
            for (WebElement currentColumn : columns){
                if (currentColumn.getText().equalsIgnoreCase(bookTitle)){
                    LOG.info("Found book in collection table: " + bookTitle);

                    WebElement deleteButton = columns.get(4).findElement(By.xpath("//span[@id='delete-record-undefined']"));
                    deleteButton.click();

                    LOG.info("Book " + bookTitle + " have been removed from the collection");
                    return;
                }
            }
        }

        Assert.fail("Unable to find book entry " + bookTitle + " in collection table");
    }

    public void entryDoesNotExistsOnTable(String bookTitle){
        List<WebElement> table = driver.findElements(By.xpath("//div[@class='rt-tr-group' and @role='rowgroup']"));

        for (WebElement currentRowElement : table){
            List<WebElement> columns = currentRowElement.findElements(By.xpath("//div[@class='rt-td' and @role='gridcell']"));
            for (WebElement currentColumn : columns){
                if (currentColumn.getText().equalsIgnoreCase(bookTitle)){
                    Assert.fail("Found entry on table " + bookTitle + " in collection table");
                    return;
                }
            }
        }

        LOG.info("Book " + bookTitle + " does not exist on table");
    }
}
