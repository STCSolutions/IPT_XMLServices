/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.web;



import com.DB.EmployeOperations;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
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
public class UserCheck extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String thisServer = InetAddress.getLocalHost().getHostAddress();
        int thisPort = request.getServerPort();
        ServletContext sc = getServletConfig().getServletContext();
        String path = sc.getContextPath();
        String url = "http://" + thisServer + ":" + thisPort + path + "/";
       
        String LockPage = url + "Lock";
        String ErrorPage = url + "ErrorPage";
        String ext = "";
        String pin = "";
                 String CredntialsError="";
        EmployeOperations op=new EmployeOperations();
        if (request.getParameter("Ext") != null && !request.getParameter("Ext").equals("")) {
            ext = (String) request.getParameter("Ext");
        }
        else {
          CredntialsError = "User Name or Password Can't Be Empty";
        }
        if (request.getParameter("Pin") != null && !request.getParameter("Pin").equals(""))
        {
            pin = (String) request.getParameter("Pin");
        }
        else {
           CredntialsError = "User Name or Password Can't Be Empty";
        }
         if(ext.equals("")||pin.equals(""))
         {
        response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(CredntialsError));
        System.out.println("Ext/PIn are Empty");
         }
          try {
            //  String DomainEmail=op.getDomainEmail(ext);
          //    System.out.println("Domain Email For Ext"+ext+" Is "+DomainEmail);
          
            EmployeOperations Op = new EmployeOperations();
            boolean ISAuthorized = Op.employeeExtCheck(ext, pin);
            if (ISAuthorized) {
            session.setAttribute("Ext",ext);
            response.sendRedirect(LockPage);

            } else {

            String text = "Please Check your Credntials";
            response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));
            }



          /*  ActiveDirectoryConnection AD = new ActiveDirectoryConnection();//aabdelmonsif
            if (AD.checkCredntials(DomainEmail, pin)) {
                 session.setAttribute("Ext",ext);
                     System.out.println(DomainEmail+" Is Autorized !");
                response.sendRedirect(LockPage);
            } else {
               String text = "Please Check your Credntials,or Ask your Administrator to make sure That Active Directory is Reachable from your Phone";
                  response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));

            }*/
        } catch (Exception ex) {
            System.out.println("Error In Connection" + ex.getMessage());
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
