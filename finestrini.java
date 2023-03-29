import java.util.*;
import java.io.*;
//import java.lang.*;

public class finestrini {

    int[][] mem;
    int[][] mem2;

    public int solve(int N, int[] L, int[] R) {

        mem = new int[2][N];
        mem2 = new int[2][N];
        
        for(int i=0;i<2;i++){
            for(int j=0;j<N;j++){
                mem[i][j]=-1;
                mem2[i][j]=-1;
            }
            
        }
        int sc=ric("",L, R, 0, 2, 0, true);
        System.out.println("°°°°°");
        int dc=ric2("",L, R, 0, 2, 0, false);
        System.out.println(sc);
        System.out.println(dc);

        /*for(int i=0;i<N;i++){
            System.out.println(mem[i]);
        }*/

        return min(dc, sc);
    }

    int min(int dc, int sc){
        if(sc<dc){
            return sc;
        }else{
            return dc;
        }
    }

    int ric(String s,int[] L, int[] R, int posizione, int quantoDalCambiare, int costo, boolean verso){
        
        if(verso){
            costo+=L[posizione];
        }else{
            costo+=R[posizione];
        }
        System.out.println(s+"p="+posizione+"   c="+costo+"   v="+verso);
       // System.out.println(costo);
        if(posizione>=R.length-1){
            return costo;
        }
        if(verso){
            if(mem[0][posizione]!=-1){
                return mem[0][posizione];
            }
        }else{
            if(mem[1][posizione]!=-1){
                return mem[1][posizione];
            }
        }
        
        int dc=0;
        int sc=0;
        if(quantoDalCambiare==0){
            if(verso==true){
                //System.out.println("fine");
                return ric(s+" | ",L, R, posizione+1, 2, costo, !verso);
            }else{
                //System.out.println("fine");
                return ric(s+" | ",L, R, posizione+1, 2, costo, !verso);
            }
        }else{
            if(verso==true){
                //System.out.println("fine");
                sc=ric(s+" | ",L, R, posizione+1, quantoDalCambiare-1, costo, verso);
                dc=ric(s+" | ",L, R, posizione+1, 2, costo, !verso);
            }else{
                //S//ystem.out.println("fine");
                sc=ric(s+" | ",L, R, posizione+1, 2, costo, !verso);
                dc=ric(s+" | ",L, R, posizione+1, quantoDalCambiare-1, costo, verso);
            }
        }
        if(verso){
            mem[0][posizione]=min(dc, sc);
            //System.out.println("fine");
            return mem[0][posizione];
        }else{
            mem[1][posizione]=min(dc, sc);
            //System.out.println("fine");
            return mem[1][posizione];
        }

        







    }
    int ric2(String s,int[] L, int[] R, int posizione, int quantoDalCambiare, int costo, boolean verso){
        
        if(verso){
            costo+=L[posizione];
        }else{
            costo+=R[posizione];
        }
        System.out.println(s+"p="+posizione+"   c="+costo+"   v="+verso+" "+mem2[0][posizione]);
       // System.out.println(costo);
        if(posizione>=R.length-1){
            return costo;
        }
        if(verso){
            if(mem2[0][posizione]!=-1){
                //System.out.println("1");
                return mem2[0][posizione];
            }
        }else{
            if(mem2[1][posizione]!=-1){
                //System.out.println("2");
                return mem2[1][posizione];
            }
        }
        
        int dc=0;
        int sc=0;
        if(quantoDalCambiare==0){
            //System.out.println("3");
            if(verso==true){
                return ric2(s+" | ",L, R, posizione+1, 2, costo, !verso);
            }else{
                return ric2(s+" | ",L, R, posizione+1, 2, costo, !verso);
            }
        }else{
            //S/ystem.out.println("4");
            if(verso==true){
                sc=ric2(s+" | ",L, R, posizione+1, quantoDalCambiare-1, costo, verso);
                dc=ric2(s+" | ",L, R, posizione+1, 2, costo, !verso);
            }else{
                sc=ric2(s+" | ",L, R, posizione+1, 2, costo, !verso);
                dc=ric2(s+" | ",L, R, posizione+1, quantoDalCambiare-1, costo, verso);
            }
        }
        if(verso){
            mem2[0][posizione]=min(dc, sc);
            //System.out.println("5");
            return mem2[0][posizione];
        }else{
            mem2[1][posizione]=min(dc, sc);
            //System.out.println("6");
            return mem2[1][posizione];
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
            finestrini solver = new finestrini();
            int risposta = solver.solve(N, L, R);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}