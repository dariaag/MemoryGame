/**
 * Created by Daria on 2/18/17.
 *SetFinder.java
 * finds sets in the currently dealt cards
 */
import java.util.*;
public class SetFinder {


    private Set test;// not sure about this
    private Card[] input;// input from table
    private ArrayList<Card[]> validSets;

    int l;

    /**
     *constructor
     * @param dealtCards card[]
     * @param size int
     */
    public SetFinder(Card[] dealtCards, int size){
        test = new Set();
        input = dealtCards;
        l = size;
        validSets = new ArrayList<Card[]>();
    }

    /**
     * Gets the ArrayList of valid sets
     * @return ArrayList of valid sets
     */
    public ArrayList<Card[]> getSets (){
        return validSets;
    }



    /**
     *finds all the valid set combinations in an array of cards
     * @param
     */
   public void findSets(){
       for (int i=0; i<l-2; i++) {
           Card cd = input[i];
           test.add(cd);
           for (int j=i+1; j<l-1; j++) {
               Card cd2 = input[j];
               test.add(cd2);
               for (int k=j+1; k<l; k++) {
                   Card cd3 = input[k];
                   test.add(cd3);
                   if (test.isValidSet()) {
                       Card[] valid = {cd, cd2, cd3};
                       validSets.add(valid);// add to sets
                   }
                   test.remove();
               }
               test.remove();
           }
           test.remove();
       }
   }


}




