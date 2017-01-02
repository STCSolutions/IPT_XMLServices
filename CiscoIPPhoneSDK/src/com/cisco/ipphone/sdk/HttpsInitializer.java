package com.cisco.ipphone.sdk;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * <p>Title: HttpsInitializer</p>
 * <p>Description: This helper class sets the default socket facory and hostname verifier for the
 * HttpsURLConnection class. Overriding the default HttpURLConnection behavior is done to prevent
 * this client from attempting to authenticate the remote server. This is done because the CallManager
 * server uses its locally-significant certificate when sending SSL credentials for API connections and
 * this client application won't have any CA cert avaliable to verify the CallManager cert.
 * Overriding the HttpURLConnection avoids the hassle of loading the CallManager's certificate onto the
 * application server.</p>
 * <p>
 * NOTE: Some Java application environments (such as WebSphere) may not allow the HttpURLConnection defaults
 * to be modified. In this case, you may need to change the platform configuration to allow this, or
 * change the source code for the Provider classes such that they only modify specific HttpURLConnections,
 * or if that still isn't allowed, you may need to install the CallManager's certificate onto your
 * application server.
 * </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Cisco Systems, Inc.</p>
 * @author kstearns
 * @version 1.0
 */

public class HttpsInitializer {

  private static boolean initialized = false;

  public static final synchronized void init() {

    if (initialized) return;

    try {
      X509TrustManager xtm = new MyTrustManager();
      TrustManager[] mytm = {
          xtm};
      SSLContext ctx = SSLContext.getInstance("SSL");
      ctx.init(null, mytm, null);
      SSLSocketFactory sf = ctx.getSocketFactory();

      HttpsURLConnection.setDefaultSSLSocketFactory(sf);
      HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
      initialized = true;
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

class MyTrustManager implements X509TrustManager {
  MyTrustManager() {}
  public void checkClientTrusted(X509Certificate chain[], String authType) throws CertificateException {}
  public void checkServerTrusted(X509Certificate chain[], String authType) throws CertificateException {}
  public X509Certificate[] getAcceptedIssuers() {
      return null;
  }
}

class MyHostnameVerifier implements HostnameVerifier {
  public boolean verify(String hostname, SSLSession session) {
    return true;
  }
}
