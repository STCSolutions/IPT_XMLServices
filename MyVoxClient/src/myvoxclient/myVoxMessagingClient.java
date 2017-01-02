/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myvoxclient;

import com.ADConnector.User;
import com.DB.EmployeOperations;
import com.DB.PersonalContact;
import com.DB.TimeSheetRecord;
import com.univox.core.Streaming.AVTransmit2;
import com.univox.pushtophone.Push2Phone;
import com.univox.pushtophone.Text2Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Format;
import javax.media.MediaLocator;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import univoxipext.IPProvider;

/**
 *
 * @author Amr
 */
public class myVoxMessagingClient {

//    private static MyVoxCallHandlerServerService service = new MyVoxCallHandlerServerService();
    //  private static MyVoxCallHandlerServer port = service.getMyVoxCallHandlerServerPort();
 
    static String CallManager_ApplicationUser_Credintials = "";
    public static long Lenth = 60000;
    public static String UpLoadDirectory = "";
    public static String BroadCastIP = "";
    public static String BroadCastLevel = "";

    public static void sendSms(String employeeName, String reciptient, String message) {

        EmployeOperations op = new EmployeOperations();
        String[] reciptients = reciptient.split(",");
        for (int x = 0; x < reciptients.length; x++) {
            op.insertSMSHistory(employeeName, message, reciptients[x]);
        }
    }

    public static ArrayList<PersonalContact> GetUserPeronalContact(String AcountUser) {
        EmployeOperations op = new EmployeOperations();
        return op.getAccountPersonalContacts(AcountUser.toLowerCase());
    }

    public static Hashtable<String, ArrayList<User>> GetUsersGroups() throws Exception {
        EmployeOperations op = new EmployeOperations();
        ArrayList<User> Users = op.getUsersInfo_DB();
        ArrayList<String> Groups = op.GetGroups();
        ArrayList<String> OUs = op.GetOUs();
        Hashtable<String, ArrayList<User>> UserGroups = op.UserAsignedGroups(Groups, Users);
        Hashtable<String, ArrayList<User>> UserCategories = op.UserAsignedGOU(OUs, Users);
        UserCategories.putAll(UserGroups);
        return UserCategories;
    }

    public static ArrayList<User> GetUserInfo() throws Exception {

        EmployeOperations OP = new EmployeOperations();
        ArrayList<User> Users = null;
            Users = OP.getUsersInfo_DB();
        return Users;
    }

    public static ArrayList<TimeSheetRecord> GetTimeSheetData() {
        EmployeOperations op = new EmployeOperations();
        ArrayList<TimeSheetRecord> Sheet = op.getTimeSheetData();
        return Sheet;
    }

    public static void makeCall(String sourceExtn, String destExtn) {
        Properties ProbFile = new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {
        }
        CallManager_ApplicationUser_Credintials = ProbFile.getProperty("CallManager_ApplicationUser_Credintials");
        Push2Phone p2p = new Push2Phone();
        String[] url = {"Dial:" + destExtn};
        String IP = IPProvider.getPhoneIpByExten(sourceExtn);
        if (!IP.equals("")) {
            p2p.push(url, IP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);
        }
    }

    public static void AzanRegister(String phoneExtn, String Flag) {
        EmployeOperations op = new EmployeOperations();
        op.RigisterAzan(phoneExtn, Flag);
    }

    public static void ChangeUserPassword(String ADUserAcount, String Pin) {
        EmployeOperations op = new EmployeOperations();
        op.ChangePassword(ADUserAcount, Pin);
    }

    public static void DeletePersonalContact(String Name, String Company, String AcountUser) {
        EmployeOperations op = new EmployeOperations();
        op.DeletePerosnalContactRecord(Name, Company, AcountUser.toLowerCase());
    }

    public static void UpdatePersonalContact(String Name, String Company, String OfficeNumber, String Mobile, String AcountUser) {
        EmployeOperations op = new EmployeOperations();
        op.UpdatePerosnalContactRecord(Name, Company, OfficeNumber, Mobile, AcountUser.toLowerCase());
    }

    public static void AddContact(String Name, String Company, String OfficeNumber, String Mobile, String AcountUser) {
        EmployeOperations op = new EmployeOperations();
        op.AddContactRecord(Name, Company, OfficeNumber, Mobile, AcountUser.toLowerCase());
    }

