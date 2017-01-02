package com.cisco.ipphone.sdk;

import java.util.*;

/**
 * The <code>LDAPProviderIF</code> interface defines a simple Java "wrapper" interface into an LDAP
 * directory. Any class implementing the <code>LDAPProviderIF</code> interface
 * is expected to take care of formatting and communication of LDAP query commands along with basic
 * LDAP attribute parsing.
 * The <code>LDAPProvider</code> class included in this SDK is an implementation of the
 * <code>LDAPProviderIF</code> interface.
 * @author  kstearns
 * @version 1.0  (March 2003)
 * @see com.cisco.ipphone.sdk.LDAPProvider
 */
public interface LDAPProviderIF {

  /**
  * This method searches the previous LDAP <code>sendReqest</code> result looking for the
  * <b>first</b> child node with the specified LDAP attribute and returns the value (the first value
  * if multiple values exist) of that attribute.
  * The <code>sendReqest</code> MUST always be called first to populate the search result - otherwise
  * this method will always return <code>null</code>.
  * @param attrName The String name of the desired attribute (Example: telephoneNumber);
  * @return String containing the value of the requested attribute. If multiple values exist, only the first value will be returned. If the specified attribute cannot be found
  * or does not contain a value, <code>null</code> will be returned.
  */
  public String getAttributeValue(String attrName);

  /**
  * This method searches the previous LDAP <code>sendReqest</code> result looking for the
  * <b>first</b> child node with the specified LDAP attribute and returns the values of that attribute.
  * The <code>sendReqest</code> MUST always be called first to populate the search result - otherwise
  * this method will always return <code>null</code>.
  * @param attrName The String name of the desired attribute (Example: ciscoCCNatControlDevices);
  * @return An array of Strings containing the values of the requested attribute. If the specified attribute cannot be found
  * or does not contain any values, <code>null</code> will be returned.
  */
  public String[] getAttributeValues(String attrName);

  /**
  * This method performs an LDAP query based on the specified parameters and returns the result
  * as a Vector of HashMaps. This may seem a bit strange, but it is actually much easier to use and
  * parse than a standard LDAP tree object.
  * The result of the last search is also cached in the <code>LDAPProviderIF</code> object for
  * subsequent <code>getAttributeValue</code> requests - these methods provide a simple method of extracting
  * attribute values without the need to parse the Vector of HashMaps returned by this method.
  * The implementing <code>LDAPProviderIF</code> class must be fully initialized prior to calling this
  * method, but the initialization is not specified by the interface and is implementation-specific.
  * @param searchBase The LDAP Search Base to be used for this search (Example: ou=Users)
  * @param matchRules A HashMap containing attribute String names (the HashMap keys) and their String values. These filters limit the search results to only those nodes which match the specified criteria. A value of <code>null</code> will specify that no filtering is applied.
  *        (Example: sn=Stearns, givenName=Kelly)
  * @param ignoreCase boolean value indicating whether or not the search should be case-sensitive. If <code>true</code>, the search ignores case.
  * @param returnAttrs an Array of Strings indicating which attributes should be returned in the result. A value of <code>null</code> will specify ALL attributes.
  *        (Example: sn, givenName, telephoneNumber)
  * @return returns a Vector of HashMaps where the HashMaps represent the matching nodes of the
  * LDAP search. Each HashMap entry has the attribute names as the keys (for example, givenName) and
  * a Vector as the value - these Vectors, in turn, contain the attribute's String values. See the <code>User</code> class in this SDK
  * for an example of how to extract information from this result.
  * @see com.cisco.ipphone.sdk.User
  */
  public Vector sendRequest(String searchBase, HashMap matchRules, boolean ignoreCase, String[] returnAttrs);

  public Vector sendRequest(String searchBase, String filter, boolean ignoreCase, String[] returnAttrs);

}