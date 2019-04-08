package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.swagger.model.User;

public class UserDAOTest {
    
    UserDAO userDAO;
    User user;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

  
    @Before
    public void setUp() throws Exception {
        
        userDAO = new UserDAO();
        user = new User();
        con =DBConnection.getConnection();
    }

    @After
    public void tearDown() {
        userDAO = null;
        user = null;
        
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Test
    public void testReadInt() {
        user = userDAO.read(1);
        assertEquals(user.getUsername(), "stanley00069");
        assertEquals(user.getPassword(), "1234abc");
        assertEquals(user.getFirstName(), "Stanley");
        assertEquals(user.getLastName(), "Smith");
        assertEquals(user.getPhone(), "613747999");
        assertEquals(user.getEmail(), "stanley001@gmail.com");
    }

    @Test
    public void testReadString() {
        user = userDAO.read("an000022");
        assertEquals(user.getUsername(), "an000022");
        assertEquals(user.getPassword(), "1234abc");
        assertEquals(user.getFirstName(), "Joanna");
        assertEquals(user.getLastName(), "Lee");
        assertEquals(user.getPhone(), "6135566785");
        assertEquals(user.getEmail(), "joanna@hotmail.com");

    }

    @Test
    public void testUpdate() {
        
        user.setUsername("username");
        user.setPassword("123abc");
        user.setFirstName("firstname update");
        user.setLastName("lastname update");
        user.setPhone("6131101111");
        user.setDobDate(java.sql.Date.valueOf("1985-04-21").toString());
        user.setEmail("update@email.com");
        user.setCreditScore(800);
        user.setId(11);
        
        user = userDAO.update(user);   
        assertEquals(user.getUsername(), "username");
        assertEquals(user.getPassword(), "123abc");
        assertEquals(user.getFirstName(), "firstname update");
        assertEquals(user.getLastName(), "lastname update");
        assertEquals(user.getPhone(), "6131101111");
        assertEquals(user.getDobDate(),  java.sql.Date.valueOf("1985-04-21").toString());
        assertEquals(user.getEmail(), "update@email.com");
        assertEquals(user.getCreditScore().intValue(), 800);
         
        
    }

    @Test
    public void testCreate() {
        
        user.setUsername("username1");
        user.setPassword("1234abc");
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first@gmail.com");
        user.setPhone("6132201111");
        user.setDobDate(java.sql.Date.valueOf("1983-04-21").toString());
        user.setCreditScore(700);
        
        user = userDAO.create(user);  
        assertEquals(user.getUsername(), "username1");
        assertEquals(user.getPassword(), "1234abc");
        assertEquals(user.getFirstName(), "first");
        assertEquals(user.getLastName(), "last");      
        assertEquals(user.getEmail(), "first@gmail.com");
        assertEquals(user.getPhone(), "6132201111");
        assertEquals(user.getDobDate(), java.sql.Date.valueOf("1983-4-21").toString());
        assertEquals(user.getCreditScore().intValue(), 700);
      
    }

//    @Test
//    public void testDelete() {
//        fail("Not yet implemented");
//    }

}
