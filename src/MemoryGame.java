//import javafx.scene.control.Tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;

/**
 * Created by Daria on 2/12/17.
 *
 *
 */
//
public class MemoryGame extends JApplet {

    private static final long serialVersionUID = 1L;

    private final int APPLET_WIDTH_WELCOME = 500, APPLET_HEIGHT_WELCOME = 500;
    private final int APPLET_WIDTH = 1150, APPLET_HEIGHT = 750;
    private final int DELTA = 160;
    private Deck deck;

    private Mode mode;

    private Container cp;//container where other panels are placed
    private JPanel cardPanel; // displays cards currently that player can interact with
    private JPanel buttonPanel; // panel with navigation buttons
    private JLabel turns; // label for number of turns
    private JLabel pairs; // label for number of pairs
    private JLabel userID; //label for the userID
    private JButton quitButton;//button that leads to the main welcome screen
    private JPanel welcome; //main welcome screen
    private JButton turnOverButton; //button that shows hints for solitaire mode
    private JButton showSetsButton;//hints for tutorial mode
    private ScoreStore scoreStore;
    private TextField text1;
    private String userName;

   // private JButton Turns;
//added more points to array
    private Point pt[] = {
            new Point(10, 10), new Point(10, 120), new Point(10, 230), new Point(10, 340), new Point(10, 450),new Point(10, 560),
            new Point(95, 10), new Point(95, 120), new Point(95, 230), new Point(95, 340), new Point(95, 450),new Point(95, 560),
            new Point(180, 10), new Point(180, 120), new Point(180, 230), new Point(180, 340), new Point(180, 450),new Point(180, 560),
            new Point(265, 10), new Point(265, 120), new Point(265, 230), new Point(265, 340), new Point(265, 450),new Point(265, 560),
            new Point(350, 10), new Point(350, 120), new Point(350, 230), new Point(350, 340), new Point(350, 450),new Point(350, 560),
            new Point(435, 10), new Point(435, 120), new Point(435, 230), new Point(435, 340), new Point(435, 450),new Point(435, 560),
            new Point(520, 10), new Point(520, 120), new Point(520, 230), new Point(520, 340), new Point(520, 450),new Point(520, 560),
            new Point(605, 10), new Point(605, 120), new Point(605, 230), new Point(605, 340), new Point(605, 450),new Point(605, 560),
            new Point(690, 10), new Point(690, 120), new Point(690, 230), new Point(690, 340), new Point(690, 450),new Point(690, 560),
            new Point(775, 10), new Point(775, 120), new Point(775, 230), new Point(775, 340), new Point(775, 450),new Point(775, 560),
            new Point(860, 10), new Point(860, 120), new Point(860, 230), new Point(860, 340), new Point(860, 450),new Point(860, 560),
            new Point(945, 10), new Point(945, 120), new Point(945, 230), new Point(945, 340), new Point(945, 450),new Point(945, 560)};

/**
 * changed layout and added text field
 */
    public void init() {
        scoreStore = new ScoreStore(); // add path

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(229, 255, 204));
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new GridLayout(30,1));

        userID = new JLabel("      UserID: ");
        text1 = new TextField("UserID ");

        //labels for number of turns and pairs
        turns = new JLabel("      Turns = 0");
        pairs = new JLabel("      Pairs = 0");


        //add(text1);
        text1.setMaximumSize(new Dimension(Integer.MAX_VALUE, text1.getPreferredSize().height));

        //Turns = new JButton("TURNS = 0");
        //Turns.setBackground(Color.yellow);

        //buttons for buttonPanel
        quitButton = new JButton("Quit");
        //quitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, quitButton.getPreferredSize().height));
        turnOverButton = new JButton("Turn Over Cards");
        //turnOverButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, turnOverButton.getPreferredSize().height));
        showSetsButton = new JButton("Show Sets");
        //showSetsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, showSetsButton.getPreferredSize().height));

        //buttonPanel.add(text1);
        //buttonPanel.add(userID);
        buttonPanel.add(turnOverButton); //
        buttonPanel.add(turns);
        buttonPanel.add(pairs);

        buttonPanel.add(quitButton);


        //card display panel
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.white);
        deck = new Deck();//singleton
        deck.shuffle();


        //end card display*/

        //Buttons and Cards will appear in the container
        cp = getContentPane();

        //initializing buttons for modes and adding buttonListeners
        JButton memoryButton = new JButton("Play");

        memoryButton.addActionListener(new MemoryButtonListener());
        quitButton.addActionListener(new QuitButtonListener());

        //initializing and displaying welcome message with icon
        JLabel label = new JLabel("Welcome to the Memory Game: Enter UserID to proceed");
        JLabel icon = new JLabel();

       /* try {
            Image img = ImageIO.read(getClass().getResource("resources/poker.png"));
            icon.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println(ex);
        }*/


        //initializing panel with the picture
        JPanel pic = new JPanel();
        pic.setBackground(Color.white);
        pic.setLayout(new FlowLayout());
        pic.add(icon);


        //initializing panel with welcome message label
        JPanel greet = new JPanel();
        greet.setBackground((Color.white));
        greet.setLayout(new FlowLayout());
        greet.add(label);


        //initializing panel with mode buttons
        JPanel modePanel = new JPanel();
        modePanel.setBackground(Color.white);
        modePanel.setLayout(new FlowLayout());
        modePanel.add(text1);
        modePanel.add(memoryButton);


        //adding elements to the welcome panel
        welcome = new JPanel();
        welcome.setBackground((Color.white));
        welcome.setLayout((new GridLayout(3, 1)));
        welcome.add(greet);
        welcome.add(modePanel);
        welcome.add(pic);



        cp.setBackground(Color.white);
        //adding welcome panel to the container
        cp.add(welcome, BorderLayout.CENTER);

        setSize(APPLET_WIDTH_WELCOME, APPLET_HEIGHT_WELCOME);



    }


    /**changed names of vars  and added userID
     * action listener for play button. Creates mode object that handles logic
     */

    private class MemoryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            userName = text1.getText();//gets the user name for the game

            if (!userName.equals("UserID ")) {
                setSize(APPLET_WIDTH + DELTA, APPLET_HEIGHT);
                cp.remove(welcome);
                cp.add(cardPanel, BorderLayout.CENTER);
                cp.add(buttonPanel, BorderLayout.LINE_START);
                buttonPanel.remove(showSetsButton);
                //buttonPanel.add(Turns);

                userID.setForeground(Color.black);
                userID.getVerticalAlignment();
                userID.setHorizontalTextPosition(SwingConstants.CENTER);
                userID.repaint();
                userID.setBackground(Color.GRAY);

                turns.setForeground(Color.black);
                turns.setHorizontalTextPosition(SwingConstants.CENTER);
                turns.repaint();
                turns.setBackground(Color.GRAY);

                pairs.setForeground(Color.black);
                pairs.setHorizontalTextPosition(SwingConstants.CENTER);
                pairs.repaint();
                pairs.setBackground(Color.GRAY);


                if (scoreStore.isNewUser(userName)) {
                    scoreStore.addNewEntry(userName);
                }
                //turnOverButton.removeActionListener(new ShowSetsButtonListener());
                // turnOverButton.addActionListener(new hintButtonListener());
                cardPanel.revalidate();
                cardPanel.repaint();
                deck.restart();//singleton
                // hintCounter = 0;
                mode = new Memory(deck, cardPanel, pt, turnOverButton, buttonPanel, quitButton, turns, pairs, scoreStore, userName);
            }





        }
    }

    /**
     * button allows player quit current game at any time and change modes easily
     */
    private class QuitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            cp.remove(buttonPanel);
            cp.remove(cardPanel);
            cardPanel.revalidate();
            cp.repaint();
            init();
        }
    }


}