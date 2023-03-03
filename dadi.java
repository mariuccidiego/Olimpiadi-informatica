import java.util.*;
import java.io.*;
//import java.lang.*;

public class dadi {
    public int solve(int K, int A, int B, int C, int D) {

        // aggiungi codice...
        int risposta = 0;
        risposta=(A*1+B*2+C*3+D*4);
        int p=0;
        while(p<K){
            if(A>0){
                risposta-=1;
                risposta+=4;
                A-=1;
                p+=1;
            }else if(B>0){
                risposta-=2;
                risposta+=4;
                B-=1;
                p+=1;
            }else if(C>0){
                risposta-=3;
                risposta+=4;
                C-=1;
                p+=1;
            }else {
                risposta-=4;
                risposta+=4;
                D-=1;
                p+=1;
            }
        }


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
            int K = scn.nextInt();
            int A = scn.nextInt();
            int B = scn.nextInt();
            int C = scn.nextInt();
            int D = scn.nextInt();
            
            dadi solver = new dadi();
            int risposta = solver.solve(K, A, B, C, D);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}