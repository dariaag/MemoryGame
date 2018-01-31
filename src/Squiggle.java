import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.awt.*;

/**
 * Created by Daria on 2/13/17.
 * Squiggle.java
 * Responsible for drawing squiggle symbol
 *
 */
public class Squiggle extends Symbol {

    // the singleton design pattern to enable comparisons
    private Squiggle() { }
    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SquiggleHolder{
        private static final Symbol INSTANCE = new Squiggle();

    }

    /**
     * returns static instance of squiggle, singleton pattern
     * @return Symbol
     */
    public static Symbol getInstance() {return SquiggleHolder.INSTANCE;}


    /**
     * draws the squiggle shape
      * @param g Graphics
     * @param location int
     * @param color Color
     * @param backGround Color
     * @param shading Shading
     */
    public void draw  (Graphics g, int location, Color color, Color backGround, Shading shading) {

        // Draw the squiggle
        Polygon p = new Polygon();
        p.addPoint (20, location+15);
        p.addPoint (33, location);
        p.addPoint (43, location+10);
        p.addPoint (55,location+5);
        p.addPoint (43, location+20);
        p.addPoint (33, location+10);

        // Fill the squiggle
        Graphics2D graph = (Graphics2D) g;
        graph.setColor(color);
        graph.setStroke(new BasicStroke(5.0f));
        graph.drawPolygon (p);
        graph.setPaint(shading.getPaint(color, backGround));
        graph.fillPolygon(p);
    }
}
