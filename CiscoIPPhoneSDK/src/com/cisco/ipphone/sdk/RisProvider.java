package com.cisco.ipphone.sdk;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The <code>RisProvider</code> class provides a simple Java "wrapper"  around the CUCM RIS realtime API. 
 * This implementation of the <code>IPAddressProviderIF</code> interface caches
 * IP address information and automatically refreshes the information at the specified interval.
 * The initial loading of IP addresses from the DeviceListX API is performed by the calling thread
 * of the RisProvider constructor - this ensures that the provider is ready for requests as
 * soon as the constructor method returns. Subsequent updates are handled automatically by the
 * RisProvider class using a Timer thread. The refresh logic is designed so that the calling
 * thread will always return immediately - it will never block while waiting for the device list to be
 * updated (except for the initial load as stated above).
 * 
 * <br>Note: This class is for sample application purposes only - a fully-functional production application 
 * would make more efficient use of the RIS API capabilities, as needed by the application.
 *
 * @author  kstearns
 * @version 1.0  (Oct 2009)
 * @see com.cisco.ipphone.sdk.IPAddressProviderIF
 */
public class RisProvider implements IPAddressProviderIF {

  static {
    // Make sure the HTTPS subsystem is setup, in case we need it
    HttpsInitializer.init();
  }

  public static final int DEFAULT_INTERVAL = 3600;  // in seconds (one hour)
  public static final int MAX_DEVICES = 100;
  
  private static final String SOAP_REQUEST = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" " +
            "xmlns:tns=\"http://schemas.cisco.com/ast/soap/\" "+
            "xmlns:types=\"http://schemas.cisco.com/ast/soap/encodedTypes\" "+
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "+
            "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
            "<soap:Header><tns:AstHeader id=\"id1\"><SessionId xsi:type=\"xsd:string\">123456</SessionId></tns:AstHeader></soap:Header>" +
            "<soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"+
            "<tns:SelectCmDevice><CmSelectionCriteria href=\"#id1\"/></tns:SelectCmDevice>"+
            "<tns:CmSelectionCriteria id=\"id1\" xsi:type=\"tns:CmSelectionCriteria\">"+
            "<MaxReturnedDevices xsi:type=\"xsd:unsignedInt\">"+MAX_DEVICES+"</MaxReturnedDevices>"+
            "<Class xsi:type=\"tns:DeviceClass\">Phone</Class>"+
            "<Status xsi:type=\"tns:CmDevRegStat\">Registered</Status>"+
            "<NodeName xsi:type=\"xsd:string\"/>"+
            "<SelectBy xsi:type=\"tns:CmSelectBy\">Name</SelectBy>"+
            "</tns:CmSelectionCriteria></soap:Body></soap:Envelope>";

  static HashMap providers = new HashMap(); // holds references to all address maps
  static Timer refreshTimer = new Timer();

  static HostnameVerifier ver = new HostnameVerifier() {
    public boolean verify(String urlHostname, SSLSession session) {
      // We don't care if the hostname doesn't match the cert, just return true
      return true;
    }
  };

  private ProviderImpl impl;

  /**
   * Equivalent to calling:
   *
   * IPAddressProvider(callManager, callManagerUserId, callManagerPassword, false, IPAddressProvider.DEFAULT_INTERVAL);
   *
   * @param callManager String
   * @param callManagerUserId String
   * @param callManagerPassword String
   */
  public RisProvider(String callManager, String callManagerUserId, String callManagerPassword) {
    this(callManager, callManagerUserId, callManagerPassword, true, DEFAULT_INTERVAL);
  }

  /**
   *
   * Equivalent to calling:
   *
   * IPAddressProvider(callManager, callManagerUserId, callManagerPassword, useHttps, IPAddressProvider.DEFAULT_INTERVAL);
   *
   * @param callManager String
   * @param callManagerUserId String
   * @param callManagerPassword String
   * @param useHttps boolean
   */
  public RisProvider(String callManager, String callManagerUserId, String callManagerPassword, boolean useHttps) {
    this(callManager, callManagerUserId, callManagerPassword, useHttps, DEFAULT_INTERVAL);
  }

