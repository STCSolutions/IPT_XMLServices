/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package univox.callmanager.V6_1_1.realtimeservice;

import com.cisco.schemas.ast.soap.CmDevice;
import com.cisco.schemas.ast.soap.CmNode;
import com.cisco.schemas.ast.soap.CmSelectionCriteria;
import com.cisco.schemas.ast.soap.DeviceClass;
import com.cisco.schemas.ast.soap.RISService;
import com.cisco.schemas.ast.soap.RISServiceLocator;
import com.cisco.schemas.ast.soap.RisPortType;
import com.cisco.schemas.ast.soap.SelectCmDeviceResult;
import com.cisco.schemas.ast.soap.SelectItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.types.UnsignedInt;

/**
 *
 * @author Magdy.Alhoseiny
 */
public class PhoneProvider {

    static String risURL = "https://192.168.1.10/realtimeservice/services/RisPort";
    static String axlUser = "Administrator";
    static String axlPassword = "IST_ccm6";
    static String callManagerIP = "192.168.1.10";
    static String TrustStorePath = "C:\\Program Files\\Java\\jre1.6.0_03\\lib\\security\\cacerts";
    static String TrustStorePass = "changeit";

    private static void getCallManagerConfigurations() {
        Properties ProbFile = new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {
        }
        axlUser = ProbFile.getProperty("callManagerUserName");
        axlPassword = ProbFile.getProperty("callManagerPass");
        callManagerIP = ProbFile.getProperty("callManagerIP");
        TrustStorePath = ProbFile.getProperty("TrustStorePath");
        TrustStorePass = ProbFile.getProperty("TrustStorePass");
        risURL = "https://" + callManagerIP + "/realtimeservice/services/RisPort";

    }

