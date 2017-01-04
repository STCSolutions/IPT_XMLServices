/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.paging.core;

import com.univox.pushtophone.Push2Phone;
import com.univox.pushtophone.Text2Base64;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMR
 */
public class ResiverJob implements Runnable{
    ArrayList<String> IPS;
    int port;
    String CucmCreiditials;
    public ResiverJob(ArrayList<String> ips,int  port,String CucmCreiditials) {
        this.IPS=ips;
        this.port=port;
        this.CucmCreiditials=CucmCreiditials;
    }




    public void run() {
        try {
            Push2Phone p2p = new Push2Phone();
          
            String[] ReciveCommand = {"RTPRx:Stop", "RTPTx:Stop", "RTPRx:" + InetAddress.getLocalHost().getHostAddress() + ":" + port};
            for (int i = 0; i < IPS.size(); i++) {
                if(!IPS.get(i).equals(""))
                p2p.push(ReciveCommand, IPS.get(i), Text2Base64.getBase64(CucmCreiditials), true);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ResiverJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
