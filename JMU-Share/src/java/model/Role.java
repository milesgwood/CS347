package model;

/**
 * This class is a data representation of the role table
 * 
 * @author recinocs
 * @version 2016-11-30
 */
public class Role {
    private String roleName;
    
    public Role(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRole() {
        return roleName;
    }
    
    public void setRole(String roleName) {
        this.roleName = roleName;
    }
    
    public String toString() {
        return "Role: " + roleName;
    }
}
