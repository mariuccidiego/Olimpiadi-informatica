import java.util.*;
import java.io.*;

class Main {
    static int massimoSalto = 0;
    // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
    static class Nodo implements Comparable<Nodo>{
        int id;
        int costoPercorso;
        int bigger;
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
            return "{"+id+" $:"+costoPercorso+" ^"+(padre==null?"-":padre.id)+"} "+vicini;
        }
        @Override
        public int compareTo(Nodo o) {
            return this.costoPercorso - o.costoPercorso;
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
            return "[-> "+a.id+" $"+peso+"]";
        }
    }
    static Nodo grafo[];
    // ------------------ FINE implementazione dell'oggetto Grafo -----------

    // ------ stampe di utilità (in pratica solo robe decorative) -----------
    static void dump(){
        for(Nodo n:grafo){
            System.out.println(n);
        }
    }
    static void coperturaDump(){
        System.out.print("P");
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

    // ---------- Dijkstra ----------
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

    static void camminiMinimi(Nodo x){
        // pulizia
        for(Nodo n: grafo){
            n.costoPercorso = Integer.MAX_VALUE;
            n.padre = null;
            n.bigger = 0;
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
                    //if(a.peso > attuale.bigger) {a.a.bigger = a.peso;} else {a.a.bigger = attuale.bigger;}
                }
            }
        }
    }

    public int solve(){
        return 0;
    }

    public static void main(String[] args) throws Exception{
        InputStream fin = new FileInputStream("input.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);
        int T = scn.nextInt();

        for (int t = 1; t <= T; t++) {
            
            int R = scn.nextInt();
            int C = scn.nextInt();
            int numeroNodi = R*C;
            grafo = new Nodo[numeroNodi];
            int altitudini[] = new int[numeroNodi];
            for(int i=0; i<numeroNodi; i++){
                grafo[i] = new Nodo(i);
                altitudini[i] = scn.nextInt();
            }

            for (int h = 0, i = 0; h < R; h++) {
                for (int w = 0; w < C; w++, i++) {
                    if(w!=0){   new Arco( i, i-1, Math.abs(altitudini[i]-altitudini[i-1]) ); } // LEFT
                    if(h!=0){   new Arco( i, i-C, Math.abs(altitudini[i]-altitudini[i-C]) ); } // TOP
                    if(w!=C-1){ new Arco( i, i+1, Math.abs(altitudini[i]-altitudini[i+1]) ); } // RIGHT
                    if(h!=R-1){ new Arco( i, i+C, Math.abs(altitudini[i]-altitudini[i+C]) ); } // BOTTOM
                }
            }

            camminiMinimi(grafo[0]);
            massimoSalto=0;
            tuttoCammino(grafo[grafo.length-1]);
            prnt.format("Case #%d: %s\n", t, ""+massimoSalto);
            fout.flush();
        }
        scn.close();
        fin.close();
        prnt.close();
    }
}