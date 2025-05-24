import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/*
Do Exercise 1.5.1, but use weighted quick-union (page 228).
* */

public class Ex_1_5_3 {
    private int id[];
    private int sz[];
    private int count;

    public Ex_1_5_3(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        if(sz[i] < sz[j]) {id[i] = j;  sz[j] += sz[i]}
        else              {id[j] = i;  sz[i] += sz[j]}
        count--;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex_1_5_3 ex = new Ex_1_5_3(N);

        while (!StdIn.isEmpty()) {
            int pid = StdIn.readInt();
            int qid = StdIn.readInt();
            if (ex.connected(pid, qid)) return;
            ex.union(pid, qid);
            StdOut.println(pid + " " + qid);
        }
        StdOut.println("# components: " + ex.count());
    }
}
/*
*                     EX1.5.3: Solution
*  --------------------------------------------------------------------------------------------
*  | Operation   | id\[] after operation           | Array Accesses | Size Accesses           |
*  | ---------   | ------------------------------- | -------------- | ------------------------|
*  | 9-0         | [9, 1, 2, 3, 4, 5, 6, 7, 8, 9]  | 2r+1w=3        | 3r+1w=4  sz[9] = 2      |
*  | 3-4         | [9, 1, 2, 3, 3, 5, 6, 7, 8, 9]  | 2r+1w=3        | 3r+1w=4  sz[3] = 2      |
*  | 5-8         | [9, 1, 2, 3, 3, 5, 6, 7, 5, 9]  | 2r+1w=3        | 3r+1w=4  sz[9] = 2      |
*  | 7-2         | [9, 1, 7, 3, 3, 5, 6, 7, 5, 9]  | 2r+1w=3        | 3r+1w=4  sz[7] = 2      |
*  | 2-1         | [9, 7, 7, 3, 3, 5, 6, 7, 5, 9]  | 4r+1w=5        | 3r+1w=4  sz[7] = 3      |
*  | 5-7         | [9, 7, 7, 3, 3, 7, 6, 7, 5, 9]  | 2r+1w=3        | 3r+1w=4  sz[7] = 4      |
*  | 0-3         | [9, 7, 7, 9, 3, 7, 6, 7, 5, 9]  | 3r+1w=4        | 3r+1w=4  sz[9] = 4      |
*  | 4-2         | [9, 7, 7, 9, 3, 7, 6, 7, 5, 7]  | 8r+2w=5        | 3r+1w=4  sz[9] = 8      |
*  --------------------------------------------------------------------------------------------
*
*
* my solution
* [9, 7, 7, 9, 3, 7, 6, 7, 5, 7]
*
* */