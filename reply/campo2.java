import java.util.*;
import java.io.*;
//import java.lang.*;

public class campo2 {
    
    static class Nodo{
        int id;
        int costoPercorso=-1;
        Nodo padre;
        int peso = 0;
        boolean prescelto = false;
        boolean roccia = false;

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
        public String toString(){
            return "[→"+a.id+"]";
        }
    }
    static Nodo grafo[];

    public static void BFS(Nodo start){
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(start);
        start.prescelto = true;
        while(!queue.isEmpty()){
            Nodo current = queue.poll();
            System.out.println("Visitato nodo: "+current.id);
            for(Arco a: current.archi){
                Nodo neighbor = a.a;
                if(!neighbor.prescelto){
                    neighbor.prescelto = true;
                    queue.add(neighbor);
                }
            }
        }
    }
    public int dist(int x1,int y1,int x2,int y2){
        int distanza=0;

        distanza=Math.abs(x1-x2)+Math.abs(y1-y2);

        return distanza;
    }

    // RESOLUTION
    /*public int solve(int W, int H, int nFiori, int nLiberi) {
        int risposta = 0;
        //dump();

        //dump2(null, campo, W, H);
        int distMax=0;
        int[][] distFiori = new int[H][W];
        int[][] possFiori = new int[nFiori][2];
        possFiori[0][0]=0;
        possFiori[0][1]=0;
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(grafo[i*W+j].roccia==true){
                    int distOra=dist(0, 0, j, i);
                    //System.out.println(distOra);
                    grafo[i*W+j].costoPercorso=distOra;
                    System.out.println("costo="+grafo[i*W+j].costoPercorso);
                    distFiori[i][j]=distOra;
                    if(distMax<distOra){
                        distMax=distOra;
                        possFiori[1][0]=i;
                        possFiori[1][1]=j;
                        System.out.println(possFiori[1][1]);
                    }
                }else{
                    distFiori[i][j]=-1;
                }
            }  
        }
        dump(H,W);


        return risposta;
    }*/
    public int solve(int W, int H, int nFiori, int nLiberi, int first) {
        int risposta = 0;
        boolean ntrovato = true;
        int size = W*H;
        --nFiori;
        int idPresceltoAttuale = first;

        for (int index = 0; index < nFiori; index++) {
            BFS(grafo[idPresceltoAttuale]);
        }

        return risposta;
    }

    /*public void dump2(String nome, int m[][],int N, int M){
        System.out.println(nome);
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.format("%2d ", m[i][j] );
            }   
            System.out.println();
        }
    }*/

    /*void bfs(Nodo grafo[], ) {

    }*/
    static void dump(int N,int M){
            for(int i=0;i<N;i++){
                for(int j=0; j<M; j++){
                    System.out.format("%2d ", grafo[i*N+j].costoPercorso );
                    //System.out.print(grafo[i*N+j].costoPercorso+" ");
                }  
                System.out.println();
            }
            

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

            fiori solver = new fiori();
            int risposta = solver.solve(W, H, nFiori, nLiberi, prescelto);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}