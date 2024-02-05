package cells;

@SuppressWarnings("serial")
public class Empty_cell extends Common_cell {

	public Empty_cell() {
		super();
		setFrame("0");
	}
	
	public boolean isEmptyCell()
    {
        return true;
    }
    
    public boolean isMine()
    {
        return false;
    }

    public boolean isNearMine()
    {
        return false;
    }

}
