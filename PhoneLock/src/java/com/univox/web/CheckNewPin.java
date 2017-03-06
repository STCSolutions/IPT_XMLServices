/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.web;



import Com.Stcs.IPPhone.Objects.CiscoIPPhoneText;
import Com.Stcs.IPPhone.Objects.SoftKey;
import com.DB.EmployeOperations;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.Vector;
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
public class CheckNewPin extends HttpServlet {

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
       
        String Welcome = url + "welcome";
        String ErrorPage = url + "ErrorPage";
        String ext = "";
        String pin = "";
        String newPin="";
                 String CredntialsError="";
        EmployeOperations op=new EmployeOperations();
        if (request.getParameter("Ext") != null && !request.getParameter("Ext").equals("")) {
            ext = (String) request.getParameter("Ext");
        }
        else {
          CredntialsError = "Ext ,Current Pin and New Pin Can't Be Empty";
        }
        if (request.getParameter("CurrentPin") != null && !request.getParameter("CurrentPin").equals(""))
        {
            pin = (String) request.getParameter("CurrentPin");
        }
        else {
           CredntialsError = "Ext ,Current Pin and New Pin Can't Be Empty";
        }
        if (request.getParameter("NewPin") != null && !request.getParameter("NewPin").equals(""))
        {
            newPin = (String) request.getParameter("NewPin");
        }
        else {
           CredntialsError = "Ext ,Current Pin and New Pin Can't Be Empty";
        }
         if(ext.equals("")||pin.equals("")||newPin.equals(""))
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
           
                
                boolean pinChanged = Op.changePin(ext, pin,newPin);
            if (pinChanged) {
           
                Vector softkeys=new Vector();
                softkeys.add(new SoftKey("Done", "1",Welcome));
                softkeys.add(new SoftKey("Exit", "3", "Init:Services"));
                 CiscoIPPhoneText text=new CiscoIPPhoneText("Confirmation", "", "Your Pin Have Been Successfully Changed, Thanks .",softkeys);

        out.print(text.getTextObject());

            } else {

            String text = "Unable to change Pin Now ,Kindly Try Again Later.";
            response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));
            }



            }else{
            String text = "Please Check your Credntials";
            response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(text));
            }
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
