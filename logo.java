import java.util.*;
import java.io.*;
//import java.lang.*;

public class logo {
    public int solve(int N, int M, String[] I, String[] F) {

        // aggiungi codice...
        int risposta = 0;
        ArrayList<ArrayList<Integer>> posti = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                //System.out.println(I[i].charAt(j)+" "+F[i].charAt(j));
                if(I[i].charAt(j)!=F[i].charAt(j)){
                    ArrayList<Integer> p = new ArrayList<Integer>();
                    p.add(j);
                    p.add(i);
                    posti.add(p);
                    //System.out.println(p.get(0)+" "+p.get(1));
                }
            }
        }
        int massimoX=0;
        int minimoX=Integer.MAX_VALUE;
        int massimoY=0;
        int minimoY=Integer.MAX_VALUE;
        for(int i=0;i<posti.size();i++){
            if(posti.get(i).get(0)<minimoX){
                minimoX=posti.get(i).get(0);
            }
            if(posti.get(i).get(0)>massimoX){
                massimoX=posti.get(i).get(0);
            }
            if(posti.get(i).get(1)<minimoY){
                minimoY=posti.get(i).get(1);
            }
            if(posti.get(i).get(1)>massimoY){
                massimoY=posti.get(i).get(1);
            }
        }
        risposta=(Math.abs(massimoX-minimoX)+1)*(Math.abs(massimoY-minimoY)+1);

        return risposta;
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
            String[] I = new String[N];
            String[] F = new String[N];
            for(int i = 0; i < N; i++){
                I[i] = scn.next();
            }
            for(int i = 0; i < N; i++){
                F[i] = scn.next();
            }
            logo solver = new logo();
            int risposta = solver.solve(N, M, I, F);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}