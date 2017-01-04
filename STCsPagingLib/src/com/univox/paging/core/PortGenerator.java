/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.paging.core;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.Random;

/**
 *
 * @author AMR
 */
public class PortGenerator {

    public int getFreePort() {
        int Minport = 20482;
        int MaxPort = 32768;
        Random Rand = new Random();
        int range = MaxPort - Minport + 1;
        int port = (int) (range * Rand.nextDouble()) + Minport;
        while (Minport <= port && port <= MaxPort) {
            if (port % 2 == 0) {
                DatagramSocket ds = null;
                try {
                    ds = new DatagramSocket(port);
                    ds.close();
                    //return port;
                } catch (IOException e) {
                    System.out.println("Error : in" + port);
                    port = (int) (range * Rand.nextDouble()) + Minport;
                    continue;
                }
             return port;
            }
            else {
                port = (int) (range * Rand.nextDouble()) + Minport;
                continue;

            }
             
        }
        return 0;
    }

    public static void main(String[] args) {

        PortGenerator xx = new PortGenerator();
        int x = -1;
        for (int i = 0; i < 100; i++) {
            x = xx.getFreePort();
            System.out.println(x + "\n");
            try {
                Thread.sleep(300);
            } catch (Exception y) {
                y.printStackTrace();
            }
        }


        // System.out.println(PortGenerator.getFreePort());

    }
}
