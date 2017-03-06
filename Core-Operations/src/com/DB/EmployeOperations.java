/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DB;

import com.ADConnector.ActiveDirectoryConnection;
import com.ADConnector.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.DB.International.*;

/**
 *
 * @author AMR
 */
public class EmployeOperations extends ConnectionManager {

    public boolean employeeCheck(String AccountName, String Pin)
    {
        String query = "SELECT emp.Pin FROM employee emp where lower(emp.ADUserAcount)=lower('" + AccountName + "')";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            String employeePin = "";
            try
            {
                while ( result.next() )
                {
                    employeePin = result.getString( "Pin" );
                    System.out.println( employeePin );
                }
            }
            catch ( Exception e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }

            if ( employeePin.equals( Pin ) )
            {
                return true;
            }
        }
        return false;
    }

    

   public boolean employeeExtCheck(String Ext, String Pin)
    {
        if ( !Ext.equals( "" ) && !Pin.equals( "" ) )
        {
            String query = "SELECT emp.Pin FROM employee emp inner join phone p where emp.ID=p.employeeID AND p.Extension='" + Ext + "'";
            ResultSet result = getRows( query );
            if ( result != null )
            {
                String employeePin = "";
                
                try
                {
                    while ( result.next() )
                    {
                        employeePin = result.getString( "Pin" );
                        System.out.println( employeePin );
                    }
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        result.close();
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                    closeConnection();
                }
                if ( employeePin.equals( Pin ) )
                {
                    return true;
                }
            }
        }
        return false;
    }
   
   
   public boolean changePin(String Ext, String Pin,String newPin)
   {
       
       if ( !Ext.equals( "" ) && !Pin.equals( "" )&& !newPin.equals( "" ) )
        {
            String query = "SELECT emp.ID FROM employee emp inner join phone p where emp.ID=p.employeeID AND p.Extension='" + Ext + "'";
            ResultSet result = getRows( query );
            if ( result != null )
            {
                String employeeID = "";
                
                try
                {
                    while ( result.next() )
                    {
                        employeeID = result.getString( "ID" );
                       
                    }
                    return add("update employee SET Pin='" + newPin + "' where ID=" + employeeID);
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                    
                }
                finally
                {
                    try
                    {
                        result.close();
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                    closeConnection();
                }
            }
        }
       return false;
   }

public String getUserName(String Ext)
    {
        String employeeName = "Employee";
        String query = "SELECT emp.Name FROM employee emp inner join phone p where emp.ID=p.employeeID AND p.Extension='" + Ext + "'";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    employeeName = result.getString( "Name" );
                    System.out.println( employeeName );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }

        return employeeName;
    }

