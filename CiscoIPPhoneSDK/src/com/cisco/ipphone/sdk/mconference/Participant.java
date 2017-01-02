package com.cisco.ipphone.sdk.mconference;

/**
 * Simple class used to encapsulate the attributes of Conference Participant
 * @author kstearns
 * @version 1.0
 */
public class Participant {
  public String name;
  public String ipAddress;

  public Participant(String name, String ipAddress) {
    this.name = name;
    this.ipAddress = ipAddress;
  }
}
