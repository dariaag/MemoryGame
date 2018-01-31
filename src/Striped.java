import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Daria on 2/13/17.
 * Striped.java
 * @see Shading
 */
public class Striped extends Shading {
    /**
     * private constructor prevents outside classes from making new instances
     */
    private Striped(){ }
    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class StripedHolder{
        private static final Shading INSTANCE = new Striped();

    }
    /**
     * returns static instance of squiggle, singleton pattern
     * @return Shading
     */
    public static Shading getInstance() {return StripedHolder.INSTANCE;}

    /**
     * Paints striped shading using Java Paint interface
     * @param color Color
     * @param backGround Color
     * @return Paint
     */
    public Paint getPaint (Color color, Color backGround) {
        BufferedImage buffImage = new BufferedImage(
                10, 10, BufferedImage.TYPE_INT_RGB );

        Graphics2D gg = buffImage.createGraphics();
        gg.setColor( color );
        gg.fillRect( 0, 0, 10, 5 ); // draw a filled rectangle

        gg.setColor(backGround);
        gg.fillRect( 0, 5, 10, 5 ); // draw a filled rectangle

        // paint buffImage onto the JComponent
        return (new TexturePaint(
                buffImage, new Rectangle( 10, 12 ) ) );
    }
}
