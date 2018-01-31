import java.awt.*;

/**
 * Created by Daria on 2/13/17.
 *Abstract class for symbols
 * Draws and returns symbols for comparison
 */
abstract class Symbol {
    /**
     * returns instance of a particular Symbol object
     * @return Symbol
     */
    public static Symbol getInstance() {
        return null;
    }

    /**
     * draws symbol
     * @param g Graphics
     * @param location int
     * @param color Color
     * @param background Color
     * @param shading Shading
     */
    abstract public void draw(Graphics g, int location, Color color, Color background, Shading shading);
//    abstract public void cover(Graphics g, int location, Color color, Color background, Shading shading);

}