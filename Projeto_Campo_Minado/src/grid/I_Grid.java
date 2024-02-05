package grid;

public interface I_Grid {
	    
	    public abstract void printGrid();

	    public abstract void positionMines();

	    public abstract void placeMines(int line, int collum);

	    public abstract void hideGrid();

	    public abstract void revealGrid();

	    public abstract void revealCells(int line, int collum); //throws AchouMinaException, ForaDoTabuleiroException;

	    public abstract boolean checkMines(int line, int collum);

	    public abstract void revealMines();

	    public abstract boolean checkWin();

	    public abstract void setFlag(int line, int collum);

	    public abstract boolean getVisible(int line, int collum);

}
