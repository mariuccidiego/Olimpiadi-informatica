import java.util.*;
import java.io.*;
//import java.lang.*;

public class escursione {
    static Nodo grafo[];
    static int massimoSalto=0;
    public int solve(int X, int Y, int[][] V) {

        // aggiungi codice...
        //int risposta = 42;

        int numeroNodi=X*Y;
        System.out.println(numeroNodi);
        grafo = new Nodo[numeroNodi];
        for(int i=0; i<numeroNodi; i++){
            grafo[i] = new Nodo(i);
        }
        int count=0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                grafo[count].id=V[i][j];
                //System.out.println(grafo[count]);
                count++;
            }
        }


        
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (j < Y - 1) {
                    int da = (i*Y)+j;
                    int a = (i*Y)+j+1;
                    
                    System.out.println("da: "+da+" i="+i+" j="+j);
                    System.out.println("a: "+a);
                    int peso = Math.abs(V[i][j] - V[i][j+1]);
                    new Arco(da, a, peso);
                    new Arco(a, da, peso);
                }
            }
        }

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (i < X - 1) {
                    int da = (i*Y)+j;
                    int a = (i*Y)+j+Y;
                    /*System.out.println("da: "+da);
                    System.out.println("a: "+a);
                    System.out.println(V[i][j]+"  "+V[i+1][j]);*/
                    int peso = Math.abs(V[i][j] - V[i+1][j]);
                    new Arco(da, a, peso);
                    new Arco(a, da, peso);
                }
            }
        }
        dump();
        
        camminiMinimi(grafo[0]);
        massimoSalto=0;
        tuttoCammino(grafo[grafo.length-1]);
        System.out.println(massimoSalto);

        System.out.println("-----------");
        return grafo[grafo.length-1].costoPercorso;
    }

    static void tuttoCammino(Nodo x){
        //ArrayList<Nodo> finale = new ArrayList<Nodo>();
        if(x.padre.padre==null){

        }else{
            int c=x.costoPercorso-x.padre.costoPercorso;
            if(c>massimoSalto){
                massimoSalto=c;
                System.out.println("= "+massimoSalto);
            }
            System.out.println("padre="+x.padre.id);
            tuttoCammino(x.padre);
        }
        
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
                int nDist = attuale.costoPercorso > a.peso ? attuale.costoPercorso : a.peso;
                
                /*if(attuale.costoPercorso > a.peso){
                    nDist = attuale.costoPercorso;
                    vicino.padre = attuale;
                    s.add(vicino);
                }else{
                    nDist = a.peso;
                    vicino.padre = attuale;
                    s.add(vicino);
                }*/

                
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