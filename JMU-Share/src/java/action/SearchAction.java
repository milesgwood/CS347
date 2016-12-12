package action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Post;
import model.Post_DB;


/**
 *
 * @author wilson
 */
public class SearchAction extends ActionSupport {

    Post_DB postDBSearcher = new Post_DB();
    private ArrayList results;

    private Integer class_num;
    private String title, class_name, school, confirm;

    
    //things for the search results
    public void setResults(ArrayList results) {
        this.results = results;
    }

    public ArrayList getResults() {
        return results;
    }
    
    
    //Other things
    public String getTitle() {
        return title;
    }

    public int getClassNum() {
        return class_num;
    }

    public String getClassName() {
        return class_name;
    }
    
    public String getConfirm() {
        return confirm;
    }

    public String getSchool() {
        return school;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassNum(int class_num) {
        this.class_num = class_num;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    public void validate() {
        //if(!class_num.toString().matches("^[0-9]*$")) {
        //    addFieldError("class_num", "Class number can only contain numbers 0 - 9");
        //}
        if(class_num == null){
            class_num = 0;
        }
        if(class_name == null){
            class_name = "";
        }
        if(title == null){
            title = "";
        }
        if(school == null){
            school = "";
        }
    }
    

    /**
     * The tester object is a private POST_DB oject
     * @return
     * @throws SQLException 
     */
    public String execute() throws SQLException {
        try {          
            results = postDBSearcher.searchSpecific(title, class_num, class_name, school);

            //If the search does not find any results tries the partial search
            if (results.isEmpty()){
                results = postDBSearcher.searchPartiallyGeneral(title, class_num, class_name, school);
            }

            //If the partial search did not find anything, then it tries to make a general search
            if (results.isEmpty()){
                results = postDBSearcher.searchGeneral(title, class_num, class_name, school);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
