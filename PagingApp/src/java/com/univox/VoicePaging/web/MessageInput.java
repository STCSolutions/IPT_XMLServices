/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.VoicePaging.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneInput;
import Com.Stcs.IPPhone.Objects.InputItem;
import Com.Stcs.IPPhone.Objects.SoftKey;
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
public class MessageInput extends HttpServlet {

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
        String SendMessage = url + "SnedMessage";
        String Backurl = url + "BroadcastMenu";

        Vector Inputs = new Vector();
        Inputs.add(new InputItem("", "msg1", InputItem.ASCII, ""));
        Inputs.add(new InputItem("", "msg2", InputItem.ASCII, ""));

        Vector softkeys = new Vector();
        softkeys.add(new SoftKey("Exit", "4", "Init:Services"));
        softkeys.add(new SoftKey("Finish", "1", "SoftKey:Submit"));
        softkeys.add(new SoftKey("Back", "2", Backurl));
        softkeys.add(new SoftKey("<<", "3", "SoftKey:<<"));
        CiscoIPPhoneInput input = new CiscoIPPhoneInput("Enter YOur Message", "Please Enter your Message Here ", SendMessage, Inputs, softkeys);
        out.print(input.getInputObject());
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
            Logger.getLogger(MessageInput.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MessageInput.class.getName()).log(Level.SEVERE, null, ex);
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
