/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.VoicePaging.web;

import Com.Stcs.IPPhoneInfo.IPPhone;
import com.DB.EmployeOperations;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AMR
 */
public class SnedMessage extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String thisServer = InetAddress.getLocalHost().getHostAddress();
        int thisPort = request.getServerPort();
        ServletContext sc = getServletConfig().getServletContext();
        String path = sc.getContextPath();
        String url = "http://" + thisServer + ":" + thisPort + path + "/";
            HttpSession session = request.getSession(true);
        String MainPage=url+"MainPage";
        String phoneIP = request.getRemoteAddr();
        String UserName = "UnKnown";
        String Ext = IPPhone.getPhoneDN(phoneIP);
        EmployeOperations op = new EmployeOperations();
        UserName = op.getUserName(Ext);
        String Line1="";
        String Line2="";
        String MSG ="";
        
       Line1=request.getParameter("msg1");
       Line2=request.getParameter("msg2");
        if(Line1!=null&&!Line1.equals(""))
   MSG += Line1+" ";
   if(Line2!=null&&!Line2.equals(""))
   MSG+=Line2;

        
op.insertMessageBroadcast(Ext,MSG,UserName);
System.out.println("The Message is"+MSG);
int MessageID=op.getMessageBroadcastID(Ext);
        session.setAttribute("BroadcastMessage",""+MessageID);

        
System.out.println(MainPage+"?type="+URLEncoder.encode("text"));
response.sendRedirect(MainPage+"?type=text");
return;
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SnedMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SnedMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
