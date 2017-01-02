package com.cisco.ipphone.sdk;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;

import java.net.URLEncoder;

/**
 * The <code>EMProvider</code> class implements the <code>EMProviderIF</code> interface and provides
 * a simple Java "wrapper" interface into the Cisco CallManager Extension Mobility API.
 * @author  kstearns
 * @version 2.0  (March 2004)
 * @see com.cisco.ipphone.sdk.EMProviderIF
 */
public class EMProvider implements EMProviderIF{

  public static final int QUERY = 1;
  public static final int REQUEST = 2;

  private boolean pre40 = false;

  private String callManager, callManagerUserId, callManagerPassword, extMobUserId, extMobPassword;

  /**
   * This is the only constructor provided for the <code>EMProvider</code> class and
   * all parameters must be supplied. The parameters can be modified after instantiation, if
   * needed, by direct access to the public attributes.
   * @param callManager The IP address or hostname of the CallManager server hosting the AXL API
   * @param callManagerUserId The UserId used to authenticate to the CallManager for AXL access
   * @param callManagerPassword The password of the above UserId
   * @param extMobUserId The UserId of a CallManager Directory user with EM Authentication Proxy Rights enabled
   * @param extMobPassword The password of the above UserId
   * @param pre40 If true, the provider will be compatible with CallManager releases prior to 4.0
   */
  public EMProvider(String callManager, String callManagerUserId, String callManagerPassword, String extMobUserId, String extMobPassword, boolean pre40) {
      this(callManager, callManagerUserId, callManagerPassword, extMobUserId, extMobPassword);
      this.pre40 = pre40;
  }

  /**
   * This is the only constructor provided for the <code>EMProvider</code> class and
   * all parameters must be supplied. The parameters can be modified after instantiation, if
   * needed, by direct access to the public attributes.
   * @param callManager The IP address or hostname of the CallManager server hosting the AXL API
   * @param callManagerUserId The UserId used to authenticate to the CallManager for AXL access
   * @param callManagerPassword The password of the above UserId
   * @param extMobUserId The UserId of a CallManager Directory user with EM Authentication Proxy Rights enabled
   * @param extMobPassword The password of the above UserId
   */
  public EMProvider(String callManager, String callManagerUserId, String callManagerPassword, String extMobUserId, String extMobPassword) {
    this.callManager = callManager;
    this.callManagerUserId = callManagerUserId;
    this.callManagerPassword = callManagerPassword;
    this.extMobUserId = extMobUserId;
    this.extMobPassword = extMobPassword;
  }

  /**
  * This method accepts an Extension Mobility command along with the command action of either
  * QUERY or REQUEST, and sends the request to the CallManager Extension Mobility API.
  * @param action The EM action type of either EMProvider.QUERY or EMProvider.REQUEST. The action type is determined by the type of command being issued. Some commands QUERY for EM login state while others make a REQUEST to change it.
  * @param command The EM command String (Example: &lt;deviceUserQuery&gt;&lt;deviceName&gt;SEP00036B7FFE23&lt;/deviceName&gt;&lt;/deviceUserQuery&gt;)
  * @return String containing the full EM response in text XML. If the request is unsuccessful, an empty String or EM error message will be returned.
  */
  public String sendRequest(int action, String command) {
    if (action == QUERY) {
      command = "<query><appInfo>" +
          "<appID>" + extMobUserId + "</appID>" +
          "<appCertificate>" + extMobPassword + "</appCertificate>" +
          "</appInfo>" + command + "</query>";
    }
    if (action == REQUEST) {
      command = "<request><appInfo>" +
          "<appID>" + extMobUserId + "</appID>" +
          "<appCertificate>" + extMobPassword + "</appCertificate>" +
          "</appInfo>" + command + "</request>";
    }
    String script = (action == REQUEST) ? "login.asp" : "query.asp";
    String emUrl = (pre40) ?
            "http://" + callManager + "/LoginService/" + script :
            "http://" + callManager + "/emservice/EMServiceServlet";

    String auth = Text2Base64.getBase64(callManagerUserId + ":" + callManagerPassword);

    ByteArrayOutputStream httpBuffer = new ByteArrayOutputStream(1024);

    try
    {

      URL url = new URL(emUrl);
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setDoInput(true);
      conn.setDoOutput(true);
      conn.setFollowRedirects(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      conn.setRequestProperty("Authorization", "Basic " + auth);
      conn.connect();
      PrintWriter pout = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
      pout.print("xml=" + URLEncoder.encode(command));
      pout.flush();

      InputStream istr = conn.getInputStream();

      byte[] buff = new byte[1024];
      int bytesRead = istr.read(buff);
      while (bytesRead != -1) {
          httpBuffer.write(buff, 0, bytesRead);
          bytesRead = istr.read(buff);
      }

    }
    catch (Exception e) {System.err.println("Exception:" + e); }
    return httpBuffer.toString();
  }


}
