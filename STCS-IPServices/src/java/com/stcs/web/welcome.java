/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stcs.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneGraphicFileMenu;
import Com.Stcs.IPPhone.Objects.CiscoIPPhoneMenu;
import Com.Stcs.IPPhone.Objects.GraphicMenuItem;
import Com.Stcs.IPPhone.Objects.MenuItem;
import Com.Stcs.IPPhone.Objects.Model.PhoneSeriesModel;
import Com.Stcs.IPPhone.Objects.Point;
import Com.Stcs.IPPhone.Objects.SoftKey;
import Com.Stcs.IPPhoneInfo.IPPhone;
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

/**
 *
 * @author AMR  Abd El Monsif
 */
public class welcome extends HttpServlet {
   
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
        String LoginPage=url+"Login";
       
        String ImageName="";
        String phoneIP = request.getRemoteAddr();
        PhoneSeriesModel phoneModel=IPPhone.getPhoneDetails(phoneIP);
        Properties ProbFile=new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String AzanUrl =ProbFile.getProperty("AzanUrl");
        String PhoneLockUrl =ProbFile.getProperty("PhoneLockUrl");
        String NewsUrl =ProbFile.getProperty("NewsUrl");
       // String AttendanceUrl =ProbFile.getProperty("AttendanceUrl");
        String VoicePaging=ProbFile.getProperty("VoicePaginUrl");
        

        System.out.println(url);
        Vector softkeys=new Vector();

softkeys.add(new SoftKey("Lock","1", PhoneLockUrl));
softkeys.add(new SoftKey("Paging","2", VoicePaging));
softkeys.add(new SoftKey("Azan","3" ,AzanUrl));
softkeys.add(new SoftKey("News","4",NewsUrl));
//softkeys.add(new SoftKey("Attendance","5", AttendanceUrl));
softkeys.add(new SoftKey("Exit", "5", "Init:Services"));

Vector menuItems=new Vector();

if(phoneModel.isIsColorPhone())
{
 

 ImageName="home_"+phoneModel.getPhotoSize()+".png";
 if(phoneModel.getSeriesName().equals("Cisco_88XX"))
 {//559.265
menuItems.add(new GraphicMenuItem("Phone Lock",PhoneLockUrl,new Point(15, 65),new Point(135, 165)));
menuItems.add(new GraphicMenuItem("Voice Paging",VoicePaging,new Point(160, 65),new Point(270, 165)));
menuItems.add(new GraphicMenuItem("Azan",AzanUrl,new Point(300, 65),new Point(410, 165)));     
menuItems.add(new GraphicMenuItem("News",NewsUrl,new Point(430, 65),new Point(545, 165)));

 }
 else if(phoneModel.getSeriesName().equals("Cisco89_99XX"))
 {//498.289
menuItems.add(new GraphicMenuItem("Phone Lock",PhoneLockUrl,new Point(10, 70),new Point(125, 185)));
menuItems.add(new GraphicMenuItem("Voice Paging",VoicePaging,new Point(145, 70),new Point(240, 185)));
menuItems.add(new GraphicMenuItem("Azan",AzanUrl,new Point(265, 70),new Point(375, 185)));     
menuItems.add(new GraphicMenuItem("News",NewsUrl,new Point(385, 70),new Point(485, 185)));
 }else if(phoneModel.getSeriesName().equals("Cisco79XXBW"))
 {//298.144
menuItems.add(new GraphicMenuItem("Phone Lock",PhoneLockUrl,new Point(5, 35),new Point(70, 90)));
menuItems.add(new GraphicMenuItem("Voice Paging",VoicePaging,new Point(85, 35),new Point(150, 90)));
menuItems.add(new GraphicMenuItem("Azan",AzanUrl,new Point(160, 35),new Point(220, 90)));     
menuItems.add(new GraphicMenuItem("News",NewsUrl,new Point(230, 35),new Point(290, 90)));
 }else if(phoneModel.getSeriesName().equals("Cisco79XXA"))
 {//298.156
menuItems.add(new GraphicMenuItem("Phone Lock",PhoneLockUrl,new Point(5, 35),new Point(70, 100)));
menuItems.add(new GraphicMenuItem("Voice Paging",VoicePaging,new Point(80, 35),new Point(150, 100)));
menuItems.add(new GraphicMenuItem("Azan",AzanUrl,new Point(160, 35),new Point(220, 100)));     
menuItems.add(new GraphicMenuItem("News",NewsUrl,new Point(230, 35),new Point(290, 100)));
 }else if(phoneModel.getSeriesName().equals("Cisco79XXB"))
 {//298.168
menuItems.add(new GraphicMenuItem("Phone Lock",PhoneLockUrl,new Point(5, 40),new Point(75, 105)));
menuItems.add(new GraphicMenuItem("Voice Paging",VoicePaging,new Point(80, 40),new Point(150, 105)));
menuItems.add(new GraphicMenuItem("Azan",AzanUrl,new Point(160, 40),new Point(220, 105)));     
menuItems.add(new GraphicMenuItem("News",NewsUrl,new Point(230, 40),new Point(290, 105)));
 }else if(phoneModel.getSeriesName().equals("Cisco79XXMob"))
 {//176.140
menuItems.add(new GraphicMenuItem("Phone Lock",PhoneLockUrl,new Point(5, 35),new Point(45, 85)));
menuItems.add(new GraphicMenuItem("Voice Paging",VoicePaging,new Point(50, 35),new Point(90, 85)));
menuItems.add(new GraphicMenuItem("Azan",AzanUrl,new Point(95, 35),new Point(130, 85)));     
menuItems.add(new GraphicMenuItem("News",NewsUrl,new Point(135, 35),new Point(170, 85)));
 }
CiscoIPPhoneGraphicFileMenu menu=new CiscoIPPhoneGraphicFileMenu("Welcome","Select Your Service",url+"images/"+ImageName, menuItems, softkeys);
out.print(menu.getGraphicMenuObject());
}
else
{
softkeys.add(new SoftKey("Select", "1", "SoftKey:Select"));
menuItems.add(new MenuItem("Phone Lock", PhoneLockUrl));
menuItems.add(new MenuItem("Broadcasting", VoicePaging));
menuItems.add(new MenuItem("Azan", AzanUrl));
menuItems.add(new MenuItem("Rss News", NewsUrl));
CiscoIPPhoneMenu menu=new CiscoIPPhoneMenu("STCs IP-Phone Services","Select Your Service", menuItems, softkeys);
out.print(menu.getMenuObject());
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
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
