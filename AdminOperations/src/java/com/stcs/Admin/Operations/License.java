/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stcs.Admin.Operations;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author AMR
 */
public class License {
public boolean  Valid_License()
{
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
Calendar Today=Calendar.getInstance();

        try {
            Date Licnece = df.parse("15/06/2030");
            Date today=Today.getTime();
            System.out.println("Today = " + today.after(Licnece));
            if(today.after(Licnece))
            {
                FileWriter erasor1 = new FileWriter(new File("C:\\STCs\\Config.properties"),false);
erasor1.write("Your POC License Finished ,Please Contact STC to purchase");
erasor1.close(); FileWriter erasor = new FileWriter(new File("C:\\STCs\\Location.properties"),false);
erasor.write("Your POC License Finished ,Please Contact STC to purchase");
erasor.close();
return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
return true;
}


}
