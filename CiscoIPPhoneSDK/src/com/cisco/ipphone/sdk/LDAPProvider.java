package com.cisco.ipphone.sdk;

import java.util.HashMap;
import java.util.Set;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.NamingEnumeration;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;
import javax.naming.directory.SearchControls;


/**
 * The <code>LDAPProvider</code> implements the <code>LDAPProviderIF</code> and provides a simple
 * Java "wrapper" around a generic LDAP directory. This class handles the formatting and communication
 * of LDAP query commands and provides basic LDAP attribute parsing.
 * @author  kstearns
 * @version 1.0  (March 2003)
 * @see com.cisco.ipphone.sdk.LDAPProviderIF
 */
public class LDAPProvider implements LDAPProviderIF{

  private String ldapServer, ldapUserId, ldapPassword, rootDn;
  private int ldapPort;
  private Vector lastResult = null;

  /**
   * This is the only constructor provided for the <code>LDAPProvider</code> class and
   * all parameters must be supplied. The parameters can be modified after instantiation, if
   * needed, by direct access to the public attributes.
   * @param ldapServer The IP address or hostname of the LDAP server to be queried
   * @param ldapUserId The UserId used to authenticate to the LDAP server
   * @param ldapPassword The password of the above UserId
   * @param ldapPort The TCP port number used by the LDAP server (Example: 8404 for CallManager's integrated LDAP directory)
   */
  public LDAPProvider(String ldapServer, String ldapUserId, String ldapPassword, int ldapPort, String rootDn) {
    this.ldapServer = ldapServer;
    this.ldapUserId = ldapUserId;
    this.ldapPassword = ldapPassword;
    this.ldapPort = ldapPort;
    this.rootDn = rootDn;
  }

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
  public synchronized String getAttributeValue(String attrName) {
    if (lastResult != null) {
      HashMap theFirstMap = (HashMap)lastResult.get(0);
      if (theFirstMap != null) {
        Vector values = (Vector)theFirstMap.get(attrName);
        if (values != null) {
          return (String)values.get(0);
        }
      }
    }
    return null;
  }

  /**
  * This method searches the previous LDAP <code>sendReqest</code> result looking for the
  * <b>first</b> child node with the specified LDAP attribute and returns the values of that attribute.
  * The <code>sendReqest</code> MUST always be called first to populate the search result - otherwise
  * this method will always return <code>null</code>.
  * @param attrName The String name of the desired attribute (Example: ciscoCCNatControlDevices);
  * @return An array of Strings containing the values of the requested attribute. If the specified attribute cannot be found
  * or does not contain any values, <code>null</code> will be returned.
  */
  public synchronized String[] getAttributeValues(String attrName) {
    if (lastResult != null) {
      HashMap theFirstMap = (HashMap)lastResult.get(0);
      if (theFirstMap != null) {
        Vector values = (Vector)theFirstMap.get(attrName);
        if (values != null) {
          String[] stringValues = new String[values.size()];
          for (int i=0; i < values.size(); i++) {
            stringValues[i] = (String)values.get(i);
          }
          return stringValues;
        }
      }
    }
    return null;
  }

  public synchronized String[] getAttributeNames(int index) {
    if (lastResult != null && index >= 0 && index < lastResult.size()) {
      HashMap theMap = (HashMap)lastResult.get(index);
      Set keySet = theMap.keySet();
      String[] attrNames = (String[])keySet.toArray();
      return attrNames;
    }
    return null;
  }

