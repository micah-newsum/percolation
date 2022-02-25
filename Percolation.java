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

        // connect all of top row to virtualTopSite
        
        // connect all of bottom row to virtualBottomSite
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        check(row, col);
        sites[row - 1][col - 1] = true;
        int p = getSite(row, col);
        
        // union left site
        int leftRow = row - 1;
        if (isValidSite(leftRow, col) && isOpen(leftRow, col)){
            int q = getSite(leftRow, col);
            uf.union(p, q);
        }

        // union right site
        int rightRow = row + 1;
        if (isValidSite(rightRow, col) && isOpen(rightRow, col)){
            int q = getSite(rightRow, col);
            uf.union(p, q);
        }

        // union top site
        int topCol = col + 1;
        if (isValidSite(row, topCol) && isOpen(row, topCol)){
            int q = getSite(row, topCol);
            uf.union(p, q);
        }

        // union bottom site
        int bottomCol = col - 1;
        if (isValidSite(row, bottomCol) && isOpen(row, col)){
            int q = getSite(row, bottomCol);
            uf.union(p, q);
        }

        numberOfOpenSites++;
    }

    private int getSite(int row, int col){
        return (row - 1) * 3 + (col - 1);
    }

    private boolean isValidSite(int row, int col){
        return !(row == 0 || row > n || col == 0 || col > n);
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
        // System.out.println(getSite(0, 0));
        // Percolation percolation = new Percolation(3);
        // percolation.open(10, 10);
    }
}