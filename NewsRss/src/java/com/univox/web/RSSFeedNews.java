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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
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
public class RSSFeedNews extends HttpServlet {
   
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
        String NewsDetails=url+"FeedDetails";
         String CategoryPage=url+"RSSCategories";
       String FeedURL=request.getParameter("FeedURL");
         String ErrorPage = url + "ErrorPage";
       String FeedName=request.getParameter("FeedName");

        RssParser rssParser=new RssParser();
      
        ArrayList<Hashtable<String,String>> NewsFeeds=null;
        try {
            NewsFeeds = rssParser.ParseFeedNews(FeedURL);
        } catch (Exception ex) {
response.sendRedirect(ErrorPage + "?Message=" + URLEncoder.encode(ex.getMessage()));
return;
        }
       


     
        Vector softkeys=new Vector();
softkeys.add(new SoftKey("Exit", "4", "App:Close"));
softkeys.add(new SoftKey("Back", "3", CategoryPage));
softkeys.add(new SoftKey("Select", "1", "SoftKey:Select"));
        Vector menuItems =new Vector();
        for(int i=0;i<NewsFeeds.size();i++)
        {
           Hashtable<String,String> Feed=NewsFeeds.get(i);
           String date=""+Feed.get("Publish Date");
           session.setAttribute("PublishDate",date);
           String DateMofdified=date.replace(":", ".").replace(",", ".");
           menuItems.add(new MenuItem(Feed.get("Title"), NewsDetails+"?Desc="+URLEncoder.encode(Feed.get("Description"))));
        }
        CiscoIPPhoneMenu menu=new CiscoIPPhoneMenu(FeedName, "Select your News", menuItems, softkeys);
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
