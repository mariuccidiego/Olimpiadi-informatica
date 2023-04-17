import java.util.*;
import java.io.*;
//import java.lang.*;

public class multicore {
    static int massimo=0;
    public int solve(int N, int B, int[][] V) {

        // aggiungi codice...
        int risposta = 0;
        massimo=0;
        ArrayList<Integer> presi = new ArrayList<Integer>();

        for(int i=0;i<N;i++){
            massimo=V[i][0];
            presi.add(i);
            controllaSotto(V, presi, V[i][1], B, V[i][0]);
            presi.remove(presi.size()-1);
            //System.out.println(massimo);
            if(risposta<massimo){
                risposta=massimo;
            }
        }
        System.out.println("--------------");
        return risposta;
    }
    
    static void controllaSotto(int[][] V, ArrayList<Integer> presi, int spesi, int B, int coreFinoAde){
        
        for(int i=0; i<V.length;i++){
            for(int j=0;j<presi.size();j++){
                if(i!=presi.get(j)){
                    if(V[i][1]+spesi<=B){
                        //System.out.println("massimo="+massimo);
                        //System.out.println("spesi="+(V[i][1]+spesi)+"  n="+presi.size());
                        presi.add(i);
                        if(V[i][0]+coreFinoAde>massimo){
                            massimo=V[i][0]+coreFinoAde;
                            
                        }
                        controllaSotto(V, presi, spesi+V[i][1], B, coreFinoAde+V[i][0]);
                        presi.remove(presi.size()-1);
                    }
                }
            }
        }
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
            int B = scn.nextInt();

            //inserisce tutti i dati di una riga in un array
            int[][] V = new int[N][2];
            for (int i = 0; i < N; i++) {
                V[i][0] = scn.nextInt();
                V[i][1] = scn.nextInt();
            }

            multicore solver = new multicore();
            int risposta = solver.solve(N, B, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}