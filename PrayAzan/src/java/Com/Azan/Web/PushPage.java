/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Com.Azan.Web;

import Com.Stcs.IPPhone.Objects.CiscoIPPhoneGraphicFileMenu;
import Com.Stcs.IPPhone.Objects.CiscoIPPhoneText;
import Com.Stcs.IPPhone.Objects.GraphicMenuItem;
import Com.Stcs.IPPhone.Objects.Model.PhoneSeriesModel;
import Com.Stcs.IPPhone.Objects.Point;
import Com.Stcs.IPPhone.Objects.SoftKey;
import Com.Stcs.IPPhoneInfo.IPPhone;
import Com.Univox.Core.CheckPhone;
import Com.Univox.pushtophone.Push2Phone;
import Com.Univox.pushtophone.Text2Base64;
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
 * @author AMR
 */
public class PushPage extends HttpServlet {
   
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
        String phoneIP = request.getRemoteAddr();
        PhoneSeriesModel phoneModel=IPPhone.getPhoneDetails(phoneIP);
        String CallManager_ApplicationUser_Credintials="";
        String ImageName="";
        Properties ProbFile = new Properties();
        ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        CallManager_ApplicationUser_Credintials = ProbFile.getProperty("CallManager_ApplicationUser_Credintials");


        String SalatName=request.getParameter("SalatName");
        Vector softkeys=new Vector();
        softkeys.add(new SoftKey("Exit", "3", "Init:Services"));
        String[] Reciving_urls = {"RTPRx:Stop", "Play:Azan1.raw"};

        Push2Phone p2p = new Push2Phone();
        p2p.push(Reciving_urls,phoneIP, Text2Base64.getBase64(CallManager_ApplicationUser_Credintials), true);
 
if(phoneModel.isIsColorPhone())
{
    ImageName="PrayTime_"+phoneModel.getPhotoSize()+".png";

Vector menuItems=new Vector();
menuItems.add(new GraphicMenuItem(path,"App:Close",new Point(0, 0),new Point(559, 265)));
CiscoIPPhoneGraphicFileMenu menu=new CiscoIPPhoneGraphicFileMenu("PrayTime","Now Is Time For "+SalatName+" Pray",url+"images/"+ImageName, menuItems, softkeys);
out.print(menu.getGraphicMenuObject());
}
else
{
CiscoIPPhoneText text=new CiscoIPPhoneText("PrayTime","","Now Is Time For "+SalatName+" Pray" , softkeys);
out.print(text.getTextObject());
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
            Logger.getLogger(PushPage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PushPage.class.getName()).log(Level.SEVERE, null, ex);
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
