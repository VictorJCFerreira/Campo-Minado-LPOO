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
    private Player player;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel roundLabel;
    private int round;
    
    private ImageIcon bombaIcon = new ImageIcon("");
    private ImageIcon player1Icon = new ImageIcon("");
    private ImageIcon player2Icon = new ImageIcon("");
    private ImageIcon bomberIcon = new ImageIcon("");

    public Grid_Window(Grid grid, Player player, Boolean twoPlayers){
        
        this.lines = grid.getLines();
        this.cols = grid.getCols();
        this.cellGrid = grid.getOnGrid();
        this.player = player;

        this.setSize(lines*50,(cols*50));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        
        label = new JLabel[lines][cols];
        button = new JButton[lines][cols];
        panel = new JPanel[lines][cols];
        layeredPane = new JLayeredPane[lines][cols];
        bigPanel = new JPanel(new GridLayout(lines,cols));
        upperPanel = new JPanel(new BorderLayout());
        round = 0;

        if(!twoPlayers){
            roundLabel = new JLabel("round " + Integer.toString(round));
            roundLabel.setFont(new Font("Arial",Font.PLAIN, 40));
            roundLabel.setForeground(Color.WHITE);
            roundLabel.setHorizontalAlignment(JLabel.CENTER);
            roundLabel.setVerticalAlignment(JLabel.CENTER);     
            upperPanel.setPreferredSize(new Dimension(0,70));
            upperPanel.setBackground(new Color(192,192,192));
            upperPanel.add(roundLabel);
        }
        
        else if(twoPlayers){
            player1Label = new JLabel(player1Icon);
            player2Label = new JLabel(player2Icon);
            player2Label.setVisible(false);
            player1Label.setPreferredSize(new Dimension(50,50));
            player2Label.setPreferredSize(new Dimension(50,50));   
            upperPanel.setBackground(Color.WHITE);
            upperPanel.add(player1Label,BorderLayout.WEST);
            upperPanel.add(player2Label,BorderLayout.EAST);
        }

        upperPanel.setBackground(Color.WHITE);

        for(int i = 0; i < lines ; i++){
            for(int j = 0; j < cols; j++){
                final int linha = i;
                final int col = j;
                button[i][j] = new JButton();

                button[i][j].setBounds(0,0,60,60);
                button[i][j].setFocusable(false);
                button[i][j].setFont(new Font("Arial", Font.PLAIN, 25));
                button[i][j].setBackground(Color.GRAY);
                button[i][j].setForeground(Color.white);
                button[i][j].setBorder(BorderFactory.createLineBorder(new Color(143,143,143), 3));

                button[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if(e.getButton() == MouseEvent.BUTTON1 && !cellGrid[linha][col].getIsFlagged()){
                            try{
                                grid.revealCells(linha, col);
                                round++;
                                if(!twoPlayers){
                                    roundLabel.setText("round " + Integer.toString(round));
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

                                     if(!twoPlayers){
                                    	 gameOver();
                                    }
                                    else if(twoPlayers){
                                        victoryGameOver2();
                                    } 
                                    stopGame();

                                }
                            }catch(FoundMineException foundMine){
                                
                                 	stopGame();
                                if(!twoPlayers){
                                    gameOver();
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
    
                            cellGrid[linha][col].setIsFlagged(rootPaneCheckingEnabled); 
                            
                            if(cellGrid[linha][col].getIsFlagged() == true){
                                button[linha][col].setText("B");
                            }
                            else if(cellGrid[linha][col].getIsFlagged() == false){
                                button[linha][col].setText("");
                            }

                             if(cellGrid[linha][col].getCrazy() && !cellGrid[linha][col].getIsMine()){
                                cellGrid[linha][col] = new Mine_cell(linha, col);
                                grid.calculateNeighbours(linha,col);
                                changeLabels();
                            }

                        }
                    }

                });
                
                label[i][j] = new JLabel(cellGrid[i][j].getContent());

                if(label[i][j].getText().equals("*")){
                    label[i][j].setText("");
                    label[i][j].setIcon(bombaIcon);
                }
                if(label[i][j].getText().equals("0")){
                    label[i][j].setText("");
                }
                if(label[i][j].getText().equals("1")){
                    label[i][j].setForeground(Color.BLUE);
                }
                if(label[i][j].getText().equals("2")){
                    label[i][j].setForeground(new Color(34,139,34));
                }
                if(label[i][j].getText().equals("3")){
                    label[i][j].setForeground(Color.RED);
                }
                if(label[i][j].getText().equals("4")){
                    label[i][j].setForeground(new Color(13,33,79));
                }
                if(label[i][j].getText().equals("5")){
                    label[i][j].setForeground(new Color(80,48,30));
                }
                if(label[i][j].getText().equals("6")){
                    label[i][j].setForeground(new Color(0, 139, 139));
                }
                if(label[i][j].getText().equals("7")){
                    label[i][j].setForeground(Color.BLACK);
                }
                if(label[i][j].getText().equals("8")){
                    label[i][j].setForeground(Color.CYAN);
                }

                label[i][j].setFont(new Font("Arial", Font.BOLD,50));
                label[i][j].setBounds(0,0,70,70);
                label[i][j].setHorizontalAlignment(JLabel.CENTER);
                label[i][j].setVerticalAlignment(JLabel.CENTER);

                panel[i][j] = new JPanel(new BorderLayout());
                panel[i][j].setBounds(0,0,70,70);
                panel[i][j].setBorder(BorderFactory.createBevelBorder(1));
                panel[i][j].setBackground(new Color(170,170,170));

                if(cellGrid[i][j].getCrazy()){
                    panel[i][j].setBackground(new Color(122,198,198));
                }

                panel[i][j].add(label[i][j]);

                layeredPane[i][j] = new JLayeredPane();
                layeredPane[i][j].add(panel[i][j], JLayeredPane.DEFAULT_LAYER);
                layeredPane[i][j].add(button[i][j], JLayeredPane.DRAG_LAYER);
                
                bigPanel.add(layeredPane[i][j]);
            }
        }

        this.setResizable(false);
        this.setIconImage(bomberIcon.getImage());
        this.add(bigPanel);
        this.add(upperPanel,BorderLayout.NORTH);
        this.setVisible(false);
    }

    
    
    private void gameOver(){
    	new GameOver_Window(this.player, totalPoints(), this, 0, 1);
    }

    private void victoryGameOver2(){
    	new GameOver_Window(this.player, totalPoints(), this, (round + 1) % 2, 2);
    }

    private void loseGameOver2(){
    	new GameOver_Window(this.player, totalPoints(), this, (round% 2), 2);
    }

    private int totalPoints(){
        
        int openSpaces = 0;
        int total = 0;

        for(int i = 0; i < this.lines; i++){
            for(int j = 0; j < this.cols; j++){
                if(cellGrid[i][j].getVisibility() && !cellGrid[i][j].getIsMine()){
                    openSpaces++;
                }
            }
        }

        total = openSpaces * 20;
        return total;
    }
    

    private void changeLabels(){
        for(int i = 0; i < lines; i++){
            for(int j = 0 ; j < cols; j++){
                label[i][j].setText(cellGrid[i][j].getContent());

                if(label[i][j].getText().equals("*")){
                    label[i][j].setText("");
                    label[i][j].setIcon(bombaIcon);
                }
                if(label[i][j].getText().equals("0")){
                    label[i][j].setText("");
                }
                if(label[i][j].getText().equals("1")){
                    label[i][j].setForeground(Color.BLUE);
                }
                if(label[i][j].getText().equals("2")){
                    label[i][j].setForeground(Color.GREEN);
                }
                if(label[i][j].getText().equals("3")){
                    label[i][j].setForeground(Color.RED);
                }
                if(label[i][j].getText().equals("4")){
                    label[i][j].setForeground(new Color(13,33,79));
                }
                if(label[i][j].getText().equals("5")){
                    label[i][j].setForeground(new Color(80,48,30));
                }
                if(label[i][j].getText().equals("6")){
                    label[i][j].setForeground(new Color(0, 139, 139));
                }
                if(label[i][j].getText().equals("7")){
                    label[i][j].setForeground(Color.BLACK);
                }
                if(label[i][j].getText().equals("8")){
                    label[i][j].setForeground(Color.DARK_GRAY);
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
}
