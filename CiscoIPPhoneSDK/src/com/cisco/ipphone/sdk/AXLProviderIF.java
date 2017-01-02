package com.cisco.ipphone.sdk;

/**
 * The <code>AXLProviderIF</code> interface defines a simple Java "wrapper" interface into Cisco CallManager
 * AVVID XML-Layer Database API (AXL). Any class implementing the <code>AXLProviderIF</code> interface
 * is expected to take care of formatting and communication of AXL SOAP/XML commands.
 * The <code>AXLProvider</code> class included in this SDK is an implementation of the
 * <code>AXLProviderIF</code> interface.
 * @author  kstearns
 * @version 1.0  (March 2003)
 * @see com.cisco.ipphone.sdk.AXLProvider
 */
public interface AXLProviderIF {

  /**
  * This method accepts an AXL Request command along with AXL Parameters and returns the AXL SOAP/XML Response.
  * The implementing <code>AXLProviderIF</code> class must be fully initialized prior to calling this method, but
  * the initialization is not specified by the interface and is implementation-specific.
  * @param axlRequest The AXL Request string (Example: getPhone)
  * @param axlParams The AXL Request parameters required for the given AXL Request (Example: &lt;phoneName&gt;SEP00036B7FFE23&lt;/phoneName&gt;)
  * @return String containing the full AXL response in text XML. If the request was unsuccessful, an empty String or AXL error message will be returned.
  */
public String sendRequest(String axlRequest, String axlParams);

}