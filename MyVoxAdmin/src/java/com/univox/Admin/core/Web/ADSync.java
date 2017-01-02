/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.Admin.core.Web;

import com.univox.Admin.Operations.AdminLoader;
import com.univox.Admin.Operations.IPPhoneEXT;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMR
 */
public class ADSync extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ADSync</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ADSync at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    }

    @Override
    public void init() throws ServletException {
      System.out.println("|||*||*|||*|||*|||*|||*|||*|||");
            System.out.println("Admin Loader Task to fill tabels Was Fierd in" + new Date().toString());
            System.out.println("|||*||*|||*|||*|||*|||*|||*|||");
            AdminLoader Loader=new AdminLoader();

            System.out.println("|||*||*|||*  Sleep For 20 Second Then Fire IP EXT Loader  *|||*|||*|||");
       
            System.out.println("|||*||*|||*|||*|||*|||*|||*|||");
            System.out.println("IPExt Loader Task to fill tabels Was Fierd in" + new Date().toString());
            System.out.println("|||*||*|||*|||*|||*|||*|||*|||");
            IPPhoneEXT IPExtLoader=new IPPhoneEXT();

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
