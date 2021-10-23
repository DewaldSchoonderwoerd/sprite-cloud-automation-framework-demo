package com.sprite.cloud.automation.framework.api.functional;

import com.sprite.cloud.automation.framework.base.TestBase;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.postPet.request.PostPetRequest;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.postPet.request.Tag;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.postPet.response.PostPetResponse;
import com.sprite.cloud.automation.framework.base.api.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.sprite.cloud.automation.framework.base.constants.TestGroups.REGRESSION;
import static com.sprite.cloud.automation.framework.base.constants.TestGroups.SMOKE;
import static com.sprite.cloud.automation.framework.base.utilities.Random.randomIntInRange;

public class PostPetTest extends TestBase {

    private static final Logger LOG = LoggerFactory.getLogger(PostPetTest.class);

    @Test(groups = {SMOKE, REGRESSION})
    private void addPetSuccessfullyTest() {
        PostPetRequest postPetRequest = new PostPetRequest();

        postPetRequest.setId(randomIntInRange(99999, 1));
        postPetRequest.setName("Fluff Meister");
        postPetRequest.setStatus("available");

        postPetRequest.getCategory().setId(randomIntInRange(99999, 1));
        postPetRequest.getCategory().setName("English bulldog");

        postPetRequest.getPhotoUrls().add("some.image.url.of.cool.pup");

        Tag tag = new Tag();
        tag.setId(randomIntInRange(99999, 1));
        tag.setName("Dog");
        postPetRequest.getTags().add(tag);

        PostPetResponse postPetResponse = PetService.addPetSuccessfully(postPetRequest);
        PetService.assertPetResponseAgainstRequest(postPetResponse, postPetRequest);
    }

    @Test(groups = {REGRESSION})
    private void addPetUnsuccessfullyTest() {
        PostPetRequest postPetRequest = new PostPetRequest();

        postPetRequest.setId(0);
        postPetRequest.setName("Doggie");

        postPetRequest.getCategory().setId(0);
        postPetRequest.getCategory().setName("Some Category");

        postPetRequest.getPhotoUrls().add("some.image.url");

        Tag tag = new Tag();
        tag.setId(0);
        tag.setName("Some Tag");
        postPetRequest.getTags().add(tag);

        PetService.addPetError(postPetRequest);
    }

}
