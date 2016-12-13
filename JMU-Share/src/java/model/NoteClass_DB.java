package model;

import java.util.*;
import java.sql.*;

/**
 * This class contains all of the database interactions for NoteClass
 *
 * @author recinocs
 * @version 2016-12-2
 */
public class NoteClass_DB {

    private final DBHandler handler = new DBHandler();
    private Connection con;

    /**
     * This method adds noteClass to the database if it doesn't already exist
     *
     * @param noteClass, the NoteClass being added
     * @return true if the noteClass is successfully added, false otherwise
     * @throws SQLException
     */
    public boolean addClass(NoteClass noteClass) throws SQLException {
        boolean classAdded = false;

        if ((!checkIfClassExists(noteClass))) {

            int classNum;
            String className, classDesc;

            String insertSql = "INSERT INTO class VALUES(null, ?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(insertSql);

            classNum = noteClass.getClassNum();
            className = noteClass.getClassName();
            classDesc = noteClass.getClassDesc();

            ps.setInt(1, classNum);
            ps.setString(2, className);
            ps.setString(3, classDesc);

            ps.executeUpdate();

            classAdded = true;
        }

        return classAdded;
    }

    /**
     * This method checks if a NoteClass already exists in the database
     *
     * @param noteClass, the class being checked
     * @return true if it does exist, false if it doesn't
     * @throws SQLException
     */
    private boolean checkIfClassExists(NoteClass noteClass) throws SQLException {
        boolean classFound = false;
        int classNum = noteClass.getClassNum();
        String className = noteClass.getClassName();

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String command = "SELECT * FROM class WHERE class_num=? AND class_name=?;";
        PreparedStatement ps = con.prepareStatement(command);
        ps.setInt(1, classNum);
        ps.setString(2, className);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            classFound = true;
        }

        return classFound;
    }

    /**
     * This method deletes a NoteClass if it exists in the database
     *
     * @param noteClass, the NoteClass being deleted
     * @return true if the NoteClass is successfully deleted, false if it isn't
     * @throws SQLException
     */
    public boolean deleteClass(NoteClass noteClass) throws SQLException {
        boolean classDeleted = false;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        if (checkIfClassExists(noteClass)) {
            String delClassCommand = "DELETE FROM class WHERE class_num = ? AND class_name = ?;";

            int classNum = noteClass.getClassNum();
            String className = noteClass.getClassName();

            PreparedStatement ps = con.prepareStatement(delClassCommand);
            ps.setInt(1, classNum);
            ps.setString(2, className);

            ps.executeUpdate();

            classDeleted = true;
        }

        return classDeleted;
    }

    /**
     * This method returns a NoteClass with the given classNum and className, or
     * null if no NoteClasses are in the database with the entered
     * classNum-className combination
     *
     * @param classNum, the classNum of the NoteClass being searched for
     * @param className, the className of the NoteClass being searched for
     * @return a NoteClass object if the combination is present in the database,
     * or null if no combination is found
     * @throws SQLException
     */
    public NoteClass getClassFromName(String className) throws SQLException {
        NoteClass retClass = null;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String getUser = "SELECT * FROM class WHERE class_name = ?;";
        PreparedStatement ps = con.prepareStatement(getUser);
        ps.setString(1, className);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Integer id = rs.getInt("id");
            Integer classNum = rs.getInt("class_num");
            String classDesc = rs.getString("class_desc");
            retClass = new NoteClass(id, classNum, className, classDesc);
        }

        return retClass;
    }

    /**
     * This method returns all classes in the database, or null if the Class
     * table is empty
     *
     * @return An arraylist of all found classes, or null if none are found
     * @throws SQLException
     */
    public ArrayList<NoteClass> getAllClasses() {
        ArrayList<NoteClass> retClasses = new ArrayList<>();

        String className, classDesc;
        int classNum;

        try{
        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }
        String getAllClasses = "SELECT * FROM class;";
        PreparedStatement ps = con.prepareStatement(getAllClasses);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            classNum = rs.getInt(2);
            className = rs.getString(3);
            classDesc = rs.getString(4);

            retClasses.add(new NoteClass(classNum, className, classDesc));
        }

        if (retClasses.isEmpty()) {
            retClasses = null;
        }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return retClasses;
    }

    /**
     * This method gets the id for a NoteClass and returns it, or returns -1 if
     * the noteClass isn't in the database
     *
     * @param noteClass, the NoteClass being searched for
     * @return the id of noteClass, or -1 if noteClass isn't in the database
     * @throws SQLException
     */
    public int getClassId(NoteClass noteClass) throws SQLException {
        int id = -1;

        String className = noteClass.getClassName();
        int classNum = noteClass.getClassNum();

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String sql = "SELECT id FROM class WHERE class_num = ? AND class_name = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, classNum);
        ps.setString(2, className);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }
}
