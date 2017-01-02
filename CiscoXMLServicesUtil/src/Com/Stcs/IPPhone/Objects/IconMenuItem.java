package Com.Stcs.IPPhone.Objects;

/**
 * Class represent MenuItem inside CiscoIPPhoneIconFileMenu XML
 * @author Amonsif
 */
public class IconMenuItem {

    private int index;
    private String name;
    private String url;

    /**
     * Creates a new instance of IconMenuItem
     * @param index IconMenuItem IconIndex
     * @param name IconMenuItem Name
     * @param url IconMenuItem Url
     */
    public IconMenuItem(int index, String name, String url) {
        setIndex(index);
        setName(name);
        setUrl(url);
    }

    /**
     * Get MenuItem name
     * @return MenuItem name
     */
    public String getName() {
        return name;
    }

    /**
     * Set MenuItem name
     * @param name MenuItem name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get MenuItem url
     * @return MenuItem url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set MenuItem url
     * @param url MenuItem url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get Icon index
     * @return Icon index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set Icon index
     * @param index Icon index
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
