package com.univox.paging.core;


import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wmustafa
 */
public class StreamData {

    Queue<Packet> data;
    int numberOfThreads;
    int numberOfFinishedThreads = 0;
    Packet currentData;

    public StreamData(int noOfThreads) {

        data = new LinkedList<Packet>();

        this.numberOfThreads = noOfThreads;

    }

  synchronized  public void addStreamPacket(Packet p) {
        data.add(p);
   
        this.notifyAll();

    }

    synchronized public Packet getStreamPacket()  {
     
        if(numberOfFinishedThreads == 0)
        {
            if(data.isEmpty())
         return null;
            
            currentData = data.remove();
    
        
        }
        numberOfFinishedThreads++;

  
        if (numberOfFinishedThreads == numberOfThreads) {
            
           
            numberOfFinishedThreads = 0;

        }
        return currentData;
    }
}
