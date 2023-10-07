package Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Highscore {
    public int highscore;
    public Highscore(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src//res//highscore.txt"));
            highscore = Integer.parseInt(reader.readLine());
            reader.close();
        }    
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public boolean HighscoreCheck(int score){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src//res//highscore.txt"));
            highscore = Integer.parseInt(reader.readLine());
            reader.close();
            if(score>highscore){
                highscore = score;
                BufferedWriter writer = new BufferedWriter(new FileWriter("src//res//highscore.txt"));
                writer.write(Integer.toString(highscore));
                writer.close();
                return true;
            }
            else{
                return false;
            }
        }    
        catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
