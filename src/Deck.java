import java.util.Random;
import java.awt.*;

/**
 * Created by Daria on 2/14/17.
 *
 */
public class Deck {

    private static final int DECK_SIZE = 72;
    private static final int UNIQUE_SIZE = 36;
    private Card[] deck;

    private int cardToDeal;


    /**
     * constructor
     */

    // the constructor initializes the array and then each
    // card in the array
    public Deck () {
        Card[] unique_deck = new Card[UNIQUE_SIZE];

        deck = new Card[DECK_SIZE];
        cardToDeal = 0;

        // We maintain arrays of the properties of each card
        // By reusing the same properties, we allow == and != to work
        Color colors[] = {new Color(153, 204, 255), new Color(0, 204, 0), new Color(255, 192, 203)};
        Shading shadings[] = {Filled.getInstance(), Outlined.getInstance()};
        Symbol symbols[] = {Oval.getInstance(), Diamond.getInstance(), Squiggle.getInstance()};
        Locations locations[] = {OneNum.getInstance(), ThreeNum.getInstance()};

        //36 unique cards, put two of each in the deck
        int z = 0;
        for (int i = 0; i < UNIQUE_SIZE; i++) {

            Card card = new Card(colors[(i / 12)], shadings[(i / 2) % 2], locations[i % 2], symbols[(i / 4) % 3]);
            Card card2 = new Card(colors[(i / 12)], shadings[(i / 2) % 2], locations[i % 2], symbols[(i / 4) % 3]);
            unique_deck[i] = card;
            deck[z] = card;
            deck[z+1] = card2;
            z += 2;
        }





        shuffle();

    }



    /**
     * reused code from data structures Crazy Eights project
     */
    public void shuffle() {

        Random shuffleIndex = new Random();
        int newIndex;

        //the cardHolder will hold the value of the card object while swapping
        Card cardHolder;

        for (int i = 0; i < DECK_SIZE; i++) {

            //gives new index from random numbers 0-51

            newIndex = shuffleIndex.nextInt(DECK_SIZE);

            //swapping cards

            cardHolder = deck[i];
            deck[i] = deck[newIndex];
            deck[newIndex] = cardHolder;

        }


    }



    /**
     * reused code from data structures Crazy Eights project
     */


    /**
     * returns the index of card to be dealt
     * @return int
     */
    public int getCard() {

        return cardToDeal;
    }

    /**deals a card
     *
     * @return card object
     */
    public Card deal() {
        if (cardToDeal < DECK_SIZE) {

            Card currentCard = deck[cardToDeal];
            cardToDeal++;
            return currentCard;


        } else {


            return null;

        }


    }

    /**
     * checks if the deck is empty. FOr set game it meeds to have at least 73 cards.
     * @return boolean
     */

    public boolean isEmpty(){
        return cardToDeal>=72; //specific to set game
    }

    /**
     * instead of creating new Deck for every game, we simply restart the top card. Allows us to use the same deck.
     */

    public void restart(){
        cardToDeal = 0;
    }



}
