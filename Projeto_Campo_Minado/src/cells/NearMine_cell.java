package cells;

public class NearMine_cell extends Common_cell {

	public NearMine_cell() {
		super();
	}
	
	 public boolean isEmptyCell()
	    {
	        return false;
	    }
	    
	    public boolean isMine()
	    {
	        return false;
	    }

	    public boolean isNearMine()
	    {
	        return true;
	    }


}
