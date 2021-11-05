package com.sprite.cloud.automation.framework.base.api.service.petstore;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sprite.cloud.automation.framework.base.api.client.JaxRsBase;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.user.getuserbyusername.response.GetUserByUsernameResponse;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.user.getuserlogin.response.GetUserLoginResponse;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.user.postuser.request.PostUserRequest;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.user.postuser.response.PostUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import javax.ws.rs.core.Response;

import java.util.HashMap;

import static com.sprite.cloud.automation.framework.base.constants.UserStatus.ACTIVE;
import static com.sprite.cloud.automation.framework.base.utilities.RandomValues.randomIntInRange;

public class UserApiService {

    private static final Logger LOG = LoggerFactory.getLogger(UserApiService.class);
    private static JaxRsBase jaxRsClient = JaxRsBase.getInstance();

    public static PostUserResponse addUserSuccessfully(PostUserRequest postUserRequests) {
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting add a new user, expecting status code 200: " + postUserRequests.getUsername());

        Response response = jaxRsClient.getUserResource().addUser(postUserRequests);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully add new user: " + postUserRequests.getUsername());

        LOG.info("Successfully added a new user: " + postUserRequests.getUsername());

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(PostUserResponse.class).getType());
    }

    public static HashMap<String, String> addRandomUserSuccessfully() {
        PostUserRequest postUserRequest = new PostUserRequest();
        postUserRequest.setId(randomIntInRange(99999, 1));
        postUserRequest.setUsername(new Faker().name().username());
        postUserRequest.setFirstName(new Faker().name().firstName());
        postUserRequest.setLastName(new Faker().name().lastName());
        postUserRequest.setEmail(new Faker().internet().emailAddress());
        postUserRequest.setPassword(Integer.toString(randomIntInRange(9999, 1000)));
        postUserRequest.setPhone(new Faker().phoneNumber().phoneNumber());
        postUserRequest.setUserStatus(ACTIVE);

        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting add a new random user, expecting status code 200: " + postUserRequest.getUsername());

        Response response = jaxRsClient.getUserResource().addUser(postUserRequest);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully add new random user: " + postUserRequest.getUsername());

        LOG.info("Successfully added a new random user: " + postUserRequest.getUsername());

        HashMap<String, String> userDetails = new HashMap<>();
        userDetails.put("username", postUserRequest.getUsername());
        userDetails.put("password", postUserRequest.getPassword());

        return userDetails;
    }

    public static GetUserByUsernameResponse getUserByName(String username){
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting get user by user name, expecting status code 200: " + username);

        Response response = jaxRsClient.getUserResource().getUserByUsername(username);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully get user: " + username);

        LOG.info("Successfully got user: " + username);

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(GetUserByUsernameResponse.class).getType());
    }

    public static GetUserLoginResponse userLogin(String username, String password){
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting get user login, expecting status code 200: " + username);

        Response response = jaxRsClient.getUserResource().getUserLogin(username, password);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully get user login: " + username);

        LOG.info("Successfully got user login: " + username);

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(GetUserLoginResponse.class).getType());
    }

}
