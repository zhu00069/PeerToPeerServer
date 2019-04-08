/**
* File: OpportunityDAO.java
*
* Creation Date: Feb 12, 2019
* @authors Ryan
* 
* Description: DAO implementation class of Opportunity
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.Path;

import io.swagger.model.User;
import server.management.ServerManager;

/**
 * Connect to database and display a user by ID/name, add/edit/delete a user.
 * @author Ryan
 *
 */
@Path("")
public class UserDAO implements IDAO<User> {

    // Creates the DAO including the datasource reference for it to use throughout
    // the other operations
    public UserDAO() {
        ServerManager.log.info("Created UserDAO");
    }

    // This is the function used to read an entry from the database using a specific
    // userID
    @Override
    public User read(int id) {
    	// try catch clause to handle exception
        try {
            ServerManager.log.info("Started Read operation");
            Connection dbConnect = DBConnection.getConnection();
            // Creating the preparedstatement, this is just the sql query
            PreparedStatement readUser = dbConnect.prepareStatement("SELECT * FROM USER_T WHERE USERID = ?");
            readUser.setInt(1, id);
            // Resultset stores the returned information from the database
            ResultSet rs = readUser.executeQuery();
            User retUser = new User();
            while (rs.next()) {
                // Iterate over the returned information and populate the value of the return
                // user with the values from
                // the database
                ServerManager.log.info("creating output");
                retUser.setId(rs.getInt("USERID"));
                retUser.setUsername(rs.getString("userName"));
                retUser.setFirstName(rs.getString("firstName"));
                retUser.setLastName(rs.getString("lastName"));
                retUser.setEmail(rs.getString("email"));
                retUser.setPassword(rs.getString("pswd"));
                retUser.setPhone(rs.getString("phone"));
            }
            // Close the connection to use later
            dbConnect.close();
            ServerManager.log.info("DB connection Closed");
            return retUser;
        } catch (SQLException e) {
            ServerManager.log.error("Issue with the provided SQL Request " + e);
        }
        return null;
    }

    // Same as read(id), but this time uses one of the fields (username) for the
    // retreival
    public User read(String userName) {
    	// try catch clause to handle exception
        try {
            ServerManager.log.info("Started Read operation");
            Connection dbConnect = DBConnection.getConnection();
            ServerManager.log.info("Reading using: " + userName);
            // Write the query
            PreparedStatement readUser = dbConnect.prepareStatement("SELECT * FROM USER_T WHERE userName = ?");
            readUser.setString(1, userName);
            // Store the result in the resultset
            ResultSet rs = readUser.executeQuery();
            User retUser = new User();

            while (rs.next()) {
                // Iterate over returned data and populate the return user
                ServerManager.log.info("creating output");
                retUser.setId(rs.getInt("USERID"));
                retUser.setUsername(rs.getString("userName"));
                retUser.setFirstName(rs.getString("firstName"));
                retUser.setLastName(rs.getString("lastName"));
                retUser.setEmail(rs.getString("email"));
                retUser.setDobDate(rs.getDate("DOB").toString());
                retUser.setPassword(rs.getString("pswd"));
                retUser.setPhone(rs.getString("phone"));
            }
            // Close the database connection so it can be used in other operations
            dbConnect.close();
            ServerManager.log.info("DB connection Closed");
            return retUser;
        } catch (SQLException e) {
            ServerManager.log.error("Issue with the provided SQL Request " + e);
        }
        return null;
    }

    @Override
    // Update a user with information supplied from the front end
    public User update(User toUpdate) {
    	// try catch clause to handle exception
        try {
            Connection dbConnect = DBConnection.getConnection();
            ServerManager.log.info(toUpdate.toString());
            // Write the DB Query
            PreparedStatement ps = dbConnect.prepareStatement(
                    "UPDATE USER_T SET credentialID = ?,userName = ?, pswd = ? , firstName = ?, lastName = ?, DOB = ?, email = ?, phone = ?, creditScore = ? WHERE userId = ?");
            // populate the prepared statement values with the information from the front
            // end
            ps.setInt(1, 1);
            // The above line is a placeholder, once the credential DAO is populated we can
            // start interacting with it
            ps.setString(2, toUpdate.getUsername());
            ps.setString(3, toUpdate.getPassword());
            ps.setString(4, toUpdate.getFirstName());
            ps.setString(5, toUpdate.getLastName());
            ps.setDate(6, java.sql.Date.valueOf(toUpdate.getDobDate()));
            ps.setString(7, toUpdate.getEmail());
            ps.setString(8, toUpdate.getPhone());
            ps.setInt(9, toUpdate.getCreditScore());
            ps.setInt(10, toUpdate.getId());
            // Execute the update, and commits the data to the database
            ps.executeUpdate();
            // Close the connection for future use
            dbConnect.close();
            return toUpdate;
        } catch (SQLException e) {
            ServerManager.log.error("Issue with the provided SQL Request " + e);
        }
        return toUpdate;
    }

    @Override
    // Create a user from information provided in the front end
    public User create(User body) {
    	// try catch clause to handle exception
        try {
            Connection dbConnect = DBConnection.getConnection();// Create the query to run
            PreparedStatement ps = dbConnect.prepareStatement(
                    "INSERT INTO USER_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES(?,?,?,?,?,?,?,?,?,?)",
                    new String[] { "userID" });
            // PLACE HOLDER//
            ps.setInt(1, 1);
            // TODO:Implement Credential DAO
            /// populate the prepared statement with information supplied from the front end
            ps.setString(2, body.getUsername());
            ps.setString(3, body.getPassword());
            ps.setString(4, body.getFirstName());
            ps.setString(5, body.getLastName());
            ps.setString(6, body.getDobDate());
            ps.setString(7, body.getEmail());
            ps.setString(8, body.getPhone());
            ps.setInt(9, body.getCreditScore().intValue());
            ps.setDate(10, java.sql.Date.valueOf(java.time.LocalDate.now()));
            // Execute the query adding the item to the database
            ps.executeUpdate();
            // Return the resulting generated key
            ResultSet gKey = ps.getGeneratedKeys();
            if (gKey.next()) {
                int id = Integer.parseInt(gKey.getString(1));
                // Set the id to the newly generated id
                body.setId(id);
            }
            ServerManager.log.info(body.toString());
            dbConnect.close();
            // Close the connection and return the infromation to the front-end including
            // the now created userID
            return body;
        } catch (SQLException e) {
            ServerManager.log.error("Issue with the provided SQL Request: " + e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    // Remove a user by id from the database
    public void delete(int id) {
    	// try catch clause to handle exception
        try {
            // TODO Correct dependencies for the delete (Cascading what is effected if we
            // delete a user row)
            Connection dbConnect = DBConnection.getConnection();
            PreparedStatement ps = dbConnect.prepareStatement("DELETE FROM USER_T WHERE userID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            dbConnect.close();
        } catch (SQLException e) {
            ServerManager.log.error("Issue with the provided SQL Request " + e);
        }
    }
}
