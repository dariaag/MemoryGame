/**
 * Created by Daria on 2/13/17.
 *
 */


public class TwoNum extends Locations {
    private static Locations instance = new TwoNum();

    /**
     * private constructor keeps outsider classes from making new instances
     * singleton pattern
     */
    private TwoNum() {
        super();
        add(25);
        add(55);
    }
    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class TwoNumHolder{
        private static final Locations INSTANCE = new TwoNum();
    }
    /**
     *returns static instance
     * @return Locations
     */

    public static Locations getInstance() {return TwoNumHolder.INSTANCE;}

}
