import java.util.*;
import java.io.*;
//import java.lang.*;

public class antivirus {
    public int[] solve(int[]D, int N, String[] V) {

        // aggiungi codice...
        int[] risposta = new int[4];
        boolean stop=false;
        for(int k=0;k<D[0]+1-N && !stop;k++){
            char[] c = new char[N];

            for(int i=0;i<c.length;i++){
                c[i]=V[0].charAt(k+i);

            }
            //String inizio="";
            String fine="";
            int doveArrivati=1;
            for(int i=0;i<4 && !stop && i<doveArrivati;i++){
                for(int j=0;j<D[i]+1-N && !stop;j++){
                    //String inizio=String.valueOf(c);
                    char[] pFin = new char[N];

                    for(int cont=0;cont<pFin.length;cont++){
                        pFin[cont]=V[i].charAt(j+cont);
                    }
                    //String fine=String.valueOf(pFin);
                    //System.out.println(inizio+" "+fine);
                    if((String.valueOf(pFin)).equals(String.valueOf(c))){
                        if(i==0){
                            fine=String.valueOf(c);
                            risposta[i]=j;
                            System.out.println(i+" "+j+" "+String.valueOf(pFin)+" = "+fine+"    ");
                            doveArrivati=2;
                        }else if(String.valueOf(c).equals(fine)){
                            if(i==3){
                                fine=String.valueOf(c);
                                risposta[i]=j;
                                System.out.println(i+" "+j+" "+String.valueOf(pFin)+" = "+fine+"    "+0);
                                doveArrivati=i+1;
                                stop=true;
                                System.out.println("stop");
                                //break;
                            }else{
                                fine=String.valueOf(c);
                                risposta[i]=j;
                                System.out.println(i+" "+j+" "+String.valueOf(pFin)+" = "+fine+"    "+0);
                                doveArrivati=i+2;
                            }
                            
                        }
                        //risposta[i]=j;
                        //System.out.println(i+" "+inizio+" = "+fine);

                    }
                }
            }
            
        }  
        System.out.println("----------------");
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
            int[] D = new int[4];
            D[0]=scn.nextInt();
            D[1]=scn.nextInt();
            D[2]=scn.nextInt();
            D[3]=scn.nextInt();

            int N = scn.nextInt();

            //inserisce tutti i dati di una riga in un array
            String[] V = new String[4];
            for (int i = 0; i < 4; i++) {
                V[i] = scn.next();
            }

            antivirus solver = new antivirus();
            int[] risposta = solver.solve(D, N, V);

            prnt.format("Case #%d: %d %d %d %d\n", t, risposta[0],risposta[1],risposta[2],risposta[3]);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}