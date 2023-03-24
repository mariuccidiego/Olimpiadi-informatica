import java.util.*;
import java.io.*;
//import java.lang.*;

public class campoDinamico {
    public int solve(int W, int H, int nFiori, int nRocce, int[][]campo) {

        // aggiungi codice...
        int risposta = 0;

        dump(null, campo, W, H);
        int distMax=0;
        int[][] distFiori = new int[H][W];
        int[][] possFiori = new int[nFiori][2];
        possFiori[0][0]=0;
        possFiori[0][1]=0;
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(campo[i][j]==0){
                    int distOra=dist(0, 0, j, i);
                    //System.out.println(distOra);
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
        dump(null, distFiori, W, H);


        
        return risposta;
    }
    public int dist(int x1,int y1,int x2,int y2){
        int distanza=0;

        distanza=Math.abs(x1-x2)+Math.abs(y1-y2);

        return distanza;
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
            int W = scn.nextInt();
            int H = scn.nextInt();
            int nFiori = scn.nextInt();
            int nRocce = scn.nextInt();

            int[][] campo = new int[H][W];

            for(int i=0;i<H;i++){
                for(int j=0;j<W;j++){
                    campo[i][j]=-1;
                }
            }

            for (int i = 0; i < nRocce; i++) {
                int Y = scn.nextInt();
                int X = scn.nextInt();

                campo[X][Y]=0;
            }
            

            campoDinamico solver = new campoDinamico();
            int risposta = solver.solve(W,H,nFiori,nRocce,campo);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}