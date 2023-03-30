import java.util.*;
import java.io.*;
//import java.lang.*;

public class finestrini1 {


    public int solve(int N, int[] L, int[] R) {
        int sc = ric(L[0]+"",L, R, 0, 1, 0, true);
        int dc = ric(R[0]+"",L, R, 0, 1, 0, false);
        return Math.min(dc, sc);
    }
    
    int ric(String s,int[] L, int[] R, int posizione, int quantoDalCambiare, int costo, boolean verso) {
        if(verso){
            if(quantoDalCambiare==0){
                if(posizione==1){
                    costo+=L[posizione];
                }else{
                    costo+=L[posizione];
                }
            }else{
                costo+=L[posizione];
            }
        }else{
            if(quantoDalCambiare==0){
                if(posizione==1){
                    costo+=R[posizione];
                }else{
                    costo+=R[posizione];
                }
            }else{
                costo+=R[posizione];
            }
        }
    
        if (posizione == R.length - 1) {
            System.out.println(s+"   c="+costo);
            return costo;
        }
    
        int dc;
        int sc;
    
        if (quantoDalCambiare == 0) {
            if(verso){
                return ric(s+" "+R[posizione+1]+" ("+costo+"),",L, R, posizione + 1, 2, costo, !verso);
            }else{
                return ric(s+" "+L[posizione+1]+" ("+costo+"),",L, R, posizione + 1, 2, costo, !verso);
            }
            
        } else {
            if (verso) {
                sc = ric(s+" "+L[posizione+1]+" ("+costo+"),",L, R, posizione + 1, quantoDalCambiare - 1, costo, verso);
                dc = ric(s+" "+R[posizione+1]+" ("+costo+"),",L, R, posizione + 1, 2, costo, !verso);
            } else {
                sc = ric(s+" "+L[posizione+1]+" ("+costo+"),",L, R, posizione + 1, 2, costo, !verso);
                dc = ric(s+" "+R[posizione+1]+" ("+costo+"),",L, R, posizione + 1, quantoDalCambiare - 1, costo, verso);
            }
            return Math.min(dc, sc);
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

            int[] L = new int[N];
            int[] R = new int[N];
            for (int i = 0; i < N; i++) {
                L[i] = scn.nextInt();
                R[i] = scn.nextInt();
            }
            System.out.println("-----------");
            finestrini1 solver = new finestrini1();
            int risposta = solver.solve(N, L, R);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}