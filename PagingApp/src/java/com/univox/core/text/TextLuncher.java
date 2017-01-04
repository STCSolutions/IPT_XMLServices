/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.core.text;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMR
 */
public class TextLuncher {
 private String[] Recivers;
 private String CallManager_Credntials;
 private String TransmitterIP;
 private String BroadcastMessage;
 private String pushUrl;

    public TextLuncher(String[] Recivers, String CallManager_Credntials, String TransmitterIP, String BroadcastMessage, String pushUrl) {
        this.Recivers = Recivers;
        this.CallManager_Credntials = CallManager_Credntials;
        this.TransmitterIP = TransmitterIP;
        this.BroadcastMessage = BroadcastMessage;
        this.pushUrl = pushUrl;
    }
   



        public void sendRecieveCommand()
    {
           int numberOfPhones = this.Recivers.length;//get the number of the phones

            int numberOfPhonePerPackage=10;

            int numberOfThreads = (numberOfPhones / numberOfPhonePerPackage) + 1;//get the ceiling of the phones diveded by 35 representing # of threads
 ArrayList<String> ipList;
        if (Recivers.length != 0) {
            try {
                 for (int i = 0; i < numberOfThreads; i++) {
                ipList = new ArrayList<String>();
                     System.out.println("Trasmiting IP Is :"+TransmitterIP);
                for (int j = 0; j <= numberOfPhonePerPackage; j++)//this for loop take the 35 phone and put it in array
                {
                    int indexOfCurrentPhone = (i * numberOfPhonePerPackage) + j;

                    if (indexOfCurrentPhone >=Recivers.length) {
                        break;
                    }
                    if( !Recivers[indexOfCurrentPhone].equals(TransmitterIP))
                    ipList.add(Recivers[indexOfCurrentPhone]);
                    else
                        System.out.println("Afsho IP :"+TransmitterIP);

                }
                     System.out.println("Number Of Recivers = "+ipList.size());
                     if(ipList.size()!=0)
                     {
Runnable streamjob=new ResiverJob(ipList,CallManager_Credntials,BroadcastMessage,pushUrl);
Thread StreamThread=new Thread(streamjob, "TextStreamThread :"+i);
StreamThread.start();
                     }

               }
            }
            catch (Exception ex) {
                Logger.getLogger(TextLuncher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
