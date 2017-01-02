package Com.Stcs.IPPhone.Objects;

/**
 * This class holds all SoftKey Parameter (Name,Url, and Position)
 * @author Amonsif
 */
public class SoftKey {
    /*
     * Main SoftKey& Key Opertaions
     */

    public static String SELECT = "SoftKey:Select";
    public static String EXIT = "SoftKey:Exit";
    public static String SUBMIT = "SoftKey:Submit";
    public static String Erase = "SoftKey:<<";
    public static String SERVICES = "Key:Services";
    public static String SPEAKER = "Key:Speaker";
    public static String MUTE = "Key:Mute";
    public static String MESSAGES = "Key:Messages";
    ///////////
    private String name;
    private String position;
    private String url;

    /**
     * Creates a new instance of SoftKey
     * @param name SoftKey name
     * @param position SoftKey position
     * @param url SoftKey URl
     */
    public SoftKey(String name, String position, String url) {
        setName(name);
        setPosition(position);
        setUrl(url);
    }

    /**
     * Use this method to retrieve SoftKey name.
     * @return SoftKey name
     */
    public String getName() {
        return name;
    }

    /**
     * Use thid method to set SoftKey name.
     * @param name SoftKey name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Use thid method to retrieve SoftKey position.
     * @return SoftKey position.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Use thid method to set SoftKey position.
     * @param position SoftKey position.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Use thid method to retrieve SoftKey URL.
     * @return SoftKey URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Use thid method to set SoftKey URL.
     * @param url SoftKey URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
