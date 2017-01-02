package Com.Stcs.IPPhone.Objects;

import java.util.Vector;
import org.w3c.dom.Node;

/**
 * This class used to generate CiscoIPPhoneImageFile XML object
 * @author Amonsif
 */
public class CiscoIPPhoneImageFile extends CiscoIPPhoneObject {

    private Node IPPhoneContainerObjectMainNode;

    /**
     * Creates a new instance of CiscoIPPhoneImageFile Class
     * @param url PNG image url
     * @param locationX PNG Image X Location on the IPPhone Screen.
     * @param locationY PNG Image Y Location on the IPPhone Screen.
     * @param title Title of CiscoIPPhoneImageFile Page
     * @param prompt Prompt of CiscoIPPhoneImageFile Page
     * @param softkeys Vector of Softkey objects
     */
    public CiscoIPPhoneImageFile(String title, String prompt, String url, int locationX, int locationY, Vector<SoftKey> softkeys) {
        IPPhoneContainerObjectMainNode = (Node) document.createElement("CiscoIPPhoneImageFile");
        setTitle(title);
        setPrompt(prompt);
        setLocationX(locationX);
        setLocationY(locationY);
        setUrl(url);
        setSoftKeys(softkeys);

    }

    /**
     * Use this method to generate CiscoIPPhoneImageFile XML
     * @return String contains CiscoIPPhoneImageFile XML
     */
    public String getImageFileObject() {
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Title", getTitle());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Prompt", getPrompt());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "LocationX", String.valueOf(getLocationX()));
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "LocationY", String.valueOf(getLocationY()));
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "URL", getUrl());
        addSoftKeysNode(IPPhoneContainerObjectMainNode);
        document.appendChild(IPPhoneContainerObjectMainNode);
        return getCiscoIPPhoneObjectXML();
    }

    public static void main(String args[]) {
        Vector<SoftKey> softkeys = new Vector();
        softkeys.add(new SoftKey("select", "1", "SoftKey:Select"));
        CiscoIPPhoneImageFile imageFile = new CiscoIPPhoneImageFile("title", "prompt", "http://localhost:8080", 0, 0, softkeys);

        System.out.println(imageFile.getImageFileObject());
    }
}
