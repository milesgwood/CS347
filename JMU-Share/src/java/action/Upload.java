package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.ServletContext;
import model.NoteClass;
import model.NoteClass_DB;
import model.NotesImageFile_DB;
import model.NotesImageFile;
import model.Post;
import model.Post_DB;
import org.apache.struts2.ServletActionContext;

/**
 * <code>Allows upload a file</code>
 */
public class Upload extends FetchSessionAware {

    private File[] upload;
    private String[] uploadFileName;
    private String[] uploadContentType;
    private String uploadResultMessage;

    //I need these from the form along with the class ID
    private String notesDesc;
    private String title, body;
    private String className;

    private Integer upLoadedPostId;
    
    public String execute() throws Exception {
        File file;
        ServletContext context = ServletActionContext.getServletContext();
        String filePath = context.getInitParameter("file-upload");
       
        Integer authorId;
        String contentBody;
        
        if(body == null || body.equals(""))
            contentBody = "NO TEXT CONTENT";
        else
            contentBody = body;
        
        Float rating = (float) 0.0;
        Integer endorse = 0;
        File chosenFile;

        //Get the user Id
        Object ses = session.get("userId");
        authorId = (Integer) ses;
        
        NoteClass_DB ndb = new NoteClass_DB();
        NoteClass classroom = ndb.getClassFromName(className);
        
        //Create and add a new post to the database. The new post ID is returned
        Post newPost = new Post(authorId, classroom.getId(), contentBody, rating, endorse, notesDesc, title);
        Post_DB dbPost = new Post_DB();
        upLoadedPostId = dbPost.insertPost(newPost);
        newPost.setId(upLoadedPostId);

        //Loop through this uploading the files
        if(upload != null && upload.length > 0) {
            for (int i = 0; i < upload.length; i++) {
                chosenFile = upload[i];
                if (chosenFile == null || !chosenFile.isFile()) {
                    break;
                }
                //Create the image first but don't add it to the database
                NotesImageFile img = new NotesImageFile(uploadFileName[i], uploadContentType[i]);
                img.setPostId(newPost.getId());
                
                
                file = new File(filePath, img.getFileName());
                InputStream input = new FileInputStream(upload[i]);
                Files.copy(input, file.toPath());
                
                //must upload the post before the image
                NotesImageFile_DB db = new NotesImageFile_DB();
                db.insertImage(img);
            }
        }
        return SUCCESS;
    }

    public Integer getUpLoadedPostId() {
        return upLoadedPostId;
    }

    public void setUpLoadedPostId(Integer upLoadedPostId) {
        this.upLoadedPostId = upLoadedPostId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNotesDesc() {
        return notesDesc;
    }

    public void setNotesDesc(String notesDesc) {
        this.notesDesc = notesDesc;
    }

    public String getUploadResultMessage() {
        return uploadResultMessage;
    }

    public void setUploadResultMessage(String uploadResultMessage) {
        this.uploadResultMessage = uploadResultMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public File[] getUpload() {
        return upload;
    }

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String[] getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
}
