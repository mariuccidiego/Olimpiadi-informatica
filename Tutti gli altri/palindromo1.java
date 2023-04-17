import java.util.*;
import java.io.*;
//import java.lang.*;

public class palindromo1 {
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
        int peso;
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

        // aggiungi codice...
        //int risposta = 42;
        grafo = new Nodo[N];
        for (int i = 0; i < N; i++) {
            grafo[i] = new Nodo(i);
        }
        // System.out.println(M);;
        for (int i = 0; i < M; i++) {
            //System.out.println(A[i]);
            grafo[A[i]].archi.add(new Arco(A[i], B[i], L[i]));
            grafo[B[i]].archi.add(new Arco(B[i], A[i], L[i]));
        }

        //dump();
        ArrayList<Integer> v1 = new ArrayList<Integer>();
        v1.add(X);
        ArrayList<Integer> v2 = new ArrayList<Integer>();
        v2.add(Y);
        String c = prossimaLettera("",X, Y, v1, v2, "","","");
        //System.out.println(c);
        int min=Integer.MAX_VALUE;
        if(c.equals("-1")){
            return -1;
        }else{
            for(int i=0;i<lunghezze.size();i++){
                System.out.println(lunghezze.get(i));
                if(lunghezze.get(i).length()<min){
                    min=lunghezze.get(i).length();
                    
                }
            }
        }
        return min;
    }

    static void dump() {
        for (Nodo n : grafo) {
            System.out.println(n);
        }
    }

    String prossimaLettera(String sys,int indice1, int indice2, ArrayList<Integer> visitati1, ArrayList<Integer> visitati2,String parolaA,String parolaB, String parola) {

        boolean nienteUguale = true;
        //String s="";
        for (int i = 0; i < grafo[indice1].archi.size(); i++) {
            for (int j = 0; j < grafo[indice2].archi.size(); j++) {
                
                if (grafo[indice1].archi.get(i).lettera == grafo[indice2].archi.get(j).lettera) {
                    // controllo gia passato
                    //System.out.println(sys+grafo[indice1].id+" == "+grafo[indice2].id)

                    boolean vb = true;
                    for (int k = 0; k < visitati1.size(); k++) {
                        if (grafo[indice1].archi.get(i).a.id == visitati1.get(k)) {
                            for (int l = 0; l < visitati2.size(); l++) {
                                if (grafo[indice2].archi.get(j).a.id == visitati2.get(l)) {
                                    vb = false;
                                }
                            }
                        }
                    }

                    if (vb == true) {
                        if (indice1 == grafo[indice2].archi.get(j).a.id &&
                            indice2 == grafo[indice1].archi.get(i).a.id) {
                            
                            lunghezze.add(""+parolaA + "" + grafo[indice1].archi.get(i).lettera+""+parolaB);
                            return parolaB + "" + grafo[indice1].archi.get(i).lettera+""+parolaB;
                        }

                        if (grafo[indice1].archi.get(i).a.id == grafo[indice2].archi.get(j).a.id) {
                            lunghezze.add(""+parolaA + "" + grafo[indice1].archi.get(i).lettera+grafo[indice1].archi.get(i).lettera+parolaB);
                            return parolaA + "" + grafo[indice1].archi.get(i).lettera+grafo[indice1].archi.get(i).lettera+""+parolaB;
                        }

                        ArrayList<Integer> visitati1P= new ArrayList<>(visitati1);
                        ArrayList<Integer> visitati2P= new ArrayList<>(visitati2);
                        visitati1P.add(grafo[indice1].archi.get(i).a.id);
                        visitati2P.add(grafo[indice2].archi.get(j).a.id);
                        System.out.println(visitati1P+" "+visitati2P);
                        nienteUguale = false;
                        parolaA+=grafo[indice1].archi.get(i).lettera;
                        parolaB+=grafo[indice1].archi.get(i).lettera;
                        parola =parolaA+""+ prossimaLettera(" | ",grafo[indice1].archi.get(i).a.id, 
                        grafo[indice2].archi.get(j).a.id, visitati1P, visitati2P, parolaA,parolaB,parolaA+parolaB);
                    }
                }
            }
        }

        if (nienteUguale == true) {
            return "-1";
        }

        return parola;
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

            palindromo1 solver = new palindromo1();
            int risposta = solver.solve(N, M, X, Y, A, B, L);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}