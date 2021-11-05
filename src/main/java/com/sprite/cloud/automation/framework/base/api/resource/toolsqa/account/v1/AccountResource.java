package com.sprite.cloud.automation.framework.base.api.resource.toolsqa.account.v1;

import com.sprite.cloud.automation.framework.base.api.models.bookstore.account.v1.postAccount.request.PostAccountRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Account/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AccountResource {

    @Path("/user")
    @POST
    Response addAccount(PostAccountRequest postAccountRequest);

}
