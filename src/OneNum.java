/**
 * Created by Daria on 2/13/17.
 * @see Locations
 */
class OneNum extends Locations {
    private static Locations instance = new OneNum();

    /**
     * private constructor prevents outside classes from making new instances
     */
    private OneNum() {
        super();
        add(40);
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class OneNumHolder{
        private static final Locations INSTANCE = new OneNum();
    }
    /**
     * returns static instance of squiggle, singleton pattern
     * @return Locations
     */

    public static Locations getInstance() {return OneNumHolder.INSTANCE;}

}
