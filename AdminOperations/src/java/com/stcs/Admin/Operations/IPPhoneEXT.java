/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stcs.Admin.Operations;

import com.DB.EmployeOperations;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import univox.callmanager.V6_1_1.realtimeservice.PhoneProvider;

/**
 *
 * @author AMR
 */
public class IPPhoneEXT {

    Timer timer;
    public static void main(String[] args) {
        IPPhoneEXT ex=new IPPhoneEXT();
    }
    public IPPhoneEXT() {
        try {
           License POC=new License();
            if(POC.Valid_License())
            {
            Calendar calendar = Calendar.getInstance();
            timer = new Timer();
            Thread.sleep(40000);
            timer.schedule(new IPPhoneExtLoader(), calendar.getTime(), 30 * 60 * 1000); // .5 Hours
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(IPPhoneEXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class IPPhoneExtLoader extends TimerTask {

        @Override
        public void run() {
            EmployeOperations op = new EmployeOperations();
            ActiveDirectoryOperations ADoP=new ActiveDirectoryOperations();
            if (!op.iSEmpytyPhone()) {
FillIPExtTable();
            } else {
System.out.println("Tabels Found Empty In IPPhoneEXT Process");
op.dropGroupsTable();
op.dropOUTable();
op.dropEmployeeTable();
op.fillEmptyMyVoxTables();
FillIPExtTable();
//fill tabels First
            }
        }
        public void FillIPExtTable()
        {
            EmployeOperations op = new EmployeOperations();
            Hashtable<String, String> IpExtMap = new Hashtable<String, String>();
               ArrayList<String> EmployeeExtns = op.GetExtens();
                String[] Extns = EmployeeExtns.toArray(new String[EmployeeExtns.size()]);
                if (Extns.length != 0) {
                    String[] IPs = PhoneProvider.getPhonesIpsByExtens(Extns);
                    if (IPs != null && IPs.length != 0) {
                        if (IPs.length == Extns.length) {
                            for (int i = 0; i < IPs.length; i++) {
                                if(Extns[i] != null && IPs[i] != null)
                                if (!Extns[i].equals("") &&!IPs[i].equals("") ) {
                                    IpExtMap.put(Extns[i], IPs[i]);
                                }
                            }
                        }
                        else {
                            System.out.println("Ext And IP Is Not Equal Length");
                        }
                    }
                }
                if(IpExtMap.size()>0)
                {
                    op.dropIpExtTable();
                    op.FillIpExtTable(IpExtMap);
                }
        }
    }
}