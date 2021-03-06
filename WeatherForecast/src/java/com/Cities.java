/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import Com.UniVox.IPPhone.Objects.CiscoIPPhoneMenu;
import Com.UniVox.IPPhone.Objects.CiscoIPPhoneText;
import Com.UniVox.IPPhone.Objects.MenuItem;
import Com.UniVox.IPPhone.Objects.SoftKey;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor.STRING;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author adel
 */
public class Cities extends HttpServlet {

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
        //get the application's url
            String thisServer = InetAddress.getLocalHost().getHostAddress();
            int thisPort = request.getServerPort();
            ServletContext sc = getServletConfig().getServletContext();
            String path = sc.getContextPath();
            String url = "http://" + thisServer + ":" + thisPort + path + "/";
        try {
            //get the choosen country
            String country = request.getParameter("country").replace("@", " ");
            String continent=request.getParameter("continent");

            ArrayList<String> cities=getCitiesList(url, country);
            
            Vector menuItems = new Vector();
            //fill the menuItes with the countries name
            for (int i = 0; i < cities.size(); i++) {
                menuItems.add(new MenuItem(cities.get(i), url + "WeatherInfo?city=" + cities.get(i).replace(" ", "@")+"&country="+country.replace(" ", "@")+"&continent="+continent+"&currentdayflag=true"));
            }

            Vector softKeys = new Vector();
            softKeys.add(new SoftKey("Select", "1", "SoftKey:Select"));
            softKeys.add(new SoftKey("Search", "2", url+"Search?country="+country.replace(" ", "@")+"&continent="+continent));
            softKeys.add(new SoftKey("Back", "3", url + "Countries?continent="+continent));
            softKeys.add(new SoftKey("Exit", "4", "Key:Services"));

            CiscoIPPhoneMenu menu = new CiscoIPPhoneMenu(country, "Choose your city", menuItems, softKeys);
            out.print(menu.getMenuObject());
            
        } catch (Exception ex) {
            
            log(url,ex);
            Vector softkeys = new Vector();
            softkeys.add(new SoftKey("Exit", "3", "Key:Services"));
            CiscoIPPhoneText text=new CiscoIPPhoneText("Error", "Unexpected error occured", "Unexpected error occured please check your log file or contact your system administrator", softkeys);
            out.write(text.getTextObject());

        } finally {
            out.close();
        }
    }

        private ArrayList<String> getCitiesList(String url,String country) throws Exception {
        ArrayList<String> cities = new ArrayList<String>();
        try {
            DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docfactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docbuilder.parse(url + "resources/world.xml");
            doc.getDocumentElement().normalize();
            NodeList citieslist = doc.getElementsByTagName(country.replace(" ", "-")).item(0).getChildNodes();

            for(int i=0;i<citieslist.getLength();i++){
                cities.add(citieslist.item(i).getTextContent());
            }

        } catch (Exception ex) {
            log(url,ex);
            throw new Exception(ex);
        }
        return cities;
    }

            private void log(String url,Exception ex){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            DateFormat simpledateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String currentTime=dateFormat.format(date);
            String fileName=simpledateFormat.format(date);

            File file = new File(getServletContext().getRealPath("/") + "Logs\\Error_Log_" + fileName + ".log");

            if (!file.exists())
                file.createNewFile();

            BufferedWriter writer=new BufferedWriter(new FileWriter(file,true));

            writer.append("______________________________________________________________________________________");
            writer.newLine();
            writer.append("Time: " + dateFormat.format(date));
            writer.newLine();
            writer.append("--------");
            writer.newLine();
            writer.append("Page: Cities");
            writer.newLine();
            writer.append("--------");
            writer.newLine();
            writer.append("Message: " + ex.getMessage());
            writer.newLine();
            writer.append("--------");
            writer.newLine();
            writer.append("Class: " + ex.getStackTrace()[0].getClassName());
            writer.newLine();
            writer.append("--------");
            writer.newLine();
            writer.append("Method: " + ex.getStackTrace()[0].getMethodName());
            writer.newLine();
            writer.append("--------");
            writer.newLine();
            writer.append("Line number: " + ex.getStackTrace()[0].getLineNumber());
            writer.newLine();
            writer.append("______________________________________________________________________________________");
            writer.newLine();
            
            writer.flush();
            writer.close();

        } catch(IOException ex1) {
            Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex1);
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
