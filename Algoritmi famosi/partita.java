import java.util.*;
import java.io.*;
//import java.lang.*;

public class partita {
    public int solve(int N, int K, int[] R) {
        System.out.println("----------");
        int gemme = 0;
        
        Giocatore pino = new Giocatore(K);

        for(int i=0;i<N;i++){
            int nS=pino.soldatini;
            boolean hoVinto=possoVincere(pino, R, i);
            //System.out.println(hoVinto);
            if(hoVinto==false){
                if(pino.soldatini==0){
                    return -1;
                }
                pino.soldatini=nS;
                pino.haiPerso=false;
                if(R[i]!=0){
                    pino.prendiRinforsi(R[i]);
                    gemme+=1;
                    //System.out.println("# rinforso = "+gemme+"  in poss="+i);
                }
                
                
            }else{
                return gemme;
            }
            pino.avanza();
        }
        System.out.println("----------");
        return gemme;
    }
    static boolean possoVincere(Giocatore p, int[] R, int pos){
        //boolean ans=false;
        Giocatore pino=p;
        //System.out.println("[posizione="+pos);
        //System.out.println("nSoldati="+pino.soldatini+"   n="+(R.length-pos));
        if(R.length-pos<pino.soldatini){
            return true;
        }else{
            return false;
        }

    }

    static class Giocatore{
        boolean haiPerso=false;
        int soldatini=0;
        public Giocatore(int soldatini){

            this.soldatini = soldatini;
        }
        public void prendiRinforsi(int nSoldati){

            soldatini+=nSoldati;
        }

        public void avanza(){
            soldatini-=1;
            if(soldatini<1){
                haiPerso=true;
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Se preferisci leggere e scrivere da file
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
            int K = scn.nextInt();

            int[] R = new int[N];
            for (int i = 0; i < N; i++) {
                R[i]=scn.nextInt();
            }
            
            partita solver = new partita();
            int risposta = solver.solve(N, K, R);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }

        scn.close();
        prnt.close();
    }
}