/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;
import model.Comment;
import model.DBHandler;

/**
 *
 * @author greatwmc
 */
public class FetchPost {
    Integer postId;
    Integer sesPostId;
    Integer authorId;
    Integer classId;
    String contentBody;
    Float rating;
    Integer endorse;
    ArrayList<Comment> commentsList = new ArrayList<>();
    
    public String execute() {
        DBHandler db = new DBHandler();
        commentsList = postId != null? db.getPostComments(postId) : db.getPostComments(sesPostId);
        return "success";
    }
}
