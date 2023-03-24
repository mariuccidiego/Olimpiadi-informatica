import java.util.*;
import java.io.*;
//import java.lang.*;

public class casino {
    public int solve(int nSlot, int obbiettivo, int iniziale, int[][]slot) {

        // aggiungi codice...
        int risposta=0;
        int conto=iniziale;

        while(conto<obbiettivo){
            int indice= migliore(slot, conto);
            //System.out.println(migliore(slot, conto));
            conto+=slot[indice][1]-slot[indice][0];
            
            risposta++;
        }
        //System.out.println(migliore(slot, conto));

        return risposta;
    }
    int migliore(int[][] slot, int conto){
        int indiceMigliore=0;
        int maggioreGuadagno=0;
        for(int i=0;i<slot.length;i++){
            int guadagnio=slot[i][1]-slot[i][0];
            if(slot[i][0]<=conto){
                if(guadagnio>maggioreGuadagno){
                    maggioreGuadagno=guadagnio;
                    indiceMigliore=i;
                }
            }
        }

        return indiceMigliore;
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
            int nSlot= scn.nextInt();
            int obbiettivo = scn.nextInt();
            int iniziale = scn.nextInt();
            
            int[][] slot = new int[nSlot][2];

            for (int i = 0; i < nSlot; i++) {
                slot[i][0] = scn.nextInt();
                slot[i][1] = scn.nextInt();
                //System.out.println(i+" "+slot[i][0]+" "+slot[i][1]);
            }

            casino solver = new casino();
            int risposta = solver.solve(nSlot, obbiettivo, iniziale, slot);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}