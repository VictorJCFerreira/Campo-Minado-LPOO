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

        String linha;
            ArrayList<String> nomes = new ArrayList<>();
            ArrayList<String> pontos = new ArrayList<>();

            do{
                linha = reader.readLine();
                if(linha != null){
                    String [] dadosBrutos = linha.split(";");
                    
                    for(int i = 0; i < dadosBrutos.length; i++){
                        if(i%2 == 0){
                            nomes.add(dadosBrutos[i]);
                        }
                        if(i%2 == 1){
                            pontos.add(dadosBrutos[i]);
                        }
                    }
                }
            }while(linha != null);

            this.names = names;
            this.points = points;
            reader.close();
            
        }catch(Exception e){
            System.out.println("Falha na leitura");
        }

        try{

            this.fileWriter = new FileOutputStream("playerRank.txt");
            this.writer = new PrintWriter(fileWriter);

            Player[] jogadores = new Player[names.size() + 1];
            for(int i = 0; i < names.size() ; i++)
            {
                jogadores[i] = new Player(names.get(i), Integer.parseInt(points.get(i)));
            }
            jogadores[names.size()] = player; 

            Arrays.sort(jogadores, Comparator.comparingInt(Player::getPoints).reversed());

            for (Player jogadorAtualizado : jogadores) {
                writer.println(jogadorAtualizado.getName() + ";" + jogadorAtualizado.getPoints());
            }
            writer.close();

        }catch(Exception e){
            System.out.println("erro na gravação");
        }


        System.out.println("ranking atualizado com sucesso");
    }

    public Scoreboard(){
        
        try{
            this.file = new FileInputStream("playerRank.txt");
            this.input = new InputStreamReader(file);
            this.reader = new BufferedReader(input); 

            String linha;
                ArrayList<String> nomes = new ArrayList<>();
                ArrayList<String> pontos = new ArrayList<>();

                do{
                    linha = reader.readLine();
                    if(linha != null){
                        String [] dadosBrutos = linha.split(";");
                        
                        for(int i = 0; i < dadosBrutos.length; i++){
                            if(i%2 == 0){
                                nomes.add(dadosBrutos[i]);
                            }
                            if(i%2 == 1){
                                pontos.add(dadosBrutos[i]);
                            }
                        }
                    }
                }while(linha != null);

                this.names = nomes;
                this.points = pontos;
                reader.close();
            }catch(Exception e){
                System.out.println("Falha na leitura");
            }

    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getPoints() {
        return points;
    }
}
