import java.util.*;
import java.io.*;

public class ostacoli1 {

    Ostacolo[] gara;
    int mem[][];
    int tempoTotale;
    
    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        gara = new Ostacolo[N];
        for (int i = 0; i < N; i++) {
            gara[i] = new Ostacolo(X[i], P[i], S[i]);
        }
        mem=new int[N+1][N+1];
        for(int i=0;i<N+1;i++){
            for(int j=0;j<N+1;j++){
                mem[i][j]=-1;
            }   
        }
        tempoTotale=D;
        int posizione = 0;
        int punteggio = 0;
        int ostacoliArivati = 0;
        System.out.println("--------------------------");
        int ris=ric("",gara, posizione, ostacoliArivati,0, punteggio, 0);
        
        dump(null, mem, N+1, N+1);
        
        return ris;
    }

    public void dump(String nome, int m[][],int N, int M){
        System.out.println(nome);
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.format("%2d ", m[i][j] );
            }   
            System.out.println();
        }
    }

    int ric(String s,Ostacolo[] gara, int posizione, int indiceProssimo, int indiceAttuale, int punteggio, int secondiPassati) {
        System.out.println("sto controllando "+gara[indiceAttuale].punti+"  "+punteggio);
        System.out.println(s+" T "+indiceProssimo+" "+(indiceProssimo+1));
        if (indiceProssimo >= gara.length || secondiPassati>tempoTotale) {
            return punteggio;
        }

        if(mem[indiceAttuale][indiceProssimo] != -1){
            //System.out.println(s+"avevo salvato il valore "+mem[indiceAttuale][indiceProssimo]);
            return mem[indiceAttuale][indiceProssimo];
        }


        
        int punteggioPreso = 0;
        if (Math.abs(posizione - gara[indiceProssimo].distanza) <= gara[indiceProssimo].secondi - secondiPassati) {
            int nuovaPosizione = gara[indiceProssimo].distanza;
            int nuoviSecondiPassati = gara[indiceProssimo].secondi;
            punteggioPreso = ric(s+" | ",gara, nuovaPosizione, indiceProssimo+1,indiceProssimo, gara[indiceProssimo].punti, nuoviSecondiPassati);
        }
        int punteggioNonPreso = ric(s+" | ",gara, posizione, indiceProssimo + 1, indiceAttuale, 0, secondiPassati);
        System.out.println(s+" L "+punteggioPreso+" "+punteggioNonPreso+"    p="+punteggio);
        mem[indiceAttuale][indiceProssimo]=punteggio+Math.max(punteggioNonPreso, punteggioPreso);
        System.out.println(s+"salvo il valore "+Math.max(punteggioNonPreso, punteggioPreso));
        return Math.max(punteggioNonPreso, punteggioPreso);
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