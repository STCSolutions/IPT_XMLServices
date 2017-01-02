/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.core.text;

import com.univox.Paging.pushtophone.Push2Phone;
import com.univox.Paging.pushtophone.Text2Base64;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMR
 */
public class ResiverJob implements Runnable{
    ArrayList<String> IPS;
    String CucmCreiditials;
    String BroadcastMessage;
    String pushUrl;
    public ResiverJob(ArrayList<String> ips,String CucmCreiditials,String BroadcastMessage,String pushUrl) {
        this.IPS=ips;
      this.BroadcastMessage=BroadcastMessage;
      this.pushUrl=pushUrl;
        this.CucmCreiditials=CucmCreiditials;
    }




    public void run() {
        try {

            Push2Phone p2p = new Push2Phone();
           
            String[] ReciveCommand = {pushUrl+"?broadmessage="+URLEncoder.encode(BroadcastMessage)};
            for (int i = 0; i < IPS.size(); i++) {
                if(!IPS.get(i).equals(""))
                p2p.push(ReciveCommand, IPS.get(i), Text2Base64.getBase64(CucmCreiditials), true);
            }
        } catch (Exception ex) {
            Logger.getLogger(ResiverJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}