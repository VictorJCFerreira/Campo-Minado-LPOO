package cells;

public class Mine_cell extends Common_cell {

	public Mine_cell() {
		super();
		setFrame("*");
	}
	
	public boolean isEmptyCell()
    {
        return false;
    }
    
    public boolean isMine()
    {
        return true;
    }

    public boolean isNearMine()
    {
        return false;
    }

}
