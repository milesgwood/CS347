/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Comment;
import model.DBHandler;

public class FetchComments extends ActionSupport{

    ArrayList<Comment> list = new ArrayList<>();

    public ArrayList<Comment> getList() {
        return list;
    }

    public void setList(ArrayList<Comment> list) {
        this.list = list;
    }

    /**
     * In order to get access to the post_id in the parameters, the request parameters must be pushed onto the stack.
     * Then get the stack context
     * Then get the parameters HashMap
     * Then get the post_id as a String[]
     * Then get the post id from the 0 index of the String array
     * Then parse that String as an integer and get the comments for that ID.
     * @return 
     */
    public String execute() {
        ValueStack stack = ActionContext.getContext().getValueStack();
        Map stackContext = stack.getContext();
        HashMap requestParameterMap = (HashMap)stackContext.get("parameters");
        String[] post_id_param =  (String[])requestParameterMap.get("post_id");
        String post_id = post_id_param[0];
        System.out.println("The post id is " + post_id);
        
        int id = Integer.parseInt(post_id);
        System.out.println("As an int it is " + id);
        //DBHandler db = new DBHandler();
        //list = db.getPostComments(id);

        return "success";
    }
}
