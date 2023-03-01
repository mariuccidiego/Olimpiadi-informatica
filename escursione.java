import java.util.*;
import java.io.*;
//import java.lang.*;

public class escursione {
    static Nodo grafo[];
    public int solve(int X, int Y, int[][] V) {

        // aggiungi codice...
        int risposta = 42;

        int numeroNodi=X*Y;

        grafo = new Nodo[numeroNodi];
        for(int i=0; i<numeroNodi; i++){
            grafo[i] = new Nodo(i);
        }
        int count=0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (j < Y - 1) {
                    grafo[count].id=V[i][j];

                    count++;
                }
            }
        }
        /* 
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                for (int k = 0; k < Grafo[i][j].adiacenti.size(); k++) {
                    // System.out.println("riga "+j+" colonna: "+i+" adiacente: "+ Grafo[i][j].id+"
                    // con "+Grafo[i][j].adiacenti.get(k).id);
                    Grafo[i][j].pesi.add(Math.abs(Grafo[i][j].id - Grafo[i][j].adiacenti.get(k).id));
                    // System.out.println("riga "+j+" colonna: "+i+" adiacente: "+ Grafo[i][j].id+"
                    // con "+Grafo[i][j].adiacenti.get(k).id+" peso: "+Grafo[i][j].pesi.get(k));


                    int da = scn.nextInt();
                    int a = scn.nextInt();
                    int peso = Math.abs(Grafo[i][j].id - Grafo[i][j].adiacenti.get(k).id);
                    new Arco(da, a, peso);
                    new Arco(a, da, peso);

                }
            }
        }


        Vertice[][] Grafo = new Vertice[X][Y];

        Vertice[]GrafNonMat = new Vertice[X*Y];

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                Vertice g = new Vertice(V[i][j]);
                Grafo[i][j] = g;
            }
        }

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (j < Y - 1) {
                    Grafo[i][j].adiacenti.add(Grafo[i][j + 1]);
                    Grafo[i][j + 1].adiacenti.add(Grafo[i][j]);

                }
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (j < X - 1) {
                    // System.out.println(j+"<"+X+"----"+i);
                    Grafo[j][i].adiacenti.add(Grafo[j + 1][i]);
                    Grafo[j + 1][i].adiacenti.add(Grafo[j][i]);
                    // System.out.println(Grafo[j+1][i].id);
                }
            }
        }

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                for (int k = 0; k < Grafo[i][j].adiacenti.size(); k++) {
                    // System.out.println("riga "+j+" colonna: "+i+" adiacente: "+ Grafo[i][j].id+"
                    // con "+Grafo[i][j].adiacenti.get(k).id);
                    Grafo[i][j].pesi.add(Math.abs(Grafo[i][j].id - Grafo[i][j].adiacenti.get(k).id));
                    // System.out.println("riga "+j+" colonna: "+i+" adiacente: "+ Grafo[i][j].id+"
                    // con "+Grafo[i][j].adiacenti.get(k).id+" peso: "+Grafo[i][j].pesi.get(k));
                }
            }
        }
        int c=0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {

                GrafNonMat[c]=Grafo[i][j];
                GrafNonMat[c].posizione=c;
                c++;
            }
        }
        System.out.println(dijkstra(GrafNonMat));*/

        return risposta;
    }

    static class Nodo implements Comparable<Nodo>{
        int id;
        int costoPercorso;
        Nodo padre;
        ArrayList<Arco> archi;
        public Nodo( int id ){
            this.id = id;
            archi = new ArrayList<>();
        }
        public String toString(){
            String vicini = "";
            for(Arco a: archi){
                vicini+=a;
            }
            return "{"+id+" 💰:"+costoPercorso+" ↑"+(padre==null?"-":padre.id)+"} "+vicini;
        }
        @Override
        public int compareTo(Nodo o) {
            return this.costoPercorso - o.costoPercorso;
            // return o.costoPercorso - this.costoPercorso;
        }
    }
    static class Arco{
        Nodo a;
        int peso;
        // il costruttore di un arco inserisce anche l'arco stesso
        // nel nodo relativo, il parametro "da" viene ignorato
        // in questa implementazione
        public Arco(int da, int a, int peso){
            this.a = grafo[a];
            this.peso = peso;
            grafo[da].archi.add(this);
        }
        public String toString(){
            return "[→"+a.id+" 💰"+peso+"]";
        }
    }
    
    // ------------------ FINE implementazione dell'oggetto Grafo -----------

    // ------ stampe di utilità (in pratica solo robe decorative) -----------
    static void dump(){
        for(Nodo n:grafo){
            System.out.println(n);
        }
    }
    static void coperturaDump(){
        System.out.print("🌳");
        Nodo n=null;
        for(int i=0; i<grafo.length; i++){
            if(grafo[i].padre==null){
                n = grafo[i];
                break;
            }
        }
        coperturaDumpRec(0, n);
    }
    private static void coperturaDumpRec(int nest, Nodo n){
        String indent = new String(new char[nest*2]).replace('\0', ' ');
        System.out.println(indent+n);
        for(int i=0; i<grafo.length; i++){
            if(grafo[i].padre == n){
                coperturaDumpRec(nest+1, grafo[i]);
            }
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
        // true = lettura file
        // false = input da tastiera
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

        // T = numero di casi
        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int X = scn.nextInt();
            int Y = scn.nextInt();

            // inserisce tutti i dati di una riga in un array
            int[][] V = new int[X][Y];

            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    V[i][j] = scn.nextInt();
                }
            }

            escursione solver = new escursione();
            int risposta = solver.solve(X, Y, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}