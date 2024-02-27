package grid;

import java.util.ArrayList;
import java.util.Random;

import Exceptions.*;
import cells.*;

public abstract class A_Grid {
	
	private int numberOfMines;	
	int lines;
    int cols;
    private Basic_cell cells[][];
    private int crazy;
    private ArrayList<ArrayList<Integer>> clickedPositions;

    public A_Grid(int numberOfMines, int lines, int cols){
    	
        this.lines = lines;
        this.cols = cols;
        this.numberOfMines = numberOfMines;
        cells = new Basic_cell[lines][cols];
        this.clickedPositions = new ArrayList<ArrayList<Integer>>();

        createEmptyCells();  

        setMines();

        setSurroundingMinesNumber();
    }
  
	public Basic_cell[][] getOnGrid(){
		return cells;
	}
	
	public void createCells(int lines, int cols){
        this.cells = new Basic_cell[lines][cols];
        for(int i=0; i<lines; i++){
            this.cells[i] = new Basic_cell[cols]; 
        }
     
    }

	public void createEmptyCells()
    {
        for (int x = 0; x < cols; x++)
        {
            for (int y = 0; y < lines; y++)
            {
                cells[x][y] = new Empty_cell(x,y);
            }
        }
    }
	
	public void printTabuleiro()
    {
        for(int i = 0; i < lines; i++){
        for(int j = 0; j < cols; j++){

            if(cells[i][j].getVisibility()){
            System.out.printf(" %s ", cells[i][j].getContent());
            }
            else if(!(cells[i][j].getVisibility()) && cells[i][j].getIsFlagged())
            {
                System.out.print(" @ ");
            }
            else{
                System.out.print(" _ ");
            }
        }
        System.out.println();
        }
    }
	
	
	private void insertMines(int line, int cols) {
		Mine_cell mine = new Mine_cell(line, cols); 
		this.cells[line][cols] = mine;
	}
	
	
	public void setMines() {
		Random random = new Random();
        int x,y;
        boolean hasMine;
        int currentMines = 0;                

        while (currentMines != numberOfMines){
            x = random.nextInt(cols);
            y = random.nextInt(lines);

            hasMine = cells[x][y].getIsMine();

            if(!hasMine)
            {		
            	insertMines(x,y);
                currentMines++;	
            }			
        }
    }
	
	public void revealCells(int line, int col) throws OutOfBoundsException, FoundMineException
    {    
        if(line < 0 || line > lines || col < 0 || col > cols)
        {
            throw new OutOfBoundsException();
        }
        
        if(cells[line][col].getIsMine())
        {
            throw new FoundMineException();
        }
        else if(cells[line][col].getIsNearByMineCell() || (cells[line][col].getCrazy() && !cells[line][col].getContent().equals("0"))){
            cells[line][col].setVisibility(true);
        }
        else if(cells[line][col].getIsEmptyCell()){
       
	        for(int i = line - 1; i <= line + 1; i++){
		        for(int j = col - 1; j <= col + 1; j++){
		            if(i >= 0 && i < line && j >= 0 && j < line && !(cells[i][j].getIsMine()) && !(cells[i][j].getVisibility())){
		                cells[i][j].setVisibility(true);
		
		                if(cells[i][j].getIsEmptyCell() && i >=0 && i < line && j >= 0 && j < line)
		                {
		                	revealCells(i,j);
		                }
		            }            
		        }
	        }
        }
    }
	
	public void revealMines()
    {
        for(int i = 0; i < lines ; i++){
        for(int j = 0; j < cols; j++){
            if(cells[i][j].getIsMine())
            {
                cells[i][j].setVisibility(true);
            }
        }
        }
    }
	
	public boolean checkPositionMine(int line, int col){
		
        if((this.cells[line][col].getIsMine())){
        	return true;      
         }
        return false;
    }
	
	
	public void setSurroundingMinesNumber()
    {	
        for(int x = 0 ; x < cols ; x++) 
        {
            for(int y = 0 ; y < lines ; y++) 
            {
                cells[x][y].setMinesNearBy(calculateNeighbours(x,y));                        
            }
        }
    }

	public int calculateNeighbours(int xCo, int yCo) {
		int neighbours = 0;

        for(int x=makeValidCoordinateX(xCo - 1); x<=makeValidCoordinateX(xCo + 1); x++) 
        {
            for(int y=makeValidCoordinateY(yCo - 1); y<=makeValidCoordinateY(yCo + 1); y++) 
            {
                if(x != xCo || y != yCo)
                    if(cells[x][y].getIsMine())  
                        neighbours++;
            }
        }

        return neighbours;
	}

	private int makeValidCoordinateX(int i) {
		
        if (i < 0)
            i = 0;
        else if (i > cols-1)
            i = cols-1;

        return i;
    }

	private int makeValidCoordinateY(int i) {
		
        if (i < 0)
            i = 0;
        else if (i > lines-1)
            i = lines-1;

        return i;
    }
	
	public void updateNeighbours() {
        int contador = 0;
        
        
       
        for(int i = 0; i < lines ; i++){
            for(int j = 0; j < cols ; j++){
                contador = 0;
                
               
                for(int linha = i - 1; linha <= i + 1; linha++){
                    for(int coluna = j - 1; coluna <= j + 1; coluna++){

                           
                            if(linha >= 0 && linha < lines && coluna >= 0 && coluna < cols && (linha != i || coluna != j) && !(cells[i][j].getIsMine()))
                            {
                                if(cells[linha][coluna].getIsMine())
                                {
                                    contador++;
                                }
                            }

                        }
                    }
                    
                if(contador > 0 && !cells[i][j].getIsMine()){

                    String contadorString = Integer.toString(contador);
                    cells[i][j].setState(contador);
                    cells[i][j].setContent(contadorString);
                }
                
            }
        }
    }
	
	public boolean checkWin()
    {
        for(int i = 0; i < lines; i++){
	        for(int j = 0; j < cols; j++){
		        if(!(cells[i][j].getVisibility()) && !(cells[i][j].getIsMine())){
		            return false;
		        }
	        }
        }
        return true;
    }

	
	public void setFlag(int line, int col)
    {
        if(cells[line][col].getVisibility() == false)
        {
            if(cells[line][col].getIsFlagged()){
            cells[line][col].setIsFlagged(false);
            }
            else if(!(cells[line][col].getIsFlagged()))
            {
                cells[line][col].setIsFlagged(true);
            }
        }
        
    }

	
	public ArrayList<ArrayList<Integer>> getClickedPositions() {
        return clickedPositions;
    }
	
	
	public void AddPosition(int line, int col){
        ArrayList<Integer> current_position = new ArrayList<>();
        current_position.add(line);
        current_position.add(col);
        this.clickedPositions.add(current_position);
    }
	
    
    public int lengthClicked(){
        return this.clickedPositions.size();
    }
    
    
    public int[] ClickedElement(int position){
        int[] positions = {this.clickedPositions.get(position).get(0), this.clickedPositions.get(position).get(1)};
        return positions;
    }
	
	
	public void setNumberOfMines(int numberOfMines)
    {
        this.numberOfMines = numberOfMines;
    }
	
	public boolean getVisibility(int line, int col)
    {
        return cells[line][col].getVisibility();
    }

    public int getNumberOfMines()
    {
        return numberOfMines;
    }

    public A_Cell[][] getCells()
    {
        return cells;
    }
    
    public int getLines()
    {
        return lines;
    }
    
    public int getCols()
    {
        return cols;
    }
    
}
