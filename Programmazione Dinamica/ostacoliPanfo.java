import java.util.*;
import java.io.*;

public class ostacoliPanfo {
    int[] posizione;
    int[] punti;
    int[] tempo;
    int tempoTotale;

    int memoria[][];

    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        this.posizione = X;
        this.punti = P;
        this.tempo = S;
        this.tempoTotale = D;

        memoria = new int[N+1][N+1];
        for(int i=0;i<N+1;i++){
            for(int j=0;j<N+1;j++){
                memoria[i][j]=-1;
            }   
        }
        //System.out.println(N+" "+L+" "+D+"=========");
        int risposta = calcola("", 0, 0, 0, 0, 0);

        //dump(null, memoria, N+1, N+1);

        return risposta;
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

    private int calcola(
        String s,
        int attualePunteggio,
        int attualePosizione,
        int attualeTempo,
        int indiceProssimo,
        int attualeIndice
    ){
        int puntiSalto=0, puntiIgnoro;

        int risposta;

        //System.out.println(s+" "+attualePunteggio);


        if(attualeTempo>=tempoTotale || indiceProssimo==posizione.length ){
            risposta = attualePunteggio;
        } else {

            if(memoria[attualeIndice][indiceProssimo] > -1)
                return memoria[attualeIndice][indiceProssimo];

            int deltaT = tempo[indiceProssimo] - attualeTempo;
            int deltaP = Math.abs( posizione[indiceProssimo] - attualePosizione );
            if(deltaP<=deltaT){
                // lo salto
                puntiSalto = punti[indiceProssimo] + calcola(s+" ",0, posizione[indiceProssimo], tempo[indiceProssimo], indiceProssimo+1, indiceProssimo);
            }
            puntiIgnoro = calcola(s+" ",0, attualePosizione, attualeTempo, indiceProssimo+1, attualeIndice);
            
            risposta = attualePunteggio + Math.max(puntiSalto, puntiIgnoro);
            memoria[attualeIndice][indiceProssimo] = risposta;
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
            // fin = new FileInputStream("ostacoli_input_2.txt");
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

            ostacoliPanfo solver = new ostacoliPanfo();
            int risposta = solver.solve(N, L, D, X, P, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}