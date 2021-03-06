package com.sprite.cloud.automation.framework.base.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.api.client.interceptors.HttpLogger;
import com.sprite.cloud.automation.framework.base.api.resource.petstore.v2.PetResource;
import com.sprite.cloud.automation.framework.base.api.resource.petstore.v2.StoreResource;
import com.sprite.cloud.automation.framework.base.api.resource.petstore.v2.UserResource;
import com.sprite.cloud.automation.framework.base.api.resource.toolsqa.account.v1.AccountResource;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.sprite.cloud.automation.framework.base.TestBase.PROPERTIES;

public class JaxRsBase {

    private static final Logger LOG = LoggerFactory.getLogger(JaxRsBase.class);

    private static final String PET_STORE_KEY = "swagger.pet.shop";
    private static final String TOOLS_QA_KEY = "tools.api";

    public static JaxRsBase baseRequest = null;

    public static JaxRsBase getInstance() {
        if (baseRequest == null) {
            LOG.info("Setting up new instance of JaxRsBase");
            baseRequest = new JaxRsBase();
            baseRequest.setObjectMapper(new ObjectMapper());
        }
        return baseRequest;
    }

    private ObjectMapper objectMapper;

    protected <T> T getResource(Class<T> resourceClass, String key) {
        String baseUrl = EndpointLoader.getApiEndpoint(key, TestBase.ENVIRONMENT);
        List<Object> providers = new ArrayList<>();
        providers.add(new JacksonJsonProvider(objectMapper));
        providers.add(new HttpLogger());
        return JAXRSClientFactory.create(baseUrl, resourceClass, providers);
    }

    protected void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public PetResource getPetResource(){
        return getResource(PetResource.class, PET_STORE_KEY);
    }

    public StoreResource getStoreResource() {return getResource(StoreResource.class, PET_STORE_KEY);}

    public UserResource getUserResource() {return getResource(UserResource.class, PET_STORE_KEY);}

    public AccountResource getAccountResource() {
        return getResource(AccountResource.class, TOOLS_QA_KEY);
    }
}
