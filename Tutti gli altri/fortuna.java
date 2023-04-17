import java.util.*;
import java.io.*;
//import java.lang.*;

public class fortuna {
    public int solve(int N, int[] V, int[] G) {

        // aggiungi codice...
        int risposta = Integer.MAX_VALUE;

        for(int i=0;i<V.length;i++){//spostamento
            int sommaTotale=0;
            
            for(int j=0;j<V.length;j++){//calcolo somma
                int posizione=i+j;

                if(posizione>V.length-1){
                    posizione=posizione-V.length;
                }

                sommaTotale=sommaTotale+(G[j]*V[posizione]);
            }
            if(sommaTotale<risposta){
                risposta=sommaTotale;
            }
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

            int[] V = new int[N];
            int[] G = new int[N];

            for (int i = 0; i < N; i++) {
                V[i] = scn.nextInt();
            }

            for (int i = 0; i < N; i++) {
                G[i] = scn.nextInt();
            }

            fortuna solver = new fortuna();
            int risposta = solver.solve(N, V, G);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}