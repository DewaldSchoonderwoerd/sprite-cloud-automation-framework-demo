package com.sprite.cloud.automation.framework.base.api.service.toolsqa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sprite.cloud.automation.framework.base.api.client.JaxRsBase;
import com.sprite.cloud.automation.framework.base.api.models.bookstore.account.v1.postAccount.request.PostAccountRequest;
import com.sprite.cloud.automation.framework.base.api.models.bookstore.account.v1.postAccount.response.PostAccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import javax.ws.rs.core.Response;

public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static JaxRsBase jaxRsClient = JaxRsBase.getInstance();

    public static PostAccountResponse createNewUser(String username, String password) {
        jaxRsClient = JaxRsBase.getInstance();

        PostAccountRequest postAccountRequest = new PostAccountRequest();
        postAccountRequest.setUserName(username);
        postAccountRequest.setPassword(password);

        LOG.info("Requesting add new user account, expecting status code 201: " + postAccountRequest.getUserName());

        Response response = jaxRsClient.getAccountResource().addAccount(postAccountRequest);
        Assert.assertEquals(response.getStatus(), 201, "Unable to successfully add new user account: " + postAccountRequest.getUserName());

        PostAccountResponse postAccountResponse = new Gson().fromJson(response.readEntity(String.class), TypeToken.get(PostAccountResponse.class).getType());
        LOG.info("New Book Store User Created " + postAccountResponse.toString());

        return postAccountResponse;
    }
}