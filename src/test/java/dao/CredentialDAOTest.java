/**
* File: CredentialDAOTest.java
* Description: Junit test on CredentialDAO class
* Date: March 2019
* @authors Bo Zhu
* Modified:
*/
package dao;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dao.CredentialDAO;
import io.swagger.model.Credential;


public class CredentialDAOTest {

    CredentialDAO credentialDAO;
    Credential credential;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Before
    public void setUp() throws SQLException{
        credentialDAO = new CredentialDAO();
        credential = new Credential();    
        con = DBConnection.getConnection();

    }

    @After
    public void tearDown() {
        credentialDAO = null;
        credential = null;
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
        credential = credentialDAO.read(1);
        assertEquals(credential.getCredentialName(), "NAME1");

    }

    @Test
    public void testReadString() {
        credential = credentialDAO.read("Borrower");
        assertEquals(credential.getCredentialID().intValue(), 21);

    }

    @Test
    public void testUpdate() {

        credential.setCredentialName("Borrower");
        credentialDAO.update(credential);
        assertEquals(credential.getCredentialName(), "Borrower");

    }

    @Test
    public void testCreate() {
        credential.setCredentialName("CRED1");
        credential = credentialDAO.create(credential);   
        assertEquals(credential.getCredentialName(), "CRED1");

    }

    //    @Test
    //    public void testDelete() {
    //        
    //    }

}
