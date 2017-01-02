/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.Univox.Core.Streaming;

import Com.Univox.Core.CheckPhone;
import Com.Univox.Core.PrayTimeIO;
import Com.Univox.pushtophone.Push2Phone;
import Com.Univox.pushtophone.Text2Base64;
import com.DB.EmployeOperations;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Format;
import univoxipext.IPProvider;

/**
 *
 * @author XPPRESP3
 */
public class AzanRtpStreeming {

    Timer timer;
    int MinPort = 20478;// 1022;
    int MaxPort = 32768;//65534;
    PrayTimeIO PrayIo = new PrayTimeIO();
    Push2Phone p2p = new Push2Phone();

    public AzanRtpStreeming(String PrayName, String PrayTime) {

        String[] TimeTokens = PrayTime.split(":");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(TimeTokens[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(TimeTokens[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(TimeTokens[2]));

        timer = new Timer();
        //System.out.println(calendar.getTime().toString());
        timer.schedule(new AzanTask(PrayName, calendar.getTime()), calendar.getTime());
    }

    class AzanTask extends TimerTask {

        public boolean Flag = true;
        public String SalatName = "";
        public long Lenth;

        public AzanTask(String SalatName, Date Tasktime) {
            this.SalatName = SalatName;
            Calendar CurrentTime = Calendar.getInstance();
            if (Tasktime.before(CurrentTime.getTime())) {
                Flag = false;
            }
        }

        public void run() {
            if (Flag) {
                String SalatPageUrl = "";
                String CallManager_ApplicationUser_Credintials = "";
                Properties ProbFile = new Properties();
                try {
                    ProbFile.load(new FileInputStream(new File("C:\\STCs\\Location.properties")));
                    SalatPageUrl = ProbFile.getProperty("SalatPageUrl");
                    ProbFile.clear();
                    ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
                    CallManager_ApplicationUser_Credintials = ProbFile.getProperty("CallManager_ApplicationUser_Credintials");
                } catch (IOException ex) {
                    Logger.getLogger(AzanRtpStreeming.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(SalatPageUrl);
                EmployeOperations OP = new EmployeOperations();               
                ArrayList<String> IPPhonesExtList = OP.getExtentions();
                String[] ExtList = IPPhonesExtList.toArray(new String[IPPhonesExtList.size()]);
                String[] IPs = IPProvider.getPhonesIpsByExtens(ExtList);
                for (int i = 0; i < IPs.length; i++) {
                    //Get IP
                    System.out.println("IP num" + i + " = " + IPs[i]);
                    String PhoneIP = IPs[i];
                    if (!PhoneIP.equals("")) {
                        p2p.push(new String[]{SalatPageUrl + "?SalatName=" + SalatName}, PhoneIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        String time = "15:30:00";
//        SimpleDateFormat fore=new SimpleDateFormat("hh:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 2);
//        calendar.set(Calendar.MINUTE, 48);
//        calendar.set(Calendar.SECOND, 0);
//        System.out.println(calendar.getTime());



        new AzanRtpStreeming("Aser", time);
        System.out.format("Task scheduled.%n");


    }
}
