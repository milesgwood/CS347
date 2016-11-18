/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Comment;
import model.DBHandler;

/**
 *
 * @author greatwmc
 */
public class SubmitComment extends ActionSupport{
    
    private String comment;
    
    public String execute() {
        DBHandler db = new DBHandler();
        //Here you need to somehow get the post_id, and author_id. Possibly from session.
        int author_id = 55;
        int post_id = 55;
        Comment newComment = new Comment(author_id, post_id, getComment());
        db.insertComment(newComment);
        return "success";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
