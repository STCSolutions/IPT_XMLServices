package UnivoxAXLOperations;

import Com.UniVox.Axl.AXLProvider;
import com.cisco.ipphone.sdk.Phone;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class CallManagerOperation {

    public boolean changeAlertingName(String ext, String guestName, String mac) {
        try {

            //String xml = "<lines><line index=\"1\" uuid=\"{1A496506-7763-A6B3-132D-E1A417CDE005}\"><label>" + guestName + "</label><display>ITizMagix</display><dirn uuid=\"{58ACA7DD-F0CC-22C0-F65E-C954A95F4B31}\"/><ringSetting>Use System Default</ringSetting><consecutiveRingSetting>Use System Default</consecutiveRingSetting><ringSettingIdlePickupAlert>Use System Default</ringSettingIdlePickupAlert><ringSettingActivePickupAlert>Use System Default</ringSettingActivePickupAlert><displayASCII/><e164Mask/><dialPlanWizardId>0</dialPlanWizardId><mwlPolicy>Use System Policy</mwlPolicy><maxNumCalls>4</maxNumCalls><busyTrigger>2</busyTrigger><callInfoDisplay><callerName>true</callerName><callerNumber>false</callerNumber><redirectedNumber>false</redirectedNumber><dialedNumber>true</dialedNumber></callInfoDisplay><recordingProfileName/><monitoringCSSName/><recordingFlagName>Call Recording Disabled</recordingFlagName><audibleMWI>Off</audibleMWI><speedDial/><partitionUsage>General</partitionUsage><associatedEndusers/></line><line index=\"2\" uuid=\"{2EF8C952-7523-DC39-629E-6A115AEAB5AD}\"><label/><display/><dirn uuid=\"{681D298C-4167-1FBF-3128-BC65B2CBF7CC}\"/><ringSetting>Use System Default</ringSetting><consecutiveRingSetting>Use System Default</consecutiveRingSetting><ringSettingIdlePickupAlert>Use System Default</ringSettingIdlePickupAlert><ringSettingActivePickupAlert>Use System Default</ringSettingActivePickupAlert><displayASCII/><e164Mask/><dialPlanWizardId>0</dialPlanWizardId><mwlPolicy>Use System Policy</mwlPolicy><maxNumCalls>4</maxNumCalls><busyTrigger>2</busyTrigger><callInfoDisplay><callerName>true</callerName><callerNumber>false</callerNumber><redirectedNumber>false</redirectedNumber><dialedNumber>true</dialedNumber></callInfoDisplay><recordingProfileName/><monitoringCSSName/><recordingFlagName>Call Recording Disabled</recordingFlagName><audibleMWI>Off</audibleMWI><speedDial/><partitionUsage>General</partitionUsage><associatedEndusers/></line></lines>";
            int time = 1;
            int x = 0;
            AXLProvider axl = new AXLProvider("10.100.2.80", "admin", "pvpv!123", true);

            String userName = guestName;

            String res = axl.sendRequest("getPhone", "<phoneName>SEP" + mac + "</phoneName>");

//            SynchronizedLogger.log(res);
            //
            //          SynchronizedLogger.log("da b3d el request");

            String line1;
            String line2;

            String linesString = res.substring(res.indexOf("<lines>"), res.indexOf("</lines>") + 8);
            //        SynchronizedLogger.log(linesString);

            int midPoint = linesString.indexOf("</line>") + 7;

            line1 = (String) linesString.substring(0, midPoint);
            line2 = (String) linesString.substring(midPoint, linesString.length());
            boolean orderChanged = false;

            if (line2.matches(".*<line index=\"1\".*")) {
                String temp = line1;
                line1 = line2;
                line2 = temp;
                //  SynchronizedLogger.log("Caught");
                orderChanged = true;

            }


            if (line1.matches(".*<display>.*</display>.*")) {
                line1 = line1.replaceFirst("<display>.*</display>", "<display>" + userName + "</display>");
            } else {
                line1 = line1.replaceFirst("<display/>", "<display>" + userName + "</display>");
            }


            if (line1.matches(".*<label>.*</label>.*")) {
                line1 = line1.replaceFirst("<label>.*</label>", "<label>" + userName + "</label>");
            } else {
                line1 = line1.replaceFirst("<label/>", "<label>" + userName + "</label>");
            }
            if (orderChanged) {
                String temp = line1;
                line1 = line2;
                line2 = temp;
            }
            linesString = line1 + line2;

            boolean enterLoop = true;

            while (res.contains("503 Service Unavailable") || enterLoop) {
                x++;
                enterLoop = false;
                Thread.sleep(time);
                if (x == 5) {
                    return false;
                }
                time = 20000;
                res = axl.sendRequest("updatePhone", "<name>SEP" + mac + "</name>" + "<lines/>");
            }
            time = 1;
            x = 0;


            //  SynchronizedLogger.log("CallManagerOperation: updatePhone");
            //SynchronizedLogger.log("The string sent to the call manager is " + linesString);
            // Thread.sleep(20000);
            enterLoop = true;
            while (res.contains("503 Service Unavailable") || enterLoop) {
                enterLoop = false;
                Thread.sleep(time);
                time = 20000;
                res = axl.sendRequest("updatePhone", "<name>SEP" + mac + "</name>" + linesString);


            }
            time = 1;
            enterLoop = true;
            //SynchronizedLogger.log("The result of the updatePhone \n" + res);
            //SynchronizedLogger.log("");
            //SynchronizedLogger.log("CallManagerOperation: updateLine");
            //SynchronizedLogger.log("The username sent to the call manager is " + ext + "  " + userName);
            // Thread.sleep(20000);
            while (res.contains("503 Service Unavailable") || enterLoop) {
                x++;
                enterLoop = false;
                if (x == 5) {
                    return false;
                }
                Thread.sleep(time);

                time = 20000;
                res = axl.sendRequest("updateLine", "<pattern>" + ext + "</pattern><alertingName>" + userName + "</alertingName>");

            }
            //SynchronizedLogger.log("The result of updateLine\n" + res);

            return true;

        } catch (Exception ex) {

            //SynchronizedLogger.error(ex);

            return false;

        }

    }

    public boolean changeCSS(String ext, String cssFlag) {
        try {
            String css;
            Properties ProbFile = new Properties();
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
            String callManagerIP = ProbFile.getProperty("callManagerIP");
            System.out.println(callManagerIP);
            String callManagerUserName = ProbFile.getProperty("callManagerUserName");
            String callManagerPass = ProbFile.getProperty("callManagerPass");
            System.out.println("CallManager User Name: "+callManagerUserName);

            if(cssFlag.equals("PhoneLock_Css"))
            {
                 css= ProbFile.getProperty("LockCSS");
            }
            else
            {
                css= ProbFile.getProperty("UnLockCSS");
            }
            System.out.println(ext + "  Change CSS To  " + css);
            
            int x = 0;
            AXLProvider axl = new AXLProvider(callManagerIP, callManagerUserName, callManagerPass, true);
            String res;
            res = axl.sendRequest("getCSS", "<name>" + css + "</name>");
            System.out.println("The Current Css is :"+res);
            System.out.println("--------------------");
            int i = res.indexOf("uuid");
            String cssUUID = res.substring(i + 6, i + 44);
            System.out.println("After Substring the Line :"+cssUUID);
            boolean enterLoop = true;
            int time = 1;
while (res.contains("503 Service Unavailable") || enterLoop) {
                x++;
                enterLoop = false;
                if (x == 5) {
                    return false;
                }
                Thread.sleep(time);
                time = 20000;
                 res = axl.sendRequest("updateLine", "<pattern>" + ext + "</pattern><shareLineAppearanceCSS uuid=\"" + cssUUID + "\"/>");
            }
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
    public static void main(String[] args) {
        CallManagerOperation op = new CallManagerOperation();
        //op.clearPhoneHistor("192.168.33.207");
        //op.changeCSS("1007", "Concord-International-CSS");
        op.changeCSS("1021", "PhoneLock_Css");//PhoneLock_Css//Unlock_Css
        //   op.enableDND("247", false);
///       String mac = "00235eb68287";
 //       String mac2 = "0022555EB80E";//"0019E8AFA11A"//"B8AC6F4F2A8A";
        /// for(int i = 0 ; i < 10;i++)
        //   op.changeAlertingName("8667", "IST Test", mac2);
        //     SynchronizedLogger.log(Constants.ccmPass);
        //Phone.push("Dial:7990", "10.97.112.131", "cisco", "cisco", true);
//        op.showWelcomeScreen("EN", "Title", "10.97.112.131");
    }
}


