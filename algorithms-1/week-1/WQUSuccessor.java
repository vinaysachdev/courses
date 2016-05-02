import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Auto Generated Java Class.
 */
public class WQUSuccessor {
    private int[] S;
    private WQUFindLargest wqul;
    
    public WQUSuccessor(int N) { 
        S = new int[N];
        WQUFindLargest - new WQUFindLargest(N);
    }
    
    public void remove(int p) {
        S[p] = -p;
        if(p >= 1) wqul.union(p-1, p);
        if(p < N-1) wqul.union(p, p+1);
    }
    
    public int successor(int p) {
        if(S[p] >=0) return S[p];
        return wqul.find(p);
    }
    
    public static void main(String[] args) { 
        
    }
    
    /* ADD YOUR CODE HERE */
    
}
