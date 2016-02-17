//import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean[][] grid;
  private int N;
  private WeightedQuickUnionUF wqu;
  private WeightedQuickUnionUF wquWithVirtual;
  
  // create N-by-N grid, with all sites blocked
  public Percolation(int N) { 
    if (N <= 0) throw new IllegalArgumentException("N should be grater than 0");
    grid = new boolean[N][N];
    this.N = N;
    // one virtual sites at the start
    wqu = new WeightedQuickUnionUF(N*N + 1);
    // two virtual sites at the start and at the end
    wquWithVirtual = new WeightedQuickUnionUF(N*N + 2);

    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++) {
      grid[i][j] = false; 
    }
  }
  
  // open site (row i, column j) if it is not open already
  public void open(int i, int j) { 
    checkValidIndex(i, j);
    grid[i-1][j-1] = true;
    if (i == 1) {
      wqu.union(0, xyTo1D(i-1, j-1));
      wquWithVirtual.union(0, xyTo1D(i-1, j-1));
    }
    if (i == N) {
      wquWithVirtual.union(N*N + 1, xyTo1D(i-1, j-1));
    }
    
    if (i-2 >= 0 && isOpen(i-1, j)) {
      wqu.union(xyTo1D(i-1, j-1), xyTo1D(i-2, j-1));
      wquWithVirtual.union(xyTo1D(i-1, j-1), xyTo1D(i-2, j-1));
    }
    
    if (i < N && isOpen(i+1, j)) {
      wqu.union(xyTo1D(i-1, j-1), xyTo1D(i, j-1));
      wquWithVirtual.union(xyTo1D(i-1, j-1), xyTo1D(i, j-1));
      //if (i+1 == N && isFull(i+1, j)) isPercolates = true;
    }
    
    if (j-2 >= 0 && isOpen(i, j-1)) {
      wqu.union(xyTo1D(i-1, j-1), xyTo1D(i-1, j-2));
      wquWithVirtual.union(xyTo1D(i-1, j-1), xyTo1D(i-1, j-2));
    }
    
    if (j < N && isOpen(i, j+1)) {
      wqu.union(xyTo1D(i-1, j-1), xyTo1D(i-1, j));
      wquWithVirtual.union(xyTo1D(i-1, j-1), xyTo1D(i-1, j));
    }
  }
  
  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) { 
    checkValidIndex(i, j);
    return grid[i-1][j-1];
  }
  
  // is site (row i, column j) full?
  public boolean isFull(int i, int j) {
    checkValidIndex(i, j);
    //if (isOpen(i, j) && wquWithVirtual.connected(0, xyTo1D(i-1, j-1)))
    if (isOpen(i, j) && wqu.connected(0, xyTo1D(i-1, j-1)))
      return true;
    return false;
  }
  
  // does the system percolate?
  public boolean percolates() {
    //return isPercolates;
    return wquWithVirtual.connected(0, N*N + 1);
  }
  
  private void checkValidIndex(int i, int j) {
    if (i <= 0 || i > N) 
      throw new IndexOutOfBoundsException("row index i -" + i + "- out of bounds");
    if (j <= 0 || j > N) 
      throw new IndexOutOfBoundsException("row index j -" + j + "- out of bounds");
  }
  
  private int xyTo1D(int i, int j) {
    return i*N + j + 1;
  }
  
  public static void main(String[] args) {  // test client (optional)
//    Percolation perc = new Percolation(10);
//    int i = 10, j = 1;
//    perc.open(i, j);
//    if(perc.isOpen(i ,j))
//      StdOut.println(i + "," + j + " is open ");
//    else
//      StdOut.println(i + "," + j + " is NOT open ");
  }
  
}