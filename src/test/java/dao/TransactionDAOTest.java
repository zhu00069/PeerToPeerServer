/**
* File: TransactionDAOTest.java
* Description: Junit test on TransactionDAO class
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

import io.swagger.model.Transaction;

public class TransactionDAOTest {
    
    TransactionDAO transactionDAO;
    Transaction transaction;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    @Before
    public void setUp() throws Exception {
        
        transactionDAO = new TransactionDAO();
        transaction = new Transaction();
        con = DBConnection.getConnection();
    }

    @After
    public void tearDown() {
        transactionDAO = null;
        transaction = null;
        
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
        transaction = transactionDAO.read(1);
        assertEquals(transaction.getAmount(), new BigDecimal(30000.00));
        assertEquals(transaction.getTranType(), "PAYEE");
        assertEquals(transaction.getTranDate(), java.sql.Timestamp.valueOf("2019-01-01 00:00:00").toString());
        assertEquals(transaction.getCreateDate(), java.sql.Timestamp.valueOf("2018-01-12 00:00:00").toString());

    }

    @Test
    public void testUpdate() {
        transaction.setAmount(new BigDecimal(30000.00));
        transaction.setTranType("PAYEE");
        transaction.setTranDate(java.sql.Date.valueOf("2019-01-18").toString());
        transaction.setUserID(3);
        transaction.setOpportunityID(1);
        transaction.setTransactionID(1);
        
        transaction = transactionDAO.update(transaction); 
        assertEquals(transaction.getAmount(), new BigDecimal(30000.00));
        assertEquals(transaction.getTranType(), "PAYEE");
        assertEquals(transaction.getTranDate(), java.sql.Date.valueOf("2019-01-18").toString());
         
    }

    @Test
    public void testCreate() {
        transaction.setUserID(3);
        transaction.setOpportunityID(1);
        transaction.setAmount(new BigDecimal(10000.00));
        transaction.setTranType("PAYER");
        transaction.setTranDate(java.sql.Date.valueOf("2019-02-21").toString());
         
        transaction = transactionDAO.create(transaction); 
        assertEquals(transaction.getAmount(), new BigDecimal(10000.00));
        assertEquals(transaction.getTranType(), "PAYER");
        assertEquals(transaction.getTranDate(), java.sql.Date.valueOf("2019-02-21").toString());
         
    }

//    @Test
//    public void testDelete() {
//        fail("Not yet implemented");
//    }

}
