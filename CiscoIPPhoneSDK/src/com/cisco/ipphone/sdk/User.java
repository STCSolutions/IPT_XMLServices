package com.cisco.ipphone.sdk;

import java.util.HashMap;
import java.util.Vector;
import java.io.StringBufferInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class encapsulates the attributes and functions of a Cisco IP Telephony User.
 * The primary purpose of this class is to provide a convenient mechanism for finding relationships
 * between IP Telephony Users and other system components such as Phones, Extensions, and PCs.
 * Methods implemented in this class allow simple, efficient access to that information using service
 * Providers, such as an LDAP Directory Provider.
 * @author kstearns
 * @version 1.0 (March 2003)
 */
public class User {
  String userId = null;
  String firstName = null;
  String lastName = null;
  String department = null;
  String manager = null;
  String telephoneNumber = null;
  String mail = null;

  private static DocumentBuilder docBuilder;
  private static HashMap theUsers = new HashMap();

  private User(String userId) {
    this.userId = userId;
    if (docBuilder == null) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try {
        docBuilder = factory.newDocumentBuilder();
      }
      catch (ParserConfigurationException pe) {
        System.out.println("ParserConfigurationException:" + pe);
      }
    }
  }

  /**
   * The getUser method is used to obtain a reference to a User object which has the specified, unique
   * userId. User objects cannot be directly instantiated with a <code>new</code> command since there are no public constructors - instead a
   * reference is obtained to a given User by calling this method. This architecture has the added
   * benefit of being able to directly compare User objects for equality - since there is guaranteed to be
   * only one object in existence with a given, unique userId. This also has the effect of minimizing memory
   * utilization since only a single instance of a given User object exists within the JVM.
   * @param userId the unique UserId of the requested User
   * @return the User object with the given UserId. If the User with this UserId does not exist, it is created and returned.
   */
  public static synchronized User getUser(String userId) {
    User user = (User)theUsers.get(userId);
    if (user == null) {
      user = new User(userId);
      theUsers.put(userId, user);
    }
    return user;
  }

  public static User[] searchUsers(String firstName, String lastName, String telephoneNumber, LDAPProviderIF lp) {
    String[] searchCtrls = {"cn", "sn", "mail", "givenName", "departmentNumber", "telephoneNumber", "manager"};
    if (firstName == null) firstName = "";
    if (lastName == null) lastName = "";
    if (telephoneNumber == null) telephoneNumber = "";
    String filter = "(&";
    if (firstName != null) {
      if (!firstName.equals("")) {
        filter = filter + "(givenName=" + firstName + "*)";
      }
    }
    if (lastName != null) {
      if (!lastName.equals("")) {
        filter = filter + "(sn=" + lastName + "*)";
      }
    }

    if (telephoneNumber != null) {
      if (!telephoneNumber.equals("")) {
        filter = filter + "(telephoneNumber=" + telephoneNumber + "*)";
      }
    }

    filter = filter +")";

    Vector answer = lp.sendRequest("ou=Users", filter, true, searchCtrls);
    if (answer != null) {
      User[] users = new User[answer.size()];
      for (int i = 0; i < answer.size(); i++) {
        HashMap attrMap = (HashMap) answer.get(i);
        Vector values = (Vector) attrMap.get("cn");
        User user = User.getUser((String)values.get(0));
        users[i] = user;

        values = (Vector) attrMap.get("givenName");
        if (values != null)
          user.firstName = (String)values.get(0);

        values = (Vector) attrMap.get("sn");
        if (values != null)
          user.lastName = (String)values.get(0);

        values = (Vector) attrMap.get("mail");
        if (values != null)
          user.mail = (String)values.get(0);

        values = (Vector) attrMap.get("department");
        if (values != null)
          user.department = (String)values.get(0);

        values = (Vector) attrMap.get("telephoneNumber");
        if (values != null)
          user.telephoneNumber = (String)values.get(0);

        values = (Vector) attrMap.get("manager");
        if (values != null)
          user.manager = (String)values.get(0);
      }
      return users;
    }
    else {
      return new User[0];
    }
  }

  /**
   * @return the String UserId of this User
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @return the String UserId of this User
   */
  public String toString() {
    return userId;
  }

  /**
   * Queries the specified LDAP Provider for a list of Phones controlled by this User
   * @param lp the LDAPProvider
   * @param beginsWith a String used to filter the Phones by matching it against the beginning of the deviceName. If beginsWith is <code>null</code> or an empty String, all Controlled Phones will be returned.
   * @return an Array of Phones controlled by this User. If no Phones were found or the query fails, an empty Array (length=0) will be returned.
   */
  public Phone[] getControlledPhones(LDAPProvider lp, String beginsWith) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoatProfileOwner", this.userId);
    String[] searchCtrls = {"ciscoCCNatControlDevices"};
    Vector answer = lp.sendRequest("ou=profiles, ou=CCN", matchRules, true, searchCtrls);
    Vector matchingPhones = new Vector();
    if (answer != null) {
      Vector phones = (Vector)((HashMap)answer.get(0)).get("ciscoCCNatControlDevices");
      if (phones != null) {
        for (int i = 0; i < phones.size(); i++) {
          if (beginsWith == null)
            beginsWith = "";
          String phone = (String) phones.get(i);
          if (phone.startsWith(beginsWith)) {
            matchingPhones.add(phone);
          }
        }
      }
    }
    Phone[] phoneArray = new Phone[matchingPhones.size()];
    for (int i = 0; i < matchingPhones.size(); i++) {
      boolean excluded = false;
      phoneArray[i] = Phone.getPhone((String)matchingPhones.get(i));
    }
    return phoneArray;
  }

  /**
   * Queries the specified LDAP Provider for the PC Associated to this User
   * @param lp the LDAPProvider
   * @return the associated PC. Returns <code>null</code> if this User does not have an Associated PC defined, or if the query fails.
   */
  public PC getAssociatedPC(LDAPProviderIF lp) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoatProfileOwner", this.userId);
    String[] searchCtrls = {
        "ciscoCCNatAssociatedPC"};
    Vector answer = lp.sendRequest("ou=profiles, ou=CCN", matchRules, true,
                                   searchCtrls);
    if (answer != null) {
      Vector pc = (Vector) ( (HashMap) answer.get(0)).get(
          "ciscoCCNatAssociatedPC");
      if (pc != null) {
        return PC.getPC((String)pc.get(0));
      }
    }
    return null;
  }

  public String getDefaultDeviceProfile(LDAPProviderIF lp) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoatProfileOwner", this.userId);
    String[] searchCtrls = {
        "ciscoCCNatDefaultDeviceProfile"};
    Vector answer = lp.sendRequest("ou=profiles, ou=CCN", matchRules, true,
                                   searchCtrls);
    if (answer != null) {
      Vector dp = (Vector) ( (HashMap) answer.get(0)).get(
          "ciscoCCNatDefaultDeviceProfile");
      if (dp != null) {
        return (String)dp.get(0);
      }
    }
    return null;
  }

  public String[] getDeviceProfiles(LDAPProviderIF lp) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoatProfileOwner", this.userId);
    String[] searchCtrls = {
      "ciscoCCNatDefaultDeviceProfile"};
    Vector answer = lp.sendRequest("ou=profiles, ou=CCN", matchRules, true, searchCtrls);
    String[] dps = new String[0];
    if (answer != null) {
      Vector dp = (Vector) ( (HashMap) answer.get(0)).get(
          "ciscoCCNatDefaultDeviceProfile");
      if (dp != null) {
        dps = new String[dp.size()];
        for (int i=0; i < dp.size(); i++) {
          dps[i] = (String)dp.get(i);
        }
      }
    }
    return dps;
  }

  /**
   * Queries the specified LDAP Provider for the Primary Extension of this User
   * @param lp the LDAPProvider
   * @return the Primary Extension for this used. Returns <code>null</code> if this User does not have a Primary Extension defined or if the query fails.
   */
  public Extension getPrimaryExtension(LDAPProviderIF lp) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoatProfileOwner", this.userId);
    String[] searchCtrls = {
        "ciscoCCNatExtensions"};
    Vector answer = lp.sendRequest("ou=profiles, ou=CCN", matchRules, true,
                                   searchCtrls);
    if (answer != null) {
      Vector ext = (Vector) ( (HashMap) answer.get(0)).get(
          "ciscoCCNatExtensions");
      if (ext != null) {
        return Extension.getExtension((String)ext.get(0));
      }
    }
    return null;
  }

  /**
   * Queries the specified Extension Mobility Provider for the Phone that this User is currently logged into
   * @param ep the EMProvider
   * @return the login Phone. Returns <code>null</code> if the User is not currently logged in via Extension Mobility or if the query fails
   */
  public Phone getCurrentExtMobPhone(EMProvider ep) {
    String xmlQuery = "<userDevicesQuery><userID>" + this.userId + "</userID></userDevicesQuery>";
    String xmlResponse = ep.sendRequest(EMProvider.QUERY, xmlQuery);
    StringBufferInputStream xmlResponseStream = new StringBufferInputStream(xmlResponse);
    Document xmlDoc;

    try {
      xmlDoc = docBuilder.parse(xmlResponseStream);
    }
    catch (Exception e) {
      System.out.println("Exception:" + e);
      return null;
    }

    //  Parse the document and load the device elements
    NodeList phoneList = xmlDoc.getElementsByTagName("deviceName");
    if (phoneList.getLength() != 0) {
      Element phone = (Element) phoneList.item(0);
      return Phone.getPhone(phone.getFirstChild().getNodeValue());
    }
    else {
      return null;
    }
  }

  /**
   * This method queries the specified LDAP Provider for all of the basic attributes of this User.
   * These attributes include first name, last name, telephone number (not the same as Primary Extension), department, and mail.
   * This method MUST be called prior to calling the corresponding <code>get</code> methods for those attributes - otherwise they will simply return <code>null</code>.
   * @param lp the LDAPProvider
   * @return boolean value indicating whether or not the query was successful. Successful = true, failed = false.
   */
  public synchronized boolean loadAttributes(LDAPProvider lp) {
    firstName = null;
    lastName = null;
    mail = null;
    department = null;
    manager = null;
    telephoneNumber = null;

    HashMap matchRules = new HashMap();
    String[] searchCtrls = {"givenName", "sn", "mail", "departmentNumber", "telephoneNumber", "manager"};
    matchRules.put("cn", this.userId);
    Vector answer = lp.sendRequest("ou=Users", matchRules, true, searchCtrls);
    if (answer != null) {
      HashMap attrMap = (HashMap)answer.get(0);
      Vector values = (Vector)attrMap.get("givenName");
      if (values != null)
        this.firstName = (String)values.get(0);
      values = (Vector)attrMap.get("sn");
      if (values != null)
        this.lastName = (String)values.get(0);
      values = (Vector)attrMap.get("mail");
      if (values != null)
        this.mail = (String)values.get(0);
      values = (Vector)attrMap.get("departmentNumber");
      if (values != null)
        this.department = (String)values.get(0);
      values = (Vector)attrMap.get("telephoneNumber");
      if (values != null)
        this.telephoneNumber = (String)values.get(0);
      values = (Vector)attrMap.get("manager");
      if (values != null) {
        // Parse the userId out of the LDAP path string
        manager = (String) values.get(0);
        manager = manager.substring(manager.indexOf("cn=") + 3);
        manager = manager.substring(0, manager.indexOf(","));
      }
      return true;
    }
    return false;
  }

  /**
   * Returns the First Name of this User
   * The <code>loadAtributes</code> method MUST be called first to populate the User information or this method will always return <code>null</code>.
   * @return a String containing the First Name of the User (LDAP attribute: givenName), or <code>null</code> if it does not exist.
   */
  public synchronized String getFirstName()
  {
    return firstName;
  }

  /**
   * Returns the Last Name of this User
   * The <code>loadAtributes</code> method MUST be called first to populate the User information or this method will always return <code>null</code>.
   * @return a String containing the Last Name of the User (LDAP attribute: sn), or <code>null</code> if it does not exist.
   */
  public synchronized String getLastName()
  {
    return lastName;
  }

  /**
   * Returns this User's Department
   * The <code>loadAtributes</code> method MUST be called first to populate the User information or this method will always return <code>null</code>.
   * @return a String containing the Department of the User (LDAP attribute: departmentNumber), or <code>null</code> if it does not exist.
   */
  public synchronized String getDepartment()
  {
    return department;
  }

  /**
   * Returns this User's Department
   * The <code>loadAtributes</code> method MUST be called first to populate the User information or this method will always return <code>null</code>.
   * @return a String containing the Department of the User (LDAP attribute: departmentNumber), or <code>null</code> if it does not exist.
   */
  public synchronized String getManager()
  {
    return manager;
  }

  /**
   * Returns this User's Mail address
   * The <code>loadAtributes</code> method MUST be called first to populate the User information or this method will always return <code>null</code>.
   * @return a String containing the Mail address of the User (LDAP attribute: mail), or <code>null</code> if it does not exist.
   */
  public synchronized String getMail()
  {
    return mail;
  }

  /**
   * Returns this User's Telephone Number
   * <p>NOTE: This is NOT the same as a User's Primary Extension !!!</p>
   * The Primary Extension is a Cisco-specific attribute stored in the User's CCN profile (ou=profiles, ou=CCN) which
   * is tied directly to the User's Controlled Phone and tightly integrated with the CallManager database.
   * This Telephone Number is a standard User attribute (ou=Users) and not linkedb to the CallManager database.
   * The <code>loadAtributes</code> method MUST be called first to populate the User information or this method will always return <code>null</code>.
   * @return a String containing the Telephone Number of the User (LDAP attribute: telephoneNumber), or <code>null</code> if it does not exist.
   */
  public synchronized String getTelephoneNumber()
  {
    return telephoneNumber;
  }

  /**
   * Logs this User into the specified Phone using the given Extension Mobility Provider
   * @param ep the EMProvider
   * @param loginPhone the Phone to be used by this User
   * @return boolean return value indicates whether or not the login was successful
   */
  public boolean login(EMProvider ep, Phone loginPhone) {
    return loginPhone.login(ep, this);
  }

}
