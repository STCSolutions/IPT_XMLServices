/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.Admin.Operations;


import com.DB.EmployeOperations;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author AMR
 */
public class ScheduleBrdcastLoader {
Timer timer;
    public ScheduleBrdcastLoader() {
        License POC=new License();
        if(POC.Valid_License())
        {
         Calendar calendar = Calendar.getInstance();
        timer = new Timer();
        timer.schedule(new BroadCastScedhuler(), calendar.getTime(), 5 * 60 * 1000);//Run Every 5 Min
        }
    }

            class BroadCastScedhuler extends TimerTask {

public void run() {
EmployeOperations OP=new EmployeOperations();
SchedulerOperations SCD=new SchedulerOperations();
SCD.broadcastRtpStreaming(OP.getBroadcastInTime());

        }

}


}
