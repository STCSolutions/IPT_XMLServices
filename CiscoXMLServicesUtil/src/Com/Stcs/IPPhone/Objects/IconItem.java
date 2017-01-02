package Com.Stcs.IPPhone.Objects;

/**
 * Class represent IconItem inside CiscoIPPhoneIconFileMenu XML
 * @author Amonsif
 */
public class IconItem {

    private int index;
    private String url;

    /**
     * Creates a new instance of IconItem
     * @param index Icon index
     * @param url Icon png image url
     */
    public IconItem(int index, String url) {
        this.setIndex(index);
        this.setUrl(url);
    }

    /**
     * Use this method to get Icon index
     * @return Icon index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Use this method to set icon index
     * @param index Icon index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Use this method to get icon png image url
     * @return Icon png image url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Use this method to set icon png image url
     * @param url Icon png image url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
