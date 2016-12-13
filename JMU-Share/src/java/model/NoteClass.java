package model;

/**
 * This class is a data representation of the class table. Due to method calls in
 * DBHandler, the class could not be named Class, and is going to be called NoteClass
 * until a better name is made available or the project is finished
 * 
 * @author recinocs
 * @version 2016-12-2
 */
public class NoteClass implements Comparable<NoteClass>{
    private int classNum, id;
    private String className, classDesc;
    
    public NoteClass(int classNum, String className, String classDesc){
        this.classNum = classNum;
        this.className = className;
        this.classDesc = classDesc;
    }

    public NoteClass(int id, int classNum, String className, String classDesc) {
        this.classNum = classNum;
        this.className = className;
        this.classDesc = classDesc;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }
    
    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }
    
    public String toString() {
        return "Class Number: " + this.classNum + "\n" +
                "Class Name: " + this.className + "\n" +
                "Class Description: " + this.classDesc + "\n";
    }
    
    @Override
    public int compareTo(NoteClass other) {
        return this.className.compareTo(other.getClassName());
    }
}
