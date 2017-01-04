/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.paging.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wmustafa
 */
public class LiveReciever implements Runnable  {


    private int recieverPortNumber;
    private StreamData currentSession;
    private DatagramSocket recieverSocket;
    private DatagramPacket packet;

    private ActionListener idleAction;
  
    public LiveReciever(int recieverPortNumber,StreamData currentSession){
        try {
            
            
            
            
            
            this.recieverPortNumber = recieverPortNumber;
            this.currentSession = currentSession;
            recieverSocket = new DatagramSocket(this.recieverPortNumber);
            recieverSocket.setSoTimeout(11000);
        
        
              
          
        } catch (SocketException ex) {
          
        }
    
    
    
    }
    
    
    public void run() {
       byte[] whatRecieved = new byte[172]; 
       Packet streamPacket = new Packet(); 
       try {
       while(true){
       packet = new DatagramPacket(whatRecieved,172);
            
               
                recieverSocket.receive(packet);
                streamPacket.setPacketByte(packet.getData());
                
                currentSession.addStreamPacket(streamPacket);
            
       
       }} catch (IOException ex) {
                idleAction.actionPerformed(new ActionEvent(this,12,"Hang up phone"));
                
          
            }
       }
public void addActionListener(ActionListener aL)
{
this.idleAction = aL;
}
    
    
    //setters and getters//
    

    public int getRecieverPortNumber() {
        return recieverPortNumber;
    }

    public void setRecieverPortNumber(int recieverPortNumber) {
        this.recieverPortNumber = recieverPortNumber;
    }

   

    public DatagramSocket getRecieverSocket() {
        return recieverSocket;
    }

    public void setRecieverSocket(DatagramSocket recieverSocket) {
        this.recieverSocket = recieverSocket;
    }

    public static void main(String[] args) {
        try {byte[] whatRecieved = new byte[172];
            File file = new File("C://locall.dat");
          
            FileInputStream strem = new FileInputStream(file);
                DataInputStream dataStream = new DataInputStream(strem);
                
            DatagramSocket socket = new DatagramSocket(20584);
           InetAddress address = InetAddress.getByName("192.168.33.207");
           DatagramPacket packet ;int x = 0 ; 
       while(true){
      Thread.sleep(20);
          int numberRead =  dataStream.read(whatRecieved, x*172, 172);
           System.out.println("helo");
          if(numberRead < 172)break;
          packet =   new DatagramPacket(whatRecieved, 172, address, 23642);
          socket.send(packet);
       }
        } catch (InterruptedException ex) {
            Logger.getLogger(LiveReciever.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LiveReciever.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    }

}
