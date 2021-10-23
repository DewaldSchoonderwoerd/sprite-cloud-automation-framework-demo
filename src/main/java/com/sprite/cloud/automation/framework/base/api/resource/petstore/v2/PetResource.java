package com.sprite.cloud.automation.framework.base.api.resource.petstore.v2;

import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.postPet.request.PostPetRequest;
import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.putpet.request.PutPetRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
Everything about your Pets
 */
@Path("/v2/pet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PetResource {

    @POST
    Response addPet(PostPetRequest postPetRequest);

    @PUT
    Response editPet(PutPetRequest putPetRequest);
}
