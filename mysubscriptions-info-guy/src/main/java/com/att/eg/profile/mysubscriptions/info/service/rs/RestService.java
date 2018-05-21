package com.att.eg.profile.mysubscriptions.info.service.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

@Api
@Path("/service")
@Produces({MediaType.APPLICATION_JSON})
public interface RestService {        // NOSONAR This is not a functional interface
    @GET
    @Path("/channels")
    @Produces({
            MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Get user's subscriptions information", notes = "Given a profileId returns a JSON object containing information on user's base package, add-ons, and cdvr hours", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AEG-Profile-ID", value = "Unique profile identifier of the profile. Used to query couchbase for eProfile", dataType = "string", required = true, paramType = "header", example = "554205618_TMBR"),
            @ApiImplicitParam(name = "Secure-Token-Id", value = "Token id used to get the session id from QPUMS - need to get more info", dataType = "string", required = false, paramType = "header", example = "dont-know-yet")})
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unexpected Runtime error"),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
            @ApiResponse(code = 404, message = "Not found")})
    Response getChannels(
            @Context HttpHeaders headers);

}