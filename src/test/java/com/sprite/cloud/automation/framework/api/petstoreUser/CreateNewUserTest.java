package com.sprite.cloud.automation.framework.api.petstoreUser;

import com.github.javafaker.Faker;
import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.user.postuser.request.PostUserRequest;
import com.sprite.cloud.automation.framework.base.api.service.petstore.UserApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;
import static com.sprite.cloud.automation.framework.base.constants.UserStatus.ACTIVE;
import static com.sprite.cloud.automation.framework.base.utilities.RandomValues.randomIntInRange;

public class CreateNewUserTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(CreateNewUserTest.class);

    @Test(groups = {SMOKE, REGRESSION})
    public void createNewValidUser() {
        PostUserRequest postUserRequest = new PostUserRequest();

        postUserRequest.setId(randomIntInRange(99999, 1));
        postUserRequest.setUsername(new Faker().name().username());
        postUserRequest.setFirstName(new Faker().name().firstName());
        postUserRequest.setLastName(new Faker().name().lastName());
        postUserRequest.setEmail(new Faker().internet().emailAddress());
        postUserRequest.setPassword(Integer.toString(randomIntInRange(9999, 1000)));
        postUserRequest.setPhone(new Faker().phoneNumber().phoneNumber());
        postUserRequest.setUserStatus(ACTIVE);

        UserApiService.addUserSuccessfully(postUserRequest);
    }

}
