/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import model.DBHandler;

/**
 *
 * @author greatwmc
 */
public class Voting extends ActionSupport {

    private Integer commentIdParam;

    public Integer getCommentIdParam() {
        return commentIdParam;
    }

    public void setCommentIdParam(Integer commentIdParam) {
        this.commentIdParam = commentIdParam;
    }

    public String execute() throws Exception {
        DBHandler db = new DBHandler();
        System.out.println("We're voting now");
        int upOne = commentIdParam + 1;
        if (upOne == db.increaseScore(commentIdParam)) {
            return SUCCESS;
            /**
             * } else { return ERROR; } *
             */
        }
    return SUCCESS;
    }
}
