package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.AccessToken;
import io.swagger.model.User;
import server.management.ServerManager;

import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.auth.oauth.OAuthException;
import org.jboss.resteasy.auth.oauth.OAuthRequestToken;
import org.jboss.resteasy.auth.oauth.OAuthToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-12T06:56:57.774Z")
public class UserApiServiceImpl implements UserApi {
    UserDAO userAccess;
    ObjectMapper map;
    JacksonConfig conf;

    public UserApiServiceImpl() {
        userAccess = new UserDAO();
        try {
            conf = new JacksonConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map = conf.getContext(User.class);
    }

    

    @Override
    public Response userLoginOptions(SecurityContext securityContext) {
       return ServerManager.buildResp(Response.ok()).build();
    }

    @Override
    public Response createUser(User body, SecurityContext securityContext) {
        User retUser = null;
        try {
            retUser = body;
            ServerManager.log.info(retUser.toString());
            retUser = userAccess.create(retUser);
            ServerManager.log.info("ID is " + retUser.getId());
            return ServerManager.buildResp(Response.ok(map.writeValueAsString(retUser), MediaType.APPLICATION_JSON)).build();
        } catch (

        JsonProcessingException e) {
            return ServerManager.buildResp(Response.status(400)).entity(e.getMessage()).build();
        }
    }

    public Response findUser(String auth, String key, String username, SecurityContext securityContext) {
        ServerManager.log.info("Find user");
        if (auth == null) {
            return Response.status(401).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);

            User retUser = userAccess.read(username);
            if (retUser.getId() != null) {
                ServerManager.log.info(retUser.toString());
                try {
                    return ServerManager.buildResp(Response.ok(map.writeValueAsString(retUser), MediaType.APPLICATION_JSON)).build();
                } catch (JsonProcessingException e) {
                    ServerManager.log.error("Error pertaining to the formation of the JSon " + e);
                }
            }
            ServerManager.log.info("Unable to find user with provided username");
            return ServerManager.buildResp(Response.status(404)).entity("Unable to find the user").build();
        } catch (OAuthException e) {
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    public Response removeUser(String auth, String key, Integer userId, SecurityContext securityContext) {
        if (auth == null) {
            return ServerManager.buildResp(Response.status(401)).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);
            userAccess.delete(userId);
            return ServerManager.buildResp(Response.ok()).build();
        } catch (OAuthException e) {
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    public Response updateUser(String auth, String key, User body, SecurityContext securityContext) {
        if (auth == null) {
            return Response.status(401).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);

            User retUser = null;
            try {
                retUser = body;
                ServerManager.log.info(retUser.toString());
                retUser = userAccess.update(retUser);
                ServerManager.log.info("ID is " + retUser.getId());
            } catch (Exception e) {
                ServerManager.log.error("Issue with parsing the provided JSON " + e);
            }
            if (retUser.getId() == null) {
                return ServerManager.buildResp(Response.status(404)).build();
            }
            return ServerManager.buildResp(Response.ok()).build();
        } catch (OAuthException e) {
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    public Response readUser(String auth, String key, Integer userId, SecurityContext securityContext) {
        if (auth == null) {
            return ServerManager.buildResp(Response.status(401)).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);
            User resUser = userAccess.read(userId);
            if (resUser.getId() != null) {
                try {
                    return ServerManager.buildResp(Response.ok(map.writeValueAsString(resUser), MediaType.APPLICATION_JSON)).build();
                } catch (JsonProcessingException e) {
                    ServerManager.log.error("Unable to parse the provided object id " + e);
                }
            }
            return ServerManager.buildResp(Response.status(404)).entity("Unable to find User with provided id: " + userId).build();
        } catch (OAuthException e) {
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    public Response logoutUser(String auth, String key, SecurityContext securityContext) {
        if (auth == null) {
            return ServerManager.buildResp(Response.status(401)).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.RevokeToken(ServerManager.AuthManager.getAccessToken(key, token));
            return ServerManager.buildResp(Response.ok()).build();
        } catch (OAuthException e) {
            ServerManager.log.info("errorThrown");
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response readRoles(String auth, String key, Integer userId, SecurityContext securityContext) {
        // TODO Prototype 2 goal, more well developed
        return null;
    }

    @Override
    public Response loginUser(String userName, String password, String clientId, String clientSecret,
            SecurityContext securityContext) {
        //return Response.ok().build();/*
        OAuthToken retToken = null;
        AccessToken token = new AccessToken();
        User retUser = null;
        try {
            OAuthRequestToken requestToken = (OAuthRequestToken) ServerManager.AuthManager
                    .makeRequestToken(clientSecret, "", null, null);
            String verifier = ServerManager.AuthManager.authoriseRequestToken(clientSecret, requestToken.getToken());
            ServerManager.log.info("Verifier API is " + verifier);
            retToken = ServerManager.AuthManager.makeAccessToken(clientSecret, requestToken.getToken(), verifier);
            retUser = userAccess.read(userName);
            if (retUser == null || !retUser.getPassword().equals(password)) {
                return ServerManager.buildResp(Response.status(404)).entity("Unable to find a user with the provided information").build();
            }
        } catch (OAuthException e) {
            ServerManager.log.info("errorThrown");
            return ServerManager.buildResp(Response.status(401).entity(e.getMessage())).build();
        }
        token.setAccessToken(retToken.getToken());
        token.setExpiresIn(new BigDecimal(retToken.getTimeToLive()));
        ServerManager.log.info("Preparing to output token to server");
        try {
            return ServerManager.buildResp(Response.ok(map.writeValueAsString(token), MediaType.APPLICATION_JSON)).build();
        } catch (JsonProcessingException e) {
            return ServerManager.buildResp(Response.status(401).entity(e.getMessage())).build();
        }
    }

}
