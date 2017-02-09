
import com.DB.EmployeOperations;
import com.stcs.Admin.Operations.ActiveDirectoryOperations;
import java.util.ArrayList;
import java.util.Hashtable;
import univox.callmanager.V6_1_1.realtimeservice.PhoneProvider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AMR
 */
public class main {
    public static void main(String[] args) {
                    EmployeOperations op = new EmployeOperations();
            ActiveDirectoryOperations ADoP=new ActiveDirectoryOperations();
            if (!op.iSEmpytyPhone()) {
                FillIPExtTable();
            } else {
                System.out.println("Tabels Found Empty In IPPhoneEXT Process");
                op.dropEmployeeTable();
                op.fillEmptyMyVoxTables();
                FillIPExtTable();
                //fill tabels First
            }
    }


            public static void FillIPExtTable()
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
                        } else {
                            System.out.println("Ext And IP Is Not Equal Length");
                        }
                    }
                }
                if(IpExtMap.size()>0)
                {
                    op.FillIpExtTable(IpExtMap);
                }
        }
}
