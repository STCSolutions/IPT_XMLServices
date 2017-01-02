/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DB;

/**
 *
 * @author AMR
 */
public class PersonalContact {
    private String name;

    public PersonalContact() {
        setAccountName("");
        setCompany("");
        setMobile("");
        setName("");
        setOfficeNumber("");
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String Company) {
        this.company = Company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String Mobile) {
        this.mobile = Mobile;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String OfficeNumber) {
        this.officeNumber = OfficeNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String accountName;
      private String mobile;
      private String officeNumber;
      private String company;



}
