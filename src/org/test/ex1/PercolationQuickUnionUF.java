package org.test.ex1;

import edu.princeton.cs.algs4.QuickFindUF;

public class PercolationQuickUnionUF {
	private QuickFindUF grid;
	private boolean[][] id;
	private int n;

	// create n-by-n grid, with all sites blocked
	public PercolationQuickUnionUF(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("n is illegal");
		// this.num = 0;
		this.n = n;
		this.id = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				id[i][j] = false;
		}
		// the last union the top node
		grid = new QuickFindUF(n * n + 1);
		for (int i = 0; i < n; i++)
			// q to p
			grid.union(i, n * n);
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row > n || row < 0 || col > n || col < 0)
			throw new IllegalArgumentException("The row or col is illagel!!");
		id[row - 1][col - 1] = true;
		int index = (row - 1) * n + col - 1;
		if (row > 1 && isOpen(row - 1, col))
			grid.union(index, index - n);
		if (row < n - 1 && isOpen(row + 1, col))
			grid.union(index, index + n);
		if (col > 1 && isOpen(row, col - 1))
			grid.union(index, index - 1);
		if (col < n - 1 && isOpen(row, col + 1))
			grid.union(index, index + 1);
	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		return id[row - 1][col - 1];
	}

	// is site (row, col) full?
	// check the root is top.
	public boolean isFull(int row, int col) {
		return grid.find((row - 1) * n + col - 2) == grid.find(n * n);
	}

	// number of open sites
	public int numberOfOpenSites() {
		return grid.count();
	}

	// does the system percolate?
	public boolean percolates() {
		for (int i = 0; i < n; i++) {
			if (isFull(n, i+1)) {
				return true;
			}
		}
		return false;
	}

	// test client (optional)
	public static void main(String[] args) {

	}

}
