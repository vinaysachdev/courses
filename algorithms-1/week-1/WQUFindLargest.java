import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Auto Generated Java Class.
 */
public class WQUFindLargest {
    private int[] id;   // access to component id (site indexed)
    private int[] sz;   // size of component for roots
    private int[] largest;
    private int count;  // number of components  
    
    public WQUFindLargest(int N) { 
        count = N;
        id = new int[N];
        sz = new int[N];
        largest = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            largest[i] = i;
        }
    }
    /* ADD YOUR CODE HERE */
    public int count() {
        return count;
    }
    
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }
    
    // worst case the array is accessed for 2N - 1 times
    public int findRoot(int p) {
        while(p != id[p])
            p = id[p];
        return p;
    }
    
    // return the largest element in the connected component containing p
    public int find(int p) {
        return largest[findRoot(p)];
    }
    
    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        // Nothing to do if p and q are already in the same component.
        if (pRoot == qRoot) return;
        // Rename p�s component to q�s name.
        if(sz[pRoot] < sz[qRoot]) {
            largest[qRoot] = (largest[qRoot] > largest[pRoot] ? largest[qRoot] : largest[pRoot]);
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            largest[pRoot] = (largest[qRoot] > largest[pRoot] ? largest[qRoot] : largest[pRoot]);  
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }
    
    public static void main(String[] args)
    { // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt(); // Read number of sites.
        WQUFindLargest wqu = new WQUFindLargest(N); // Initialize N components.
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt(); // Read pair to connect.
            if (wqu.connected(p, q)) continue; // Ignore if connected.
            wqu.union(p, q); // Combine components
            StdOut.println(p + " " + q + ", Largest elem: " + wqu.find(p)); // and print connection.
        }
        StdOut.println(wqu.count() + " components");
    }
}
