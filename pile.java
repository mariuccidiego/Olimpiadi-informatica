import java.util.*;
import java.io.*;
//import java.lang.*;

public class pile {
    public int solve(int N, int[] A, int[] B, int[] C) {

        // aggiungi codice...
        int risposta = 0;

        for(int i=0;i<N;i++){
            int max=A[i];

            if(B[i]>max){
                max=B[i];
            }
            if(C[i]>max){
                max=C[i];
            }
            risposta+=max;
        }

        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
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
            int N = scn.nextInt();

            int[] A = new int[N];
            int[] B = new int[N];
            int[] C = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
                C[i] = scn.nextInt();
            }

            pile solver = new pile();
            int risposta = solver.solve(N, A, B, C);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}