  /**
   * Creates a new RisProvider and immediately loads the IP address table
   * @param callManager String Hostname or IP address of the CallManager server hosting the DeviceListX API
   * @param callManagerUserId String  The UserId used to authenticate to the CallManager for CCMAdmin access
   * @param callManagerPassword String  The password of the above UserId
   * @param useHttps boolean If 'true' then https is used rather than http (Note: https is required for CallManager 4.1)
   * @param refreshInterval int Interval in seconds to refresh the IP address data
   */
  public RisProvider(String callManager, String callManagerUserId, String callManagerPassword, boolean useHttps, int refreshInterval) {
    synchronized(providers) {
      if (providers.containsKey(callManager)) {
        this.impl = (ProviderImpl) providers.get(callManager);
        // Go ahead and update these values in case they change while the VM is running - mainly for testing
        impl.callManagerUserId = callManagerUserId;
        impl.callManagerPassword = callManagerPassword;
        impl.useHttps = useHttps;
      }
      else {
        this.impl = new ProviderImpl(callManager, callManagerUserId,
                                     callManagerPassword, useHttps,
                                     refreshInterval);
        providers.put(callManager, impl);
      }
    }
  }


  /**
  * This method does the inverse of the other methods - it accepts an IP address as a parameter
  * and searches for the Phone currently using that address.
  * @param ipAddress a String containing the current IP address of the requested Phone
  * @return the Phone currently using the specified IP address or <code>null</code> if the address could not be found
  */
  public Phone getPhone(String ipAddress) {
    return impl.getPhone(ipAddress);
  }

  /**
  * This method retrieves the current active IP address of the specified Phone.
  * @param phone the Phone for which an IP address is requested
  * @return a String containing the IP address of the Phone in dotted-decimal format. If the Phone is not found, <code>null</code> will be returned.
  */
  public String getIPAddress(Phone phone) {
    return impl.getIPAddress(phone);
  }

  /**
  * This method retrieves the current active IP addresses of the specified Phones.
  * @param phones an Array of Phones for which IP addresses are requested
  * @return an Array of Strings containing the IP addresses of the requested Phones in dotted-decimal format. The returned array will always be the same length as the phones[] supplied as an argument. For any phones not found, the corresponding array element will contain <code>null</code>.
  */
  public String[] getIPAddresses(Phone[] phones) {
    return impl.getIPAddresses(phones);
  }

  /**
  * This method retrieves all of the current active IP addresses of devices.
  * @return a HashMap which contains all of the deviceNames (the HashMap keys) and their IP addresses (the HashMap values)
  */
  public HashMap getAllIPAddresses() {
    return impl.getAllIPAddresses();
  }

