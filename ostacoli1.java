import java.util.*;
import java.io.*;

public class ostacoli1 {
    Ostacolo[] gara;
    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {

        

        gara=new Ostacolo[N];

        for(int i=0;i<N;i++){
            gara[i]=new Ostacolo(X[i], P[i], S[i]);
        }
        int posizione=0;
        int punteggio=0;
        int ostacoliArivati=0;
        
        /*for(int i=0;i<gara.length;i++){
            System.out.println("secondo = "+i+"   "+gara[ostacoliArivati].secondi);
            if(gara[i].secondi>=gara[i].distanza){
                punteggio+=gara[i].punti;
                int x = ric(gara, posizione, ostacoliArivati, punteggio);
                if(gara[i+1].secondi>=gara[i+1].distanza){
                    punteggio+=gara[i+i].punti;
                    int y = ric(gara, posizione, ostacoliArivati, punteggio);
                }
            }
        }*/

        System.out.println("------------------------");
        return ric(gara, posizione, ostacoliArivati, punteggio, ostacoliArivati, 0);
    }
    int ric(Ostacolo[] gara, int posizione, int ostacoliArivati, int punteggio, int massimo, int secondiPassati){
        System.out.println(punteggio);
        System.out.println("sto controllando il n="+ostacoliArivati);
        if(secondiPassati>gara[gara.length-1].secondi){
            System.out.println("sono arrivato al limite");
            return 0;
        }
        
        if(ostacoliArivati>=gara.length-1){
            return 0;
        }
        
        for(int i=0;i<gara.length-1;i++){
            System.out.println("--- "+i);
            int x=0;           
            if(Math.abs(posizione-gara[i+1].distanza)<=gara[i+1].distanza){
                
                x=gara[i+1].punti;
                System.out.println("aggiungo i punti="+gara[i+1].punti+"  punteggio="+punteggio);
                secondiPassati+=gara[i+1].distanza;
                x+=ric(gara, posizione, ostacoliArivati+1, punteggio, massimo, secondiPassati);
            }else{
                System.out.println("non è raggiungibile");
            }
            System.out.println(Math.abs(posizione-gara[i+1].distanza)+" < "  +gara[i+1].distanza);
            
            if(x>punteggio){
                punteggio=x;
            }
            System.out.println("x="+x+"  punteggio="+punteggio);
        }
        
        /*
        if(Math.abs(posizione-gara[ostacoliArivati+1].distanza)<gara[ostacoliArivati+1].distanza){
            ostacoliArivati+=1;
            posizione=gara[ostacoliArivati].distanza;
            punteggio+=gara[ostacoliArivati].punti;
            secondiPassati=gara[ostacoliArivati].secondi;
            int x=ric(gara, posizione, ostacoliArivati, punteggio, massimo, secondiPassati);
            if(x>punteggio){
                punteggio=x;
            }
        }else{
            return punteggio;
        }
        */
        return punteggio;
    }
/* 
    int ric2(Ostacolo[] gara, int posizione, int ostacoliArivati, int punteggio){
        int x=punteggio;
        int y=punteggio;
        if(gara[ostacoliArivati].secondi>=gara[ostacoliArivati].distanza){
            x+=gara[ostacoliArivati].punti;
            x += ric(gara, posizione, ostacoliArivati,punteggio);
            if(gara[ostacoliArivati+1].secondi>=gara[ostacoliArivati+1].distanza){
                y+=gara[ostacoliArivati].punti;
                y = ric(gara, posizione, ostacoliArivati, punteggio);
            }
        }
        if(x<y){
            return y;
        }else{
            return x;
        }
        
    }
*/
    class Ostacolo implements Comparable<Ostacolo>{
        int distanza=0;
        int punti=0;
        int secondi=0;

        public Ostacolo(int distanza, int punti, int secondi){
            this.distanza=distanza;
            this.punti=punti;
            this.secondi=secondi;
        }

        @Override
        public int compareTo(Ostacolo o) {
            return this.secondi - o.secondi;
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
            int L = scn.nextInt();
            int D = scn.nextInt();

            int[] X = new int[N];
            int[] P = new int[N];
            int[] S = new int[N];
            for (int i = 0; i < N; i++) {
                X[i] = scn.nextInt();
                P[i] = scn.nextInt();
                S[i] = scn.nextInt();
            }

            ostacoli1 solver = new ostacoli1();
            int risposta = solver.solve(N, L, D, X, P, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}