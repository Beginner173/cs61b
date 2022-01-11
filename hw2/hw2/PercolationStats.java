package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] thresholdArray;
    private int times;
    private int n;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0||T <= 0) {
            throw new IllegalArgumentException();
        }
        thresholdArray = new double[T];
        times = T;
        n = N;
        for (int i = 0;i < T; i += 1) {
        Percolation aa = pf.make(N);
        thresholdArray[i] = threshold(aa);
        }
    }


    private void RandomUnit(Percolation aa) {
        int row = StdRandom.uniform(n);
        int col = StdRandom.uniform(n);
        while (aa.isOpen(row, col)) {
            row = StdRandom.uniform(n);
            col = StdRandom.uniform(n);
        }
        aa.open(row, col);
    }

    private double threshold(Percolation aa) {
        int i = 0;
        while (!aa.percolates()) {
            RandomUnit(aa);
            i = i + 1;
        }
        return (double) i/(n * n);
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholdArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholdArray);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }

}
