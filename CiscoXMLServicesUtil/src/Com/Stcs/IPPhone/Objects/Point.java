package Com.Stcs.IPPhone.Objects;

/**
 * class represent the point locatin o
 * @author Amonsif
 */
public class Point {

    private int x;
    private int y;

    /**
     * Creates a new instance of Point
     * @param x x location
     * @param y y location
     */
    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Use this method to retrieve point X value.
     * @return Point X value
     */
    public int getX() {
        return x;
    }

    /**
     * Set Point X
     * @param x Point X value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Use this method to retrieve point Y value.
     * @return Point Y value.
     */
    public int getY() {
        return y;
    }

    /**
     * Set Point X
     * @param y Point Y value
     */
    public void setY(int y) {
        this.y = y;
    }
}
