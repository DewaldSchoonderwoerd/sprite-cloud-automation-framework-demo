package com.sprite.cloud.automation.framework.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAutomationException extends RuntimeException {

    private static final Logger LOG = LoggerFactory.getLogger(WebAutomationException.class);

    public WebAutomationException(String message) {
        LOG.error(message);
    }
}
