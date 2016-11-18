/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;
import model.Comment;
import model.DBHandler;

public class FetchComments {

    ArrayList<Comment> list = new ArrayList<>();

    public ArrayList<Comment> getList() {
        return list;
    }

    public void setList(ArrayList<Comment> list) {
        this.list = list;
    }

    public String execute() {
        DBHandler db = new DBHandler();
        //Here you need to somehow get the post_id. Possibly from session.
        list = db.getPostComments(2);

        return "success";
    }
}
