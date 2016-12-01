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
     * This method takes in a User object and adds it to the database if the
     * email and username are unique
     *
     * @param user, the User being added to the database
     * @return whether or not the add was successful
     */
    public boolean addUser(User user) {
        boolean userAdded = false;

        try {
            if (!checkIfUserExists(user)) {

                String name, username, password, email;
                boolean isProfessor;
                int roleId, schoolId;

                String insertSql = "INSERT INTO user VALUES(null, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(insertSql);

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
     * This method will search the database for the user and delete them if they're
     * found
     * @param user, the User being deleted
     * @return true if the user is successfully deleted, false otherwise.
     */
    public boolean deleteUser(User user) {
        boolean delUser = false;
        
        try {
            if (!(handler.isOpen)) {
                handler.open();
                con = handler.con;
            }
            
            User temp = getUser(user.getUsername(), user.getEmail());
            
            if(temp != null) {
                String delUserCommand = "DELETE FROM user WHERE username = ? AND email = ?;";
                
                String username, email;
                username = user.getUsername();
                email = user.getEmail();
                
                PreparedStatement ps = con.prepareStatement(delUserCommand);
                ps.setString(1, username);
                ps.setString(2, email);
                
                ps.executeUpdate();
                
                delUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return delUser;
    }

    /**
     * This method checks to see whether or not the User parameter is present in
     * the database. A User is defined as present if the username and/or email
     * already exists in the database
     *
     * @param user, the User being checked
     * @return true if the user is in the database, false otherwise
     */
    public boolean checkIfUserExists(User user) {
        boolean userFound = false;
        String username, email;
        try {
            if (!(handler.isOpen)) {
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

            if (rs.next()) {
                userFound = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFound;
    }

    /**
     * This method will return the User that corresponds to id, or null if no
     * user corresponds to the id
     *
     * @param username, the username of the User being searched for
     * @param email, the email of the User being searched for
     * @return found User or null
     */
    public User getUser(String username, String email) {
        User retUser = null;
        try {
            if (!(handler.isOpen)) {
                handler.open();
                con = handler.con;
            }

            String getUser = "SELECT * FROM user WHERE username = ? AND email = ?;";
            PreparedStatement ps = con.prepareStatement(getUser);
            ps.setString(1, username);
            ps.setString(2, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String password, name;
                boolean isProfessor;
                int roleId, schoolId;

                password = rs.getString(2);
                name = rs.getString(4);
                roleId = rs.getInt(6);
                isProfessor = rs.getBoolean(7);
                schoolId = rs.getInt(8);

                retUser = new User(password, email, name, username, roleId, isProfessor, schoolId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retUser;
    }

    /**
     * This method returns all users in the database
     *
     * @return An arraylist of user objects corresponding to all users in the
     * database. Returns null if there are no users in the database
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> retUsers = new ArrayList<User>();

        String password, email, name, username;
        boolean isProfessor;
        int roleId, schoolId;

        try {
            if (!(handler.isOpen)) {
                handler.open();
                con = handler.con;
            }
            String getAllUsers = "SELECT * FROM user;";
            PreparedStatement ps = con.prepareStatement(getAllUsers);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                password = rs.getString(2);
                email = rs.getString(3);
                name = rs.getString(4);
                username = rs.getString(5);
                roleId = rs.getInt(6);
                isProfessor = rs.getBoolean(7);
                schoolId = rs.getInt(8);

                retUsers.add(new User(password, email, name, username, roleId, isProfessor, schoolId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (retUsers.isEmpty()) {
            retUsers = null;
        }
        return retUsers;
    }

    public static void main(String[] args) {
        User user = new User("654321", "kester$ON@gmail.com", "Dumbledore", "dbubbs", 2, false, 11);
        User otherUser = new User("8973941", "the@briankendrick.com", "Brian Kendrick", "bk", 2, true, 12);
        System.out.println(new User_DB().addUser(otherUser));
        System.out.println(new User_DB().getUser("bk", "the@briankendrick.com"));
        System.out.println(new User_DB().deleteUser(otherUser));
        System.out.println(new User_DB().deleteUser(user));
        System.out.println(new User_DB().getUser("bk", "the@briankendrick.com"));
    }
}
