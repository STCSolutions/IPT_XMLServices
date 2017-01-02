/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.Univox.Core;

import Com.Azan.Web.Watcher;

import Com.Univox.Core.Streaming.AzanRtpStreeming;
import Com.Univox.pushtophone.Push2Phone;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMR
 */
public class AzanLoaderTask {

    Timer timer;
    PrayTimeIO PrayIo = new PrayTimeIO();
    Push2Phone p2p = new Push2Phone();

    public AzanLoaderTask() {


        Calendar calendar = Calendar.getInstance();
        timer = new Timer();
        timer.schedule(new AzanLoaderInit(), calendar.getTime(), 24 * 60 * 60 * 1000);
    }

    class AzanLoaderInit extends TimerTask {

        public void run() {
            try {

            PrayTimeIO PrayManger = new PrayTimeIO();
            if(PrayManger.doForceReload())
            PrayManger.WriteAzanXmlData("C:\\STCs\\PraysTimes.xml");
                System.out.println("PrayTimes Calculated");
            Hashtable<String, String> prayTimesTable = PrayManger.ReadPrayTimesXmlData("C:\\STCs\\PraysTimes.xml");
            Enumeration Keys = prayTimesTable.keys();
            while (Keys.hasMoreElements()) {
                String PrayName = (String) Keys.nextElement();
                new AzanRtpStreeming(PrayName, prayTimesTable.get(PrayName));
                System.out.println(PrayName + " at " + prayTimesTable.get(PrayName) + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(Watcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}

