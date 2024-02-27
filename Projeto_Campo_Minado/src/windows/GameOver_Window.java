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
    private Grid_Window playGrid;
    private int x;
    private int y;
    private int points;
    private JFrame gridFrame;
    private Player player;

    ImageIcon bomberIcon = new ImageIcon("images/bomber.png");
    
    public GameOver_Window(Player player,int points, JFrame gridFrame, int resto,int choice) {
    	if(choice == 1) {
    		this.gridFrame = gridFrame;
            this.points = points;
            this.player = player;

            this.x = getToolkit().getScreenSize().width;
            this.y = getToolkit().getScreenSize().height;

            this.setLocation((x/2)-250, (y/2)-100);
            
            this.setSize(500,240);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            gameOverLabel = new JLabel("GAME OVER!");
            gameOverLabel.setFont(new Font("Comic Sans MS",Font.BOLD, 70));
            gameOverLabel.setForeground(Color.white);
            gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
            gameOverLabel.setVerticalAlignment(JLabel.CENTER);

            pointsLabel = new JLabel("Pontuação : " + this.points);
            pointsLabel.setFont(new Font("Comic Sans MS",Font.PLAIN, 35));
            pointsLabel.setForeground(new Color(255,184,28));
            pointsLabel.setHorizontalAlignment(JLabel.CENTER);
            pointsLabel.setVerticalAlignment(JLabel.CENTER);

            button = new JButton("ok");
            button.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(100,50));
            button.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            button.setBackground(Color.lightGray);
            button.addActionListener(this);

            buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(170,0));
            buttonPanel.setBackground(Color.RED);
            buttonPanel.add(Box.createRigidArea(new Dimension(0,90)));
            buttonPanel.add(button);

            panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.red);
            panel.add(gameOverLabel,BorderLayout.NORTH);
            panel.add(pointsLabel,BorderLayout.CENTER);
            panel.add(buttonPanel,BorderLayout.EAST); 
            
            this.setIconImage(bomberIcon.getImage());
            this.add(panel);
            //this.setUndecorated(true);
            this.setVisible(true);
    		
    		
    	} else if(choice == 2) {
    		
    		int x = Toolkit.getDefaultToolkit().getScreenSize().width;
            int y = Toolkit.getDefaultToolkit().getScreenSize().height;

            this.setLocation((x/2) - 200,(y/2) - 100);
            this.setSize(400,200);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.setIconImage(bomberIcon.getImage());
            this.gridFrame = gridFrame;

            Border bordaDourada = BorderFactory.createLineBorder(new Color(255,184,28), 2);

            centralPanel = new JPanel();
            centralPanel.setBackground(Color.black);
            centralPanel.setBorder(bordaDourada);

            topPanel = new JPanel(new BorderLayout());

            upperLabel = new JLabel();
            upperLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
            upperLabel.setForeground(Color.white);
            upperLabel.setOpaque(true);
            upperLabel.setBackground(Color.black);
            upperLabel.setBorder(bordaDourada);
            upperLabel.setHorizontalAlignment(JLabel.CENTER);
            upperLabel.setVerticalAlignment(JLabel.CENTER);

            centralLabel = new JLabel();
            centralLabel.setHorizontalAlignment(JLabel.CENTER);
            centralLabel.setVerticalAlignment(JLabel.CENTER);

            if(resto == 1){
                upperLabel.setText("Vitória do Player 1!");
                upperLabel.setForeground(new Color(0,162,232));
                //centralLabel.setIcon(player1x50Icon);    
            }
            else if(resto == 0){

                upperLabel.setText("Vitória do Player 2!");
                upperLabel.setForeground(new Color(237,28,36));
                //centralLabel.setIcon(player2x50Icon);    
            }

            button = new JButton("jóia!");
            button.addActionListener(this);
            button.setFocusable(false);
            button.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
            button.setPreferredSize(new Dimension(120,60));
            button.setForeground(Color.white);
            button.setBackground(Color.black);
            button.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 4));

            topPanel.add(upperLabel);
            centralPanel.add(centralLabel);
            centralPanel.add(Box.createRigidArea(new Dimension(20,0)));
            centralPanel.add(button);
        

            upperLabel.setHorizontalAlignment(JLabel.CENTER);
            upperLabel.setVerticalAlignment(JLabel.CENTER);
            upperLabel.setHorizontalTextPosition(SwingConstants.LEFT);

            this.add(upperLabel, BorderLayout.NORTH);
            this.add(centralPanel, BorderLayout.CENTER);
            this.setVisible(true);
    		
    	}
    	
    	
    }
    
    
    public void actionPerformed1(ActionEvent e){
        if(e.getSource() == button){
            this.dispose();
            this.gridFrame.dispose();
            this.player.setPoints(points);
            new Scoreboard(this.player);
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            this.dispose();
        }
    }
}
