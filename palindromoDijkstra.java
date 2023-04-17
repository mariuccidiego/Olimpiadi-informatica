import java.util.*;
import java.io.*;
//import java.lang.*;

public class palindromoDijkstra {
    static Nodo grafo[];
    ArrayList<String> lunghezze = new ArrayList<String>();

    static class Nodo implements Comparable<Nodo> {
        int id;
        int costoPercorso;
        Nodo padre;
        ArrayList<Arco> archi;

        public Nodo(int id) {
            this.id = id;
            archi = new ArrayList<>();
        }

        public String toString() {
            String vicini = "";
            for (Arco a : archi) {
                vicini += a;
            }
            return "{" + id + " 💰:" + costoPercorso + " ↑" + (padre == null ? "-" : padre.id) + "} " + vicini;
        }

        @Override
        public int compareTo(Nodo o) {
            return this.costoPercorso - o.costoPercorso;
            // return o.costoPercorso - this.costoPercorso;
        }
    }

    static class Arco {
        Nodo a;
        int peso=1;
        char lettera;

        // il costruttore di un arco inserisce anche l'arco stesso
        // nel nodo relativo, il parametro "da" viene ignorato
        // in questa implementazione
        public Arco(int da, int a, char lettera) {
            this.a = grafo[a];
            this.lettera = lettera;
            // grafo[da].archi.add(this);
        }

        public String toString() {
            return "[-" + a.id + " " + lettera + "]";
        }
    }

    public int solve(int N, int M, int X, int Y, int[] A, int[] B, char[] L) {

        grafo = new Nodo[N];
        for (int i = 0; i < N; i++) {
            grafo[i] = new Nodo(i);
        }

        for (int i = 0; i < M; i++) {
            //System.out.println(A[i]);
            grafo[A[i]].archi.add(new Arco(A[i], B[i], L[i]));
            grafo[B[i]].archi.add(new Arco(B[i], A[i], L[i]));
        }

        dump();

        camminiMinimi(grafo[X]);

        return grafo[Y].costoPercorso;
    }

    static void dump() {
        for (Nodo n : grafo) {
            System.out.println(n);
        }
    }

    static void camminiMinimi(Nodo x){
        // pulizia
        for(Nodo n: grafo){
            n.costoPercorso = Integer.MAX_VALUE;
            n.padre = null;
        }

        // costruzione partenza
        x.costoPercorso = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(x);
        
        // costruzione della copertura
        while( !s.isEmpty() ){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                Nodo vicino = a.a;
                int nDist = attuale.costoPercorso + a.peso;
                if( nDist < vicino.costoPercorso ){
                    vicino.costoPercorso = nDist;
                    vicino.padre = attuale;
                    s.add(vicino);
                }
            }
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
            int M = scn.nextInt();
            int X = scn.nextInt();
            int Y = scn.nextInt();

            int[] A = new int[M];
            int[] B = new int[M];
            char[] L = new char[M];
            for (int i = 0; i < M; i++) {
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
                L[i] = scn.next().charAt(0);
            }

            palindromoDijkstra solver = new palindromoDijkstra();
            int risposta = solver.solve(N, M, X, Y, A, B, L);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}