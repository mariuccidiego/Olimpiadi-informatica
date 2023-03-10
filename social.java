import java.util.*;
import java.io.*;
//import java.lang.*;

public class social {
    public String solve(int N, ArrayList<Integer> V) {

        // aggiungi codice...
        int[] risposta = new int[N];

        ArrayList<Boolean> bono = new ArrayList<>();
        
        for(int i=0;i<N;i++){
            bono.add(false);
        }
        
        if(V.stream().allMatch(val -> val == 1)){
            for(int i=0;i<N-1;i++){
                risposta[i]=i+1;
            }
            risposta[N-1]=0;
            String ris="";
        for(int i=0;i<N;i++){
            ris=ris+risposta[i]+" ";
            System.out.println("4------------");
        }
        System.out.println(ris);
        return ris;
        } 

        boolean fine=false;
        while(!fine){
            int max = Collections.max(V);
            int indexMax = V.indexOf(max);
            for(int i=0, j=0;i<N && j<max;i++){
                //System.out.println();
                if(i!=indexMax && bono.get(i)==false){
                    j++;
                    System.out.println(max+" "+indexMax);
                    System.out.println(i);
                    bono.set(i, true);
                    V.set(indexMax, -1);
                    risposta[i]=indexMax;
                }
                //System.out.println("1------------"+(i)+" "+risposta[i]+" "+(indexMax-i));
            }
            //System.out.println("4------------");
            if(bono.stream().allMatch(val -> val == true)){
                fine=true;
            }  
        }
        String ris="";
        for(int i=0;i<N;i++){
            ris=ris+risposta[i]+" ";
            System.out.println("4------------");
        }
        System.out.println(ris);
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
            ArrayList<Integer> V = new ArrayList<Integer>();
            for (int i = 0; i < N; i++) {
                V.add(scn.nextInt());
                //System.out.println(V.get(i));
            }

            social solver = new social();
            String risposta = solver.solve(N, V);

            prnt.format("Case #"+t+": "+ risposta+"\n");
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}