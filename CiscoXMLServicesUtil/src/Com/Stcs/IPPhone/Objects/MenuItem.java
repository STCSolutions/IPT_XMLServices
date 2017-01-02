package Com.Stcs.IPPhone.Objects;

/**
 * Class represent MenuItem inside CiscoIPPhoneMent XML
 * @author Amonsif
 */
public class MenuItem {

    private String name;
    private String url;

    /**
     * Creates a new instance of MenuItem
     * @param name MenuItem Name
     * @param url MenuItem Url
     */
    public MenuItem(String name, String url) {
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
        if (name.length() > 64) {
            name = name.substring(0, 63);
        }
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
        if (url.length() > 256) {
            url = url.substring(0, 255);
        }
        this.url = url;
    }
}
