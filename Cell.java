import java.awt.Graphics;
import java.util.List;

public class Cell {
    // Booleans of wall existance 
    boolean north; 
    boolean east; 
    boolean south; 
    boolean west; 
    
    // Coordinates of cell
    public int x;
    public int y;
    
    // Booleans for finding solution
    public boolean visited;
    public boolean colored;
    
    // Constructor
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.north = true;
        this.east = true;
        this.south = true;
        this.west = true;
        
        this.visited = false;
        this.colored = false;
    }
    
    // Draws existing walls of a cell
    public void drawCell(Graphics g, int x, int y, int size){
        int x2 = x+size;
        int y2 = y+size;
        
        if(north){
            g.drawLine(x, y, x2, y);
        }
        if(east){
            g.drawLine(x2, y, x2, y2);
        }
        if(south){
            g.drawLine(x2, y2, x, y2);
        }
        if(west){
            g.drawLine(x, y2, x, y);
        }
    }
    
    public void visit(){
        visited = true;
    }
}
