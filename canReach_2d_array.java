1. 给一个2D board, agent和client的位置，问agent能不能到client的位置. agent能上下左右移动，但是只有碰到墙壁（1）或者array的边缘才能还方向
例子：
|0|0|1|0|A|
|0|0|0|0|0|
|0|0|0|1|0|
|1|1|0|1|1|
|0|0|0|0|C|
agent: [0, 4]
client: [4, 4]
return true

  
  A value of cell 1 means Source.
A value of cell 2 means Destination.
A value of cell 3 means Blank cell.
A value of cell 0 means Wall.



public class FindPath {

    public boolean isPossible(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m]; // to maintain if a cell has previously visited or not

        boolean output = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {  // if visited don't dfs
                    output = dfs(i, j, grid, visited);
                    break;
                }
            }
        }
        return output;
    }

    public boolean dfs(int row, int col, int[][] grid, boolean[][] visited) {
        // if row or col is out of bounds or was cell previously visited 
       //  tell 'dfs you are drunk! go home!'
        if (row<0 || col<0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0 || visited[row][col])  
            return false;

        visited[row][col] = true;
  
        if(grid[row][col]==2) {
            return true;

        return  dfs(row - 1, col, grid, visited)    // assuming that any other 1,3 and any other is traversable. 
                    || dfs(row + 1, col, grid, visited)
                    || dfs(row, col-1, grid, visited)
                    || dfs(row, col+1, grid, visited);
    }

    public void run() {
        int[][] grid = {{3, 0, 3, 0, 0},
                {3, 0, 0, 0, 3},
                {3, 3, 3, 3, 3},
                {0, 2, 0, 0, 0},
                {3, 0, 0, 1, 3}};
        System.out.println(this.isPossible(grid));
    }

    public static void main(String[] args) {
        FindPath path = new FindPath();
        path.run();
    }
}
