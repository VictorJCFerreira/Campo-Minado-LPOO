package cells;

import javax.swing.*;

public class Common_cell extends JButton{
	
	private int type;
	private int position;
	private boolean discovered;
	private boolean flagged;

	public Common_cell(int type,int position, boolean discovered, boolean flagged) {
		this.type = type;
		this.position = position;
		this.discovered = discovered;
		this.flagged = flagged;
	}
	
	public int getType() {
		//0 = Vazia ; 1 = Mina ; 2 = Numero de Celulas ao redor;
		
		return type;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean isDircovered() {
		return discovered;
	}
	
	public void setDiscovered(boolean d) {
		this.discovered = d;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public void setFlagged(boolean f) {
		this.flagged = f;
	}
}
