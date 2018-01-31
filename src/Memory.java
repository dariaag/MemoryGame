import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Daria on 2/20/17.
 * Memory.java
 * Mode class to set up and handle Solitaire Mode.
 *@see Mode
 */
public class Memory extends Mode{


    private JPanel cardPanel;

    private Deck deck;
    JLabel scores;
    private Set test;
    private Vector<Card> dealt;
    private Point[] pt;
    private JPanel buttonPanel;
    private JButton turnOverButton;
    private JButton quit;
    private JButton playAgain;
    private String userName;
    private JLabel turns; //shows number of turns
    private JLabel pairs;//shows number of pairs
    private int pairCounter;
    private int turnCounter;
    ScoreStore scoreStore;
    String s = "";//score string for showing scores

    //private JButton Turns;



    /**
     * constructor. Sets up the game
     * @param deck Deck
     * @param cardPanel JPanel
     * @param pt Point[]
     * @param turnOverButton JButton
     * @param buttonPanel JPanel
     * @param quit JButton
     */
    public Memory(Deck deck, JPanel cardPanel, Point[] pt, JButton turnOverButton, JPanel buttonPanel, JButton quit,
                     JLabel turns, JLabel pairs, ScoreStore scoreStore, String userName){
        this.userName = userName;
        this.scoreStore = scoreStore;
        this.deck = deck;
        this.quit = quit;
        this.buttonPanel = buttonPanel;
        this.cardPanel = cardPanel;
        this.pt = pt;
        this.pairs = pairs;
        this.turns = turns;
        playAgain = new JButton("Play Again");
        turnCounter = 0;
        pairCounter = 0;

        deck.shuffle();
        test = new Set();

        dealt = new Vector<Card>(72);


        //displays first 12 cards
        for (int i = 0; i < 72; i++) {
            Card card = deck.deal();
            card.setLocation(pt[i]);
            dealt.add(card);
            dealt.get(i).cover();
            card.setIndex(i);
            cardPanel.add(card);
            card.addActionListener(new CardSolButtonListener());
        }

        //finder = new SetFinder(dealt, 12);
//        finder.findSets();
        this.turnOverButton = turnOverButton;
       turnOverButton.addActionListener(new TurnButtonListener());
    }




    /**changed logic to accommodate memory game
     * main game logic class. Handles events when user presses on the card.
     * When the mouse is clicked checks the validity of the set chosen by user,
     * increments turn and pair counters, highlights and adds cards.
     */
    private class CardSolButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (!test.isFull()) {
                Card c = (Card) event.getSource();


                if (!test.isFull() && !test.contains(c) ) {
                    c.uncover();
                    test.add(c);
                }

                if (test.isFull()) {
                    if (test.isValidSet()) {

                        test.displaySet();
                        dealt.remove(test.get(0));
                        dealt.remove(test.get(1));
                        cardPanel.remove(test.get(0));
                        cardPanel.remove(test.get(1));

                        test.remove();
                        test.remove();
                        pairCounter = pairCounter + 1; // increase pair counter
                        pairs.setText("      Pairs = " + pairCounter);
                        turnCounter = turnCounter + 1; // increase turn counter
                        turns.setText("      Turns = " + turnCounter);
                        cardPanel.revalidate();
                        cardPanel.repaint();

                    } else {

                        test.displayRed();
                        turnCounter = turnCounter + 1;  // increase turn counter
                        turns.setText("      Turns = " + turnCounter);
                        //Turns.setText("Turns = " + turnCounter);
                    }
                }
            }

            if(dealt.isEmpty()){

                cardPanel.removeAll();

                cardPanel.setLayout(new BoxLayout(cardPanel,BoxLayout.PAGE_AXIS));
                dealt.removeAllElements();


                scoreStore.addScore(userName, turnCounter);
                scoreStore.save();
                s = scoreStore.display(userName);
                scores = new JLabel(s);

                cardPanel.add(scores);
                cardPanel.revalidate();
                cardPanel.repaint();
                playAgain.addActionListener(new AgainButtonListener());
                buttonPanel.add(playAgain);
                buttonPanel.revalidate();
                buttonPanel.repaint();


            }


        }
    }

    /**
     * Every time button is clicked, shows one of the available sets on the table. Cycles through all of them,
     * so the user can choose.
     */
    private class TurnButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            if (test.isFull()) {
                test.get(0).cover();
                test.get(1).cover();
                test.remove();
                test.remove();
                cardPanel.revalidate();
                cardPanel.repaint();
            }

        }

    }

    /** added this class
     * handles play again button events
     */

    private class AgainButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            cardPanel.remove(scores);



            deck.restart();
            cardPanel.setLayout(null);
            for (int i = 0; i < 72; i++) {
                Card card = deck.deal();
                card.setLocation(pt[i]);
                dealt.add(card);
                dealt.get(i).cover();
                card.setIndex(i);
                cardPanel.add(card);
                card.addActionListener(new CardSolButtonListener());
            }
            cardPanel.revalidate();
            cardPanel.repaint();

            turnCounter = 0;
            pairCounter = 0;
            buttonPanel.remove(playAgain);
            buttonPanel.revalidate();
            buttonPanel.repaint();

        }

    }
}



