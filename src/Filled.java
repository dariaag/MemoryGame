import java.awt.*;

/**
 * Created by Daria on 2/13/17.
 * @see Shading
 * Filled.java
 */
class Filled extends Shading {

    // We have exactly one instance of the filled class


    /**
     * The private constructor keeps outsiders from making instances
     */

    private Filled(){ }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class FilledHolder{

        private static final Shading INSTANCE = new Filled();

    }

    /**
     * returns static intance
     * @return Shading
     */
    public static Shading getInstance() {return FilledHolder.INSTANCE;}

    /**
     *filled uses the foreground color to fill the card
     */
    public Paint getPaint (Color color, Color backGround) {return color;}
}
