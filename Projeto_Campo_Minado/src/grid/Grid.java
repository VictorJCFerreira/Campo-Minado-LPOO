package grid;

import javax.swing.*;
import cells.*;
import handler.*;
import java.awt.GridLayout;
import java.util.*;
import main.Game;

public class Grid extends JPanel {
	
	private int bounds = Game.GRID_SIZE * Game.GRID_SIZE;
	
	private boolean picked = false;
	private ArrayList<Integer> mines = new ArrayList<Integer>();
	
	public static ArrayList<Common_cell> cellGrid = new ArrayList<Common_cell>();
	
	
	public Grid(GridLayout g, Handler h) {
		
		super(g);
		createCells(h);
		addCells();
	}

	private void createCells(Handler h) {
		for(int i = 1; i <= Game.MINECOUNT; i++) {
            while(!picked) {
                int minePosition = (int) (Math.random() * bounds);
                if (!mines.contains(minePosition)) {
                    mines.add(minePosition);
                    picked = true;
                }
            }
            picked = false;
        }
		
		for (int i=0; i < bounds; i++) {
			if(mines.contains(i)) {
				cellGrid.add(new Common_cell(1, i, false, false, h));
			} 
			
			else if(i % Game.GRID_SIZE == 0) {
				if(mines.contains(i - Game.GRID_SIZE) ||
						mines.contains(i - Game.GRID_SIZE + 1) ||
						mines.contains(i + Game.GRID_SIZE) ||
						mines.contains(i + Game.GRID_SIZE + 1) ||
						mines.contains(i + 1) ) {
					
						cellGrid.add(new Common_cell(2, i, false, false, h));
					} else {
						cellGrid.add(new Common_cell(0, i, false, false, h));
					}
				} 
			
			else if(i % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
					if(mines.contains(i - Game.GRID_SIZE - 1) ||
							mines.contains(i - Game.GRID_SIZE) ||
							mines.contains(i - 1) ||
							mines.contains(i + Game.GRID_SIZE - 1) ||
							mines.contains(i + Game.GRID_SIZE) ) {
							
							cellGrid.add(new Common_cell(2, i, false, false, h));
						} else {
							cellGrid.add(new Common_cell(0, i, false, false, h));
						}
					} 
			
			else {
				if(mines.contains(i - Game.GRID_SIZE - 1) ||
					mines.contains(i - Game.GRID_SIZE) ||
					mines.contains(i - Game.GRID_SIZE + 1) ||
					mines.contains(i - 1) ||
					mines.contains(i + Game.GRID_SIZE - 1) ||
					mines.contains(i + Game.GRID_SIZE) ||
					mines.contains(i + Game.GRID_SIZE + 1) ||
					mines.contains(i + 1) ) {
					
					cellGrid.add(new Common_cell(2, i, false, false, h));
				} else {
					cellGrid.add(new Common_cell(0, i, false, false, h));
				}
			}
		}
		
	}
	
	
	private void addCells() {
		for (int i=0; i < cellGrid.size(); i++) {
			add(cellGrid.get(i));
		}
			
	}

}
