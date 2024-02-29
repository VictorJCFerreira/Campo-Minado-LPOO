package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import rank.*;

public class GameOver_Window extends JFrame implements ActionListener{
    
    private JPanel panel;
    private JPanel buttonPanel;
    private JLabel gameOverLabel;
    private JLabel pointsLabel;
    private JButton button;
    private JPanel centralPanel;
    private JPanel topPanel;
    private JLabel upperLabel;
    private JLabel centralLabel;
    private int x;
    private int y;
    private int points;
    private JFrame gridFrame;
    private Player player;
    private int choice;
    private Grid_Window gridWindow;
    
    private ImageIcon mineIcon = new ImageIcon("src/images/mine.png");
    
    public GameOver_Window(Player player,int points, JFrame gridFrame, int rest,int choice, Grid_Window gridWindow) {
    	if(choice == 1) {
    		this.gridFrame = gridFrame;
            this.points = points;
            this.player = player;
            this.gridWindow = gridWindow;
            this.choice = choice;

            this.x = getToolkit().getScreenSize().width;
            this.y = getToolkit().getScreenSize().height;

            this.setLocation((x/2)-250, (y/2)-100);
            
            this.setSize(520,250);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            gameOverLabel = new JLabel("GAME OVER!");
            gameOverLabel.setFont(new Font("Arial",Font.BOLD, 70));
            gameOverLabel.setForeground(Color.yellow);
            gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
            gameOverLabel.setVerticalAlignment(JLabel.CENTER);

            pointsLabel = new JLabel("SCORE : " + this.points);
            pointsLabel.setFont(new Font("Arial",Font.PLAIN, 40));
            pointsLabel.setForeground(Color.yellow);
            pointsLabel.setHorizontalAlignment(JLabel.CENTER);
            pointsLabel.setVerticalAlignment(JLabel.CENTER);

            button = new JButton("OK");
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(100,50));
            button.setBackground(Color.yellow);
            button.addActionListener(this);

            buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(170,0));
            buttonPanel.setBackground(Color.black);
            buttonPanel.add(Box.createRigidArea(new Dimension(0,90)));
            buttonPanel.add(button);

            panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.black);
            panel.add(gameOverLabel,BorderLayout.NORTH);
            panel.add(pointsLabel,BorderLayout.CENTER);
            panel.add(buttonPanel,BorderLayout.EAST); 
            
            this.setIconImage(mineIcon.getImage());
            this.add(panel);
            this.setVisible(true);
    		
    		
    	} else if(choice == 2) {
    		
    		int x = Toolkit.getDefaultToolkit().getScreenSize().width;
            int y = Toolkit.getDefaultToolkit().getScreenSize().height;

            this.setLocation((x/2) - 200,(y/2) - 100);
            this.setSize(500,250);
            this.setResizable(false);
            this.gridFrame = gridFrame;

            centralPanel = new JPanel();
            centralPanel.setBackground(Color.black);

            topPanel = new JPanel(new BorderLayout());

            upperLabel = new JLabel();
            upperLabel.setFont(new Font("Arial",Font.PLAIN,30));
            upperLabel.setForeground(Color.white);
            upperLabel.setOpaque(true);
            upperLabel.setBackground(Color.black);
            upperLabel.setHorizontalAlignment(JLabel.CENTER);
            upperLabel.setVerticalAlignment(JLabel.CENTER);

            centralLabel = new JLabel();
            centralLabel.setHorizontalAlignment(JLabel.CENTER);
            centralLabel.setVerticalAlignment(JLabel.CENTER);

            if(rest == 1){
                upperLabel.setText("PLAYER 1 WON!");
                upperLabel.setForeground(new Color(0,162,232));  
            }
            else if(rest == 0){

                upperLabel.setText("PLAYER 2 WON!");
                upperLabel.setForeground(new Color(237,28,36));
                   
            }

            button = new JButton("OK");
            button.addActionListener(this);
            button.setFocusable(false);
            button.setFont(new Font("Arial",Font.PLAIN,20));
            button.setPreferredSize(new Dimension(120,60));
            button.setForeground(Color.white);
            button.setBackground(Color.gray);

            topPanel.add(upperLabel);
            centralPanel.add(centralLabel);
            centralPanel.add(Box.createRigidArea(new Dimension(20,0)));
            centralPanel.add(button);
        

            upperLabel.setHorizontalAlignment(JLabel.CENTER);
            upperLabel.setVerticalAlignment(JLabel.CENTER);
            upperLabel.setHorizontalTextPosition(SwingConstants.LEFT);

            this.setIconImage(mineIcon.getImage());
            this.add(upperLabel, BorderLayout.NORTH);
            this.add(centralPanel, BorderLayout.CENTER);
            this.setVisible(true);
    		
    	} else if (choice == 3) {
    		this.x = getToolkit().getScreenSize().width;
            this.y = getToolkit().getScreenSize().height;

            this.setLocation((x/2)-250, (y/2)-100);
            
            this.setSize(520,250);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            gameOverLabel = new JLabel("GAME OVER!");
            gameOverLabel.setFont(new Font("Arial",Font.BOLD, 70));
            gameOverLabel.setForeground(Color.yellow);
            gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
            gameOverLabel.setVerticalAlignment(JLabel.CENTER);
            
            button = new JButton("OK");
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(100,50));
            button.setBackground(Color.yellow);
            button.addActionListener(this);

            buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(170,0));
            buttonPanel.setBackground(Color.black);
            buttonPanel.add(Box.createRigidArea(new Dimension(0,90)));
            buttonPanel.add(button);

            panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.black);
            panel.add(gameOverLabel,BorderLayout.NORTH);
            panel.add(buttonPanel,BorderLayout.EAST); 
            
            this.setIconImage(mineIcon.getImage());
            this.add(panel);
            this.setVisible(true);
    		
    	}
    	
    	
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            
            if(choice == 1) {
            	this.dispose();
            	this.gridFrame.dispose();
            	this.player.setPoints(points);
                new Scoreboard(this.player);
                
            } else if (choice == 2) {
            	this.gridWindow.dispose();
            	this.gridWindow.dispose();
            } else {
            	this.dispose();
            }
            
            
        }
    }
    
}
