package cells;

public abstract class A_Cell {
	
	
	 public int[] gridPosition;
	 private boolean visibility;
	 private int minesNearBy;
	 private boolean flagged;
	 private int state;
	 private String content;
	 private boolean crazy;
	 

	public A_Cell(int line, int collum) {
		
		this.gridPosition = new int[2];
        this.gridPosition[0] = line;
        this.gridPosition[1] = collum;
        this.content = "!";
        this.flagged = false;
        this.setState(0);
	}
	
	
	public abstract boolean getIsEmptyCell();
	
	
	public abstract boolean getIsMine();
	
	
	public boolean getVisibility() {
		return visibility;
	}


	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}


	public int getMinesNearBy() {
        return minesNearBy;
    }
	
	public boolean getIsNearByMineCell() {
		return false;
	}
	
	
    public void setMinesNearBy(int numberMines){
        this.minesNearBy = numberMines;
    }
    
    
    public void setIsFlagged(boolean flag){
    	this.flagged = flag;
    }
    

    public boolean getIsFlagged() {
        return flagged;
    }
    
    
    public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setCrazy(Boolean isCrazy) {
        this.crazy = isCrazy;
    }
    

    public boolean getCrazy() {
        return crazy;
    }
    
    public void beCrazy() {
    	this.crazy = true;
    }


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}
    
}
 

	

