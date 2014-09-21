/**
 * Created by Cee on 9/20/14.
 */

public class Percolation {
//    public Percolation(int N)                // create N-by-N grid, with all sites blocked
//    public void open(int i, int j)           // open site (row i, column j) if it is not already
//    public boolean isOpen(int i, int j)      // is site (row i, column j) open?
//    public boolean isFull(int i, int j)      // is site (row i, column j) full?
//    public boolean percolates()              // does the system percolate?
//    public static void main(String[] args)   // test client, optional

    private boolean[][] boolMap;     //true for blocked, false for not blocked
    private WeightedQuickUnionUF map;
    private int N;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        boolMap = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolMap[i][j] = false;
            }
        }
        map = new WeightedQuickUnionUF(N * N);
    }

    public void open(int i, int j) {
        if (outOfBounds(i, j)) throw new IndexOutOfBoundsException();
        int mapI = i - 1;
        int mapJ = j - 1;
        boolMap[mapI][mapJ] = true;
        int index = mapI * N + mapJ;
        if (mapI != 0) {
            if (boolMap[mapI - 1][mapJ]) {
                map.union(index, index - N);
            }
        }
        if (mapJ != 0) {
            if (boolMap[mapI][mapJ - 1]) {
                map.union(index, index - 1);
            }
        }
        if (mapI != N - 1) {
            if (boolMap[mapI + 1][mapJ]) {
                map.union(index, index + N);
            }
        }
        if (mapJ != N - 1) {
            if (boolMap[mapI][mapJ + 1]) {
                map.union(index, index + 1);
            }
        }
    }

    public boolean isOpen(int i, int j) {
        if (outOfBounds(i, j)) throw new IndexOutOfBoundsException();
        return boolMap[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        if (outOfBounds(i, j)) throw new IndexOutOfBoundsException();
        int index = (i - 1) * N + j - 1;
        for (int k = 0; k < N; k++) {
            if (isOpen(1, k + 1)) {
                if (map.connected(k, index)) return true;
            }
        }
        return false;
    }

    public boolean percolates() {
        for (int j = 0; j < N; j++) {
            if (isFull(N, j + 1)) return true;
        }
        return false;
    }

    private boolean outOfBounds(int i, int j) {
        return (i < 1 || j < 1 || i > N || j > N);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(2);
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(2, 1);
        System.out.println(percolation.isFull(1, 1));
    }
}
