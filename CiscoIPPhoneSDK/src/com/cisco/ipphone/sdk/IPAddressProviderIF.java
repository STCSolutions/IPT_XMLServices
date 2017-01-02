package com.cisco.ipphone.sdk;

import java.util.HashMap;

/**
 * The <code>IPAddressProviderIF</code> interface defines a simple Java "wrapper" interface into Cisco CallManager
 * DeviceListX API. Any class implementing the <code>IPAddressProviderIF</code> interface
 * is expected to query the CallManager for a current list of active devices and their IP addresses.
 * The implementation is expected to cache this information and control its refresh interval in order
 * to minimize the load on the CallManager server, as well as the application server.
 * The implementation should only report the IP addresses of devices which are actively registered to a CallManager
 * server - any IP addresses listed for devices in an un-registered state, should be ignored.
 * The <code>IPAddressProvider</code> class included in this SDK is an implementation of the
 * <code>IPAddressProviderIF</code> interface.
 * @author  kstearns
 * @version 1.0  (March 2003)
 * @see com.cisco.ipphone.sdk.IPAddressProvider
 */
public interface IPAddressProviderIF {

  /**
  * This method retrieves the current active IP address of the specified Phone.
  * @param phone the Phone for which an IP address is requested
  * @return a String containing the IP address of the Phone in dotted-decimal format. If the Phone is not found, <code>null</code> will be returned.
  */
  public String getIPAddress(Phone phone);

  /**
  * This method retrieves the current active IP addresses of the specified Phones.
  * @param phones an Array of Phones for which IP addresses are requested
  * @return an Array of Strings containing the IP addresses of the requested Phones in dotted-decimal format. The returned array will always be the same length as the phones[] supplied as an argument. For any phones not found, the corresponding array element will contain <code>null</code>.
  */
  public String[] getIPAddresses(Phone[] phones);


  /**
  * This method retrieves all of the current active IP addresses of devices.
  * @return a HashMap which contains all of the deviceNames (the HashMap keys) and their IP addresses (the HashMap values)
  */
  public HashMap getAllIPAddresses();

  /**
  * This method does the inverse of the previous methods - it accepts an IP address as a parameter
  * and searches for the Phone currently using that address.
  * @param ipAddress a String containing the current IP address of the requested Phone
  * @return the Phone currently using the specified IP address or <code>null</code> if the address could not be found
  */
  public Phone getPhone(String ipAddress);

}