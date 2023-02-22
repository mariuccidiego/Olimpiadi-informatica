import java.util.*;
import java.io.*;

public class download {
    public int[] solve(int N, int F, int C) {
        int[] risposta={0,0};
        int spazioRim=N;
        
        while(spazioRim>=C || spazioRim>=F){
            if(F<=spazioRim){
                risposta[0]+=1;
                spazioRim-=F;
            }else{
                risposta[1]+=1;
                spazioRim-=C;
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
        for(int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int F = scn.nextInt();
            int C = scn.nextInt();

            download solver = new download();
            int[] risposta = solver.solve(N, F, C);

            prnt.format("Case #%d: %d %d\n", t, risposta[0],risposta[1]);
            fout.flush();
        }
        prnt.close();
        scn.close();
        
    }
}