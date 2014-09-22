/**
 * Created by Cee on 9/20/14.
 */

public class PercolationStats {

    private double pSum = 0.0;
    private double pSquareSum = 0.0;
    private double mean = 0.0;
    private double stddev = 0.0;
    private double upper = 0.0;
    private double lower = 0.0;

    private int T;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        for (int count = 0; count < T; count++) {
            Percolation percolation = new Percolation(N);
            int index = 0;
            do {
                int i = (int) (N * Math.random()) + 1;
                int j = (int) (N * Math.random()) + 1;
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    index++;
                }
            } while(!percolation.percolates());
            double p = (double) (index) / (N * N);
            pSum += p;
            pSquareSum += p * p;
        }

        mean = mean();
        stddev = stddev();
        this.lower = confidenceLo();
        this.upper = confidenceHi();
    }

    public double mean() {
        return pSum / T;
    }

    public double stddev() {
        return Math.sqrt((pSquareSum - T * mean * mean) / (T - 1));
    }

    public double confidenceLo()  {
        return mean - 1.96 * stddev / Math.sqrt(T);
    }

    public double confidenceHi() {
        return mean + 1.96 * stddev / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
//        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("mean                    = " + percolationStats.mean);
        System.out.println("stddev                  = " + percolationStats.stddev);
        System.out.println("95% confidence interval = " + percolationStats.lower + ", " + percolationStats.upper);
    }
}
