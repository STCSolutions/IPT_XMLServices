package Com.Stcs.IPPhone.Objects;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * *Parent Class which Hold all Fields and methods used by IP Phone Different XML Objects.
 * @author AMonsif
 */
public abstract class CiscoIPPhoneObject {

    private String title;
    private String prompt;
    private String text;
    private String url;
    private int locationX;
    private int locationY;
    private Vector<SoftKey> softKeys = new Vector();
    private Vector<MenuItem> menuItems = new Vector();
    private Vector<GraphicMenuItem> graphicMenuItems = new Vector();
    private Vector<InputItem> inputs = new Vector();
    private Vector<DirectoryItem> directoryItems = new Vector();
    private Vector<IconMenuItem> iconMenuItems = new Vector();
    private Vector<IconItem> iconItems = new Vector();
    DocumentImpl document;

    /** Creates a new instance of CiscoIPPhoneObject */
    public CiscoIPPhoneObject() {
        document = new DocumentImpl();
    }

    /**
     * Use this method to add tag to xml node for example 
     * <CiscoIPPhoneText>
     *    <Text>hello<Text>
     * <CiscoIPPhoneText>
     * CiscoIPPhoneText is node, Text is a tag we want to add it to the node.
     * @param parentNode Object of org.w3c.dom.Node to add tag on it.
     * @param subElementTagName String contains Xml tag name
     * @param subElementTagValue String contains Xml tag value
     */
    protected void addNewSubElementTag(Node parentNode, String subElementTagName, String subElementTagValue) {
        Node newSubElementTag = (Node) document.createElement(subElementTagName);
        Text tagText = document.createTextNode(subElementTagValue);
        newSubElementTag.appendChild(tagText);
        parentNode.appendChild(newSubElementTag);
    }

    /**
     * use this method to add softkey node to the main CiscoIPPhone node
     * @param ciscoNode Object of org.w3c.dom.Node
     */
    protected void addSoftKeysNode(Node IPPhoneContainerObjectNode) {
        for (int index = 0; index < softKeys.size(); index++) {
            SoftKey softKeyElement = softKeys.get(index);

            Node softKey = (Node) document.createElement("SoftKeyItem");

            addNewSubElementTag(softKey, "Name", softKeyElement.getName());
            addNewSubElementTag(softKey, "URL", softKeyElement.getUrl());
            addNewSubElementTag(softKey, "Position", softKeyElement.getPosition());

            IPPhoneContainerObjectNode.appendChild(softKey);
        }

    }

    protected void addMenuItemsNode(Node IPPhoneContainerObjectMainNode) {
        int length = (menuItems.size() > 100) ? 100 : menuItems.size();
        for (int index = 0; index < length; index++) {
            MenuItem menuItemElement = menuItems.get(index);
            //System.out.println(menuItemElement.name);
            Node menuNode = (Node) document.createElement("MenuItem");

            addNewSubElementTag(menuNode, "Name", menuNameLengthChecker(menuItemElement.getName()));
            addNewSubElementTag(menuNode, "URL", urlLengthChecker(menuItemElement.getUrl()));

            IPPhoneContainerObjectMainNode.appendChild(menuNode);
        }
    }

    protected void addGraphicMenuItemsNode(Node IPPhoneContainerObjectMainNode) {
        int length = (graphicMenuItems.size() > 32) ? 32 : graphicMenuItems.size();
        for (int index = 0; index < length; index++) {
            GraphicMenuItem graphicMenuItemElement = graphicMenuItems.get(index);
            //System.out.println(menuItemElement.name);
            Node graphicMenuNode = (Node) document.createElement("MenuItem");

            addNewSubElementTag(graphicMenuNode, "Name", menuNameLengthChecker(graphicMenuItemElement.getName()));
            addNewSubElementTag(graphicMenuNode, "URL", urlLengthChecker(graphicMenuItemElement.getUrl()));


            Node graphicMenuTouch = (Node) document.createElement("TouchArea");
            Element touchElement = (Element) graphicMenuTouch;
            touchElement.setAttribute("X1", String.valueOf(graphicMenuItemElement.getUpperLeftPoint().getX()));
            touchElement.setAttribute("Y1", String.valueOf(graphicMenuItemElement.getUpperLeftPoint().getY()));
            touchElement.setAttribute("X2", String.valueOf(graphicMenuItemElement.getLowerRightPoint().getX()));
            touchElement.setAttribute("Y2", String.valueOf(graphicMenuItemElement.getLowerRightPoint().getY()));

            graphicMenuNode.appendChild(graphicMenuTouch);
            IPPhoneContainerObjectMainNode.appendChild(graphicMenuNode);
        }
    }

