/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.Admin.Operations;

import com.ADConnector.ActiveDirectoryConnection;
import com.ADConnector.User;
import com.DB.EmployeOperations;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMR
 */
public class ActiveDirectoryOperations {

    EmployeOperations OP;
    ActiveDirectoryConnection AD;
    String ActiveDirectoryIP = "";
    String ActiveDirectoryPort = "";
    String ActiveDirectory_DomainName = "";
    String ActiveDirectory_UserLogginName = "";
    String ActiveDirectory_UserLogginPassword = "";
    String ActiveDirectory_SearchString = "";

    public ActiveDirectoryOperations() {
        try {
            OP = new EmployeOperations();
            Properties ProbFile = new Properties();
            try {
                ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
            } catch (IOException ex) {
            }
            ActiveDirectoryIP = ProbFile.getProperty("ActiveDirectoryIP");
            ActiveDirectoryPort = ProbFile.getProperty("ActiveDirectoryPort");
            ActiveDirectory_DomainName = ProbFile.getProperty("ActiveDirectory_DomainName");
            ActiveDirectory_UserLogginName = ProbFile.getProperty("ActiveDirectory_UserLogginName");
            ActiveDirectory_UserLogginPassword = ProbFile.getProperty("ActiveDirectory_UserLogginPassword");
            ActiveDirectory_SearchString = ProbFile.getProperty("ActiveDirectory_SearchString");
            AD = new ActiveDirectoryConnection(ActiveDirectoryIP, ActiveDirectoryPort, ActiveDirectory_DomainName, ActiveDirectory_UserLogginName, ActiveDirectory_UserLogginPassword, ActiveDirectory_SearchString);
        } catch (Exception ex) {
            Logger.getLogger(ActiveDirectoryOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void updateMyVoxTables() {
        try {
            Hashtable<String, String> EmployeePin = OP.getEmployeeCredintials();
            ArrayList<String> phones = OP.getUnRegisterdAzanServicePhones();
            ArrayList<User> Users = AD.getUsersInfo(AD.getCtx_AD());
            if (Users.size() != 0) {
                OP.dropPhoneTable();
                OP.dropEmployeeTable();
            }
            int tempIndex = 1;
            for (int i = 0; i < Users.size(); i++) {
                if (EmployeePin.containsKey(Users.get(i).getUserDomainEmail())) {
                    OP.InsertEmploye(tempIndex, Users.get(i).getName(), Users.get(i).getUserDomainEmail(), EmployeePin.get(Users.get(i).getUserDomainEmail()),Users.get(i).getTelephonenumber(),Users.get(i).getMail(),Users.get(i).getHomePhone(),Users.get(i).getAccountName());
                } else {
                    OP.InsertEmploye(tempIndex, Users.get(i).getName(), Users.get(i).getUserDomainEmail(), "123",Users.get(i).getTelephonenumber(),Users.get(i).getMail(),Users.get(i).getHomePhone(),Users.get(i).getAccountName());
                }
                if (phones.contains(Users.get(i).getIpPhoneExt())) {
                    OP.InsertPhone(tempIndex, Users.get(i).getIpPhoneExt(), "false");
                } else {
                    OP.InsertPhone(tempIndex, Users.get(i).getIpPhoneExt(), "true");
                }
                tempIndex++;
            }
        } catch (Exception ex) {
            Logger.getLogger(ActiveDirectoryOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        
    }
}
