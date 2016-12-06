/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import action.HashSlingSlasher;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
/**
 *
 * @author greatwmc
 */
public class NotesImageFile implements SessionAware, ParameterNameAware{
    
    private String fileLocation, contentType, uniqueFileLocation;
    private Integer id, postId;
    private Map<String, Object> userSession;

    /**
     * Use this for the images that aren't yet hashed by name and date. They have yet to be added to the database.
     * @param fileLocation
     * @param contentType
     * @param id
     * @param postId 
     */
    public NotesImageFile(String fileLocation, String contentType) {
        this.fileLocation = fileLocation;
        this.uniqueFileLocation = HashSlingSlasher.getUniqueFileName(fileLocation);
        this.contentType = contentType;
    }

    /**
     * Use this for the images already in the database as the fileLocation is hashed and dated
     * @param contentType
     * @param uniqueFileLocation
     * @param id
     * @param postId 
     */
    public NotesImageFile(String contentType, String uniqueFileLocation, Integer id, Integer postId) {
        this.contentType = contentType;
        this.uniqueFileLocation = uniqueFileLocation;
        this.id = id;
        this.postId = postId;
    }

    public String getUniqueFileLocation() {
        return uniqueFileLocation;
    }

    public void setUniqueFileLocation(String uniqueFileLocation) {
        this.uniqueFileLocation = uniqueFileLocation;
    }
    
    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
/**
     * This allows access to the session so that the userId can be accessed
     * @param session 
     */
    public void setSession(Map<String, Object> session) {

        userSession = session;

    }

    /**
     * This stops the user from hacking the URL request parameters
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
