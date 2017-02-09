/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stcs.pushtophone;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author Magdy.Alhoseiny
 */
public class Push2Phone
{

    @SuppressWarnings("static-access")
    public boolean push(String []uris, String phoneIP,
            String pushUserIdPassword,
            boolean getResult)
    {
       // String []uris = {urlStr};
        if (uris.length > 3)
        {

            return false;
        }
        if (phoneIP == null)
        {

            return false;
        }

        
        String pushAuth = pushUserIdPassword;
        //System.out.println(pushAuth);
        String pushXML = "<CiscoIPPhoneExecute>";
        for (int i = 0; i < uris.length; i++)
        {
            pushXML = pushXML + "<ExecuteItem Priority=\"0\" URL=\"" +
                    uris[i] + "\"/>";
        }
        pushXML = pushXML + "</CiscoIPPhoneExecute>";


        try
        {
            String httpData = "XML=" + URLEncoder.encode(pushXML,"UTF-8");

            URL url = new URL("http://" + phoneIP + "/CGI/Execute");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setFollowRedirects(getResult);
            conn.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "Basic " + pushAuth);
            PrintWriter pout = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
            pout.print(httpData);
            pout.flush();
            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            int c;
            do
            {
                char x;
                c = is.read();
                x = (char) c;
                builder.append(x);
            }
            while (c != -1);
            System.out.println(builder.toString());
        } catch (Exception e)
        {
            e.printStackTrace();
            //System.err.println("Exception:" + e);
            return false;
        }
        return true;
    }
    
    
    public static void main(String[] args)
    {

        //String []urls = {"RTPTx:192.168.1.160:20482:99","http://192.168.1.106:8080/WebApplication3/WeatherServlet"};

        //String []urls = {"RTPTx:Stop","RTPMTx:239.0.0.1:20484"};
       // String []urls = {"RTPRx:Stop","RTPRx:192.168.1.255:32702:100"};
        //String []urls2 = {"RTPRx:Stop","RTPMRx:239.0.0.1:20484:50"};
        //String []urls2 = {"RTPRx:Stop","RTPRx:129.130.131.132:42050:50"};
        //String []urls2 = {"RTPRx:Stop","RTPRx:192.168.1.160:42050"};
        String []urls2 = {"RTPRx:Stop","RTPRx:192.168.1.255:32704:100"};


        //String []urls = {"RTPTx:Stop","RTPTx:192.168.1.106:20484"};
        //String []urls2 = {"RTPRx:Stop","RTPRx:192.168.1.106:20484:100"};
        
        //try {
            //Thread.sleep(10000);
        //} catch (InterruptedException ex) {
        //    Logger.getLogger(Push2Phone.class.getName()).log(Level.SEVERE, null, ex);
        //}
        
        //p2p2.push(urls2, "192.168.1.160", Text2Base64.getBase64("AppUser:hosting2010"), true);

        Push2Phone p2p = new Push2Phone();
        //p2p.push(urls, "192.168.1.160", Text2Base64.getBase64("AppUser:hosting2010"), true);
        //Push2Phone p2p2 = new Push2Phone();
        //p2p.push(urls, "192.168.1.160", Text2Base64.getBase64("AppUser:hosting2010"), true);
      //  p2p.push(urls, "192.168.1.160", Text2Base64.getBase64("AppUser:hosting2010"), true);
        //p2p.push(urls, "192.168.1.137", Text2Base64.getBase64("AppUser:hosting2010"), true);
        p2p.push(urls2, "192.168.1.161", Text2Base64.getBase64("AppUser:hosting2010"), true);
        //p2p.push(urls2, "192.168.1.137", Text2Base64.getBase64("AppUser:hosting2010"), true);

    }


}
