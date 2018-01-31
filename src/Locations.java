import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Daria on 2/13/17.
 * Locations.java
 * Keeps track of number of symbols on card and their locations for drawing them
 */
abstract class Locations {

    private ArrayList<Integer> l;
    public Locations() {l = new ArrayList<Integer>();}
    public static Locations getInstance() {return null;}
    public int getSize() {return l.size();}
    public int getLoc(int i) {return l.get(i);}
    protected boolean add(int i) {return l.add(i);}

}
