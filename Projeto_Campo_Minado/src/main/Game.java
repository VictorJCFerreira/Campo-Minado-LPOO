package main;

import gameLogic.*;

public class Game {
	
	
	// Deixar o Tabuleiro e a Janela do jogo decidir isso 
	
    public static final int WIDTH = 720, HEIGHT = 720;
    public static final int GRID_SIZE = 10;
    public static final int MINECOUNT = 15;
    
    // Alteração na parte superior a ser feita

    private Handler handler = new Handler();
    
    public Game() {
        new Window(WIDTH, HEIGHT, GRID_SIZE, "Minesweeper - ", this, handler);
        Window.update(0);
    }

    public static void main(String[] args) {
        new Game();
    }

}
