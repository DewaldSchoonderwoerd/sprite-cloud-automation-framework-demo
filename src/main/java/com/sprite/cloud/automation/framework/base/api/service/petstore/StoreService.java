package com.sprite.cloud.automation.framework.base.api.service.petstore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sprite.cloud.automation.framework.base.api.client.JaxRsBase;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.store.getstoreorderbyorderid.response.GetStoreOrderByOrderIdResponse;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.store.poststoreorder.request.PostStoreOrderRequest;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.store.poststoreorder.response.PostStoreOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import javax.ws.rs.core.Response;

public class StoreService {

    private static final Logger LOG = LoggerFactory.getLogger(StoreService.class);
    private static JaxRsBase jaxRsClient = JaxRsBase.getInstance();

    public static PostStoreOrderResponse createNewOrder(PostStoreOrderRequest postStoreOrderRequest) {
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting add a new store order, expecting status code 200: " + postStoreOrderRequest.getId());

        Response response = jaxRsClient.getStoreResource().createOrder(postStoreOrderRequest);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully add new store order: " + postStoreOrderRequest.getId());

        LOG.info("Successfully added a new store order: " + postStoreOrderRequest.getId());

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(PostStoreOrderResponse.class).getType());
    }

    public static GetStoreOrderByOrderIdResponse getOrderByOrderId(int orderId) {
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting get store order by order id, expecting status code 200: " + orderId);

        Response response = jaxRsClient.getStoreResource().getStoreOrderByOrderId(orderId);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully get store order by order id: " + orderId);

        LOG.info("Successfully got store order by order id: " + orderId);

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(GetStoreOrderByOrderIdResponse.class).getType());
    }

}
