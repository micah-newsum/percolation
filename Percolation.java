public class Percolation {
    boolean[][] sites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException("n must be greater than 0");
        }

        sites = new boolean[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                sites[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row == 0 || row > sites.length) {
            throw new IllegalArgumentException("Invalid row: must be between 1 & "+ sites.length);
        } else if (col == 0 || col > sites.length) {
            throw new IllegalArgumentException("Invalid column: must be between 1 & " + sites.length);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row == 0 || row > sites.length) {
            throw new IllegalArgumentException("Invalid row: must be between 1 & "+ sites.length);
        } else if (col == 0 || col > sites.length) {
            throw new IllegalArgumentException("Invalid column: must be between 1 & " + sites.length);
        }
        
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row == 0 || row > sites.length) {
            throw new IllegalArgumentException("Invalid row: must be between 1 & "+ sites.length);
        } else if (col == 0 || col > sites.length) {
            throw new IllegalArgumentException("Invalid column: must be between 1 & " + sites.length);
        }
        
        
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return -1;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    public static void main(String args[]){
        Percolation percolation = new Percolation(10);
        percolation.open(10, 10);
    }
}