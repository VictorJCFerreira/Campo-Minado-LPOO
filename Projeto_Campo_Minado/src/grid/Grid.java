package grid;

import javax.swing.*;
import cells.*;
import gameLogic.*;

import java.awt.GridLayout;
import java.util.*;
import main.Game;

@SuppressWarnings("serial")
public class Grid extends JPanel implements I_Grid{
	
	private int bounds = Game.GRID_SIZE * Game.GRID_SIZE;
	
	private boolean picked = false;
	private ArrayList<Integer> mines = new ArrayList<Integer>();
	
	public static ArrayList<Common_cell> cellGrid = new ArrayList<Common_cell>();
	
	
	public void VGrid(GridLayout g, Handler h) {
		
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
				cellGrid.add(new Common_cell());
			} 
			
			else if(i % Game.GRID_SIZE == 0) {
				if(mines.contains(i - Game.GRID_SIZE) ||
						mines.contains(i - Game.GRID_SIZE + 1) ||
						mines.contains(i + Game.GRID_SIZE) ||
						mines.contains(i + Game.GRID_SIZE + 1) ||
						mines.contains(i + 1) ) {
					
						cellGrid.add(new Common_cell());
					} else {
						cellGrid.add(new Common_cell());
					}
				} 
			
			else if(i % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
					if(mines.contains(i - Game.GRID_SIZE - 1) ||
							mines.contains(i - Game.GRID_SIZE) ||
							mines.contains(i - 1) ||
							mines.contains(i + Game.GRID_SIZE - 1) ||
							mines.contains(i + Game.GRID_SIZE) ) {
							
							cellGrid.add(new Common_cell());
						} else {
							cellGrid.add(new Common_cell());
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
					
					cellGrid.add(new Common_cell());
				} else {
					cellGrid.add(new Common_cell());
				}
			}
		}
		
	}
	
	
	private void addCells() {
		for (int i=0; i < cellGrid.size(); i++) {
			add(cellGrid.get(i));
		}
			
	}
	
	
	
	/////////Refazendo a  Grid Novamente, para preparar para a GUI
	
	private Common_cell[][] grid;
	private int dimension;
	private int mine;
	private int craziness;
	

	public Grid(int dimension, int mine ) {
		
		this.dimension = dimension;
		this.mine = mine;
		this.grid = new Common_cell[dimension][dimension];
		
		for(int i = 0; i < dimension ; i++){
	        for(int j = 0; j < dimension; j++){
	        	
	        	grid[i][j] = new Empty_cell();
	        	
	        }
	    }
		
		this.positionMines();
	}
	
	
	public int getDimensao() {
		
        return dimension;
        
    }

	
    public int getMinas() {
    	
        return mine;
        
    }

    
    public Common_cell[][] getGrid() {
    	
        return grid;
        
    }
    
    
    public void printGrid() {
    	
        for(int i = 0; i < dimension; i++){
	        for(int j = 0; j < dimension; j++){
	
	            if(grid[i][j].getDiscovered()){
	            	
	            	System.out.printf(" %s ", grid[i][j].getFrame());
	            	
	            }
	            else if(!(grid[i][j].getDiscovered()) && grid[i][j].getFlagged()){
	            	
	                System.out.print(" @ ");
	                
	            }
	            else{
	            	
	                System.out.print(" _ ");
	                
	            }
	        }
	        System.out.println();
        }
    }


	public void positionMines() {
		Random random = new Random();
	    
        int minesLeft = this.mine;

        while(minesLeft > 0) {
        	
            int line = random.nextInt(dimension);
            int collum = random.nextInt(dimension);

            if(!(grid[line][collum].isMine())) {
            	
                Mine_cell mine = new Mine_cell();
                grid[line][collum] = mine;
                minesLeft -= 1;
                
            }
        }
    }
	
	
	public void placeMines(int line, int collum){
		
	        Mine_cell mine = new Mine_cell();
	        grid [line][collum] = mine;
	        
	}
	
	
	 public void hideGrid(){
		 
	        for(int i = 0; i < dimension; i++){
	        	for(int j = 0; j < dimension; j++){

	            grid[i][j].setVisible(false);

	        	}
	        }
	 }
	 
	 
	 public void revealGrid(){
	        for(int i = 0; i < dimension; i++){
	        	for(int j = 0; j < dimension; j++){

	            grid[i][j].setVisible(true);

	        	}
	        }
	 }
	 
	 public void revealCells(int line, int collum) {
		 
		 if(grid[line][collum].isNearMine() || (grid[line][collum].isCrazyCell() && !grid[line][collum].getFrame().equals("0"))){
			 
			 grid[line][collum].setVisible(true);
			 
	     } else if(grid[line][collum].isEmptyCell()){
	    	 
	        for(int i = line - 1; i <= line + 1; i++){
		        for(int j = collum - 1; j <= collum + 1; j++){
	
		            if(i >= 0 && i < dimension && j >= 0 && j < dimension && !(grid[i][j].isMine()) && !(grid[i][j].getDiscovered())){
		            	grid[i][j].setVisible(true);
	
		                if(grid[i][j].isEmptyCell() && i >=0 && i < dimension && j >= 0 && j < dimension){
		                	
		                	revealCells(i,j);
		                	
		                }
		            }            
		        }
	         }
	      }
	 }
	 
	 public boolean checkMines(int line, int collum) {
		 
	        return grid[line][collum].isMine();
	        
	    }
	 
	 
	 public void revealMines(){
		 
	        for(int i = 0; i < dimension ; i++){
		        for(int j = 0; j < dimension; j++){
		            if(grid[i][j].isMine()){
		            	
		                grid[i][j].setVisible(true);
		                
		            }
		        }
	        }
	    }
	 
	 
	 public boolean checkWin(){

	        for(int i = 0; i < dimension; i++){
		        for(int j = 0; j < dimension; j++){
		        	
			        if(!(grid[i][j].getDiscovered()) && !(grid[i][j].isMine())){
			        	
			            return false;
			            
			        }
		        }
	        }
	        return true;
	    }

	    public void setFlag(int linha, int coluna){
	    	
	        if(grid[linha][coluna].getDiscovered() == false){
	        	
	            if(grid[linha][coluna].getFlagged()){
	            	
	            	grid[linha][coluna].setFlagged(false);
	            
	            }
	            else if(!(grid[linha][coluna].getFlagged())){
	            	
	                grid[linha][coluna].setFlagged(true);
	            }
	        }
	        
	    }

	    public void placeCrazyness(int crazyLevel){
	        this.craziness = crazyLevel;
	        Random random = new Random();

	        for(int i = 0; i < this.dimension; i++) {
		        for(int j = 0; j < this.dimension; j++) {
		            int chance = random.nextInt(11); //de 0 a 10
		            
			            if(chance <= crazyLevel){
			            	
			                grid[i][j].changeToCrazyCell();
			                
			            }
		        	}
		        }
	    }

	    public int getCraziness() {
	        return craziness;
	    }

	    
	    public boolean crazyCell(int line, int collum)
	    {
	        return grid[line][collum].isCrazyCell();
	    }

	    
	    public boolean getVisible(int line, int collum)
	    {
	        return grid[line][collum].getDiscovered();
	    }

}
