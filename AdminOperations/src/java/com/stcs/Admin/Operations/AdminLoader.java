/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stcs.Admin.Operations;

import com.DB.EmployeOperations;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author AMR
 */
public class AdminLoader {
    public static void main(String[] args) {
        AdminLoader lo=new AdminLoader();



    }
Timer timer;
    public AdminLoader() {
License POC=new License();
if(POC.Valid_License())
{
        Calendar calendar = Calendar.getInstance();
        timer = new Timer();
        timer.schedule(new AdminLoaderInit(), calendar.getTime(), 3 * 62 * 60 * 1000);//12 Hours
}
    }


        class AdminLoaderInit extends TimerTask {

public void run() {
EmployeOperations OP=new EmployeOperations();
if(OP.iSEmpytyEmployee()||OP.iSEmpytyPhone()||OP.iSEmptyOU()||OP.iSEmptyGroups())
{
    System.out.println("Run For First Time Or Tabels Are Empty");
OP.dropPhoneTable();
OP.dropOUTable();
OP.dropGroupsTable();
OP.dropIpExtTable();
OP.dropEmployeeTable();
OP.fillEmptyMyVoxTables();
}
else
{
    System.out.println("Run For Normal Tabels Are Not Empty");
OP.updateMyVoxTables();
}
        }

}
}
