/**
* File: TransactionDAO.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: DAO implementation class of Transaction
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.swagger.model.Transaction;
import server.management.ServerManager;

/**
 * Connect to database and display a transaction by ID/name, add/edit/delete a transaction.
 * @author Y. Fu
 *
 */
public class TransactionDAO implements IDAO<Transaction> {

	// SQL query statement

    private static final String GET_TRANSACTION_BY_ID = "SELECT * FROM TRANSACTION_T WHERE transactionID = ?";
    private static final String CREATE_TRANSACTION = "INSERT INTO TRANSACTION_T (userID,opportunityID,amount,tran_type,transactionDate,createDate) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_TRANSACTION = "UPDATE TRANSACTION_T SET userID = ?, opportunityID = ?, amount = ?, tran_type = ? , transactionDate = ? WHERE transactionID = ?";
    private static final String DELETE_TRANSACTION = "DELETE FROM TRANSACTION_T WHERE transactionID = ?";

    /**
     * constructor
     */
    public TransactionDAO() {
        ServerManager.log.info("Created TransactionDAO");

    }

    @Override
    public Transaction read(int id) {
        Transaction retTran = new Transaction();
        retTran.setTransactionID(id);
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // search a transaction by id

            PreparedStatement readTran = dbConnect.prepareStatement(GET_TRANSACTION_BY_ID);
            // set parameter
            readTran.setInt(1, id);
            ResultSet readSet = readTran.executeQuery();
            // while loop to get all the results
            while (readSet.next()) {
                retTran.setUserID(readSet.getInt("userID"));
                retTran.setOpportunityID(readSet.getInt("opportunityID"));
                retTran.setAmount(readSet.getBigDecimal("amount"));
                retTran.setTranType(readSet.getString("tran_type"));
                retTran.setTranDate(readSet.getString("transactionDate"));
                retTran.setCreateDate(readSet.getString("createDate"));
            }
            // ServerManager.log.info(retOpp.toString());
            dbConnect.close();
            return retTran;
        } catch (SQLException e) {
            ServerManager.log.error("Poorly formed SQL provided " + e);
        }
        return null;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
        ServerManager.log.info("Updating transaction");
        // try catch clause to handle exception
        try {

        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // update a transaction

            PreparedStatement ps = dbConnect.prepareStatement(UPDATE_TRANSACTION);
            // set parameter
            ps.setInt(1, toUpdate.getUserID());
            ps.setInt(2, toUpdate.getOpportunityID());
            ps.setBigDecimal(3, toUpdate.getAmount());
            ps.setString(4, toUpdate.getTranType());
            ps.setDate(5, java.sql.Date.valueOf(toUpdate.getTranDate()));
            ps.setInt(6, toUpdate.getTransactionID());
            ps.executeUpdate();
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        return toUpdate;
    }

    @Override
    public Transaction create(Transaction toAdd) {
        ServerManager.log.info("Creating transaction");
        // try catch clause to handle exception
        try {

        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // create a new transaction
            PreparedStatement ps = dbConnect.prepareStatement(CREATE_TRANSACTION, new String[] { "transactionID" });
            // set parameter

            ps.setInt(1, toAdd.getUserID());
            ps.setInt(2, toAdd.getOpportunityID());
            ps.setBigDecimal(3, toAdd.getAmount());
            ps.setString(4, toAdd.getTranType());
            ps.setDate(5, java.sql.Date.valueOf(toAdd.getTranDate()));
            ps.executeUpdate();
            ResultSet gKey = ps.getGeneratedKeys();
            if (gKey.next()) {
                int id = Integer.parseInt(gKey.getString(1));
                toAdd.setTransactionID(id);
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
            // TODO Correct dependencies for the delete (Cascading what is effected if we
            // delete a Transaction row)

        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // delete a transaction

            PreparedStatement ps = dbConnect.prepareStatement(DELETE_TRANSACTION);
            // set parameter
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            ServerManager.log.error("Improperly Formed SQL " + e);
        }

    }

}