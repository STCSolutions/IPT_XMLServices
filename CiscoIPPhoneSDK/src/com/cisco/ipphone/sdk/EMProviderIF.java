package com.cisco.ipphone.sdk;

/**
 * The <code>EMProviderIF</code> interface defines a simple Java "wrapper" interface into Cisco CallManager
 * Extension Mobility API. Any class implementing the <code>EMProviderIF</code> interface
 * is expected to take care of formatting and communication of Extension Mobility XML commands.
 * The <code>EMProvider</code> class included in this SDK is an implementation of the
 * <code>EMProviderIF</code> interface.
 * @author  kstearns
 * @version 1.0  (March 2003)
 * @see com.cisco.ipphone.sdk.EMProvider
 */
public interface EMProviderIF {

  /**
  * This method accepts an Extension Mobility command along with the command action of either
  * QUERY or REQUEST, and sends the request to the CallManager Extension Mobility API.
  * The values of the public static integer constants QUERY and REQUEST should be defined by the
  * implementing class with the integer values of 1 and 2, respectively, as they are in the
  * provided <code>EMProvider</code> implementation.
  * The implementing <code>AXLProviderIF</code> class must be fully initialized prior to calling this
  * method, but the initialization is not specified by the interface and is implementation-specific.
  * @param action The EM action type of either EMProvider.QUERY or EMProvider.REQUEST. The action type is determined by the type of command being issued. Some commands QUERY for EM login state while others make a REQUEST to change it.
  * @param command The EM command String (Example: &lt;deviceUserQuery&gt;&lt;deviceName&gt;SEP00036B7FFE23&lt;/deviceName&gt;&lt;/deviceUserQuery&gt;)
  * @return String containing the full EM response in text XML. If the request is unsuccessful, an empty String or EM error message will be returned.
  */
  public String sendRequest(int action, String command);

}