import java.util.*;
import java.io.*;
//import java.lang.*;

public class soldatini {
    static int numPrima=0;
    static int numDopo=0;
    public int solve(int N, String S) {

        // aggiungi codice...
        int risposta = 0;
        //String tolti=S;
        
        char[] c= S.toCharArray();
        int count=0;
        boolean vero=false;
        while(count<N && vero==false){
            if(c[count]=='0'){
                vero=true;
            }
            count++;
        }
        if(vero==false){
            return N;
        }

        for(int i=0;i<N;i++){
            if(c[i]=='0'){
                numPrima=0;
                numDopo=0;
                nPrima(c, i);
                nDopo(c, i);

                if(numPrima+numDopo+1>risposta){
                    risposta=numPrima+numDopo+1;
                }
            }

        }

        return risposta;
    }
    
    static void nPrima(char[] c,int num){

        if(num==0){
            
        }else{
            if(c[num-1]=='0'){

            }else{
                numPrima+=1;
                nPrima(c, num-1);
            }
            
        }
    }
    static void nDopo(char[] c,int num){

        if(num==c.length-1){
            
        }else{
            if(c[num+1]=='0'){

            }else{
                numDopo+=1;
                nDopo(c, num+1);
            }
            
        }
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
            String S = "";
            for(int i=0;i<N;i++){
                S+=scn.nextInt()+"";
            }
            soldatini solver = new soldatini();
            int risposta = solver.solve(N, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}