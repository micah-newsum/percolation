import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return 0.0;
    }

    // test client
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        double[] simulations = new double[T];

        double totalSites = Math.pow(n, 2);

        for (int i = 0; i < T; i++){
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            simulations[i] = ((double) percolation.numberOfOpenSites() / totalSites);
        }
        System.out.println(simulations);
    }
}
