/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class PercolationStats {
    double[] thresholds;
    int n;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.thresholds = new double[trials];
        this.n = n;
        // repeat for number of trials
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            int tries = 0;
            // until percolation, open random site
            while (!test.percolates()) {
                int randRow = StdRandom.uniform(n) + 1;
                int randCol = StdRandom.uniform(n) + 1;
                test.open(randRow, randCol);
                tries++;
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {

    }

    // sample standard deviation of percolation threshold
    public double stddev() {

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

    }

    // test client (described below)
    public static void main(String[] args) {

    }
}
