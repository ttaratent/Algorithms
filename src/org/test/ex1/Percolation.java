package org.test.ex1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF grid, auxGrid;
    private final boolean[] state;
    private final int n;

    // create N-by-N grid, with all sites blocked
    public Percolation(int n) {

        int siteCount = n * n;
        this.n = n;

        // index 0 and N^2+1 are reserved for virtual top and bottom sites
        grid = new WeightedQuickUnionUF(siteCount + 2);
        auxGrid = new WeightedQuickUnionUF(siteCount + 1);
        state = new boolean[siteCount + 2];

        // Initialize all sites to be blocked.
        for (int i = 1; i <= siteCount; i++)
            state[i] = false;
        // Initialize virtual top and bottom site with open state
        state[0] = true;
        state[siteCount + 1] = true;
    }

    // return array index of given row i and column j
    private int xyToIndex(int i, int j) {
        // Attention: i and j are of range 1 ~ N, NOT 0 ~ N-1.
        // Throw IndexOutOfBoundsException if i or j is not valid
        if (i <= 0 || i > n)
            throw new IndexOutOfBoundsException("row i out of bound");
        if (j <= 0 || j > n)
            throw new IndexOutOfBoundsException("column j out of bound");

        return (i - 1) * n + j;
    }

    private boolean isTopSite(int index) {
        return index <= n;
    }

    private boolean isBottomSite(int index) {
        return index >= (n - 1) * n + 1;
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        // All input sites are blocked at first.
        // Check the state of site before invoking this method.
        int idx = xyToIndex(i, j);
        state[idx] = true;

        // Traverse surrounding sites, connect all open ones.
        // Make sure we do not index sites out of bounds.
        if (i != 1 && isOpen(i - 1, j)) {
            grid.union(idx, xyToIndex(i - 1, j));
            auxGrid.union(idx, xyToIndex(i - 1, j));
        }
        if (i != n && isOpen(i + 1, j)) {
            grid.union(idx, xyToIndex(i + 1, j));
            auxGrid.union(idx, xyToIndex(i + 1, j));
        }
        if (j != 1 && isOpen(i, j - 1)) {
            grid.union(idx, xyToIndex(i, j - 1));
            auxGrid.union(idx, xyToIndex(i, j - 1));
        }
        if (j != n && isOpen(i, j + 1)) {
            grid.union(idx, xyToIndex(i, j + 1));
            auxGrid.union(idx, xyToIndex(i, j + 1));
        }
        // if site is on top or bottom, connect to corresponding virtual site.
        if (isTopSite(idx)) {
            grid.union(0, idx);
            auxGrid.union(0, idx);
        }
        if (isBottomSite(idx))
            grid.union(state.length - 1, idx);
    }

    public int numberOfOpenSites() {
        int num = 0;
        for (int i = 1; i <= n*n; i++)
            if (state[i] == true)
                  num++;
        return num;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        int idx = xyToIndex(i, j);
        return state[idx];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        // Check if this site is connected to virtual top site
        int idx = xyToIndex(i, j);
        return grid.connected(0, idx) && auxGrid.connected(0, idx);
    }

    // does the system percolate?
    public boolean percolates() {
        // Check whether virtual top and bottom sites are connected
        return grid.connected(0, state.length - 1);
    }
}
