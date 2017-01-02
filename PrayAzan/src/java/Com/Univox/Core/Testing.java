/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Com.Univox.Core;

import Com.Univox.Core.Streaming.AzanRtpStreeming;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author XPPRESP3
 */
public class Testing {
    public static void main(String[] args) {
        try {
            PrayTimeIO PrayManger = new PrayTimeIO();
            if (PrayManger.doForceReload()) {
                PrayManger.WriteAzanXmlData("D:\\Work\\STCs\\PrayerTimes\\PraysTimes.xml");
            }
            Hashtable<String, String> prayTimesTable = PrayManger.ReadPrayTimesXmlData("D:\\Work\\STCs\\PrayerTimes\\PraysTimes.xml");
            Enumeration Keys = prayTimesTable.keys();
            while (Keys.hasMoreElements()) {
                String PrayName = (String) Keys.nextElement();
                new AzanRtpStreeming(PrayName, prayTimesTable.get(PrayName));
                System.out.println("K");
            }
        } catch (IOException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
