/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.univox.web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneMenu;
import Com.Stcs.IPPhone.Objects.MenuItem;
import Com.Stcs.IPPhone.Objects.SoftKey;
import com.univox.core.RssParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;
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
public class RSSCategories extends HttpServlet {
   
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
        String path = sc.getContextPath();
        String url = "http://" + thisServer + ":" + thisPort + path + "/";
        String Backurl = "http://" + thisServer + ":" + thisPort + "/STCS-IPServices/welcome";
        String FeedNewsPage=url+"RSSFeedNews";
        Vector menuItems=new Vector();
       RssParser rssParser=new RssParser();
        Hashtable<String,String> Feeds=rssParser.ReadRSSFeedXmlData("C:\\STCs\\RSS.xml");
        Enumeration Keys=Feeds.keys();
        while(Keys.hasMoreElements())
        {
            String FeedName =(String) Keys.nextElement();
            menuItems.add(new MenuItem(FeedName, FeedNewsPage+"?FeedURL="+URLEncoder.encode( Feeds.get(FeedName))+"&FeedName="+URLEncoder.encode(FeedName)));//+URLEncoder.encode( Feeds.get(FeedName))+
        }
Vector softkeys=new Vector();
softkeys.add(new SoftKey("Select", "1", "SoftKey:Select"));
softkeys.add(new SoftKey("Back", "2", Backurl));
softkeys.add(new SoftKey("Exit", "3", "Init:Services"));
CiscoIPPhoneMenu menu=new CiscoIPPhoneMenu("RSS Feeds", "Please Select your Feed", menuItems, softkeys);
out.print(menu.getMenuObject());
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
