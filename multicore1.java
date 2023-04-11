import java.util.*;
import java.io.*;
//import java.lang.*;

public class multicore1 {

    int mem[][];
    int lista[][];
    int budget;
    int n;

    public int solve(int N, int B, int[][] V) {

        mem = new int[N+1][N+1];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                mem[i][j]=-1;
            }
        }

        lista = new int[V.length][V[0].length];
        for (int i = 0; i < V.length; i++) {
            for (int j = 0; j < 2; j++) {
                lista[i][j] = V[i][j];
            }
        }
        budget = B;
        n = N;

        //dump(null, lista, N, 2);

        int max = 0;
        for (int i = 0; i < V.length; i++) {
            int x = ric("",i, lista[i][1],0);
            if (x > max) {
                max = x;
            }
        }
        

        System.out.println("---------");
        return max;
    }

    

    int ric(String s,int posizione, int soldiSpesi, int salti) {

        
        int nCore=0;
        if (soldiSpesi > budget) {
            return nCore;
        } else {
            nCore = lista[posizione][0];
            
        }
        /*if(mem[posizione][salti]!=-1){
            //System.out.println(mem[posizione][0]+" == "+(soldiSpesi+lista[possPrev][1]));
            
            return mem[posizione][salti];
        }*/
        

        int max = 0;
        if (posizione != lista.length - 1) {

            for (int i = posizione; i < lista.length - 1; i++) {
                int x = ric(s+" | ",i + 1, soldiSpesi + lista[i + 1][1],salti+1);
                if (x > max) {
                    max = x;
                }
            }
            nCore+=max;
        }
        //System.out.println("salvo = "+nCore+"  "+soldiSpesi);
        mem[posizione][salti]=nCore;
        return nCore;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // true = lettura file
        // false = input da tastiera
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

        // T = numero di casi
        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int N = scn.nextInt();
            int B = scn.nextInt();

            // inserisce tutti i dati di una riga in un array
            int[][] V = new int[N][2];
            for (int i = 0; i < N; i++) {
                V[i][0] = scn.nextInt();
                V[i][1] = scn.nextInt();
            }

            multicore1 solver = new multicore1();
            int risposta = solver.solve(N, B, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}