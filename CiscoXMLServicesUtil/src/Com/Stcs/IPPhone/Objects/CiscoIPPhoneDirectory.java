package Com.Stcs.IPPhone.Objects;

import java.util.Vector;
import org.w3c.dom.Node;

/**
 * This class is used to create CiscoIPPhoneDirectory XML object
 * @author Amonsif
 */
public class CiscoIPPhoneDirectory extends CiscoIPPhoneObject {

    private Node IPPhoneContainerObjectMainNode;

    /**
     * Create new instance of CiscoIPPhoneDirectory class
     * @param title Title of Directory page
     * @param prompt prompt of Directory page
     * @param directoryItems Vector contains DirectoryItem objects
     * @param softkeys Vector contains Softtey objects
     */
    public CiscoIPPhoneDirectory(String title, String prompt, Vector<DirectoryItem> directoryItems, Vector<SoftKey> softkeys) {
        IPPhoneContainerObjectMainNode = (Node) document.createElement("CiscoIPPhoneDirectory");
        setTitle(title);
        setPrompt(prompt);
        setDirectoryItems(directoryItems);
        setSoftKeys(softkeys);
    }

    /**
     * Use this method to create String contains CiscoIPPhoneDirectory xml
     * @return String Contain CiscoIPPhoneDirectory object xml
     */
    public String getDirectoryObject() {
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Title", getTitle());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Prompt", getPrompt());
        addDirectoryItemsNode(IPPhoneContainerObjectMainNode);
        addSoftKeysNode(IPPhoneContainerObjectMainNode);
        document.appendChild(IPPhoneContainerObjectMainNode);
        return getCiscoIPPhoneObjectXML();
    }

    public static void main(String args[]) {
        Vector<SoftKey> softkeys = new Vector();
        softkeys.add(new SoftKey("select", "1", "SoftKey:Select"));

        Vector<DirectoryItem> directoryItems = new Vector();
        directoryItems.add(new DirectoryItem("first", "5598"));
        directoryItems.add(new DirectoryItem("second", "2235"));
        CiscoIPPhoneDirectory text = new CiscoIPPhoneDirectory("title", "prompt", directoryItems, softkeys);

        System.out.println(text.getDirectoryObject());
    }
}
