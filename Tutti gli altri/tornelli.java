import java.util.*;
import java.io.*;
//import java.lang.*;

public class tornelli {
    public int solve(int N, ArrayList<Integer> V) {
        // aggiungi codice...
        int risposta = 0;

        while(V.contains(+1)||V.contains(-1)){
            boolean positivo=false;

            if(V.get(0)==+1){
                positivo=true;
            }

            for(int i=1;i<V.size();i++){
                if(positivo==true){
                    if(V.get(i)==-1){
                        V.remove(i);
                        positivo=false;
                        i--;
                    }
                    
                }else{
                    if(V.get(i)==+1){
                        V.remove(i);
                        positivo=true;
                        i--;
                    }
                }
            }
            V.remove(0);
            risposta++;
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

            tornelli solver = new tornelli();
            int risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }

        prnt.close();
        scn.close();
    }
}