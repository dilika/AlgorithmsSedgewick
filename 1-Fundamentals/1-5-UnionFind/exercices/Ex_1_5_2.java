import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



/*
1.5.2 Do Exercise 1.5.1, but use quick-union (page 224). In addition, draw the forest of
trees represented by the id[] array after each input pair is processed.
* */

public class Ex_1_5_2 {
    private int id[];
    private int count;

    public Ex_1_5_2(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
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

        id[i] = j;
        count--;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex_1_5_2 ex = new Ex_1_5_2(N);

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
*                     EX1.5.2: Solution
*  ------------------------------------------------------------------
*  | Operation   | id\[] after operation           | Array Accesses |
*  | ---------   | ------------------------------- | -------------- |
*  | 9-0         | [0, 1, 2, 3, 4, 5, 6, 7, 8, 0]  | 2r+1w=3        |  1)  0 1 2 3 4 5 6 7 8
*  | 3-4         | [9, 1, 2, 4, 4, 5, 6, 7, 8, 0]  | 2r+1w=3        |      |
*  | 5-8         | [0, 1, 2, 4, 4, 8, 6, 7, 8, 0]  | 2r+1w=3        |      9
*  | 7-2         | [0, 1, 2, 4, 4, 8, 6, 2, 8, 0]  | 2r+1w=3        |  2)  0 1 2 4 5 6 7 8
*  | 2-1         | [0, 1, 1, 4, 4, 8, 6, 2, 8, 0]  | 2r+1w=3        |      |     |
*  | 5-7         | [0, 1, 1, 4, 4, 8, 6, 2, 1, 0]  | 6r+4w=10       |      9     3
*  | 0-3         | [4, 1, 1, 4, 4, 1, 6, 1, 1, 4]  | 3r+2w=5        |  3)  0 1 2 4 6 7 8
*  | 4-2         | [1, 1, 1, 1, 1, 1, 6, 1, 1, 1]  | 3r+2w=5        |      |     |     |
*  ------------------------------------------------------------------      9     3     5
*                                                                      4)  0 1 2 4 6 8
*                                                                          |   | |   |
*                                                                          9   7 3   5
*                                                                      5)  0   1    4 6
                                                                           |   |    |
                                                                           9   2    3
*                                                                             /  \
*                                                                            8    7
*                                                                            |
*                                                                            5
*                                                                       7) 4           1    6
*                                                                         / \          |
*                                                                        0   3         2
*                                                                        |            / \
*                                                                        9           8   7
*                                                                                    |
*                                                                                    5
*
*                                                                          4 -------1    6
*                                                                          / \        \
*                                                                         0   3         2
*                                                                         |            / \
*                                                                         9           8   7
*                                                                                     |
*                                                                                     5
*
*
*
* */