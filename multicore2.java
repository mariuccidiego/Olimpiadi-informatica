import java.util.*;
import java.io.*;
//import java.lang.*;

public class multicore2 {
    static int N, B;
    static int[] NC, PC;
    int mem[][];
    static int max;

    public int solve() {
        mem = new int[max+1][N+1];
        for(int i=0; i<max+1; i++){
            for(int j=0; j<N+1; j++){
                mem[i][j] = -1;
            }
        }

        for (int i = max; i >= 0; i--) {
            if(rec(i, N) <= B){
                return i;
            }
        }

        return 0;
    }

    public int rec(int c, int n) {
        if (c == 0) return 0;
        if (n == 0) return 1000000001;

        if (mem[c][n] != -1) return mem[c][n];

        if (NC[n - 1] > c) {
            return mem[c][n] = rec(c, n - 1); 
        } else {
            return mem[c][n] = Math.min(PC[n - 1] + rec(c - NC[n - 1], n - 1), rec(c, n - 1));
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            fin = new FileInputStream("input.txt");
            fout = new FileOutputStream("output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            N = scn.nextInt();//
            B = scn.nextInt();

            NC = new int[N];
            max = 0;
            PC = new int[N];
            for (int i = 0; i < N; i++) {
                NC[i] = scn.nextInt();
                max += NC[i];
                PC[i] = scn.nextInt();
            }

            multicore2 solver = new multicore2();
            int risposta = solver.solve();

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}