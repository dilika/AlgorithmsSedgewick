import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/*
*Exercie:
* 1.5.1 Show the contents of the id[] array and the number of times the array 
* is accessed for each input pair when you use quick-find for the sequence
@input = 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
* */

public class Ex_1_5_1 {
    private int id[];
    private int count;

    public Ex_1_5_1(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex_1_5_1 ex = new Ex_1_5_1(N);

        while (!StdIn.isEmpty()) {
            int pid = StdIn.readInt();
            int qid = StdIn.readInt();
            if (ex.connected(pid, qid)) return;
            ex.union(pid, qid);
            StdOut.println(pid + " " + qid);
        }
        StdOut.println("# components: " + ex.count());
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = qid;
        count--;
    }

    public int count() {
        return count;
    }
}

// Solution
// For before first input line
// Table id content: [0,1,2,3,4,5,6,7,8]
// For Input : 9-0
// Union(9,0):
// pid = 9, qid = 0; Find --> 2 access
// Non connecte --> 10
// contenu id apres union est: [0,1,2,3,4,5,6,7,8]

// For before first input line
// Table id content: [0,1,2,3,4,5,6,7,8]
// For Input : 3-4
// Union(3,4):
// pid = 3, qid = 4; Find --> 2 access
// Non connecte union + ecriture --> 10
// contenu id apres union est: [0,1,2,4,4,5,6,7,8]

// For before first input line
// Table id content: [0,1,2,4,4,5,6,7,8]
// For Input : 3-4
// Union(3,4):
// pid = 3, qid = 4; Find --> 2 access
// Non connecte union + ecriture --> 10
// contenu id apres union est: [0,1,2,4,4,5,6,7,8]