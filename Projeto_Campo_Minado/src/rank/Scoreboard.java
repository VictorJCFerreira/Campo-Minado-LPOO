package rank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Scoreboard {

	private FileInputStream file; 
    private InputStreamReader input;
    private BufferedReader reader;

    private FileOutputStream fileWriter;
    private PrintWriter writer; 

    private ArrayList<String> names;
    private ArrayList<String> points;

    public Scoreboard(Player player){
        try{
    
        this.file = new FileInputStream("playerRank.txt"); 
        this.input = new InputStreamReader(file); 
        this.reader = new BufferedReader(input); 

        	String line;
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> points = new ArrayList<>();

            do{
            	line = reader.readLine();
                if(line != null){
                    String [] data = line.split(";");
                    
                    for(int i = 0; i < data.length; i++){
                        if(i%2 == 0){
                            names.add(data[i]);
                        }
                        if(i%2 == 1){
                            points.add(data[i]);
                        }
                    }
                }
            }while(line != null);

            this.names = names;
            this.points = points;
            reader.close();
            
        }catch(Exception e){
            System.out.println("Reading Error");
        }

        try{

            this.fileWriter = new FileOutputStream("playerRank.txt");
            this.writer = new PrintWriter(fileWriter);

            Player[] players = new Player[names.size() + 1];
            for(int i = 0; i < names.size() ; i++)
            {
                players[i] = new Player(names.get(i), Integer.parseInt(points.get(i)));
            }
            players[names.size()] = player; 

            Arrays.sort(players, Comparator.comparingInt(Player::getPoints).reversed());

            for (Player updatedPlayer : players) {
                writer.println(updatedPlayer.getName() + ";" + updatedPlayer.getPoints());
            }
            writer.close();

        }catch(Exception e){
            System.out.println("Error");
        }


        System.out.println("Complete Updated");
    }

    public Scoreboard(){
        
        try{
            this.file = new FileInputStream("playerRank.txt");
            this.input = new InputStreamReader(file);
            this.reader = new BufferedReader(input); 

            String line;
                ArrayList<String> names = new ArrayList<>();
                ArrayList<String> points = new ArrayList<>();

                do{
                    line = reader.readLine();
                    if(line != null){
                        String [] data = line.split(";");
                        
                        for(int i = 0; i < data.length; i++){
                            if(i%2 == 0){
                                names.add(data[i]);
                            }
                            if(i%2 == 1){
                                points.add(data[i]);
                            }
                        }
                    }
                }while(line != null);

                this.names = names;
                this.points = points;
                reader.close();
            }catch(Exception e){
                System.out.println("Reading Error");
            }

    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getPoints() {
        return points;
    }
}
