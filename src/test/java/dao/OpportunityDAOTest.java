/**
* File: OpportunityDAOTest.java
* Description: Junit test on OpportunityDAO class
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.swagger.model.Opportunity;


public class OpportunityDAOTest {
    
    OpportunityDAO opportunityDAO;
    Opportunity opportunity;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    @Before
    public void setUp() throws Exception {
        opportunityDAO = new OpportunityDAO();
        opportunity = new Opportunity();
        con = DBConnection.getConnection();
    }

    @After
    public void tearDown() {
        opportunityDAO = null;
        opportunity = null;
        
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

        opportunity = opportunityDAO.read(1);
        assertEquals(opportunity.getOperationID(), 1);
        assertEquals(opportunity.getTitle(), "Financial Dilemma at BBT");
        assertEquals(opportunity.getDescription(), "If approved, the merger of BBT and SunTrust would create the sixth-biggest bank in the U.S., large enough to compete against the country�s largest lenders.");
        assertEquals(opportunity.getStatus(), "CLOSE");
        assertEquals(opportunity.getFundAmount(),new BigDecimal(500000.00));
        assertEquals(opportunity.getFundExpDate(), java.sql.Timestamp.valueOf("2018-01-12 00:00:00").toString());
        assertEquals(opportunity.getCreateDate(), java.sql.Timestamp.valueOf("2019-01-12 00:00:00").toString());
    }

    @Test
    public void testReadList() {
        List<Opportunity> opportunities = Collections.EMPTY_LIST;
        opportunities = new ArrayList<>(62);
        opportunities = opportunityDAO.readList();
        
        for(int i = 0; i < opportunities.size(); i++){
            opportunities.add(opportunities.get(i));      
        }
        assertEquals(opportunities.size(), 62);
        
    }

    @Test
    public void testUpdate() {
      opportunity.setLenderID(2);
      opportunity.setOperationID(1);
      opportunity.setTitle("oppo description update");
      opportunity.setDescription("update Opportunity description for testing");
      opportunity.setStatus("CLOSE");
      opportunity.setFundAmount(new BigDecimal(70000.00));
      opportunity.setFundExpDate(java.sql.Date.valueOf("2019-04-01").toString());
      opportunity.setBorrowerID(1);
      opportunity.setOpportunityID(3);
     
      opportunity = opportunityDAO.update(opportunity);
      assertEquals(opportunity.getTitle(), "oppo description update");
      assertEquals(opportunity.getDescription(), "update Opportunity description for testing");
      assertEquals(opportunity.getStatus(), "CLOSE");
      assertEquals(opportunity.getFundAmount(), new BigDecimal(70000.00));
      assertEquals(opportunity.getFundExpDate(), java.sql.Date.valueOf("2019-04-01").toString());
          
    }

    @Test
    public void testCreate() {
        opportunity.setBorrowerID(1);
        opportunity.setLenderID(1);
        opportunity.setOperationID(1);
        opportunity.setTitle("oppo description test");
        opportunity.setDescription("Create Opportunity description for testing");
        opportunity.setStatus("OPEN");
        opportunity.setFundAmount(new BigDecimal(80000.00));
        opportunity.setFundExpDate(java.sql.Date.valueOf("2019-04-25").toString());
        
        opportunity = opportunityDAO.create(opportunity);
        assertEquals(opportunity.getTitle(), "oppo description test");
        assertEquals(opportunity.getDescription(), "Create Opportunity description for testing");
        assertEquals(opportunity.getStatus(), "OPEN");
        assertEquals(opportunity.getFundAmount(), new BigDecimal(80000.00));
        assertEquals(opportunity.getFundExpDate(), java.sql.Date.valueOf("2019-04-25").toString());
        
        
    }

//    @Test
//    public void testDelete() {
//        fail("Not yet implemented");
//    }

}