    protected void addInputsNode(Node IPPhoneContainerObjectMainNode) {
        int length = (inputs.size() > 5) ? 5 : inputs.size();
        for (int index = 0; index < length; index++) {
            InputItem inputElement = inputs.get(index);
            Node inputNode = (Node) document.createElement("InputItem");

            addNewSubElementTag(inputNode, "DisplayName", inputElement.getDisplayName());
            addNewSubElementTag(inputNode, "QueryStringParam", inputElement.getQueryStringParam());
            addNewSubElementTag(inputNode, "InputFlags", inputElement.getInputFlags());
            addNewSubElementTag(inputNode, "DefaultValue", inputElement.getDefaultValue());


            IPPhoneContainerObjectMainNode.appendChild(inputNode);
        }
    }

    protected void addDirectoryItemsNode(Node IPPhoneContainerObjectMainNode) {
        int length = (directoryItems.size() > 32) ? 32 : directoryItems.size();
        for (int index = 0; index < length; index++) {
            DirectoryItem directoryItemElement = directoryItems.get(index);

            Node directoryNode = (Node) document.createElement("DirectoryEntry");

            addNewSubElementTag(directoryNode, "Name", menuNameLengthChecker(directoryItemElement.getName()));
            addNewSubElementTag(directoryNode, "Telephone", directoryItemElement.getTelephone());

            IPPhoneContainerObjectMainNode.appendChild(directoryNode);
        }
    }

    protected void addIconMenuItemsNode(Node IPPhoneContainerObjectMainNode) {
        int length = (iconMenuItems.size() > 32) ? 32 : iconMenuItems.size();
        for (int index = 0; index < length; index++) {
            IconMenuItem menuItemElement = iconMenuItems.get(index);
            //System.out.println(menuItemElement.name);
            Node menuNode = (Node) document.createElement("MenuItem");

            addNewSubElementTag(menuNode, "IconIndex", Integer.toString(menuItemElement.getIndex()));
            addNewSubElementTag(menuNode, "Name", menuNameLengthChecker(menuItemElement.getName()));
            addNewSubElementTag(menuNode, "URL", urlLengthChecker(menuItemElement.getUrl()));

            IPPhoneContainerObjectMainNode.appendChild(menuNode);
        }
    }

    protected void addIconItemsNode(Node IPPhoneContainerObjectMainNode) {
        int length = (iconItems.size() > 10) ? 10 : iconItems.size();
        for (int index = 0; index < length; index++) {
            IconItem iconItemElement = iconItems.get(index);
            //System.out.println(menuItemElement.name);
            Node iconNode = (Node) document.createElement("IconItem");
            addNewSubElementTag(iconNode, "Index", Integer.toString(iconItemElement.getIndex()));
            addNewSubElementTag(iconNode, "URL", urlLengthChecker(iconItemElement.getUrl()));

            IPPhoneContainerObjectMainNode.appendChild(iconNode);
        }
    }

    /**
     * Use this method to retrieve all iconMenuItems
     * @return Vector of MenuItem objects
     */
    public Vector<IconMenuItem> getIconMenuItems() {
        return iconMenuItems;
    }

    /**
     * Use this method to set the vector of IconMenuItems
     * @param iconMenuItems Vector of IconMenuItem objects
     */
    public void setIconMenuItems(Vector<IconMenuItem> iconMenuItems) {
        this.iconMenuItems = iconMenuItems;
    }

    /**
     * Use this method to retrieve all iconItems
     * @return Vector of IconItem objects
     */
    public Vector<IconItem> getIconItems() {
        return iconItems;
    }

    /**
     * Use this method to set the vector of IconItems
     * @param iconItems Vector of IconItem objects
     */
    public void setIconItems(Vector<IconItem> iconItems) {
        this.iconItems = iconItems;
    }

    /**
     * Use this method to get CiscoIPPhone object's title
     * @return String contains CiscoIPPhone object title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Use this method to set title string of the CiscoIPPhone object
     * @param title Title of CiscoIPPhone object
     */
    public void setTitle(String title) {
        if (title.length() > 32) {
            title = title.substring(0, 31);
        }
        this.title = title;
    }

