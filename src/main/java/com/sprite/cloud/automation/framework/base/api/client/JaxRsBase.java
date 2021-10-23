package com.sprite.cloud.automation.framework.base.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sprite.cloud.automation.framework.base.api.client.interceptors.HttpLogger;
import com.sprite.cloud.automation.framework.base.api.resource.petstore.v2.PetResource;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.sprite.cloud.automation.framework.base.TestBase.PROPERTIES;

public class JaxRsBase {

    private static final Logger LOG = LoggerFactory.getLogger(JaxRsBase.class);

    private static final String PET_STORE_KEY = "swagger.pet.shop";

    public static JaxRsBase baseRequest = null;

    public String env;

    protected JaxRsBase(String env) {
        this.env = env;
    }

    public static JaxRsBase getInstance() {
        if (baseRequest == null) {
            baseRequest = new JaxRsBase(PROPERTIES.getProperty("environment"));
            baseRequest.setObjectMapper(new ObjectMapper());
        }
        return baseRequest;
    }

    private ObjectMapper objectMapper;

    protected <T> T getResource(Class<T> resourceClass, String key) {
        String baseUrl = EndpointLoader.getApiEndpoint(key, env);
        List<Object> providers = new ArrayList<>();
        providers.add(new JacksonJsonProvider(objectMapper));
//        providers.add(new AllureHttpInterceptor());
        providers.add(new HttpLogger());
        return JAXRSClientFactory.create(baseUrl, resourceClass, providers);
    }

    protected void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public PetResource getPetResource(){
        return getResource(PetResource.class, PET_STORE_KEY);
    }
}
