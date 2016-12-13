/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.*;
import model.NoteClass;
import model.NoteClass_DB;

/**
 * @author greatwmc
 */
public class ClassList {
    private NoteClass_DB ndb = new NoteClass_DB();
    private ArrayList<NoteClass> allClasses = ndb.getAllClasses();
    private String[] alphaOrderedClasses = new String[allClasses.size()];
    
    public String[] getAlphaOrderedClasses() {
        if(alphaOrderedClasses[0] == null)
            populate();
        return alphaOrderedClasses;
    }
    
    private void populate() {
        Collections.sort(allClasses);
        for(int i = 0; i < allClasses.size(); i++)
            alphaOrderedClasses[i] = allClasses.get(i).getClassName();
    }
    
    public void getAlphaOrderedClasses(String[] alphaOrderedClasses) {
        this.alphaOrderedClasses = alphaOrderedClasses;
    }
    
}
