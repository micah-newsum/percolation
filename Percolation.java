import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private boolean[][] sites;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private int numberOfOpenSites;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        int totalSites = (int) Math.pow(n, 2) + 2;
        virtualTopSite = totalSites - 2;
        virtualBottomSite = totalSites - 1;
        uf = new WeightedQuickUnionUF(totalSites);
        sites = new boolean[n][n];
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
            uf.union(p, virtualTopSite);
        } else {
            int top = row - 1;
            if (isValidSite(top, col) && isOpen(top, col)) {
                int q = getSite(top, col);
                uf.union(p, q);
            }
        }

        // union bottom site
        if (row == n) {
            uf.union(p, virtualBottomSite);
        } else {
            int bottom = row + 1;
            if (isValidSite(bottom, col) && isOpen(bottom, col)) {
                int q = getSite(bottom, col);
                uf.union(p, q);
            }
        }

        sites[row - 1][col - 1] = true;
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
        return sites[row - 1][col - 1];
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
        return isOpen(row, col) && uf.find(getSite(row, col)) == uf.find(virtualTopSite);
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

        return uf.find(virtualTopSite) == uf.find(virtualBottomSite);
    }

    public static void main(String[] args) {
        // Percolation percolation = new Percolation(3);

        // Change access to public to test
        // System.out.println(percolation.getSite(1, 1)); // expect 0
        // System.out.println(percolation.getSite(2, 2)); // expect 4
        // System.out.println(percolation.getSite(3, 3)); // expect 8

        // test opening outside boundaries
        // try {
        // percolation.isOpen(0, 0);
        // } catch (IllegalArgumentException e) {
        // System.out.println(e);
        // }

        // try {
        // percolation.isOpen(4, 4);
        // } catch (IllegalArgumentException e) {
        // System.out.println(e);
        // }

        // percolation.open(1, 1);
        // percolation.open(2, 1);
        // percolation.open(3, 1);
        // System.out.println("System percolates: "+percolation.percolates()); // expect
        // true
        // percolation.print();

        // // test full site
        // System.out.println("Site is full: "+percolation.isFull(2, 2)); // expect true
        // System.out.println("Site is open: "+percolation.isOpen(2, 1)); // expect true

        // // open remaining sites and check if full
        // percolation.open(2, 1);
        // percolation.open(2, 2);
        // percolation.open(2, 3);
        // percolation.open(3, 1);
        // percolation.open(3, 2);
        // percolation.open(3, 3);
        // System.out.printf("%d out of 9 sites
        // open\n",percolation.numberOfOpenSites());
    }
}