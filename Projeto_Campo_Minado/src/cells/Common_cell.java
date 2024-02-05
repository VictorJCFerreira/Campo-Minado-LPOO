package cells;

import java.awt.event.*;
import javax.swing.*;

import gameLogic.*;

@SuppressWarnings("serial")
public class Common_cell extends JButton implements I_Cell {

    private int type;
    private boolean discovered;
    private boolean flagged;
    private boolean crazyness;
    private String frame;

    private Handler handler;

    public Common_cell() {
    	
        this.type = 0; // TYPES -- 0: Empty or Common, 1: Mine, 2: NearCell, 3: CrazyCell
        this.discovered = false;
        this.flagged = false;
        this.frame = "$";

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    rightClickButton();
                } else {
                    clickButton();
                }
            }

            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
        
    }

    public void clickButton() {
        handler.click(this);
    }

    
    public void rightClickButton() {
        handler.rightClick(this);
    }
    
	public void setType(int type) {
		this.type = type;	
	}
	
	
    public int getType() {
        return type;
    }

    
    public boolean getDiscovered() {
        return this.discovered;
    }

    
    public void setDiscovered(boolean d) {
        this.discovered = d;
    }

    
    public void setFlagged(boolean f) {
        this.flagged = f;
    }
    
    
    public boolean getFlagged() {
        return this.flagged;
    }
    
    
    public void setFrame(String frame) {
    	this.frame = frame;
    }
    
    
    public String getFrame() {
    	return this.frame;
    }
    
    
    public boolean isEmptyCell(){
        return false;
    }
    
    
    public boolean isMine(){
        return false;
    }

    
    public boolean isNearMine() {
        return false;
    }

    
    public boolean isCrazyCell(){
        return this.crazyness;
    }

    
    public void changeToCrazyCell(){
        this.crazyness = true;
    }
    
    
}

	

