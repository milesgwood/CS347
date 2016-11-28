/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import model.Comment;
import model.DBHandler;

/**
 *
 * @author greatwmc
 */
public class SubmitComment extends ActionSupport {

    String comment;
    Integer post_id;
    ArrayList<Comment> list = new ArrayList<>();

    public String execute() {
        DBHandler db = new DBHandler();
        //Here you need to get the author_id from session.
        int author_id = 1;
        Comment newComment = new Comment(author_id, post_id, comment);
        db.insertComment(newComment);
        //Now we load the list of comments with the new comment added
        list = db.getPostComments(post_id);
        return "success";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }
    
    public ArrayList<Comment> getList() {
        return list;
    }

    public void setList(ArrayList<Comment> list) {
        this.list = list;
    }
}