    public static void SignIn(String phoneExtn) {
        EmployeOperations op = new EmployeOperations();
        String EmployeeName = op.getUserName(phoneExtn);
        op.SignIn(EmployeeName, phoneExtn, "MyVox");
    }

    public static void SignOut(String phoneExtn) {
        EmployeOperations op = new EmployeOperations();
        String EmployeeName = op.getUserName(phoneExtn);
        op.SignOut2(EmployeeName);
    }

    public static Boolean UserAuthentication(String AcountName, String Pin) {
        EmployeOperations op = new EmployeOperations();
        return op.employeeCheck(AcountName, Pin);
    }

    public static void AddSceduleBroadcast(String BroadCastDate, byte[] byteArray, Object[] extensArr) {
        String WaveFilePath = "";
        Properties ProbFile = new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {
        }
        UpLoadDirectory = ProbFile.getProperty("UpLoadDirectory");

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bais);
            AudioFormat formate = ais.getFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, formate);
            SourceDataLine sourceDataLineTemp = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLineTemp.open(formate);
            sourceDataLineTemp.start();
            Random randomGenerator = new Random();
            WaveFilePath = "WavFile" + randomGenerator.nextInt() + ".wav";
            File fileOut = new File(UpLoadDirectory + WaveFilePath);
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
            if (AudioSystem.isFileTypeSupported(fileType,
                    ais)) {
                System.out.println("Trying to write");
                AudioSystem.write(ais, fileType, fileOut);
                System.out.println("Written successfully");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[] RecipientGroup = new String[extensArr.length];
        int index = 0;
        for (Object ext : extensArr) {
            RecipientGroup[index++] = (String) ext;
        }
        if (BroadCastDate.equals("") || BroadCastDate == null || WaveFilePath.equals("") || WaveFilePath == null || RecipientGroup.length == 0) {
            System.out.println("Parameters are not sufficient");
        } else {
            EmployeOperations op = new EmployeOperations();
            System.out.println("Elamfrod yktb ");
            op.AddSceduleBroadcastItem(BroadCastDate, WaveFilePath, RecipientGroup);
        }
    }

    public static void BroadcastNow(byte[] byteArray, Object[] extensArr) {
        String WaveFilePath = "";

        Properties ProbFile = new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {
        }
        UpLoadDirectory = ProbFile.getProperty("UpLoadDirectory");
        BroadCastIP = ProbFile.getProperty("BroadCastIP");
        BroadCastLevel = ProbFile.getProperty("BroadCastLevel");
        CallManager_ApplicationUser_Credintials = ProbFile.getProperty("CallManager_ApplicationUser_Credintials");
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bais);
            AudioFormat formate = ais.getFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, formate);
            SourceDataLine sourceDataLineTemp = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLineTemp.open(formate);
            sourceDataLineTemp.start();
            Random randomGenerator = new Random();
            WaveFilePath = "WavFile" + randomGenerator.nextInt() + ".wav";
            File fileOut = new File(UpLoadDirectory + WaveFilePath);
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
            if (AudioSystem.isFileTypeSupported(fileType, ais)) {
                System.out.println("Trying to write");
                AudioSystem.write(ais, fileType, fileOut);
                System.out.println("Written successfully");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[] RecipientGroup = new String[extensArr.length];
        int index = 0;
        for (Object ext : extensArr) {
            RecipientGroup[index++] = (String) ext;
        }
        if (WaveFilePath.equals("") || WaveFilePath == null || RecipientGroup.length == 0) {
            System.out.println("Parameters are not sufficient");
        } else {
            //          Fire The Broadcast
            System.out.println("The Extensions Are :" + RecipientGroup.toString());
            String[] IPs = IPProvider.getPhonesIpsByExtens(RecipientGroup);
            System.out.println("===Now Try To Broadcast ON Path :" + UpLoadDirectory + WaveFilePath + " on Extensions :" + RecipientGroup);
            try {
                //Get wave Lenth
                Clip clip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(UpLoadDirectory + WaveFilePath));
                clip.open(ais);
                Lenth = clip.getMicrosecondLength() / 1000;
                System.out.println("The Track on Path :" + UpLoadDirectory + WaveFilePath + "Lenth is: " + Lenth);

            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "::" + ex.getStackTrace());
            }
            int MinPort = 20478;
            int MaxPort = 32768;
            Format form = null;
            Push2Phone p2p = new Push2Phone();
            AVTransmit2 Transmeter = null;
            String Result = "Can't open local data port:";
            while (Result.contains("Can't open local data port:")) {
                MinPort += 2;
                if (MinPort <= MaxPort) {
                    Transmeter = new AVTransmit2(new MediaLocator("file:/" + UpLoadDirectory + WaveFilePath), BroadCastIP, String.valueOf(MinPort), form);//"file:/C:/iqama.wav"//"javasound://8000"
                    Result = Transmeter.start();
                    if (Result == null) {
                        Result = "";
                    }
                } else {
                    MinPort = 20478;
                }
            }
            for (int i = 0; i < IPs.length; i++) {
                //Get IP
                System.out.println("1=== Push Recive Command TO Phones OP Port :" + MinPort);
                System.out.println("IP num" + i + " = " + IPs[i]);
                String PhoneIP = IPs[i];
                String[] Reciving_urls = {"RTPRx:Stop", "RTPRx:" + BroadCastIP + ":" + MinPort + ":" + BroadCastLevel};
                p2p.push(Reciving_urls, PhoneIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);
            }
            try {
                Thread.sleep(Lenth);//1 Min
            } catch (InterruptedException ex) {
            }
            Transmeter.stop();
            for (int i = 0; i < IPs.length; i++) {
                //Get IP
                System.out.println("1-Push Stop Reciving Command TO Phones OP Port :" + MinPort);
                String PhoneIP = IPs[i];
                String[] Reciving_urls = {"RTPRx:Stop"};
                p2p.push(Reciving_urls, PhoneIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);
            }
        }
    }

    public static void main(String args[]) {
        try {

            //System.out.println(UserAuthentication("Amr@Univox.Com", "123"));
             makeCall("2049", "2054");
            //SignOut("0000");
            //PhoneProvider.getPhoneByExten("2009");
            //SignOut("1000");
            //GetUserInfo();
            //sendSms("Amr", "+966506241309,+966506241309,+966506241309,+966506241309,+966506241309,+966506241309,+966506241309,+966506241309,+966506241309", "لا اله الا الله محمد رسول الله");
            /*User u=new User();
            u.setIpPhoneExt("1000");
            u.setName("Magdy Alhoseiny");
            String[] dest={"1001","1003"};
            Hashtable<String,String>result=createMeetMe(u, dest);
            for(String s:result.keySet()){
            System.out.println(s+"\t\t"+result.get(s).toString());
            }*/
            /*String u="1000,Magdy Alhoseiny";
            String[] dest={"1001","1003"};
            Hashtable<String,String>result=createMeetMe(u, dest);
            for(String s:result.keySet()){
            System.out.println(s+"\t\t"+result.get(s).toString());
            }*/
            /*String u="1000,Magdy Alhoseiny";
            String[] dest={"1001","1003"};
            Dictionary<Object,Object>result=createMeetMe(u, dest);
            Enumeration res=result.keys();
            for(int i=0;i<result.size();i++){
            String key=res.nextElement().toString();
            System.out.println(key+"\t\t"+result.get(key));
            }*/
            //        String u="1000,Magdy Alhoseiny";
            //        String[] dest={"1000","1001","1003"};
            //        ArrayList<String>result=createMeetMe(u, dest);
            //        for(String res:result){
            //            System.out.println(res);
            //        }
            //      getNextMeetMeExt();
            /* String[] dest={"1000","1001","1003"};
            byte[] byteArray={1,3,5,7,9,1,3,5,7,1,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,99,1,3,5,1,3,5,7,91,3,5,7,91,3,5,7,91,3,5,7,97,9,1,3,5,7,9,1,3,5,7,9,1,3,5,7,9};
            BroadcastNow(byteArray, dest);*/
            //  AddSceduleBroadcast("2010/09/19 2:00", byteArray, dest);
        } catch (Exception ex) {
            Logger.getLogger(myVoxMessagingClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
