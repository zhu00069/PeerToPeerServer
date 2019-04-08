package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;
import server.management.ServerManager;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.jboss.resteasy.auth.oauth.OAuthException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.OpportunityDAO;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-12T06:56:57.774Z")
public class OpportunityApiServiceImpl implements OpportunityApi {
    private OpportunityDAO oppAccess;
    private JacksonConfig conf;
    private ObjectMapper map;

    public OpportunityApiServiceImpl() {
        oppAccess = new OpportunityDAO();
        try {
            conf = new JacksonConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map = conf.getContext(Opportunity.class);
    }

   
    @Override
    public Response createOpp( Opportunity body, String auth, String key, SecurityContext securityContext) {
        ServerManager.log.info("Creating Opprotinity from: " + body);
        if (auth == null) {
            return Response.status(401).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);
            Opportunity opp = body;
            opp = oppAccess.create(opp);
            return Response.ok(map.writeValueAsString(opp.toString()), MediaType.APPLICATION_JSON).build();
        } catch (OAuthException e) {
            return Response.status(401).entity(e.getMessage()).build();
        } catch (JsonParseException e) {
            ServerManager.log.error("Unable to Parse provided JSON " + e);
            return Response.status(400).entity(e.getMessage()).build();
        } catch (JsonMappingException e) {
            ServerManager.log.error("Unable to map provided JSON " + e);
            return Response.status(400).entity(e.getMessage()).build();
        } catch (IOException e) {
            ServerManager.log.error("Unspecified IOError " + e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response listOpp(String auth, String key, SecurityContext securityContext) {
        ServerManager.log.info("Retreiving a list of Opporotunites");
        if (auth == null) {
            return ServerManager.buildResp(Response.status(401)).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);

            OpportunityList oppList = new OpportunityList();

            oppList = oppAccess.readList();
            try {
                return Response.ok(map.writeValueAsString(oppList), MediaType.APPLICATION_JSON).build();
            } catch (JsonProcessingException e) {
                ServerManager.log.error("An issue with the provided object when mapping to JSON" + e);
            }
            return ServerManager.buildResp(Response.status(400)).entity("No Opportunites to List").build();
        } catch (OAuthException e) {
            ServerManager.log.info("errorThrown");
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    public Response readOpp(String auth, String key, Integer opportunityId, SecurityContext securityContext) {
        ServerManager.log.info("Reading Opportunity with this ID: " + opportunityId);
        if (auth == null) {
            return Response.status(401).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);

            Opportunity opp = oppAccess.read(opportunityId);
            if (opp.getOpportunityID() != null) {
                ServerManager.log.info("opportunity retrieved " + opp.toString());
                try {
                    return ServerManager.buildResp(Response.ok(map.writeValueAsString(opp), MediaType.APPLICATION_JSON)).build();
                } catch (JsonProcessingException e) {
                    ServerManager.log.error("Unable to proccess provided Object into JSON: " + e);
                }
            }
            return ServerManager.buildResp(Response.status(404)).entity("No Opprotunity found with id: " + opportunityId).build();
        } catch (OAuthException e) {
            ServerManager.log.info("errorThrown");
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    public Response removeOpp(String auth, String key, Integer opportunityId, SecurityContext securityContext) {
        ServerManager.log.info("remove " + opportunityId);
        if (auth == null) {
            return ServerManager.buildResp(Response.status(401)).entity("No Token provided").build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);

            oppAccess.delete(opportunityId);
            return Response.ok().build();
        } catch (OAuthException e) {
            ServerManager.log.info("errorThrown");
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        }
    }

    public Response updateOpp(String auth, String key, Opportunity body, SecurityContext securityContext) {
        Opportunity opp = body;
        if (auth == null) {
            return ServerManager.buildResp(Response.status(401).entity("No Token provided")).build();
        }
        String token = auth.split(" ")[1];
        try {
            ServerManager.AuthManager.getAccessToken(key, token);

            opp = oppAccess.update(opp);
            if (opp != null) {
                return ServerManager.buildResp(Response.ok(map.writeValueAsString(opp), MediaType.APPLICATION_JSON)).build();
            }
            return ServerManager.buildResp(Response.status(404)).entity("Unable t    o find the requested opportunity").build();
        } catch (OAuthException e) {
            ServerManager.log.info("errorThrown");
            return ServerManager.buildResp(Response.status(401)).entity(e.getMessage()).build();
        } catch (JsonProcessingException e) {
            return ServerManager.buildResp(Response.status(400)).entity(e.getMessage()).build();
        }
    }

}