    /**
     * Use this method to get CiscoIPPhone object's prompt
     * @return String contains CiscoIPPhone object's prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Use this method to set prompt string of the CiscoIPPhone object
     * @param prompt Prompt of CiscoIPPhone object
     */
    public void setPrompt(String prompt) {
        if (prompt.length() > 32) {
            prompt = prompt.substring(0, 31);
        }
        this.prompt = prompt;
    }

    /**
     * Use this method to retreive PNG image url.
     * @return PNG image url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Use this method to set the PNG image url.
     * @param url PNG image url
     */
    public void setUrl(String url) {
        this.url = urlLengthChecker(url);
    }

    /**
     * Use this method to retrieve LocationX
     * @return LocationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * Ust this method to set LocationX
     * @param locationX PNG image LocationX
     */
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    /**
     * Use this method to retrieve LocationY
     * @return LocatinoY
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     * Use this method to set LocationX
     * @param locationY PNG image LocationY
     */
    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    /**
     * Use this method to retreive all Directory items.
     * @return Vector contains DirectoyItem objects
     */
    public Vector<DirectoryItem> getDirectoryItems() {
        return directoryItems;
    }

    /**
     * Use this method to set the vector of DirectoryItem objects
     * @param directoryItems Vector contains DirectoryItem objects
     */
    public void setDirectoryItems(Vector<DirectoryItem> directoryItems) {
        this.directoryItems = directoryItems;
    }

    /**
     * Use this method to retrieve all InputItems
     * @return Vector of InputItem objects
     */
    public Vector<InputItem> getInputs() {
        return inputs;
    }

    /**
     * Use this method to set the vector of InputItems
     * @param inputs Vector of InputItem objects.
     */
    public void setInputs(Vector<InputItem> inputs) {
        this.inputs = inputs;
    }

    /**
     * Use this method to retrieve all MenuItems
     * @return Vector of MenuItem objects
     */
    public Vector<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Use this method to set the vector of MenuItems
     * @param menuItems Vector of MenuItem objects
     */
    public void setMenuItems(Vector<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Use this method to retrieve Softkey objects
     * @return Vector of Softkey objects.
     */
    public Vector<SoftKey> getSoftKeys() {
        return softKeys;
    }

    /**
     * Use this method to set the Softkeys
     * @param softKeys Vector of Softkey objects
     */
    public void setSoftKeys(Vector<SoftKey> softKeys) {
        this.softKeys = softKeys;
    }

    /**
     * Use this method to retreive all GraphicMenuItems
     * @return Vector of GraphicMenuItem objects
     */
    public Vector<GraphicMenuItem> getGraphicMenuItems() {
        return graphicMenuItems;
    }

    /**
     * Use this method to set the vector of GraphicMenuItems
     * @param graphicMenuItems Vector of GraphicMenuItem objects
     */
    public void setGraphicMenuItems(Vector<GraphicMenuItem> graphicMenuItems) {
        this.graphicMenuItems = graphicMenuItems;
    }

    /**
     * Use this method to retrieve text
     * @return String contains page text
     */
    public String getText() {
        return text;
    }

    /**
     * Use this method to set the page text
     * @param text page text
     */
    public void setText(String text) {
        if (text.length() > 4000) {
            text = text.substring(0, 3995) + "...";
        }
        this.text = text;
    }

    public String urlLengthChecker(String url) {
        url = (url.length() < 256) ? url : url.substring(0, 255);
        return url;
    }

    public String menuNameLengthChecker(String name) {
        name = (name.length() < 64) ? name : name.substring(0, 63);
        return name;
    }

    /**
     * Use this method to get complete CiscoIPPhone xml object in String
     * @return String contains CiscoIPPhone xml object
     */
    protected String getCiscoIPPhoneObjectXML() {
        OutputFormat format = new OutputFormat(document);
        //format.setMethod("xml");
        format.setPreserveSpace(false);
        format.setIndent(2);
        format.setLineSeparator(LineSeparator.Web);
        //format.setLineWidth(5);

        StringWriter stringOut = new StringWriter(); //Writer will be a String
        XMLSerializer serial = new XMLSerializer(stringOut, format);
        try {
            serial.asDOMSerializer(); // As a DOM Serializer
            serial.serialize(document.getDocumentElement());
        } catch (IOException ex) {
            ex.printStackTrace();
        } // As a DOM Serializer

        return stringOut.toString();
    }
}
