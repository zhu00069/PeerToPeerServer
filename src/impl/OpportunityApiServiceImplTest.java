package impl;
/**
 * This is JUnit test on REST API endpoint with security (OpportunityApiServiceImpl.java)
 * @author Bo Zhu
 *
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.OpportunityDAO;
import dao.UserDAO;
import io.swagger.api.JacksonConfig;
import io.swagger.api.impl.OpportunityApiServiceImpl;
import io.swagger.api.impl.UserApiServiceImpl;
import io.swagger.model.AccessToken;
import io.swagger.model.Opportunity;
import io.swagger.model.User;

public class OpportunityApiServiceImplTest {

    OpportunityDAO oppAccess;
    OpportunityApiServiceImpl impl;
    UserApiServiceImpl userImpl;

    ObjectMapper map;
    JacksonConfig conf;

    @Before
    public void setUp() throws Exception { 

        oppAccess = new OpportunityDAO();
        impl = new OpportunityApiServiceImpl();
        userImpl = new UserApiServiceImpl();
        map = new ObjectMapper();
        conf = new JacksonConfig();
        Opportunity opp = new Opportunity();

    }

    @After
    public void tearDown() throws Exception {
        oppAccess = null;
        impl = null;
    }


    @Test
    //@Ignore
    public void testCreateOpp() {
        
        //login as borrower(frank0009) to post opportunity
        javax.ws.rs.core.Response userResp = userImpl.loginUser("frank0009", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
        
        //create opp id is 121
        Opportunity opp = new Opportunity();
        opp.setBorrowerID(1);
        opp.setLenderID(2);
        opp.setCreateDate("2019-04-01");
        opp.setDescription("This is description of Opportunity1");
        opp.setFundAmount(new BigDecimal(70000.00));
        opp.setFundExpDate("2019-09-01");
        opp.setOperationID(1);
        opp.setStatus("CLOSE");
        opp.setTitle("Opportunity1");

        String result = "";

        try {
            result = map.writeValueAsString(opp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        AccessToken token;

        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            javax.ws.rs.core.Response resp = impl.createOpp(opp, token.getAccessToken(), "1234-5678-9101-1121", null);
            
            assertEquals("Should return status 200", 200, resp.getStatus());

            userImpl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);

        } catch (JsonParseException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        
    }

    @Test
    //@Ignore
    public void testListOpp() {

        javax.ws.rs.core.Response userResp = userImpl.loginUser("frank0009", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
        
        AccessToken token;

        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            javax.ws.rs.core.Response resp = impl.listOpp(token.getAccessToken(), "1234-5678-9101-1121", null);
            
            assertEquals("Should return status 200", 200, resp.getStatus());

            userImpl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);

        } catch (JsonParseException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        

    }

    @Test
    //@Ignore
    public void testReadOpp() {
        
        javax.ws.rs.core.Response userResp = userImpl.loginUser("frank0009", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
     
        AccessToken token;

        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            javax.ws.rs.core.Response resp = impl.readOpp(token.getAccessToken(), "1234-5678-9101-1121", 3, null);
            
            assertEquals("Should return status 200", 200, resp.getStatus());

            userImpl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);

        } catch (JsonParseException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        
    }

    @Test
    //@Ignore
    public void testRemoveOpp() {
        //javax.ws.rs.core.Response resp = impl.removeOpp(81, null);
        //assertEquals("Should return status 200", 200, resp.getStatus());
        javax.ws.rs.core.Response userResp = userImpl.loginUser("frank0009", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
        AccessToken token;

        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            javax.ws.rs.core.Response resp = impl.removeOpp(token.getAccessToken(), "1234-5678-9101-1121", 119, null);
            
            assertEquals("Should return status 200", 200, resp.getStatus());

            userImpl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);

        } catch (JsonParseException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Test
    //@Ignore
    public void testUpdateOpp() {
        
        javax.ws.rs.core.Response userResp = userImpl.loginUser("frank0009", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
        
        //create new opp
        Opportunity opp = new Opportunity();
        opp.setBorrowerID(1);
        opp.setLenderID(2);
        opp.setCreateDate("2019-04-07");
        opp.setDescription("This is description of Opportunity test");
        opp.setFundAmount(new BigDecimal(80000.00));
        opp.setFundExpDate("2019-09-01");
        opp.setOperationID(1);
        opp.setStatus("CLOSE");
        opp.setTitle("Opp test");

        String result = "";

        try {
            result = map.writeValueAsString(opp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        AccessToken token;

        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            //createOpp() called
            javax.ws.rs.core.Response resp1 = impl.createOpp(opp, token.getAccessToken(), "1234-5678-9101-1121", null);
            
            //update opp's title
            opp.setTitle("Update Opp title");
            //updateOpp() called
            javax.ws.rs.core.Response resp2 = impl.updateOpp(token.getAccessToken(), "1234-5678-9101-1121", opp, null);

            userImpl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);

        } catch (JsonParseException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        


    }

}
