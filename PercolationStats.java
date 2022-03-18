import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double simulations[];
    private final int trials;
    private final static double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        simulations = new double[trials];
        this.trials = trials;

        double totalSites = Math.pow(n, 2);

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }
            simulations[i] = (double) percolation.numberOfOpenSites() / totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(simulations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(simulations);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.printf("mean \t\t\t = %.16f\n", stats.mean());
        System.out.printf("stddev \t\t\t = %.16f\n", stats.stddev());
        System.out.printf("95%% confidence interval  = [%.16f, %.16f]\n", stats.confidenceLo(), stats.confidenceHi());
    }
}
