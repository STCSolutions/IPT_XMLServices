/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.VoicePaging.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneText;
import Com.Stcs.IPPhone.Objects.SoftKey;
import Com.Stcs.IPPhoneInfo.IPPhone;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMR
 */
public class ErrorPage extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String thisServer = InetAddress.getLocalHost().getHostAddress();
            int thisPort = request.getServerPort();
            ServletContext sc = getServletConfig().getServletContext();
            String path = sc.getContextPath();
            String url = "http://" + thisServer + ":" + thisPort + path + "/";
            String MainPage = url + "BroadcastMenu";
            String ErrorMessage=request.getParameter("Message");
            Vector softkeys = new Vector();
             softkeys.add(new SoftKey("OK", "1", MainPage));
             softkeys.add(new SoftKey("Exit", "3", "Init:Services"));

                CiscoIPPhoneText text = new CiscoIPPhoneText("Notification !", "Thank You",ErrorMessage , softkeys);
                out.print(text.getTextObject());

        } catch (Exception ex) {
            Logger.getLogger(ErrorPage.class.getName()).log(Level.SEVERE, null, ex);
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
