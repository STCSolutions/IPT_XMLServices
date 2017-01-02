package Com.Stcs.IPPhone.Objects;

import java.util.Vector;
import org.w3c.dom.Node;

/**
 * This class used to generate CiscoIPPhoneInput XML object
 * @author AMonsif
 */
public class CiscoIPPhoneInput extends CiscoIPPhoneObject {

    private Node IPPhoneContainerObjectMainNode;

    /**
     * Creates a new instance of CiscoIPPhoneInput Class
     * @param title Title of Input page
     * @param prompt Prompt of Input page
     * @param url Url of submit button
     * @param inputs Vector of Input objects
     * @param softkeys Vector of Softkey objects
     */
    public CiscoIPPhoneInput(String title, String prompt, String url, Vector<InputItem> inputs, Vector<SoftKey> softkeys) {
        IPPhoneContainerObjectMainNode = (Node) document.createElement("CiscoIPPhoneInput");
        setTitle(title);
        setPrompt(prompt);
        setUrl(url);
        setInputs(inputs);
        setSoftKeys(softkeys);
    }

    /**
     * Use this method to generate CiscoIPPhoneInput XML
     * @return String contains CiscoIPPhoneInput XML
     */
    public String getInputObject() {
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Title", getTitle());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "Prompt", getPrompt());
        addNewSubElementTag(IPPhoneContainerObjectMainNode, "URL", getUrl());
        addInputsNode(IPPhoneContainerObjectMainNode);
        addSoftKeysNode(IPPhoneContainerObjectMainNode);
        document.appendChild(IPPhoneContainerObjectMainNode);
        return getCiscoIPPhoneObjectXML();
    }

    public static void main(String args[]) {
        Vector<SoftKey> softkeys = new Vector();
        softkeys.add(new SoftKey("select", "1", "SoftKey:Select"));

        Vector<InputItem> inputs = new Vector();
        inputs.add(new InputItem("Name", "name", InputItem.ASCII, ""));
        inputs.add(new InputItem("Address", "address", InputItem.NUMERIC, ""));
        CiscoIPPhoneInput input = new CiscoIPPhoneInput("title", "prompt", "http://localhost:8080/ff", inputs, softkeys);

        System.out.println(input.getInputObject());
    }
}
