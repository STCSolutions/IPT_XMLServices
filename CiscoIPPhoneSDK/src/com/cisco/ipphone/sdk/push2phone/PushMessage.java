package com.cisco.ipphone.sdk.push2phone;

import java.awt.*;
import java.awt.image.*;
import javax.swing.ImageIcon;
import com.cisco.ipphone.sdk.*;
import java.io.*;

/**
 * Simple class used to create and cache the current PushMessage for the Push2Phone application.
 * This class uses a static attribute to store the current message so that it is
 * CLASS-scoped and accessible from all threads and, therefore, can be retrieved by all Phones
 * making a web request to Push2Phone to retrieve the message.
 * Since the class is static and will be accessed by multiple threads simultaneously, the methods
 * are synchronized to ensure thread-safety.
 * @author kstearns
 * @version 1.0
 */
public class PushMessage {

  private static String theXmlMessage = "";

  /**
   * This method accepts a text message, creates a graphic alert message from that String, and caches
   * the CiscoIPPhoneImage for subsequent retrieval by IP Phones.
   *
   * @param text the text message to be sent
   */
  public static synchronized void setMessageText(String text) {
    // Load the background image from the JAR file
    ImageIcon image = new ImageIcon(com.cisco.ipphone.sdk.push2phone.PushMessage.class.getResource("attention.gif"));
    // Create a new mutable image the same size as the background image
    BufferedImage bimage = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics g = bimage.getGraphics();
    // Draw the background image onto the mutable image
    g.drawImage(image.getImage(), 0, 0, null);
    g.setColor(Color.black);
    // Now loop thru each line in the text message and draw the text onto the mutable image
    int index = 0;
    int line = 0;
    int endIndex = text.indexOf("\n");
    while (endIndex != -1) {
      g.drawString(text.substring(index, endIndex),30,30+15*line++);
      index = endIndex+1;
      endIndex = text.indexOf("\n", index);
    }
    g.drawString(text.substring(index), 30, 30+15*line);
    // Now create a CIPImage object from the mutable image data
    CIPImage xmlImage = new CIPImage(bimage);
    xmlImage.setTitle("");
    xmlImage.setPrompt("");
    // Extract the CiscoIPPhoneImage XML from the CIPImage object and
    // set that as the current message
    theXmlMessage = xmlImage.saveCIPToBuffer();
  }

  /**
   * Retrieves the current Push2Phone message
   * @return a String containing an XML CiscoIPPhoneImage object
   */
  public static synchronized String getMessageXML() {
    // Simply return the current message XML
    return theXmlMessage;
  }

}