import java.util.*;
import java.io.*;
//import java.lang.*;

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

        public String toString() {
            return "[→" + a.id + "]";
        }
    }

    class Combinazione {
        int[] numeri = new int[4];
        boolean[] giaVis = new boolean[4];

        public Combinazione(int a, int b, int c, int d) {
            numeri[0] = a;
            numeri[1] = b;
            numeri[2] = c;
            numeri[3] = d;

            giaVis[0] = false;
            giaVis[1] = false;
            giaVis[2] = false;
            giaVis[3] = false;
        }
    }

    static Nodo grafo[];
    int[] nCoppie;
    ArrayList<Combinazione> combs;
    int pino;
    String ulti;

    public int solve(int N, int M) {
        nCoppie = new int[N];
        pino=0;
        int max = 0;
        ulti="";
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            a.add(grafo[i].id);
            combs = new ArrayList<Combinazione>();
            ric(a);
            // System.out.println(nCoppie[i]);
            if (nCoppie[i] > max) {
                max = nCoppie[i];
            }
        }

        dump();

        return pino;
    }

    void ric(ArrayList<Integer> lista) {
        if (lista.size() == 1) {
            for (int i = 0; i < grafo[lista.get(0)].archi.size(); i++) {
                if (grafo[lista.get(0)].archi.get(i).a.id != grafo[lista.get(0)].id) {
                    lista.add(grafo[lista.get(0)].archi.get(i).a.id);
                    // System.out.println(" - "+lista.get(0));
                    ric(lista);
                }
            }
        } else if (lista.size() == 2) {
            for (int i = 0; i < grafo[lista.get(1)].archi.size(); i++) {
                if (grafo[lista.get(1)].archi.get(i).a.id != grafo[lista.get(1)].id &&
                        grafo[lista.get(1)].archi.get(i).a.id != grafo[lista.get(0)].id) {
                    lista.add(grafo[lista.get(1)].archi.get(i).a.id);
                    // System.out.println(" | - "+lista.get(0)+" "+lista.get(1));
                    ric(lista);
                }
            }
        } else if (lista.size() == 3) {
            for (int i = 0; i < grafo[lista.get(2)].archi.size(); i++) {

                if (grafo[lista.get(2)].archi.get(i).a.id == grafo[lista.get(0)].id) {
                    boolean vb = true;
                    for (int j = 0; j < grafo[lista.get(2)].archi.size(); j++) {
                        if (grafo[lista.get(2)].archi.get(j).a.id == grafo[lista.get(0)].id) {
                            vb = false;
                        }
                    }
                    if (vb == false) {
                        lista.add(grafo[lista.get(2)].archi.get(i).a.id);
                        // System.out.println(" | | - "+lista.get(0)+" "+lista.get(1)+" "+lista.get(2));
                        ric(lista);
                    }
                    // grafo[lista.get(2)].archi.get(i).a.archi.get(j).         
                }
            }
        } else {
            boolean unaVolta=false;
            for (int i = 0; i < grafo[lista.get(2)].archi.size() && unaVolta==false; i++) {

                boolean vb = true;
                for (int j = 0; j < grafo[lista.get(3)].archi.size(); j++) {
                    if (grafo[lista.get(3)].archi.get(j).a.id == grafo[lista.get(1)].id) {
                        vb = false;
                    }
                }
                if (vb == false) {
                    Combinazione com = new Combinazione(lista.get(0), lista.get(1), lista.get(2), lista.get(3));

                    boolean giusto = true;
                    for (int j = 0; j < combs.size(); j++) {
                        if (!controllaCombs(combs.get(j), com)) {
                            giusto = false;
                        }
                    }
                    if (giusto == true) {
                        if(lista.get(0)!=lista.get(3)){
                            unaVolta=true;
                            
                            //nCoppie[lista.get(0)] += 1;
                            if(ulti.equals(lista.get(0) + " " + lista.get(1) + " " + lista.get(2) + " " + lista.get(3))){

                            }else{
                                System.out.println(
                                    "- " + lista.get(0) + " " + lista.get(1) + " " + lista.get(2) + " " + lista.get(3));
                                pino+=1;
                                ulti=lista.get(0) + " " + lista.get(1) + " " + lista.get(2) + " " + lista.get(3);
                            }
                            
                        }
                    
                    }
                }
            }

        }
    }

    boolean controllaCombs(Combinazione a, Combinazione b) {

        boolean fine = false;
        for (int i = 0; i < 4 && fine == false; i++) {
            if (a.numeri[0] == b.numeri[i] && b.giaVis[i] == false) {
                fine = true;
                b.giaVis[i] = true;
            }
        }
        if (fine == false) {
            return false;
        }
        fine = false;

        for (int i = 0; i < 4 && fine == false; i++) {
            if (a.numeri[1] == b.numeri[i] && b.giaVis[i] == false) {
                fine = true;
                b.giaVis[i] = true;
            }
        }
        if (fine == false) {
            return false;
        }
        fine = false;

        for (int i = 0; i < 4 && fine == false; i++) {
            if (a.numeri[2] == b.numeri[i] && b.giaVis[i] == false) {
                fine = true;
                b.giaVis[i] = true;
            }
        }
        if (fine == false) {
            return false;
        }
        fine = false;

        for (int i = 0; i < 4 && fine == false; i++) {
            if (a.numeri[3] == b.numeri[i] && b.giaVis[i] == false) {
                fine = true;
                b.giaVis[i] = true;
            }
        }
        if (fine == false) {
            return false;
        }
        fine = false;

        b.giaVis[0] = false;
        b.giaVis[1] = false;
        b.giaVis[2] = false;
        b.giaVis[3] = false;

        return true;
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
        prnt.close();
        scn.close();
    }
}