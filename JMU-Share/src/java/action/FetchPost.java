/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Comment;
import model.DBHandler;
import model.Post;

/**
 * @author greatwmc
 */
public class FetchPost {
    Integer postId;
    Post post;
    ArrayList<Comment> commentsList = new ArrayList<>();
    ArrayList<String> postImageLocations = new ArrayList<>();
    
    public String execute() throws SQLException {
        DBHandler db = new DBHandler();
        post = db.getPostFromId(postId);
        commentsList = db.getPostComments(postId);
        postImageLocations = db.getAllImagesForPostId(postId);
        return "success";
    }

    public ArrayList<String> getPostImageLocations() {
        return postImageLocations;
    }

    public void setPostImageLocations(ArrayList<String> postImageLocations) {
        this.postImageLocations = postImageLocations;
    }

    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setCommentsList(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }
}
