package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    public double[] thresholdArray;
    public int times;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0||T <= 0) {
            throw new IllegalArgumentException();
        }
        thresholdArray = new double[T];
        times = T;
        for (int i = 0;i < T; i += 1) {
        Percolation aa = pf.make(N);
        thresholdArray[i] = threshold(aa);
        }
    }


    private void RandomUnit(Percolation aa) {
        int row = StdRandom.uniform(aa.n);
        int col = StdRandom.uniform(aa.n);
        while (aa.isOpen(row, col)) {
            row = StdRandom.uniform(aa.n);
            col = StdRandom.uniform(aa.n);
        }
        aa.open(row, col);
    }

    private double threshold(Percolation aa) {
        int i = 0;
        while (!aa.percolates()) {
            RandomUnit(aa);
            i = i + 1;
        }
        return (double) i/(aa.n * aa.n);
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

    public static void main(String args[]) {
        PercolationFactory a = new PercolationFactory();
        PercolationStats i = new PercolationStats(20,1000,a);
        System.out.println(i.mean());
        System.out.println(i.stddev());
        System.out.println(i.confidenceHigh());
        System.out.println(i.confidenceLow());
    }
}
