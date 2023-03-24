import java.util.*;
import java.io.*;
//import java.lang.*;

public class miner {
    public int solve(int N, int colori, int nGemme, char[][]campo) {

        // aggiungi codice...
        int risposta = 0;

        dump(null, campo, N, N);

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                
                

            }
        }

        return risposta;
    }
    public int dist(int x1,int y1,int x2,int y2){
        int distanza=0;

        distanza=Math.abs(x1-x2)+Math.abs(y1-y2);

        return distanza;
    }

    public void dump(String nome, char m[][],int N, int M){
        System.out.println(nome);
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.format(m[i][j] +" ");
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
            int colori = scn.nextInt();
            int nGemme = scn.nextInt();

            char[][] campo = new char[N][N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    campo[i][j]='0';
                }
            }

            for (int i = 0; i < nGemme; i++) {
                int Y = scn.nextInt();
                int X = scn.nextInt();
                char c = scn.next().charAt(0);
                //System.out.println(Y+" "+X);
                campo[X][Y]=c;
            }
            

            miner solver = new miner();
            int risposta = solver.solve(N,colori,nGemme,campo);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}