/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author greatwmc
 */
public class Comment {
    
    private int id, author_id, post_id;
    private String comment;

    public Comment(int author_id, int post_id, String comment) {
        this.author_id = author_id;
        this.post_id = post_id;
        this.comment = comment;
        DBHandler db = new DBHandler();
        this.id = db.getNextPostId();
    }
    
    public Comment(int id, int author_id, int post_id, String comment) {
        this.id = id;
        this.author_id = author_id;
        this.post_id = post_id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String createCommentDiv()
    {
        return "<p>Hello<p>";
    }
    
}
