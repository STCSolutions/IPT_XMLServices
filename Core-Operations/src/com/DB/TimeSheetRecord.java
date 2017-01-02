/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DB;

/**
 *
 * @author AMR
 */
public class TimeSheetRecord {

    public TimeSheetRecord() {
    setEmployeeExt("");
    setEmployeeName("");
    setSignIn("");
    setSignOut("");
    setEmployeePhoneMac("");
    }

    private String EmployeeName;

    public String getEmployeeExt() {
        return EmployeeExt;
    }

    public void setEmployeeExt(String EmployeeExt) {
        this.EmployeeExt = EmployeeExt;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public String getEmployeePhoneMac() {
        return EmployeePhoneMac;
    }

    public void setEmployeePhoneMac(String EmployeePhoneMac) {
        this.EmployeePhoneMac = EmployeePhoneMac;
    }

    public String getSignIn() {
        return SignIn;
    }

    public void setSignIn(String SignIn) {
        this.SignIn = SignIn;
    }

    public String getSignOut() {
        return SignOut;
    }

    public void setSignOut(String SignOut) {
        this.SignOut = SignOut;
    }
    private String EmployeeExt;
    private String EmployeePhoneMac;
    private String SignIn;

    private String SignOut;

}
