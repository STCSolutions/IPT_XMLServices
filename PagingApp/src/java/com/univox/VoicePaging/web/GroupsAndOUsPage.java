/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.VoicePaging.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneMenu;
import Com.Stcs.IPPhone.Objects.MenuItem;
import Com.Stcs.IPPhone.Objects.SoftKey;
import com.DB.EmployeOperations;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMR
 */
public class GroupsAndOUsPage extends HttpServlet {
    private String ActiveDirectoryIP;
    private String ActiveDirectoryPort;
    private String ActiveDirectory_DomainName;
    private String ActiveDirectory_UserLogginName;
    private String ActiveDirectory_UserLogginPassword;
   private String ActiveDirectory_SearchString;
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
            String thisServer = InetAddress.getLocalHost().getHostAddress();
            int thisPort = request.getServerPort();
            ServletContext sc = getServletConfig().getServletContext();
            String path = sc.getContextPath();
            String url = "http://" + thisServer + ":" + thisPort + path + "/";
            String Backurl = url + "BroadcastMenu";
            String PagingProcess = url + "PagingProcess";
            String Flag = request.getParameter("Flag");
            String Prompt = request.getParameter("Message");
            String ErrorPage = url + "ErrorPage";
 try {
            EmployeOperations OP=new EmployeOperations();
            Vector menuItems = new Vector();
            String title = "";
            if(OP.iSEmptyGroups()||OP.iSEmptyOU())
                OP.fillEmptyMyVoxTables();
            if (Flag.equals("OU")) {
                title = "Organization Unit";
                ArrayList<String> OUs = OP.GetOUs();
              for (int i = 0;i<OUs.size(); i++) {
                    String OU = OUs.get(i);
                    menuItems.add(new MenuItem(OU, PagingProcess + "?Flag=OU&Name=" + URLEncoder.encode(OU)));
                }
            } else {
                title = "Groups";
                ArrayList<String> Groups = OP.GetGroups();
                for (int i = 0;i<Groups.size(); i++) {
                    String group = Groups.get(i);
                    menuItems.add(new MenuItem(group, PagingProcess + "?Flag=Group&Name=" + URLEncoder.encode(group)));
                }
            }
            Vector softkeys = new Vector();
            softkeys.add(new SoftKey("Select", "1", "SoftKey:Select"));
            softkeys.add(new SoftKey("Exit", "3", "Init:Services"));
            softkeys.add(new SoftKey("Back", "2", Backurl));
            CiscoIPPhoneMenu menu = new CiscoIPPhoneMenu(title, "Please Select An Opthion", menuItems, softkeys);
            out.print(menu.getMenuObject());
        } catch (Exception ex) {
            response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(ex.getMessage()));
return;
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
