/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.paging.core;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wmustafa
 */
public class LiveSender implements Runnable {

    private String[] ip;
    DatagramPacket packet;
    DatagramSocket socket;
    StreamData data;
    int senderPort;

    public LiveSender(StreamData data, int senderPort, String[] ip) {
        try {

            socket = new DatagramSocket();
            this.data = data;
            this.senderPort = senderPort;
            this.ip = ip;


        } catch (SocketException ex) {
       
        }
    }

    public void run() {
     
            InetAddress address = null;
            byte[] whatRecieved = new byte[172];
            while (true) {
                Packet p = data.getStreamPacket();
                if(p==null )continue;
                whatRecieved = p.getPacketByte();
                for (int i = 0; i < ip.length; i++) {
                try {

                    address = InetAddress.getByName(ip[i]);
                } catch (UnknownHostException ex) {
       
                }if(address != null)
                    packet = new DatagramPacket(whatRecieved, 172, address, this.senderPort);
                try {
                    socket.send(packet);
                } catch (IOException ex) {
                    }
                }
            }
      
         
   
    }
public static void main(String[] args) {

        FileOutputStream strem = null;
        try {
            byte[] whatRecieved = new byte[172];
            File file = new File("C://locall.dat");
            strem = new FileOutputStream(file);
            DataOutputStream dataStream = new DataOutputStream(strem);

            DatagramSocket socket = new DatagramSocket(20584);
            socket.setSoTimeout(100);
                    


            DatagramPacket packet;
            int x = 0;
            while (true) {


                packet = new DatagramPacket(whatRecieved, 172);
                socket.receive(packet);
                whatRecieved = packet.getData();
                dataStream.write(whatRecieved);
                dataStream.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(LiveSender.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
            try {
                strem.close();
            } catch (IOException ex) {
                Logger.getLogger(LiveSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
            
    
    }
}
