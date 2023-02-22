import java.util.*;
import java.io.*;
//import java.lang.*;

public class ois_truffa {
    public int solve(int N, int[] V) {

        // aggiungi codice...a
        int risposta = 0;

        int sum=0;

        for(int i=0;i<N;i++){
            sum=sum+V[i];
        }

        //Arrays.sort(V);

        boolean fine=true;

        int num = 0;

        for(int i=0;i<N && fine;i++){
            int index=0;
            int min=Integer.MAX_VALUE;
            for (int j = 0; j < V.length; j++) {
                if (min < V[j]) {
                    min= V[j];
                    index = j;
                }
            }
            //int min = Arrays.stream(V).min().getAsInt();
            sum=sum+(Math.abs(V[index])*2);
            V[index]=0;
            if(sum>0){
                risposta+=1;
                fine=false;
            }else{
                num+=1;
            }
        }

        risposta=num+1;
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

        // N = numero di dati in una riga
        int N = scn.nextInt();

        //inserisce tutti i dati di una riga in un array
        int[] V = new int[N];
        for (int i = 0; i < N; i++) {
            V[i] = scn.nextInt();
        }

        ois_truffa solver = new ois_truffa();
        int risposta = solver.solve(N, V);

        prnt.format(""+risposta);
        fout.flush();    
            
        prnt.close();
        scn.close();
    }
}