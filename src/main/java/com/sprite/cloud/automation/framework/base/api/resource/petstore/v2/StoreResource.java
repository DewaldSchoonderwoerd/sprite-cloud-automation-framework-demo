package com.sprite.cloud.automation.framework.base.api.resource.petstore.v2;

import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.store.poststoreorder.request.PostStoreOrderRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
Access to Petstore orders
 */
@Path("/v2/store")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface StoreResource {

    @POST
    @Path("/order")
    Response createOrder(PostStoreOrderRequest postStoreOrderRequest);

    @GET
    @Path("/order/{orderId}")
    Response getStoreOrderByOrderId(@PathParam("orderId") int orderId);

}
