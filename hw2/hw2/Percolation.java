package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[] Array;
    private WeightedQuickUnionUF uf;
    private int opensiteNum;

    //create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        n = N;
        Array = new boolean[n*n];
        for (boolean i : Array) {
            i = false;
        }
        uf = new WeightedQuickUnionUF(n * n);
        opensiteNum = 0;
    }

    private boolean outRange(int a) {
        return (a < 0) || (a >= n);
    }

    private int xyTo1D(int row, int col) {
        if (outRange(row) || outRange(col)) {
            throw new IndexOutOfBoundsException();
        }
        return col + row * n;
    }

    //open the site (row, col) if it is not open already
    public void open(int row, int col) {
        int code = xyTo1D(row, col);
        Array[code] = true;
        Check4(code, code - 1);
        Check4(code, code + 1);
        Check4(code, code - n);
        Check4(code, code + n);
        opensiteNum = opensiteNum + 1;
    }

    //check is the neighbor open ,and if yes,connect them 2
    private void Check4(int a ,int neighbor) {
        if ((neighbor < 0)||(neighbor >= n * n)) {
            return;
        }
        if (Array[neighbor]) {
            uf.union(a, neighbor);
        }
    }

    //is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return Array[xyTo1D(row, col)];
    }

    public boolean isOpen(int a) {
        return Array[a];
    }

    //is the site (row, col) full?
    public boolean isFull(int row, int col) { //复杂度N 判断是否和任意一个一层open块 connected
        return isFull(xyTo1D(row, col));
    }

    private boolean isFull(int code) {
        if (!isOpen(code)) {
            return false;
        }
        for (int i = 0; i < n; i += 1) {
            if (uf.connected(code, i)) {
                return true;
            }
        }
        return false;
    }

    //number of open sites
    public int numberOfOpenSites() {
        return opensiteNum;
    }

    //does the system percolate?
    public boolean percolates() {
        for (int i = xyTo1D(n - 1, 0); i < n * n; i += 1) {
            if (isFull(i)) {
                return true;
            }
        }
        return false;
    }

    //use for unit testing (not required)
    public static void main(String args[]) {
        Percolation a = new Percolation(100);
        a.open(0,0);
        a.open(1,0);
        System.out.println(a.percolates());
        System.out.println(a.isFull(0,3));

    }
}
