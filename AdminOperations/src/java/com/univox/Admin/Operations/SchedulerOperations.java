/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.Admin.Operations;

import com.univox.Core.Streaming.AVTransmit2;
import com.univox.pushtophone.Push2Phone;
import com.univox.pushtophone.Text2Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Format;
import javax.media.MediaLocator;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import univoxipext.IPProvider;


/**
 *
 * @author AMR
 */
public class SchedulerOperations {

    String UpLoadDirectory = "";
    String BroadCastIP="";
    String BroadCastLevel="";
    String CallManager_ApplicationUser_Credintials = "";
    Push2Phone p2p = new Push2Phone();

    public SchedulerOperations() {
        Properties ProbFile = new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        UpLoadDirectory = ProbFile.getProperty("UpLoadDirectory");
         BroadCastIP = ProbFile.getProperty("BroadCastIP");
          BroadCastLevel = ProbFile.getProperty("BroadCastLevel");

        CallManager_ApplicationUser_Credintials = ProbFile.getProperty("CallManager_ApplicationUser_Credintials");
    }

    public void broadcastRtpStreaming(Hashtable<String, String> Members) {
        String WaveFileName = "";
        String Recipient = "";

        Enumeration<String> Keys = Members.keys();
        while (Keys.hasMoreElements()) {
            WaveFileName = Keys.nextElement();
            Recipient = Members.get(WaveFileName);
            String[] RecipientArr = Recipient.split(",");
            String WaveFilePath = UpLoadDirectory + WaveFileName;
            StreamJob job=new StreamJob(WaveFilePath, RecipientArr, CallManager_ApplicationUser_Credintials,BroadCastIP,BroadCastLevel);
            job.start();
            System.out.println("Streaming JOb Thread Was Fierd For Extentions :"+RecipientArr+" Playes Wave File On Path :"+WaveFilePath);
            ///

//To Here make it run in Separate Thread

        }
    }

    public static void main(String[] args) {
       
  //      System.out.println(PhoneProvider.getPhoneIpByExten("2009"));


    }
}

class StreamJob extends Thread {

    public String WaveFilePath = "";
    public String[] RecipientArr = null;
    String CallManager_ApplicationUser_Credintials = "";
    long Lenth = 60000;
    int MinPort = 20478;
    int MaxPort = 32768;
    Format form = null;
     String BroadCastIP="";
    String BroadCastLevel="";
    Push2Phone p2p = new Push2Phone();

    public StreamJob(String filePath, String[] RecArr, String Credintials, String BroadCastIP,String BroadCastLevel) {
        this.WaveFilePath = filePath;
        this.RecipientArr = RecArr;
        this.CallManager_ApplicationUser_Credintials = Credintials;
this.BroadCastIP=BroadCastIP;
this.BroadCastLevel=BroadCastLevel;
    }

    @Override
    public void run() {
        ///Make This Part To run in separate Thread
        String[] IPs =IPProvider.getPhonesIpsByExtens(RecipientArr);
        System.out.println("===Now Try To Broadcast ON Path :" + WaveFilePath + " on Extensions :" + RecipientArr);
        try {
            //Get wave Lenth
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(WaveFilePath));
            clip.open(ais);
            Lenth = clip.getMicrosecondLength() / 1000;
            System.out.println("The Track on Path :" + WaveFilePath + "Lenth is: " + Lenth);

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "::" + ex.getStackTrace());
        }
        AVTransmit2 Transmeter = null;
        String Result = "Can't open local data port:";
        while (Result.contains("Can't open local data port:")) {
            MinPort += 2;
            if (MinPort <= MaxPort) {
                Transmeter = new AVTransmit2(new MediaLocator("file:/" + WaveFilePath),BroadCastIP, String.valueOf(MinPort), form);//"file:/C:/iqama.wav"//"javasound://8000"
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
            String[] Reciving_urls = {"RTPRx:Stop", "RTPRx:"+BroadCastIP+":" + MinPort + ":"+BroadCastLevel};
            if(!PhoneIP.equals(""))
            p2p.push(Reciving_urls, PhoneIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);

        }

        try {
            Thread.sleep(Lenth);//1 Min
        } catch (InterruptedException ex) {
            Logger.getLogger(SchedulerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        Transmeter.stop();
        for (int i = 0; i < IPs.length; i++) {
            //Get IP
            System.out.println("1=== Push Stop Reciving Command TO Phones OP Port :" + MinPort);
            String PhoneIP = IPs[i];
            String[] Reciving_urls = {"RTPRx:Stop"};
            p2p.push(Reciving_urls, PhoneIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);
        }
        this.stop();
    }
}
