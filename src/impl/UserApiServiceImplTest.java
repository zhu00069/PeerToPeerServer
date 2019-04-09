package impl;
/**
 * This is JUnit test on REST API endpoint with security (UserApiServiceImpl.java)
 * @author Bo Zhu
 *
 */
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DBConnection;
import dao.UserDAO;
import io.swagger.api.JacksonConfig;
import io.swagger.api.impl.UserApiServiceImpl;
import io.swagger.model.AccessToken;
import io.swagger.model.User;
import io.swagger.models.Response;

public class UserApiServiceImplTest {
    UserDAO userAccess;
    UserApiServiceImpl impl;
    ObjectMapper map;
    JacksonConfig conf;

    @Before
    public void setUp() throws Exception {

        userAccess = new UserDAO();
        impl = new UserApiServiceImpl();
        map = new ObjectMapper();
        conf = new JacksonConfig();

    }

    @After
    public void tearDown() throws Exception {

        userAccess = null;
        impl = null;

    }
    // This test works (no need add security when create user)
    @Test
    //@Ignore
    public void testCreateUser() {

        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setCreateDate("2019-04-01");
        user.setCreditScore(600);
        user.setDobDate("1990-01-01");
        user.setEmail("test@gmail.com");
        user.setPassword("1234abc");
        user.setPhone("6131101111");
        user.setUsername("test_username");

        String result = "";

        try {
            result = map.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        javax.ws.rs.core.Response resp = impl.createUser(user, null);
        assertEquals("Should return status 200", 200, resp.getStatus());

    }   

    @Test
    public void testFindUser() {

        javax.ws.rs.core.Response userResp = impl.loginUser("fn_username", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
        
        AccessToken token;
        
        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            System.out.println("get token : " + token.getAccessToken());
            System.out.println("get user Response: " + userResp.getEntity());
            javax.ws.rs.core.Response resp = impl.findUser(token.getAccessToken(), "1234-5678-9101-1121", "fn_username", null);
  
            assertEquals("Should return status 200", 200, resp.getStatus());
            
            impl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);
            
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
    public void testReadUser() {
  
        javax.ws.rs.core.Response userResp = impl.loginUser("fn_username", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
        
        AccessToken token;
        
        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            javax.ws.rs.core.Response resp = impl.readUser(token.getAccessToken(), "1234-5678-9101-1121", 103, null);
          
            assertEquals("Should return status 200", 200, resp.getStatus());
            
            impl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);
            
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
    public void testUpdateUser() {
        //create user
        User user = new User();
        user.setFirstName("elena");
        user.setLastName("an");
        user.setCreateDate("2017-06-24");
        user.setCreditScore(600);
        user.setDobDate("2017-06-24");
        user.setEmail("elena@gmail.com");
        user.setPassword("1234abc");
        user.setPhone("6131102222");
        user.setUsername("elena_username");

        String result = "";

        try {
            result = map.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        javax.ws.rs.core.Response resp = impl.createUser(user, null);
        
        //update user's email
        user.setEmail("update_elena@gmail.com");
        
        try {
            
            result = map.writeValueAsString(user);
            
        } catch (JsonProcessingException e) {
            
            e.printStackTrace();
        }
        
        javax.ws.rs.core.Response userResp = impl.loginUser("elena_username", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        
        AccessToken token;
        
        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
           
            javax.ws.rs.core.Response resp1 = impl.updateUser(token.getAccessToken(), "1234-5678-9101-1121", user, null);
                  
            assertEquals("Should return status 200", 200, resp.getStatus());
            
            impl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);
            
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
    public void testRemoveUser() {
      
        javax.ws.rs.core.Response userResp = impl.loginUser("bob1009", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get: " + userResp.getMetadata());
        
        AccessToken token;
        
        try {
            token = map.readValue(userResp.getEntity().toString(),AccessToken.class);
            
            javax.ws.rs.core.Response resp = impl.removeUser(token.getAccessToken(), "1234-5678-9101-1121", 119, null);
  
            assertEquals("Should return status 200", 200, resp.getStatus());
            
            impl.logoutUser(token.getAccessToken(),"1234-5678-9101-1121" , null);
            
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
    public void testLoginUserStringStringSecurityContext() {
        javax.ws.rs.core.Response userResp = impl.loginUser("fn_username", "1234abc", "p2plWebApp", "1234-5678-9101-1121", null);
        System.out.println("get login user string: " + userResp.getMetadata());
        assertEquals("Should return status 200", 200, userResp.getStatus());      
     
    }

}
