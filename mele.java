import java.util.*;
import java.io.*;
//import java.lang.*;

public class mele {
    public int solve(int N, int M, int C) {

        // aggiungi codice...
        int risposta = 0;
        if(N*M>=C){
            risposta=C;
        }else{
            risposta=M*N;
        }
        

        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // true = lettura file
        // false = input da tastiera
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

        // T = numero di casi
        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int N = scn.nextInt();
            int M = scn.nextInt();
            int C = scn.nextInt();

            mele solver = new mele();
            int risposta = solver.solve(N, M, C);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}