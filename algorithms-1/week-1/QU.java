import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QU
{
  private int[] id; // access to component id (site indexed)
  private int count; // number of components
  public QU(int N)
  { // Initialize component id array.
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
  }
  
  public int count()
  { return count; }
  
  public boolean connected(int p, int q)
  { return find(p) == find(q); }
  
  // worst case the array is accessed for 2N - 1 times
  public int find(int p) {
    while(p != id[p])
      p = id[p];
    return p;
  }
  
  public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    // Nothing to do if p and q are already in the same component.
    if (pRoot == qRoot) return;
    // Rename p’s component to q’s name.
    id[pRoot] = qRoot;
    count--;
  }
  
  public static void main(String[] args)
  { // Solve dynamic connectivity problem on StdIn.
    int N = StdIn.readInt(); // Read number of sites.
    QU qu = new QU(N); // Initialize N components.
    while (!StdIn.isEmpty())
    {
      int p = StdIn.readInt();
      int q = StdIn.readInt(); // Read pair to connect.
      if (qu.connected(p, q)) continue; // Ignore if connected.
      qu.union(p, q); // Combine components
      StdOut.println(p + " " + q); // and print connection.
    }
    StdOut.println(qu.count() + " components");
  }
}
