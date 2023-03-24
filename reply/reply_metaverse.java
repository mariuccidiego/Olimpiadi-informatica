import java.util.*;
import java.io.*;
//import java.lang.*;

public class reply_metaverse {
    public int solve(int X, int Y, int[] V) {

        // aggiungi codice...
        int risposta=1;
        int quantiG = V[0];

        for(int i=1; i<V.length;i++){
            quantiG=(int)mcm(quantiG, V[i]);
        

        }
        quantiG+=1;

        risposta+=(int)X/quantiG;

        return risposta;
    }
    int mcm(int a, int b){
        int c;
        if(a<b){
            c=b;
            b=a;
            a=c;
        }
        int d;
        d=a*b;
        int minCom=d/mcd((int)a, (int)b);
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
            int Y = scn.nextInt();
            int X = scn.nextInt();

            int[] V = new int[Y];

            for (int i = 0; i < Y; i++) {
                V[i] = scn.nextInt();
            }

            reply_metaverse solver = new reply_metaverse();
            int risposta = solver.solve(X, Y, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}