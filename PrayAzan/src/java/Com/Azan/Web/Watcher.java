/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Com.Azan.Web;

import Com.Univox.Core.AzanLoaderTask;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author XPPRESP3
 */
public class Watcher extends HttpServlet {
   
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
//            String time="11:46:00";
////            new AzanRtpStreeming("douhr",time);
//           PrayTimeIO PrayManger=new PrayTimeIO();
//           if(PrayManger.doForceReload())
//           {
//               PrayManger.WriteAzanXmlData("C:\\STCs\\PraysTimes.xml");
//           }
//           if(!new File("C:\\STCs\\PraysTimes.xml").exists())
//           {
//               PrayManger.WriteAzanXmlData("C:\\STCs\\PraysTimes.xml");
//           }
//        PrayManger.WritePhoneListeners("C:\\STCs\\RegisteredUser.xml");
//        Hashtable<String,String> prayTimesTable=  PrayManger.ReadPrayTimesXmlData("C:\\STCs\\PraysTimes.xml");
//        Enumeration Keys=prayTimesTable.keys();
//        while(Keys.hasMoreElements())
//        {
//            String PrayName =(String) Keys.nextElement();
//           new AzanRtpStreeming(PrayName,prayTimesTable.get(PrayName));
//           out.print(PrayName+" at " +prayTimesTable.get(PrayName)+"\n");
//        }
        } finally { 
            out.close();
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

    @Override
    public void init() throws ServletException {
        
            System.out.println("|||||||||||||||||||||||||||||||");
            System.out.println("Azan Loader Tasker Was Fierd in" + new Date().toString());
            System.out.println("|||||||||||||||||||||||||||||||");
            AzanLoaderTask Loader = new AzanLoaderTask();

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
