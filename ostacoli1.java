import java.util.*;
import java.io.*;

public class ostacoli1 {
    /*Ostacolo[] gara;
    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {

        

        gara=new Ostacolo[N];

        for(int i=0;i<N;i++){
            gara[i]=new Ostacolo(X[i], P[i], S[i]);
        }
        int posizione=0;
        int punteggio=0;
        int ostacoliArivati=0;

        System.out.println("------------------------");
        return ric(gara, posizione, ostacoliArivati, punteggio, ostacoliArivati, 0);
    }
    int ric(Ostacolo[] gara, int posizione, int ostacoliArivati, int punteggio, int massimo, int secondiPassati){
        //System.out.println(punteggio);
        //System.out.println("sto controllando il n="+ostacoliArivati);
        if(secondiPassati>gara[gara.length-1].secondi){
            //System.out.println("sono arrivato al limite");
            return punteggio;
        }
        
        if(ostacoliArivati>=gara.length-1){
            return punteggio;
        }
        
        for(int i=-1;i<gara.length-1;i++){
            //System.out.println("- "+gara[i+1].punti);
            int x=0;
            //System.out.println(Math.abs(posizione-gara[i+1].distanza)+" < "+ gara[i+1].secondi); 
            if(Math.abs(posizione-gara[i+1].distanza)<=gara[i+1].secondi){
                
                x=gara[i+1].punti;
                secondiPassati+=gara[i+1].distanza;
                posizione=gara[i+1].distanza;
                x=ric(gara, posizione, ostacoliArivati+1, x, massimo, secondiPassati);
            }else{
                //System.out.println("non è raggiungibile");
            }
            
            if(x>punteggio){
                punteggio=x;
            }
        }
        
        return punteggio;
    }*/




    /* 
    Ostacolo[] gara;

    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        gara = new Ostacolo[N];
        for (int i = 0; i < N; i++) {
            gara[i] = new Ostacolo(X[i], P[i], S[i]);
        }
        Arrays.sort(gara);

        int maxX = Arrays.stream(X).max().getAsInt();
        int[][] memo = new int[N][maxX + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }
        return getMaxScore(0, 0, D, memo);
    }

    private int getMaxScore(int idx, int currentTime, int currentX, int[][] memo) {
        if (idx == gara.length) {
            return 0;
        }
        if (memo[idx][currentX] != -1) {
            return memo[idx][currentX];
        }

        int score = 0;
        if (Math.abs(currentX - gara[idx].distanza) <= (gara[idx].secondi - currentTime)) {
            score = getMaxScore(idx + 1, gara[idx].secondi, gara[idx].distanza, memo) + gara[idx].punti;
        }
        score = Math.max(score, getMaxScore(idx + 1, currentTime, currentX, memo));

        memo[idx][currentX] = score;
        return score;
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
        punteggioPreso = ric(gara, nuovaPosizione, indiceOstacolo + 1, punteggio + gara[indiceOstacolo].punti, nuoviSecondiPassati);
    }

    return Math.max(punteggioNonPreso, punteggioPreso);
}


/*Ostacolo[] gara;

public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
    gara = new Ostacolo[N];
    for (int i = 0; i < N; i++) {
        gara[i] = new Ostacolo(X[i], P[i], S[i]);
    }
    int posizione = 0;

    int[][] dp = new int[N][L + 1];
    for (int i = 0; i < N; i++) {
        Arrays.fill(dp[i], -1);
    }

    return ric(gara, posizione, 0, 0, dp);
}

int ric(Ostacolo[] gara, int posizione, int indiceOstacolo, int secondiPassati, int[][] dp) {
    if (indiceOstacolo == gara.length) {
        return 0;
    }

    if (dp[indiceOstacolo][posizione] != -1) {
        return dp[indiceOstacolo][posizione];
    }

    int punteggioNonPreso = ric(gara, posizione, indiceOstacolo + 1, secondiPassati, dp);

    int punteggioPreso = 0;
    if (Math.abs(posizione - gara[indiceOstacolo].distanza) <= gara[indiceOstacolo].secondi - secondiPassati) {
        int nuovaPosizione = gara[indiceOstacolo].distanza;
        int nuoviSecondiPassati = gara[indiceOstacolo].secondi;
        punteggioPreso = ric(gara, nuovaPosizione, indiceOstacolo + 1, nuoviSecondiPassati, dp) + gara[indiceOstacolo].punti;
    }

    int risultato = Math.max(punteggioNonPreso, punteggioPreso);
    dp[indiceOstacolo][posizione] = risultato;
    return risultato;
}*/




    class Ostacolo implements Comparable<Ostacolo>{
        int distanza=0;
        int punti=0;
        int secondi=0;

        public Ostacolo(int distanza, int punti, int secondi){
            this.distanza=distanza;
            this.punti=punti;
            this.secondi=secondi;
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