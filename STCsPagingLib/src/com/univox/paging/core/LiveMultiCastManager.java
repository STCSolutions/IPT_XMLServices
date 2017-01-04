/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.paging.core;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author wmustafa
 */
public class LiveMultiCastManager implements MulticastManager {

    private String[] senders;//the LiveReciever Phones IP Note: it's called here senders ans they will
//use the sender module as the applicaton will send to them
    private int sendersPort;// the port all RecieverPhones will be listen to 
    private int recieverPort;// the LiveSender Phone Port
    private String recieverIP;// The LiveSender Phone IP
    StreamData streamData;//current session 
    Thread recieverThread;// the sread of the reciever phone 
    private Vector<Thread> runningSendersThreads;// the current running threads for the Senders
    private boolean debug;
    private ActionListener idleAction;

    public LiveMultiCastManager(int senderPort, String[] senders, int recieverPort, String recieverIP) {
        this.debug = false;
        this.sendersPort = senderPort;

        this.recieverPort = recieverPort;

        this.senders = senders;

        this.recieverIP = recieverIP;

        this.runningSendersThreads = new Vector<Thread>();
    }

    public void startMultiCast() {
        try {
            RTPCommandManager pCM = new RTPCommandManager();
            if (!debug) {
                pCM.sendRecieveCommand(senders, this.sendersPort,this.recieverIP);// on the other hand
                // this sends to the phone that will recieve from the sender Module.

                pCM.sendTransferCommand(recieverIP, this.recieverPort);//sends the transfer command to the 
                //ip phone which is listened by the reciever Module and that's way the name is recieverIP and LiveReciever port
            }
            this.recieverThread.start();
            for (int i = 0; i < this.runningSendersThreads.size(); i++) {
                this.runningSendersThreads.get(i).start();
            }
           
        } catch (Exception ex) {
        }
    }
    public void stopMultiCast() {
        try {
            this.recieverThread = null;
            RTPCommandManager pCM = new RTPCommandManager();
            pCM.sendStopRecieveCommand(senders);
            pCM.sendStopTransferCommand(recieverIP);
            for (int i = 0; i < this.runningSendersThreads.size(); i++) {
                this.runningSendersThreads.remove(i).stop();
            }
            if (this.recieverThread != null) {
                this.recieverThread.stop();
                System.gc();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void configure() {
        try {
            int numberOfPhones = senders.length;//get the number of the phones

            int numberOfPhonePerPackage=10;

            int numberOfThreads = (numberOfPhones / numberOfPhonePerPackage) + 1;//get the ceiling of the phones diveded by 35 representing # of threads

            System.out.println(numberOfThreads);
            this.streamData = new StreamData(numberOfThreads);


            LiveReciever reciever = new LiveReciever(this.recieverPort, this.streamData);
            reciever.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
             try{
                    stopMultiCast();
                    idleAction.actionPerformed(new ActionEvent(this, 12, "hello"));
                }
             catch(Exception ex)
             {
                 System.out.println("Error That: "+ex.getMessage());
             }

            }
            });


            recieverThread = new Thread(reciever, "Reciever");
            ArrayList<String> ipList;


            for (int i = 0; i < numberOfThreads; i++) {
                ipList = new ArrayList<String>();

                for (int j = 0; j <= numberOfPhonePerPackage; j++)//this for loop take the 35 phone and put it in array
                {
                    int indexOfCurrentPhone = (i * numberOfPhonePerPackage) + j;

                    if (indexOfCurrentPhone >= senders.length) {
                        break;
                    }
                    ipList.add(senders[indexOfCurrentPhone]);
                    System.out.println(senders[indexOfCurrentPhone]);



                }
                String[] ips = new String[ipList.size()];
                ips = ipList.toArray(ips);

                LiveSender sender = new LiveSender(this.streamData, this.sendersPort, ips);

                this.runningSendersThreads.add(new Thread(sender, "Sender"));
            }
        } catch (Exception ex) {
        }

    }

    public String[] getSenders() {
        return senders;
    }

    public void setSenders(String[] senders) {
        this.senders = senders;
    }

    public int getSendersPort() {
        return sendersPort;
    }

    public void setSendersPort(int sendersPort) {
        this.sendersPort = sendersPort;
    }

    public int getRecieverPort() {
        return recieverPort;
    }

    public void setRecieverPort(int recieverPort) {
        this.recieverPort = recieverPort;
    }

    public String getRecieverIP() {
        return recieverIP;
    }

    public void setRecieverIP(String recieverIP) {
        this.recieverIP = recieverIP;
    }

    public void addActionListener(ActionListener aL) {
        this.idleAction = aL;
    }

    public static void main(String[] args) {
    PortGenerator protato=new PortGenerator();
        int port1=protato.getFreePort();
        int port2=protato.getFreePort();
       String[] ips={"10.9.22.59","10.9.22.225"};
        //String[] ips={"10.100.110.108","10.100.110.172","10.100.110.179","10.100.110.160","10.100.110.183","10.100.110.182","10.100.110.109","10.100.110.85"};
        MulticastManager MCM=new LiveMultiCastManager(port1, ips, port2, "10.9.22.59");
        MCM.configure();
        MCM.startMultiCast();

    }
}
