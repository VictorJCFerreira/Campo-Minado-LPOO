package grid;

public class Grid extends A_Grid {

	public Grid(int numberOfMines, int line, int cols) {
		super(numberOfMines, line, cols);
		createCells(this.lines, this.cols);
		createEmptyCells();  
		setMines();
		setSurroundingMinesNumber();
	}

}
