package com.cisco.ipphone.sdk;

import java.util.Vector;
import java.util.HashMap;
import java.util.Calendar;
import java.io.StringBufferInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class encapsulates the attributes and functions of a Cisco IP Phone.
 * The primary purpose of this class is to provide a convenient mechanism for finding relationships
 * between IP Phones and other system components such as Users, Extensions, and PCs.
 * Methods implemented in this class allow simple, efficient access to that information using service
 * Providers, such as an LDAP Directory Provider.
 * @author kstearns
 * @version 1.0 (March 2003)
 */
public class Phone {

  private static HashMap thePhones = new HashMap();
  private static DocumentBuilder docBuilder;

  private String deviceName;
  private SpeedDial[] speedDials = new SpeedDial[0];
  private Extension[] extensions = new Extension[0];

  private Phone(String deviceName) {
    this.deviceName = deviceName;
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
   * The getPhone method is used to obtain a reference to a Phone object which has the specified, unique
   * deviceName. Phone objects cannot be directly instantiated with a <code>new</code> command since there are no public constructors - instead a
   * reference is obtained to a given Phone by calling this method. This architecture has the added
   * benefit of being able to directly compare Phone objects for equality - since there is guaranteed to be
   * only one object in existence with a given, unique deviceName. This also has the effect of minimizing memory
   * utilization since only a single instance of a given Phone object exists within the JVM.
   * @param deviceName the unique deviceName of the requested Phone
   * @return the requested Phone object. If the Phone with this deviceName does not exist, it is created and returned.
   */
  public static synchronized Phone getPhone(String deviceName) {
    Phone phone = (Phone)thePhones.get(deviceName);
    if (phone == null) {
      phone = new Phone(deviceName);
      thePhones.put(deviceName, phone);
    }
    return phone;
  }

  /**
   * Equivalent to calling loadAttributes(ap, false)
   * @param ap the AXLProvider to be queried for configuration information
   * @return boolean value indicating whether or not the query was successful
   */
  public synchronized boolean loadAttributes(AXLProviderIF ap) {
    return loadAttributes(ap, false);
  }

  /**
   * The loadAttributes method queries the specified AXL Provider for Phone configuration info.
   * The method currently only loads the SpeedDial settings into the object, but it could be
   * easily extended to load other device configuration settings and make them available via
   * new getter methods (as was done for the SpeedDials).
   * If the optional loadExtensions flag is included and true, then AXL will also be queried for
   * detailed information about the Phone's line appearances and this information can be
   * subsequently retrieved by a call to getExtensions(). Since this creates multiple AXL queries,
   * this parameter should only be used when Extension information is needed.
   * @param ap the AXLProvider to be queried for configuration information
   * @param loadExtensions boolean value specifying whether or not the Phone's Extension information
   *  should also be loaded from AXL
   * @return boolean value indicating whether or not the query was successful
   */
  public synchronized boolean loadAttributes(AXLProviderIF ap, boolean loadExtensions) {
    String axlResult = ap.sendRequest("getPhone", "<phoneName>" + deviceName + "</phoneName>");
    StringBufferInputStream axlResultStream = new StringBufferInputStream(axlResult);
    Document axlResultDoc;

    try {
      axlResultDoc = docBuilder.parse(axlResultStream);
    }
    catch (Exception e) {
      System.out.println("Exception:" + e);
      return false;
    }

    //  Parse the document and load the speeddial elements
    NodeList speeddialList = axlResultDoc.getElementsByTagName("speeddial");
    speedDials = new SpeedDial[speeddialList.getLength()];
    for (int i = 0; i < speeddialList.getLength(); i++) {
      Element speeddial = (Element)speeddialList.item(i);
      int tempIndex = Integer.parseInt(speeddial.getAttribute("index"));
      Node dirnnode = speeddial.getFirstChild().getNextSibling();
      Node labelnode = dirnnode.getNextSibling().getNextSibling();
      SpeedDial sd = new SpeedDial(labelnode.getFirstChild().getNodeValue(), dirnnode.getFirstChild().getNodeValue(), tempIndex);
      speedDials[i] = sd;
    }

    if (loadExtensions) {
      // First, grab the Line UUIDs from the getPhone response
      NodeList lineList = axlResultDoc.getElementsByTagName("line");
      String[] lineIds = new String[lineList.getLength()];
      String[] tempLabels = new String[lineList.getLength()];
      String[] tempDisplays = new String[lineList.getLength()];

      for (int i = 0; i < lineList.getLength(); i++) {
        Element line = (Element)lineList.item(i);
        NodeList temp = line.getElementsByTagName("dirn");
        lineIds[i] = temp.item(0).getAttributes().getNamedItem("uuid").getNodeValue();
        temp = line.getElementsByTagName("label");
        tempLabels[i] = temp.item(0).hasChildNodes() ? temp.item(0).getFirstChild().getNodeValue() : "";
        temp = line.getElementsByTagName("display");
        tempDisplays[i] = temp.item(0).hasChildNodes() ? temp.item(0).getFirstChild().getNodeValue() : "";
      }
      // Now send an AXL query for each of those Lines and parse the result
      extensions = new Extension[lineIds.length];
      for (int i = 0; i < lineIds.length; i++) {
        axlResult = ap.sendRequest("getLine", "<uuid>" + lineIds[i] + "</uuid>");
        axlResultStream = new StringBufferInputStream(axlResult);
        try {
          axlResultDoc = docBuilder.parse(axlResultStream);
        }
        catch (Exception e) { break;}

        String pattern = "";
        NodeList temp = axlResultDoc.getElementsByTagName("pattern");
        if (temp != null) {
            pattern = temp.item(0).getFirstChild().getNodeValue();
        }

        // Get a reference to the Extension with this pattern
        // and populate the label and display
        Extension ext = Extension.getExtension(pattern);
        extensions[i] = ext;
        ext.label = tempLabels[i];
        ext.display = tempDisplays[i];

        // Get Message Waiting Indication status
        temp = axlResultDoc.getElementsByTagName("mwiStatus");
        if (temp != null) {
          Node status = temp.item(0).getFirstChild();
          if (status != null) {
            //  If the MWI status is On, then add this extension/user to the list
            ext.mwiStatus = status.getNodeValue();
          }
        }
      }
    }
    return true;
  }

  /**
   *
   * @return the String deviceName of the Phone
   */
  public String getDeviceName() {
    return deviceName;
  }

  /**
   *
   * @return the String deviceName of the Phone
   */
  public String toString() {
    return deviceName;
  }

  /**
   * This method returns an Array of SpeedDial objects which are currently configured
   * for this phone. The SpeedDial class is a simple structure which contains all relevant info
   * for a SpeedDial - index, label, and number.
   * The SpeedDials attributes MUST first be loaded by a call to loadAttributes prior to calling this method -
   * otherwise this method will simply return an empty Array.
   * @return the Array of SpeedDials. Returns an empty Array (length=0) if no SpeedDials are configured.
   * @see com.cisco.ipphone.sdk.SpeedDial
   */
  public synchronized SpeedDial[] getSpeedDials() {
    return speedDials;
  }

  /**
   * This method returns an Array of Extension objects which are currently configured
   * for this phone. The Extension class contains the key attributes of an extension: pattern (number),
   * label, display (internal CallerId display), and MWI status.
   * The Extension attributes MUST first be loaded by a call to loadAttributes(ap, true) prior to calling
   * this method - otherwise this method will simply return an empty Array.
   * @return the Array of Extensions. Returns an empty Array (length=0) if no Extensions are configured.
   * @see com.cisco.ipphone.sdk.Extension
   */
  public synchronized Extension[] getExtensions() {
    return extensions;
  }

  /**
   * Queries the specified LDAP Provider for a list of Users who are allowed to Control this device (phone).
   * @param lp the LDAPProvider
   * @param excludedUsers an Array of Strings containing UserIds which should NOT be returned in the
   * result. If excludedUsers is either <code>null</code> or an empty Array, all matching Users will be returned.
   * This easily filters out "application" UserIds which are Controlling groups of devices.
   * For example, many of the sample applications will
   * filter out the "sdkapps" userId which is associated with all devices for PUSH control and other purposes.
   * @return an Array of Users who are Controlling this Phone. If no Users were found or the query
   * fails, an empty Array (length=0) will be returned.
   */
  public User[] getControllingUsers(LDAPProviderIF lp, String[] excludedUsers) {
    HashMap matchRules = new HashMap();
    matchRules.put("ciscoCCNatControlDevices", this.deviceName);
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

  /**
   * Queries the specified Extension Mobility Provider to determine if this Phone
   * currently has an EM USer logged into it.
   * @param ep the EMProvider
   * @return the User currently logged into this Phone, or <code>null</code> if no User is currently logged in
   */
  public User getCurrentExtMobUser(EMProviderIF ep) {
    String xmlQuery = "<deviceUserQuery><deviceName>" + this.deviceName + "</deviceName></deviceUserQuery>";
    String xmlResponse = ep.sendRequest(EMProvider.QUERY, xmlQuery);

    if (xmlResponse.indexOf("<userID>") != -1)
      return User.getUser(xmlResponse.substring(xmlResponse.indexOf("<userID>") + 8, xmlResponse.indexOf("</userID>")));
    else
      return null;
  }

  /**
   * Uses the specified Extension Mobility Provider to log the specified User into this Phone
   * @param ep the EMProvider
   * @param loginUser the User to be logged into this Phone
   * @return boolean value indicated the success (true) or failure (false) of the login request.
   * Note that the request will fail if another User is currently logged into the Phone.
   */
  public boolean login(EMProviderIF ep, User loginUser) {
    String xmlQuery = "<login><deviceName>" + this.getDeviceName() + "</deviceName><userID>" + loginUser.getUserId() + "</userID></login>";
    String xmlResponse = ep.sendRequest(EMProvider.REQUEST, xmlQuery);
    if (xmlResponse != null) {
      if (xmlResponse.indexOf("success") != -1)
        return true;
    }
    return false;
  }

  /**
   * Uses the specified Extension Mobility Provider to logout the current EM User of this Phone
   * @param ep the EMProvider
   * @return boolean value indicating the success (true) or failure (false) of this request
   * Note that the request will fail if no User is currently logged into this Phone
   */
  public boolean logout(EMProviderIF ep) {
    String xmlQuery = "<logout><deviceName>" + deviceName + "</deviceName></logout>";
    String xmlResponse = ep.sendRequest(EMProvider.REQUEST, xmlQuery);
    if (xmlResponse != null) {
      if (xmlResponse.indexOf("success") != -1)
        return true;
    }
    return false;
  }

  public String getIPAddress(IPAddressProviderIF ipProv) {
    return ipProv.getIPAddress(this);
  }

  /**
   * This method PUSHes a command to the Phone. A PUSH to the phone is performed by HTTP POSTing
   * a CiscoIPPhoneExecute object.
   * @param uri the URI to be executed by the Phone
   * @param ipProv the IPAddressProvider to be used to obtain the IP address of this Phone in
   * order to perform the PUSH
   * @param pushUserId the UserId of a user with permissions to PUSH to this Phone
   * @param pushPassword the password for the above UserId
   * @param getResult indicates whether or not the HTTP redirect to the CiscoIPPhoneResponse
   * object should be followed. Unless the application requires verification of the PUSH success,
   * this parameter should be set to false, otherwise performance will be degraded.
   * @return the XML PUSH response returned by the Phone which indicates the success of PUSH
   */
  public String push(String uri, IPAddressProviderIF ipProv, String pushUserId, String pushPassword, boolean getResult) {
    String ipAddr = ipProv.getIPAddress(this);
    return this.push(uri, ipAddr, pushUserId, pushPassword, getResult);
  }

  /**
   * This method PUSHes commands to the Phone. A PUSH to the phone is performed by HTTP POSTing
   * a CiscoIPPhoneExecute object.
   * @param uris an Array of String URIs (maximum of 3) to be executed by the Phone
   * If an Array larger than 3 is passed, the method will fail and return <code>null</code>
   * @param ipProv the IPAddressProvider to be used to obtain the IP address of this Phone in
   * order to perform the PUSH
   * @param pushUserId the UserId of a user with permissions to PUSH to this Phone
   * @param pushPassword the password for the above UserId
   * @param getResult indicates whether or not the HTTP redirect to the CiscoIPPhoneResponse
   * object should be followed. Unless the application requires verification of the PUSH success,
   * this parameter should be set to false, otherwise performance will be degraded.
   * @return the XML PUSH response returned by the Phone which indicates the success of PUSH
   */
  public String push(String[] uris, IPAddressProviderIF ipProv, String pushUserId, String pushPassword, boolean getResult) {
    String ipAddr = ipProv.getIPAddress(this);
    return this.push(uris, ipAddr, pushUserId, pushPassword, getResult);
  }

  /**
   * This method PUSHes a command to the Phone. A PUSH to the phone is performed by HTTP POSTing
   * a CiscoIPPhoneExecute object.
   * This method takes the IP address of this phone as a parameter - this is generally
   * a better approach if an application is PUSHing to multiple Phones at the same time because a
   * single query can be made to the IPAddressProvider to get the entire list of IP addresses and
   * then the PUSH method can be called for each Phone - this avoids a call to the IPAddressProvider
   * for each Phone.
   * @param uri the URI to be executed by the Phone
   * @param phoneIP the IP address of this Phone
   * @param pushUserId the UserId of a user with permissions to PUSH to this Phone
   * @param pushPassword the password for the above UserId
   * @param getResult indicates whether or not the HTTP redirect to the CiscoIPPhoneResponse
   * object should be followed. Unless the application requires verification of the PUSH success,
   * this parameter should be set to false, otherwise performance will be degraded.
   * @return the XML PUSH response returned by the Phone which indicates the success of PUSH
   */
  public static String push(String uri, String phoneIP, String pushUserId, String pushPassword, boolean getResult) {
    String[] uris = { uri };
    return push( uris, phoneIP, pushUserId, pushPassword, getResult);
  }

  /**
   * This method PUSHes a command to the Phone. A PUSH to the phone is performed by HTTP POSTing
   * a CiscoIPPhoneExecute object.
   * This method takes the IP address of this phone as a parameter - this is generally
   * a better approach if an application is PUSHing to multiple Phones at the same time because a
   * single query can be made to the IPAddressProvider to get the entire list of IP addresses and
   * then the PUSH method can be called for each Phone - this avoids a call to the IPAddressProvider
   * for each Phone.
   * @param uris an Array of String URIs (maximum of 3) to be executed by the Phone.
   * If an Array larger than 3 is passed, the method will fail and return <code>null</code>
   * @param phoneIP the IP address of this Phone
   * @param pushUserId the UserId of a user with permissions to PUSH to this Phone
   * @param pushPassword the password for the above UserId
   * @param getResult indicates whether or not the HTTP redirect to the CiscoIPPhoneResponse
   * object should be followed. Unless the application requires verification of the PUSH success,
   * this parameter should be set to false, otherwise performance will be degraded.
   * @return the XML PUSH response returned by the Phone which indicates the success of PUSH.
   */
  public static String push(String[] uris, String phoneIP,
                                         String pushUserId, String pushPassword,
                                         boolean getResult) {
    if (uris.length > 3) {
     System.out.println("Phone.push() Invalid URI count:"+uris.length);
     return null;
    }
    if (phoneIP == null) {
      System.out.println("Phone.push() Null IP address");
      return null;
    }

    System.out.println("Phone.push() IP="+phoneIP+"appId="+pushUserId+" appPass="+pushPassword);
    String pushAuth = Text2Base64.getBase64(pushUserId + ":" + pushPassword);
    String pushXML = "<CiscoIPPhoneExecute>";
    for (int i = 0; i < uris.length; i++) {
      pushXML = pushXML + "<ExecuteItem Priority=\"0\" URL=\"" +
          uris[i] + "\"/>";
    }
    pushXML = pushXML + "</CiscoIPPhoneExecute>";

    StringBuffer response = new StringBuffer();
    try {
      String httpData = "XML=" + URLEncoder.encode(pushXML);
      System.out.println("xml="+httpData);
      URL url = new URL("http://" + phoneIP + "/CGI/Execute");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoInput(true);
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setFollowRedirects(getResult);
      conn.setRequestProperty("Content-type",
                              "application/x-www-form-urlencoded");
      conn.setRequestProperty("Authorization", "Basic " + pushAuth);
      PrintWriter pout = new PrintWriter(new OutputStreamWriter(conn.
          getOutputStream()), true);
      pout.print(httpData);
      pout.flush();
      InputStream is = conn.getInputStream();
      int c;
      do {
        char x;
        c = is.read();
        x = (char) c;
      }
      while (c != -1);
    }
    catch (Exception e) {
      System.err.println("Exception:" + e);
    }
    return response.toString();
  }
}
