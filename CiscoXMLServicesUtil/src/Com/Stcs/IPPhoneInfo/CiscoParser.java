package Com.Stcs.IPPhoneInfo;

import org.xml.sax.*;
import java.io.*;
import java.util.Vector;

/**
 * Use this class to parse xml documents
 */
public class CiscoParser
{
    javax.xml.parsers.DocumentBuilderFactory factory;
    
	/**
	 * Create new instance of CisocParser class
	 */
    public CiscoParser()
    {
        factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        
    }
    
    
    
    
	/**
	 * Use this method to parse any xml document using its InputSource
	 * @param is XML InputSourse object
	 * @param tagName1 get xml value of this tag name
	 * @return Vector of parsed xml
	 * @throws java.lang.Exception Exception
	 */
    public Vector list(InputSource is,String tagName1 ) throws Exception
    {
        javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document document = builder.parse( is );
        org.w3c.dom.NodeList list = document.getElementsByTagName(tagName1);
        
        Vector elements = new Vector();
        elements = listToVector(list);
        
        
        return elements;
    }
    
    private Vector listToVector(org.w3c.dom.NodeList list)
    {
        Vector results = new Vector();
        for (int i = 0; i < list.getLength(); i++)
        {
            org.w3c.dom.Node node = list.item(i);
            results.add(node.getTextContent());
        }
        return results;
    }
    public static void main(String[] args)
    {
        CiscoParser p=new CiscoParser();
        try
        {
            //Vector v=p.list(new InputSource(new StringReader("<row><pkid>{97B01D33-C364-44C2-93E8-C37D336B5F44}</pkid><Name>Personal</Name><URLTemplate>http://192.168.33.201:8084/PersonalDirectory/Login</URLTemplate></row>")),"URLTemplate");
            Vector v=p.list(new InputSource(new FileInputStream(new File("d:\\test.xml"))),"URL");
            for(int i=0;i<v.size();i++)
            {
                System.out.println(i+"="+v.get(i));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
