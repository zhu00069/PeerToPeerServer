package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.AccessToken;
import io.swagger.model.Credential;
import io.swagger.model.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/user")


@io.swagger.annotations.Api(description = "the user API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-04-03T22:28:30.901Z")
public interface UserApi  {
   
    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create user", notes = "any notes for create user here", response = User.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = User.class) })
    public Response createUser(@ApiParam(value = "Created user object" ,required=true) User body,@Context SecurityContext securityContext);
    @GET
    @Path("/find")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find user by name", notes = "any notes for read user here", response = User.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Void.class) })
    public Response findUser(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey, @PathParam("username") String username,@Context SecurityContext securityContext);
    @POST
    @Path("/login")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs user into the system", notes = "", response = AccessToken.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = AccessToken.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username/password supplied", response = Void.class) })
    public Response loginUser( @QueryParam("username") String username, @QueryParam("password") String password,@ApiParam(value = "" )@HeaderParam("clientId") String clientId,@ApiParam(value = "" )@HeaderParam("clientSecret") String clientSecret,@Context SecurityContext securityContext);
    @GET
    @Path("/logout")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs out current logged in user session", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    public Response logoutUser(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey,@Context SecurityContext securityContext);
    @GET
    @Path("/roles")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get roles assigned to a user", notes = "", response = Credential.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Credential.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Void.class) })
    public Response readRoles(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey, @PathParam("userId") Integer userId,@Context SecurityContext securityContext);
    @GET
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Read user", notes = "any notes for read user here", response = User.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Void.class) })
    public Response readUser(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey, @PathParam("userId") Integer userId,@Context SecurityContext securityContext);
    @DELETE
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Void.class) })
    public Response removeUser(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey, @PathParam("userId") Integer userId,@Context SecurityContext securityContext);
    @PUT
    
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update user", notes = "any notes for update user here", response = User.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuthSecure", scopes = {
            
        })
    }, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid opportunity id supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Opportunity not found", response = Void.class) })
    public Response updateUser(@ApiParam(value = "" ,required=true)@HeaderParam("Authorization") String authorization,@ApiParam(value = "" ,required=true)@HeaderParam("conKey") String conKey,@ApiParam(value = "Updated user object" ,required=true) User body,@Context SecurityContext securityContext);
    @OPTIONS
    @Path("/login")
    
    
    @io.swagger.annotations.ApiOperation(value = "cors preflight management", notes = "", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class) })
    public Response userLoginOptions(@Context SecurityContext securityContext);
}
