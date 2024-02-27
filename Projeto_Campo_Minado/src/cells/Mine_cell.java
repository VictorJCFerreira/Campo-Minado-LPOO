package cells;

public class Mine_cell extends Basic_cell {

	public Mine_cell(int line, int collum) {
		super(line, collum);
		setContent("*");
	}

	
	@Override
	public boolean getIsEmptyCell() {
		return false;
	}
	

	@Override
	public boolean getIsMine() {
		return true;
	}

}
