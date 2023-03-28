import java.util.*;
import java.io.*;
//import java.lang.*;

public class finestrini {
    public int solve(int N, int[] L, int[] R) {

        int costo=0;

        int sc=ric(L, R, 0, 2, 0, true);
        int dc=ric(L, R, 0, 2, 0, false);

        return max(dc, sc);
    }

    int max(int dc, int sc){
        if(sc<dc){
            return sc;
        }else{
            return dc;
        }
    }

    int ric(int[] L, int[] R, int posizione, int quantoDalCambiare, int costo, boolean verso){
        if(verso){
            costo+=1;
        }else{
            costo+=1;
        }

        if(posizione>=R.length-1){
            return costo;
        }
        int dc=0;
        int sc=0;
        if(quantoDalCambiare==0){
            if(verso==true){
                return ric(L, R, posizione+1, 2, costo, !verso);
            }else{
                return ric(L, R, posizione+1, 2, costo, !verso);
            }
        }else{
            if(verso==true){
                sc=ric(L, R, posizione+1, quantoDalCambiare-1, costo, verso);
                dc=ric(L, R, posizione+1, 2, costo, !verso);
            }else{
                sc=ric(L, R, posizione+1, 2, costo, !verso);
                dc=ric(L, R, posizione+1, quantoDalCambiare-1, costo, verso);
            }
        }

        return costo+max(dc, sc);
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

            int[] L = new int[N];
            int[] R = new int[N];
            for (int i = 0; i < N; i++) {
                L[i] = scn.nextInt();
                R[i] = scn.nextInt();
            }
            System.out.println("-----------");
            finestrini solver = new finestrini();
            int risposta = solver.solve(N, L, R);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}