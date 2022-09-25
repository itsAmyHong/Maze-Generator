import java.util.*;

// My implementation of maze solving
public class MazeSolver
{
    private Maze maze;
    private String pathString = "";
    
    public MazeSolver(Maze m){
        maze = m;
    }
    
    public void generatePath(){
        Stack<Character> path = new Stack<Character>();
        Stack<Cell> cellPath = new Stack<Cell>();
        Cell current = maze.getCellAt(0,0);
        path.push('\0');
        cellPath.push(current);
        
        while(current.x != 49 || current.y != 49){
            boolean moved = false;
            current.visit();
            String adj = getAdjacent(current);
            while(adj.equals("")){
                path.pop();
                cellPath.pop();
                current = cellPath.peek();
                adj = getAdjacent(current);
            }
            for(int i=0; i<adj.length(); i++){
                char direction = adj.charAt(i);
                Cell temp = maze.getCell(direction, current.x, current.y);
                if(!temp.visited){
                    current.visit();
                    current = maze.getCell(direction, current.x, current.y);
                    path.push(direction);
                    cellPath.push(current);
                    moved = true;
                    break;
                }
            }
        }
        
        System.out.println("The SEN form is: ");
        printPath(path, 1);
        colorPath(cellPath);
    }
    
    public String getAdjacent(Cell c){
        String adj = "";
        if(!c.north && !maze.getCell('n', c.x, c.y).visited){
            adj += 'n';
        }
        if(!c.east && !maze.getCell('e', c.x, c.y).visited){
            adj += 'e';
        }
        if(!c.south && !maze.getCell('s', c.x, c.y).visited){
            adj += 's';
        }
        if(!c.west && !maze.getCell('w', c.x, c.y).visited){
            adj += 'w';
        }
        return adj;
    }
    
    public void colorPath(Stack<Cell> s){
        if (s.isEmpty()){
            return;
        }
        Cell c = s.peek();
        maze.getCellAt(c.y, c.x).colored = true;
        s.pop();
        colorPath(s);
    }
    
    public static void printPath(Stack<Character> s, int count)
    {
        if (s.isEmpty()){
            return;
        }
        char x = s.peek();
        s.pop();
        x = Character.toUpperCase(x);
        printPath(s, count+1);
        System.out.print(x);
        if(count%20 == 0){
            System.out.println();
        }
        s.push(x);
    }
}
