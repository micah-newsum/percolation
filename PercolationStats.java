import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    double simulations[];
    int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        
        simulations = new double[trials];
        this.trials = trials; 

        double totalSites = Math.pow(n, 2);

        for (int i = 0; i < trials; i++){
            Percolation percolation = new Percolation(n);
            
            while (!percolation.percolates()){
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }
            simulations[i] = (double) percolation.numberOfOpenSites() / totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(simulations);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(simulations);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args){
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println(stats.confidenceLo());
        System.out.println(stats.confidenceHi());
    }
}
