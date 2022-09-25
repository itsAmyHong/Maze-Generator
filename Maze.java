import java.util.Random;

public class Maze {
    private Cell[][] maze;
    private Wall[] walls;
    
    // Constructor
    public Maze(int rows, int cols) {
        int numWalls = 2 * rows * cols - rows - cols;

        this.maze = new Cell[rows][cols];
        this.walls = new Wall[numWalls];
        
        init_walls(rows, cols);
    }

    public void generateMaze() {
        Random rand = new Random();
        int rows = this.getNumRows();
        int cols = this.getNumCols();
        int numCells = rows * cols;

        // opens start and end of maze 
        this.maze[0][0].west = false;
        this.maze[rows - 1][cols - 1].east = false;
        
        // create disjoint set for union and find
        Set s = new Set(numCells);
        
        // while subsets exist...
        while (s.hasNoSubsets()) {
            // break random walls
            Wall randWall = this.walls[rand.nextInt(this.walls.length)];

            int row = randWall.getRow();
            int col = randWall.getCol();
            int aCell = row * cols + col;

            if (randWall.getOrientation() == 'h') {
                int bCell = (row + 1) * cols + col;
                if (s.find(aCell) != s.find(bCell)) {
                    this.maze[row][col].south = false;
                    this.maze[row + 1][col].north = false;
                    s.merge(aCell, bCell);
                }
            } else{
                int bCell = row * cols + col + 1;
                if (s.find(aCell) != s.find(bCell)) {
                    this.maze[row][col].east = false;
                    this.maze[row][col + 1].west = false;
                    s.merge(aCell, bCell);
                }
            }
        }
    }
    
    // retrieves neighboring cell from specified direction
    public Cell getCell(char direction, int c, int r){
        int x = c;
        int y = r;
        switch(direction){
            case 'n': y = r-1;
            break;
            case 'e': x = c+1;
            break;
            case 's': y = r+1;
            break;
            case 'w': x = c-1;
            break;
        }
        try{
            return getCellAt(y,x);
        } catch(Exception e){
            return getCellAt(r,c);
        }
    }
    
    // initalizes walls
    public void init_walls(int rows, int cols){
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.maze[i][j] = new Cell(j, i);
                if (i < rows - 1) {
                    this.walls[num++] = new Wall(i, j, 'h');
                }
                if (j < cols - 1) {
                    this.walls[num++] = new Wall(i, j, 'v');
                }
            }
        }
    }
    
    // Getters and Setters
    public Cell getCellAt(int r, int c) {
        return this.maze[r][c];
    }

    public void setCellAt(int r, int c, Cell cell) {
        this.maze[r][c] = cell;
    }

    public int getNumRows() {
        return this.maze.length;
    }

    public int getNumCols() {
        return this.maze[0].length;
    }
    
    public void visit(Cell cell){
        cell.visited = false;
    }

}
