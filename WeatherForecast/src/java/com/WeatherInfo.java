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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author adel
 */
public class WeatherInfo extends HttpServlet {

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
            String city = request.getParameter("city").replace("@", " ");
            String country = request.getParameter("country");
            String continent = request.getParameter("continent");
            String currentdayview = request.getParameter("currentdayflag");

            String phoneIP = request.getRemoteAddr();
            boolean isColoredPhone=IPPhone.isColorPhone(phoneIP);
            int PhoneModel = IPPhone.getPhoneType(phoneIP);

            Vector softKeys = new Vector();
            if (currentdayview.equals("true")) {
                softKeys.add(new SoftKey("Forecast", "2", url + "WeatherInfo?city=" + city.replace(" ", "@") + "&country=" + country.replace(" ", "@") + "&continent=" + continent + "&currentdayflag=false"));
                if (country.equals("") && continent.equals("")) {
                    softKeys.add(new SoftKey("Back", "3", url + "Search?country=&continent="));
                } else {
                    softKeys.add(new SoftKey("Back", "3", url + "Cities?country=" + country.replace(" ", "@") + "&continent=" + continent));
                }
            } else {
                softKeys.add(new SoftKey("Back", "3", url + "WeatherInfo?city=" + city.replace(" ", "@") + "&country=" + country.replace(" ", "@") + "&continent=" + continent + "&currentdayflag=true"));
            }
            softKeys.add(new SoftKey("Exit", "4", "Key:Services"));

