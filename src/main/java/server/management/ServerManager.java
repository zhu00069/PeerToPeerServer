package server.management;

import java.lang.invoke.MethodHandles;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HttpHeaders;

import security.OAuthManager;

/**
 * Expandable Class for instantiation of Static Server Application-Wide Objects
 * 
 * @author Ryan
 *
 */
@Path("")
public class ServerManager extends Application {

    /**
     * Creating a referencable instance of the logger class to use for commenting
     * across the project
     */
    public static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final OAuthManager AuthManager = new OAuthManager();

    public static ResponseBuilder buildResp(ResponseBuilder resp) {
        return resp
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:4200")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Access-Control-Allow-Origin,Access-Control-Allow-Credentials")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                        "clientId, clientSecret,origin,Content-Type,accept,authorization")
                .header(HttpHeaders.ACCEPT, "GET,POST,PUT,DELETE,OPTIONS,HEAD")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
    }
    
    public ServerManager() {
        log.info("Registering Application");
    }
}
