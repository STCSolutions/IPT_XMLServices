/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ADConnector;

import java.util.ArrayList;

/**
 *
 * @author AMR
 */
public class User {

private String Guid;
    private String mail;
    private String telephonenumber;
    private String name;
    private ArrayList<String> UserMemberShips;
    private String ipPhoneExt;
    private String homePhone;
    private String userDomainEmail;
    private String accountName;
    private ArrayList<String> UserOU;

    public ArrayList<String> getUserOU() {
        return UserOU;
    }

    public void setUserOU(ArrayList<String> UserOU) {
        this.UserOU = UserOU;
    }

   


    public String getGuid() {
        return Guid;
    }

    public void setGuid(String Guid) {
        this.Guid = Guid;
    }
    

        public User() {

        setMail("");
        setName("");
        setTelephonenumber("");
        setUserMemberShips(null);
        setIpPhoneExt("");
        setAccountName("");
        setUserDomainEmail("");
        setHomePhone("");
        setUserOU(null);
    
    }



    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getIpPhoneExt() {
        return ipPhoneExt;
    }

    public void setIpPhoneExt(String ipPhoneExt) {
        this.ipPhoneExt = ipPhoneExt;
    }

    public String getUserDomainEmail() {
        return userDomainEmail;
    }

    public void setUserDomainEmail(String userDomainEmail) {
        this.userDomainEmail = userDomainEmail;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getUserMemberShips() {
        return UserMemberShips;
    }

    public void setUserMemberShips(ArrayList<String> UserMemberShips) {
        this.UserMemberShips = UserMemberShips;
    }
}
