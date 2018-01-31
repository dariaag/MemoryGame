import java.awt.*;

/**
 * Created by Daria on 2/13/17.
 *@see Symbol
 */
public class Oval extends Symbol {
    /**
     * private constructor prevents outside classes from making new instances. We use the singleton design pattern
     * to enable comparisons.
     */

    private Oval() { }


    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class OvalHolder{
        private static final Symbol INSTANCE = new Oval();

    }

    public static Symbol getInstance() {return OvalHolder.INSTANCE;}
    // the draw method draws an oval

    /**
     * draw oval on the card
     * @param g Graphics
     * @param location int
     * @param color Color
     * @param backGround Color
     * @param shading Shading
     */
    public void draw (Graphics g, int location, Color color, Color backGround, Shading shading) {

        // Draw the oval
        Graphics2D graphs = (Graphics2D) g;
        graphs.setColor(color);
        graphs.setStroke(new BasicStroke(5.0f));
        graphs.drawOval(20, location, 35, 20);

        // Fill the oval
        graphs.setPaint(shading.getPaint(color, backGround));
        graphs.fillOval(20, location, 35, 20);
    }

}
