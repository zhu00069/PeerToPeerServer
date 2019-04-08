/**
* File: InsuranceDAO.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: DAO implementation class of Insurance
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.swagger.model.Insurance;
import server.management.ServerManager;

/**
 * Connect to database and display an insurance by ID/name, add/edit/delete an insurance.
 * @author Y. Fu
 *
 */
public class InsuranceDAO implements IDAO<Insurance> {

	// SQL query statement
    private static final String GET_INSURANCE_BY_ID = "SELECT * FROM INSURANCE_T WHERE insuranceID = ?";
    private static final String CREATE_INSURANCE = "INSERT INTO INSURANCE_T (insuranceNo,opportunityID,insuranceCompany,insuranceAmount,effectiveDate) VALUES (?,?,?,?,?)";
    private static final String UPDATE_INSURANCE = "UPDATE INSURANCE_T SET insuranceNo = ?, opportunityID = ?, insuranceCompany = ?, insuranceAmount = ? , effectiveDate = ?  WHERE insuranceID = ?";
    private static final String DELETE_INSURANCE = "DELETE FROM INSURANCE_T WHERE insuranceID = ?";

    /**
     * constructor
     */
    public InsuranceDAO() {
        ServerManager.log.info("Created InsuranceDAO");

    }

    @Override
    public Insurance read(int id) {
        Insurance retIns = new Insurance();
        retIns.setInsuranceID(id);
        // try catch clause to handle exception
        try {
        	  // connect to database
            Connection dbConnect = DBConnection.getConnection();
            // search an insurance by id

            PreparedStatement readIns = dbConnect.prepareStatement(GET_INSURANCE_BY_ID);
            // set parameter
            readIns.setInt(1, id);
            ResultSet readSet = readIns.executeQuery();
            // while loop to get all the results
            while (readSet.next()) {
                retIns.setInsuranceNo(readSet.getString("insuranceNo"));
                retIns.setOpportunityID(readSet.getInt("opportunityID"));
                retIns.setInsuranceCompany(readSet.getString("insuranceCompany"));
                retIns.setInsuranceAmount(readSet.getBigDecimal("insuranceAmount"));
                retIns.setEffectiveDate(readSet.getString("effectiveDate"));
            }
            // ServerManager.log.info(retOpp.toString());
            dbConnect.close();
            return retIns;
        } catch (SQLException e) {
            ServerManager.log.error("Poorly formed SQL provided " + e);
        }
        return null;
    }

    @Override
    public Insurance update(Insurance toUpdate) {
        ServerManager.log.info("Updating insurance");
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // update an insurance

            PreparedStatement ps = dbConnect.prepareStatement(UPDATE_INSURANCE);
            // set parameter
            ps.setString(1, toUpdate.getInsuranceNo());
            ps.setInt(2, toUpdate.getOpportunityID());
            ps.setString(3, toUpdate.getInsuranceCompany());
            ps.setBigDecimal(4, toUpdate.getInsuranceAmount());
            ps.setDate(5, java.sql.Date.valueOf(toUpdate.getEffectiveDate()));
            ps.setInt(6, toUpdate.getInsuranceID());
            ps.executeUpdate();
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        return toUpdate;
    }

    @Override
    public Insurance create(Insurance toAdd) {
        ServerManager.log.info("Creating insurance");
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // create a new insurance
            PreparedStatement ps = dbConnect.prepareStatement(CREATE_INSURANCE, new String[] { "insuranceID" });
            // set parameter

            ps.setString(1, toAdd.getInsuranceNo());
            ps.setInt(2, toAdd.getOpportunityID());
            ps.setString(3, toAdd.getInsuranceCompany());
            ps.setBigDecimal(4, toAdd.getInsuranceAmount());
            ps.setDate(5, java.sql.Date.valueOf(toAdd.getEffectiveDate()));
            ps.executeUpdate();
            ResultSet gKey = ps.getGeneratedKeys();
            if (gKey.next()) {
                int id = Integer.parseInt(gKey.getString(1));
                toAdd.setInsuranceID(id);
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
            // delete an Insurance row)

        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // delete an insurance

            PreparedStatement ps = dbConnect.prepareStatement(DELETE_INSURANCE);
            // set parameter
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            ServerManager.log.error("Improperly Formed SQL " + e);
        }

    }

}