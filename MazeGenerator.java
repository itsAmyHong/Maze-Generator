
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Random;
import java.util.Scanner;
import java.awt.BorderLayout;;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;

public final class MazeGenerator{
    // Do not change!
    private static int SIZE_X = 780;
    private static int SIZE_Y = 810;
    private static int CELL_SIZE = 15;
    private static int PADDING = 0;
    private static int SIZE = 50;
    
    public static void main(String[] args) {        
        // Create and generate maze.
        Maze maze = new Maze(SIZE, SIZE);
        maze.generateMaze();
        
        MazeSolver s = new MazeSolver(maze);
        s.generatePath();
        
        // Draw maze on new window
        SwingUtilities.invokeLater(() -> {
            JPanel panel = new Display(maze, CELL_SIZE, PADDING);
            panel.setBackground(Color.WHITE);
            
            JFrame frame = new JFrame("Maze Generator");
            frame.setSize(SIZE_X, SIZE_Y);
            frame.setBackground(Color.WHITE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

}