/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;
import model.Comment;
import model.DBHandler;
import model.Post;

/**
 *
 * @author greatwmc
 */
public class SubmitComment extends FetchSessionAware {

    String comment;
    Integer postId;
    Post post;
    ArrayList<Comment> commentsList = new ArrayList<>();

    public String execute() {
        DBHandler db = new DBHandler();
        Integer authorId = (Integer) session.get("userId");
        //Create the new comment and insert it into the database
        if(null == authorId)
            authorId = 1;
        //If we don't have a postId from the URL params, go to the session
        if (postId == null) {postId = (Integer) session.get("postId");}
        Comment newComment = new Comment(authorId, postId, comment);
        db.insertComment(newComment);
        //Now load the post again with the updated data
        post = db.getPostFromId(postId);
        commentsList = db.getPostComments(postId);
        return "success";
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    public void setCommentsList(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
