/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBHandler;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;


@Result(
    name = "success", 
    type = "stream", 
    params = { 
        "postId", "${commentId}", 
        "inputName", "${inputStream}", 
    }
)

/**
 * This class handles an upvote on a comment. It returns the new score in the
 * result stream. It doesn't need to though.
 *
 * @author greatwmc
 */
public class UpVoting extends ActionSupport {

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
        System.out.println("Upvote" + commentId);
        db.increaseScore(commentId);
        inputStream = new ByteArrayInputStream(commentId.toString().getBytes("UTF-8"));
        inputStream.close();
        return SUCCESS;
    }
}
