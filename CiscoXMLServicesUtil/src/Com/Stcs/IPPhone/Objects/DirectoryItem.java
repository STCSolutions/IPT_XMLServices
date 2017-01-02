package Com.Stcs.IPPhone.Objects;

/**
 * Class represent DirectoryItem in CiscoIPPhoneDirectory XML object
 * @author Amonsif
 */
public class DirectoryItem {

    private String name;
    private String telephone;

    /**
     * Creates a new instance of DirectoryItem
     * @param name DirectoryItem name
     * @param telephone DirectoryItem telephone
     */
    public DirectoryItem(String name, String telephone) {
        setName(name);
        setTelephone(telephone);
    }

    /**
     * Get DirectoryItem name
     * @return DirectoryItem name
     */
    public String getName() {
        return name;
    }

    /**
     * Set DirectoryItem name
     * @param name DirectoryItem name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get DirectoryItem telephone
     * @return DirectoryItem telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set DirectoryItem telephone
     * @param telephone DirectoryItem telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
