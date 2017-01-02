/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.paging.core;

import com.univox.pushtophone.Push2Phone;
import com.univox.pushtophone.Text2Base64;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMR
 */
public class RTPCommandManager {

    public String CallManager_ApplicationUser_Credintials = "";

    public RTPCommandManager() {
        try {
            Properties ProbFile = new Properties();
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
            CallManager_ApplicationUser_Credintials = ProbFile.getProperty("CallManager_ApplicationUser_Credintials");
        } catch (Exception ex) {
            Logger.getLogger(RTPCommandManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendRecieveCommand(String[] Ips, int port, String TransmiterIP) {
        int numberOfPhones = Ips.length;//get the number of the phones

        int numberOfPhonePerPackage = 10;

        int numberOfThreads = (numberOfPhones / numberOfPhonePerPackage) + 1;//get the ceiling of the phones diveded by 35 representing # of threads
        ArrayList<String> ipList;
        if (Ips.length != 0) {
            try {
                for (int i = 0; i < numberOfThreads; i++) {
                    ipList = new ArrayList<String>();
                    System.out.println("Trasmiting IP Is :" + TransmiterIP);
                    for (int j = 0; j <= numberOfPhonePerPackage; j++)//this for loop take the 35 phone and put it in array
                    {
                        int indexOfCurrentPhone = (i * numberOfPhonePerPackage) + j;

                        if (indexOfCurrentPhone >= Ips.length) {
                            break;
                        }
                        if (!Ips[indexOfCurrentPhone].equals(TransmiterIP)) {
                            ipList.add(Ips[indexOfCurrentPhone]);
                        } else {
                            System.out.println("Afsho IP :" + TransmiterIP);
                        }

                    }
                    System.out.println("Number Of Recivers = " + ipList.size());
                    if (ipList.size() != 0) {
                        Runnable streamjob = new ResiverJob(ipList, port, CallManager_ApplicationUser_Credintials);
                        Thread StreamThread = new Thread(streamjob, "StreamThread :" + i);
                        StreamThread.start();
                    }

                }
            } catch (Exception ex) {
                Logger.getLogger(RTPCommandManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void sendTransferCommand(String TransmitterIP, int port) {
        try {
            String[] ReciveCommand = {"RTPRx:Stop", "RTPTx:Stop", "RTPTx:" + InetAddress.getLocalHost().getHostAddress() + ":" + port};
            Push2Phone p2p = new Push2Phone();
            System.out.println("Credntials :" + CallManager_ApplicationUser_Credintials);
            p2p.push(ReciveCommand, TransmitterIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);
        } catch (UnknownHostException ex) {
            Logger.getLogger(RTPCommandManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendStopRecieveCommand(String[] Ips) {
        int numberOfPhones = Ips.length;//get the number of the phones

        int numberOfPhonePerPackage = 10;

        int numberOfThreads = (numberOfPhones / numberOfPhonePerPackage) + 1;//get the ceiling of the phones diveded by 35 representing # of threads
        ArrayList<String> ipList;
        if (Ips.length != 0) {
            try {
                for (int i = 0; i < numberOfThreads; i++) {
                    ipList = new ArrayList<String>();

                    for (int j = 0; j <= numberOfPhonePerPackage; j++)//this for loop take the 35 phone and put it in array
                    {
                        int indexOfCurrentPhone = (i * numberOfPhonePerPackage) + j;

                        if (indexOfCurrentPhone >= Ips.length) {
                            break;
                        }
                        ipList.add(Ips[indexOfCurrentPhone]);
                        System.out.println(Ips[indexOfCurrentPhone]);
                    }
                    System.out.println("Recivers Stop Command number : " + ipList.size());
                    if (ipList.size() != 0) {
                        Runnable Stopjob = new StopReciveJob(ipList, CallManager_ApplicationUser_Credintials);
                        Thread StreamThread = new Thread(Stopjob, "StopJobThread :" + i);
                        StreamThread.start();
                    }

                }
            } catch (Exception ex) {
                Logger.getLogger(RTPCommandManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendStopTransferCommand(String TransmitterIP) {

        String[] ReciveCommand = {"RTPRx:Stop", "RTPTx:Stop"};
        Push2Phone p2p = new Push2Phone();
        p2p.push(ReciveCommand, TransmitterIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);

    }
}
