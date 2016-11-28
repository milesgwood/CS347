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
    
    private int id, authorId, postId;
    private String comment;
    private String commentAuthorName;
    private int score;

    public String getCommentAuthorName() {
        return commentAuthorName;
    }

    public void setCommentAuthorName(String commentAuthorName) {
        this.commentAuthorName = commentAuthorName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public Comment(int authorId, int postId, String comment) {
        this.authorId = authorId;
        this.postId = postId;
        this.comment = comment;
        
        DBHandler db = new DBHandler();
        this.id = db.getNextCommentId();
        
        commentAuthorName = db.getAuthorName(authorId);
    }
    
    public Comment(int id, int authorId, int postId, String comment) {
        this.id = id;
        this.authorId = authorId;
        this.postId = postId;
        this.comment = comment;
        
        DBHandler db = new DBHandler();
        commentAuthorName = db.getAuthorName(authorId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
