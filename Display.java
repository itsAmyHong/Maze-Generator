import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Color;

public class Display extends JPanel{
    
    private Maze maze;
    private int cellSize;
    private int padding;
    
    public Display(Maze m, int c, int p){
        maze = m;
        cellSize = c;
        padding = p;
    }

    //Place tiles
    @Override
    public void paintComponent(Graphics g) {
        int x_pos = 10; int y_pos = 10;
        for (int row = 0; row < this.maze.getNumRows(); row++) {
            x_pos = 10;
            for (int col = 0; col < this.maze.getNumCols(); col++) {
                Cell c = maze.getCellAt(row, col);
                g.setColor(Color.BLACK);
                c.drawCell(g, x_pos, y_pos, cellSize);
                if(c.colored){
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.RED);
                    g2d.fillRect(x_pos+7, y_pos+7, 5, 5);
                }
                x_pos += (cellSize + padding);
            }
            y_pos += (cellSize + padding);
        }
    }
}