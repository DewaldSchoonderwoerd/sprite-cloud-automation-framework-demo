package com.sprite.cloud.automation.framework.api.petstoreUser;

import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.api.service.petstore.UserApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;

public class GetUserByUsernameTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(GetUserByUsernameTest.class);

    @Test(groups = {REGRESSION})
    public void userLogin() {
        HashMap<String, String> userDetails = UserApiService.addRandomUserSuccessfully();

        UserApiService.getUserByName(userDetails.get("username"));
    }


}
