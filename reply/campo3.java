import java.util.*;
import java.io.*;
//import java.lang.*;

public class campo3 {
    
    static class Nodo{
        int id;
        int costoPercorso;
        Nodo padre;
        int peso = 0;
        boolean visitato = false;
        boolean prescelto = false;
        boolean roccia = true;

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
    }
    static class Arco{
        Nodo a;
        public Arco(int da, int a){
            this.a = grafo[a];
            grafo[da].archi.add(this);
        }
    }
    static Nodo grafo[];
    static int dist[][];


    // RESOLUTION
    public int solve(int W, int H, int nFiori, int nLiberi, int first) {
        int risposta = 0;
        --nFiori;
        int idPresceltoAttuale = first;

        for (int index = 0; index < nFiori; index++) {
            idPresceltoAttuale = BFS(grafo[idPresceltoAttuale], idPresceltoAttuale);
        }

        return risposta;
    }

    public static int BFS(Nodo start, int idPresceltoAttuale){
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(start);
        start.prescelto = true;

        while(!queue.isEmpty()){
            Nodo current = queue.poll();
            // System.out.println("Visitato nodo: "+current.id);
            for(Arco a: current.archi){
                Boolean fratello=false;
                Nodo neighbor = a.a;
                if(!neighbor.visitato){
                    neighbor.visitato = true;
                    if(neighbor.peso  == current.peso+1){
                        fratello=true;
                    }
                    neighbor.peso = current.peso+1;
                    queue.add(neighbor);
                }
            }
        }

        return idPresceltoAttuale;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            fin = new FileInputStream("latest/input.txt");
            fout = new FileOutputStream("latest/output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int W = scn.nextInt();
            int H = scn.nextInt();
            int nFiori = scn.nextInt();
            int nLiberi = scn.nextInt();

            //int[][] campo = new int[H][W];

            for(int i=0;i<H*W;i++){
                grafo[i] = new Nodo(i);
                grafo[i].id = i;
            }
            int prescelto = -1;
            for (int i = 0; i < nLiberi; i++) {
                int Y = scn.nextInt();
                int X = scn.nextInt();
                grafo[Y*W+X].roccia = false;
                if(prescelto==-1){ grafo[Y*W+X].prescelto = true; prescelto = i; }
            }

            
            for (int h = 0, i = 0; h < H; h++) {
                for (int w = 0; w < W; w++, i++) {
                    if(w!=0){   new Arco( i, i-1); } // LEFT
                    if(h!=0){   new Arco( i, i-W); } // TOP
                    if(w!=W-1){ new Arco( i, i+1); } // RIGHT
                    if(h!=H-1){ new Arco( i, i+W); } // BOTTOM
                }
            }

            campo3 solver = new campo3();
            int risposta = solver.solve(W, H, nFiori, nLiberi, prescelto);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}