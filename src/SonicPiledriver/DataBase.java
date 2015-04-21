/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SonicPiledriver.SonicPiledriver;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jeff
 * 
 */
public class Database {

    //Fields
    protected Connection connect;
    protected ArrayList<String> tables;

    //Constructor
    public Database() throws Exception {
        String dbPath = "./Credentials.db";

        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + dbPath;

        connect = DriverManager.getConnection(url);

    } //End Constructor
    
    

    //Gets the current user's registered friends
    public HashMap<String, String> getFriends(String username) throws SQLException { 

        HashMap<String, String> buddies = new HashMap<>();

        Statement stmt = connect.createStatement();

        ResultSet resSet = stmt.executeQuery("SELECT * FROM 'friends' WHERE username='" + username + "';");

        while (resSet.next()) {
            buddies.put(resSet.getString(0), resSet.getString(2));
        }

        stmt.close();

        return buddies;
    } //Ends getFriends
    
    //
    public String getIP (String username) throws SQLException{
        
        String IP;
        Statement stmt = connect.createStatement();
        
         ResultSet resSet = stmt.executeQuery("SELECT 'IP' FROM 'login' WHERE username='" + username + "';");
        
         return IP;
    }
    
    //Checks Login Credentials
    public boolean isValid(String username, String password) {
        try {
            Statement stmt = connect.createStatement();

            boolean hasRecord;

            ResultSet resSet = stmt.executeQuery("SELECT * FROM 'login' WHERE username='" + username + "' AND password='" + password + "';");
            hasRecord = resSet.next();

            if (stmt != null) {
                stmt.close();
            }

            return hasRecord;
        } catch (SQLException e) {
            return false;
        }

    } //ends isValid

    //Kills the database connection to avoid memory leaks.
    public void killDB() throws SQLException {
        if (connect != null) {
            connect.close();
        }

    } //End killDB()

}
