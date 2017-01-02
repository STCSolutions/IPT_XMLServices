package com.cisco.ipphone.sdk;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class XmlEncoder {

  private static final String[] reservedChars = { "&", "<", ">", "\"" }; // & must execute first
  private static final String[] escapeSequences = { "&amp;", "&lt;", "&gt;", "&quote" };

  private XmlEncoder() {}

  public static final String encode(String text) {
    StringBuffer buff = new StringBuffer(text);
    for (int i=0; i < reservedChars.length; i++) {
      replace(reservedChars[i], escapeSequences[i], buff);
    }
    return buff.toString();
  }

  private static void replace(String theChar, String theSequence, StringBuffer buff) {
    int i=0;
    while ((i = buff.indexOf(theChar, i)) != -1) {
      buff.replace(i, i+1, theSequence);
      i++;
    }
  }

}
