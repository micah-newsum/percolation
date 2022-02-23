import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean sites[][];
    private int virtualTopSite;
    private int virtualBottomSite;
    private int numberOfOpenSites;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        int totalSites = n^2 + 2;
        virtualTopSite = totalSites - 2;
        virtualBottomSite = totalSites - 1;
        uf = new WeightedQuickUnionUF(totalSites);
        sites = new boolean[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        check(row, col);
        sites[row - 1][col - 1] = true;
        // check if adjacent sites are also open, and if so, connect.
        numberOfOpenSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        check(row, col);
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        check(row, col);
        return sites[row - 1][col - 1];
    }

    private void check(int row, int col){
        if (row == 0 || row > n) {
            throw new IllegalArgumentException("Invalid row: must be between 1 & "+ n);
        } else if (col == 0 || col > n) {
            throw new IllegalArgumentException("Invalid column: must be between 1 & " + n);
        } 
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(virtualTopSite, virtualBottomSite);
    }

    public static void main(String args[]){
        Percolation percolation = new Percolation(3);
        percolation.open(10, 10);
    }
}