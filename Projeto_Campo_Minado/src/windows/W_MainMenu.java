package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import grid.*;
import rank.*;

public class W_MainMenu extends JFrame implements ActionListener {

    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton crazyButton;
    private JButton rankButton;
    private JButton twoPButton;
    private static JFrame upTitle;
    private static String title;
    private JPanel upperPanel;
    private JPanel midPanel;
    private Grid grid;
    private Grid_Window gridWindow;
    private boolean twoPOption = false;
    private boolean crazy = false;
    private Player player;
    
    private ImageIcon playerIcon = new ImageIcon("src/images/1(1).png");
    private ImageIcon mineIcon = new ImageIcon("src/images/mine.png");
   

    public W_MainMenu(String title) {
    	
    	W_MainMenu.title = title;
        upTitle = new JFrame(title);
        
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	int x = ((screenSize.width - this.getWidth()) / 2) - 210;
        int y = ((screenSize.height - this.getHeight()) / 2) - 210;
        
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720,720);
        
        JLabel upperLabel = new JLabel("MINESWEEPER");
        upperLabel.setFont(new Font("Arial", Font.BOLD, 46));
        
        upperPanel = new JPanel();
        upperPanel.setBackground(Color.WHITE);
        upperPanel.setPreferredSize(new Dimension(0, 80));
        upperPanel.add(upperLabel);
        
        midPanel = new JPanel(new GridBagLayout());
        midPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        
        easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Arial", Font.PLAIN, 30));
        easyButton.setBackground(Color.WHITE);
        easyButton.setPreferredSize(new Dimension(200, 100));
        easyButton.setFocusable(false);
        easyButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        midPanel.add(easyButton, gbc);
        
        mediumButton = new JButton("Medium");
        mediumButton.setFont(new Font("Arial", Font.PLAIN, 30));
        mediumButton.setBackground(Color.WHITE);
        mediumButton.setPreferredSize(new Dimension(200, 100));
        mediumButton.setFocusable(false);
        mediumButton.addActionListener(this);
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        midPanel.add(mediumButton, gbc);
        
        hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Arial", Font.PLAIN, 30));
        hardButton.setBackground(Color.WHITE);
        hardButton.setPreferredSize(new Dimension(200, 100));
        hardButton.setFocusable(false);
        hardButton.addActionListener(this);
        gbc.gridx = 20;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        midPanel.add(hardButton, gbc);
        
        crazyButton = new JButton("Crazy Mode");
        crazyButton.setFont(new Font("Arial", Font.PLAIN, 30));
        crazyButton.setBackground(Color.WHITE);
        crazyButton.setPreferredSize(new Dimension(200, 100));
        crazyButton.setFocusable(false);
        crazyButton.addActionListener(this);
        gbc.gridx = 10;
        gbc.gridy = 2;
        midPanel.add(crazyButton, gbc);
        
        twoPButton = new JButton("Two Player");
        twoPButton.setFont(new Font("Arial", Font.PLAIN, 30));
        twoPButton.setBackground(Color.WHITE);
        twoPButton.setPreferredSize(new Dimension(200, 100));
        twoPButton.setFocusable(false);
        twoPButton.addActionListener(this);
        gbc.gridx = 20;
        gbc.gridy = 2;
        midPanel.add(twoPButton, gbc);
        
        rankButton = new JButton("Rank");
        rankButton.setFont(new Font("Arial", Font.PLAIN, 30));
        rankButton.setBackground(Color.WHITE);
        rankButton.setPreferredSize(new Dimension(200, 100));
        rankButton.setFocusable(false);
        rankButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        midPanel.add(rankButton, gbc);
        
        this.setResizable(false);
        this.setIconImage(mineIcon.getImage());
        this.setLocationRelativeTo(null);
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(midPanel, BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }
    
    public static void update(int flagged) {
    	upTitle.setTitle(title + " - Flags: " + flagged);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
	    	if (e.getSource() == easyButton) {
	    		 String name = (String) JOptionPane.showInputDialog(null, "Name:", "Player", JOptionPane.PLAIN_MESSAGE, playerIcon, null, null);
	             this.player = new Player(name);
	             if (name != null) {
	            	 
	            	 int sides = 9;
	            	 int mines = 11;
	            	 this.grid = new Grid(mines,sides,sides);
	            	 
	            	 gridWindow = new Grid_Window(grid, this.player, twoPOption, crazy);
	      			gridWindow.setVisible(true);
	      	        this.dispose();
	            	 
	             }
	            
	        }
	    	
    	
	        if (e.getSource() == mediumButton) {
	        	String name = (String) JOptionPane.showInputDialog(null, "Name:", "Player", JOptionPane.PLAIN_MESSAGE, playerIcon, null, null);
	             this.player = new Player(name);
	             if (name != null) {
	            	 
	            	 int sides = 12;
	            	 int mines = 17;
	            	 this.grid = new Grid(mines,sides,sides);
	            	 
	            	 gridWindow = new Grid_Window(grid, this.player, twoPOption, crazy);
	      			gridWindow.setVisible(true);
	      	        this.dispose();
	            	 
	             }
        
	        }
	        
	        
			if (e.getSource() == hardButton) {
				
				String name = (String) JOptionPane.showInputDialog(null, "Name:", "Player", JOptionPane.PLAIN_MESSAGE, playerIcon, null, null);
	             this.player = new Player(name);	
	             if (name != null) {

	            	 int sides = 15;
	            	 int mines = 35;
	            	 this.grid = new Grid(mines,sides,sides);
	            	 
	            	gridWindow = new Grid_Window(grid, this.player, twoPOption, crazy);
	      			gridWindow.setVisible(true);
	      	        this.dispose();
	            	 
	             }
				            
			}
			
			if (e.getSource() == crazyButton) {
				
	            	 int sides = 15;
	            	 int mines = 27;
	            	 this.grid = new Grid(mines,sides,sides);
	            	 this.grid.setCrazyGrid(5);
	            	 this.crazy = true;
	            	 
	            	gridWindow = new Grid_Window(grid, this.player, twoPOption, crazy);
	      			gridWindow.setVisible(true);
	      	        this.dispose();
	            	
	            
	        }
			
			if (e.getSource() == twoPButton) {
				
	            	 int sides = 15;
	            	 int mines = 27;
	            	 this.grid = new Grid(mines,sides,sides); 
	            	 this.twoPOption = true;
	            	 
	            	gridWindow = new Grid_Window(grid, this.player, twoPOption, crazy);
	      			gridWindow.setVisible(true);
	      	        this.dispose();
	            	 
	            	 
			}
	        
	        if (e.getSource() == rankButton) { 
	        	new Rank_Window();
	        }
	        
	        
	        
    }
}
