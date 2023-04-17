import java.util.*;
import java.io.*;
//import java.lang.*;

public class pesci {
    public static int solve(int n, int k) {
        // Scrivi il tuo codice qua, modificando il valore di ritorno con la risposta
        int uova=n;
        int risposta=n;
        while(uova>=k){
            int pesci=uova;
            uova=0;
            uova=pesci/k;

            risposta+=uova;
        }


        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin;
        OutputStream fout;
        // Lascia true per fare input / output da tastiera, cambia con false per farlo da file
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);
        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int n = scn.nextInt();
            int k = scn.nextInt();
            int result = solve(n, k);
            prnt.format("Case #%d: %d\n", t, result);
        }
        fout.flush();
        prnt.close();
        scn.close();
    }
}