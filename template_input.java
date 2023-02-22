import java.util.*;
import java.io.*;
//import java.lang.*;

public class template_input {
    public int solve(int N, int[] V) {

        // aggiungi codice...
        int risposta = 42;

        

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

            //inserisce tutti i dati di una riga in un array
            int[] V = new int[N];
            for (int i = 0; i < N; i++) {
                V[i] = scn.nextInt();
            }

            template_input solver = new template_input();
            int risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}