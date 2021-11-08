package com.sprite.cloud.automation.framework.base.api.resource.petstore.v2;

import com.sprite.cloud.automation.framework.base.api.models.petstore.v2.user.postuser.request.PostUserRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
Operations about user
 */
@Path("/v2/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserResource {

    @POST
    Response addUser(PostUserRequest postUserRequest);

    @GET
    @Path("/{username}")
    Response getUserByUsername(@PathParam("username") String username);

    @GET
    @Path("/login")
    Response getUserLogin(@QueryParam("username") String username,
                          @QueryParam("password") String password);

}
