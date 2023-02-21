import java.util.*;
import java.io.*;
//import java.lang.*;

public class finestrini {
    public int solve(int N, int[] L, int[] R) {

        // aggiungi codice...
        int risposta = 0;

        Vertice[][] grafo = new Vertice[N][2];

        for(int i=0;i<N;i++){
            Vertice v = new Vertice(L[i]);
            grafo[i][0]=v;
            Vertice v1 = new Vertice(R[i]);
            grafo[i][1]=v1;
        }
        for(int i=0;i<N-1;i++){
            grafo[i][0].adiacenti.add(grafo[i+1][0]);
            grafo[i][1].adiacenti.add(grafo[i+1][1]);

            grafo[i+1][0].adiacenti.add(grafo[i][0]);
            grafo[i+1][1].adiacenti.add(grafo[i][1]);

            grafo[i][0].adiacenti.add(grafo[i+1][1]);
            grafo[i][1].adiacenti.add(grafo[i+1][0]);

            grafo[i+1][0].adiacenti.add(grafo[i][1]);
            grafo[i+1][1].adiacenti.add(grafo[i][0]);

            grafo[i][0].pesi.add(grafo[i][0].id);
            grafo[i][1].pesi.add(grafo[i][1].id);

        }

        return risposta;
    }

    class Vertice {
        int id;
        boolean visited = false;
        ArrayList<Vertice> adiacenti = new ArrayList<>();
        ArrayList<Integer> pesi = new ArrayList<>();
        int posizione;
        
        
        public Vertice(int i) {
            id = i;
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

            int[] L = new int[N];
            int[] R = new int[N];
            for (int i = 0; i < N; i++) {
                L[i] = scn.nextInt();
                R[i] = scn.nextInt();
            }
            System.out.println("-----------");
            finestrini solver = new finestrini();
            int risposta = solver.solve(N, L, R);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}