import java.util.*;
import java.io.*;
//import java.lang.*;

public class vacanze {
    public int solve(int N, int M, int[] A, int[] B) {

        // aggiungi codice...
        int risposta = 42;

        

        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = false;

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
            int N = scn.nextInt();
            int M = scn.nextInt();

            int[] A = new int[M];
            int[] B = new int[M];
            for (int i = 0; i < M; i++) {
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
            }

            vacanze solver = new vacanze();
            int risposta = solver.solve(N, M, A, B);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}