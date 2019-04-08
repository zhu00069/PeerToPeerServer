/**
* File: OperationDAO.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: DAO implementation class of Operation
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import io.swagger.model.Operation;
import server.management.ServerManager;

/**
 * Connect to database and display an operation by ID/name, add/edit/delete an operation.
 * @author Y. Fu
 *
 */
public class OperationDAO implements IDAO<Operation> {


	// SQL query statement

    private static final String GET_OPERATION_BY_ID = "SELECT * FROM OPERATION_T WHERE operationId = ?";
    private static final String GET_OPERATION_LIST = "SELECT * FROM OPERATION_T";
    private static final String CREATE_OPERATION = "INSERT INTO OPERATION_T (amount,amountRepaid,dueDate,duePenalty,interest,createDate) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_OPERATION = "UPDATE OPERATION_T SET amount = ?, amountRepaid = ?, dueDate = ?, duePenalty = ? , interest = ?  WHERE operationID = ?";
    private static final String DELETE_OPERATION = "DELETE FROM OPERATION_T WHERE operationID = ?";

    /**
     * constructor
     */
    public OperationDAO() {
        ServerManager.log.info("Created OperationDAO");
    }

    @Override
    public Operation read(int id) {
        Operation retOper = new Operation();
        retOper.setOperationID(id);
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // search an operation by id

            PreparedStatement readOpp = dbConnect.prepareStatement(GET_OPERATION_BY_ID);
            // set parameter
            readOpp.setInt(1, id);
            ResultSet readSet = readOpp.executeQuery();
            // while loop to get all the results
            while (readSet.next()) {
                retOper.setAmount(readSet.getBigDecimal("amount"));
                retOper.setAmountRepaid(readSet.getBigDecimal("amountRepaid"));
                retOper.setDueDate(readSet.getString("dueDate"));
                retOper.setDuePenalty(readSet.getBigDecimal("duePenalty"));
                retOper.setInterest(readSet.getBigDecimal("interest"));
                retOper.setCreateDate(readSet.getString("createDate"));
            }
            // ServerManager.log.info(retOpp.toString());
            dbConnect.close();
            return retOper;
        } catch (SQLException e) {
            ServerManager.log.error("Poorly formed SQL provided " + e);
        }
        return null;
    }

    public List<Operation> readList() {
        ArrayList<Operation> retList = new ArrayList<Operation>();
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // get a list of operations

            PreparedStatement ps = dbConnect.prepareStatement(GET_OPERATION_LIST);
            ResultSet readList = ps.executeQuery();
            while (readList.next()) {
                retList.add(this.read(readList.getInt("operationID")));
            }
        } catch (SQLException e) {
            ServerManager.log.error("Poorly formed SQL provided " + e);
        }
        return retList;
    }

    @Override
    public Operation update(Operation toUpdate) {
        ServerManager.log.info("Updating operation");
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // update an operation

            PreparedStatement ps = dbConnect.prepareStatement(UPDATE_OPERATION);
            // set parameter
            ps.setBigDecimal(1, toUpdate.getAmount());
            ps.setBigDecimal(2, toUpdate.getAmountRepaid());
            ps.setDate(3, java.sql.Date.valueOf(toUpdate.getDueDate()));
            ps.setBigDecimal(4, toUpdate.getDuePenalty());
            ps.setBigDecimal(5, toUpdate.getInterest());
            ps.setInt(6, toUpdate.getOperationID());
            ps.executeUpdate();
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        return toUpdate;
    }

    @Override
    public Operation create(Operation toAdd) {
        ServerManager.log.info("Creating operation");
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // create an operation
            PreparedStatement ps = dbConnect.prepareStatement(CREATE_OPERATION, new String[] { "operationID" });
            // set parameter

            ps.setBigDecimal(1, toAdd.getAmount());
            ps.setBigDecimal(2, toAdd.getAmountRepaid());
            ps.setDate(3, java.sql.Date.valueOf(toAdd.getDueDate()));
            ps.setBigDecimal(4, toAdd.getDuePenalty());
            ps.setBigDecimal(5, toAdd.getInterest());
            ps.executeUpdate();
            ResultSet gKey = ps.getGeneratedKeys();
            if (gKey.next()) {
                int id = Integer.parseInt(gKey.getString(1));
                toAdd.setOperationID(id);
            }
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        ServerManager.log.info(toAdd.toString());
        return toAdd;
    }

    @Override
    public void delete(int id) {
    	// try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // delete an operation

            PreparedStatement ps = dbConnect.prepareStatement(DELETE_OPERATION);
            // set parameter
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            ServerManager.log.error("Improperly Formed SQL " + e);
        }

    }

}
