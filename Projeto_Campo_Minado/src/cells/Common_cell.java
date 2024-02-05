package cells;

import java.awt.event.*;
import javax.swing.*;
import handler.*;

public class Common_cell extends JButton implements I_Cell {

    private int type;
    private int position;
    private boolean discovered;
    private boolean flagged;
    private boolean crazyness;

    private Handler handler;

    public Common_cell(int type, int position, boolean discovered, boolean flagged, Handler handler) {
        this.type = type;
        this.position = position;
        this.discovered = discovered;
        this.flagged = flagged;
        this.handler = handler;

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

	public void setType(int type) {
		this.type = type;	
	}
    public int getType() {
        return type;
    }

    public int getPosition() {
        return position;
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
        return false;
    }

    public boolean isCrazyCell(){
        return this.crazyness;
    }

    public void beCrazyCell(){
        this.crazyness = true;
    }
    public void clickButton() {
        handler.click(this);
    }

    public void rightClickButton() {
        handler.rightClick(this);
    }
}

	

