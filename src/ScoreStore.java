import java.io.*;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import java.io.IOException;
import java.nio.file.*;



/**
 *
 * Created by Daria on 3/7/17.
 * ScoreStore.java
 * handles game data
 */
public class ScoreStore implements Serializable {
    private static final long serialVersionUID = 1L;



    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Hashtable<String, Vector<Integer>> scores;

    /**
     * constructor
     * reads data from file
     */

    public ScoreStore(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);


        try{
            fileInputStream = new FileInputStream("data.txt");
            inputStream = new ObjectInputStream(fileInputStream);
            try {
                scores = (Hashtable<String, Vector<Integer>>) inputStream.readObject();
                fileInputStream.close();
                inputStream.close();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * adds new entry if the user doesn't have an ID
     * @param userID
     */
    public void addNewEntry(String userID){
        Vector<Integer> scoreList = new Vector<Integer>(10);
        scores.put(userID,scoreList);
    }

    /**
     * adds score for existing user
     * @param userID String
     * @param score Integer
     */

    public void addScore(String userID, Integer score){
        Vector<Integer> updated = scores.get(userID);

        updated.add(score);
        sort(updated);
        scores.put(userID, updated);
    }

    /**
     * chacks if the user already has ID
     * @param userID
     * @return
     */
    public boolean isNewUser(String userID){
        return !scores.containsKey(userID);
    }

    /**
     * sorts the score array
     * @return
     */
    private Vector<Integer> sort(Vector<Integer> vector){

        for (int i = 0; i < vector.size(); i++)
        {
            Collections.sort(vector);
        }

        return vector;
    }

    /**
     * Displays all scores
     * @param userID
     * @return ret
     */
    public String display(String userID){
        String ret ="<html> Your Scores:<br>";
        Vector<Integer> userScores = scores.get(userID);

        sort(userScores);
        int size;
        if (userScores.size()>10){
            size = 10;
        }else{
            size = userScores.size();
        }
        for(int i= 0; i < size; i++){
            ret += "\n"+(i + 1)+ ": " + userScores.get(i) + "<br>";

        }
        ret+="<html>";

        return ret;
    }

    /**
     * saves scores to the file
     */
    public void save(){
        try {
            fileOutputStream = new FileOutputStream("data.txt");
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(scores);
            outputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