            if (isColoredPhone) {

                String imageURL = getWeatherInfoImageURL(city, url,PhoneModel, currentdayview);

                Vector menuItems = new Vector();
                menuItems.add(new GraphicMenuItem("weatherinfo", "", new Point(0, 0), new Point(150, 180)));
                CiscoIPPhoneGraphicFileMenu menu = new CiscoIPPhoneGraphicFileMenu(city, city, imageURL, menuItems, softKeys);
                out.print(menu.getGraphicMenuObject());

                File f = new File(imageURL);
                f.delete();
            } else {

                String resultTxt = getWeatherInfoText(city, url, currentdayview);

                CiscoIPPhoneText textView = new CiscoIPPhoneText(city, city, resultTxt, softKeys);
                out.print(textView.getTextObject());

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

    private String getWeatherInfoImageURL(String city, String applicationURL,int phoneModel, String currentdayview) throws Exception {

        String forcasturl = "http://api.wunderground.com/auto/wui/geo/ForecastXML/index.xml?query=" + city;
        String iconsURL = "http://icons-ecast.wxug.com/i/c/e/";
        String imageURL = "";
        HashMap<String, ArrayList<String>> forecastdata = new HashMap<String, ArrayList<String>>();
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> weekDays = new ArrayList<String>();
        ArrayList<String> highC = new ArrayList<String>();
        ArrayList<String> highF = new ArrayList<String>();
        ArrayList<String> lowC = new ArrayList<String>();
        ArrayList<String> lowF = new ArrayList<String>();
        ArrayList<String> iconsurl = new ArrayList<String>();
        ArrayList<String> conditions = new ArrayList<String>();
        ArrayList<String> winds = new ArrayList<String>();
        ArrayList<String> pops = new ArrayList<String>();
        String moonIllumination = "";
        String sunset = "";
        String sunrise = "";

        try {
            DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docfactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docbuilder.parse(forcasturl);
            NodeList forcastNodes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < forcastNodes.getLength(); i++) {
                //simple forecast subNodeList parsing --------------------
                if (forcastNodes.item(i).getNodeType() == Node.ELEMENT_NODE && ((Element) forcastNodes.item(i)).getTagName().equals("simpleforecast")) {
                    NodeList simpleforecast = ((Element) forcastNodes.item(i)).getChildNodes();
                    for (int j = 0; j < simpleforecast.getLength(); j++) {
                        if (simpleforecast.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) simpleforecast.item(j)).getTagName().equals("forecastday")) {
                            NodeList forecastday = ((Element) simpleforecast.item(j)).getChildNodes();
                            for (int k = 0; k < forecastday.getLength(); k++) {
                                if (forecastday.item(k).getNodeName().equals("conditions")) {
                                    conditions.add(forecastday.item(k).getTextContent());
                                }
                                if (forecastday.item(k).getNodeName().equals("pop")) {
                                    pops.add(forecastday.item(k).getTextContent() + " %");
                                }
                                if (forecastday.item(k).getNodeName().equals("date")) {
                                    NodeList datelist = ((Element) forecastday.item(k)).getChildNodes();

                                    for (int m = 0; m < datelist.getLength(); m++) {
                                        if (datelist.item(m).getNodeName().equals("weekday")) {
                                            weekDays.add(datelist.item(m).getTextContent());
                                        }
                                    }
                                }
                                if (forecastday.item(k).getNodeName().equals("high")) {
                                    NodeList degree = ((Element) forecastday.item(k)).getChildNodes();

                                    for (int m = 0; m < degree.getLength(); m++) {
                                        if (degree.item(m).getNodeName().equals("fahrenheit")) {
                                            highF.add(degree.item(m).getTextContent());
                                        }
                                        if (degree.item(m).getNodeName().equals("celsius")) {
                                            highC.add(degree.item(m).getTextContent());
                                        }
                                    }
                                }
                                if (forecastday.item(k).getNodeName().equals("low")) {
                                    NodeList degree = ((Element) forecastday.item(k)).getChildNodes();

                                    for (int m = 0; m < degree.getLength(); m++) {
                                        if (degree.item(m).getNodeName().equals("fahrenheit")) {
                                            lowF.add(degree.item(m).getTextContent());
                                        }
                                        if (degree.item(m).getNodeName().equals("celsius")) {
                                            lowC.add(degree.item(m).getTextContent());
                                        }
                                    }
                                }
                                if (forecastday.item(k).getNodeName().equals("icon")) {
                                    iconsurl.add(iconsURL + forecastday.item(k).getTextContent() + ".gif");
                                }
                            }
                        }
                    }
                }
                //txt_forecast subNodeList parsing --------------------
                if (forcastNodes.item(i).getNodeType() == Node.ELEMENT_NODE && ((Element) forcastNodes.item(i)).getTagName().equals("txt_forecast")) {
                    NodeList txtforecast = ((Element) forcastNodes.item(i)).getChildNodes();

                    for (int j = 0; j < txtforecast.getLength(); j++) {
                        if (txtforecast.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) txtforecast.item(j)).getTagName().equals("forecastday")) {
                            NodeList forecastday = ((Element) txtforecast.item(j)).getChildNodes();

                            for (int k = 0; k < forecastday.getLength(); k++) {
                                if (forecastday.item(k).getNodeName().equals("title")) {
                                    dates.add(forecastday.item(k).getTextContent());
                                }
                                if (forecastday.item(k).getNodeName().equals("fcttext")) {
                                    String windExtractor = forecastday.item(k).getTextContent();
                                    winds.add(windExtractor.substring(windExtractor.toLowerCase().indexOf("winds")));
                                }
                            }
                        }
                    }
                }
                //moon_phase subNodeList parsing --------------------
                if (forcastNodes.item(i).getNodeType() == Node.ELEMENT_NODE && ((Element) forcastNodes.item(i)).getTagName().equals("moon_phase")) {
                    NodeList moonPhase = ((Element) forcastNodes.item(i)).getChildNodes();

                    for (int j = 0; j < moonPhase.getLength(); j++) {
                        if (moonPhase.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) moonPhase.item(j)).getTagName().equals("sunset")) {
                            NodeList sunsetTime = ((Element) moonPhase.item(j)).getChildNodes();

                            for (int k = 0; k < sunsetTime.getLength(); k++) {
                                if (sunsetTime.item(k).getNodeName().equals("hour")) {
                                    sunset = sunsetTime.item(k).getTextContent();
                                }
                                if (sunsetTime.item(k).getNodeName().equals("minute")) {
                                    sunset += " : " + sunsetTime.item(k).getTextContent();
                                }
                            }
                        }

                        if (moonPhase.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) moonPhase.item(j)).getTagName().equals("sunrise")) {
                            NodeList sunriseTime = ((Element) moonPhase.item(j)).getChildNodes();

                            for (int k = 0; k < sunriseTime.getLength(); k++) {
                                if (sunriseTime.item(k).getNodeName().equals("hour")) {
                                    sunrise = sunriseTime.item(k).getTextContent();
                                }
                                if (sunriseTime.item(k).getNodeName().equals("minute")) {
                                    sunrise += " : " + sunriseTime.item(k).getTextContent();
                                }
                            }
                        }

                        if (moonPhase.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) moonPhase.item(j)).getTagName().equals("percentIlluminated")) {
                            moonIllumination = moonPhase.item(j).getTextContent();
                        }
                    }
                }
            }



            forecastdata.put("dates", dates);
            forecastdata.put("weekDays", weekDays);
            forecastdata.put("highC", highC);
            forecastdata.put("highF", highF);
            forecastdata.put("lowC", lowC);
            forecastdata.put("lowF", lowF);
            forecastdata.put("iconsurl", iconsurl);
            forecastdata.put("conditions", conditions);
            forecastdata.put("winds", winds);
            forecastdata.put("pops", pops);

            if (currentdayview.equals("true")) {
                imageURL = drawCurrentdayImage(forecastdata, sunset, sunrise, moonIllumination, applicationURL,phoneModel);
            } else {
                imageURL = drawForecastImage(forecastdata, applicationURL,phoneModel);
            }

        } catch (Exception ex) {
            log(applicationURL, ex);
            throw new Exception(ex);
        }

        return imageURL;
    }

    private String drawCurrentdayImage(HashMap<String, ArrayList<String>> forecastData, String sunset, String sunrise, String moonIllumination, String applicationURL,int phoneModel) throws Exception {
        BufferedImage weatherImage;
        int imageSizeOffset=1;
        if(phoneModel==7941){
            weatherImage = ImageIO.read(new URL(applicationURL + "images/weatherday-7941.png"));
            imageSizeOffset=5;
        } else if (phoneModel == 7945){
            weatherImage = ImageIO.read(new URL(applicationURL + "images/weatherday-7945.png"));
            imageSizeOffset=3;
        } else{
            weatherImage = ImageIO.read(new URL(applicationURL + "images/weatherday.png"));
        }

        Graphics g = weatherImage.getGraphics();
        g.setColor(Color.white);
        Font font = new Font("times new romane", 3, 21-imageSizeOffset);
        g.setFont(font);

        //draw date
        g.drawString(forecastData.get("dates").get(0), 80+(imageSizeOffset*2), 20-imageSizeOffset);

        font = new Font("times new romane", 3, 20);
        g.setFont(font);
        //draw cilicius degree
        g.setColor(Color.cyan);
        g.drawString(forecastData.get("highC").get(0) + "/" + forecastData.get("lowC").get(0), 194, 45);
        g.drawOval(258, 30, 3, 3);
        g.drawString("C", 260, 45);

        //draw the icon and condition
        g.drawImage(ImageIO.read(new URL(forecastData.get("iconsurl").get(0))), 125, 40-imageSizeOffset, null);
        font = new Font("times new romane", 3, 14);
        g.setFont(font);
        g.drawString(forecastData.get("conditions").get(0), 145 - ((forecastData.get("conditions").get(0).length() / 2) * 7), 101-(imageSizeOffset*2));

        //draw wind,POP, and surise and sunset forecast
        font = new Font("Arial", 1, 10);
        g.setFont(font);
        g.drawString(forecastData.get("winds").get(0), 33, 121-(imageSizeOffset*4));
        g.drawString("Moon Illumination :- " + moonIllumination + " %", 33, 131-(imageSizeOffset*4));
        g.drawString("Probability of precipitation : " + forecastData.get("pops").get(0), 33, 141-(imageSizeOffset*4));
        g.drawString("Sunset :- " + sunset + "     " + "Sunrise :- " + sunrise, 33, 151-(imageSizeOffset*4));
        File resultImagefile = new File(getServletContext().getRealPath("/") + "images/currentday.png");
        try {
            ImageIO.write(weatherImage, "png", resultImagefile);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return applicationURL + "images/currentday.png";
    }

    private String drawForecastImage(HashMap<String, ArrayList<String>> forecastData, String applicationURL,int phoneModel) throws Exception {
        BufferedImage weatherImage;
        int imageSizeOffset=1;
        if(phoneModel==7941){
            weatherImage= ImageIO.read(new URL(applicationURL + "/images/weather-7941.png"));
            imageSizeOffset=5;
        } else if(phoneModel==7945){
            weatherImage= ImageIO.read(new URL(applicationURL + "/images/weather-7945.png"));
            imageSizeOffset=3;
        } else {
            weatherImage= ImageIO.read(new URL(applicationURL + "/images/weather.png"));
        }
        
        Graphics g = weatherImage.getGraphics();
        g.setColor(Color.white);
        Font font = new Font("times new romane", 3, 21-imageSizeOffset);
        g.setFont(font);
        g.drawString(Integer.toString(forecastData.get("dates").size() - 1) + "-days Forecasting", 105+(imageSizeOffset*2), 50-(imageSizeOffset*3));

        font = new Font("times new romane", 3, 10);
        g.setFont(font);
        for (int i = 1; i < forecastData.get("dates").size(); i++) {
            //draw the icon and condition
            int x = ((i - 1) * 58) + 10;
            g.drawImage(ImageIO.read(new URL(forecastData.get("iconsurl").get(i))), x, 70-(imageSizeOffset*2), null);
            g.drawString(forecastData.get("highC").get(i) + "/" + forecastData.get("lowC").get(i) + " C", x, 125-(imageSizeOffset*2));
            g.drawString(forecastData.get("weekDays").get(i), x - 2, 138-(imageSizeOffset*2));

            if (i != (forecastData.get("dates").size() - 1)) {
                g.drawLine(x + 50, 68-(imageSizeOffset*2), x + 50, 160-(imageSizeOffset*4));
            }
        }

        File f = new File(getServletContext().getRealPath("/") + "images/forecastdays.png");
        try {
            ImageIO.write(weatherImage, "png", f);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return applicationURL + "images/forecastdays.png";
    }

    private String getWeatherInfoText(String city, String applicationURL, String currentdayview) throws Exception {

        String forcasturl = "http://api.wunderground.com/auto/wui/geo/ForecastXML/index.xml?query=" + city;
        String iconsURL = "http://icons-ecast.wxug.com/i/c/e/";
        String txtWeatherInfo = "";
        HashMap<String, ArrayList<String>> forecastdata = new HashMap<String, ArrayList<String>>();
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> weekDays = new ArrayList<String>();
        ArrayList<String> highC = new ArrayList<String>();
        ArrayList<String> highF = new ArrayList<String>();
        ArrayList<String> lowC = new ArrayList<String>();
        ArrayList<String> lowF = new ArrayList<String>();
        ArrayList<String> iconsurl = new ArrayList<String>();
        ArrayList<String> conditions = new ArrayList<String>();
        ArrayList<String> winds = new ArrayList<String>();
        ArrayList<String> pops = new ArrayList<String>();
        String moonIllumination = "";
        String sunset = "";
        String sunrise = "";

        try {
            DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docfactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docbuilder.parse(forcasturl);
            NodeList forcastNodes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < forcastNodes.getLength(); i++) {
                //simple forecast subNodeList parsing --------------------
                if (forcastNodes.item(i).getNodeType() == Node.ELEMENT_NODE && ((Element) forcastNodes.item(i)).getTagName().equals("simpleforecast")) {
                    NodeList simpleforecast = ((Element) forcastNodes.item(i)).getChildNodes();
                    for (int j = 0; j < simpleforecast.getLength(); j++) {
                        if (simpleforecast.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) simpleforecast.item(j)).getTagName().equals("forecastday")) {
                            NodeList forecastday = ((Element) simpleforecast.item(j)).getChildNodes();
                            for (int k = 0; k < forecastday.getLength(); k++) {
                                if (forecastday.item(k).getNodeName().equals("conditions")) {
                                    conditions.add(forecastday.item(k).getTextContent());
                                }
                                if (forecastday.item(k).getNodeName().equals("pop")) {
                                    pops.add(forecastday.item(k).getTextContent() + " %");
                                }
                                if (forecastday.item(k).getNodeName().equals("date")) {
                                    NodeList datelist = ((Element) forecastday.item(k)).getChildNodes();

                                    for (int m = 0; m < datelist.getLength(); m++) {
                                        if (datelist.item(m).getNodeName().equals("weekday")) {
                                            weekDays.add(datelist.item(m).getTextContent());
                                        }
                                    }
                                }
                                if (forecastday.item(k).getNodeName().equals("high")) {
                                    NodeList degree = ((Element) forecastday.item(k)).getChildNodes();

                                    for (int m = 0; m < degree.getLength(); m++) {
                                        if (degree.item(m).getNodeName().equals("fahrenheit")) {
                                            highF.add(degree.item(m).getTextContent());
                                        }
                                        if (degree.item(m).getNodeName().equals("celsius")) {
                                            highC.add(degree.item(m).getTextContent());
                                        }
                                    }
                                }
                                if (forecastday.item(k).getNodeName().equals("low")) {
                                    NodeList degree = ((Element) forecastday.item(k)).getChildNodes();

                                    for (int m = 0; m < degree.getLength(); m++) {
                                        if (degree.item(m).getNodeName().equals("fahrenheit")) {
                                            lowF.add(degree.item(m).getTextContent());
                                        }
                                        if (degree.item(m).getNodeName().equals("celsius")) {
                                            lowC.add(degree.item(m).getTextContent());
                                        }
                                    }
                                }
                                if (forecastday.item(k).getNodeName().equals("icon")) {
                                    iconsurl.add(iconsURL + forecastday.item(k).getTextContent() + ".gif");
                                }
                            }
                        }
                    }
                }
                //txt_forecast subNodeList parsing --------------------
                if (forcastNodes.item(i).getNodeType() == Node.ELEMENT_NODE && ((Element) forcastNodes.item(i)).getTagName().equals("txt_forecast")) {
                    NodeList txtforecast = ((Element) forcastNodes.item(i)).getChildNodes();

                    for (int j = 0; j < txtforecast.getLength(); j++) {
                        if (txtforecast.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) txtforecast.item(j)).getTagName().equals("forecastday")) {
                            NodeList forecastday = ((Element) txtforecast.item(j)).getChildNodes();

                            for (int k = 0; k < forecastday.getLength(); k++) {
                                if (forecastday.item(k).getNodeName().equals("title")) {
                                    dates.add(forecastday.item(k).getTextContent());
                                }
                                if (forecastday.item(k).getNodeName().equals("fcttext")) {
                                    String windExtractor = forecastday.item(k).getTextContent();
                                    winds.add(windExtractor.substring(windExtractor.toLowerCase().indexOf("winds")));
                                }
                            }
                        }
                    }
                }
                //moon_phase subNodeList parsing --------------------
                if (forcastNodes.item(i).getNodeType() == Node.ELEMENT_NODE && ((Element) forcastNodes.item(i)).getTagName().equals("moon_phase")) {
                    NodeList moonPhase = ((Element) forcastNodes.item(i)).getChildNodes();

                    for (int j = 0; j < moonPhase.getLength(); j++) {
                        if (moonPhase.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) moonPhase.item(j)).getTagName().equals("sunset")) {
                            NodeList sunsetTime = ((Element) moonPhase.item(j)).getChildNodes();

                            for (int k = 0; k < sunsetTime.getLength(); k++) {
                                if (sunsetTime.item(k).getNodeName().equals("hour")) {
                                    sunset = sunsetTime.item(k).getTextContent();
                                }
                                if (sunsetTime.item(k).getNodeName().equals("minute")) {
                                    sunset += " : " + sunsetTime.item(k).getTextContent();
                                }
                            }
                        }

                        if (moonPhase.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) moonPhase.item(j)).getTagName().equals("sunrise")) {
                            NodeList sunriseTime = ((Element) moonPhase.item(j)).getChildNodes();

                            for (int k = 0; k < sunriseTime.getLength(); k++) {
                                if (sunriseTime.item(k).getNodeName().equals("hour")) {
                                    sunrise = sunriseTime.item(k).getTextContent();
                                }
                                if (sunriseTime.item(k).getNodeName().equals("minute")) {
                                    sunrise += " : " + sunriseTime.item(k).getTextContent();
                                }
                            }
                        }

                        if (moonPhase.item(j).getNodeType() == Node.ELEMENT_NODE && ((Element) moonPhase.item(j)).getTagName().equals("percentIlluminated")) {
                            moonIllumination = moonPhase.item(j).getTextContent();
                        }
                    }
                }
            }

            if (currentdayview.equals("true")) {
                txtWeatherInfo = "Date : " + dates.get(0) + " (" + weekDays.get(0) + ")/n"
                        + "High degree: " + highC.get(0) + " ْC" + "\n"
                        + "Low degree: " + lowC.get(0) + " ْC" + "\n"
                        + "Condition: " + conditions.get(0) + "\n"
                        + winds.get(0) + "\n"
                        + "Probability of precipitation: " + pops.get(0) + "\n"
                        + "Moon Illumination " + moonIllumination + " %" + "\n"
                        + "Sunset: " + sunset + "\n" + "Sunrise: " + sunrise;
            } else {
                for (int i = 0; i < dates.size(); i++) {
                    txtWeatherInfo += "Date : " + dates.get(i) + " (" + weekDays.get(i) + ")/n"
                            + "     High degree: " + highC.get(i) + " ْC" + " -- Low degree: " + lowC.get(i) + " ْC" + "\n";
                }

            }

        } catch (Exception ex) {
            log(applicationURL, ex);
            throw new Exception(ex);
        }

        return txtWeatherInfo;
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
            writer.append("Page: WeatherInfo");
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
