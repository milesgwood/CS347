package model;

import java.util.*;
import java.sql.*;
/**
 * This class handles all database transactions for the User class.
 * 
 * @author Christopher Recinos
 * @version 2016-11-28
 */
public class User_DB {
    
    /**
     * 
     * @param user The user being added
     * @return whether or not the operation succeeded
    */
    
    private final DBHandler handler = new DBHandler();
    private Connection con;
    
    /**
     * This method takes in a User object and adds it to the database if
     * the email and username are unique
     * @param user, the User being added to the database
     * @return whether or not the add was successful
     */
    public boolean addUser(User user) {
        boolean userAdded = false;
        
        try {
            if(!checkIfUserExists(user)) {
                
                String name, username, password, email;
                boolean isProfessor;
                int roleId, schoolId; 
                
                String insert_sql = "INSERT INTO user VALUES(null, ?, ?, ?, ?, ?, ?, ?)"; 
                
                PreparedStatement ps = con.prepareStatement(insert_sql);
		
		name = user.getName();
		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();
		isProfessor = user.isProfessor();
		roleId = user.getRoleId();
		schoolId = user.getSchoolId();

		ps.setString(1, password);
		ps.setString(2, email);
		ps.setString(3, name);
		ps.setString(4, username);
		ps.setInt(5, roleId);
		ps.setBoolean(6, isProfessor);
		ps.setInt(7, schoolId);

		ps.executeUpdate();

		userAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
        return userAdded;
    }
    
    /**
     * This method checks to see whether or not the User parameter is present
     * in the database. A User is defined as present if the username and/or
     * email already exists in the database
     * @param user, the User being checked
     * @return true if the user is in the database, false otherwise
     */
    public boolean checkIfUserExists(User user) {
        boolean userFound = false;
        String username, email;
        try{
            if(!(handler.isOpen)) {   
                handler.open();
                con = handler.con;
            }
        
            username = user.getUsername();
            email = user.getEmail();
        
            String command = "SELECT * FROM user WHERE username=? OR email=?;";
            PreparedStatement ps = con.prepareStatement(command);
            ps.setString(1, username);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
               userFound = true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFound;
    }
    
    /**
     * This method will return the User that corresponds to id, or null if
     * no user corresponds to the id
     * @param id, the id for the User to be returned
     * @return found User or null
     */
    public User getUser(int id) {
        User ret_user = null;
        try {
            if(!(handler.isOpen)) {   
                handler.open();
                con = handler.con;
            }
            
            String get_user = "SELECT * FROM user WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(get_user);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                String password, email, name, username;
                boolean isProfessor;
                int roleId, schoolId;
                
                password = rs.getString(2);
                email = rs.getString(3);
                name = rs.getString(4);
                username = rs.getString(5);
                roleId = rs.getInt(6);
                isProfessor = rs.getBoolean(7);
                schoolId = rs.getInt(8);
                
                ret_user = new User(password, email, name, username, roleId, isProfessor, schoolId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret_user;
    }
    public static void main(String[] args) {
        User user = new User("654321", "kesterSON@gmail.com", "Dumbledore", "dbubbs", 2, false, 11);
        System.out.println(new User_DB().addUser(user));
        System.out.println(new User_DB().getUser(5));
    }
}
