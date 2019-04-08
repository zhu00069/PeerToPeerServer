/**
* File: DBConnection.java
*
* Creation Date: Feb 12, 2019
* @authors Ryan
* 
* Description: Database conncetion
*/
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import server.management.ServerManager;

/**
 * Singleton access of the Database
 * 
 * @author Ryan
 * 
 */
public class DBConnection {
    private DataSource dataBase;
    // Eager initialization of the database connection object
    private static final DBConnection connectionInstance = new DBConnection();

    private DBConnection() {
        Context initContext;
        // try catch clause to handle exception
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataBase = (DataSource) envContext.lookup("jdbc/P2PL_DB");
        } catch (NamingException e) {
            ServerManager.log.error("Error in establishing context for database connection " + e);
        }
    }

    /**
     * @return the datasource for use in operations
     */
    public static DataSource getSource() {
        return connectionInstance.dataBase;
    }

    public static Connection getConnection() {

        try {
        if(connectionInstance.dataBase != null) {
            return getSource().getConnection();
        }
        else {
                return DriverManager.getConnection("jdbc:oracle:thin:@p2pl.cnb9hfak1br3.us-east-2.rds.amazonaws.com:1521:P2PL","p2plAdmin","p2plAdmin");
        }
        }catch(SQLException e) {
            ServerManager.log.error("Unable to connect to db " + e.getMessage());
            return null;
        }
    }
}
