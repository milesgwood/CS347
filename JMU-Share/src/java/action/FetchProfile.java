/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import model.DBHandler;
import model.Post;
import model.Role;
import model.School;
import model.User;
import model.User_DB;

/**
 *
 * @author greatwmc
 */
public class FetchProfile {

    private ArrayList<Post> userPosts;
    private User user;
    private School school;
    private Role role;
    private Map<String, Object> session;
    private Integer userIdParam;
    private ArrayList<Class> classes;

    /**
     * Gets all of the objects for the specified userIdParam.
     * If there is no userIdParam then use the session userId 
     * to get their profile instead.
     * @return 
     */
    public String execute() throws SQLException {
        DBHandler db = new DBHandler();
        //Use the userIdParam first.
        if (userIdParam == null)
        {
            Object ses = session.get("userId");
            userIdParam = (Integer) ses;
        }
        userPosts = db.getPostsWritenByUser(userIdParam);
        User_DB udb = new User_DB();
        user = udb.getUserFromId(userIdParam);
        //classes = db.getClassesForUser(userIdParam);  Use this to link to the students' classes
        //favoriteNotes = db.getUserFavorites(userId);
        return "success";
    }

    public Integer getUserIdParam() {
        return userIdParam;
    }

    public void setUserIdParam(Integer userIdParam) {
        this.userIdParam = userIdParam;
    }

    public ArrayList<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(ArrayList<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public User getProfile() {
        return profile;
    }

    public void setProfile(User profile) {
        this.profile = profile;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
    

    /**
     * This allows access to the session so that the userId can be accessed
     *
     * @param session
     */
    public void setSession(Map<String, Object> session) {

        this.session = session;
    }

    /**
     * This stops the user from hacking the URL request parameters
     *
     * @param parameterName
     * @return
     */
    public boolean acceptableParameterName(String parameterName) {

        boolean allowedParameterName = true;

        if (parameterName.contains("session") || parameterName.contains("request")) {

            allowedParameterName = false;

        }
        return allowedParameterName;
    }

}
