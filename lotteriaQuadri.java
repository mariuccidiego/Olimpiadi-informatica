import java.util.*;
import java.io.*;

public class lotteriaQuadri {
    public int solve(int N, long B, long[] V) {

        int risposta = 1;
        long[] vet = new long[N + 1];

        boolean fine = false;

        for (int i = 1; i < N; i++) {
            vet[i] = V[i - 1] + vet[i];
        }

        vet[N] = 0;

        while (!fine) {

            for (int i = risposta; i <= N; i++) {
                long somma = 0;

                somma = vet[i] - vet[i - risposta];
                //System.out.println(i + " " + risposta);
                if (somma > B || risposta == N) {
                    fine = true;
                }
            }
            risposta += 1;

        }

        //System.out.println("-----");
        return risposta - 1;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

        // se preferisci leggere e scrivere da file
        // ti basta decommentare le seguenti righe
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int N = scn.nextInt();
        long B = scn.nextLong();

        long[] V = new long[N];

        for (int i = 0; i < N; i++) {
            V[i] = scn.nextInt();
        }

        lotteriaQuadri solver = new lotteriaQuadri();
        int risposta = solver.solve(N, B, V);

        prnt.format("" + risposta);
        fout.flush();
        prnt.close();
        scn.close();
    }
}