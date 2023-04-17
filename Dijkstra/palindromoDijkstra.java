import java.util.*;
import java.io.*;
//import java.lang.*;

public class palindromoDijkstra {
    static Nodo grafo[];
    ArrayList<String> lunghezze = new ArrayList<String>();

    static class Nodo implements Comparable<Nodo> {
        int id;
        int costoPercorsoA;
        int costoPercorsoB;
        String parolaA="";
        String parolaB="";
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
            return "{" + id + " 💰:" + costoPercorsoA + " ↑" + (padre == null ? "-" : padre.id) + "} " + vicini;
        }

        @Override
        public int compareTo(Nodo o) {
            return this.costoPercorsoA - o.costoPercorsoA;
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

        //dump();

        camminiMinimiA(grafo[X]);
        camminiMinimiB(grafo[Y]);

        //System.out.println(grafo[Y].costoPercorsoA);
        System.out.println("-------------");

        for(int i=0;i<N;i++){
            grafo[i].parolaA="";
            creaParolaA(grafo[i], grafo[i],grafo[X]);
            System.out.println("- parola  = "+grafo[i].parolaA);
            //creaParolaB(grafo[Y], grafo[i]);
        }

        return grafo[Y].costoPercorsoA;
    }

    static void dump() {
        for (Nodo n : grafo) {
            System.out.println(n);
        }
    }

    void creaParolaA(Nodo partenza,Nodo x,Nodo arrivo){
        if(x.padre==null){
            
        }else{
            for(int i=0;i<x.padre.archi.size();i++){
                if(x.padre.archi.get(i).a==x.padre.padre){
                    arrivo.parolaA+=""+x.padre.archi.get(i).lettera;
                }
            }
            System.out.println("parola ( DA="+partenza.id+" A="+arrivo.id+" ("+x.padre.id+")) = "+arrivo.parolaA);
            //System.out.println("-");
            creaParolaA(arrivo, x.padre,partenza);
        }
    }

    void creaParolaB(Nodo partenza,Nodo arrivo){

    }

    static void camminiMinimiA(Nodo x){
        // pulizia
        for(Nodo n: grafo){
            n.costoPercorsoA = Integer.MAX_VALUE;
            n.padre = null;
        }

        // costruzione partenza
        x.costoPercorsoA = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(x);
        
        // costruzione della copertura
        while( !s.isEmpty() ){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                Nodo vicino = a.a;
                int nDist = attuale.costoPercorsoA + a.peso;
                if( nDist < vicino.costoPercorsoA ){
                    vicino.costoPercorsoA = nDist;
                    vicino.padre = attuale;
                    s.add(vicino);
                }
            }
        }
    }

    static void camminiMinimiB(Nodo x){
        // pulizia
        for(Nodo n: grafo){
            n.costoPercorsoB = Integer.MAX_VALUE;
            n.padre = null;
        }

        // costruzione partenza
        x.costoPercorsoB = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(x);
        
        // costruzione della copertura
        while( !s.isEmpty() ){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                Nodo vicino = a.a;
                int nDist = attuale.costoPercorsoB + a.peso;
                if( nDist < vicino.costoPercorsoB ){
                    vicino.costoPercorsoB = nDist;
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