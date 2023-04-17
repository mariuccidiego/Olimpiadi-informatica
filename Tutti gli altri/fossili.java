import java.util.*;
import java.io.*;
//import java.lang.*;

public class fossili {
    public int solve(int a1, int a2, int b1, int b2, int c1, int c2) {

        int min=a1;
        int max=a2;

        if(min<b1){
            min=b1;
        }
        if(min<c1){
            min=c1;
        }

        if(max>b2){
            max=b2;
        }
        if(max>c2){
            max=c2;
        }

        if((max-min)<0){
            return 0;
        }

        return max-min;
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
        for (int t = 1; t <= T; t++) {
            int a1 = scn.nextInt();
            int a2 = scn.nextInt();
            int b1 = scn.nextInt();
            int b2 = scn.nextInt();
            int c1 = scn.nextInt();
            int c2 = scn.nextInt();
            fossili solver = new fossili();
            int risposta = solver.solve(a1, a2, b1, b2, c1, c2);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }

        prnt.close();
        scn.close();
    }
}