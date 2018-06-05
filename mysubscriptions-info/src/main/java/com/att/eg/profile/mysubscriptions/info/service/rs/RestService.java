package com.att.eg.profile.mysubscriptions.info.service.rs;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import com.att.eg.profile.mysubscriptions.info.model.SubscriptionComparisonResponse;

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
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(
            value = "Get user's subscriptions information",
            notes = "Given a profileId returns a JSON object containing information on user's base package, add-ons, and cdvr hours",
            response = Response.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AEG-Profile-ID", value = "Unique profile identifier of the profile. Used to query couchbase for eProfile",
                    dataType = "string", required = true, paramType = "header", example = "554205618_TMBR"),
            @ApiImplicitParam(name = "Secure-Token-Id", value = "Token id used to get the session id from QPUMS - need to get more info",
                    dataType = "string", required = false, paramType = "header", example = "dont-know-yet")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Unexpected Runtime error"),
                    @ApiResponse(code = 400, message = "Invalid request parameters"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public Response getInfo(
            @ApiParam(hidden = true) @HeaderParam("X-AEG-Profile-ID") String profileId,
            @ApiParam(hidden = true, required = false) @HeaderParam("Secure-Token-ID") String secureTokenId);


    @GET
    @Path("carousel")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(
            value = "Get carousel resources",
            notes = "Given a profileId returns a JSON object with carousel data based on request parameter",
            response = Response.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AEG-Profile-ID", value = "Unique profile identifier of the profile. Used to query couchbase for eProfile",
                    dataType = "string", required = true, paramType = "header", example = "554205618_TMBR"),
            @ApiImplicitParam(name = "Secure-Token-Id", value = "Token id used to get the session id from QPUMS - need to get more info",
                    dataType = "string", required = false, paramType = "header", example = "dont-know-yet"),
            @ApiImplicitParam(name = "uxReference", value = "uxReference to determine carousel type",
                    dataType = "string", required = true, paramType = "query", example = "MySubscriptions_ChannelPackagesReference")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Unexpected Runtime error"),
                    @ApiResponse(code = 400, message = "Invalid request parameters"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public Response getCarousel(
            @ApiParam(hidden = true) @HeaderParam("X-AEG-Profile-ID") String profileId,
            @ApiParam(hidden = true) @HeaderParam("Secure-Token-Id") String secureToken,
            @ApiParam(hidden = true) @QueryParam("uxReference") String uxReference);

    @GET
    @Path("subscriptions/compare/{subscriptionId}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(
            value = "Get subscription comparison between selected subscription and current subscription",
            notes = "Given a profileId and a subscriptionId, returns a JSON object containing " +
                    "three lists of channels: " +
                    "1. Common channels" +
                    "2. Removed channels that the selected package does not contain, but the current one does" +
                    "3. Added that the selected package contains, but the current one does not",
            response = SubscriptionComparisonResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AEG-Profile-ID", value = "Unique profile identifier of the profile. Used to query couchbase for eProfile",
                    dataType = "string", required = true, paramType = "header", example = "554205618_TMBR"),
            @ApiImplicitParam(name = "Secure-Token-Id", value = "Token id used to get the session id from QPUMS - need to get more info",
                    dataType = "string", required = false, paramType = "header", example = "dont-know-yet"),
            @ApiImplicitParam(name = "subscriptionId", value = "id associated to the subscription under which the channels link has been pressed",
                    dataType = "string", required = true, paramType = "path", example = "??"),
            @ApiImplicitParam(name = "X-AEG-Route-Offer", value = "Value used to determine on which route path to make internal cross-microservice calls",
                    dataType = "string", required = true, paramType = "header", example = "GREEN"),
            @ApiImplicitParam(name = "fisProperties", value = "Paramater that includes the scale factor, image types, and sizes of the images whose metadata we want",
                    dataType = "string", required = true, paramType = "query", example = "ISF:2.0#chlogo-bwdb-mytv,47,35#chlogo-clb-guide,60,45#chlogo-cdb-gcd,87,66#chlogo-bwdb-fplayer,87,66")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Unexpected Runtime error"),
                    @ApiResponse(code = 400, message = "Invalid request parameters"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public Response getSubscriptionComparison(
            @ApiParam(hidden = true) @HeaderParam("X-AEG-Profile-ID") String profileId,
            @ApiParam(hidden = true, required = false) @HeaderParam("Secure-Token-ID") String secureTokenId,
            @ApiParam(hidden = true) @HeaderParam("X-AEG-Route-Offer") String routeOffer,
            @ApiParam(hidden = true) @PathParam("subscriptionId") String subscriptionId,
            @ApiParam(hidden = true) @QueryParam("fisProperties") String fisProperties);
}