  public static void main(String[] args) {
    if (args.length != 6) {
      System.out.println("Arguments:");
      System.out.println("callManager callManagerUserId callManagerPassword useHttps refreshInterval queryInterval");
      System.exit(0);
    }
    final RisProvider prov = new RisProvider(args[0], args[1], args[2], Boolean.valueOf(args[3]).booleanValue(), Integer.parseInt(args[4]));
    int queryInterval = Integer.parseInt(args[5]) * 1000;
    TimerTask tt = new TimerTask() {
      public void run() {
        try {
          HashMap map = prov.getAllIPAddresses();
          Object[] keys = map.keySet().toArray();
          System.out.println("\r\nIP Address Table:\r\n=================================");
          for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i] + "=" + map.get(keys[i]));
          }
        }
        catch (Throwable t) {
          t.printStackTrace();
        }
      }
    };
    new Timer().schedule(tt, queryInterval, queryInterval);
  }

  private class ProviderImpl {
    private HashMap name2IpMap = new HashMap();
    private HashMap ip2NameMap = new HashMap();
    private DocumentBuilder docBuilder;
    private String callManager, callManagerUserId, callManagerPassword;
    private boolean useHttps;

    /**
     * This is the only constructor provided for the <code>RisProvider</code> class and
     * all parameters must be supplied. The parameters can be modified after instantiation, if
     * needed, by direct access to the public attributes.
     * @param callManager The IP address or hostname of the CallManager server hosting DeviceListX API
     * @param callManagerUserId The UserId used to authenticate to the CallManager for CCMAdmin access
     * @param callManagerPassword The password of the above UserId
     */
    ProviderImpl(String callManager, String callManagerUserId, String callManagerPassword, boolean useHttps, int refreshInterval) {
      this.callManager = callManager;
      this.callManagerPassword = callManagerPassword;
      this.callManagerUserId = callManagerUserId;
      this.useHttps = useHttps;

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try {
        docBuilder = factory.newDocumentBuilder();
      }
      catch (ParserConfigurationException pe) {
        System.out.println("ParserConfigurationException:" + pe);
      }

      // Connect and load the initial IP address table using the calling thread -
      // this ensures that the IP address table is populated as soon as the cstr returns
      // Subsequent refreshes are handled automatically by the Timer thread.
      new RefreshTask().run();

      // Schedule the Timer to repeatedly call the RefreshTask at the given interval
      refreshTimer.schedule(new RefreshTask(), refreshInterval*1000, refreshInterval*1000);

    }

    /**
    * This method does the inverse of the other methods - it accepts an IP address as a parameter
    * and searches for the Phone currently using that address.
    * @param ipAddress a String containing the current IP address of the requested Phone
    * @return the Phone currently using the specified IP address or <code>null</code> if the address could not be found
    */
    synchronized Phone getPhone(String ipAddress) {
      if (ip2NameMap != null) {
          return Phone.getPhone((String)ip2NameMap.get(ipAddress));
      }
      return null;
    }

    /**
    * This method retrieves the current active IP address of the specified Phone.
    * @param phone the Phone for which an IP address is requested
    * @return a String containing the IP address of the Phone in dotted-decimal format. If the Phone is not found, <code>null</code> will be returned.
    */
    synchronized String getIPAddress(Phone phone) {
      if (name2IpMap != null) {
        return (String)name2IpMap.get(phone.getDeviceName());
      }
      return null;
    }

    /**
    * This method retrieves the current active IP addresses of the specified Phones.
    * @param phones an Array of Phones for which IP addresses are requested
    * @return an Array of Strings containing the IP addresses of the requested Phones in dotted-decimal format. The returned array will always be the same length as the phones[] supplied as an argument. For any phones not found, the corresponding array element will contain <code>null</code>.
    */
    synchronized String[] getIPAddresses(Phone[] phones) {
      String[] addresses = new String[phones.length];
        for (int i=0; i < phones.length; i++) {
          addresses[i] = getIPAddress(phones[i]);
        }
      return addresses;
    }

    /**
    * This method retrieves all of the current active IP addresses of devices.
    * @return a HashMap which contains all of the deviceNames (the HashMap keys) and their IP addresses (the HashMap values)
    */
    synchronized HashMap getAllIPAddresses() {
      return (HashMap)name2IpMap.clone();
    }

    class RefreshTask extends TimerTask {
      public void run() {
        // Rather than reusing the old HashMaps, we create new ones - this lets
        // us postpone the synchronization until we're done retrieving and parsing
        // the device list. This keeps the calling threads from blocking on sync
        // while the Timer thread gets the address map updated.
        // The penalty is that we temporarily have a duplicate copy of the address map,
        // but that isn't be too much of a memory hit.
        Document xmlDoc = getDeviceList();
        if (xmlDoc != null) {
          HashMap newIp2NameMap = new HashMap();
          HashMap newName2IpMap = new HashMap();
          NodeList cmDevicesList = xmlDoc.getElementsByTagName("CmDevices");
          //System.out.println("### Number of CmDevices:"+cmDevicesList.getLength());
          
          //NodeList phoneList = xmlDoc.getElementsByTagName("Device");
          for (int i = 0; i < cmDevicesList.getLength(); i++) {
            Element cmDevices = (Element) cmDevicesList.item(i);
            NodeList deviceList = cmDevices.getElementsByTagName("item");
            //System.out.println("### Number of items:"+deviceList.getLength());
            for (int j = 0; j <deviceList.getLength(); j++) {
                String name=null, ipAddress=null;
                Element device = (Element)deviceList.item(j);
                NodeList attrs = device.getChildNodes();
                for (int k=0; k < attrs.getLength(); k++) {
                    String tagName = attrs.item(k).getNodeName();
                    //System.out.println("### getNodeName="+tagName);
                    if (tagName.equals("Name")) {
                        name = attrs.item(k).getTextContent();
                       
                    }
                    else if (tagName.equals("IPAddress")) {
                        ipAddress = attrs.item(k).getFirstChild().getFirstChild().getTextContent();
                    }
                }
                if (name != null && ipAddress != null) {  // safety check
                                    System.out.println("ADDING DEVICE: name="+name+" ip="+ipAddress);
                    newName2IpMap.put(name, ipAddress);
                    newIp2NameMap.put(ipAddress, name);
                }

            }
          }
          
          // We must synchronize here to ensure that no other threads are accessing
          // the address map when we swap it out
          synchronized(ProviderImpl.this) {
            name2IpMap.clear(); // clear all entries from the old maps
            ip2NameMap.clear();
            name2IpMap = newName2IpMap; // change our reference to point to the new maps
            ip2NameMap = newIp2NameMap;
          }
        }
      }

      Document getDeviceList() {
        // This method is called to request the XML device list
        String xmlResponse = null;
        Document xmlDoc = null;
        boolean success = true;
        // Try sending the request
        try {
          xmlResponse = sendRequest();
          StringBufferInputStream xmlStream = new StringBufferInputStream(
              xmlResponse);
          System.out.println(xmlResponse);
          xmlDoc = docBuilder.parse(xmlStream);
        }
        catch (Exception e) {
          e.printStackTrace();
          success = false;
        }
        // If it did not succeeed, log the error to the console and return null
        if (!success || xmlDoc == null ||
            xmlResponse.indexOf("<Error number=\"1002\">") != -1) {
          System.out.println(
              "IPAddressProvider.getDeviceList() request failed.");
          return null;
        }
        // If it succeeded, return the XML document
        return xmlDoc;
      }

  String sendRequest() {

    ByteArrayOutputStream httpBuffer = new ByteArrayOutputStream(1024);
    HttpURLConnection conn = null;
    try
    {
      String auth = Text2Base64.getBase64(callManagerUserId + ":" +
                                          callManagerPassword);

      URL url = new URL( (useHttps ? "https" : "http") + "://" + callManager +
                        ":8443/realtimeservice/services/RisPort70");
      conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
      conn.setRequestProperty("Content-Length", "" + SOAP_REQUEST.length());
      conn.setRequestProperty("Authorization", "Basic " + auth);
      conn.setRequestProperty("SOAPAction", "\"http://schemas.cisco.com/ast/soap/action/#RisPort#SelectCmDevice\"");

      conn.connect();

      OutputStream ostr = conn.getOutputStream();
      ostr.write(SOAP_REQUEST.getBytes());
      ostr.flush();

      InputStream istr = conn.getInputStream();

      byte[] buff = new byte[1024];
      int bytesRead = istr.read(buff);
      while (bytesRead != -1) {
          httpBuffer.write(buff, 0, bytesRead);
          bytesRead = istr.read(buff);
      }

      
      
      
      conn.disconnect();
    }
    catch (Exception e) {
      System.err.println("AXL Exception:" + e);
      if (conn != null && conn.getErrorStream() != null) {
          try {
          InputStream istr = conn.getErrorStream();
          byte[] buff = new byte[1024];
      int bytesRead = istr.read(buff);
      while (bytesRead != -1) {
          httpBuffer.write(buff, 0, bytesRead);
          bytesRead = istr.read(buff);
      }
      istr.close();
      conn.disconnect();
      System.err.print(httpBuffer.toString());
          }
          catch (Exception ex) {}
      }
    }
    return httpBuffer.toString();
  }
    }

  }

  class DeviceInfo {
    String name, ipAddress;

    DeviceInfo(String name, String ipAddress) {
      this.name = name;
      this.ipAddress = ipAddress;
    }
    
    public String toString() {
        return "DeviceInfo ("+name+","+ipAddress+")";
    }
  }

}
