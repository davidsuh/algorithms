/* *****************************************************************************
 *  Name: David Suh
 *  Date: 10/18/2018
 *  Description: First Assignment
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF grid;
    int[] open;
    int n;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n cannot be negative");
        }
        this.n = n;
        int size = n * n;
        this.grid = new WeightedQuickUnionUF(size);
        this.open = new int[size];

        // connect top row to site n
        // connect bottom row to site n + 1
        for (int i = 0; i < n; i++) {
            this.grid.union(i, size);
            this.grid.union(size - i - 1, size + 1);
        }
    }

    private int rowColtoN(int row, int col) {
        return 3 * (row - 1) + (col - 1);
    }

    // if provided (row, col) is out of the grid bounds, return false
    private boolean isValidSite(int row, int col) {
        return (row > 0 && row <= this.n) && (col > 0 && col <= this.n);
    }

    // check neighboring sites, union if appropriate
    private void joinNeighbors(int row, int col, int site) {
        // top
        if (isValidSite(row - 1, col)) {
            if (isOpen(row - 1, col)) {
                this.grid.union(rowColtoN(row - 1, col), site);
            }
        }

        // right
        if (isValidSite(row, col + 1)) {
            if (isOpen(row, col + 1)) {
                this.grid.union(rowColtoN(row, col + 1), site);
            }
        }

        // bottom
        if (isValidSite(row + 1, col)) {
            if (isOpen(row + 1, col)) {
                this.grid.union(rowColtoN(row + 1, col), site);
            }
        }

        // left
        if (isValidSite(row, col - 1)) {
            if (isOpen(row, col - 1)) {
                this.grid.union(rowColtoN(row, col - 1), site);
            }
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValidSite(row, col)) {
            throw new java.lang.IllegalArgumentException("row and col out of bounds");
        }
        int site = rowColtoN(row, col);
        open[site] = 1;
        joinNeighbors(row, col, site);
    }

    // // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isValidSite(row, col)) {
            throw new java.lang.IllegalArgumentException("row and col out of bounds");
        }
        int site = rowColtoN(row, col);
        return open[site] == 1;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValidSite(row, col)) {
            throw new java.lang.IllegalArgumentException("row and col out of bounds");
        }
        int site = rowColtoN(row, col);
        return this.grid.connected(site, this.n);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int sum = 0;
        for (int i = 0; i < open.length; i++) {
            sum += this.open[i];
        }
        return sum;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.grid.connected(this.n, this.n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.print("hello world");
    }
}
