import java.util.*;
import java.io.*;
//import java.lang.*;

public class ostacoli {
    public int solve(int N, int K, int[] C) {

        int risposta = 0;

        Arrays.sort(C);
     
        ArrayList<Integer> diff = new ArrayList<Integer>(C.length-1);
        for(int j=0;j<C.length-1;j++){
            diff.add(Math.abs(C[j]-C[j+1]));
        }

        Collections.sort(diff);

        for(int i=0;i<diff.size()-K+1;i++){
            risposta+=diff.get(i);
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
            int N = scn.nextInt();
            int K = scn.nextInt();

            int[] C = new int[N];
            for (int i = 0; i < N; i++) {
                C[i] = scn.nextInt();
            }

            ostacoli solver = new ostacoli();
            int risposta = solver.solve(N, K, C);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}