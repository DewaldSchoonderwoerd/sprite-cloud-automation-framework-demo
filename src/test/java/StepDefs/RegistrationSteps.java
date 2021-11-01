package StepDefs;

import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.sprite.cloud.automation.framework.base.TestBase.driver;
import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;

public class RegistrationSteps extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationSteps.class);
    private static String url;

    @BeforeClass
    @Parameters({"environment"})
    public void setup(@Optional(QA) String environment) {
        url = WebPropertyUtils.getWebUrl("tools", environment);

        driver.navigate().to("https://demoqa.com" + "/checkbox");
        LOG.info("Navigating to URL: ");
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String arg0) {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();

        String fullUrl = "https://demoqa.com/register";
        LOG.info("navigating to " + fullUrl);
        driver.navigate().to(fullUrl);

        LOG.info("Navigating to URL" + fullUrl);
    }

    @When("I create a new valid account")
    public void iCreateANewValidAccount() {
        LOG.info("Hello");

    }

    @Then("Login button should exits")
    public void loginButtonShouldExits() {
        LOG.info("Hello");
    }
}
