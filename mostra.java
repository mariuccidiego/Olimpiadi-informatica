import java.util.*;
import java.io.*;

public class mostra {
   
    /** @return massimo guadagno possibile */
    public int solve(int V[], int G[]) {
        

        int[][] matrix = new int[G.length][V.length+1];

        for(int i=0;i<G.length;i++){
            int somma=0;
            for(int j=0;j<V.length;j++){
                if(V[j]<G[i]){
                    matrix[i][j]=1;
                    somma+=1;
                }else{
                    matrix[i][j]=0;
                }
                
            }
            matrix[i][V.length]=somma;
        }
        dump(null, matrix, G.length, V.length+1);
        
        int[][] assegnati = new int[2][V.length];

        for(int i=0;i<V.length;i++){
            assegnati[0][i]=V[i];
            assegnati[1][i]=-1;
        }
        for(int i=0;i<G.length;i++){
            for(int j=0;j<V.length;j++){
                int ultimoAssegnato=0;
                if(matrix[i][j]==1 && i==0){
                    assegnati[1][j]=0;
                    ultimoAssegnato=j;
                }
                if(matrix[i][V.length]!=0){
                    int fast=quantoFastidio(V, G, matrix, assegnati, i, j, ultimoAssegnato);
                    System.out.println(fast);
                }
                
                if(matrix[i][j]==1 && assegnati[1][j]!=-1){
                    
                    break;
                }
                
            }
        }

        return 0;
    }



    public int quantoFastidio(int V[], int G[],int[][]matrix, int[][]assegnati, int guida, int poss, int ultimoAssegnato){
        int nFastidio=0;
        
        for(int i=guida;i<G.length-guida;i++){
            for(int j=ultimoAssegnato+1;j<=poss;j++){
                if(matrix[i][j]==1){
                    nFastidio+=1;
                    System.out.println("FASTIDIO="+nFastidio);
                }
            }
            
            
        }
        System.out.println("--- "+guida);
        return nFastidio;
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

            mostra solver = new mostra();
            int risposta = solver.solve(V, G);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}