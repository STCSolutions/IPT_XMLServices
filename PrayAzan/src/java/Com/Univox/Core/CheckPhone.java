/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Com.Univox.Core;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author AMR
 */
public class CheckPhone {
    public boolean IsUsed(String PhoneIP)
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler( null );
            Document doc = db.parse( ( "http://" + PhoneIP + "/StreamingStatisticsX" ) );
            doc.getDocumentElement().normalize();
            NodeList nodelst = doc.getElementsByTagName( "StreamStatus" );
            Node node = nodelst.item( 0 );
            if(node.getFirstChild().getNodeValue().equals( "Not Ready" ))
                return false;
        }
        catch ( SAXException ex )
        {
            return true;
        }
        catch ( Exception ex )
        {
            return true;

        }
        return true;
    }

}
