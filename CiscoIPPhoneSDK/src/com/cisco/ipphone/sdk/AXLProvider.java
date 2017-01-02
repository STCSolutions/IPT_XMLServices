package com.cisco.ipphone.sdk;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.*;
import javax.net.ssl.*;

/**
 * The <code>AXLProvider</code> class implements the <code>AXLProviderIF</code> interface
 * and is used by IP Phone Service applications to access the Cisco CallManager AVVID
 * XML-Layer Database API (AXL).
 * @author  kstearns
 * @version 2.0  (March 2005)
 * @see com.cisco.ipphone.sdk.AXLProviderIF
 */
public class AXLProvider implements AXLProviderIF {

  static {
    // Make sure the HTTPS subsystem is setup, in case we need it
    HttpsInitializer.init();
  }


  private String callManager, callManagerUserId, callManagerPassword;
  private boolean useHttps;

  /**
   * Equivalent to:
   *
   * AXLProvider(callManager, callManagerUserId, callManagerPassword, false)
   *
   * @param callManager String
   * @param callManagerUserId String
   * @param callManagerPassword String
   */
  public AXLProvider(String callManager, String callManagerUserId, String callManagerPassword) {
    this(callManager, callManagerUserId, callManagerPassword, false);
  }

  /**
   * This is the only constructor provided for the <code>AXLProvider</code> class and
   * all parameters must be supplied. The parameters can be modified after instantiation, if
   * needed, by direct access to the public attributes.
   * @param callManager The IP address or hostname of the CallManager server hosting the AXL API
   * @param callManagerUserId The UserId used to authenticate to the CallManager for AXL access
   * @param callManagerPassword The password of the above UserId
   * @param useHttps boolean If true, https is used instead of http
   */
  public AXLProvider(String callManager, String callManagerUserId, String callManagerPassword, boolean useHttps) {
    this.callManager = callManager;
    this.callManagerUserId = callManagerUserId;
    this.callManagerPassword = callManagerPassword;
    this.useHttps = useHttps;
  }

  /**
  * This method accepts an AXL Request command along with AXL Parameters and returns the AXL SOAP/XML Response.
  * @param axlRequest The AXL Request string (Example: getPhone)
  * @param axlParams The AXL Request parameters required for the given AXL Request (Example: &lt;phoneName&gt;SEP00036B7FFE23&lt;/phoneName&gt;)
  * @return String containing the full AXL response in XML text. If the request was unsuccessful, an empty String or AXL error message will be returned.
  */
  public String sendRequest(String axlRequest, String axlParams) {
    ByteArrayOutputStream httpBuffer = new ByteArrayOutputStream(1024);
    try
    {
      String auth = Text2Base64.getBase64(callManagerUserId + ":" +
                                          callManagerPassword);

      String soaprequest = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2000/10/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\">\n" +
          "<SOAP-ENV:Body>\n" +
          "<axl:" + axlRequest + " xmlns:axl=\"http://www.cisco.com/AXL/1.0\" xsi:schemaLocation=\"http://www.cisco.com/AXL/1.0 http://gkar.cisco.com/schema/axlsoap.xsd\" xsi:type=\"XRequest\" sequence=\"1234\">\n" +
          axlParams + "\n" +
          "</axl:" + axlRequest + ">\n" +
          "</SOAP-ENV:Body>\n" +
          "</SOAP-ENV:Envelope>";

      URL url = new URL( (useHttps ? "https" : "http") + "://" + callManager +
                        "/CCMApi/AXL/V1/soapisapi.dll");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "text/xml");
      conn.setRequestProperty("Content-Length", "" + soaprequest.length());
      conn.setRequestProperty("Authorization", "Basic " + auth);

      conn.connect();

      OutputStream ostr = conn.getOutputStream();
      ostr.write(soaprequest.getBytes());
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
    }
    return httpBuffer.toString();
  }

  public static void main(String[] args) {
    if (args.length != 6) {
      System.out.println("Parameters:");
      System.out.println("callManager callManagerUserID callManagerPassword useHttps axlRequest axlParams");
      System.exit(0);
    }
    AXLProvider prov = new AXLProvider(args[0], args[1], args[2], Boolean.valueOf(args[3]).booleanValue());
    String response = prov.sendRequest(args[4], args[5]);

    System.out.println(response);
  }

}
