/**
* File: OpportunityDAO.java
*
* Creation Date: Feb 12, 2019
* @authors Ryan
* updated by Y. Fu 
* 
* Description: DAO implementation class of Opportunity
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import io.swagger.model.Opportunity;
import io.swagger.model.OpportunityList;
import server.management.ServerManager;

/**
 * Connect to database and display an opportunity by ID/name, add/edit/delete an opportunity.
 * @author Y. Fu
 *
 */
public class OpportunityDAO implements IDAO<Opportunity> {

    // Initalize the reference to the database for future use
    public OpportunityDAO() {
        ServerManager.log.info("Created OpportunityDAO");
    }

    // Read a specific opportunity from the provided id
    @Override
    public Opportunity read(int id) {
        Opportunity retOpp = new Opportunity();
        retOpp.setOpportunityID(id);
        // try catch clause to handle exception
        try {
            Connection dbConnect = DBConnection.getConnection();
            // Write the Query to access the opportunity by id
            PreparedStatement readOpp = dbConnect
                    .prepareStatement("SELECT * FROM OPPORTUNITY_T WHERE opportunityId = ?");
            readOpp.setInt(1, id);

            ResultSet readSet = readOpp.executeQuery();
            while (readSet.next()) {
                // populate the returning opportunity with information from the database
                retOpp.setBorrowerID(readSet.getInt("borrowerID"));
                retOpp.setLenderID(readSet.getInt("lenderID"));
                retOpp.setOperationID(readSet.getInt("operationID"));
                retOpp.setTitle(readSet.getString("oppoTitle"));
                retOpp.setDescription(readSet.getString("oppoDescription"));
                retOpp.setStatus(readSet.getString("status"));
                retOpp.setFundAmount(readSet.getBigDecimal("fundAmount"));
                retOpp.setFundExpDate(readSet.getString("expectedFundDate"));
                retOpp.setCreateDate(readSet.getString("createDate"));
            }
            // close the database connection
            dbConnect.close();
            return retOpp;
        } catch (SQLException e) {
            ServerManager.log.error("Poorly formed SQL provided " + e);
        }
        return null;
    }

    // Return a list of opportunities from the database (currently returns all will
    // need to be adjusted to return a set)
    public OpportunityList readList() {
        OpportunityList retList = new OpportunityList();

        // try catch clause to handle exception

        try {
            Connection dbConnect = DBConnection.getConnection();
            PreparedStatement ps = dbConnect.prepareStatement("SELECT * FROM OPPORTUNITY_T");
            ResultSet readList = ps.executeQuery();
            while (readList.next()) {
                retList.add(this.read(readList.getInt("opportunityID")));
            }
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly formed SQL provided " + e);
        }
        return retList;
    }

    // Update a opportunity with information provided from the front end
    @Override
    public Opportunity update(Opportunity toUpdate) {
        ServerManager.log.info("Updating opportunity");
        // try catch clause to handle exception
        try {
            Connection dbConnect = DBConnection.getConnection();
            // write the sql statemnet to update the provided opportunity
            PreparedStatement ps = dbConnect.prepareStatement(
                    "UPDATE OPPORTUNITY_T SET borrowerID = ?, lenderID = ?, operationID = ?, oppoTitle = ? , oppoDescription = ?, status = ?, fundAmount = ?, expectedFundDate = ?  WHERE opportunityID = ?");
            ps.setInt(1, toUpdate.getBorrowerID());
            ps.setInt(2, toUpdate.getLenderID());
            ps.setInt(3, toUpdate.getOperationID());
            ps.setString(4, toUpdate.getTitle());
            ps.setString(5, toUpdate.getDescription());
            ps.setString(6, toUpdate.getStatus());
            ps.setBigDecimal(7, toUpdate.getFundAmount());
            ps.setDate(8, java.sql.Date.valueOf(toUpdate.getFundExpDate()));
            ps.setInt(9, toUpdate.getOpportunityID());
            // Make the changes in db
            ps.executeUpdate();
            // close the connection to the database
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        return toUpdate;
    }

    // Create a new opportunity from the information provided at the front end
    @Override
    public Opportunity create(Opportunity toAdd) {
        ServerManager.log.info("Creating opportunity");
        // try catch clause to handle exception
        try {
            Connection dbConnect = DBConnection.getConnection();
            // Write a query to access and update an opportunity from the database
            PreparedStatement ps = dbConnect.prepareStatement(
                    "INSERT INTO OPPORTUNITY_T (borrowerID,lenderID,operationID,oppoTitle,oppoDescription,status,fundAmount,expectedFundDate,createDate) VALUES (?,?,?,?,?,?,?,?,?)",
                    new String[] { "opportunityID" });
            ps.setInt(1, toAdd.getBorrowerID());
            ps.setInt(2, toAdd.getLenderID());
            ps.setInt(3, toAdd.getOperationID());
            ps.setString(4, toAdd.getTitle());
            ps.setString(5, toAdd.getDescription());
            ps.setString(6, toAdd.getStatus());
            ps.setBigDecimal(7, toAdd.getFundAmount());
            ps.setDate(8, java.sql.Date.valueOf(toAdd.getFundExpDate()));
            ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.executeUpdate();
            // retrieve the keys generated by adding the opportunity
            ResultSet gKey = ps.getGeneratedKeys();
            if (gKey.next()) {
                int id = Integer.parseInt(gKey.getString(1));
                toAdd.setOpportunityID(id);
            }
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        ServerManager.log.info(toAdd.toString());
        // Return the newly created opportunity with the userid
        return toAdd;
    }

    @Override
    // Remove a specific opportunity from the database based on id
    public void delete(int id) {
    	// try catch clause to handle exception
        try {
            Connection dbConnect = DBConnection.getConnection();
            PreparedStatement ps = dbConnect.prepareStatement("DELETE FROM OPPORTUNITY_T WHERE opportunityID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            ServerManager.log.error("Improperly Formed SQL " + e);
        }

    }

}
