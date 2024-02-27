package cells;

public class Empty_cell extends Basic_cell {
	
	public Empty_cell(int line, int collum) {
		super(line, collum);
		setContent("0");
	}

	
	@Override
	public boolean getIsEmptyCell() {
		return true;
	}

	
	@Override
	public boolean getIsMine() {
		return false;
	}

}
