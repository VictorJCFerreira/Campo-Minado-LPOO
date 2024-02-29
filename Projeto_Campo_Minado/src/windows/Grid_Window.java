package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Exceptions.FoundMineException;
import Exceptions.OutOfBoundsException;
import cells.*;
import rank.*;
import grid.*;

public class Grid_Window extends JFrame{
    
    private JLabel[][] label;
    private JButton[][] button;
    private JLayeredPane[][] layeredPane;
    private JPanel[][] panel;
    private JPanel bigPanel;
    private JPanel upperPanel;
    private Basic_cell[][] cellGrid;
    private int lines;
    private int cols;
    private int round;
    private Player player;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel roundLabel;
    
    private ImageIcon mineFrame = new ImageIcon("src/images/bomb (3).png");
    private ImageIcon mineIcon = new ImageIcon("src/images/mine.png");
    private ImageIcon player1Icon = new ImageIcon("src/images/1 (1).png");
    private ImageIcon player2Icon = new ImageIcon("src/images/2 (1).png");
    private ImageIcon flagFrame = new ImageIcon("src/images/red-flag.png");
  

    public Grid_Window(Grid grid, Player player, Boolean twoPlayers, Boolean crazy){
        
        this.lines = grid.getLines();
        this.cols = grid.getCols();
        this.cellGrid = grid.getOnGrid();
        this.player = player;

        this.setSize(lines*40,(cols*40) + 70);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth())/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/2;

        this.setLocation(x,y);

        
        label = new JLabel[lines][cols];
        button = new JButton[lines][cols];
        panel = new JPanel[lines][cols];
        layeredPane = new JLayeredPane[lines][cols];
        bigPanel = new JPanel(new GridLayout(lines,cols));
        upperPanel = new JPanel(new BorderLayout());
        round = 0;

        if(!twoPlayers){
            roundLabel = new JLabel("Round :" + Integer.toString(round));
            roundLabel.setFont(new Font("Arial",Font.PLAIN, 40));
            roundLabel.setForeground(Color.WHITE);
            roundLabel.setHorizontalAlignment(JLabel.CENTER);
            roundLabel.setVerticalAlignment(JLabel.CENTER);     
            upperPanel.add(roundLabel);
        }
        
        else if(twoPlayers){
            player1Label = new JLabel(player1Icon);
            player2Label = new JLabel(player2Icon);
            player2Label.setVisible(false);
            player1Label.setPreferredSize(new Dimension(50,50));
            player2Label.setPreferredSize(new Dimension(50,50));   
            upperPanel.setBackground(Color.BLACK);
            upperPanel.setForeground(Color.WHITE);
            upperPanel.add(player1Label,BorderLayout.WEST);
            upperPanel.add(player2Label,BorderLayout.EAST);
        }

        upperPanel.setBackground(Color.BLACK);