  /**
  * This method performs an LDAP query based on the specified parameters and returns the result
  * as a Vector of HashMaps. This may seem a bit strange, but it is actually much easier to use and
  * parse than a standard LDAP tree object.
  * The result of the last search is also cached in the <code>LDAPProvider</code> object for
  * subsequent <code>getAttributeValue</code> requests - those methods provide a simple method of extracting
  * attribute values without the need to parse the Vector of HashMaps returned by this method.
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
  public synchronized Vector sendRequest(String searchBase, HashMap matchRules, boolean ignoreCase, String[] returnAttrs) {
    lastResult = new Vector();
    NamingEnumeration response = null;
    try {
      Hashtable env = new Hashtable();
      env.put(Context.INITIAL_CONTEXT_FACTORY,
              "com.sun.jndi.ldap.LdapCtxFactory");
      env.put(Context.PROVIDER_URL,
              "ldap://" + ldapServer + ":" + String.valueOf(ldapPort) + "/" + rootDn);
      env.put(Context.SECURITY_AUTHENTICATION, "simple");
      env.put(Context.SECURITY_PRINCIPAL, ldapUserId);
      env.put(Context.SECURITY_CREDENTIALS, ldapPassword);

      DirContext ctx = new InitialDirContext(env);

      Attributes matchAttrs = new BasicAttributes(ignoreCase);
      if (matchRules != null) {
        Iterator attrIter = matchRules.keySet().iterator();
        while (attrIter.hasNext()) {
          String key = (String) attrIter.next();
          String value = (String) matchRules.get(key);
          matchAttrs.put(new BasicAttribute(key, value));
        }
      }

      response = ctx.search(searchBase, matchAttrs, returnAttrs);

      lastResult = new Vector();
      while (response.hasMore()) {
        HashMap attrMap = new HashMap();
        SearchResult sr = (SearchResult) response.next();
        Attributes attrs = sr.getAttributes();
        NamingEnumeration attrsEnum = attrs.getAll();
        while (attrsEnum.hasMore()) {
          Vector values = new Vector();
          Attribute attr = (Attribute) attrsEnum.next();
          for (int i = 0; i < attr.size(); i++) {
            values.add(attr.get(i));
          }
          attrMap.put(attr.getID(), values);
        }
        lastResult.add(attrMap);
      }
    }
    catch (NamingException e) {
      System.err.println("Problem looking up name:" + e);
    }
    catch (Exception e) {
      System.err.println("Exception:" + e);
    }
    return lastResult;

  }

  /**
  * This method performs an LDAP query based on the specified parameters and returns the result
  * as a Vector of HashMaps. This may seem a bit strange, but it is actually much easier to use and
  * parse than a standard LDAP tree object.
  * The result of the last search is also cached in the <code>LDAPProvider</code> object for
  * subsequent <code>getAttributeValue</code> requests - those methods provide a simple method of extracting
  * attribute values without the need to parse the Vector of HashMaps returned by this method.
  * @param searchBase The LDAP Search Base to be used for this search (Example: ou=Users)
  * @param filter An LDAP filter String containing attributes to match. For example: (sn=Stearns*)
  * @param ignoreCase boolean value indicating whether or not the search should be case-sensitive. If <code>true</code>, the search ignores case.
  * @param returnAttrs an Array of Strings indicating which attributes should be returned in the result. A value of <code>null</code> will specify ALL attributes.
  *        (Example: sn, givenName, telephoneNumber)
  * @return returns a Vector of HashMaps where the HashMaps represent the matching nodes of the
  * LDAP search. Each HashMap entry has the attribute names as the keys (for example, givenName) and
  * a Vector as the value - these Vectors, in turn, contain the attribute's String values. See the <code>User</code> class in this SDK
  * for an example of how to extract information from this result.
  * @see com.cisco.ipphone.sdk.User
  */
  public synchronized Vector sendRequest(String searchBase, String filter, boolean ignoreCase, String[] returnAttrs) {
    lastResult = new Vector();
    NamingEnumeration response = null;
    try {
      Hashtable env = new Hashtable();
      env.put(Context.INITIAL_CONTEXT_FACTORY,
              "com.sun.jndi.ldap.LdapCtxFactory");
      env.put(Context.PROVIDER_URL,
              "ldap://" + ldapServer + ":" + String.valueOf(ldapPort) + "/" + rootDn);
      env.put(Context.SECURITY_AUTHENTICATION, "simple");
      env.put(Context.SECURITY_PRINCIPAL, ldapUserId);
      env.put(Context.SECURITY_CREDENTIALS, ldapPassword);

      DirContext ctx = new InitialDirContext(env);

      SearchControls sc = new SearchControls(SearchControls.ONELEVEL_SCOPE, 0, 0, returnAttrs, false, true);
      response = ctx.search(searchBase, filter, sc);

      lastResult = new Vector();
      while (response.hasMore()) {
        HashMap attrMap = new HashMap();
        SearchResult sr = (SearchResult) response.next();
        Attributes attrs = sr.getAttributes();
        NamingEnumeration attrsEnum = attrs.getAll();
        while (attrsEnum.hasMore()) {
          Vector values = new Vector();
          Attribute attr = (Attribute) attrsEnum.next();
          for (int i = 0; i < attr.size(); i++) {
            values.add(attr.get(i));
          }
          attrMap.put(attr.getID(), values);
        }
        lastResult.add(attrMap);
      }
    }
    catch (NamingException e) {
      System.err.println("Problem looking up name:" + e);
    }
    catch (Exception e) {
      System.err.println("Exception:" + e);
    }
    return lastResult;
  }

  /**
   * This main() method is for testing purposes only.
   * It provides a simple mechanism for setting up and debugging LDAP server connections.
   * The arguments must be:
   *   LDAPServer UserID Password LDAPPort RootDN SearchBase [repeatCount] [repeatInterval]
   * @param args String[]
   */
  public static void main(String args[]) {
    if (args.length < 6) {
      System.out.println("Parameters: LDAPServer UserID Password LDAPPort RootDN SearchBase [repeatCount] [repeatInterval]");
      System.exit(0);
    }
    int repeatCount = (args.length > 6) ? Integer.parseInt(args[6]) : 1;
    int repeatInterval = (args.length > 7) ? Integer.parseInt(args[7]) : 10;

    LDAPProvider lp = new LDAPProvider(args[0], args[1], args[2], Integer.parseInt(args[3]), args[4]);
    HashMap nullmap = null;
    Vector results = new Vector();

    for (int r=0; r < repeatCount; r++) {
      Vector result = lp.sendRequest(args[5], nullmap, true, null);
      results.add(result);
      for (int i = 0; i < result.size(); i++) {
        HashMap attrs = (HashMap) result.get(i);
        Set keySet = attrs.keySet();
        Object[] attrNames = (Object[]) keySet.toArray();

        for (int j = 0; j < attrNames.length; j++) {
          System.out.print("<br>" + attrNames[j] + ":");
          Vector values = (Vector) attrs.get(attrNames[j]);
          for (int k = 0; k < values.size(); k++) {
            System.out.print(" " + values.get(k));
          }
          System.out.println(" ");
        }
        System.out.println(" ");
      }
      try {
        Thread.sleep(repeatInterval * 1000);
      }
      catch (InterruptedException ex) {
      }
    }
    System.exit(0);
  }

}
