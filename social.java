import java.util.*;
import java.io.*;
//import java.lang.*;

public class social {
    public String solve(int N, ArrayList<Integer> V) {

        // aggiungi codice...
        int[] risposta = new int[N];

        ArrayList<Boolean> bono = new ArrayList<>(N);
        
        for(int i=0;i<N;i++){
            bono.set(i, false);
        }

        boolean fine=false;

        while(!fine){
            int min = Collections.max(V);
            int indexMin = V.indexOf(min);
            for(int i=0;i<min;i++){
                if(i!=indexMin && bono.get(i)==false){
                    bono.set(i, true);
                    V.set(indexMin, -1);
                    risposta[i]=indexMin;
                }
            }
        }
        String ris="";
        for(int i=0;i<N;i++){
            ris=ris+risposta[i]+" ";
        }
        return ris;
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
            ArrayList<Integer> V = new ArrayList<Integer>(N);
            for (int i = 0; i < N; i++) {
                V.set(i, scn.nextInt());
            }

            social solver = new social();
            String risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}