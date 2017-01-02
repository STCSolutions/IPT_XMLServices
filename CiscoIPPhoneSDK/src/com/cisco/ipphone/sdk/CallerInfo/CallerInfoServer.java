package com.cisco.ipphone.sdk.CallerInfo;

import java.util.*;
import javax.telephony.*;
import javax.telephony.callcontrol.*;
import javax.telephony.events.*;
import javax.telephony.CallObserver;
import com.cisco.cti.util.Condition;
import com.cisco.jtapi.*;
//import com.cisco.services.*;
import com.cisco.jtapi.extensions.*;

public class CallerInfoServer implements CallObserver, ProviderObserver {

  // The singleton instance of CallInfoServer
  private static CallerInfoServer theInfoServer = null;

  Condition conditionInService = new Condition();
  Provider provider;
  String invokeUrl = null;

  private CallerInfoServer(String ctiServer, String appId, String appPassword, String invokeUrl) {
    this.invokeUrl = invokeUrl;

    try {
      System.out.println("\r\nCreating the CallerInfoServer ...");
      System.out.println("Initializing Jtapi");
      JtapiPeer peer = JtapiPeerFactory.getJtapiPeer(null);
      String providerString = ctiServer + ";login=" + appId + ";passwd=" + appPassword;

      System.out.println("Opening JTAPI provider connection to " + ctiServer + "...");
      provider = peer.getProvider(providerString);
      provider.addObserver(this);
      conditionInService.waitTrue();
      System.out.println("Provider successfully opened.");

      // Add observer for each terminal controlled by the supplied appId
      Terminal[] terms = provider.getTerminals();
      for (int i = 0; i < terms.length; i++) {
        terms[i].addCallObserver(this);
      }

      System.out.println("CallerInfoServer successfully created and running.");
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    if (args.length != 4) {
      System.out.println("Usage: java CallerInfoServer <ctiServer> <ctiAppId> <ctiAppPassword> <phoneName>");
      System.out.println("Example: java CallerInfoServer mycallmanager sdkapp sdkpassword SEP00036B75BAD5");
      System.exit(0);
    }
    // This will create the one and only CallerInfoServer object which starts the application
    getCallerInfoServer(args[0], args[1], args[2], null);
    // Now just continually check the call status of the specified phone
    while (true) {
      String[] numbers = theInfoServer.getActiveCallPartyNumbers(args[3]);
      if (numbers == null) {
        System.out.println("No active calls on " + args[3]);
      }
      else {
        System.out.println("Orig#:" + numbers[0] + " Dest#:" + numbers[1] + " This#:" + numbers[2]);
      }
      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException e) {}
    }
  }

  public static CallerInfoServer getCallerInfoServer(String ctiServer, String appId, String appPassword, String invokeUrl) {
    // Create the CallerInfoServer object with the supplied parameters if it doesn't already exist
    if (theInfoServer == null) {
      theInfoServer = new CallerInfoServer(ctiServer, appId, appPassword, invokeUrl);
    }
    return theInfoServer;
  }

  public static CallerInfoServer getCallerInfoServer(String ctiServer, String appId, String appPassword) {
    return getCallerInfoServer(ctiServer, appId, appPassword, null);
  }

  public static CallerInfoServer getCallerInfoServer() {
    // Simply return the CallerInfoServer - it may be null if it has not yet been created
    return theInfoServer;
  }

  public void providerChangedEvent ( ProvEv [] eventList ) {
          if ( eventList != null ) {
                  for ( int i = 0; i < eventList.length; i++ )
                  {
                          if ( eventList[i] instanceof ProvInServiceEv ) {
                                  conditionInService.set ();
                          }
                  }
          }
  }

  public void callChangedEvent(CallEv[] ev) {
    if (invokeUrl != null) {
      for (int i = 0; i < ev.length; i++) {
        Connection[] conns = ev[i].getCall().getConnections();
        // Only going to hand 2-party calls - no conference calls
        if (conns.length == 2) {
          // Specifically looking for a Connected event
          if (ev[i] instanceof ConnConnectedEv) {

            // Originating connection/number will always be first Connection in the list
            String origNumber = conns[0].getAddress().getName();
            String destNumber = conns[1].getAddress().getName();

            // Create the Execute object to PUSH to the phone that just answered this call
            String xml = "<CiscoIPPhoneExecute>" +
                "<ExecuteItem URL=\"" + invokeUrl + origNumber + "\"/>" +
                "</CiscoIPPhoneExecute>";

            // Use the 2nd Connection object because it will be the receiving end of the call -
            // that's the phone we want to PUSH to
            CiscoTerminal ct = (CiscoTerminal) conns[1].getTerminalConnections()[
                0].getTerminal();

            try {
              // DEBUG code
              System.out.println("SENDING:" + xml + " \r\n to phone " +
                                 ct.getName());
              ct.sendData(xml);
            }
            catch (Exception e) {
              System.out.println(e);
            }
          }
        }
      }
    }
  }

  public String[] getActiveCallPartyNumbers(String phoneName) {
    Terminal term = null;
    try {
      term = provider.getTerminal(phoneName);
    }
    catch (InvalidArgumentException e) {
      System.out.println(e);
      return null;
    }
    TerminalConnection[] tconns = term.getTerminalConnections();
    if (tconns != null) {
      // Step thru each TerminalConnection for this Phone and find the current active (TALKING)
      // Terminal connection if it exists
      for (int i = 0; i < tconns.length; i++) {
        CallControlTerminalConnection tconn = (CallControlTerminalConnection)tconns[i];
        if (tconn.getCallControlState() == CallControlTerminalConnection.TALKING) {
          Connection conn = tconn.getConnection();

          // This not used in our case, but this would capture the address (number)
          // in use on local phone (specified by phoneName) for the current active call
          String localCallPartyNumber = conn.getAddress().getName();

          Connection[] conns = conn.getCall().getConnections();
          if (conns.length != 2) {
            // This code doesn't support more than 2-party calls, so return null if more than 2.
            return null;
          }
          String[] numbers = new String[3];
          // Originating number
          numbers[0] = conns[0].getAddress().getName();
          // Destination number
          numbers[1] = conns[1].getAddress().getName();
          // This phone's number
          numbers[2] = conn.getAddress().getName();
          // By returning all three numbers, you can identify both call parties and the direction
          // of the call
          return numbers;
        }
      }
    }
    // No active call found, so return null
    return null;
  }
}
