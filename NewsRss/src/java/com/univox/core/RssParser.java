/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univox.core;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author AMR
 */
public class RssParser {

    public Hashtable<String, String> ReadRSSFeedXmlData(String fileName) {
        Hashtable<String, String> RssFeeds = new Hashtable<String, String>();
        try {

            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("RssFeed");
            System.out.println("Information of all RSS");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("FeedName");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    String FeedName = ((Node) fstNm.item(0)).getNodeValue().toString();

                    System.out.println("Feed Name : " + ((Node) fstNm.item(0)).getNodeValue());
                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("FeedURL");
                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                    NodeList lstNm = lstNmElmnt.getChildNodes();
                    System.out.println("Feed URL : " + ((Node) lstNm.item(0)).getNodeValue());
                    String FeedURL = ((Node) lstNm.item(0)).getNodeValue().toString();
                    RssFeeds.put(FeedName, FeedURL);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return RssFeeds;
    }

    public ArrayList<Hashtable<String, String>> ParseFeedNews(String FeedUrl) throws Exception {
        ArrayList<Hashtable<String, String>> NewsFeeds = new ArrayList<Hashtable<String, String>>();
        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL u = new URL(FeedUrl); // http://rss.news.yahoo.com/rss/topstories//http://pratyush.in/feed/rss

            Document doc = builder.parse(u.openStream());

            NodeList nodes = doc.getElementsByTagName("item");

            for (int i = 0; i < nodes.getLength(); i++) {

                Hashtable<String, String> feed = new Hashtable<String, String>();
                Element element = (Element) nodes.item(i);
                //  System.out.println(element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue());

                System.out.println("Title:" + getElementValue(element, "title"));
                feed.put("Title", getElementValue(element, "title"));
//System.out.println("Publish Date: " + getElementValue(element,"pubDate"));
                String D = getElementValue(element, "pubDate");
                feed.put("Publish Date", getElementValue(element, "pubDate"));
                System.out.println("description: " + getElementValue(element, "description"));
                String x = getElementValue(element, "description");
                /*if(x.indexOf(" - ")!=-1)
                x=x.substring(x.lastIndexOf(" - ")+3,x.lastIndexOf("."));*/
                if (x.indexOf("AP -") != -1) {
                    if (x.indexOf(".") != -1 && (x.lastIndexOf(".") > x.indexOf("AP -"))) {
                        x = x.substring(x.indexOf("AP -"), x.lastIndexOf("."));
                    } else {
                        if (x.indexOf("?") != -1 && (x.lastIndexOf("?") > x.indexOf("AP -"))) {
                            x = x.substring(x.indexOf("AP -"), x.lastIndexOf("?"));
                        } else {
                            x = x.substring(x.indexOf("AP -"));
                        }

                    }
                }


                if (x.indexOf("Reuters -") != -1) {
                    if (x.indexOf(".") != -1 && (x.lastIndexOf(".") > x.indexOf("Reuters -"))) {
                        x = x.substring(x.indexOf("Reuters -"), x.lastIndexOf("."));
                    } else {
                        if (x.indexOf("?") != -1 && (x.lastIndexOf("?") > x.indexOf("Reuters -"))) {
                            x = x.substring(x.indexOf("Reuters -"), x.lastIndexOf("?"));
                        } else {
                            x = x.substring(x.indexOf("Reuters -"));
                        }

                    }
                }


                if (x.indexOf("AFP -") != -1) {
                    if (x.indexOf(".") != -1 && (x.lastIndexOf(".") > x.indexOf("AFP -"))) {
                        x = x.substring(x.indexOf("AFP -"), x.lastIndexOf("."));
                    } else {
                        if (x.indexOf("?") != -1 && (x.lastIndexOf("?") > x.indexOf("AFP -"))) {
                            x = x.substring(x.indexOf("AFP -"), x.lastIndexOf("?"));
                        } else {
                            x = x.substring(x.indexOf("AFP -"));
                        }

                    }
                }

                if (x.indexOf("The Atlantic Wire -") != -1) {
                    if (x.indexOf(".") != -1 && (x.lastIndexOf(".") > x.indexOf("The Atlantic Wire -"))) {
                        x = x.substring(x.indexOf("The Atlantic Wire -"), x.lastIndexOf("."));
                    } else {
                        if (x.indexOf("?") != -1 && (x.lastIndexOf("?") > x.indexOf("The Atlantic Wire -"))) {
                            x = x.substring(x.indexOf("The Atlantic Wire -"), x.lastIndexOf("?"));
                        } else {
                            x = x.substring(x.indexOf("The Atlantic Wire -"));
                        }

                    }
                }


                if (x.indexOf("Daily Caller -") != -1) {
                    if (x.indexOf(".") != -1 && (x.lastIndexOf(".") > x.indexOf("Daily Caller -"))) {
                        x = x.substring(x.indexOf("Daily Caller -"), x.lastIndexOf("."));
                    } else {
                        if (x.indexOf("?") != -1 && (x.lastIndexOf("?") > x.indexOf("Daily Caller -"))) {
                            x = x.substring(x.indexOf("Daily Caller -"), x.lastIndexOf("?"));
                        } else {
                            x = x.substring(x.indexOf("Daily Caller -"));
                        }
                    }
                }
                feed.put("Description", x);
                NewsFeeds.add(feed);
            }//for

        }//try
        catch (Exception ex) {
          throw new Exception("Please Check Your Internet connectivity,The Application Can't Reach Rss Service");
          
        }
        return NewsFeeds;
    }

    protected String getElementValue(Element parent, String label) {
        return getCharacterDataFromElement((Element) parent.getElementsByTagName(label).item(0));
    }

    private String getCharacterDataFromElement(Element e) {
        try {
            Node child = e.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        } catch (Exception ex) {
        }
        return "";
    }

    public static void main(String[] args) {
        RssParser parse = new RssParser();
        try {
            //        parse.ReadRSSFeedXmlData("C:\\STCs\\RSS.xml");\
            parse.ParseFeedNews("http://rss.news.yahoo.com/rss/politics");
        } catch (Exception ex) {
            System.out.println("Error That :"+ex.getMessage());
          //  Logger.getLogger(RssParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("k");
    }
}
