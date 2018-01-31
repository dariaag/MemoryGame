import java.awt.*;
/**
 * Created by Daria on 2/13/17.
 * Responsible for drawing shading on cards and comparing shadings
 */
abstract class Shading {
    /**
     * returns instance if a particular shading object
     * @return Shading
     */
    public static Shading getInstance() {
        return null;
    }

    abstract public Paint getPaint(Color color, Color filling);
}