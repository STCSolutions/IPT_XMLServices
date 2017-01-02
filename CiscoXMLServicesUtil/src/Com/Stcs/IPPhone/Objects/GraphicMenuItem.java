
package Com.Stcs.IPPhone.Objects;
/**
 * This class used to generate GraphicMenuItem XML object
 * @author Amonsif
 */
public class GraphicMenuItem {

    private String name;
    private String url;
    private Point upperLeftPoint;
    private Point lowerRightPoint;

    /**
     * Creates a new instance of GraphicMenuItem
     * @param name name of GraphicMenu item
     * @param url Item url
     * @param upperLeftPoint Object of point class represent the location of upperleft point
     * of GraphicMenuItem on PNG image
     * @param lowerRightPoint Object of point class represent the location of lowerright point
     * of GraphicMenuItem on PNG image
     */
    public GraphicMenuItem(String name, String url, Point upperLeftPoint, Point lowerRightPoint) {
        setName(name);
        setUrl(url);
        setUpperLeftPoint(upperLeftPoint);
        setLowerRightPoint(lowerRightPoint);
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Item button url
     * @return Item button url
     */
    public String getUrl() {
        return url;
    }

    /**
     * set Item button url
     * @param url Item button url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get Upper Left Point
     * @return Upper Left Point
     */
    public Point getUpperLeftPoint() {
        return upperLeftPoint;
    }

    /**
     * Set Upper Left Point
     * @param upperLeftPoint Upper Left Point
     */
    public void setUpperLeftPoint(Point upperLeftPoint) {
        this.upperLeftPoint = upperLeftPoint;
    }

    /**
     * Get Lower Right Point
     * @return Lower Right Point
     */
    public Point getLowerRightPoint() {
        return lowerRightPoint;
    }

    /**
     * Set Lower Right Point
     * @param lowerRightPoint Lower Right Point
     */
    public void setLowerRightPoint(Point lowerRightPoint) {
        this.lowerRightPoint = lowerRightPoint;
    }
}
