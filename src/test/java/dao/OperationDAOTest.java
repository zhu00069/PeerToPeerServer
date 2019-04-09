/**
* File: OperationDAOTest.java
* Description: Junit test on OperationDAO class
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

import io.swagger.model.Operation;

public class OperationDAOTest {
    
     
    OperationDAO operationDAO;
    Operation operation;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Before
    public void setUp() throws Exception {
        
        operationDAO = new OperationDAO();
        operation = new Operation();
        con = DBConnection.getConnection();
    }

    @After
    public void tearDown(){
        operationDAO = null;
        operation = null;
        
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
        operation = operationDAO.read(1);
        assertEquals(operation.getAmount(),  new BigDecimal(100000.00));
        assertEquals(operation.getAmountRepaid(),  new BigDecimal(10000.00));
        assertEquals(operation.getDueDate(), java.sql.Timestamp.valueOf("2019-03-25 00:00:00").toString());
        assertEquals(operation.getDuePenalty(), new BigDecimal(5000.00));
        assertEquals(operation.getInterest(),  new BigDecimal(10.00));
        assertEquals(operation.getCreateDate(), java.sql.Timestamp.valueOf("2019-01-21 00:00:00").toString());
        
    }

    @Test
    public void testReadList() {
        List<Operation> operations = Collections.EMPTY_LIST;
        operations = new ArrayList<>(3);
        operations = operationDAO.readList();
        for(int i = 0; i < operations.size(); i++){
            operations.add(operations.get(i));      
        }
        assertEquals(operations.size(), 3);
        
  }

    @Test
    public void testUpdate() {
        
        
      operation.setAmount(new BigDecimal(50000.00));
      operation.setAmountRepaid(new BigDecimal(5000.00));
      operation.setDueDate(java.sql.Date.valueOf("2019-05-21").toString());
      operation.setDuePenalty(new BigDecimal(1000.00));
      operation.setInterest(new BigDecimal(20.00));
  
      operation = operationDAO.update(operation);   
      assertEquals(operation.getAmount(), new BigDecimal(50000.00));
      assertEquals(operation.getAmountRepaid(), new BigDecimal(5000.00));
      assertEquals(operation.getDueDate(), java.sql.Date.valueOf("2019-05-21").toString());
      assertEquals(operation.getDuePenalty(), new BigDecimal(1000.00));
      assertEquals(operation.getInterest(), new BigDecimal(20.00));

    }

    @Test
    public void testCreate() {
       
        operation.setOperationID(1);
        operation.setAmount(new BigDecimal(20000.00));
        operation.setAmountRepaid(new BigDecimal(5000.00));
        operation.setDueDate(java.sql.Date.valueOf("2019-04-21").toString());
        operation.setDuePenalty(new BigDecimal(1000.00));
        operation.setInterest(new BigDecimal(10.00));
    
        operation = operationDAO.create(operation);   
        assertEquals(operation.getAmount(), new BigDecimal(20000.00));
        assertEquals(operation.getAmountRepaid(), new BigDecimal(5000.00));
        assertEquals(operation.getDueDate(), java.sql.Date.valueOf("2019-04-21").toString());
        assertEquals(operation.getDuePenalty(), new BigDecimal(1000.00));
        assertEquals(operation.getInterest(), new BigDecimal(10.00));

    }

//    @Test
//    public void testDelete() {
//        
//    }

}
