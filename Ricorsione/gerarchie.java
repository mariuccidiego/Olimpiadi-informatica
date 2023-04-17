import java.util.*;
import java.io.*;
//import java.lang.*;

public class gerarchie {
    static Nodo grafo[];
    Nodo inizio=new Nodo(-1);

    static class Nodo implements Comparable<Nodo>{
        int id;
        Nodo padre;
        int livello;
        int competenza;
        int promozioni=0;
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
            return "{"+id+" comp="+competenza+"} "+vicini;
        }
        @Override
        public int compareTo(Nodo o) {
            return this.competenza - o.competenza;
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
            return "[ "+a.id+" ("+a.competenza+") ]";
        }
    }

    ArrayList<ArrayList<Nodo>> livs;

    public int solve(int N, int[][] V) {
        System.out.println("------------");
        // aggiungi codice...
        //int risposta = 0;
        grafo = new Nodo[N];

        livs=new ArrayList<ArrayList<Nodo>>();
        for(int i=0;i<1000;i++){
            ArrayList<Nodo> l = new ArrayList<>();
            livs.add(l);
        }
        int primo=0;
        for(int i=0;i<N;i++){
            grafo[i]=new Nodo(i);
        }
        for(int i=0;i<N;i++){
            
            if(V[i][0]==-1){
                grafo[i].livello=0;
                //ArrayList<Nodo> l = new ArrayList<>();
                livs.get(0).add(grafo[i]);
                primo=i;
            }else{
                grafo[i].padre=grafo[V[i][0]];
                new Arco(V[i][0], grafo[i].id, 1);
                //grafo[V[i][0]].archi.add(new Arco(V[i][0], grafo[i].id, 1));
            }
            grafo[i].competenza=V[i][1];
        }
        //dump();
        
        assegnaLivelli(primo, 1);
        for(int i=livs.size()-1;i>=0;i--){
            if(livs.get(i).isEmpty()){
                livs.remove(i);
            }
        }

        for(int k=0;k<livs.size()-1;k++){
            for(int i=livs.size()-1;i>=0;i--){
                for(int j=0;j<livs.get(i).size();j++){
                    spostaMax(i, livs.get(i).get(j),j);
                }
            }
            
        }

        return spostamenti;
    }
    int spostamenti=0;

    void spostaMax(int livello, Nodo x, int arrP){
        if(x.archi.size()==0){

        }else{
            int max=0;
            Nodo ind=new Nodo(0);
            for(int i=0;i<x.archi.size();i++){
                if(x.archi.get(i).a.competenza>max){
                    max=x.archi.get(i).a.competenza;
                    ind=x.archi.get(i).a;
                }
            }
            //Nodo pas;
            if(max>x.competenza){

                scambiaNodi(x, ind);
                spostamenti+=1;
            }
        }
    }

    public static void scambiaNodi(Nodo n1, Nodo n2) {
    
        // scambia le competenze
        int tempCompetenza = n1.competenza;
        n1.competenza = n2.competenza;
        n2.competenza = tempCompetenza;
    
    }

    void assegnaLivelli(int x,int livello){

        //ArrayList<Nodo> l = new ArrayList<>();
       // System.out.println(grafo[x].archi.size());
        for(int i=0;i<grafo[x].archi.size();i++){
            grafo[i].livello=livello;
            livs.get(livello).add(grafo[x].archi.get(i).a);
            assegnaLivelli(grafo[x].archi.get(i).a.id, livello+1);
        }
        //livs.add(l);
    }

    void dumpLivs(){
        System.out.println("#### livelli ####");
        for(int i=0;i<livs.size();i++){
            for(int j=0;j<livs.get(i).size();j++){
                System.out.print("[ "+livs.get(i).get(j).competenza+" ]");
            }
            System.out.println("- "+i);
        }
    }

    static void dump() {
        for (Nodo n : grafo) {
            System.out.println(n);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // true = lettura file
        // false = input da tastiera
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

        // T = numero di casi
        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int N = scn.nextInt();

            //inserisce tutti i dati di una riga in un array
            int[][] V = new int[N][2];
            for (int i = 0; i < N; i++) {
                V[i][0] = scn.nextInt();
                V[i][1] = scn.nextInt();
            }

            gerarchie solver = new gerarchie();
            int risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}