        for(int i = 0; i < lines ; i++){
            for(int j = 0; j < cols; j++){
                final int line = i;
                final int col = j;
                button[i][j] = new JButton();

                button[i][j].setBounds(0,0,50,50);
                button[i][j].setFocusable(false);
                button[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                button[i][j].setBackground(Color.white);
                button[i][j].setForeground(Color.white);

                button[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if(e.getButton() == MouseEvent.BUTTON1 && !cellGrid[line][col].getIsFlagged()){
                            try{
                                grid.revealCells(line, col);
                                round++;
                                if(!twoPlayers){
                                    roundLabel.setText("Round :" + Integer.toString(round));
                                }
                                else if(twoPlayers){
                                    if(round % 2 == 0){
                                        player1Label.setVisible(true);
                                        player2Label.setVisible(false);
                                    }
                                    else if(round % 2 == 1){
                                        player1Label.setVisible(false);
                                        player2Label.setVisible(true);
                                    }
                                }
                                if(grid.checkWin()){
                                	
                                     if(!twoPlayers && !crazy){
                                    	 gameOver();
                                    }
                                     else if(!twoPlayers && crazy){
                                    	 gameOverCrazy();
                                    } 
                                    else if(twoPlayers){
                                        victoryGameOver2();
                                    } 
                                    stopGame();
                                }
                            }catch(FoundMineException foundMine){                              
                                 	stopGame();
                                if(!twoPlayers && !crazy){
                                    gameOver();
                                }
                                else if(!twoPlayers && crazy){
                               	 gameOverCrazy();
                               } 
                                else if(twoPlayers){
                                    loseGameOver2();
                                } 
                            }catch(OutOfBoundsException outOfBounds){
                                System.out.println("out of bounds");
                            }

                            for(int i = 0; i < lines; i++){
                                for(int j = 0 ; j < cols; j++){
                                    if(cellGrid[i][j].getVisibility()){
                                        button[i][j].setVisible(false);
                                    }
                                }
                            }
                        }
                        else if(e.getButton() == MouseEvent.BUTTON3){
    
                            cellGrid[line][col].setIsFlagged(!cellGrid[line][col].getIsFlagged()); 
                            
                            if(cellGrid[line][col].getIsFlagged() == true){
                            	button[line][col].setText("");
                            	button[line][col].setIcon(flagFrame);
                            }
                            else if(cellGrid[line][col].getIsFlagged() == false){
                                button[line][col].setText("");
                                button[line][col].setIcon(null);
                            }

                             if(cellGrid[line][col].getCrazy() && !cellGrid[line][col].getIsMine()){
                                cellGrid[line][col] = new Mine_cell(line, col);
                                grid.updateNeighbours();
                                changeLabels();
                            }

                        }
                    }

                });
                
                label[i][j] = new JLabel(cellGrid[i][j].getContent());

                if(label[i][j].getText().equals("*")){
                    label[i][j].setText("");
                    label[i][j].setIcon(mineFrame);
                }
                if(label[i][j].getText().equals("0")){
                    label[i][j].setText("");
                }
                if(label[i][j].getText().equals("1")){
                    label[i][j].setForeground(new Color(92,101,192));
                }
                if(label[i][j].getText().equals("2")){
                    label[i][j].setForeground(new Color(55,159,122));
                }
                if(label[i][j].getText().equals("3")){
                    label[i][j].setForeground(new Color(250,75,0));
                }
                if(label[i][j].getText().equals("4")){
                    label[i][j].setForeground(new Color(136,112,255));
                }
                if(label[i][j].getText().equals("5")){
                    label[i][j].setForeground(new Color(146,178,167));
                }
                if(label[i][j].getText().equals("6")){
                    label[i][j].setForeground(new Color(179, 32, 77));
                }
                if(label[i][j].getText().equals("7")){
                    label[i][j].setForeground(new Color(141,201,181));
                }
                if(label[i][j].getText().equals("8")){
                	label[i][j].setForeground(new Color(64,4,3));
                }

                label[i][j].setFont(new Font("Arial", Font.PLAIN,30));
                label[i][j].setBounds(0,0,45,45);
                label[i][j].setHorizontalAlignment(JLabel.CENTER);
                label[i][j].setVerticalAlignment(JLabel.CENTER);

                panel[i][j] = new JPanel(new BorderLayout());
                panel[i][j].setBounds(0,0,45,45);
                panel[i][j].setBorder(BorderFactory.createBevelBorder(1));
                panel[i][j].setBackground(new Color(170,170,170));

                if(cellGrid[i][j].getCrazy()){
                    panel[i][j].setBackground(Color.yellow);
                }

                panel[i][j].add(label[i][j]);

                layeredPane[i][j] = new JLayeredPane();
                layeredPane[i][j].add(panel[i][j], JLayeredPane.DEFAULT_LAYER);
                layeredPane[i][j].add(button[i][j], JLayeredPane.DRAG_LAYER);
                
                bigPanel.add(layeredPane[i][j]);
            }
        }

        this.setResizable(false);
        this.setIconImage(mineIcon.getImage());
        this.add(bigPanel);
        this.add(upperPanel,BorderLayout.NORTH);
        this.setVisible(false);
    }

    
    private int totalPoints(){
        
        int flaggedCells = 0;
        int total = 0;

        for(int i = 0; i < this.lines; i++){
            for(int j = 0; j < this.cols; j++){
                if(cellGrid[i][j].getIsFlagged() && cellGrid[i][j].getIsMine()){
                	flaggedCells++;
                }
            }
        }

        total = flaggedCells * 50;
        return total;
    }
    

    private void changeLabels(){
        for(int i = 0; i < lines; i++){
            for(int j = 0 ; j < cols; j++){
                label[i][j].setText(cellGrid[i][j].getContent());

                if(label[i][j].getText().equals("*")){
                    label[i][j].setText("");
                    label[i][j].setIcon(mineFrame);
                }
                if(label[i][j].getText().equals("0")){
                    label[i][j].setText("");
                }
                if(label[i][j].getText().equals("1")){
                	label[i][j].setForeground(new Color(92,101,192));
                }
                if(label[i][j].getText().equals("2")){
                    label[i][j].setForeground(new Color(55,159,122));
                }
                if(label[i][j].getText().equals("3")){
                    label[i][j].setForeground(new Color(250,75,0));
                }
                if(label[i][j].getText().equals("4")){
                    label[i][j].setForeground(new Color(136,112,255));
                }
                if(label[i][j].getText().equals("5")){
                    label[i][j].setForeground(new Color(36,0,71));
                }
                if(label[i][j].getText().equals("6")){
                    label[i][j].setForeground(new Color(179, 32, 77));
                }
                if(label[i][j].getText().equals("7")){
                    label[i][j].setForeground(new Color(141,201,181));
                }
                if(label[i][j].getText().equals("8")){
                	label[i][j].setForeground(new Color(64,4,3));
                }
            }
        }
    }

    private void stopGame(){
        for(int i = 0; i < lines; i++){
            for(int j = 0; j < cols; j++){

                button[i][j].setVisible(false);
        }
    }
}
    
    private void gameOver(){
    	new GameOver_Window(this.player, totalPoints(), this, 0, 1,this);
    }
    
    private void gameOverCrazy(){
    	new GameOver_Window(this.player, 0, this, 0, 3,this);
    }

    private void victoryGameOver2(){
    	new GameOver_Window(this.player, 0, this, (round + 1) % 2, 2,this);
    }

    private void loseGameOver2(){
    	new GameOver_Window(this.player, 0, this, (round% 2), 2,this);
    }

}
