import java.util.*;
import java.io.*;

public class ostacoli1 {

    /*Ostacolo[] gara;

    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        gara = new Ostacolo[N];
        for (int i = 0; i < N; i++) {
            gara[i] = new Ostacolo(X[i], P[i], S[i]);
        }
        int posizione = 0;
        int punteggio = 0;
        int ostacoliArivati = 0;

        return ric(gara, posizione, ostacoliArivati, punteggio, 0);
    }

    int ric(Ostacolo[] gara, int posizione, int indiceOstacolo, int punteggio, int secondiPassati) {
        if (indiceOstacolo == gara.length) {
            return punteggio;
        }

        int punteggioNonPreso = ric(gara, posizione, indiceOstacolo + 1, punteggio, secondiPassati);

        int punteggioPreso = 0;
        if (Math.abs(posizione - gara[indiceOstacolo].distanza) <= gara[indiceOstacolo].secondi - secondiPassati) {
            int nuovaPosizione = gara[indiceOstacolo].distanza;
            int nuoviSecondiPassati = gara[indiceOstacolo].secondi;
            punteggioPreso = ric(gara, nuovaPosizione, indiceOstacolo + 1, punteggio + gara[indiceOstacolo].punti,
                    nuoviSecondiPassati);
        }

        return Math.max(punteggioNonPreso, punteggioPreso);
    }*/


    Ostacolo[] gara;

    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        gara = new Ostacolo[N];
        for (int i = 0; i < N; i++) {
            gara[i] = new Ostacolo(X[i], P[i], S[i]);
        }
        int posizione = 0;
        int punteggio = 0;
        int ostacoliArivati = 0;
        int[][][] dp = new int[N][L + 1][N* 1000 + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= L; j++) {
                for (int k = 0; k <= N * 1000; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return ric(gara, posizione, ostacoliArivati, punteggio, 0, dp);
    }

    int ric(Ostacolo[] gara, int posizione, int indiceOstacolo, int punteggio, int secondiPassati, int[][][] dp) {
        if (indiceOstacolo == gara.length) {
            return punteggio;
        }

        if (dp[indiceOstacolo][posizione][punteggio] != -1) {
            return dp[indiceOstacolo][posizione][punteggio];
        }

        int punteggioNonPreso = ric(gara, posizione, indiceOstacolo + 1, punteggio, secondiPassati, dp);

        int punteggioPreso = 0;
        if (Math.abs(posizione - gara[indiceOstacolo].distanza) <= gara[indiceOstacolo].secondi - secondiPassati) {
            int nuovaPosizione = gara[indiceOstacolo].distanza;
            int nuoviSecondiPassati = gara[indiceOstacolo].secondi;
            punteggioPreso = ric(gara, nuovaPosizione, indiceOstacolo + 1, punteggio + gara[indiceOstacolo].punti,
                    nuoviSecondiPassati, dp);
        }

        dp[indiceOstacolo][posizione][punteggio] = Math.max(punteggioNonPreso, punteggioPreso);
        return dp[indiceOstacolo][posizione][punteggio];
    }



    class Ostacolo implements Comparable<Ostacolo> {
        int distanza = 0;
        int punti = 0;
        int secondi = 0;

        public Ostacolo(int distanza, int punti, int secondi) {
            this.distanza = distanza;
            this.punti = punti;
            this.secondi = secondi;
        }

        @Override
        public int compareTo(Ostacolo o) {
            return this.secondi - o.secondi;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if (input_from_file) {
            fin = new FileInputStream("input.txt");
            fout = new FileOutputStream("output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int L = scn.nextInt();
            int D = scn.nextInt();

            int[] X = new int[N];
            int[] P = new int[N];
            int[] S = new int[N];
            for (int i = 0; i < N; i++) {
                X[i] = scn.nextInt();
                P[i] = scn.nextInt();
                S[i] = scn.nextInt();
            }

            ostacoli1 solver = new ostacoli1();
            int risposta = solver.solve(N, L, D, X, P, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}