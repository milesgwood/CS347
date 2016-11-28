/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author miles greatwood
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.*;

/**
 * An abstract parent class for database handlers. This class loads database
 * access parameters from web.xml and loads the database driver class.
 *
 * @author R.Grove
 * @version 2014-10-25
 */
public class DBHandler {

    /**
     * Database access credentials
     */
    protected String driverName = null, url = null, userId = null, password = null;
    /**
     * Database connection
     */
    private Connection con;
    /**
     * SQL Statement
     */
    protected Statement stmt;
    /**
     * Connection open status
     */
    protected boolean isOpen = false;

    /**
     * Get parameters required to open DBMS connection.
     */
    public DBHandler() {
        // Get DB access credentials from web.xml -- HOW DO WE GET THE WEB.XML TO WORK 
        //NO INITIAL CONTEXT EXCEPTION WITH THIS CODE
        //Apparently this only works when deployed
        try {
            Context envCtx = (Context) (new InitialContext()).lookup("java:comp/env");
            driverName = (String) envCtx.lookup("DriverClassName");
            url = (String) envCtx.lookup("Url");
            userId = (String) envCtx.lookup("UserId");
            password = (String) envCtx.lookup("Password");
        } catch (NoInitialContextException contextError) {
            driverName = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://grove.cs.jmu.edu:3306/team27_db";
            userId = "team27";
            password = "nov#mber5";
        } catch (NamingException e) {
            e.printStackTrace();
        }

        /*
         driverName = "./sqlStuff/connector/mysql-connector-java-5.1.40-bin.jar";
         url = "jdbc:mysql://localhost:3306/team27_db";
         userId = "team27";
         password = "nov#mber5";
         */
    }

    /**
     * Open the DB connection
     *
     * @throws SQLException if the DB connection cannot be established
     */
    public void open() throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        con = DriverManager.getConnection(url, userId, password);
        stmt = con.createStatement();
        isOpen = true;
    }

    /**
     * Close the DB connection.
     *
     * @throws SQLException if the DB connection close fails
     */
    public void close() throws SQLException {
        stmt.close();
        con.close();
        isOpen = false;
    }

    /**
     * Execute a command and return a result count.
     *
     * @param command An SQL command to be executed.
     * @return The count of rows affected by the command
     * @throws SQLException if there is a database error during command
     * execution
     */
    public int doCommand(String command) throws SQLException {
        if (!isOpen) {
            open();
        }
        int resultCount = stmt.executeUpdate(command);
        return resultCount;
    }

    /**
     * Execute a query and return a ResultSet.
     *
     * @param query The SQL query to be executed
     * @return A ResultSet of the query results
     * @throws SQLException if the database query action fails
     */
    public ResultSet doQuery(String query) throws SQLException {
        if (!isOpen) {
            open();
        }
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    /**
     * Create all of the needed tables and print out the create table
     * statements.
     */
    public static void createTables() {
        String sql = "";
        System.out.println("Create Table Statements are gone");
        /**
        DBHandler db = new DBHandler();
        try {
            db.open();
            //sql = "CREATE TABLE IF NOT EXISTS comments (id INTEGER PRIMARY KEY AUTO_INCREMENT, author_id INTEGER NOT NULL, post_id  INTEGER NOT NULL, comment VARCHAR(255) NOT NULL);";
            System.out.println(sql);
            db.doCommand(sql);
        } catch (SQLException e) {
            System.err.println("The Create statement with the problem is :" + sql);
            e.printStackTrace();
        }
        * **/
    }
    
    public void increaseScore(int id)
    {
        int score = 0;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT score FROM comments WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                score = rs.getInt(1);
            }
            
            score++;
            sql = "UPDATE score SET score = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, score);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void decreaseScore(int id)
    {
        int score = 0;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT score FROM comments WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                score = rs.getInt(1);
            }
            
            score--;
            sql = "UPDATE score SET score = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, score);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a comment to the database. Uses prepared statement.
     *
     * @param c - The comment with ID's and text
     */
    public void insertComment(Comment c) {
        try {
            if (!isOpen) {
                open();
            }
            String sql = "INSERT INTO comments VALUES (?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.setInt(4, c.getAuthorId());
            ps.setInt(2, c.getPostId());
            ps.setString(3, c.getComment());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNextCommentId() {
        int next = 0;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT max(id) FROM comments;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            rs.next();
            next = rs.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return next;
    }

    public Post getPost(int postId) {
        Post post = null;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT * FROM posts WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            Integer authorId;
            Integer classId;
            String contentBody;
            Float rating;
            Integer endorse;

            while (rs.next()) {
                authorId = rs.getInt(2);
                classId = rs.getInt(3);
                contentBody = rs.getString(4);
                rating = rs.getFloat(5);
                endorse = rs.getInt(6);
                post = new Post(postId, authorId, classId, contentBody, rating, endorse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    /**
     * Retrieves all of the comments from the database for post_id
     *
     * @param post_id
     * @return
     */
    public ArrayList<Comment> getPostComments(int post_id) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT * FROM comments WHERE postId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, post_id);
            ResultSet rs = ps.executeQuery();

            int id, post, author;
            String text;
            Comment comment;

            while (rs.next()) {
                id = rs.getInt(1);
                author = rs.getInt(4);
                post = rs.getInt(2);
                text = rs.getString(3);
                comment = new Comment(id, author, post, text);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public String getAuthorName(int id) {
        String name = "deleted";
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT name FROM user WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            

            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}