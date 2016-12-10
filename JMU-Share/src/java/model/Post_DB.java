/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author greatwmc
 */
public class Post_DB extends DBHandler {

    /**
     * Adds a post to the database and returns the postID
     *
     * @param post
     * @return the integer id of the posting in the database
     */
    public int insertPost(Post post) throws Exception {
        int newId = -1;
        if (!isOpen) {
            open();
        }
        String sql = "INSERT INTO posts (author_id, class_id, text, rating, endorse, notes_desc, title ) VALUES (?, ? , ? , ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, post.getAuthorId());
        ps.setInt(2, post.getClassId());
        ps.setString(3, post.getContentBody());
        ps.setFloat(4, post.getRating());
        ps.setInt(5, post.getEndorse());
        ps.setString(6, post.getNotesDesc());
        ps.setString(7, post.getTitle());
        ps.execute();
        ps.close();

        //Now let's return the new ID
        sql = "SELECT MAX(id) FROM posts WHERE author_id = ?;";
        ps = con.prepareStatement(sql);
        ps.setInt(1, post.getAuthorId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            newId = rs.getInt(1);
        }
        if (newId < 0) {
            throw new Exception("The new post Id could not be cound in the databsae");
        }
        return newId;
    }

    public Post[] searchSpecific(String title, int class_num, String class_name, String school) throws SQLException {

        PreparedStatement stmt;
        String genericQuery = "SELECT p.id, p.author_id, p.class_id, p.text, p.rating, p.endorse, p.notes_desc, p.title FROM posts AS p JOIN class AS c ON p.class_id = c.id JOIN user AS u ON p.author_id = u.id JOIN school AS s ON u.school_id = s.id"
                + " WHERE p.title LIKE ? AND c.class_num = ? AND c.class_name LIKE ? AND s.school LIKE ? ORDER BY p.rating ASC;";
        Float rating = null;
        Integer endorse = null;
        String notes_desc = null, ret_title = null;

        if (!isOpen) {
            open();
        }

        Post[] firstTen = new Post[10];
        int i = 0;
        try {
            stmt = con.prepareStatement(genericQuery);
            stmt.setString(1, title + "%");
            stmt.setInt(2, class_num);
            stmt.setString(3, class_name + "%");
            stmt.setString(4, school + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next() && i < 10) {
                Integer id = rs.getInt("id");
                Integer author_id = rs.getInt("author_id");
                Integer class_id = rs.getInt("class_id");
                String text = rs.getString("text");
                rating = rs.getFloat("rating");
                endorse = rs.getInt("endorse");
                notes_desc = rs.getString("notes_desc");
                ret_title = rs.getString("title");
                firstTen[i] = new Post(id, author_id, class_id, text, rating, endorse, notes_desc, ret_title);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Hi");
        }
        return firstTen;
    }

    public Post[] searchPartiallyGeneral(String title, int class_num, String class_name, String school) throws SQLException {

        PreparedStatement stmt;
        String genericQuery = "SELECT p.id, p.author_id, p.class_id, p.text, p.rating, p.endorse, p.notes_desc, p.title FROM posts AS p JOIN class AS c ON p.class_id = c.id JOIN user AS u ON p.author_id = u.id JOIN school AS s ON u.school_id = s.id"
                + " WHERE (p.title LIKE ? OR c.class_num = ? oR c.class_name LIKE ?) AnD s.school LIKE ? ORDER BY p.rating DESC;";
        Float rating = null;
        Integer endorse = null;
        String notes_desc = null, ret_title = null;

        if (!isOpen) {
            open();
        }

        Post[] firstTen = new Post[10];
        int i = 0;
        try {
            stmt = con.prepareStatement(genericQuery);
            stmt.setString(1, title + "%");
            stmt.setInt(2, class_num);
            stmt.setString(3, class_name + "%");
            stmt.setString(4, school + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next() && i < 10) {
                Integer id = rs.getInt("id");
                Integer author_id = rs.getInt("author_id");
                Integer class_id = rs.getInt("class_id");
                String text = rs.getString("text");
                rating = rs.getFloat("rating");
                endorse = rs.getInt("endorse");
                notes_desc = rs.getString("notes_desc");
                ret_title = rs.getString("title");
                firstTen[i] = new Post(id, author_id, class_id, text, rating, endorse, notes_desc, ret_title);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Hi");
        }
        return firstTen;
    }

    public Post[] searchGeneral(String title, int class_num, String class_name, String school) throws SQLException {

        PreparedStatement stmt;
        String genericQuery = "SELECT p.id, p.author_id, p.class_id, p.text, p.rating, p.endorse, p.notes_desc, p.title FROM posts AS p JOIN class AS c ON p.class_id = c.id JOIN user AS u ON p.author_id = u.id JOIN school AS s ON u.school_id = s.id"
                + " WHERE p.title LIKE ? OR c.class_num = ? OR c.class_name LIKE ? OR s.school LIKE ? ORDER BY p.rating DESC;";
        Float rating = null;
        Integer endorse = null;
        String notes_desc = null, ret_title = null;

        if (!isOpen) {
            open();
        }

        Post[] firstTen = new Post[10];
        int i = 0;
        try {
            stmt = con.prepareStatement(genericQuery);
            stmt.setString(1, title + "%");
            stmt.setInt(2, class_num);
            stmt.setString(3, class_name + "%");
            stmt.setString(4, school + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next() && i < 10) {
                Integer id = rs.getInt("id");
                Integer author_id = rs.getInt("author_id");
                Integer class_id = rs.getInt("class_id");
                String text = rs.getString("text");
                rating = rs.getFloat("rating");
                endorse = rs.getInt("endorse");
                notes_desc = rs.getString("notes_desc");
                ret_title = rs.getString("title");
                firstTen[i] = new Post(id, author_id, class_id, text, rating, endorse, notes_desc, ret_title);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Hi");
        }
        return firstTen;
    }
}
