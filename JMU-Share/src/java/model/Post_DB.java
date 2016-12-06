/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author greatwmc
 */
public class Post_DB extends DBHandler {

    /**
     * Adds a post to the database
     *
     * @param post
     * @return the integer id of the posting in the database
     */
    public int insertPost(Post post) {
        int newId = -1;
        
        try {
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
            while(rs.next())
            {
                newId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }
}
