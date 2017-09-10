package org.test.ex1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation1 {

    private final WeightedQuickUnionUF grid;
    private boolean[] stats;
    private final int n; 

    // create n-by-n grid, with all sites blocked
    public Percolation1(int n) {
        int siteCount = n*n;
        if (n <= 0)
            throw new IllegalArgumentException("argument is illagel");
        this.n = n;
        stats = new boolean[siteCount+2];
        for (int i = 1; i <= siteCount; i++)
                stats[i] = false;
        stats[0] = true;
        stats[siteCount+1] = true;
        grid = new WeightedQuickUnionUF(siteCount+1);
        for (int i = 1; i <= n; i++)
            grid.union(0, i);
    }

    //transform
    private int xyToIndex(int row, int col) {
        if (row > n || row < 0)
            throw new IllegalArgumentException("The row number is wrong!");
        if (col > n || row < 0)
            throw new IllegalArgumentException("The col number is wrong!");
        return (row - 1) * n + col;
    }
    
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = xyToIndex(row, col);
        stats[index] = true;
        if (row > 1 && isOpen(row - 1, col))
            grid.union(index, xyToIndex(row-1,col));
        if (row < n - 1 && isOpen(row + 1, col))
            grid.union(index, xyToIndex(row+1,col));
        if (col > 1 && isOpen(row, col - 1))
            grid.union(index, xyToIndex(row,col-1));
        if (col < n - 1 && isOpen(row, col + 1))
            grid.union(index, xyToIndex(row,col+1));
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return stats[xyToIndex(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return grid.connected(0, xyToIndex(row, col));
    }
    
    public int numberOfOpenSites() {
        int num = 0;
        for (int i = 1; i <= n*n; i++)
            if (stats[i] == true)
                  num++;
        return num;
    }

    // does the system percolate?
    //   TODO
    public boolean percolates() {
        for (int i = 0; i < n; i++)
            if (grid.connected(0, xyToIndex(n,i)))
                return true;
        return false;
    }
}
