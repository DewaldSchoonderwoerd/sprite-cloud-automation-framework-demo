package com.sprite.cloud.automation.framework.api.functional;

import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.putpet.request.PutPetRequest;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.putpet.request.Tag;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.putpet.response.PutPetResponse;
import com.sprite.cloud.automation.framework.base.api.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;
import static com.sprite.cloud.automation.framework.base.utilities.Random.randomIntInRange;

public class PutPetTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(PutPetTest.class);

    @Test(groups = {SMOKE, REGRESSION})
    private void editPetSuccessfullyTest() {
        PutPetRequest putPetRequest = new PutPetRequest();

        putPetRequest.setId(randomIntInRange(99999, 1));
        putPetRequest.setName("Fluff Meister");
        putPetRequest.setStatus("available");

        putPetRequest.getCategory().setId(randomIntInRange(99999, 1));
        putPetRequest.getCategory().setName("English bulldog");

        putPetRequest.getPhotoUrls().add("some.image.url.of.cool.pup");

        Tag tag = new Tag();
        tag.setId(randomIntInRange(99999, 1));
        tag.setName("Dog");
        putPetRequest.getTags().add(tag);

        PutPetResponse putPetResponse = PetService.editPetSuccessfully(putPetRequest);
        PetService.assertPetResponseAgainstRequest(putPetResponse, putPetRequest);
    }
}
