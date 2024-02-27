package cells;

public class Basic_cell extends A_Cell {

	public Basic_cell(int line, int collum) {
		super(line, collum);
	}

	@Override
	public boolean getIsEmptyCell() {
		return false;
	}

	@Override
	public boolean getIsMine() {
		return false;
	}

}
