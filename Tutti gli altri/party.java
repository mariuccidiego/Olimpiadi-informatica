import java.util.*;
import java.io.*;
//import java.lang.*;

public class party {
    public int solve(int N, ArrayList<Integer> V) {
        // aggiungi codice...
        int risposta = 0;

        for(int i=0;i<N;i++){
            if(V.get(i)>0){
                risposta+=V.get(i);
            }
        }
        
        return risposta;
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

            ArrayList<Integer> V = new ArrayList<Integer>();
            //int[] V = new int[N];
            for (int i = 0; i < N; i++) {
                V.add(scn.nextInt());
            }

            party solver = new party();
            int risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }

        prnt.close();
        scn.close();
    }
}