package com.cisco.ipphone.sdk;

import java.util.Vector;
import java.util.HashMap;

/**
 * This class encapsulates the attributes and functions of a Cisco IP Telephony Personal Computer.
 * The primary purpose of this class is to provide a convenient mechanism for finding relationships
 * between IP Telephony Users and other system components such as Phones, Extensions, and PCs.
 * Methods implemented in this class allow simple, efficient access to that information using service
 * Providers, such as an LDAP Directory Provider.
 * @author kstearns
 * @version 1.0 (March 2003)
 */
public class PC {

  private static HashMap thePCs = new HashMap();
  private String id;

  private PC(String id) {
    this.id = id;
  }

  /**
   * The getPC method is used to obtain a reference to a PC object which has the specified, unique
   * id. PC objects cannot be directly instantiated with a <code>new</code> command since there are no public constructors - instead a
   * reference is obtained to a given PC by calling this method. This architecture has the added
   * benefit of being able to directly compare PC objects for equality - since there is guaranteed to be
   * only one object in existence with a given, unique id. This also has the effect of minimizing memory
   * utilization since only a single instance of a given PC object exists within the JVM.
   * @param id the unique id (IP address or hostname) of the requested PC
   * @return the PC object with the given id. If the PC with this id does not exist, it is created and returned.
   */
  public static synchronized PC getPC(String id) {
    PC pc = (PC)thePCs.get(id);
    if (pc == null) {
      pc = new PC(id);
      thePCs.put(id, pc);
    }
    return pc;
  }

  /**
   *
   * @return the unique ID of this PC
   */
  public String getId() {
    return id;
  }

  /**
   * Queries the specified LDAP Provider for a list of Users who have this PC defined as their Associated PC
   * @param lp the LDAPProvider
   * @param excludedUsers an Array of Strings containing UserIds which should NOT be returned in the
   * result. If excludedUsers is either <code>null</code> or an empty Array, all matching Users will be returned.
   * Typically there would only be one User returned, but
   * in shared-PC environemnts, there could obviously be multiple. If no Users were found or the query
   * fails, an empty Array (length=0) will be returned.
   */
  public User[] getAssociatedUsers(LDAPProvider lp, String[] excludedUsers) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoCCNatAssociatedPC", this.id);
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
