package Com.Stcs.IPPhone.Objects;

import java.util.Date;
import java.util.Vector;
import org.w3c.dom.Node;

/**
 * This class is used to create CiscoIPPhoneGraphicFileMenu XML object
 * @author Amonsif
 */
public class CiscoIPPhoneGraphicFileMenu extends CiscoIPPhoneObject {

    private Node IPPhoneContainerObjectMainNode;

    /**
     * Create new instance of CiscoIPPhoneGraphicFileMenu class
     * @param title Title of GraphiceFileMenu page
     * @param prompt Prompt of GraphiceFileMenu page
     * @param url PNG image url
     * @param graphicMenuItems Vector of GraphicMenuItem objects
     * @param softkeys Vector of Softkey objects
     */
    public CiscoIPPhoneGraphicFileMenu(String title, String prompt, String url, Vector<GraphicMenuItem> graphicMenuItems, Vector<SoftKey> softkeys) {
        IPPhoneContainerObjectMainNode = (Node) document.createElement("CiscoIPPhoneGraphicFileMenu");
        setTitle(title);
        setPrompt(prompt);
        setUrl(url);
        setGraphicMenuItems(graphicMenuItems);
        setSoftKeys(softkeys);
    }

    /**
     * Use this method to generate CiscoIPPhoneGraphicFileMenu xml object
     * @return String contains CiscoIPPhoneGraphicFileMenu xml
     */
    public String getGraphicMenuObject() {
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Title", getTitle());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Prompt", getPrompt());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "URL", getUrl());

        addGraphicMenuItemsNode(IPPhoneContainerObjectMainNode);
        addSoftKeysNode(IPPhoneContainerObjectMainNode);
        document.appendChild(IPPhoneContainerObjectMainNode);
        return getCiscoIPPhoneObjectXML();
    }

    public static void main(String args[]) {
        Vector<SoftKey> softkeys = new Vector();
        softkeys.add(new SoftKey("select", "1", "SoftKey:Select"));

        Vector<GraphicMenuItem> graphicMenuItems = new Vector();
        for (int i = 0; i < 50; i++) {
            graphicMenuItems.add(new GraphicMenuItem("first" + i, "http://localhost:8080", new Point(1 + i, 2), new Point(8 + i, 6)));
        }
        graphicMenuItems.add(new GraphicMenuItem("first", "http://localhost:8080", new Point(1, 2), new Point(8, 6)));
        //graphicMenuItems.add(new GraphicMenuItem("second","http://localhost:8080",new Point(1,2),new Point(8,6)));
        long first = new Date().getTime();
        CiscoIPPhoneGraphicFileMenu graphicFileMenu = new CiscoIPPhoneGraphicFileMenu("title", "prompt", "http://localhost:8080", graphicMenuItems, softkeys);

        System.out.println(graphicFileMenu.getGraphicMenuObject());
        long second = new Date().getTime();
        System.out.println("time " + (second - first));
    }
}
