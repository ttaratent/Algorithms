package org.test.ex1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] threshold;

    // perform trials independent experiment on an n-by-n grid
    public PercolationStats(int n, int trials) {
        int openCount, row, col;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("The arguments are wrong!!");
        threshold = new double[trials];
        openCount = 0;
        for (int i = 0; i < trials; i++) {
            Percolation1 p1 = new Percolation1(n);
            do {
                row = StdRandom.uniform(1, n + 1 );
                col = StdRandom.uniform(1, n + 1 );
                if (p1.isOpen(row, col))
                    continue;
                p1.open(row, col);
                openCount++;
            } while (!p1.percolates());
            threshold[i] = (double) openCount / (n * n);
            openCount = 0;
        }
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    private double halfInterval() {
        return 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - halfInterval();
    }

    // hing endpoint of 95% configence interval
    public double confidenceHi() {
        return mean() + halfInterval();
    }

    // test client (described below)
    public static void main(String[] args) {
//        PercolationStats pls = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
    PercolationStats pls = new PercolationStats(2, 5);
        System.out.printf("mean                     = %f\n", pls.mean());
        System.out.printf("stddev                   = %f\n", pls.stddev());
        System.out.printf("95%% confidence Interval  = %f, %f\n", pls.confidenceLo(), pls.confidenceHi());
    }

}
