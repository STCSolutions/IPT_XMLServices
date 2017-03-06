/*
 * this class acts as the interface to the active directory
 * that's used paging retrieval of the users data
 */
package com.ADConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;

/**
 *
 * @author Amr Abd El Monsif
 */
public class ActiveDirectoryConnection {

    private LdapContext ctx_AD = null;
    byte[] cookie;
    int pageSize = 50;
    public String ActiveDirectoryIP = "";
    public String ActiveDirectoryPort = "";
    public String ActiveDirectory_Domain = "";
    public String ActiveDirectory_UserLogginName = "";
    public String ActiveDirectory_UserLogginPassword = "";
    public String ActiveDirectory_AuthorizedGroups = "";
    public String ActiveDirectory_SearchString_Users_Root = "";
    public String ActiveDirectory_SearchString_AuthorizedGroups_Root = "";
    public String AD_IpphoneExtField = "";
    public String AD_MobileField = "";

    public LdapContext getCtx_AD() {
        return ctx_AD;
    }

    public void setCtx_AD(LdapContext ctx_AD) {
        this.ctx_AD = ctx_AD;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Attempting to connect...");
        //ActiveDirectoryConnection AD = new ActiveDirectoryConnection( "172.20.66.180", "389", "RMH", "asalah", "104070", "DC=rmh,DC=med,DC=local" );
        //ActiveDirectoryConnection AD = new ActiveDirectoryConnection( "128.0.0.245", "3268", "smsaexpress0", "mjunaidi", "Pass1234", "OU=Information Technology,OU=RUHHQ,OU=Accounts,OU=SMSA Express Trans. Co. Ltd.,DC=smsaexpress,DC=local" );
        ActiveDirectoryConnection AD = new ActiveDirectoryConnection("10.1.11.52", "3268", "AWAL", "aatawfik", "P@ssw0rd1", "DC=awal,DC=com,DC=sa");

        ArrayList<User> Users = AD.getUsersInfo(AD.getCtx_AD());
        System.out.println("Users List Size=" + Users.size());
//        ArrayList<String> Groups = AD.GetGroupsorOUsList(AD.getCtx_AD(), "Group");
//        ArrayList<String> OUS = AD.GetGroupsorOUsList(AD.getCtx_AD(), "organizationalUnit");
        //  Hashtable<String, ArrayList<User>> x=AD.UserAsignedGroups(Groups, Users);
        //   Hashtable<String, ArrayList<User>> x=AD.UserAsignedGOU(OUS, Users);
//        AD.checkCredntials(null, null);
    }
/////////////Constructor////////////////////

    public ActiveDirectoryConnection(String AD_IP, String AD_PortNumber, String AD_DomainName, String UserLogginName, String UserLogginPassword, String AD_SearchString) throws Exception {
        LdapContext ctx = null;
        this.ActiveDirectory_SearchString_Users_Root = AD_SearchString;
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "Simple");
        env.put(Context.SECURITY_PRINCIPAL, AD_DomainName + "\\" + UserLogginName);
        env.put(Context.SECURITY_CREDENTIALS, UserLogginPassword);
        env.put(Context.PROVIDER_URL, "ldap://" + AD_IP + ":" + AD_PortNumber);

