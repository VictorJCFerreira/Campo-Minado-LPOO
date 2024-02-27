package cells;

public class NearBy_cell extends Basic_cell {
	
	private int minesNearBy;
    private final boolean isMinesNearBy = true;

	public NearBy_cell(int line, int collum, int minesNearBy) {
		super(line, collum);
		this.minesNearBy = minesNearBy;
	}
	
	public boolean isIsMinesNearBy() {
        return isMinesNearBy;
    }
    public int getMinesNearBy() {
        return minesNearBy;
    }

	@Override
	public boolean getIsEmptyCell() {
		return false;
	}

	@Override
	public boolean getIsMine() {
		return false;
	}
	
	public boolean getIsNearByMineCell() {
		return true;
	}


	
	
}
