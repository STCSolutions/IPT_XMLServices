/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.VoicePaging.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneGraphicFileMenu;
import Com.Stcs.IPPhone.Objects.CiscoIPPhoneText;
import Com.Stcs.IPPhone.Objects.GraphicMenuItem;
import Com.Stcs.IPPhone.Objects.Model.PhoneSeriesModel;
import Com.Stcs.IPPhone.Objects.Point;
import Com.Stcs.IPPhone.Objects.SoftKey;
import Com.Stcs.IPPhoneInfo.IPPhone;
import com.ADConnector.User;
import com.DB.EmployeOperations;
import com.univox.core.text.TextLuncher;
import com.univox.paging.core.LiveMultiCastManager;
import com.univox.paging.core.MulticastManager;
import com.univox.paging.core.PortGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import univoxipext.IPProvider;

/**
 *
 * @author AMR
 */
public class PagingProcess extends HttpServlet {

    
    private String CallManager_ApplicationUser_Credintials;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession(true);
            String thisServer = InetAddress.getLocalHost().getHostAddress();
            int thisPort = request.getServerPort();
            ServletContext sc = getServletConfig().getServletContext();
            String path = sc.getContextPath();
            String url = "http://" + thisServer + ":" + thisPort + path + "/";
            String Backurl = url + "BroadcastMenu";
            String ErrorPage = url + "ErrorPage";
            String Flag = request.getParameter("Flag");
            String Name = request.getParameter("Name");
             try {
                 Properties ProbFile = new Properties();

                ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
            CallManager_ApplicationUser_Credintials=ProbFile.getProperty("CallManager_ApplicationUser_Credintials");
           EmployeOperations OP=new EmployeOperations();
            ArrayList<User> Users = OP.getUsersInfo_DB();
            String ip = request.getRemoteAddr();
            boolean iscolor = IPPhone.isColorPhone(ip);
            ArrayList<User> TempMember = null;
            PhoneSeriesModel phoneModel=IPPhone.getPhoneDetails(ip);
            String ImageName = "";
            User TempUser = null;
            String OUorGroupTempName = "";
            String[] UsersIPs = null;
            String[] MembersExt = null;
            if (Flag.equals("OU")) {
                ArrayList<String> OUs = OP.GetOUs();
                Hashtable<String, ArrayList<User>> UsersOUs = OP.UserAsignedGOU(OUs, Users);
                Enumeration<String> keys = UsersOUs.keys();
                while (keys.hasMoreElements()) {
                    OUorGroupTempName = keys.nextElement();
                    if (OUorGroupTempName.equals(Name)) {
                        TempMember = UsersOUs.get(OUorGroupTempName);
                        MembersExt = new String[TempMember.size()];
                        for (int i = 0; i < TempMember.size(); i++) {
                            TempUser = TempMember.get(i);

                            MembersExt[i] = TempUser.getIpPhoneExt();
                        }
                        if (MembersExt.length != 0) {
                            System.out.println("Exts Are"+MembersExt);
                            UsersIPs = IPProvider.getPhonesIpsByExtens(MembersExt);
                            System.out.println("IPs Are "+UsersIPs);
                        } else {
                            String text = "Sorry There aren't Any Members Assigned With this OU : " + Name + " Please Select Ok to choose Another Group Or OU or Click Exit to End The Application.";
                            response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));
                        }
                    }
                }
            } else {
                ArrayList<String> Groups = OP.GetGroups();
                Hashtable<String, ArrayList<User>> UsersGroups = OP.UserAsignedGroups(Groups, Users);
                Enumeration<String> keys = UsersGroups.keys();
                while (keys.hasMoreElements()) {
                    OUorGroupTempName = keys.nextElement();
                    if (OUorGroupTempName.equals(Name)) {
                        TempMember = UsersGroups.get(OUorGroupTempName);
                        MembersExt = new String[TempMember.size()];
                        for (int i = 0; i < TempMember.size(); i++) {
                            TempUser = TempMember.get(i);
                            MembersExt[i] = TempUser.getIpPhoneExt();
                        }
                        if (MembersExt.length != 0) {
                            UsersIPs = IPProvider.getPhonesIpsByExtens(MembersExt);
                        } else {
                            String text = "Sorry There aren't Any Members Assigned With the Group : '" + Name + "' Please Select Ok to choose Another Group Or OU or Click Exit to End The Application.";
                            response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));
                        }
                    }
                }
            }
            if (UsersIPs != null && UsersIPs.length != 0) {
                Vector softkeys = new Vector();
                softkeys.add(new SoftKey("Back", "1", Backurl));
                softkeys.add(new SoftKey("Exit", "3", "Init:Services"));
                ArrayList<String> list = new ArrayList<String>();
                for (String item : UsersIPs) {
                    if (item != null && !item.equals("")) {
                        list.add(item);
                    }
                }
                UsersIPs = list.toArray(new String[list.size()]);
                if (UsersIPs.length == 1 && UsersIPs[0].equals(ip)) {
                    String text = "You are the only one avilable in this Group or OU Please choose another One";
                    response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));
                } else {
                    String type = (String) session.getAttribute("type");
                    if (type.equals("voice")) {
                        PortGenerator protato = new PortGenerator();
                        int portIN = protato.getFreePort();
                        int portOUT = protato.getFreePort();
                        MulticastManager MCM = new LiveMultiCastManager(portIN, UsersIPs, portOUT, ip);
                        MCM.configure();
                        MCM.startMultiCast();
                        if (iscolor) {
                            
                            ImageName="Paging_"+phoneModel.getPhotoSize()+".png";

                            Vector menuItems = new Vector();
                            menuItems.add(new GraphicMenuItem("n", "", new Point(0, 0), new Point(150, 180)));
                            CiscoIPPhoneGraphicFileMenu menu = new CiscoIPPhoneGraphicFileMenu("Voice Paging", "Hang Up To Finish", url + "images/" + ImageName, menuItems, softkeys);
                            out.print(menu.getGraphicMenuObject());
                        } else {
                            CiscoIPPhoneText text = new CiscoIPPhoneText("Voice Paging", " ", "Your Voice Now is Live,To End This Broadcast Please hang up the phone", softkeys);
                            out.print(text.getTextObject());
                        }
                    } else {
                        String message = (String) session.getAttribute("BroadcastMessage");
                        TextLuncher luncher = new TextLuncher(UsersIPs, CallManager_ApplicationUser_Credintials, ip, message, url + "PushMessagePage");
                        luncher.sendRecieveCommand();
                        /* Push2Phone p2p = new Push2Phone();
                        String[] ReciveCommand = {"RTPRx:Stop", "RTPTx:Stop",url+"PushMessagePage?broadmessage="+URLEncoder.encode(message, "UTF-8")};
                        for (int i = 0; i < UsersIPs.length; i++) {
                        String reciverIP = UsersIPs[i];
                        p2p.push(ReciveCommand, reciverIP,Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), false);
                        Thread.sleep(100);
                        }*/
                        CiscoIPPhoneText text = new CiscoIPPhoneText("Text Paging", "Thanks", "Your Message was Sent Successfuly", softkeys);
                        out.print(text.getTextObject());
                    }
                }
            } else {
                String text = "Call Manager Error :Users Extntions deosn't Exist In CallManagerPlease Check Your Conectivity or ask  your Administrator,Select Ok to choose Another Group or OU or Click Exit to End The Application.";
                response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));
            }

        } catch (Exception ex) {
              response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode("BroadCasting Error: Please Check Your Network Conectivity Then Try Again "));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
