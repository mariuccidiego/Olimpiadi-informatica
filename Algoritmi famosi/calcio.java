import java.util.*;
import java.io.*;
//import java.lang.*;

public class calcio {
    public int solve(int N, int M, int K, int A, int B, int[] X, int[] Y) {

        // aggiungi codice...
        int risposta = Integer.MAX_VALUE;

        int[][] matrix = new int[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                matrix[i][j]=0;
            }
        }

        for (int i = 0; i < K; i++) {

            matrix[X[i]][Y[i]]+=1;
            //System.out.println(X[i]+" "+Y[i]);
        }
        //dump("cx",matrix,N,M);


        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(i==0){
                    if(j==0){
                        
                    }else{
                        matrix[i][j]= matrix[i][j]+ matrix[i][j-1];
                    }
                }else{
                    if(j==0){
                        matrix[i][j]= matrix[i][j]+ matrix[i-1][j];
                    }else{
                        matrix[i][j]=matrix[i][j]+matrix[i-1][j]+(matrix[i][j-1]-matrix[i-1][j-1]);
                    }
                }
            }
        }
        //dump("cxfsdaf",matrix,N,M);

        for(int i=A-1;i<N;i++){
            for(int j=B-1;j<M;j++){
                int costo=0;

                //System.out.println("i="+i+"j="+j);
                if(i==A-1){
                    if(j==B-1){
                        costo=matrix[i][j];
                    }else{
                        
                        costo= matrix[i][j]- matrix[i][j-B];
                    }
                }else{
                    if(j==B-1){
                        costo= matrix[i][j]- matrix[i-A][j];
                    }else{
                        costo=matrix[i][j]-matrix[i-A][j]-(matrix[i][j-B]-matrix[i-A][j-B]);
                    }
                }
                if(costo<risposta){
                    risposta=costo;
                }
                //System.out.println("costo= "+costo);
            }
        }

        return risposta;
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
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
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

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();
            int K = scn.nextInt();
            int A = scn.nextInt();
            int B = scn.nextInt();

            int[] X = new int[K];
            int[] Y = new int[K];
            int[][] mat = new int[N][M];

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    mat[i][j]=0;
                }
            }

            for (int i = 0; i < K; i++) {
                X[i] = scn.nextInt();
                Y[i] = scn.nextInt();
            }
            

            calcio solver = new calcio();
            int risposta = solver.solve(N, M, K, A, B, X, Y);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}