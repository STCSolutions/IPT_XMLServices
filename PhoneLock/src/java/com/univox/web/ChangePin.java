/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneInput;
import Com.Stcs.IPPhone.Objects.InputItem;
import Com.Stcs.IPPhone.Objects.SoftKey;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
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
public class ChangePin extends HttpServlet {
   
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
       // String Backurl = "http://" + thisServer + ":" + thisPort + "/STCS-IPServices/welcome";
        String path = sc.getContextPath();
        String url = "http://" + thisServer + ":" + thisPort + path + "/";
        String CheckPage=url+"CheckNewPin";
        String Backurl = url + "welcome";
        Vector Inputs=new Vector();
        Inputs.add(new InputItem("Ext", "Ext", InputItem.NUMERIC, ""));
        Inputs.add(new InputItem("Current Pin", "CurrentPin", InputItem.DigitPASSWORD, ""));
        Inputs.add(new InputItem("New Pin", "NewPin", InputItem.DigitPASSWORD, ""));
        Vector softkeys=new Vector();
        softkeys.add(new SoftKey("Exit", "4", "Init:Services"));
        softkeys.add(new SoftKey("Submit", "1", "SoftKey:Submit"));
        softkeys.add(new SoftKey("Back", "2", Backurl));
        softkeys.add(new SoftKey("<<", "3", "SoftKey:<<"));
        CiscoIPPhoneInput input=new CiscoIPPhoneInput("ChangePin", "Enter you Current Ext&Pin Then New Pin", CheckPage, Inputs, softkeys);
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
