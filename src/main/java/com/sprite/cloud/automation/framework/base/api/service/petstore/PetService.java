package com.sprite.cloud.automation.framework.base.api.service.petstore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sprite.cloud.automation.framework.base.api.client.JaxRsBase;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.postpet.request.PostPetRequest;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.postpet.request.Tag;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.postpet.response.PostPetResponse;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.putpet.request.PutPetRequest;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.putpet.response.PutPetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.ws.rs.core.Response;

public class PetService {

    private static final Logger LOG = LoggerFactory.getLogger(PetService.class);
    private static JaxRsBase jaxRsClient = JaxRsBase.getInstance();

    public static PostPetResponse addPetSuccessfully(PostPetRequest postPetRequest) {
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting add new pet, expecting status code 200: " + postPetRequest.getName());

        Response response = jaxRsClient.getPetResource().addPet(postPetRequest);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully add new pet: " + postPetRequest.getName());

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(PostPetResponse.class).getType());
    }

    public static PostPetResponse addPetError(PostPetRequest postPetRequest) {
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting add new pet, expecting exception with status code 400: " + postPetRequest.getName());

        Response response = jaxRsClient.getPetResource().addPet(postPetRequest);
        Assert.assertEquals(response.getStatus(), 400, "Unable to unsuccessfully add new pet: " + postPetRequest.getName());

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(PostPetResponse.class).getType());
    }

    public static void assertPetResponseAgainstRequest(PostPetResponse postPetResponse, PostPetRequest postPetRequest) {
        LOG.info("Running assertions for POST Pet Request against Response: " + postPetRequest.getName());

        SoftAssert softAssert = new SoftAssert();

        // Base object assertions
        softAssert.assertEquals(postPetResponse.getName(), postPetRequest.getName());
        softAssert.assertEquals(postPetResponse.getId(), postPetRequest.getId());
        softAssert.assertEquals(postPetResponse.getStatus(), postPetRequest.getStatus());

        // Category object assertions
        softAssert.assertEquals(postPetResponse.getCategory().getId(), postPetRequest.getCategory().getId());
        softAssert.assertEquals(postPetResponse.getCategory().getName(), postPetRequest.getCategory().getName());

        // Photos URLs assertions
        for (String currentPhotoUrl : postPetRequest.getPhotoUrls()){
            softAssert.assertTrue(postPetResponse.getPhotoUrls().contains(currentPhotoUrl), "PostPetResponse PhotoUrls does not contain requested: " + currentPhotoUrl);
        }

        // Tag object assertions
        for (Tag currentRequestTag : postPetRequest.getTags()) {
            for (com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.postpet.response.Tag currentResponseTag : postPetResponse.getTags()){
                if (!currentRequestTag.getId().equals(currentResponseTag.getId())){
                    softAssert.fail("PostPetResponse Tag does not contain requested Tag: " + currentRequestTag.getId());
                }

                if (!currentResponseTag.getName().equals(currentRequestTag.getName())){
                    softAssert.fail("PostPetResponse Tag does not contain requested Tag: " + currentRequestTag.getName());
                }
            }
        }

        LOG.info("Trigger assertions");
        softAssert.assertAll();

        LOG.info("All assertions successful for POST Add Pet Request against Response");
    }

    public static PutPetResponse editPetSuccessfully(PutPetRequest putPetRequest) {
        jaxRsClient = JaxRsBase.getInstance();

        LOG.info("Requesting edit existing pet, expecting status code 200: " + putPetRequest.getName());

        Response response = jaxRsClient.getPetResource().editPet(putPetRequest);
        Assert.assertEquals(response.getStatus(), 200, "Unable to successfully edit existing pet: " + putPetRequest.getName());

        return new Gson().fromJson((String) response.readEntity(String.class), TypeToken.get(PutPetResponse.class).getType());
    }

    public static void assertPetResponseAgainstRequest(PutPetResponse petResponse, PutPetRequest putPetRequest) {
        LOG.info("Running assertions for PUT Pet Request against Response: " + putPetRequest.getName());

        SoftAssert softAssert = new SoftAssert();

        // Base object assertions
        softAssert.assertEquals(petResponse.getName(), putPetRequest.getName());
        softAssert.assertEquals(petResponse.getId(), putPetRequest.getId());
        softAssert.assertEquals(petResponse.getStatus(), putPetRequest.getStatus());

        // Category object assertions
        softAssert.assertEquals(petResponse.getCategory().getId(), putPetRequest.getCategory().getId());
        softAssert.assertEquals(petResponse.getCategory().getName(), putPetRequest.getCategory().getName());

        // Photos URLs assertions
        for (String currentPhotoUrl : putPetRequest.getPhotoUrls()){
            softAssert.assertTrue(petResponse.getPhotoUrls().contains(currentPhotoUrl), "PutPetResponse PhotoUrls does not contain requested: " + currentPhotoUrl);
        }

        // Tag object assertions
        for (com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.putpet.request.Tag currentRequestTag : putPetRequest.getTags()) {
            for (com.sprite.cloud.automation.framework.base.api.models.petstore.v2.pet.putpet.response.Tag currentResponseTag : petResponse.getTags()){
                if (!currentRequestTag.getId().equals(currentResponseTag.getId())){
                    softAssert.fail("PostPetResponse Tag does not contain requested Tag: " + currentRequestTag.getId());
                }

                if (!currentResponseTag.getName().equals(currentRequestTag.getName())){
                    softAssert.fail("PostPetResponse Tag does not contain requested Tag: " + currentRequestTag.getName());
                }
            }
        }

        LOG.info("Trigger assertions");
        softAssert.assertAll();

        LOG.info("All assertions successful for POST Add Pet Request against Response");
    }

}
