/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import Com.UniVox.IPPhone.Objects.CiscoIPPhoneInput;
import Com.UniVox.IPPhone.Objects.CiscoIPPhoneMenu;
import Com.UniVox.IPPhone.Objects.CiscoIPPhoneText;
import Com.UniVox.IPPhone.Objects.InputItem;
import Com.UniVox.IPPhone.Objects.MenuItem;
import Com.UniVox.IPPhone.Objects.SoftKey;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

/**
 *
 * @author adel
 */
public class SearchResult extends HttpServlet {

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
            String continent="";
            String country="";
            String city="";
            if(request.getParameter("continent")!=null)
                continent = request.getParameter("continent");
            if(request.getParameter("country")!=null)
                country = request.getParameter("country").replace("@", " ");
            if(request.getParameter("city")!=null)
                city = request.getParameter("city").replace("@", " ");
            ;

            ArrayList<String> resultCities = search(url, continent, country, city);
            Vector menuItems = new Vector();
            for (int i = 0; i < resultCities.size(); i++) {
                menuItems.add(new MenuItem(resultCities.get(i), url + "WeatherInfo?city=" + resultCities.get(i).replace(" ", "@") + "&country=&continent=&currentdayflag=true"));
            }

            Vector softKeys = new Vector();
            softKeys.add(new SoftKey("Select", "1", "SoftKey:Select"));
            softKeys.add(new SoftKey("Back", "3", url + "Search?country=&continent="));
            softKeys.add(new SoftKey("Exit", "4", "Key:Services"));

            CiscoIPPhoneMenu menu = new CiscoIPPhoneMenu("Search Result", "(" + Integer.toString(resultCities.size()) + ") resulted item(s)", menuItems, softKeys);
            out.print(menu.getMenuObject());

        } catch (Exception ex) {
            log(url, ex);
            Vector softkeys = new Vector();
            softkeys.add(new SoftKey("Exit", "3", "Key:Services"));
            CiscoIPPhoneText text = new CiscoIPPhoneText("Error", "Unexpected error occured", "Unexpected error occured please check your log file or contact your system administrator", softkeys);
            out.write(text.getTextObject());
        } finally {
            out.close();
        }
    }

    private ArrayList<String> search(String url, String strContinent, String strCountry, String strCity) throws Exception {
        try {
            DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docfactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docbuilder.parse(url + "resources/world.xml");

            ArrayList<String> continents = new ArrayList<String>();
            for (int i = 0; i < doc.getDocumentElement().getChildNodes().getLength(); i++) {
                String continent = doc.getDocumentElement().getChildNodes().item(i).getNodeName().toLowerCase().replace("-", " ");
                if (continent.startsWith(strContinent) || continent.contains(" " + strContinent)) {
                    continents.add(doc.getDocumentElement().getChildNodes().item(i).getNodeName());
                }
            }

            ArrayList<String> countries = new ArrayList<String>();
            for (int i = 0; i < continents.size(); i++) {
                NodeList countriesNodes = doc.getElementsByTagName(continents.get(i)).item(0).getChildNodes();
                for (int j = 0; j < countriesNodes.getLength(); j++) {
                    String country = countriesNodes.item(j).getNodeName().toLowerCase().replace("-", " ");
                    if (country.startsWith(strCountry) || country.contains(" " + strCountry)) {
                        countries.add(countriesNodes.item(j).getNodeName());
                    }
                }
            }

            ArrayList<String> cities = new ArrayList<String>();
            for (int i = 0; i < countries.size(); i++) {
                NodeList citiesNodes = doc.getElementsByTagName(countries.get(i)).item(0).getChildNodes();
                for (int j = 0; j < citiesNodes.getLength(); j++) {
                    String city = citiesNodes.item(j).getTextContent().toLowerCase();
                    if (city.contains(",")) {
                        city = city.substring(0, city.indexOf(","));
                    }
                    if (city.startsWith(strCity) || city.contains(" " + strCity)) {
                        cities.add(citiesNodes.item(j).getTextContent());
                    }
                }
            }
            return cities;

        } catch (Exception ex) {
            log(url, ex);
            throw new Exception(ex);
        }

    }

    private void log(String url, Exception ex) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            DateFormat simpledateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String currentTime = dateFormat.format(date);
            String fileName = simpledateFormat.format(date);

            File file = new File(getServletContext().getRealPath("/") + "Logs\\Error_Log_" + fileName + ".log");

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.append("______________________________________________________________________________________");
            writer.newLine();
            writer.append("Time: " + dateFormat.format(date));
            writer.newLine();
            writer.append("--------");
            writer.newLine();
            writer.append("Page: SearchResult");
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

        } catch (IOException ex1) {
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
