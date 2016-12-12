package bean;

import model.School_DB;
import model.School;

import java.util.*;
/**
 * This bean creates a list of School objects and orders them by their names
 * @author recinocs modified by greatwmc
 */
public class SchoolList {
    private School_DB db = new School_DB();
    private ArrayList<School> schools = db.getAllSchools();
    private String[] schoolList = new String[schools.size()];
    
    public String[] getSchoolList() {
        if(schoolList[0] == null)
            populate();
        return schoolList;
    }
    
    private void populate() {
        Collections.sort(schools);
        for(int i = 0; i < schools.size(); i++)
            schoolList[i] = schools.get(i).getSchoolName();
    }
    
    public void setSchoolList(String[] schools) {
        this.schoolList = schools;
    }
}