        try {
            // Create the initial directory context
            ctx = new InitialLdapContext(env, null);
            //set the page size for the retrieved objects from active directory
            Properties ProbFile = new Properties();
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
            pageSize = Integer.parseInt(ProbFile.getProperty("pageSize"));
            ActiveDirectory_AuthorizedGroups = ProbFile.getProperty("ActiveDirectory_AuthorizedGroups");
            ActiveDirectory_SearchString_AuthorizedGroups_Root = ProbFile.getProperty("ActiveDirectory_SearchString_AuthorizedGroups_Root");
            AD_IpphoneExtField = ProbFile.getProperty("AD_IpphoneExtField");
            AD_MobileField = ProbFile.getProperty("AD_MobileField");
            //initialize the cookie that we pass back in subsequent pages
            cookie = null;
            Control[] ctrls = new Control[]{
                new com.sun.jndi.ldap.ctl.PagedResultsControl(pageSize)
            };
            ctx.setRequestControls(ctrls);
            System.out.println("Domain Name: " + ctx.toString());
            System.out.println("Connection Successful.");
            setCtx_AD(ctx);
        } catch (NamingException nex) {
            throw new Exception("LDAP Connection: FAILED Try Again");
        }

    }

    public ActiveDirectoryConnection() {

        try {

            Properties ProbFile = new Properties();

            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
            ActiveDirectoryIP = ProbFile.getProperty("ActiveDirectoryIP");
            ActiveDirectoryPort = ProbFile.getProperty("ActiveDirectoryPort");
            ActiveDirectory_Domain = ProbFile.getProperty("ActiveDirectory_Domain");
            ActiveDirectory_UserLogginName = ProbFile.getProperty("ActiveDirectory_UserLogginName");
            ActiveDirectory_UserLogginPassword = ProbFile.getProperty("ActiveDirectory_UserLogginPassword");
            ActiveDirectory_AuthorizedGroups = ProbFile.getProperty("ActiveDirectory_AuthorizedGroups");
            ActiveDirectory_SearchString_Users_Root = ProbFile.getProperty("ActiveDirectory_SearchString_Users_Root");
            ActiveDirectory_SearchString_AuthorizedGroups_Root = ProbFile.getProperty("ActiveDirectory_SearchString_AuthorizedGroups_Root");
            AD_IpphoneExtField = ProbFile.getProperty("AD_IpphoneExtField");
            AD_MobileField = ProbFile.getProperty("AD_MobileField");


        } catch (Exception ex) {
            // System.out.println("LDAP Connection: FAILED");
        }
    }

    public ArrayList<User> getUsersInfo(LdapContext ctx) throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> Groupsmemberships = null;
        ArrayList<String> OUsMemberships = null;

        try {

            SearchControls searchCtrls = new SearchControls();
            searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attributes = {
                "distinguishedName", "telephoneNumber", "memberOf", "name", "homephone", "ipphone", "userPrincipalName", "samaccountname", "mobile"
            };
            searchCtrls.setReturningAttributes(attributes);
            String filter = "(&(objectClass=user)(objectCategory=person))";//"(&(objectClass=user)(objectCategory=person))"//"(&(memberOf=CN=Vox,OU=VOX,OU=Member Servers,"+ActiveDirectory_SearchString+")(objectClass=person))"//organizationalPerson/group/organizationalUnit

            Random KeyGenrator = new Random(44005);
            int index = 0;

            String userNUmber = "";
            String userMobileNumber = "";
            // Search for objects using the filter
            do {
                NamingEnumeration values = ctx.search(ActiveDirectory_SearchString_Users_Root, filter, searchCtrls);

                //loop throw result values
                while (values != null && values.hasMoreElements()) {
                    System.out.println("|||||||index is" + index++);

                    SearchResult result = (SearchResult) values.next();
                    Attributes attribs = result.getAttributes();

                    if (AD_IpphoneExtField != null) {

                        if (AD_IpphoneExtField.equals("ipphone")) {
                            if(attribs.get("ipphone") != null){
                            userNUmber = attribs.get("ipphone").toString().substring(9);
                            if (userNUmber.contains("-")) {
                                userNUmber = userNUmber.substring(0, userNUmber.indexOf("-"));
                            }
                        }
                        }
                        if (AD_IpphoneExtField.equals("telephoneNumber")) {
                             if(attribs.get("ipphone") != null){
                            userNUmber = attribs.get("telephoneNumber").toString().substring(17);
                            if (userNUmber.contains("-")) {
                                userNUmber = userNUmber.substring(0, userNUmber.indexOf("-"));
                            }
                             }
                        }
                        System.out.print("User Name:"+attribs.get("name")+"User ipphone :" + userNUmber + "\n");

                        if (!userNUmber.equals("") && !userNUmber.equals(" ") && !userNUmber.equals("  ")) {
                            User UserInfo = new User();
                            System.out.print("User Data Is  :" + result.toString() + "\n");

                            UserInfo.setGuid("" + Math.abs(KeyGenrator.nextInt()));
                            System.out.print("User GUID :" + Math.abs(KeyGenrator.nextInt()) + "\n");
                            System.out.print("User name :" + attribs.get("name").toString().substring(6) + "\n");
                            UserInfo.setName(attribs.get("name").toString().substring(6));

                            UserInfo.setIpPhoneExt(userNUmber);
                            //} else {
                            //  UserInfo.setIpPhoneExt("0000");
                            //}


                            if (AD_MobileField.equals("telephoneNumber")) {
                                if (attribs.get("telephoneNumber") != null) {
                                    System.out.print("User  telephoneNumber:" + attribs.get("telephoneNumber").toString().substring(17) + "\n");
                                    userMobileNumber = attribs.get("telephoneNumber").toString().substring(17);
                                }
                            }
                            if (AD_MobileField.equals("mobile")) {
                                System.out.println("User Mobile:" + attribs.get("mobile").toString().substring(8) + "\n");
                                userMobileNumber = attribs.get("mobile").toString().substring(8);
                            }

                            UserInfo.setTelephonenumber(userMobileNumber);

                            if (attribs.get("memberOf") != null) {
                                String[] Memberships = attribs.get("memberOf").toString().substring(10).split(" CN");
                                Groupsmemberships = new ArrayList<String>();
                                for (int i = 0; i < Memberships.length; i++) {
                                    Groupsmemberships.add(Memberships[i].substring(0, Memberships[i].indexOf(",")).substring(Memberships[i].indexOf("=") + 1));
                                    System.out.print("User memberOf:" + Memberships[i].substring(0, Memberships[i].indexOf(",")).substring(Memberships[i].indexOf("=") + 1) + "\n");
                                    //System.out.print("User memberOf:"+attribs.get("memberOf").toString().substring(10).split(" ")+"\n");
                                }
                                UserInfo.setUserMemberShips(Groupsmemberships);
                            }
                            if (attribs.get("distinguishedName") != null) {
                                String DistengugeParam = attribs.get("distinguishedName").toString().substring(19);
                                System.out.print("User distinguishedName :" + DistengugeParam + "\n");
                                String[] UserOUs = DistengugeParam.split(",");
                                OUsMemberships = new ArrayList<String>();
                                for (int i = 0; i < UserOUs.length; i++) {
                                    String Tag = UserOUs[i].substring(0, UserOUs[i].indexOf("="));
                                    if (Tag.equals("OU")) {

                                        System.out.print("User member Of OU:" + UserOUs[i].substring(UserOUs[i].indexOf("=") + 1) + "\n");
                                        OUsMemberships.add(UserOUs[i].substring(UserOUs[i].indexOf("=") + 1));
                                    }
                                }
                                UserInfo.setUserOU(OUsMemberships);
                            }
                            if (attribs.get("homephone") != null) {
                                System.out.print("User homephone :" + attribs.get("homephone").toString().substring(11) + "\n");
                                UserInfo.setHomePhone(attribs.get("homephone").toString().substring(11));
                            }
                            if (attribs.get("userPrincipalName") != null) {
                                System.out.print("User userPrincipalName :" + attribs.get("userPrincipalName").toString().substring(19) + "\n");
                                UserInfo.setUserDomainEmail(attribs.get("userPrincipalName").toString().substring(19));
                            }
                            if (attribs.get("samaccountname") != null) {
                                System.out.print("User accountname :" + attribs.get("samaccountname").toString().substring(16) + "\n");
                                UserInfo.setAccountName(attribs.get("samaccountname").toString().substring(16));
                            }
                            if (users.size() < 15) {
                                users.add(UserInfo);
                            }

                        }
                    }
                }
                // examine the response controls
                cookie = parseControls(ctx.getResponseControls());
                // pass the cookie back to the server for the next page
                ctx.setRequestControls(new Control[]{
                            new com.sun.jndi.ldap.ctl.PagedResultsControl(pageSize, cookie, Control.CRITICAL)
                        });

            } while ((cookie != null) && (cookie.length != 0));


        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return users;
    }

    static byte[] parseControls(Control[] controls) throws NamingException {

        byte[] cookie = null;

        if (controls != null) {

            for (int i = 0; i < controls.length; i++) {
                if (controls[i] instanceof javax.naming.ldap.PagedResultsResponseControl) {
                    javax.naming.ldap.PagedResultsResponseControl prrc = (javax.naming.ldap.PagedResultsResponseControl) controls[i];
                    cookie = prrc.getCookie();
                    System.out.println(">>Next Page \n");
                }
            }
        }

        return (cookie == null) ? new byte[0] : cookie;
    }

    public Hashtable<String, ArrayList<User>> UserAsignedGroups(ArrayList<String> Group, ArrayList<User> UsersInfo) {
        Hashtable<String, ArrayList<User>> UserDictionary = new Hashtable<String, ArrayList<User>>();

        for (int i = 0; i < Group.size(); i++) {
            ArrayList<User> UsersInGroup = new ArrayList<User>();
            for (int z = 0; z < UsersInfo.size(); z++) {
                if (UsersInfo.get(z).getUserMemberShips() != null) {
                    if (UsersInfo.get(z).getUserMemberShips().contains(Group.get(i))) {
                        UsersInGroup.add(UsersInfo.get(z));
                    }
                }
            }
            if (UsersInGroup.size() != 0) {
                UserDictionary.put(Group.get(i), UsersInGroup);
            }
        }
//        System.out.println("Finish");
        return UserDictionary;
    }

    public Hashtable<String, ArrayList<User>> UserAsignedGOU(ArrayList<String> OUs, ArrayList<User> UsersInfo) {
        Hashtable<String, ArrayList<User>> UserDictionary = new Hashtable<String, ArrayList<User>>();
        ArrayList<User> UsersInGroup;
        for (int i = 0; i < OUs.size(); i++) {
            UsersInGroup = new ArrayList<User>();
            for (int z = 0; z < UsersInfo.size(); z++) {
                if (UsersInfo.get(z).getUserOU() != null) {
                    if (UsersInfo.get(z).getUserOU().contains(OUs.get(i))) {
                        UsersInGroup.add(UsersInfo.get(z));
                    }
                }
            }
            if (UsersInGroup.size() != 0) {
                UserDictionary.put(OUs.get(i), UsersInGroup);
            }

        }
        return UserDictionary;
    }

    public ArrayList<String> GetGroupsorOUsList(LdapContext myContext, String objectClassType) throws IOException {
        ArrayList<String> Entities = new ArrayList<String>();
        SearchControls searchCtrls = new SearchControls();
        searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String[] attributes = {
            "name"
        };
        searchCtrls.setReturningAttributes(attributes);
        String filter = "(objectClass=" + objectClassType + ")";//organizationalPerson/group/organizationalUnit

        try {

            do {
                NamingEnumeration values = myContext.search(ActiveDirectory_SearchString_Users_Root, filter, searchCtrls);//DC=CUAE,

                while (values != null && values.hasMoreElements()) {
                    SearchResult result = (SearchResult) values.next();
                    Attributes attribs = result.getAttributes();
                    Entities.add(attribs.get("name").toString().substring(6));
                    System.out.print("UNIT Name :" + attribs.get("name").toString().substring(6) + "\n");
                }

                // examine the response controls
                cookie = parseControls(myContext.getResponseControls());
                // pass the cookie back to the server for the next page
                myContext.setRequestControls(new Control[]{
                            new com.sun.jndi.ldap.ctl.PagedResultsControl(pageSize, cookie, Control.CRITICAL)
                        });

            } while ((cookie != null) && (cookie.length != 0));
        } catch (NamingException ex) {
            return null;
        }
        return Entities;
    }

    public boolean checkCredntials(String UserName, String Password) {
        LdapContext ctx = null;
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            env.put(Context.SECURITY_PRINCIPAL, UserName);
            env.put(Context.SECURITY_CREDENTIALS, Password);
            env.put(Context.PROVIDER_URL, "ldap://" + ActiveDirectoryIP + ":" + ActiveDirectoryPort);
            ctx = new InitialLdapContext(env, null);
            System.out.println("Domain Name: " + ctx.toString());
            System.out.println("Connection Successful.");
            setCtx_AD(ctx);
            return true;
        } catch (Exception ex) {
            System.out.println("LDAP Connection: FAILED Try Again");
        }
        return false;
    }

    public boolean checkGroup(String userPrincipalName, LdapContext Ctx) {
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> renum = Ctx.search(ActiveDirectory_SearchString_AuthorizedGroups_Root, "(&(userPrincipalName=" + userPrincipalName + ")(objectClass=user))", controls);
            if (!renum.hasMore()) {
                System.out.println("Cannot locate user information for" + userPrincipalName);
                System.exit(1);
            }
            SearchResult result = renum.next();
            List<GrantedAuthority> groups = new ArrayList<GrantedAuthority>();
            Attribute memberOf = result.getAttributes().get("memberOf");
            if (memberOf != null) {// null if this user belongs to no group at all
                for (int i = 0; i < memberOf.size(); i++) {
                    Attributes atts = Ctx.getAttributes(memberOf.get(i).toString(), new String[]{"CN"});
                    Attribute att = atts.get("CN");
                    groups.add(new GrantedAuthorityImpl(att.get().toString()));
                }
                System.out.println();
                System.out.println("User belongs to: ");
                Iterator ig = groups.iterator();
                String[] AuthorizedGroups = ActiveDirectory_AuthorizedGroups.split(",");
                while (ig.hasNext()) {
                    String userGruop = ig.next().toString();
                    // System.out.println("   " + userGruop);

                    for (String group : AuthorizedGroups) {
                        if (group.equals(userGruop)) {
                            return true;
                        }

                    }

                }
            }

        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public boolean checkGroupk(String userName, LdapContext Ctx) {
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> renum = Ctx.search(ActiveDirectory_SearchString_AuthorizedGroups_Root, "(&(samAccountName=" + userName + ")(objectClass=user))", controls);
            if (!renum.hasMore()) {
                System.out.println("Cannot locate user information for" + userName);
                System.exit(1);
            }
            SearchResult result = renum.next();
            List<GrantedAuthority> groups = new ArrayList<GrantedAuthority>();
            Attribute memberOf = result.getAttributes().get("memberOf");
            if (memberOf != null) {// null if this user belongs to no group at all
                for (int i = 0; i < memberOf.size(); i++) {
                    Attributes atts = Ctx.getAttributes(memberOf.get(i).toString(), new String[]{"CN"});
                    Attribute att = atts.get("CN");
                    groups.add(new GrantedAuthorityImpl(att.get().toString()));
                }
                System.out.println();
                System.out.println("User belongs to: ");
                Iterator ig = groups.iterator();
                String[] AuthorizedGroups = ActiveDirectory_AuthorizedGroups.split(",");
                while (ig.hasNext()) {
                    String userGruop = ig.next().toString();
                    // System.out.println("   " + userGruop);
                    for (String group : AuthorizedGroups) {
                        if (group.equals(userGruop)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }
}
