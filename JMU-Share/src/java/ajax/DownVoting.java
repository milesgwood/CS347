/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import model.DBHandler;

/**
 *
 * @author greatwmc
 */
public class DownVoting extends ActionSupport {

    private Integer commentId;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String execute() throws Exception {
        DBHandler db = new DBHandler();
        System.out.println("Downvoted" + commentId);
        db.decreaseScore(commentId);
        inputStream = new ByteArrayInputStream(commentId.toString().getBytes("UTF-8"));
        return SUCCESS;
    }
}
