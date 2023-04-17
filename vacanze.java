import java.util.*;
import java.io.*;
import java.lang.*;

public class vacanze {

    // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
    static class Nodo {
        int id;
        int costoPercorso;
        boolean visitato = false;
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
            return "{" + id + " $:" + costoPercorso + " ^" + (padre == null ? "-" : padre.id) + "} " + vicini;
        }
    }

    static class Arco {
        Nodo a;

        public Arco(int da, int a) {
            this.a = grafo[a];
            grafo[da].archi.add(this);
        }

        public String toString(){
            return "[→"+a.id+"]";
        }
    }

    static Nodo grafo[];
    int[] nCoppie;

    public int solve(int N, int M) {
        nCoppie=new int[N];
        int max=0;
        for(int i=0;i<N;i++){
            ArrayList<Integer> a= new ArrayList<Integer>();
            a.add(grafo[i].id);
            ric(a);
            //System.out.println(nCoppie[i]);
            if(nCoppie[i]>max){
                max=nCoppie[i];
            }
        }

        dump();

        return max;
    }

    void ric(ArrayList<Integer> lista){
        if(lista.size()==1){
            for(int i=0;i<grafo[lista.get(0)].archi.size();i++){
                if(grafo[lista.get(0)].archi.get(i).a.id != grafo[lista.get(0)].id){
                    lista.add(grafo[lista.get(0)].archi.get(i).a.id);
                    //System.out.println(" - "+lista.get(0));
                    ric(lista);
                }
            }
        }else if(lista.size()==2){
            for(int i=0;i<grafo[lista.get(1)].archi.size();i++){
                if(grafo[lista.get(1)].archi.get(i).a.id != grafo[lista.get(1)].id &&
                grafo[lista.get(1)].archi.get(i).a.id != grafo[lista.get(0)].id){
                    lista.add(grafo[lista.get(1)].archi.get(i).a.id);
                    //System.out.println(" | - "+lista.get(0)+" "+lista.get(1));
                    ric(lista);
                }
            }
        }else if(lista.size()==3){
            for(int i=0;i<grafo[lista.get(2)].archi.size();i++){
                if(grafo[lista.get(2)].archi.get(i).a.id == grafo[lista.get(0)].id){
                    lista.add(grafo[lista.get(2)].archi.get(i).a.id);
                    //System.out.println(" | | - "+lista.get(0)+" "+lista.get(1)+" "+lista.get(2));
                    ric(lista);
                }
            }
        }else{
            System.out.println("- "+lista.get(0)+" "+lista.get(1)+" "+lista.get(2)+" "+lista.get(3));
            nCoppie[lista.get(0)]+=1;
        }
    }


    static void dump() {
        for (Nodo n : grafo) {
            System.out.println(n);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream fin;
        OutputStream fout;
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();
            grafo = new Nodo[N];

            for (int i = 0; i < N; i++)
                grafo[i] = new Nodo(i);

            int a, b;
            for (int i = 0; i < M; i++) {
                a = scn.nextInt();
                b = scn.nextInt();
                new Arco(a, b);
                new Arco(b, a);
            }

            vacanze solver = new vacanze();
            int risposta = solver.solve(N, M);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}