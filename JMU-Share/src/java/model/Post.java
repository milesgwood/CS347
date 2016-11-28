/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author greatwmc
 */
public class Post {
    
    Integer id;
    Integer authorId;
    Integer classId;
    String contentBody;
    Float rating;
    Integer endorse;
    
    //Here are the derived values
    String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Post(Integer id, Integer authorId, Integer classId, String contentBody, Float rating, Integer endorse) {
        this.id = id;
        this.authorId = authorId;
        this.classId = classId;
        this.contentBody = contentBody;
        this.rating = rating;
        this.endorse = endorse;
        
        //Get the more useful values
        DBHandler db = new DBHandler();
        this.authorName = db.getAuthorName(authorId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getEndorse() {
        return endorse;
    }

    public void setEndorse(Integer endorse) {
        this.endorse = endorse;
    }
}
