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

/**
 *
 * @author Cody
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

    public boolean isValid(String username, String password){
      try{
          Statement stmt = connect.createStatement();
		
		boolean hasRecord; 
		
		ResultSet resSet = stmt.executeQuery("SELECT * FROM 'login' WHERE username='" + username + "' AND password='" + password  + "';");
		hasRecord = resSet.next();
            
		
		if(stmt != null) {
			stmt.close();
		}            
		
		return hasRecord;
      } catch (SQLException e) {
          return false;
      }
        
 

    }

    //Kills the database connection to avoid memory leaks.
    public void killDB() throws SQLException {
        if (connect != null) {
            connect.close();
        }

    } //End killDB()

}
