package Com.Stcs.IPPhone.Objects;

import java.util.Date;
import java.util.Vector;
import org.w3c.dom.Node;

/**
 * This class used to generate CiscoIPPhoneIconFileMenu XML object
 * @author Amonsif
 */
public class CiscoIPPhoneIconFileMenu extends CiscoIPPhoneObject {

    private Node IPPhoneContainerObjectMainNode;

    /**
     * Creates a new instance of CiscoIPPhoneIconFileMenu Class
     * @param title Title of Menu page
     * @param prompt Prompt of Menu page
     * @param iconMenuItems Vector of IconMenuItem objects
     * @param softkeys Vector of Softkey objects
     */
    public CiscoIPPhoneIconFileMenu(String title, String prompt, Vector<IconMenuItem> iconMenuItems, Vector<IconItem> iconItems, Vector<SoftKey> softkeys) {
        IPPhoneContainerObjectMainNode = (Node) document.createElement("CiscoIPPhoneIconFileMenu");
        setTitle(title);
        setPrompt(prompt);
        setIconMenuItems(iconMenuItems);
        setIconItems(iconItems);
        setSoftKeys(softkeys);
    }

    /**
     * Use this method to generate CiscoIPPhoneIconFileMenu XML
     * @return String contains CiscoIPPhoneIconFileMenu XML
     */
    public String getIconMenuObject() {
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Title", getTitle());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Prompt", getPrompt());
        addIconMenuItemsNode(IPPhoneContainerObjectMainNode);
        addIconItemsNode(IPPhoneContainerObjectMainNode);
        addSoftKeysNode(IPPhoneContainerObjectMainNode);
        document.appendChild(IPPhoneContainerObjectMainNode);
        return getCiscoIPPhoneObjectXML();
    }

    public static void main(String args[]) {
        long first = new Date().getTime();
        Vector<SoftKey> softkeys = new Vector();
        softkeys.add(new SoftKey("select", "1", "SoftKey:Select"));

        Vector<IconMenuItem> menuItems = new Vector();
        for (int i = 0; i < 3; i++) {
            menuItems.add(new IconMenuItem(0, "first" + i, "http://localhost:8080"));
        }

        Vector<IconItem> iconItems = new Vector();
        for (int i = 0; i < 1; i++) {
            iconItems.add(new IconItem(i, "http://localhost:8080"));
        }

        CiscoIPPhoneIconFileMenu menu = new CiscoIPPhoneIconFileMenu("title", "prompt", menuItems, iconItems, softkeys);
        // long first = new Date().getTime();

        System.out.println(menu.getIconMenuObject());
        long second = new Date().getTime();
        System.out.println("time " + (second - first));
    }
}