    public static void getPhoneByExten(String exten) {
        try {
            RISService service = new RISServiceLocator(); // Instantiate the Axis Service object
            getCallManagerConfigurations();
            RisPortType rport = null;
            try {

                rport = service.getRisPort(new java.net.URL(risURL)); // get the binding for RisPort

            } catch (MalformedURLException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServiceException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            }

            ((Stub) rport)._setProperty(Call.USERNAME_PROPERTY, axlUser); // If we cast rport as an Axis Stub class

            ((Stub) rport)._setProperty(Call.PASSWORD_PROPERTY, axlPassword); // we can set the HTTP basic auth credentials


            System.out.println("calling " + risURL);
            SelectItem item = new SelectItem();
            item.setItem("" + exten);
            SelectItem[] items = {item};
            CmSelectionCriteria cmSelectionCriteria = new CmSelectionCriteria();
            cmSelectionCriteria.setMaxReturnedDevices(new UnsignedInt(200));
            cmSelectionCriteria.set_class(DeviceClass._Any);
            cmSelectionCriteria.setModel(new UnsignedInt(255));
            cmSelectionCriteria.setStatus("Any");
            cmSelectionCriteria.setSelectBy("DirNumber");
            cmSelectionCriteria.setSelectItems(items);
            SelectCmDeviceResult cmselectresult = null;
            System.setProperty("javax.net.ssl.trustStore",
                    TrustStorePath);
            System.setProperty("javax.net.ssl.trustStorePassword",
                    TrustStorePass);
            cmselectresult = rport.selectCmDevice(new javax.xml.rpc.holders.StringHolder(""), cmSelectionCriteria);
            if (cmselectresult != null) {
                UnsignedInt devicesFound = cmselectresult.getTotalDevicesFound();
                System.out.println(devicesFound);
                CmNode[] nodes = cmselectresult.getCmNodes();
                for (CmNode node : nodes) {
                    System.out.println(node.getName());
                }
                CmDevice[] devices = nodes[0].getCmDevices();
                for (CmDevice device : devices) {
                    System.out.println("Name : " + device.getName());
                    /*if(device.description.equals("My cute phone")){
                    System.out.println("Description : MAGDY");
                    ColumnType coltype=new ColumnType();
                    coltype.setName("name");
                    ColumnType[] col={coltype};
                    ColumnValueType[] devicenames=new ColumnValueType[5];
                    devicenames=myPort.executeCCMSQLStatement("select name from device where name like'SEP%'", col, astheader, astheaderholder);
                    }else{
                    System.out.println("Description : "+device.getDescription());
                    }*/

                    System.out.println("Description : " + device.getDescription());
                    System.out.println("DirNumber : " + device.getDirNumber().substring(0, device.getDirNumber().indexOf("-")));
                    System.out.println("IpAddress : " + device.getIpAddress());
                    System.out.println("H323 Trunk Config Name : " + device.getH323Trunk().getConfigName());
                    System.out.println("Httpd Value : " + device.getHttpd().getValue());
                    System.out.println("Login User Id : " + device.getLoginUserId());
                    System.out.println("Model : " + device.getModel());
                    System.out.println("Status : " + device.getStatus().getValue());
                    System.out.println("------------------------------------------------------------------------");
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getPhoneIpByExten(String exten) {
        String phoneIp = "";
        try {
            RISService service = new RISServiceLocator(); // Instantiate the Axis Service object

            getCallManagerConfigurations();
            System.out.println("Ris After Update :" + risURL);
            RisPortType rport = null;
            try {

                rport = service.getRisPort(new java.net.URL(risURL)); // get the binding for RisPort

            } catch (MalformedURLException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServiceException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            }

            ((Stub) rport)._setProperty(Call.USERNAME_PROPERTY, axlUser); // If we cast rport as an Axis Stub class

            ((Stub) rport)._setProperty(Call.PASSWORD_PROPERTY, axlPassword); // we can set the HTTP basic auth credentials

            System.out.println("calling " + risURL);
            SelectItem item = new SelectItem();
            item.setItem("" + exten);
            SelectItem[] items = {item};
            CmSelectionCriteria cmSelectionCriteria = new CmSelectionCriteria();
            cmSelectionCriteria.setMaxReturnedDevices(new UnsignedInt(200));
            cmSelectionCriteria.set_class(DeviceClass._Any);
            cmSelectionCriteria.setModel(new UnsignedInt(255));
            cmSelectionCriteria.setStatus("Any");
            cmSelectionCriteria.setSelectBy("DirNumber");
            cmSelectionCriteria.setSelectItems(items);
            SelectCmDeviceResult cmselectresult = null;
            System.setProperty("javax.net.ssl.trustStore",
                    TrustStorePath);
            System.setProperty("javax.net.ssl.trustStorePassword",
                    TrustStorePass);

            cmselectresult = rport.selectCmDevice(new javax.xml.rpc.holders.StringHolder(""), cmSelectionCriteria);
            if (cmselectresult != null) {
                UnsignedInt devicesFound = cmselectresult.getTotalDevicesFound();
                System.out.println(devicesFound);
                CmNode[] nodes = cmselectresult.getCmNodes();
                  ArrayList<CmDevice> devicesList=new ArrayList<CmDevice>();
                if (nodes != null) {
                       for (int i=0;i<nodes.length;i++) {
                        System.out.println("Found CallManager At: " + nodes[i].getName()+"\n");
                       CmDevice[] devicesTemp=nodes[i].getCmDevices();
                       for(int z=0;z<devicesTemp.length;z++)
                           devicesList.add(devicesTemp[z]);
                    }
                    CmDevice[] devices;
                    if(devicesList.size()!=0)
                       devices=devicesList.toArray(new CmDevice[devicesList.size()]);
                    else
                        devices=new CmDevice[0];
                    if (devices.length != 0) {
                        phoneIp = devices[0].getIpAddress();
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);

        }
        return "" + phoneIp;
    }

    public static String[] getPhonesIpsByExtens(String[] extens) {
        String[] phonesIps = null;
        try {

            RISService service = new RISServiceLocator(); // Instantiate the Axis Service object
            getCallManagerConfigurations();
            RisPortType rport = null;
            try {
                rport = service.getRisPort(new java.net.URL(risURL)); // get the binding for RisPort
            } catch (MalformedURLException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServiceException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((Stub) rport)._setProperty(Call.USERNAME_PROPERTY, axlUser); // If we cast rport as an Axis Stub class
            ((Stub) rport)._setProperty(Call.PASSWORD_PROPERTY, axlPassword); // we can set the HTTP basic auth credentials
            System.out.println("calling " + risURL + " Using UN: " + axlUser + " Pass: " + axlPassword);
            SelectItem[] items = new SelectItem[extens.length];
            int index = 0;
            for (String ext : extens) {
                items[index] = new SelectItem();
                items[index++].setItem("" + ext);
            }
            CmSelectionCriteria cmSelectionCriteria = new CmSelectionCriteria();
            cmSelectionCriteria.setMaxReturnedDevices(new UnsignedInt(200));
            cmSelectionCriteria.set_class(DeviceClass._Any);
            cmSelectionCriteria.setModel(new UnsignedInt(255));
            cmSelectionCriteria.setStatus("Any");
            cmSelectionCriteria.setSelectBy("DirNumber");
            cmSelectionCriteria.setSelectItems(items);
            SelectCmDeviceResult cmselectresult = null;
            System.setProperty("javax.net.ssl.trustStore", TrustStorePath);
            System.setProperty("javax.net.ssl.trustStorePassword", TrustStorePass);
            cmselectresult = rport.selectCmDevice(new javax.xml.rpc.holders.StringHolder(""), cmSelectionCriteria);
            if (cmselectresult != null) {
                UnsignedInt devicesFound = cmselectresult.getTotalDevicesFound();
                System.out.println(devicesFound);
                CmNode[] nodes = cmselectresult.getCmNodes();
                ArrayList<CmDevice> devicesList=new ArrayList<CmDevice>();

                if (nodes != null) {
                    for (int i=0;i<nodes.length;i++) {
                        System.out.println("Found CallManager At: " + nodes[i].getName()+"\n");
                       CmDevice[] devicesTemp=nodes[i].getCmDevices();
                       for(int z=0;z<devicesTemp.length;z++)
                           devicesList.add(devicesTemp[z]);
                    }
                    CmDevice[] devices;
                    if(devicesList.size()!=0)
                       devices=devicesList.toArray(new CmDevice[devicesList.size()]);
                    else
                        devices=new CmDevice[0];


                    //phonesIps=new String[new Long(devicesFound).intValue()];
                    phonesIps = new String[extens.length];
                    for (int i = 0; i < extens.length; i++) {
                        String CurrentExt = extens[i];
                        for (int z = 0; z < devices.length; z++) {
                            //CmDevice CurrentDevice=devices[z];
                            if (devices[z].getDirNumber().contains(",")) {
                                String[] Lines = devices[z].getDirNumber().split(",");
                                for (int l = 0; l < Lines.length; l++) {
                                    if (Lines[l].split("-")[0].equalsIgnoreCase(CurrentExt) == true) {
                                        if (Lines[l].split("-")[1].equalsIgnoreCase("Registered") == true) {
                                            phonesIps[i] = devices[z].getIpAddress();
                                            System.out.println("Find IP " + devices[z].getIpAddress() + " For Ext " + CurrentExt);
                                            break;
                                        } else {
                                             System.out.println("Can't Find IP For Ext " + CurrentExt);
                                         //   phonesIps[i] = "";
                                        }
                                    }

                                }//For on Lines
                               
                            }//If Ext on IP Phone Which Has More Than on line
                            else {
                                if (devices[z].getDirNumber().split("-")[0].equalsIgnoreCase(CurrentExt) == true) {
                                    if (devices[z].getDirNumber().split("-")[1].equalsIgnoreCase("Registered") == true) {
                                        phonesIps[i] = devices[z].getIpAddress();
                                        System.out.println("Find IP " + devices[z].getIpAddress() + " with Ext " + CurrentExt);
                                        break;
                                    } else {
                                       // phonesIps[i] = "";
                                        System.out.println("Can't Find IP For Ext " + CurrentExt);
                                    }
                                }
                            }
                            //to get out of Device Loop
                            if (phonesIps[i] != null) {
                                break;
                            }
                        }
                        if (phonesIps[i] == null) {
                            phonesIps[i] = "";
                            System.out.println("Not In Devices and Can't Find IP For Ext " + CurrentExt);
                        }
                    }

                }

                System.out.println("|||||||IPS X Exts ||||||||DONE ||||||||||");


            }

        } catch (Exception ex) {
            Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);

        }
        return phonesIps;
    }

    public static void getPhoneByIp(String ip) {
        try {

            RISService service = new RISServiceLocator(); // Instantiate the Axis Service object
            getCallManagerConfigurations();
            RisPortType rport = null;
            try {

                rport = service.getRisPort(new java.net.URL(risURL)); // get the binding for RisPort

            } catch (MalformedURLException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServiceException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            }

            ((Stub) rport)._setProperty(Call.USERNAME_PROPERTY, axlUser); // If we cast rport as an Axis Stub class

            ((Stub) rport)._setProperty(Call.PASSWORD_PROPERTY, axlPassword); // we can set the HTTP basic auth credentials

            System.out.println("calling " + risURL);
            SelectItem item = new SelectItem();
            item.setItem("" + ip);
            SelectItem[] items = {item};
            CmSelectionCriteria cmSelectionCriteria = new CmSelectionCriteria();
            cmSelectionCriteria.setMaxReturnedDevices(new UnsignedInt(200));
            cmSelectionCriteria.set_class(DeviceClass._Any);
            cmSelectionCriteria.setModel(new UnsignedInt(255));
            cmSelectionCriteria.setStatus("Any");
            cmSelectionCriteria.setSelectBy("IpAddress");
            cmSelectionCriteria.setSelectItems(items);
            SelectCmDeviceResult cmselectresult = null;

            System.setProperty("javax.net.ssl.trustStore",
                    TrustStorePath);
            System.setProperty("javax.net.ssl.trustStorePassword",
                    TrustStorePass);
            cmselectresult = rport.selectCmDevice(new javax.xml.rpc.holders.StringHolder(""), cmSelectionCriteria);
            if (cmselectresult != null) {
                UnsignedInt devicesFound = cmselectresult.getTotalDevicesFound();
                System.out.println(devicesFound);
                CmNode[] nodes = cmselectresult.getCmNodes();
                for (CmNode node : nodes) {
                    System.out.println(node.getName());
                }
                CmDevice[] devices = nodes[0].getCmDevices();
                for (CmDevice device : devices) {
                    System.out.println("Name : " + device.getName());
                    /*if(device.description.equals("My cute phone")){
                    System.out.println("Description : MAGDY");
                    ColumnType coltype=new ColumnType();
                    coltype.setName("name");
                    ColumnType[] col={coltype};
                    ColumnValueType[] devicenames=new ColumnValueType[5];
                    devicenames=myPort.executeCCMSQLStatement("select name from device where name like'SEP%'", col, astheader, astheaderholder);
                    }else{
                    System.out.println("Description : "+device.getDescription());
                    }*/

                    System.out.println("Description : " + device.getDescription());
                    System.out.println("DirNumber : " + device.getDirNumber().substring(0, device.getDirNumber().indexOf("-")));
                    System.out.println("IpAddress : " + device.getIpAddress());
                    System.out.println("H323 Trunk Config Name : " + device.getH323Trunk().getConfigName());
                    System.out.println("Httpd Value : " + device.getHttpd().getValue());
                    System.out.println("Login User Id : " + device.getLoginUserId());
                    System.out.println("Model : " + device.getModel());
                    System.out.println("Status : " + device.getStatus().getValue());
                    System.out.println("------------------------------------------------------------------------");
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getPhoneByName(String name) {
        try {
            RISService service = new RISServiceLocator(); // Instantiate the Axis Service object
            getCallManagerConfigurations();
            RisPortType rport = null;
            try {

                rport = service.getRisPort(new java.net.URL(risURL)); // get the binding for RisPort

            } catch (MalformedURLException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServiceException ex) {
                Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
            }

            ((Stub) rport)._setProperty(Call.USERNAME_PROPERTY, axlUser); // If we cast rport as an Axis Stub class

            ((Stub) rport)._setProperty(Call.PASSWORD_PROPERTY, axlPassword); // we can set the HTTP basic auth credentials

            System.out.println("calling " + risURL);
            SelectItem item = new SelectItem();
            item.setItem("" + name);
            SelectItem[] items = {item};
            CmSelectionCriteria cmSelectionCriteria = new CmSelectionCriteria();
            cmSelectionCriteria.setMaxReturnedDevices(new UnsignedInt(200));
            cmSelectionCriteria.set_class(DeviceClass._Any);
            cmSelectionCriteria.setModel(new UnsignedInt(255));
            cmSelectionCriteria.setStatus("Any");
            cmSelectionCriteria.setSelectBy("Name");
            cmSelectionCriteria.setSelectItems(items);
            SelectCmDeviceResult cmselectresult = null;
            cmselectresult = rport.selectCmDevice(new javax.xml.rpc.holders.StringHolder(""), cmSelectionCriteria);
            if (cmselectresult != null) {
                UnsignedInt devicesFound = cmselectresult.getTotalDevicesFound();
                System.out.println(devicesFound);
                CmNode[] nodes = cmselectresult.getCmNodes();
                for (CmNode node : nodes) {
                    System.out.println(node.getName());
                }
                CmDevice[] devices = nodes[0].getCmDevices();
                for (CmDevice device : devices) {
                    System.out.println("Name : " + device.getName());
                    /*if(device.description.equals("My cute phone")){
                    System.out.println("Description : MAGDY");
                    ColumnType coltype=new ColumnType();
                    coltype.setName("name");
                    ColumnType[] col={coltype};
                    ColumnValueType[] devicenames=new ColumnValueType[5];
                    devicenames=myPort.executeCCMSQLStatement("select name from device where name like'SEP%'", col, astheader, astheaderholder);
                    }else{
                    System.out.println("Description : "+device.getDescription());
                    }*/

                    System.out.println("Description : " + device.getDescription());
                    System.out.println("DirNumber : " + device.getDirNumber().substring(0, device.getDirNumber().indexOf("-")));
                    System.out.println("IpAddress : " + device.getIpAddress());
                    System.out.println("H323 Trunk Config Name : " + device.getH323Trunk().getConfigName());
                    System.out.println("Httpd Value : " + device.getHttpd().getValue());
                    System.out.println("Login User Id : " + device.getLoginUserId());
                    System.out.println("Model : " + device.getModel());
                    System.out.println("Status : " + device.getStatus().getValue());
                    System.out.println("------------------------------------------------------------------------");
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(PhoneProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        //getPhoneByExten("1003");

        //getPhoneByIp("192.168.1.150");

        //getPhoneByName("SEP0026B911F8C6");

        //getPhoneByIp("192.168.1.2");
        //System.out.println("IP Is"+getPhoneIpByExten("8667"));


   getPhoneByExten("28381");

//   getPhoneByExten("29123");


      String[] exts ={"28381", "45123", "45124"};
        String[] ips = getPhonesIpsByExtens(exts);
        for (String ip : ips) {
            System.out.println(ip);
        }
    }
}
