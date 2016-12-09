/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author greatwmc
 */
public class NotesImageFile {

    private String fileName, contentType, uniqueFileLocation;
    private Integer id, postId;

    /**
     * Takes a regular filename and creates a unique filename with date values included
     * @param fileName - the plain filename
     * @return 
     */
    public static String createUniqueFileName(String fileName) {
        int hashcode = fileName.hashCode();
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd-HH-mm-ss");
        Calendar calobj = Calendar.getInstance();
        System.out.println(df.format(calobj.getTime()));
        return (df.format(calobj.getTime()) + hashcode + fileName);
    }

    /**
     * Use this for the images that aren't yet hashed by name and date. They
     * have yet to be added to the database.
     */
    public NotesImageFile(String preHashFileName, String contentType) {
        this.fileName = createUniqueFileName(preHashFileName);
        ServletContext context = ServletActionContext.getServletContext();
        String filePath = context.getInitParameter("file-upload");
        this.uniqueFileLocation = filePath + this.fileName;
        this.contentType = contentType;
    }

    /**
     * Use this for the images already in the database as the fileLocation is
     * hashed and dated
     *
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
