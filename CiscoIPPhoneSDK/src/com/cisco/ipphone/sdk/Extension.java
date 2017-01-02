package com.cisco.ipphone.sdk;

import java.util.HashMap;
import java.util.Vector;

public class Extension {

  private static HashMap theExtensions = new HashMap();
  private String pattern = "";
  String label = "";
  String display = "";
  String mwiStatus = "";

  private Extension(String pattern) {
    this.pattern = pattern;
  }

  public synchronized static Extension getExtension(String pattern) {
    Extension ext = (Extension)theExtensions.get(pattern);
    if (ext == null) {
      ext = new Extension(pattern);
      theExtensions.put(pattern, ext);
    }
    return ext;
  }

  public synchronized String getPattern() {
    return pattern;
  }

  public synchronized String getLabel() {
    return label;
  }

  public synchronized String getDisplay() {
    return display;
  }

  public synchronized String getMwiStatus() {
    return mwiStatus;
  }

  public User[] getPrimaryUsers(LDAPProviderIF lp, String[] excludedUsers) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoCCNatExtensions", this.pattern);
    String[] searchCtrls = {"ciscoatProfileOwner"};
    Vector answer = lp.sendRequest("ou=profiles, ou=CCN", matchRules, true, searchCtrls);
    Vector userIds = new Vector();
    for (int i=0; i < answer.size(); i++) {
      HashMap userProfile = ((HashMap)answer.get(i));
      String userId = (String)((Vector)userProfile.get("ciscoatProfileOwner")).get(0);
      boolean excluded = false;
      if (excludedUsers != null) {
        for (int j=0; j < excludedUsers.length; j++) {
          if (excludedUsers[j].equals(userId))
            excluded = true;
        }
      }
      if (!excluded)
        userIds.add(userId);
    }

    User[] users = new User[userIds.size()];
    for (int i=0; i < userIds.size(); i++) {
      boolean excluded = false;
      users[i] = User.getUser((String)userIds.get(i));
    }
    return users;
  }


}
