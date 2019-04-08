package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Opportunity;
import io.swagger.model.OpportunityList;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/opportunity")


@io.swagger.annotations.Api(description = "the opportunity API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-03-26T02:33:27.424Z")
public interface OpportunityApi  {
   
    @POST
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create opportunity", notes = "", response = Opportunity.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "opportunity", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Opportunity.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid opportunity id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Opportunity not found", response = Void.class) })
    public Response createOpp(@ApiParam(value = "Created opportunity object" ,required=true) Opportunity body,@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey,@Context SecurityContext securityContext);
    @GET
    @Path("/list")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = OpportunityList.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "opportunity", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = OpportunityList.class) })
    public Response listOpp(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey,@Context SecurityContext securityContext);
    @GET
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Opportunity.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "opportunity", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Opportunity.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid opportunity id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Opportunity not found", response = Void.class) })
    public Response readOpp(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey, @PathParam("opportunityId") Integer opportunityId,@Context SecurityContext securityContext);
    @DELETE
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "opportunity", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid opportunity id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Opportunity not found", response = Void.class) })
    public Response removeOpp(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey, @PathParam("opportunityId") Integer opportunityId,@Context SecurityContext securityContext);
    @PUT
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update opportunity", notes = "", response = Opportunity.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "opportunity", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Opportunity.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid opportunity id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Opportunity not found", response = Void.class) })
    public Response updateOpp(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey,@ApiParam(value = "Created opportunity object" ,required=true) Opportunity body,@Context SecurityContext securityContext);
}