public String getDomainEmail(String Ext)
    {
        String employeeDomainEmail = "Employee";
        String query = "SELECT emp.ADUserAcount FROM employee emp inner join phone p where emp.ID=p.employeeID AND p.Extension='" + Ext + "'";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    employeeDomainEmail = result.getString( "ADUserAcount" );
                    System.out.println( "User Domain Email For Ext :" + Ext + " Is :" + employeeDomainEmail );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }

        return employeeDomainEmail;
    }

   public ArrayList<TimeSheetRecord> getTimeSheetData()
    {
        ArrayList<TimeSheetRecord> timeSheet = new ArrayList<TimeSheetRecord>();
        String query = "SELECT * FROM timesheet t";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            TimeSheetRecord record;
            try
            {

                while ( result.next() )
                {
                    record = new TimeSheetRecord();
                    if ( result.getString( "EmployeeName" ) != null )
                    {
                        record.setEmployeeName( result.getString( "EmployeeName" ) );
                    }
                    if ( result.getString( "EmployeeExt" ) != null )
                    {
                        record.setEmployeeExt( result.getString( "EmployeeExt" ) );
                    }
                    if ( result.getString( "EmployeePhoneMac" ) != null )
                    {
                        record.setEmployeePhoneMac( result.getString( "EmployeePhoneMac" ) );
                    }
                    if ( result.getString( "SignIn" ) != null )
                    {
                        record.setSignIn( result.getString( "SignIn" ) );
                    }
                    if ( result.getString( "SignOut" ) != null )
                    {
                        record.setSignOut( result.getString( "SignOut" ) );
                    }
                    timeSheet.add( record );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }

        return timeSheet;
    }

    public boolean SignIn(String Name, String PhoneExt, String PhoneMac) {

        Date d = new Date();  //get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String SignINDate = dateFormat.format(d);

        String query = "INSERT INTO timesheet (EmployeeName,EmployeeExt,SignIn,EmployeePhoneMac) VALUES('" + Name + "','" + PhoneExt + "','" + SignINDate + "','" + PhoneMac + "')";
        //System.out.println("ex:  "+single);
        return add(query);
    }

    public boolean UpdatePerosnalContactRecord(String Name, String Company, String OfficeNumber, String Mobile, String AcountUser) {


        String query = "UPDATE  personalcontact  SET OfficeNumber='" + OfficeNumber + "',Mobile='" + Mobile + "' WHERE  Name='" + Name + "' AND Company='" + Company + "'AND AcountUser='" + AcountUser + "'";
        //System.out.println("ex:  "+single);
        return add(query);
    }

    public boolean DeletePerosnalContactRecord(String Name, String Company, String AcountUser) {


        String query = "DELETE FROM personalcontact  WHERE  Name='" + Name + "' AND Company='" + Company + "'AND AcountUser='" + AcountUser + "'";
        //System.out.println("ex:  "+single);
        return add(query);
    }

    public boolean AddContactRecord(String Name, String Company, String OfficeNumber, String Mobile, String AcountUser) {


        String query = "INSERT INTO personalcontact (Name,Company,OfficeNumber,Mobile,AcountUser) VALUES('" + Name + "','" + Company + "','" + OfficeNumber + "','" + Mobile + "','" + AcountUser + "')";
        //System.out.println("ex:  "+single);
        return add(query);
    }

public ArrayList<PersonalContact> getAccountPersonalContacts(String AcountUser)
    {
        ArrayList<PersonalContact> PersonalContactsList = new ArrayList<PersonalContact>();
        String PersonalQuery = "Select Name,Company,OfficeNumber,Mobile from personalcontact where AcountUser ='" + AcountUser + "'";
        ResultSet result = getRows( PersonalQuery );
        try
        {
            PersonalContact perosnalContact;
            while ( result.next() )
            {
                perosnalContact = new PersonalContact();
                if ( result.getString( "Name" ) != null )
                {
                    perosnalContact.setName( result.getString( "Name" ) );
                }
                if ( result.getString( "Company" ) != null )
                {
                    perosnalContact.setCompany( result.getString( "Company" ) );
                }
                if ( result.getString( "OfficeNumber" ) != null )
                {
                    perosnalContact.setOfficeNumber( result.getString( "OfficeNumber" ) );
                }
                if ( result.getString( "Mobile" ) != null )
                {
                    perosnalContact.setMobile( result.getString( "Mobile" ) );
                }
                PersonalContactsList.add( perosnalContact );
            }

        }
        catch ( SQLException ex )
        {
            Logger.getLogger( EmployeOperations.class.getName() ).log( Level.SEVERE, null, ex );
        }
        finally
        {
            try
            {
                result.close();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            closeConnection();
        }
        return PersonalContactsList;
    }

    
    public boolean SignOut2(String Name) {
         try
        {
            String SignINProcessID = "";
            int max = -1;
            String PIDquery = "Select ID from timesheet where EmployeeName ='" + Name + "' and signOut IS NULL";
            ResultSet rs = getRows( PIDquery );
            while ( rs.next() )
            {
                SignINProcessID = rs.getString( "ID" ); //get index of in process
                if ( Integer.parseInt( SignINProcessID ) > max )
                {
                    max = Integer.parseInt( SignINProcessID );
                }
            }
            Date d = new Date(); //get current date and time
            DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String date = dateFormat.format( d );
            String updateRecord = "UPDATE timesheet SET SignOut='" + date + "' WHERE ID = " + max;
            add( updateRecord );
            return true;
        }
        catch ( SQLException ex )
        {
            Logger.getLogger( EmployeOperations.class.getName() ).log( Level.SEVERE, null, ex );
        }
        finally
        {
            closeConnection();
        }
        return false;
 
    }

    public ArrayList<String> GetExtens() {
        ArrayList<String> employeeExt = new ArrayList<String>();
        String query = "SELECT distinct phn.Extension FROM phone phn";
        ResultSet result = getRows(query);
        if (result != null) {
            try {

                while (result.next()) {
                    employeeExt.add(result.getString("Extension"));
                    System.out.println(result.getString("Extension"));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                try{
                    result.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return employeeExt;
    }
    ///////////Admin/////////////
public Hashtable<String, String> getEmployeeCredintials()
    {
        Hashtable<String, String> employeeCredintials = new Hashtable<String, String>();
        String query = "SELECT emp.ADUserAcount,emp.Pin FROM employee emp where emp.Pin!=123";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    employeeCredintials.put( result.getString( "ADUserAcount" ), result.getString( "Pin" ) );
                    System.out.println( result.getString( "ADUserAcount" ) + " : " + result.getString( "Pin" ) );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                closeConnection();
            }
        }
        return employeeCredintials;
    }

public ArrayList<String> getExtentions()
    {
        String query = "SELECT Ph.Extension FROM phone Ph where Ph.RigesterForAzan='true'";
        ResultSet result = getRows( query );
        ArrayList<String> Exts = new ArrayList<String>();
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    Exts.add( result.getString( "Extension" ) );
                    System.out.println( result.getString( "Extension" ) );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return Exts;
    }


public ArrayList<String> getUnRegisterdAzanServicePhones()
    {
        ArrayList<String> Phones = new ArrayList<String>();
        String query = "SELECT distinct p.Extension FROM phone p where p.RigesterForAzan!='true'";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    Phones.add( result.getString( "Extension" ) );
                    System.out.println( result.getString( "Extension" ) );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return Phones;
    }


    public boolean InsertPhone(int employeeID, String Extension, String RigesterForAzan) {
        String query = "INSERT INTO phone (employeeID,Extension,RigesterForAzan) VALUES(" + employeeID + ",'" + Extension + "','" + RigesterForAzan + "')";
        //System.out.println("ex:  "+single);
        return add(query);
    }

    public void FillIpExtTable(Hashtable<String, String> IpExtMap) {
        String query = "";
        Enumeration<String> keys = IpExtMap.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            query = "INSERT INTO ipext (EXT,IP) VALUES(" + key + ",'" + IpExtMap.get(key) + "')";
            add(query);
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(EmployeOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean InsertEmploye(int ID, String Name, String ADUserAcount, String Pin, String MobileNumber, String UserEmail, String HomeNumber, String AccountName) {


        String query = "INSERT INTO employee (ID,Name,ADUserAcount,Pin,MobileNumber,UserEmail,HomeNumber,AccountName) VALUES(" + ID + ",\"" + Name + "\",\"" + ADUserAcount + "\",'" + Pin + "','" + MobileNumber + "','" + UserEmail + "','" + HomeNumber + "','" + AccountName + "')";
        //System.out.println("ex:  "+single);
        return add(query);
    }

    public boolean iSEmpytyEmployee()
    {
        String query = "SELECT COUNT(*) FROM employee emp";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            int rowsCount = -1;
            try
            {

                while ( result.next() )
                {
                    rowsCount = result.getInt( 1 );
                    System.out.println( rowsCount );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }

            if ( rowsCount == 0 )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }


public boolean iSEmpytyPhone()
    {
        String query = "SELECT COUNT(*) FROM phone phn";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            int rowsCount = -1;
            try
            {
                while ( result.next() )
                {
                    rowsCount = result.getInt( 1 );
                    System.out.println( rowsCount );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }

            if ( rowsCount == 0 )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }

    public void dropPhoneTable() {
        String query = "DELETE FROM phone";

        deleteRow(query);
    }

    public void dropGroupsTable() {
        String query = "DELETE FROM groups";

        deleteRow(query);
    }

    public void dropOUTable() {
        String query = "DELETE FROM organizationunits";

        deleteRow(query);
    }

    public void dropEmployeeTable() {
        String query = "DELETE FROM employee";

        deleteRow(query);
    }

    public void dropIpExtTable() {
        String query = "DELETE FROM ipext";

        deleteRow(query);
    }

public Hashtable<String, String> getBroadcastInTime()
    {
        String Recipient = "";
        String WaveFileName = "";
        Hashtable<String, String> data = new Hashtable<String, String>();
        String query = "SELECT * FROM schedulebroadcast s where BroadCastDate BETWEEN TIMESTAMPADD(minute,-5,now())  AND now()";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    Recipient = result.getString( "RecipientGroup" );
                    WaveFileName = result.getString( "WaveFileName" );
                    data.put( WaveFileName, Recipient );
                    System.out.println( Recipient );
                    System.out.println( WaveFileName );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return data;
    }
    /////////////////
    public boolean AddSceduleBroadcastItem(String BroadCastDate, String WaveFileName, String[] extensArr) {
        String RecipientGroup = "";
        for (int i = 0; i < extensArr.length; i++) {
            RecipientGroup += extensArr[i];
            if (i != extensArr.length - 1) {
                RecipientGroup += ",";
            }
        }
        String query = "INSERT INTO schedulebroadcast (BroadCastDate,WaveFileName,RecipientGroup) VALUES('" + BroadCastDate + "','" + WaveFileName + "','" + RecipientGroup + "')";
        return add(query);
    }

    public boolean insertSMSHistory(String employeeName, String smsTextStr, String recipientNumber) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        String smsDate = dateFormat.format(date);

        String query = "INSERT INTO smshistory (smsDate,employeeName,smsTextStr,recipientNumber) VALUES('" + smsDate + "',\"" + employeeName + "\",'" + smsTextStr + "','" + recipientNumber + "')";
        return add(query);
    }

    public boolean RigisterAzan(String Ext, String Flag) {
        String query = "update phone SET RigesterForAzan='" + Flag + "' where Extension=" + Ext;
        return add(query);
    }

    public boolean ChangePassword(String ADUserAcount, String Pin) {
        String query = "update employee emp SET emp.Pin=" + Pin + " where lower(emp.ADUserAcount)=lower('" + ADUserAcount + "')";
        return add(query);
    }
    ///////////////////////Paging//////////////////////

    public boolean insertMessageBroadcast(String SenderExt, String Message, String UserName) {


        String query = "INSERT INTO textbroadcast (SenderExt,Message,UserName) VALUES('" + SenderExt + "','" + Message + "',\"" + UserName + "\")";
        return add(query);

    }

public int getMessageBroadcastID(String Ext)
    {
        int MessageID = 0;
        String query = "SELECT max(t.ID) as IDmx FROM textbroadcast t where SenderExt='" + Ext + "'";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    MessageID = result.getInt( "IDmx" );
                    System.out.println( "Message ID Is :" + MessageID );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return MessageID;
    }

    public BroadcastMessageItem getMessageByID(String ID)
    {
        String MessageString = "Message Is Missing Sorry";
        String SenderExt = "00";
        String UserName = "UnKnown";
        BroadcastMessageItem meesage = new BroadcastMessageItem();
        String query = "SELECT * FROM textbroadcast t where t.ID=" + ID;
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    if ( result.getString( "Message" ) != null )
                    {
                        meesage.setMessage( result.getString( "Message" ) );
                    }
                    if ( result.getString( "UserName" ) != null )
                    {
                        meesage.setUserName( result.getString( "UserName" ) );
                    }
                    if ( result.getString( "SenderExt" ) != null )
                    {
                        meesage.setSenderExt( result.getString( "SenderExt" ) );
                    }
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return meesage;
    }
    //////////////IP&EXT/////////////////////
public String getIPbyEXT(String Ext)
    {
        String ip = "";
        String query = "SELECT iptable.IP FROM ipext iptable where iptable.EXT ='" + Ext + "'";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    ip = result.getString( "IP" );
                }
                return ip;
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return ip;
    }

    public ArrayList<String> getIPsbyExts(String[] Exts) {
        String ExtString;
        ArrayList<String> IPList = new ArrayList<String>();
        if (Exts.length == 0) {
            ExtString = "''";
        } else {
            StringBuffer sb = new StringBuffer();
//sb.append("'");
            sb.append(Exts[0]);
//sb.append("'");
            for (int i = 1; i < Exts.length; i++) {
                sb.append(",");
//sb.append("'");
                sb.append(Exts[i]);
//sb.append("'");
            }
            ExtString = sb.toString();
            System.out.println(ExtString);
        }
        String query = "SELECT iptable.IP FROM ipext iptable where iptable.EXT IN (" + ExtString + ")";
        ResultSet result = getRows(query);
        if (result != null)
            {
            try
            {
                while ( result.next() )
                {
                    IPList.add( result.getString( "IP" ) );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return IPList;
    }

    public boolean iSEmpytyIpExt()
    {
        String query = "SELECT COUNT(*) FROM ipext ie";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            int rowsCount = -1;
            try
            {
                while ( result.next() )
                {
                    rowsCount = result.getInt( 1 );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }

            if ( rowsCount == 0 )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }

////////////Active Directory Operations/////////////
    public boolean InsertGroup(int employeeID, String GroupName) {
        String query = "INSERT INTO groups (employeeID,GroupName) VALUES(" + employeeID + ",\"" + GroupName + "\")";
        //System.out.println("ex:  "+single);
        return add(query);
    }

    public boolean InsertOU(int employeeID, String OuName) {
        String query = "INSERT INTO organizationunits (employeeID,OuName) VALUES(" + employeeID + ",\"" + OuName + "\")";
        //System.out.println("ex:  "+single);
        return add(query);
    }

public ArrayList<String> GetGroups()
    {
        ArrayList<String> Groups = new ArrayList<String>();
        String query = "SELECT distinct g.GroupName FROM groups g";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    Groups.add( result.getString( "GroupName" ) );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return Groups;

    }

    public ArrayList<String> GetOUs()
    {
        ArrayList<String> OUs = new ArrayList<String>();
        String query = "SELECT distinct g.OuName FROM organizationunits g";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            try
            {
                while ( result.next() )
                {
                    OUs.add( result.getString( "OuName" ) );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return OUs;
    }

public ArrayList<User> getUsersInfo_DB() {
        ArrayList<User> users=null;
        if(iSEmpytyEmployee()||iSEmpytyPhone()||iSEmptyOU()||iSEmptyGroups())
        {
            dropPhoneTable();
dropOUTable();
dropGroupsTable();
dropIpExtTable();
dropEmployeeTable();
fillEmptyMyVoxTables();
        }
        users = new ArrayList<User>();
        ArrayList<Integer> UserIDs = new ArrayList<Integer>();
        String query = "SELECT emp.ID,emp.Name,emp.ADUserAcount,emp.MobileNumber,emp.UserEmail,emp.HomeNumber,emp.AccountName,p.Extension FROM employee emp inner join phone p where emp.ID=p.employeeID";
        ResultSet result = getRows(query);
        User user;
        Random KeyGenrator = new Random(44005);

        if (result != null) {
            try {
                while (result.next()) {
                    user = new User();
                    UserIDs.add(result.getInt("ID"));
                    user.setGuid("" + Math.abs(KeyGenrator.nextInt()));
                    if (result.getString("Name") != null) {
                        user.setName(result.getString("Name"));
                    }
                    if (result.getString("ADUserAcount") != null) {
                        user.setUserDomainEmail(result.getString("ADUserAcount"));
                    }
                    if (result.getString("MobileNumber") != null) {
                        user.setTelephonenumber(result.getString("MobileNumber"));
                    }
                    if (result.getString("UserEmail") != null) {
                        user.setMail(result.getString("UserEmail"));
                    }
                    if (result.getString("HomeNumber") != null) {
                        user.setHomePhone(result.getString("HomeNumber"));
                    }
                    if (result.getString("AccountName") != null) {
                        user.setAccountName(result.getString("AccountName"));
                    }
                    if (result.getString("Extension") != null) {
                        user.setIpPhoneExt(result.getString("Extension"));
                    }

                    users.add(user);
                }
                for (int i = 0; i < UserIDs.size(); i++) {
                    ArrayList<String> UserGroups = GetUserGroups(UserIDs.get(i));
                    ArrayList<String> UserOUs = GetUserOUs(UserIDs.get(i));

                    if (UserGroups != null) {
                        users.get(i).setUserMemberShips(UserGroups);
                    }

                    if (UserOUs != null) {
                        users.get(i).setUserOU(UserOUs);
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return users;
    }

    public ArrayList<String> GetUserOUs(int UserID) {
        ArrayList<String> OUs = null;
        String query = "SELECT distinct g.OuName FROM organizationunits g where employeeID=" + UserID + "";
        ResultSet result = getRows(query);
        if (result != null) {
            try {
                OUs = new ArrayList<String>();
                while (result.next()) {
                    OUs.add(result.getString("OuName"));
                    System.out.println(result.getString("OuName"));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return OUs;
    }

    public ArrayList<String> GetUserGroups(int UserID) {
        ArrayList<String> Groups = null;
        String query = "SELECT distinct g.GroupName FROM groups g where employeeID=" + UserID + "";
        ResultSet result = getRows(query);
        if (result != null) {
            try {
                Groups = new ArrayList<String>();
                while (result.next()) {
                    Groups.add(result.getString("GroupName"));
                    System.out.println(result.getString("GroupName"));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }
        }
        return Groups;
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

    public void fillEmptyMyVoxTables() {
        try {
            String ActiveDirectoryIP = "";
            String ActiveDirectoryPort = "";
            String ActiveDirectory_DomainName = "";
            String ActiveDirectory_UserLogginName = "";
            String ActiveDirectory_UserLogginPassword = "";
            String ActiveDirectory_SearchString = "";

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
            ActiveDirectoryConnection AD = new ActiveDirectoryConnection(ActiveDirectoryIP, ActiveDirectoryPort, ActiveDirectory_DomainName, ActiveDirectory_UserLogginName, ActiveDirectory_UserLogginPassword, ActiveDirectory_SearchString);
            ArrayList<User> Users = AD.getUsersInfo(AD.getCtx_AD());
            dropGroupsTable();
            dropOUTable();
            dropPhoneTable();
            dropEmployeeTable();
            int tempIndex = 1;
            for (int i = 0; i < Users.size(); i++) {
                if(Users.get(i).getIpPhoneExt().equals("25886")||Users.get(i).getAccountName().equals("massimi")||Users.get(i).getUserDomainEmail().equals("nhussein@rmh.med.local")||Users.get(i).getUserDomainEmail().equals("salnukaih@rmh.med.sa"))
                {
                    int x=0;
                    System.out.println("Tell Me"+x);
                }
                InsertEmploye(tempIndex, Users.get(i).getName(), Users.get(i).getUserDomainEmail(), "123", Users.get(i).getTelephonenumber(), Users.get(i).getMail(), Users.get(i).getHomePhone(), Users.get(i).getAccountName());
                if (Users.get(i).getUserMemberShips() != null) {
                    for (int x = 0; x < Users.get(i).getUserMemberShips().size(); x++) {
                        InsertGroup(tempIndex, Users.get(i).getUserMemberShips().get(x));
                    }
                }
                Thread.sleep(30);
                if (Users.get(i).getUserOU() != null) {
                    for (int z = 0; z < Users.get(i).getUserOU().size(); z++) {
                        InsertOU(tempIndex, Users.get(i).getUserOU().get(z));
                    }
                }

                InsertPhone(tempIndex, Users.get(i).getIpPhoneExt(), "true");
                Thread.sleep(30);
                tempIndex++;
            }


            System.out.println("Finished Table Fill");
        } catch (Exception ex) {
            System.out.println("Error That :"+ex.getMessage()+"            "+ex.getStackTrace());
        }
    }



     public void updateMyVoxTables()
    {
        try {

            String ActiveDirectoryIP = "";
            String ActiveDirectoryPort = "";
            String ActiveDirectory_DomainName = "";
            String ActiveDirectory_UserLogginName = "";
            String ActiveDirectory_UserLogginPassword = "";
            String ActiveDirectory_SearchString = "";

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
            ActiveDirectoryConnection AD = new ActiveDirectoryConnection(ActiveDirectoryIP, ActiveDirectoryPort, ActiveDirectory_DomainName, ActiveDirectory_UserLogginName, ActiveDirectory_UserLogginPassword, ActiveDirectory_SearchString);
            Hashtable<String, String> EmployeePin = getEmployeeCredintials();
            ArrayList<String> phones = getUnRegisterdAzanServicePhones();
            ArrayList<User> Users = AD.getUsersInfo(AD.getCtx_AD());
            if (Users.size() != 0) {
                dropGroupsTable();
                dropOUTable();
                dropPhoneTable();
                dropEmployeeTable();
            }
            int tempIndex = 1;
            for (int i = 0; i < Users.size(); i++) {
                if (EmployeePin.containsKey(Users.get(i).getUserDomainEmail())) {
                    InsertEmploye(tempIndex, Users.get(i).getName(), Users.get(i).getUserDomainEmail(), EmployeePin.get(Users.get(i).getUserDomainEmail()),Users.get(i).getTelephonenumber(),Users.get(i).getMail(),Users.get(i).getHomePhone(),Users.get(i).getAccountName());
                } else {
                    InsertEmploye(tempIndex, Users.get(i).getName(), Users.get(i).getUserDomainEmail(), "123",Users.get(i).getTelephonenumber(),Users.get(i).getMail(),Users.get(i).getHomePhone(),Users.get(i).getAccountName());
                }
                ///User Groups
                if (Users.get(i).getUserMemberShips() != null) {
                    for (int x = 0; x < Users.get(i).getUserMemberShips().size(); x++) {
                        InsertGroup(tempIndex, Users.get(i).getUserMemberShips().get(x));
                    }
                }
Thread.sleep(30);
                if (phones.contains(Users.get(i).getIpPhoneExt())) {
                    InsertPhone(tempIndex, Users.get(i).getIpPhoneExt(), "false");
                } else {
                    InsertPhone(tempIndex, Users.get(i).getIpPhoneExt(), "true");
                }
                ////User Ous
                if (Users.get(i).getUserOU() != null) {
                    for (int z = 0; z < Users.get(i).getUserOU().size(); z++) {
                        InsertOU(tempIndex, Users.get(i).getUserOU().get(z));
                    }
                }
Thread.sleep(30);
                tempIndex++;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

     public boolean iSEmptyGroups()
    {
        String query = "SELECT COUNT(*) FROM groups G";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            int rowsCount = -1;
            try
            {
                while ( result.next() )
                {
                    rowsCount = result.getInt( 1 );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }

            if ( rowsCount == 0 )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }

 public boolean iSEmptyOU()
    {
        String query = "SELECT COUNT(*) FROM organizationunits O";
        ResultSet result = getRows( query );
        if ( result != null )
        {
            int rowsCount = -1;
            try
            {

                while ( result.next() )
                {
                    rowsCount = result.getInt( 1 );
                    System.out.println( rowsCount );
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    result.close();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
                closeConnection();
            }

            if ( rowsCount == 0 )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }


////////////////////////////////////////////////////
    /////////////////////////////////////
    public static void main(String[] args) {
        EmployeOperations op = new EmployeOperations();
    System.out.println(op.employeeExtCheck("6013", "222"));
     //System.out.println(op.changePin("6013", "123","222"));
    //   op.fillEmptyMyVoxTables();
        //op.updateMyVoxTables();
//        op.dropGroupsTable();
//        op.dropOUTable();
//        op.dropPhoneTable();
//        op.dropEmployeeTable();
      //  ArrayList<User> users = op.getUsersInfo_DB();
      //  System.out.println("d");
        // boolean result= op.employeeCheck("Amr@nivox.com", "124");
//op.GetUserOUs(1);
//System.out.println(AttWelcomeMessage);
//op.GetUserGroups(1);

//     op.updateMyVoxTables();
       //op.RigisterAzan("1002","false");
        // op.insertSMSHistory("Amr", "Hii", "0102909291");
        // op.AddSceduleBroadcastItem("2010/09/27 16:50","WavFile-2069076184.wav",new String[]{"2009","2013"});
        //op.ChangePassword("ayman@univox.Com", "12");
        //System.out.println(result);
        //  ArrayList<PersonalContact> lis=   op.getAccountPersonalContacts("amr@univox.com");
//op.GetGroups();
        //    op.UpdatePerosnalContactRecord("Razaz", "SBM", "000000", "010101010", "amr@univox.com");
        //  op.DeletePerosnalContactRecord("razaz", "sBm", "amr@univox.com");
    }
}
