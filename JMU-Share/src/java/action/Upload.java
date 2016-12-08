package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import javax.servlet.ServletContext;
import model.NotesImageFile_DB;
import model.NotesImageFile;
import model.Post;
import model.Post_DB;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 * <code>Allows upload a file</code>
 */
public class Upload extends ActionSupport implements SessionAware, ParameterNameAware {

    private File[] upload;
    private String[] uploadFileName;
    private String[] uploadContentType;
    private String uploadResultMessage;

    //I need these from the form along with the class ID
    private String notesDesc;
    private String title;
    private Integer classId;

    //Make sure to use session and not some other name for it
    private Map<String, Object> session;
    
    public String execute() throws Exception {
        File file;
        ServletContext context = ServletActionContext.getServletContext();
        String filePath = context.getInitParameter("file-upload");
       
        Integer authorId;
        String contentBody = "NO TEXT CONTENT";
        Float rating = (float) 0.0;
        Integer endorse = 0;
        File chosenFile;

        //Get the user Id
        Object ses = session.get("userId");
        authorId = (Integer) ses;
        
        //Create and add a new post to the database. The new post ID is returned
        Post newPost = new Post(authorId, classId, contentBody, rating, endorse, notesDesc, title);
        Post_DB dbPost = new Post_DB();
        int newPostId = dbPost.insertPost(newPost);
        newPost.setId(newPostId);

        //Loop through this uploading the files
        for (int i = 0; i < upload.length; i++) {
            chosenFile = upload[i];
            if (chosenFile == null || !chosenFile.isFile()) {
                break;
            }
            file = new File(filePath, uploadFileName[i]);
            InputStream input = new FileInputStream(upload[i]);
            Files.copy(input, file.toPath());

            //Then you need to add the image
            NotesImageFile img = new NotesImageFile(uploadFileName[i], uploadContentType[i]);
            img.setPostId(newPost.getId());

            //must upload the post before the image
            NotesImageFile_DB db = new NotesImageFile_DB();
            db.insertImage(img);
        }
        return SUCCESS;
        
        /**
         * If the above version of the image uploader doesn't work use a different one
         *  int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
         * // Verify the content type //We will accept pdf and images to start
         * DiskFileItemFactory factory = new DiskFileItemFactory(); // maximum
         * size that will be stored in memory
         * factory.setSizeThreshold(maxMemSize); // Location to save data that
         * is larger than maxMemSize. factory.setRepository(new
         * File("/Users/greatwmc/NetBeansProjects/CS347/JMU-Share/uploads_temp"));
         *
         * // Create a new file upload handler ServletFileUpload uploader = new
         * ServletFileUpload(factory); // maximum file size to be uploaded.
         * uploader.setSizeMax(maxFileSize);
         */
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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
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

    /**
     * This allows access to the session so that the userId can be accessed
     *
     * @param session
     */
    @Override
    public void setSession(Map<String, Object> session) {

        this.session = session;
    }

    /**
     * This stops the user from hacking the URL request parameters
     *
     * @param parameterName
     * @return
     */
    @Override
    public boolean acceptableParameterName(String parameterName) {

        boolean allowedParameterName = true;

        if (parameterName.contains("session") || parameterName.contains("request")) {

            allowedParameterName = false;

        }
        return allowedParameterName;
    }
}
