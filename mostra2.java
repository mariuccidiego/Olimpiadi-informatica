import java.util.*;
import java.io.*;

public class mostra2 {
   
    int[][] memoria;
    int V[];
    int G[];

    /** @return massimo guadagno possibile */
    public int solve(int V[], int G[]) {
        this.V=V;
        this.G=G;
        memoria=new int[V.length][G.length];
        return 0;
    }

    static int max(int n1, int n2, int n3){
        int max=n1;
        if(max<n2){
            max=n2;
        }
        if(max<n3){
            max=n3;
        }
        
        return max;
    }

    public int ric(int possV, int possG){
        
        
        
        return 0;
    }

    public void dump(String nome, int m[][],int N, int M){
        System.out.println(nome);
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.format("%2d ", m[i][j] );
            }   
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = new FileInputStream("input.txt");
        // InputStream fin = new FileInputStream("escursione_input_1.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();

            int[] V = new int[N];
            for (int i = 0; i < N ; i++) {
                V[i] = scn.nextInt();
            }

            int[] G = new int[M];
            for (int i = 0; i < M ; i++) {
                G[i] = scn.nextInt();
            }

            mostra2 solver = new mostra2();
            int risposta = solver.solve(V, G);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}