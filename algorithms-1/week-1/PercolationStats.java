import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
  //private Percolation perc;
  private int T;
  private double[] arrThreshold;
  private double mean;
  private double stddev;
  
  // perform T independent experiments on an N-by-N grid
  public PercolationStats(int N, int T) { 
    if (N <= 0) throw new IllegalArgumentException("N should be grater than 0");
    if (T <= 0) throw new IllegalArgumentException("T should be grater than 0");
    
    this.T = T;
    arrThreshold = new double[T];
    mean = 0.0;
    stddev = 0.0;
    int noOfOpensites = 0;
    for (int i = 0; i < T; i++) {
      Percolation perc = new Percolation(N);
      while (!perc.percolates()) {
        int row = StdRandom.uniform(N);
        int col = StdRandom.uniform(N);
        // 1 is added to row and col, since our array is zero based
        if (!perc.isOpen(row+1, col+1)) {
          perc.open(row+1, col+1);
          ++noOfOpensites;
        }
      }
      arrThreshold[i] =  noOfOpensites/(double) (N*N);
      noOfOpensites = 0;
    }
  }
  
  // sample mean of percolation threshold
  public double mean() {
    mean = StdStats.mean(arrThreshold);
    return mean;
  }
  
  // sample standard deviation of percolation threshold
  public double stddev() {
    if (T == 1)
      stddev = Double.NaN;
    else
      stddev = StdStats.stddev(arrThreshold);
    return stddev;
  }
  
  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return (mean() - (1.96*stddev())/Math.sqrt(T));
  }
  
  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return (mean() + (1.96*stddev())/Math.sqrt(T)); 
  }
  
  // test client (described below)
  public static void main(String[] args) { 
    int N = 0, T = 0;
    if (args.length > 1) {
      N = Integer.parseInt(args[0]);
      T = Integer.parseInt(args[1]);
    }
    // confidenceHi() confidenceLo() mean() stddev() confidenceHi() 
    PercolationStats percolationStats = new PercolationStats(N, T);
    StdOut.println("mean                    = " + percolationStats.mean());
    StdOut.println("stddev                  = " + percolationStats.stddev());
    StdOut.println("95% confidence interval = " + percolationStats.confidenceLo()
                     + ", " + percolationStats.confidenceHi());
    
  }
}