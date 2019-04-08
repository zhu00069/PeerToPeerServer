/**
* File: InsuranceDAOTest.java
* Description: Junit test on InsuranceDAO class
* Date: March 2019
* @authors Bo Zhu
* Modified:
*/
package dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.swagger.model.Insurance;

public class InsuranceDAOTest {
    InsuranceDAO insuranceDAO;
    Insurance insurance;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    @Before
    public void setUp() throws Exception {
        insuranceDAO = new InsuranceDAO();
        insurance = new Insurance();
        con = DBConnection.getConnection();
    }

    @After
    public void tearDown() {
        insuranceDAO = null;
        insurance = null;
        
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
    public void testRead() {
        insurance = insuranceDAO.read(1);
        assertEquals(insurance.getInsuranceNo(), "I00001");
        assertEquals(insurance.getOpportunityID().intValue(), 1);
        assertEquals(insurance.getInsuranceCompany(), "Insurance Company1");
        assertEquals(new BigDecimal(10000.00),insurance.getInsuranceAmount());
        assertEquals(java.sql.Timestamp.valueOf("2019-02-10 00:00:00").toString(),insurance.getEffectiveDate());
 
        
    }

    @Test
    public void testUpdate() {
        insurance.setInsuranceNo("I00002");
        insurance.setOpportunityID(1);
        insurance.setInsuranceCompany("Insurance Company Update");
        insurance.setInsuranceAmount(new BigDecimal(10000));
        insurance.setEffectiveDate(java.sql.Date.valueOf("2019-02-10").toString());
        
        insuranceDAO.update(insurance);
        assertEquals(insurance.getInsuranceNo(), "I00002");
        //assertEquals(1, insurance.getOpportunityID());
        assertEquals(insurance.getInsuranceCompany(), "Insurance Company Update");
        assertEquals(insurance.getInsuranceAmount(), new BigDecimal(10000.00));
        assertEquals(insurance.getEffectiveDate(), java.sql.Date.valueOf("2019-02-10").toString());
    }

    @Test
    public void testCreate() {
        
        insurance.setInsuranceNo("I00010");
        insurance.setOpportunityID(10);
        insurance.setInsuranceCompany("Insurance Company10");
        insurance.setInsuranceAmount(new BigDecimal(10000));
        insurance.setEffectiveDate(java.sql.Date.valueOf("2019-02-10").toString());
        insurance = insuranceDAO.create(insurance);  
 
        assertEquals(insurance.getInsuranceNo(), "I00010");
        assertEquals(insurance.getOpportunityID().intValue(), 10);
        assertEquals(insurance.getInsuranceCompany(), "Insurance Company10");
        assertEquals(new BigDecimal(10000),insurance.getInsuranceAmount());
        assertEquals(java.sql.Date.valueOf("2019-02-10").toString(),insurance.getEffectiveDate());
    }

//    @Test
//    public void testDelete() {
//      
//    }

}
