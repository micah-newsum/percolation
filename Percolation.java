import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private byte[][] sites;
    private int numberOfOpenSites;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        int totalSites = (int) Math.pow(n, 2) + 2;
        uf = new WeightedQuickUnionUF(totalSites);
        sites = new byte[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int p = getSite(row, col);

        // union left site
        int left = col - 1;
        if (isValidSite(row, left) && isOpen(row, left)) {
            int q = getSite(row, left);
            uf.union(p, q);
        }

        // union right site
        int right = col + 1;
        if (isValidSite(row, right) && isOpen(row, right)) {
            int q = getSite(row, right);
            uf.union(p, q);
        }

        // union top site
        if (row == 1) {
            // uf.union(p, virtualTopSite);
        } else {
            int top = row - 1;
            if (isValidSite(top, col) && isOpen(top, col)) {
                int q = getSite(top, col);
                uf.union(p, q);
            }
        }

        // union bottom site
        if (row == n) {
            // uf.union(p, virtualBottomSite);
        } else {
            int bottom = row + 1;
            if (isValidSite(bottom, col) && isOpen(bottom, col)) {
                int q = getSite(bottom, col);
                uf.union(p, q);
            }
        }

        // 110 open and full (0000-0110 6) 
        // 101 open and connected to bottom (0000-0101 5)
        // 111 open, full, and connected to bottom (0000-0111 7)
        byte open = 4;
        byte site = sites[row - 1][col - 1];
        sites[row - 1][col - 1] = (byte) (site | open);
        numberOfOpenSites++;
    }

    private int getSite(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private boolean isValidSite(int row, int col) {
        return !(row == 0 || row > n || col == 0 || col > n);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);
        byte open = 4; // 0000-0100 == 4
        return (sites[row - 1][col - 1] & open) > 0;
    }

    // is the site (row, col) full?
    // aka is site connected to any site in the top row?
    /**
     * Need to keep track of full sites:
     * - A site is full if it's open and in the top row, or if it's connected to an
     * open site
     * in the top row.
     * - Two sites are connected if they are both open and share a left, right, top
     * or bottom border
     * - If a site is in the top row, it is connected to the virtual top site.
     * - Therefore, if any site is connected to virtual top site, it is full.
     */
    public boolean isFull(int row, int col) {
        check(row, col);
        return false;
        // return isOpen(row, col) && uf.find(getSite(row, col)) == uf.find(virtualTopSite);
    }

    private void check(int row, int col) {
        if (row <= 0 || row > n) {
            throw new IllegalArgumentException("Invalid row: must be between 1 & " + n);
        } else if (col <= 0 || col > n) {
            throw new IllegalArgumentException("Invalid column: must be between 1 & " + n);
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1 && isOpen(1, 1)) {
            return true;
        }

        // return uf.find(virtualTopSite) == uf.find(virtualBottomSite);
        return false;
    }
}