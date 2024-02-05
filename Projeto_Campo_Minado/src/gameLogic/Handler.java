package gameLogic;


import java.util.*;
import cells.*;
import grid.*;
import main.Game;
import main.Window;

public class Handler {
	
	private ArrayList<Common_cell> current = new ArrayList<Common_cell>();
	private ArrayList<Common_cell> queue = new ArrayList<Common_cell>();
	
	private static int flaggedCells = 0;
	

	public void click(Common_cell cell) {
		int discoveredCells = 0;
        if(!cell.getFlagged()) {
            cell.setEnabled(false);
            cell.setDiscovered(true);

            int position = cell.getType();
            if(cell.getType() == 0) {
                if(position < Game.GRID_SIZE) {
                    if(position % Game.GRID_SIZE == 0) {
                        queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE)));
                        queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE + 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                    } else if(position % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
                        queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE)));
                        queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE - 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    } else {
                        queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE)));
                        queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE + 1)));
                        queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE - 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    }
                } else if(position >= (Game.GRID_SIZE * (Game.GRID_SIZE - 1))) {
                    if(position % Game.GRID_SIZE == 0) {
                        queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE)));
                        queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE + 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                    } else if(position % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
                        queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE)));
                        queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE - 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    } else {
                        queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE)));
                        queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE + 1)));
                        queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE - 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    }
                } else if(position % Game.GRID_SIZE == 0) {
                    queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE)));
                    queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE)));
                    queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE + 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE + 1)));
                    queue.add(Grid.cellGrid.get((position + 1)));
                } else if(position % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
                    queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE)));
                    queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE)));
                    queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE - 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE - 1)));
                    queue.add(Grid.cellGrid.get((position - 1)));
                } else {
                    queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE)));
                    queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE)));
                    queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE - 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE - 1)));
                    queue.add(Grid.cellGrid.get((position - Game.GRID_SIZE + 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRID_SIZE + 1)));
                    queue.add(Grid.cellGrid.get((position - 1)));
                    queue.add(Grid.cellGrid.get((position + 1)));
                }
            } else if(cell.getType() == 2) {
                int dangerCount = 0;
                if(position < Game.GRID_SIZE) {
                    if(position % Game.GRID_SIZE == 0) {
                        if(Grid.cellGrid.get(position + Game.GRID_SIZE).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRID_SIZE + 1).getType() == 1) dangerCount++; 
                        if(Grid.cellGrid.get(position + 1).getType() == 1) dangerCount++;
                    } else if(position % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
                        if(Grid.cellGrid.get(position + Game.GRID_SIZE).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRID_SIZE - 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1) dangerCount++;
                    } else {
                        if(Grid.cellGrid.get(position + Game.GRID_SIZE).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRID_SIZE + 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRID_SIZE - 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position + 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1) dangerCount++;
                        System.out.println(dangerCount);
                    }
                } else if(position >= (Game.GRID_SIZE * (Game.GRID_SIZE - 1))) {
                    if(position % Game.GRID_SIZE == 0) {
                        if(Grid.cellGrid.get(position - Game.GRID_SIZE).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRID_SIZE + 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position + 1).getType() == 1) dangerCount++;
                    } else if(position % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
                        if(Grid.cellGrid.get(position - Game.GRID_SIZE).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRID_SIZE - 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1) dangerCount++;
                    } else {
                        if(Grid.cellGrid.get(position - Game.GRID_SIZE).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRID_SIZE + 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRID_SIZE - 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position + 1).getType() == 1) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1) dangerCount++;
                    }
                } else if(position % Game.GRID_SIZE == 0) {
                    if(Grid.cellGrid.get(position - Game.GRID_SIZE).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get(position + Game.GRID_SIZE).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get(position - Game.GRID_SIZE + 1).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get(position + Game.GRID_SIZE + 1).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get(position + 1).getType() == 1) dangerCount++;
                } else if(position % Game.GRID_SIZE == Game.GRID_SIZE - 1) {
                    if(Grid.cellGrid.get((position - Game.GRID_SIZE)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRID_SIZE)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position - Game.GRID_SIZE - 1)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRID_SIZE - 1)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position - 1)).getType() == 1) dangerCount++;
                } else {
                    if(Grid.cellGrid.get((position - Game.GRID_SIZE)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRID_SIZE)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position - Game.GRID_SIZE - 1)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRID_SIZE - 1)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position - Game.GRID_SIZE + 1)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRID_SIZE + 1)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position - 1)).getType() == 1) dangerCount++;
                    if(Grid.cellGrid.get((position + 1)).getType() == 1) dangerCount++;
                }
                cell.setText(String.valueOf(dangerCount));
            } else if(cell.getType() == 1) {
                for(int x = 0; x < Grid.cellGrid.size(); x++) {
                    Grid.cellGrid.get(x).setEnabled(false);
                    Grid.cellGrid.get(x).setText("");
                    if(Grid.cellGrid.get(x).getType() == 1) {Grid.cellGrid.get(x).setText("💣");}
                }
                cell.setText("💣");
            }  

            for(int x = 0; x < queue.size(); x++) {
                if(!queue.get(x).getDiscovered()) {
                    current.add(queue.get(x));
                    queue.get(x).setDiscovered(true);
                }
            }
            queue.clear();
            while(!current.isEmpty()) {
            	
            	Common_cell temp = current.get(0);
            	current.remove(0);
            	temp.clickButton(); 
            	
            	}

            for(int x = 0; x < Grid.cellGrid.size(); x++) {
                if(Grid.cellGrid.get(x).getDiscovered()) {
                    discoveredCells++;
                }
            }

            if(flaggedCells == Game.MINECOUNT) {
                for(int x = 0; x < Grid.cellGrid.size(); x++) {
                    if(Grid.cellGrid.get(x).getType() == 1) {
                        Grid.cellGrid.get(x).setEnabled(false);
                        Grid.cellGrid.get(x).setText("💣");
                    } else {
                        Grid.cellGrid.get(x).setEnabled(false);
                        Grid.cellGrid.get(x).setText(":)");
                    }
                }
            }
        } 
        }  
		
		public void rightClick(Common_cell cell) {
	        if(!cell.getDiscovered()) {
	            if(!cell.getFlagged()) {
	                cell.setFlagged(true);
	                cell.setText("🚩");
	                flaggedCells++;
	                Window.update(flaggedCells);
	            } else {
	                cell.setFlagged(false);
	                cell.setText("");
	                flaggedCells--;
	                Window.update(flaggedCells);
	            }
		}
		
	}
	
}
