package main;

import handler.*;

public class Game {

    public static final int WIDTH = 720, HEIGHT = 720;
    public static final int GRID_SIZE = 15;
    public static final int MINECOUNT = (int) Math.round(GRID_SIZE * GRID_SIZE * .1);

    private Handler handler = new Handler();

    public Game() {
        new Window(WIDTH, HEIGHT, GRID_SIZE, "Minesweeper - ", this, handler);
        Window.update(0);
    }

    public static void main(String[] args) {
        new Game();
    }

}
