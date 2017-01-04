

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.UniVox.Axl;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class AXLProvider implements com.cisco.ipphone.sdk.AXLProviderIF{

    private String callManager,  callManagerUserId,  callManagerPassword;
    private boolean useHttps;

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

    public String sendRequest(String axlRequest, String axlParams) {
        byte[] bArray = null; // buffer for reading response from
        Socket socket = null; // socket to AXL server
        OutputStream out = null; // output stream to server
        InputStream in = null; // input stream from server
        String sAXLSOAPRequest = "";
// HTTPS header and SOAP payload
        String sAXLRequest = null; // will hold only the SOAP payload
//username=CCMAdministrator and password=cisco_cisco
        String authorization = callManagerUserId + ":" + callManagerPassword;
// base64 encoding of the username and password
        authorization = new sun.misc.BASE64Encoder().encode(authorization.getBytes());
// Form the http header
        sAXLSOAPRequest = "POST /axl/ HTTP/1.0\r\n";
        sAXLSOAPRequest += "Host:10.100.2.80:8443\r\n";
        sAXLSOAPRequest += "Authorization: Basic " + authorization + "\r\n";
        sAXLSOAPRequest += "Accept: text/*\r\n";
        sAXLSOAPRequest += "Content-type: text/xml\r\n";
        sAXLSOAPRequest += "SOAPAction: \"CUCM:DB ver=7.0\"\r\n";
        sAXLSOAPRequest += "Content-length: ";
// Build the SOAP payload
        sAXLRequest = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
        //sAXLRequest = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
        //sAXLRequest += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> ";
        sAXLRequest += "<SOAP-ENV:Body> <axl:" + axlRequest + " xmlns:axl=\"http://www.cisco.com/AXL/1.0\" ";
        sAXLRequest += " xsi:schemaLocation=\"http://www.cisco.com/AXL/1.0 http://ccmserver/schema/axlsoap.xsd\" ";
        sAXLRequest += "sequence=\"1234\"> " + axlParams;
        sAXLRequest += "</axl:" + axlRequest + "> </SOAP-ENV:Body> </SOAP-ENV:Envelope>";
// finish the HTTPS Header
        sAXLSOAPRequest += sAXLRequest.length();
        sAXLSOAPRequest += "\r\n\r\n";
// now add the SOAP payload to the HTTPS header, which completes the AXL
// SOAP request
        sAXLSOAPRequest += sAXLRequest;
        StringBuffer sb = new StringBuffer(2048);
        try {

// Implement the certificate-related stuffs required for sending request via https
            X509TrustManager xtm = new MyTrustManager();
            TrustManager[] mytm = {xtm};
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, mytm, null);
            SSLSocketFactory sslFact = (SSLSocketFactory) ctx.getSocketFactory();
            socket = (SSLSocket) sslFact.createSocket(callManager, Integer.parseInt("8443"));
            in = socket.getInputStream();
// send the request to the server
// read the response from the server
            // SynchronizedLogger.log("socket connection");
            bArray = new byte[2048];
            int ch = 0;
            int sum = 0;
            out = socket.getOutputStream();
            out.write(sAXLSOAPRequest.getBytes());
            while ((ch = in.read(bArray)) != -1) {
                sum += ch;
                sb.append(new String(bArray, 0, ch));
            }
            socket.close();
// output the response to the standard output
        //SynchronizedLogger.log(sb.toString());

        } catch (UnknownHostException e) {
            System.err.println("Error connecting to host: " + e.getMessage());

        } catch (IOException ioe) {
            System.err.println("Error sending/receiving from server: " + ioe.getMessage());
// close the socket
        } catch (Exception ea) {
            System.err.println("Unknown exception " + ea.getMessage());

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
                System.err.println("Error closing connection to server: " + exc.getMessage());
            }
        }
        return sb.toString();
    }

