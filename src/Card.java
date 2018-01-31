import java.awt.*;
import javax.swing.*;
/**
 * Created by Daria on 2/13/17.
 *Card.java
 */

public class Card extends JButton{

    private final static int width = 75; //changed
    private final static int height = 100; //changed
    private Locations number;
    private Shading shading;
    private Symbol symbol;
    private Color color;
    private Color background;
    private boolean green;
    private Color initialColor;

    private int index;
    /**
     * Initializes the card
     * @param color
     * @param shading
     * @param number
     * @param symbol
     */
    public Card (Color color, Shading shading, Locations number, Symbol symbol) {
        this.color = color;
        this.initialColor = color;
        this.background = Color.white;
        this.shading = shading;
        this.number = number;
        this.symbol = symbol;
        green = false;
        setSize(width, height);
    }

    /**
     * draws cards and shapes on them
     * @param page Graphics
     */

    public void paint(Graphics page){
        Graphics2D graph=(Graphics2D) page;   // the 2D graphics context

        Dimension d = getSize();

        graph.setColor(background);
        //graph.setColor(Color.black) covers the card black except for the symbol and inside
        graph.fillRoundRect(1, 1, (int)d.getWidth()-2, (int)d.getHeight()-2, 20, 20);


        // We loop through this cards location drawing the symbol
        for (int i=0; i<number.getSize(); i++) {
            symbol.draw(graph, number.getLoc(i), color, background, shading);
            //symbol.draw(graph, number.getLoc(i), Color.black, Color.black, shading); makes symbols black

        }
        graph.setPaint(Color.black);
        graph.setStroke(new BasicStroke(2.0f));
        graph.drawRoundRect(1, 1, (int)d.getWidth()-2, (int)d.getHeight()-2, 20, 20);



    }


    /**
     * Changes background color to mask the card identity
     */
    public void cover(){
        background = new Color(255, 244, 182);
        color = new Color(255, 244, 182);
    }


    /**
     * Changes background color to "uncover" a card
     */
    public void uncover(){
        background = Color.white;
        color = initialColor;
        revalidate();
        repaint();
    }



    /**
     * highlights cards red when set is invalid
     */
    public void invalidChoose(){

        if(background == Color.white){
            background = (new Color (255, 142, 142));
            //green = false;
        }else {
            background = Color.white;

        }
        repaint();


    }

    /**
     * highlights the cards when chosen by user
     */
    public void choose(){
        if(background == Color.white){
            background = (new Color (155, 253, 155));
            //green = true;
        }else {
            background = Color.white;
            //green = false;
        }
        repaint();
    }




    // the following methods are defined to keep layout managers and others from
    // changing the size of the card.  Note that the location can still be set
    // using either setBounds or setLocation
    public void setSize (int width, int height) {super.setSize(width, height);}
    public void setSize (Dimension d) {super.setSize(width, height);}
    public void setBounds (int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
    }
    public void setBounds (Rectangle r) {
        super.setBounds ((int)r.getX(), (int)r.getY(), width, height);
    }

    // set and get the index of this card in the array of cards
    public void setIndex(int i) {index = i;}
    public int getIndex() {return index;}
    public Shading getShading(){
        return shading;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public Locations getNumber(){
        return number;
    }

    public Color getColor(){
        return initialColor;
    }



}
