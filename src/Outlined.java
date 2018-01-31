import java.awt.*;

/**
 * Created by Daria on 2/13/17.
 * @see Shading
 *
 */
public class Outlined extends Shading{

    /**
     * private constructor prevents outside classes from making new instances
     */
    private Outlined(){ }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class OutlinedHolder{
        private static final Shading INSTANCE = new Outlined();

    }
    /**
     * returns static instance of squiggle, singleton pattern
     * @return Shading
     */
    public static Shading getInstance() {return OutlinedHolder.INSTANCE;}

    /**
     * using paint interface to fill the symbols
     * @param color Color
     * @param backGround Color
     * @return Paint
     */
    public Paint getPaint (Color color, Color backGround) {return backGround;}
}
