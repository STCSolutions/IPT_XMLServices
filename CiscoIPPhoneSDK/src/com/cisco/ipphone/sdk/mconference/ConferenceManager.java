package com.cisco.ipphone.sdk.mconference;

import java.util.Vector;

/**
 * The ConferenceManager class controls all of state of the Conferences for the MConference
 * application.
 * ConferenceManager has static attributes which maintain a list of current Conferences and
 * track the allocation of IP multicast addresses for those Conferences.
 * ConferenceManager is a singleton class - which means there is only one instance of
 * a ConferenceManager object in the entire JVM. This ensures a single point of management for all
 * Conferences which is used by all threads (i.e., all web requests).
 * @author kstearns
 * @version 1.0
 */
public class ConferenceManager {
  private static final int MAX_CONFERENCES = 256;
  private static ConferenceManager theManager;
  private static Vector conferences = new Vector();
  private static int nextId = 0;
  private static boolean[] ipmcAddressInUse = new boolean[MAX_CONFERENCES];

  private ConferenceManager() {
  }

  /**
   * This method returns a reference to the one and only ConferenceManager.
   * Notice there are no public constructors for the ConferenceManager class and
   * this is the only method to obtain a reference to the ConferenceManager.
   * @return the ConferenceManager
   */
  public static synchronized ConferenceManager getConferenceManager() {
    if (theManager == null) {
      theManager = new ConferenceManager();
    }
    return theManager;
  }

  /**
   * Creates a new Conference with specified attributes
   * @param name the name of the Conference
   * @param owner the name of the owner of the Conference
   * @param ownerIP the IP address of the Conference Owner's phone
   * @return boolean value indicates whether the Conference was successfully created (true) or not (false)
   */
  public synchronized Conference createConference(String name, String owner, String ownerIP) {
    int i;
    for (i=0; i < MAX_CONFERENCES; i++) {
      if (!ipmcAddressInUse[i]) break;
    }
    if (i < MAX_CONFERENCES) {
      ipmcAddressInUse[i] = true;
      Conference newConf = new Conference(nextId++, name, i, owner, ownerIP);
      conferences.add(newConf);
      return newConf;
    }
    else return null;
  }

  /**
   * Deletes the Conference with the specified Conference ID
   * @param id the Conference ID
   * @return a reference to the deleted Conference or <code>null</code> if the specified Id is invalid
   */
  public synchronized Conference deleteConference(int id) {
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference)conferences.get(i);
      if (c.id == id) {
        conferences.remove(c);
        return c;
      }
    }
    return null;
  }

  /**
   * Returns a reference to the Conference identified by the specified Id
   * @param id
   * @return the Conference or <code>null</code> if the Id is invalid
   */
  public synchronized Conference getConference(int id) {
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference)conferences.get(i);
      if (c.id == id) {
        return c;
      }
    }
    return null;
  }

  /**
   * Returns an Array of all currently defined Conferences
   * @return the Conferences
   */
  public synchronized Conference[] getConferences() {
    Conference[] c = new Conference[conferences.size()];
    for (int i=0; i < conferences.size(); i++) {
      c[i] = (Conference)conferences.get(i);
    }
    return c;
  }

  /**
   * Adds a Request to Talk to the specified Conference
   * @param conferenceId
   * @param p
   * @return true if successful, false if the Conference Id is invalid
   */
  public synchronized boolean addTalkRequest(int conferenceId, Participant p) {
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference) conferences.get(i);
      if (c.id == conferenceId) {
        c.talkRequests.add(p);
        return true;
      }
    }
    return false;
  }

  /**
   * Removes all Requests to Talk for the specified Participant's IP address
   * @param conferenceId the Conference Id
   * @param ipAddress the IP address of the Participant's Phone
   */
  public synchronized void removeTalkRequest(int conferenceId, String ipAddress) {
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference) conferences.get(i);
      if (c.id == conferenceId) {
        int j=0;
        while (j < c.talkRequests.size()) {
          Participant temp = (Participant)c.talkRequests.get(j);
          if (temp.ipAddress.equals(ipAddress))
            c.talkRequests.remove(temp);
          else j++;
        }
      }
    }
    return;
  }

  /**
   * Returns an Array of all Particpants currently Requesting to Talk
   * @param conferenceId
   * @return the Participants requesting to Talk
   */
  public synchronized Participant[] getTalkRequests(int conferenceId) {
    Participant[] p = new Participant[0];
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference) conferences.get(i);
      if (c.id == conferenceId) {
        p = new Participant[c.talkRequests.size()];
        for (int j=0; j < c.talkRequests.size(); j++) {
          p[j] = (Participant) c.talkRequests.get(j);
        }
      }
    }
    return p;
  }

  /**
   * Adds the specified Particpant as a Talker in this Conference
   * @param conferenceId the Conference Id
   * @param p the Particpant
   * @return true if the Talker was added, false if the Conference Id is invalid
   */
  public synchronized boolean addTalker(int conferenceId, Participant p) {
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference) conferences.get(i);
      if (c.id == conferenceId) {
        c.talkers.add(p);
        return true;
      }
    }
    return false;
  }

  /**
   * Removes the specified Participant from the list of Talkers for this Conference
   * @param conferenceId the Conference Id
   * @param ipAddress the IP
   */
  public synchronized void removeTalker(int conferenceId, String ipAddress) {
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference) conferences.get(i);
      if (c.id == conferenceId) {
        int j=0;
        while (j < c.talkers.size()) {
          Participant temp = (Participant)c.talkers.get(j);
          if (temp.ipAddress.equals(ipAddress))
            c.talkers.remove(temp);
          else j++;
        }
      }
    }
    return;
  }

  public synchronized Participant[] getTalkers(int conferenceId) {
    Participant[] p = new Participant[0];
    for (int i=0; i < conferences.size(); i++) {
      Conference c = (Conference) conferences.get(i);
      if (c.id == conferenceId) {
        p = new Participant[c.talkers.size()];
        for (int j=0; j < c.talkers.size(); j++) {
          p[j] = (Participant) c.talkers.get(j);
        }
      }
    }
    return p;
  }

  /**
   * Conference is a simple inner class which holds all of the state information for a conference
   */
  public class Conference {
    private String name, owner, ownerIP;
    private int id, ipmcAddr;
    private Vector talkRequests = new Vector();
    private Vector talkers = new Vector();
    public boolean arbitrated;

    private Conference(int id, String name, int ipmcAddr, String owner, String ownerIP) {
      this.id = id;
      this.name = name;
      this.ipmcAddr = ipmcAddr;
      this.owner = owner;
      this.ownerIP = ownerIP;
    }

    public int getId() {
      return id;
    }
    public int getIpmcAddr() {
      return ipmcAddr;
    }
    public String getName() {
      return name;
    }
    public String getOwner() {
      return owner;
    }
    public String getOwnerIP() {
      return ownerIP;
    }
  }
}