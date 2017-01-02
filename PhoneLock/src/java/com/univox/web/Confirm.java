/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneText;
import Com.Stcs.IPPhone.Objects.SoftKey;
import UnivoxAXLOperations.CallManagerOperation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Vector;
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
public class Confirm extends HttpServlet {
   
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
        HttpSession session = request.getSession(true);
        int thisPort = request.getServerPort();
        ServletContext sc = getServletConfig().getServletContext();
        String path = sc.getContextPath();
        String IP=request.getRemoteAddr();
        String url = "http://" + thisServer + ":" + thisPort + path + "/";
        String Flag=request.getParameter("Flag");
         String LockPage=url+"Lock";
          Properties ProbFile=new Properties();
        ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        String LockCSS =ProbFile.getProperty("LockCSS");
        String UnLockCSS =ProbFile.getProperty("UnLockCSS");
        System.out.println(LockCSS +" "+UnLockCSS);
        CallManagerOperation ccmOP=new CallManagerOperation();
        //String ext=IPPhone.getPhoneDN(IP);
        String ext=(String)session.getAttribute("Ext");
        System.out.println(ext);
        if(Flag.equals("Lock"))
            ccmOP.changeCSS(ext,"PhoneLock_Css");
        else
            ccmOP.changeCSS(ext,"UNLock_Css");

        Vector softkeys=new Vector();
        softkeys.add(new SoftKey("Back", "1",LockPage));
        softkeys.add(new SoftKey("Exit", "3", "Init:Services"));
        CiscoIPPhoneText text=new CiscoIPPhoneText("Confirmation", "", "Your Request Have Been Send This May Take Few Sec Thanks .",softkeys);

        out.print(text.getTextObject());
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
            Logger.getLogger(Confirm.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Confirm.class.getName()).log(Level.SEVERE, null, ex);
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
