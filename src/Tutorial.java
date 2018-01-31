import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;

/**
 * Created by Daria on 2/20/17.
 * Last modified by Catherine on 2/20/17
 * Mode class to set up and handle Solitaire Mode.
 * @see Mode
 */
public class Tutorial extends Mode{//extends Mode {

    private Card[] dealt;
    private Point[] pt;
    private Deck deck;
    private int clickCounter; // keeps track of  clicks
    private JPanel cardPanel;
    private JPanel buttonPanel;
    private JButton sets;
    private JButton quit;

    /**
     * Sets up the game
     * @param deck Deck
     * @param cardPanel JPanel
     * @param pt Point[]
     * @param sets JButton
     * @param buttonPanel JPanel
     * @param quit JButton
     */
    public Tutorial(Deck deck, JPanel cardPanel, Point[] pt, JButton sets, JPanel buttonPanel, JButton quit){
        this.deck = deck;
        deck.shuffle();
        this.buttonPanel = buttonPanel;
        this.cardPanel = cardPanel;
        this.pt = pt;
        this.quit = quit;
        this.sets = sets;
        dealt = new Card[72];

        clickCounter = 0;
        for (int i = 0; i < 72; i++) { //method?
            Card card = deck.deal();
            card.setLocation(pt[i]);
            dealt[i] = card;
            //dealt[i].cover(); flips the cards face down testing
            cardPanel.add(card);//method end
            //card.addActionListener(new CardButtonListener());


        }


        clickCounter = 0;
        sets.addActionListener(new ShowSetsButtonListener());
    }

    /**
     * Cycles through all the available sets on the table, as user clicks the button.
     */

    private class ShowSetsButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
        if(!deck.isEmpty()) {
            SetFinder finder = new SetFinder(dealt, 12);
            finder.findSets();
            ArrayList<Card[]> validSets = finder.getSets();
            int stop = validSets.size();
            System.out.println(stop + "size");//debug
            System.out.println(clickCounter + "click counter");

            if (clickCounter > 0) {//unhighlight previously highlighted cards
                for (int a = 0; a < 3; a++) {
                    finder.getSets().get(clickCounter - 1)[a].choose();
                }
            }

            if (clickCounter == stop) {

                for (int x = 0; x < 12; x++) {
                    cardPanel.remove(dealt[x]);
                    cardPanel.revalidate();
                    cardPanel.repaint();
                    dealt[x] = null;
                    Card card = deck.deal();
                    card.setLocation(pt[x]);
                    dealt[x] = card;
                    cardPanel.add(card);

                }
                clickCounter = -1;
            } else if (!finder.getSets().isEmpty()) {
                for (int a = 0; a < 3; a++) {
                    finder.getSets().get(clickCounter)[a].choose();
                }

            }

            clickCounter++;
        }

        else
        {
            for (int x = 0; x < 12; x++) {
                if (dealt[x] != null) {
                    cardPanel.remove(dealt[x]);
                }
                cardPanel.revalidate();

                dealt[x] = null;
            }
            quit.doClick();


        }
    }
    }

}


