package model;

/*
 * User represents all registered users in JMU-Share
 * 
 * @author Christopher Recinos
 * @version 2016-11-28
 */
public class User {
    private String email, name, username, password;
    private boolean isProfessor;
    private int postId, schoolId;
    
    /*
     * Initialize a User object
     * 
     * @param password - the password for the user
     * @param email - the user's email
     * @param name - the user's name
     * @param username - the user's username
     * @param isProfessor - whether or not the user is a professor
     */
    public User(String password, String email, String name, String username,
            int postId, boolean isProfessor, int schoolId) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.username = username;
        this.isProfessor = isProfessor;
        this.postId = postId;
        this.schoolId = schoolId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean isProfessor() {
        return isProfessor;
    }
    
    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }
    
    public int getPostId() {
        return postId;
    }
    
    public int getSchoolId() {
        return schoolId;
    }
}
