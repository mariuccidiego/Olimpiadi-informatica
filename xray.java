import java.util.*;
import java.io.*;
//import java.lang.*;

public class xray {
    public int solve(int N, int[] V) {

        // aggiungi codice...
        int risposta = 0;

        boolean fine=false;
        while(!fine){
            int min=0;
            for(int i=0;i<=N;i++){
                if(i==N){
                    if(V[N-2]==0){
                        risposta+=V[N-1];
                        V[N-1]=0;
                    }          
                }else{
                    if(V[i]==0){
                        if(min!=0){
                            risposta+=min;
                            for(int j=0;j<i;j++){
                                if(V[j]!=0){
                                    V[j]-=min;
                                    //System.out.println(V[i]+" - "+min);
                                }
                            }
                            break;
                        }
                    }else if(i==N-1){
                        if(V[i]<min){
                            min=V[i];
                        }
                        if(min!=0){
                            
                            risposta+=min;
                            for(int j=0;j<N;j++){
                                if(V[j]!=0){
                                    V[j]-=min;
                                    //System.out.println(V[i]+" - "+min);
                                }
                            }
                            break;
                        }
                    }else{
                        if(min!=0){
                            if(V[i]<min){
                                min=V[i];
                                //System.out.println("min: "+min);
                            }
                        }else{
                            min=V[i];
                        }
                        
                    }
                }
                
                //System.out.println(min);
            }

            int somma=0;
            for(int i=0;i<N;i++){
                somma+=V[i];
                //System.out.println("-"+i+": "+V[i]+"   s="+somma);
            }
            if(somma==0){
                fine=true;
            }
            System.out.println("-------");
        }

        
        System.out.println("######################################");
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

        // T = numero di casi
        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int N = scn.nextInt();

            //inserisce tutti i dati di una riga in un array
            int[] V = new int[N];
            for (int i = 0; i < N; i++) {
                V[i] = scn.nextInt();
            }

            xray solver = new xray();
            int risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}