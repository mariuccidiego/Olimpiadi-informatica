import java.util.*;
import java.io.*;

public class mcm {
    public long solve(int N, int[] V) {
        long risposta=V[0];
        

        ///int ind=1;
        for(int i=1; i<V.length;i++){
            risposta=(int)mcmF(risposta, V[i]);
        }
      


        return risposta;
    }
    long mcmF(long a, long b){
        long c;
        if(a<b){
            c=b;
            b=a;
            a=c;
        }
        long d;
        d=a*b;
        long minCom=d/mcd((int)a, (int)b);
        return minCom;
    }

    int mcd(int a, int b) {
        int r;
        while (b != 0) {
           
            r = a % b;
            a = b;
            b=r;
        }
        return a;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

        // se preferisci leggere e scrivere da file
        // ti basta decommentare le seguenti righe
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();

            int[] V = new int[N];
            for (int i = 0; i < N; i++) {
                V[i] = scn.nextInt();
            }

            mcm solver = new mcm();
            long risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
        
    }
}