/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.paging.core;

import com.univox.pushtophone.Push2Phone;
import com.univox.pushtophone.Text2Base64;
import java.util.ArrayList;

/**
 *
 * @author AMR
 */
public class StopReciveJob implements Runnable{
 ArrayList<String> IPS;

    String CucmCreiditials;

    public StopReciveJob(ArrayList<String> ips,String CucmCreiditials) {
         this.IPS=ips;
        this.CucmCreiditials=CucmCreiditials;
    }

    public void run() {
         try {
            
            Push2Phone p2p = new Push2Phone();
            String[] ReciveCommand = {"RTPRx:Stop", "RTPTx:Stop"};
            for (int i = 0; i < IPS.size(); i++) {
               
                p2p.push(ReciveCommand, IPS.get(i), Text2Base64.getBase64(CucmCreiditials), true);
            }
        } catch (Exception ex) {
            
        }
    }

}