//    ********************************************************************
    public String sendTestRequest(String axlRequest, String axlParams) {
        byte[] bArray = null; // buffer for reading response from
        Socket socket = null; // socket to AXL server
        OutputStream out = null; // output stream to server
        InputStream in = null; // input stream from server
        String sAXLSOAPRequest = "";
// HTTPS header and SOAP payload
        String sAXLRequest = null; // will hold only the SOAP payload
//username=CCMAdministrator and password=cisco_cisco
        String authorization = callManagerUserId + ":" + callManagerPassword;
// base64 encoding of the username and password
        authorization = new sun.misc.BASE64Encoder().encode(authorization.getBytes());
// Form the http header
        sAXLSOAPRequest = "POST /axl/ HTTP/1.0\r\n";
        sAXLSOAPRequest += "Host:localhost:8443\r\n";
        sAXLSOAPRequest += "Authorization: Basic " + authorization + "\r\n";
        sAXLSOAPRequest += "Accept: text/*\r\n";
        sAXLSOAPRequest += "Content-type: text/xml\r\n";
        sAXLSOAPRequest += "SOAPAction: \"CUCM:DB ver=6.0\"\r\n";
        sAXLSOAPRequest += "Content-length: ";
// Build the SOAP payload
        sAXLRequest = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
        //sAXLRequest = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
        //sAXLRequest += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> ";
        sAXLRequest += "<SOAP-ENV:Body> <axl:" + axlRequest + " xmlns:axl=\"http://www.cisco.com/AXL/1.0\" ";
        sAXLRequest += " xsi:schemaLocation=\"http://www.cisco.com/AXL/1.0 http://ccmserver/schema/axlsoap.xsd\" ";
        sAXLRequest += "sequence=\"1234\"> " + axlParams;
        sAXLRequest += "</axl:" + axlRequest + "> </SOAP-ENV:Body> </SOAP-ENV:Envelope>";
//        build the SOAP 
        sAXLRequest += "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:tns=\"http://schemas.cisco.com/ast/soap/\" xmlns:types=\"http://schemas.cisco.com/ast/soap/encodedTypes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<soap:Header>" +
                "<tns:AstHeader id=\"id1\">" +
                "<SessionId xsi:type=\"xsd:string\">10.1.1.18-2006-02-01T07:16:21</SessionId>" +
                "</tns:AstHeader>" +
                "</soap:Header>" +
                "<soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                "<tns:SelectCmDevice>" +
                "<CmSelectionCriteria href=\"#id1\"/>" +
                "</tns:SelectCmDevice>" +
                "<tns:CmSelectionCriteria id=\"id1\" xsi:type=\"tns:CmSelectionCriteria\">" +
                "<MaxReturnedDevices xsi:type=\"xsd:unsignedInt\">4294967295</MaxReturnedDevices>" +
                "<Class xsi:type=\"tns:DeviceClass\">Phone</Class>" +
                "<Model xsi:type=\"xsd:unsignedInt\">0</Model>" +
                "<Status xsi:type=\"tns:CmDevRegStat\">Any</Status>" +
                "<SelectBy xsi:type=\"tns:CmSelectBy\">Name</SelectBy>" +
                "<SelectItems href=\"#id2\"/>" +
                "</tns:CmSelectionCriteria>" +
                "<soapenc:Array id=\"id2\" soapenc:arrayType=\"tns:SelectItem[1]\">" +
                "<Item href=\"#id3\"/>" +
                "</soapenc:Array>" +
                "<tns:SelectItem id=\"id3\" xsi:type=\"tns:SelectItem\">" +
                "<Item xsi:type=\"xsd:string\">SEP00115C407632</Item>" +
                "</tns:SelectItem>" +
                "</soap:Body>" +
                "</soap:Envelope>";


// finish the HTTPS Header
        sAXLSOAPRequest += sAXLRequest.length();
        sAXLSOAPRequest += "\r\n\r\n";
// now add the SOAP payload to the HTTPS header, which completes the AXL
// SOAP request
        sAXLSOAPRequest += sAXLRequest;
        StringBuffer sb = new StringBuffer(2048);
        try {

// Implement the certificate-related stuffs required for sending request via https
            X509TrustManager xtm = new MyTrustManager();
            TrustManager[] mytm = {xtm};
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, mytm, null);
            SSLSocketFactory sslFact = (SSLSocketFactory) ctx.getSocketFactory();
            socket = (SSLSocket) sslFact.createSocket(callManager, Integer.parseInt("8443"));
            in = socket.getInputStream();
// send the request to the server
// read the response from the server
            // SynchronizedLogger.log("socket connection");
            bArray = new byte[2048];
            int ch = 0;
            int sum = 0;
            out = socket.getOutputStream();
            out.write(sAXLSOAPRequest.getBytes());
            while ((ch = in.read(bArray)) != -1) {
                sum += ch;
                sb.append(new String(bArray, 0, ch));
            }
            socket.close();
// output the response to the standard output
        //SynchronizedLogger.log(sb.toString());

        } catch (UnknownHostException e) {
            System.err.println("Error connecting to host: " + e.getMessage());

        } catch (IOException ioe) {
            System.err.println("Error sending/receiving from server: " + ioe.getMessage());
// close the socket
        } catch (Exception ea) {
            System.err.println("Unknown exception " + ea.getMessage());

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
                System.err.println("Error closing connection to server: " + exc.getMessage());
            }
        }
        return sb.toString();
    }

    public String sendRisPortTestRequest(/*String axlRequest, String axlParams*/) {
        byte[] bArray = null; // buffer for reading response from
        Socket socket = null; // socket to AXL server
        OutputStream out = null; // output stream to server
        InputStream in = null; // input stream from server
        String sAXLSOAPRequest = "";
// HTTPS header and SOAP payload
        String sAXLRequest = null; // will hold only the SOAP payload
//username=CCMAdministrator and password=cisco_cisco
        String authorization = callManagerUserId + ":" + callManagerPassword;
// base64 encoding of the username and password
        authorization = new sun.misc.BASE64Encoder().encode(authorization.getBytes());
// Form the http header
        sAXLSOAPRequest = "POST /axl/ HTTP/1.0\r\n";
        sAXLSOAPRequest += "Host:localhost:8443\r\n";
        sAXLSOAPRequest += "Authorization: Basic " + authorization + "\r\n";
        sAXLSOAPRequest += "Accept: text/*\r\n";
        sAXLSOAPRequest += "Content-type: text/xml\r\n";
        // in RisPort SOAP ACTION = " SOAPAction: "http://schemas.cisco.com/ast/soap/action/#RisPort#SelectCmDevice"
        sAXLSOAPRequest += " SOAPAction: \"http://schemas.cisco.com/ast/soap/action/#RisPort#SelectCmDevice";
//        sAXLSOAPRequest += "SOAPAction: \"CUCM:DB ver=6.0\"\r\n";
        sAXLSOAPRequest += "Content-length: ";

        // Build the SOAP payload
//        sAXLRequest = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\""+
//
//                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+
//                      " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
//        //sAXLRequest = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
//
//        sAXLRequest += "<SOAP-ENV:Body> <axl:"+axlRequest+" xmlns:axl=\"http://www.cisco.com/AXL/1.0\" ";
//        sAXLRequest += " xsi:schemaLocation=\"http://www.cisco.com/AXL/1.0 http://ccmserver/schema/axlsoap.xsd\" ";
//        sAXLRequest += "sequence=\"1234\"> "+axlParams;
//        sAXLRequest += "</axl:"+axlRequest+"> </SOAP-ENV:Body> </SOAP-ENV:Envelope>";
//        build the SOAP
        sAXLRequest += "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                " xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"" +
                " xmlns:tns=\"http://schemas.cisco.com/ast/soap/\"" +
                " xmlns:types=\"http://schemas.cisco.com/ast/soap/encodedTypes\"" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                "<soap:Header>" +
                //Session ID.. That is unique session id from the client
                "<tns:AstHeader id=\"id1\">" +
                "<SessionId xsi:type=\"xsd:string\">10.1.1.18-2006-02-01T07:16:21</SessionId>" +
                "</tns:AstHeader>" +
                "</soap:Header>" +
                // Selection creteria
                "<soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                "<tns:SelectCmDevice>" +
                // If the same information is queried over and over again, send Stateinfo from the previous request for each
                // repetitive query by client.
                "<StateInfo xsi:type=\"xsd:string\"/>" +
                "<CmSelectionCriteria href=\"#id1\"/>" +
                "</tns:SelectCmDevice>" +
                //Start CmSelectionCriteria
                "<tns:CmSelectionCriteria id=\"id1\" xsi:type=\"tns:CmSelectionCriteria\">" +
                //Maximum Devices specification
                "<MaxReturnedDevices xsi:type=\"xsd:unsignedInt\">4294967295</MaxReturnedDevices>" +
                //Search Device Class
                //Device classes include 'Any',
                // 'Phone', 'Gateway', 'H323', 'Cti', 'VoiceMail', 'MediaResources', 'HuntList', 'SIPTrunk', and 'unknown'.
                "<Class xsi:type=\"tns:DeviceClass\">Phone</Class>" +
                // Specify the model of the device
                // 255 specifies all models
                "<Model xsi:type=\"xsd:unsignedInt\">0</Model>" +
                //Specify  'Any', 'Registered',
                //'UnRegistered', 'Rejected', and 'Unknown.' status device in search criteria
                "<Status xsi:type=\"tns:CmDevRegStat\">Any</Status>" +
                // Specify Selection type whether it is IP Address/Name
                "<SelectBy xsi:type=\"tns:CmSelectBy\">Name</SelectBy>" +
                "<SelectItems href=\"#id2\"/>Name or IP</tns:CmSelectionCriteria>" +
                // End CmSelectionCriteria
                "<soapenc:Array id=\"id2\" soapenc:arrayType=\"tns:SelectItem[2]\">" +
                "<Item href=\"#id3\"/><Item xsi:null=\"1\"/>" +
                "</soapenc:Array>" +
                "<tns:SelectItem id=\"id3\" xsi:type=\"tns:SelectItem\">" +
                "<Item xsi:type=\"xsd:string\"></Item>" +
                "</tns:SelectItem>" +
                "</soap:Body>" +
                "</soap:Envelope>";


// finish the HTTPS Header
        sAXLSOAPRequest += sAXLRequest.length();
        sAXLSOAPRequest += "\r\n\r\n";
// now add the SOAP payload to the HTTPS header, which completes the AXL
// SOAP request
        sAXLSOAPRequest += sAXLRequest;
        StringBuffer sb = new StringBuffer(2048);
        try {

// Implement the certificate-related stuffs required for sending request via https
            X509TrustManager xtm = new MyTrustManager();
            TrustManager[] mytm = {xtm};
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, mytm, null);
            SSLSocketFactory sslFact = (SSLSocketFactory) ctx.getSocketFactory();
            socket = (SSLSocket) sslFact.createSocket(callManager, Integer.parseInt("8443"));
            in = socket.getInputStream();
// send the request to the server
// read the response from the server
            // SynchronizedLogger.log("socket connection");
            bArray = new byte[2048];
            int ch = 0;
            int sum = 0;
            out = socket.getOutputStream();
            out.write(sAXLSOAPRequest.getBytes());
            while ((ch = in.read(bArray)) != -1) {
                sum += ch;
                sb.append(new String(bArray, 0, ch));
            }
            socket.close();
// output the response to the standard output
        //SynchronizedLogger.log(sb.toString());

        } catch (UnknownHostException e) {
            System.err.println("Error connecting to host: " + e.getMessage());

        } catch (IOException ioe) {
            System.err.println("Error sending/receiving from server: " + ioe.getMessage());
// close the socket
        } catch (Exception ea) {
            System.err.println("Unknown exception " + ea.getMessage());

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
                System.err.println("Error closing connection to server: " + exc.getMessage());
            }
        }
        return sb.toString();
    }
//    ********************************************************************

    class MyTrustManager implements X509TrustManager {

        MyTrustManager() {
           // create/load keystore
        }

        public void checkClientTrusted(X509Certificate chain[], String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate chain[], String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public static void main(String[] args) {

        AXLProvider provider = new AXLProvider("192.168.33.90", "Administrator", "metreos1", true);
//        provider.se
//        String mac = "00115C407632";
//        String callingSearchSpaceName = "Walid";
//        String axlRequest = "updatePhone";
//        String AXLString = "<name>SEP" + mac + "</name><callingSearchSpaceName>";
//        String AXLLineString = "<name>SEP" + mac + "</name><lines> </lines>";
//
//        AXLString += callingSearchSpaceName;
//
//
//        AXLString += "</callingSearchSpaceName>";
//        String axlParams = "";
//        provider.sendRequest(axlRequest, AXLString);
//        SynchronizedLogger.log(provider.sendRequest(axlRequest, AXLString));
//        SynchronizedLogger.log(provider.sendRequest(axlRequest, AXLLineString));

    }
}


