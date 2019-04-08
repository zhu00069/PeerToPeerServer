/**
* File: CredentialDAO.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: DAO implementation class of Credential
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.swagger.model.Credential;
import server.management.ServerManager;

/**
 * Connect to database and display a credential by ID/name, add/edit/delete a credential.
 * @author Y. Fu
 *
 */
public class CredentialDAO implements IDAO<Credential> {

	// SQL query statement
    private static final String GET_CREDENTIAL_BY_ID = "SELECT * FROM CREDENTIAL_T WHERE credentialID = ?";
    private static final String GET_CREDENTIAL_BY_CREDENTIALNAME = "SELECT * FROM CREDENTIAL_T WHERE credentialName = ?";
    private static final String CREATE_CREDENTIAL = "INSERT INTO CREDENTIAL_T (credentialName) VALUES(?)";
    private static final String UPDATE_CREDENTIAL = "UPDATE CREDENTIAL_T SET credentialName = ? WHERE credentialId = ?";
    private static final String DELETE_CREDENTIAL = "DELETE FROM CREDENTIAL_T WHERE credentialID = ?";

    /**
     * constructor
     */
    public CredentialDAO() {
        ServerManager.log.info("Created CredentialDAO");
    }

    /*
     * (non-Javadoc)
     * 
     * @see dao.IDAO#read(int)
     */
    @Override
    public Credential read(int id) {
        Credential retCre = new Credential();
        // set id
        retCre.setCredentialID(id);
        // try catch clause to handle exception
        try {
        	  // connect to database
            Connection dbConnect = DBConnection.getConnection();
            // search a credential by id
            PreparedStatement readCre = dbConnect.prepareStatement(GET_CREDENTIAL_BY_ID);
            // set parameter
            readCre.setInt(1, id);
            ResultSet readSet = readCre.executeQuery();
            // while loop to get all the results
            while (readSet.next()) {
                retCre.setCredentialName(readSet.getString("credentialName"));
            }
            // ServerManager.log.info(retCre.toString());
            dbConnect.close();
            return retCre;
        } catch (SQLException e) {
            ServerManager.log.error("Poorly formed SQL provided " + e);
        }
        return null;
    }

    /**
     * search a credential by name and return the result
     * @param credentialName credential name
     * @return the searched credential object
     */
    public Credential read(String credentialName) {
    	// try catch clause to handle exception
        try {
            ServerManager.log.info("Started Read operation");
            // connect to database
            Connection dbConnect = DBConnection.getConnection();
            ServerManager.log.info("Reading using: " + credentialName);
            // search a credential by name
            PreparedStatement readCre = dbConnect.prepareStatement(GET_CREDENTIAL_BY_CREDENTIALNAME);
            // set parameter
            readCre.setString(1, credentialName);
            ResultSet rs = readCre.executeQuery();
            Credential retCre = new Credential();
            // while loop to get all the results
            while (rs.next()) {
                ServerManager.log.info("creating output");
                retCre.setCredentialID(rs.getInt("credentialID"));
                retCre.setCredentialName(rs.getString("credentialName"));
            }
            dbConnect.close();
            ServerManager.log.info("DB connection Closed");
            return retCre;
        } catch (SQLException e) {
            ServerManager.log.error("Issue with the provided SQL Request " + e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see dao.IDAO#update(java.lang.Object)
     */
    @Override
    public Credential update(Credential toUpdate) {
        ServerManager.log.info("Updating credential");
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // update a credential
            PreparedStatement ps = dbConnect.prepareStatement(UPDATE_CREDENTIAL);
            // set parameter
            ps.setString(1, toUpdate.getCredentialName());
            ps.setInt(2, toUpdate.getCredentialID());
            ps.executeUpdate();
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        return toUpdate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see dao.IDAO#create(java.lang.Object)
     */
    @Override
    public Credential create(Credential toAdd) {
        ServerManager.log.info("Creating opportunity");
        // try catch clause to handle exception
        try {
        	// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // create a new credential
            PreparedStatement ps = dbConnect.prepareStatement(CREATE_CREDENTIAL, new String[] { "credentialID" });
            // set parameter
            ps.setString(1, toAdd.getCredentialName());
            ps.executeUpdate();
            ResultSet gKey = ps.getGeneratedKeys();
            if (gKey.next()) {
                int id = Integer.parseInt(gKey.getString(1));
                toAdd.setCredentialID(id);
            }
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Poorly structured SQL Statement " + e);
        }
        ServerManager.log.info(toAdd.toString());
        return toAdd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see dao.IDAO#delete(int)
     */
    @Override
    public void delete(int id) {
    	// try catch clause to handle exception
    	try {
            // TODO Correct dependencies for the delete (Cascading what is effected if we
            // delete a credential row)
    		// connect to database
            Connection dbConnect = DBConnection.getConnection();
            // delete a credential
            PreparedStatement ps = dbConnect.prepareStatement(DELETE_CREDENTIAL);
            // set parameter
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            ServerManager.log.error("Improperly Formed SQL " + e);
        }

    }

}
