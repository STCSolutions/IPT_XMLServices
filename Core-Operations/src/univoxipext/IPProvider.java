/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package univoxipext;

import com.DB.EmployeOperations;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import univox.callmanager.V6_1_1.realtimeservice.PhoneProvider;


/**
 *
 * @author AMR
 */
public class IPProvider {

    public static String[] getPhonesIpsByExtens(String[] extens) {
        EmployeOperations op = new EmployeOperations();
        if (!op.iSEmpytyIpExt()) {
            ArrayList<String> IPList = op.getIPsbyExts(extens);
            if (IPList.size() != 0) {
                String[] IPs = IPList.toArray(new String[IPList.size()]);
                return IPs;
            }
        } else {
            if (op.iSEmpytyPhone()) {
                System.out.println("Tabels Found Empty In IPPhoneEXT Process");

                op.dropGroupsTable();
                op.dropOUTable();
                op.dropEmployeeTable();
                op.fillEmptyMyVoxTables();
                FillIPExtTable();
                //Second Try
                if (!op.iSEmpytyIpExt()) {
                    ArrayList<String> IPList = op.getIPsbyExts(extens);
                    if (IPList.size() != 0) {
                        String[] IPs = IPList.toArray(new String[IPList.size()]);
                        return IPs;
                    }
                }
            } else {
                FillIPExtTable();
                if (!op.iSEmpytyIpExt()) {
                    ArrayList<String> IPList = op.getIPsbyExts(extens);
                    if (IPList.size() != 0) {
                        String[] IPs = IPList.toArray(new String[IPList.size()]);
                        return IPs;
                    }
                }
            }
        }
        return new String[0];
    }
    public static String getPhoneIpByExten(String exten) {
EmployeOperations op=new EmployeOperations();
  if (!op.iSEmpytyIpExt()) {
      return op.getIPbyEXT(exten);
  }
  else
  {
     if (op.iSEmpytyPhone()) {
                try {
                    System.out.println("Tabels Found Empty In IPPhoneEXT Process");
                    op.dropGroupsTable();
                    op.dropOUTable();
                    op.dropEmployeeTable();
                    
                    op.fillEmptyMyVoxTables();
                    Thread.sleep(30000);
                    FillIPExtTable();
                    //Second Try
                    if (!op.iSEmpytyIpExt()) {
                        return op.getIPbyEXT(exten);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(IPProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FillIPExtTable();
                if (!op.iSEmpytyIpExt()) {
                   return op.getIPbyEXT(exten);
                }
            }


  }
        return "";
    }

    public static void FillIPExtTable() {
        EmployeOperations op = new EmployeOperations();
        Hashtable<String, String> IpExtMap = new Hashtable<String, String>();
        ArrayList<String> EmployeeExtns = op.GetExtens();
        String[] Extns = EmployeeExtns.toArray(new String[EmployeeExtns.size()]);
        if (Extns.length != 0) {
            String[] IPs = PhoneProvider.getPhonesIpsByExtens(Extns);
            if (IPs != null && IPs.length != 0) {
                if (IPs.length == Extns.length) {
                    for (int i = 0; i < IPs.length; i++) {
                        if (Extns[i] != null && IPs[i] != null) {
                            if (!Extns[i].equals("") && !IPs[i].equals("")) {
                                IpExtMap.put(Extns[i], IPs[i]);
                            }
                        }
                    }
                } else {
                    System.out.println("Ext And IP Is Not Equal Length");
                }
            }
        }
        if (IpExtMap.size() > 0) {
            op.dropIpExtTable();
            op.FillIpExtTable(IpExtMap);
        }
    }
    public static void main(String[] args) {
       String[] exts = {"28381", "45123", "45124"};
        String[] ips=getPhonesIpsByExtens(exts);
        for(String ip:ips)
            System.out.println(ip);
        System.out.println("-------");
     //   System.out.println(getPhoneIpByExten("8667"));
    }
}


