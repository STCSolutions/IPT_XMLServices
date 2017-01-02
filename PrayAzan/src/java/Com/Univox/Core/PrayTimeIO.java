/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.Univox.Core;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author XPPRESP3
 */
public class PrayTimeIO {

    public Hashtable<String, String> ReadPrayTimesXmlData(String fileName) {
        Hashtable<String, String> PrayTimes = new Hashtable<String, String>();
        try {

            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("PrayTimeNode");
            //System.out.println("Information of all Salats");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("PrayName");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    String PrayName = ((Node) fstNm.item(0)).getNodeValue().toString();

              //      System.out.println("First Name : " + ((Node) fstNm.item(0)).getNodeValue());
                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("PrayTime");
                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                    NodeList lstNm = lstNmElmnt.getChildNodes();
                //    System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
                    String PrayTime = ((Node) lstNm.item(0)).getNodeValue().toString();
                    PrayTimes.put(PrayName, PrayTime);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return PrayTimes;
    }

    public void WriteAzanXmlData(String xmlPrayTimesFile) throws IOException {

         Vector Salats=Prayers.getAllPrayersTime();
        DocumentImpl Doc = new DocumentImpl();
        Node AllSalats = (Node) Doc.createElement("PrayTimeList");
        for (int i = 0; i < Salats.size(); i++) {
            Node PrayNode = (Node) Doc.createElement("PrayTimeNode");
            Node PrayNameNode = (Node) Doc.createElement("PrayName");
            Node PrayNameValue;
            switch(i)
            {
                case 0:

            PrayNameValue = (Node) Doc.createTextNode("Fajer");
            PrayNameNode.appendChild(PrayNameValue);
            PrayNode.appendChild(PrayNameNode);
            break;
                    case 1:

            PrayNameValue = (Node) Doc.createTextNode("Sunrise");
            PrayNameNode.appendChild(PrayNameValue);
            PrayNode.appendChild(PrayNameNode);
            break;
                case 2:
            PrayNameValue = (Node) Doc.createTextNode("Dhuhr");
            PrayNameNode.appendChild(PrayNameValue);
            PrayNode.appendChild(PrayNameNode);
            break;
                case 3:
                     PrayNameValue = (Node) Doc.createTextNode("Asr");
            PrayNameNode.appendChild(PrayNameValue);
            PrayNode.appendChild(PrayNameNode);
            break;
                case 4:
                     PrayNameValue = (Node) Doc.createTextNode("Maghrib");
            PrayNameNode.appendChild(PrayNameValue);
            PrayNode.appendChild(PrayNameNode);
            break;
                case 5:
             PrayNameValue = (Node) Doc.createTextNode("Isha");
            PrayNameNode.appendChild(PrayNameValue);
            PrayNode.appendChild(PrayNameNode);
                   break;
            }
            


            Node PrayTimeNode = (Node) Doc.createElement("PrayTime");
            Node PrayTimeValue = (Node) Doc.createTextNode((String) Salats.get(i));
            PrayTimeNode.appendChild(PrayTimeValue);
            PrayNode.appendChild(PrayTimeNode);
            AllSalats.appendChild(PrayNode);
        }
        Doc.appendChild(AllSalats);
        FileOutputStream fos = new FileOutputStream(xmlPrayTimesFile);

        OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
        of.setIndent(1);
        of.setIndenting(true);
        
        XMLSerializer serializer = new XMLSerializer(fos, of);
        serializer.asDOMSerializer();
        serializer.serialize(Doc.getDocumentElement());
        fos.close();

    }


    public boolean doForceReload() throws IOException
    {

        Properties Propfile=new Properties();
        Propfile.load(new FileInputStream(new File("C:\\STCs\\Location.properties")));

       String isForceReload =Propfile.getProperty("ForceReLoad");
       if(isForceReload != null && isForceReload.equals("false"))
           return false;
       else
           return  true;
    }



/*public void WritePhoneListeners(String RegisteredUserXml) throws IOException {


        DocumentImpl Doc = new DocumentImpl();
        EmployeOperations OP=new EmployeOperations();
        Node AllPhones = (Node) Doc.createElement("RegisteredUsers");
        ArrayList<String> Exts=OP.getExtentions();
        for(int i=0;i<Exts.size();i++)
        {
            Node UserNode = (Node) Doc.createElement("User");
            Node UserExtNode = (Node) Doc.createElement("PhoneExt");
            Node UserExtValue=(Node) Doc.createTextNode(Exts.get(i));
            UserExtNode.appendChild(UserExtValue);
            UserNode.appendChild(UserExtNode);
            AllPhones.appendChild(UserNode);
        }


        Doc.appendChild(AllPhones);
        FileOutputStream fos = new FileOutputStream(RegisteredUserXml);

        OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
        of.setIndent(1);
        of.setIndenting(true);

        XMLSerializer serializer = new XMLSerializer(fos, of);
        serializer.asDOMSerializer();
        serializer.serialize(Doc.getDocumentElement());
        fos.close();

    }




        public ArrayList<String> getIPPhoneListeners(String fileName) {
       ArrayList<String> ExtList = new ArrayList<String>();
        try {

            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("User");
           // System.out.println("Information of all Registerd Users Ext");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("PhoneExt");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    String PhoneExt = ((Node) fstNm.item(0)).getNodeValue().toString();

             //       System.out.println("First Name : " + ((Node) fstNm.item(0)).getNodeValue());

                    ExtList.add(PhoneExt);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ExtList;
    }*/

    public static void main(String[] args) throws IOException {
     
       PrayTimeIO wruter = new PrayTimeIO();
//      Hashtable<String,String> x= wruter.ReadPrayTimesXmlData("D:\\Work\\STCs\\PrayerTimes\\PraysTimes.xml");
//        System.out.println(x.size());
    //     wruter.WriteAzanXmlData("C:\\STCs\\PraysTimes.xml");
    //   System.out.println( wruter.getIPPhoneListeners("C:\\STCs\\RegisteredUser.xml").toString());

    }
}
