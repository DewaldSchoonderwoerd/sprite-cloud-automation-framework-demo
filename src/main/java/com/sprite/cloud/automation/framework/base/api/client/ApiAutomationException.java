package com.sprite.cloud.automation.framework.base.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiAutomationException extends RuntimeException {
    private static final Logger LOG = LoggerFactory.getLogger(ApiAutomationException.class);

    public ApiAutomationException(String message) {
        LOG.error(message);
    }
}
