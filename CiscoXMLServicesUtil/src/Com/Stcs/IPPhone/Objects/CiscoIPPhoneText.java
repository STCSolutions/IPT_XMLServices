package Com.Stcs.IPPhone.Objects;

import java.util.Vector;
import org.w3c.dom.Node;

/**
 * This class used to generate CiscoIPPhoneText XML object
 * @author Amonsif
 */
public class CiscoIPPhoneText extends CiscoIPPhoneObject {

    private Node IPPhoneContainerObjectMainNode;

    /**
     * Creates a new instance of CiscoIPPhoneText Class
     * @param title Title of Text page
     * @param prompt Prompt of Text page
     * @param text body of text page
     * @param softkeys Vector of Softkey objects
     */
    public CiscoIPPhoneText(String title, String prompt, String text, Vector<SoftKey> softkeys) {
        IPPhoneContainerObjectMainNode = (Node) document.createElement("CiscoIPPhoneText");
        setTitle(title);
        setPrompt(prompt);
        setText(text);
        setSoftKeys(softkeys);

    }

    /**
     * Use this method to generate CiscoIPPhoneText XML
     * @return String contains CiscoIPPhoneText XML
     */
    public String getTextObject() {
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Title", getTitle());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Prompt", getPrompt());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Text", getText());
        addSoftKeysNode(IPPhoneContainerObjectMainNode);
        document.appendChild(IPPhoneContainerObjectMainNode);
        return getCiscoIPPhoneObjectXML();
    }

    public static void main(String args[]) {
        Vector<SoftKey> softkeys = new Vector();
        softkeys.add(new SoftKey("select", "1", "SoftKey:Select"));
        CiscoIPPhoneText text = new CiscoIPPhoneText("title", "prompt", "dddddd \n dd", softkeys);

        System.out.println(text.getTextObject());
    }
}
