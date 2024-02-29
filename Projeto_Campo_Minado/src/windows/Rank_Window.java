package windows;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import rank.Scoreboard;

public class Rank_Window extends JFrame {

    private JLabel[] positionsLabels;
    private JLabel[] spaceLabel;
    private JLabel[] namesLabels;
    private JLabel[] pointsLabels;
    private JLabel upperLaber;
    private JPanel topPanel;
    private JPanel centralPanel;
    private JPanel leftPanel;
    private Scoreboard ranked = new Scoreboard();
    private ArrayList<String> names;
    private ArrayList<String> points;
    
    private ImageIcon mineIcon = new ImageIcon("src/images/mine.png");
    
    public Rank_Window() {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = ((screenSize.width - this.getWidth()) / 2) - 720;
        int y = ((screenSize.height - this.getHeight()) / 2) - 420;

        this.setLocation(x,y);

        names = ranked.getNames();
        points = ranked.getPoints();


        centralPanel = new JPanel(new GridLayout(10, 3));
        centralPanel.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.setBackground(Color.WHITE);

        leftPanel = new JPanel(new GridLayout(10, 1));
        leftPanel.setAlignmentX(CENTER_ALIGNMENT);
        leftPanel.setBackground(Color.WHITE);

        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(0, 70));
        topPanel.setBackground(Color.WHITE);

        upperLaber = new JLabel("RANK");
        upperLaber.setFont(new Font("Arial", Font.BOLD, 50));
        upperLaber.setHorizontalAlignment(JLabel.CENTER);
        upperLaber.setVerticalAlignment(JLabel.CENTER);
        topPanel.add(upperLaber);

        if (names.size() < 5) {
            positionsLabels = new JLabel[names.size()];
            spaceLabel = new JLabel[names.size()];
            namesLabels = new JLabel[names.size()];
            pointsLabels = new JLabel[names.size()];
        } else {
            positionsLabels = new JLabel[5];
            spaceLabel = new JLabel[5];
            namesLabels = new JLabel[5];
            pointsLabels = new JLabel[5];
        }

        for (int i = 0; i < positionsLabels.length; i++) {
            positionsLabels[i] = new JLabel("  " + Integer.toString(i + 1));
            positionsLabels[i].setFont(new Font("Arial", Font.PLAIN, 20));
            positionsLabels[i].setHorizontalAlignment(JLabel.CENTER);
            positionsLabels[i].setForeground(Color.BLACK);
            positionsLabels[i].setAlignmentX(CENTER_ALIGNMENT);

            leftPanel.add(positionsLabels[i]);
            
            spaceLabel[i] = new JLabel("");
            spaceLabel[i].setFont(new Font("Arial", Font.PLAIN, 20));
            spaceLabel[i].setHorizontalAlignment(JLabel.CENTER);
            spaceLabel[i].setForeground(Color.BLACK);
            spaceLabel[i].setAlignmentX(CENTER_ALIGNMENT);
            
            leftPanel.add(spaceLabel[i]);

            namesLabels[i] = new JLabel(names.get(i));
            namesLabels[i].setFont(new Font("Arial", Font.PLAIN, 20));
            namesLabels[i].setHorizontalAlignment(JLabel.CENTER);
            namesLabels[i].setForeground(Color.BLACK);
            namesLabels[i].setAlignmentX(CENTER_ALIGNMENT);

            centralPanel.add(namesLabels[i]);

            pointsLabels[i] = new JLabel(points.get(i) + " pts");
            pointsLabels[i].setFont(new Font("Arial", Font.PLAIN, 20));
            pointsLabels[i].setHorizontalAlignment(JLabel.CENTER);
            pointsLabels[i].setForeground(new Color(0,193,108));
            pointsLabels[i].setAlignmentX(CENTER_ALIGNMENT);

            centralPanel.add(pointsLabels[i]);
        }

        this.setIconImage(mineIcon.getImage());
        this.setLayout(new BorderLayout());
        this.add(centralPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.setSize(700, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

}