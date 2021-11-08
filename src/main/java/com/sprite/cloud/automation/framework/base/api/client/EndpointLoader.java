package com.sprite.cloud.automation.framework.base.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import static com.sprite.cloud.automation.framework.base.constants.Environments.QA;
import static com.sprite.cloud.automation.framework.base.constants.Environments.STAGING;

public class EndpointLoader {

    private static final Logger LOG = LoggerFactory.getLogger(EndpointLoader.class);
    private static Properties endpointsProperties = null;

    static {
        LOG.info("Loading endpoint properties...");
        endpointsProperties = mandatoryFileLoad(System.getProperty("user.dir") +
                "/src/main/resources/apiEndpoints.properties");
    }

    public static String getEndpointsProperties(String key) {
        return endpointsProperties.getProperty(key);
    }

    public static Properties mandatoryFileLoad(String filePath) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileReader(new File(filePath)));
        } catch (Exception e) {
            properties.clear();
            throw new ApiAutomationException(filePath + " not found");
        }
        return properties;
    }

    public static String getApiEndpoint(String key, String env) {
        if (env.equalsIgnoreCase(STAGING)) {
            key = STAGING.toLowerCase() + "." + key;
        } else if (env.equalsIgnoreCase(QA)) {
            key = QA.toLowerCase() + "." + key;
        }  else {
            throw new ApiAutomationException("Please specify environment");
        }
        String endpoint = EndpointLoader.getEndpointsProperties(key);
        if (endpoint == null) {
            LOG.error(key + " not found in apiEndpoints.properties file");
        }
        return endpoint;
    }
}
