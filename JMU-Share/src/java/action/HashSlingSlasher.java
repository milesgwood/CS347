/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author greatwmc
 */
public class HashSlingSlasher {

    public static String getUniqueFileName(String fileName) {
        int hashcode = fileName.hashCode();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        System.out.println(df.format(calobj.getTime()));
        return "notes." + (df.format(calobj.getTime())) + hashcode + fileName;
    }
}
