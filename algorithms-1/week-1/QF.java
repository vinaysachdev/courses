import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QF
{
  private int[] id; // access to component id (site indexed)
  private int count; // number of components
  public QF(int N)
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
  
  public int find(int p) { 
    return id[p];
  }
  
  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q);
    // Nothing to do if p and q are already in the same component.
    if (pID == qID) return;
    // Rename p’s component to q’s name.
    for (int i = 0; i < id.length; i++)
      if (id[i] == pID) id[i] = qID;
    count--;
  }
  
  public static void main(String[] args)
  { // Solve dynamic connectivity problem on StdIn.
    int N = StdIn.readInt(); // Read number of sites.
    QF uf = new QF(N); // Initialize N components.
    while (!StdIn.isEmpty())
    {
      int p = StdIn.readInt();
      int q = StdIn.readInt(); // Read pair to connect.
      if (uf.connected(p, q)) continue; // Ignore if connected.
      uf.union(p, q); // Combine components
      StdOut.println(p + " " + q); // and print connection.
    }
    StdOut.println(uf.count() + " components");
  }
}
