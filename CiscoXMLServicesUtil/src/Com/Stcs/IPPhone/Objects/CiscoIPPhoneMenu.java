package Com.Stcs.IPPhone.Objects;

import java.util.Date;
import java.util.Vector;
import org.w3c.dom.Node;

/**
 * This class used to generate CiscoIPPhoneMenu XML object
 * @author Amonsif
 */
public class CiscoIPPhoneMenu extends CiscoIPPhoneObject {

    private Node IPPhoneContainerObjectMainNode;

    /**
     * Creates a new instance of CiscoIPPhoneMenu Class
     * @param title Title of Menu page
     * @param prompt Prompt of Menu page
     * @param menuItems Vector of MenuItem objects
     * @param softkeys Vector of Softkey objects
     */
    public CiscoIPPhoneMenu(String title, String prompt, Vector<MenuItem> menuItems, Vector<SoftKey> softkeys) {
        IPPhoneContainerObjectMainNode = (Node) document.createElement("CiscoIPPhoneMenu");
        setTitle(title);
        setPrompt(prompt);
        setMenuItems(menuItems);
        setSoftKeys(softkeys);
    }

    /**
     * Use this method to generate CiscoIPPhoneMenu XML
     * @return String contains CiscoIPPhoneMenu XML
     */
    public String getMenuObject() {
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Title", getTitle());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Prompt", getPrompt());
        addMenuItemsNode(IPPhoneContainerObjectMainNode);
        addSoftKeysNode(IPPhoneContainerObjectMainNode);
        document.appendChild(IPPhoneContainerObjectMainNode);
        return getCiscoIPPhoneObjectXML();
    }

    public static void main(String args[]) {
        long first = new Date().getTime();
        Vector<SoftKey> softkeys = new Vector();
        softkeys.add(new SoftKey("select", "1", "SoftKey:Select"));

        Vector<MenuItem> menuItems = new Vector();
        for (int i = 0; i < 100; i++) {
            menuItems.add(new MenuItem("first" + i, "http://localhost:8080"));
        }

        CiscoIPPhoneMenu menu = new CiscoIPPhoneMenu("title", "prompt", menuItems, softkeys);
        // long first = new Date().getTime();

        System.out.println(menu.getMenuObject());
        long second = new Date().getTime();
        System.out.println("time " + (second - first));
    }
}
