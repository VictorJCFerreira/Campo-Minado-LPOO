package cells;

public interface I_Cell {
	
	public abstract void setType(int type);
    
    public abstract int getType(); // TYPES -- 0: Empty, 1: Mine, 2: NearCell, 3: CrazyCell
    
    public abstract void setDiscovered(boolean discovered);

    public abstract boolean getDiscovered();
    
    public abstract void setFlagged(boolean flagged);

    public abstract boolean getFlagged();
    
    public abstract void setFrame(String frame);
    
    public abstract String getFrame();

    public abstract boolean isEmptyCell();

    public abstract boolean isMine();

    public abstract boolean isNearMine();

    public abstract boolean isCrazyCell();
	
}
