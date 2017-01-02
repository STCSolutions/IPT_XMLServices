/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import Com.UniVox.IPPhone.Objects.CiscoIPPhoneGraphicFileMenu;
import Com.UniVox.IPPhone.Objects.CiscoIPPhoneText;
import Com.UniVox.IPPhone.Objects.GraphicMenuItem;
import Com.UniVox.IPPhone.Objects.Point;
import Com.UniVox.IPPhone.Objects.SoftKey;
import Com.UniVox.IPPhoneInfo.IPPhone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author adel
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
            throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String thisServer = InetAddress.getLocalHost().getHostAddress();
        int thisPort = request.getServerPort();
        ServletContext sc = getServletConfig().getServletContext();
        String path = sc.getContextPath();
        String url = "http://" + thisServer + ":" + thisPort + path + "/";

        try {

            String continentsPage = url + "Continent";
            String ImageName = "";

            String phoneIP = request.getRemoteAddr();
            boolean iscolor = IPPhone.isColorPhone(phoneIP);
            int PhoneModel = IPPhone.getPhoneType(phoneIP);

            Vector softkeys = new Vector();
            softkeys.add(new SoftKey("Exit", "3", "Key:Services"));
            softkeys.add(new SoftKey("Start", "1", continentsPage));

            if (iscolor) {
                if (PhoneModel == 7941) {
                    ImageName = "start-7941.png";
                } else if(PhoneModel==7945){
                    ImageName = "start-7945.png";
                } else{
                    ImageName = "start.png";
                }
                Vector menuItems = new Vector();
                menuItems.add(new GraphicMenuItem(path, continentsPage, new Point(0, 0), new Point(300, 800)));
                CiscoIPPhoneGraphicFileMenu menu = new CiscoIPPhoneGraphicFileMenu("Welcome", "Touch to start...", url + "images/" + ImageName, menuItems, softkeys);
                out.print(menu.getGraphicMenuObject());
            } else {
                CiscoIPPhoneText text = new CiscoIPPhoneText("Weather Forecasting.", "Touch to start...", "Welecome TO UniVox's Weather Forecasting Application", softkeys);
                out.print(text.getTextObject());
            }
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
            writer.append("Page: Welcome");
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
