//package com.sprite.cloud.automation.framework.web;
//
//import com.sprite.cloud.automation.framework.base.TestBase;
//import com.sprite.cloud.automation.framework.base.web.WebPropertyUtils;
//import com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects.ElementsPage;
//import com.sprite.cloud.automation.framework.base.web.pom.ElementsPageObjects.TextBoxPage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
//import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
//import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;
//
//public class ElementsTest extends TestBase {
//
//    private static final Logger LOG = LoggerFactory.getLogger(ElementsTest.class);
//
//    private String url;
//
//    private ElementsPage elementsPage;
//    private TextBoxPage textBoxPage;
//
//    @BeforeClass
//    @Parameters({"environment"})
//    private void setup(@Optional(QA) String environment){
//        url = WebPropertyUtils.getWebUrl("tools", environment);
//
//        LOG.info("Navigating to URL: " + url);
//
//        elementsPage = new ElementsPage(driver);
//        textBoxPage = new TextBoxPage(driver);
//    }
//
//
//    @Test(groups = {SMOKE, REGRESSION})
//    private void textBoxTest() {
//        driver.navigate().to(url + "/elements");
//
//        elementsPage.clickOnOption("Text Box");
//
//        textBoxPage.completeFields("Dewald Schoonderwoerd", "please.hire@me.com", "20020", "202092");
//    }
//
//}
