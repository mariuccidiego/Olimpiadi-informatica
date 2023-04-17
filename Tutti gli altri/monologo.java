import java.util.*;
import java.io.*;
//import java.lang.*;

public class monologo {
    public int solve(int N, int[] V, int[] D) {

        // aggiungi codice...
        int risposta = 0;

        for(int i=0;i<N;i++){
            if(V[i]!=D[i]){
                risposta++;
            }
        }

        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // true = lettura file
        // false = input da tastiera
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

        // T = numero di casi
        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int N = scn.nextInt();

            //inserisce tutti i dati di una riga in un array
            int[] V = new int[N];
            int[] D = new int[N];
            for (int i = 0; i < N; i++) {
                V[i] = scn.nextInt();
            }
            for (int i = 0; i < N; i++) {
                D[i] = scn.nextInt();
            }

            monologo solver = new monologo();
            int risposta = solver.solve(N, V, D);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}