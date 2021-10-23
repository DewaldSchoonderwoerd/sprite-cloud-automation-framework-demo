package com.sprite.cloud.automation.framework.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
import static com.sprite.cloud.automation.framework.base.constants.Environments.STAGING;

public class WebPropertyUtils {

    private static final Logger LOG = LoggerFactory.getLogger(WebPropertyUtils.class);

    private static Properties webUrlProperties = null;
    private static Properties remoteWebDriverProperties = null;

    static {
        LOG.info("Loading mandatory web properties...");
        webUrlProperties = mandatoryFileLoad(basePath() + "webUrl.properties");
//        remoteWebDriverProperties = mandatoryFileLoad(basePath() + "remoteWebDriver.properties");
    }

    public static String getWebUrlDetails(String key) {
        return webUrlProperties.getProperty(key);
    }

    public static String getRemoteWebDriverProperties(String key) {
        return remoteWebDriverProperties.getProperty(key);
    }


    public static String getWebUrl(String key, String environment) {
        if (key.isEmpty()) {
            LOG.info("Empty URL initiated");
            return "";
        } else if (key.contains("https")) {
            LOG.info("Complete URL " + key);
            return key;
        } else if (environment.equalsIgnoreCase(STAGING)) {
            key = "staging." + key;
        } else if (environment.equalsIgnoreCase(QA)) {
            key = "qa." + key;
        } else {
            throw new WebAutomationException("Invalid url Key");
        }
        String url = getWebUrlDetails(key);
        if (url == null) {
            LOG.error(key + " not found in webUrl.properties file");
        }
        return url;
    }

    public static Properties mandatoryFileLoad(String filePath) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileReader(new File(filePath)));
        } catch (Exception e) {
            properties.clear();
            throw new WebAutomationException(filePath + " not found");
        }
        return properties;
    }

    private static String basePath() {
        return System.getProperty("user.dir") + "/src/main/resources/";
    }
}
