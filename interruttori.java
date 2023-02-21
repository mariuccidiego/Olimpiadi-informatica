import java.util.*;
import java.io.*;

public class interruttori {
    class Lampadina{
        int id;
        boolean singolo=false;
        boolean visited=false;
        ArrayList<Lampadina> adiacenti = new ArrayList<>();
        public Lampadina(int i){
            id = i;
        }
    }

    void dump( String desc, Lampadina grafo[] ){
        System.out.println("====="+desc+"===============");
        for(int i=0; i<grafo.length; i++){
            System.out.print( " %2d %s  ".formatted(i, grafo[i].singolo ? "s" : " ") );
            for(Lampadina l: grafo[i].adiacenti){
                System.out.print(" "+l.id);
            }
            System.out.println();
        }
    }


    public String solve(int N, int A, int B, int[] S, int[] X, int[] Y) {
        ArrayList<Lampadina> nonSingoli = new ArrayList<Lampadina>();
        ArrayList<Lampadina> singoli = new ArrayList<Lampadina>();
        Lampadina grafo[] = new Lampadina[N];
        for(int i=0; i<N ; i++){
            grafo[i] = new Lampadina(i);
        }
        for(int i=0; i<A ; i++){
            grafo[ S[i] ].singolo = true;
            singoli.add(grafo[S[i]]);
        }
        for(int i=0; i<B ; i++){
            grafo[ X[i] ].adiacenti.add( grafo[ Y[i] ] );
            grafo[ Y[i] ].adiacenti.add( grafo[ X[i] ] );
            if(nonSingoli.size()==0){
                nonSingoli.add(grafo[ X[i] ]);
            }
            for(int j=0;j<nonSingoli.size();j++){
                System.out.println(nonSingoli.size());
                if(nonSingoli.get(j)!=grafo[X[i]] || nonSingoli.size()==0){
                    nonSingoli.add(grafo[ X[i] ]);
                    System.out.println("agg "+grafo[ X[i] ].id);
                }
                if(nonSingoli.get(j)!=grafo[Y[i]] || nonSingoli.size()==0){
                    nonSingoli.add(grafo[ Y[i] ]);
                    System.out.println("agg "+grafo[ Y[i] ].id);
                }
            }
            

        }

        //dump("grafo",grafo);
        Lampadina ultimo = new Lampadina(0);
        int count=0;
        while(nonSingoli.size()>2){
            int f=singoli.size();
            for(int i=0;i<f;i++){
                for(int j=0;j<singoli.get(i).adiacenti.size();j++){
                    if(singoli.get(i).adiacenti.get(j).visited==false){
                        singoli.get(i).adiacenti.get(j).visited=true;
                        nonSingoli.remove(singoli.get(i).adiacenti.get(j));
                        singoli.add(singoli.get(i).adiacenti.get(j));
                        ultimo=singoli.get(i).adiacenti.get(j);

                    }

                }
            }

            count++;
        }
        System.out.println(ultimo.id+" "+count);

        return "ciao";
    }
   

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            fin = new FileInputStream("input.txt");
            // fin = new FileInputStream("abc_quadri.input33.txt");
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
            int A = scn.nextInt();
            int B = scn.nextInt();

            int[] S = new int[A];
            int[] X = new int[B];
            int[] Y = new int[B];
            for (int i = 0; i < A; i++) {
                S[i] = scn.nextInt();
            }
            for (int i = 0; i < B; i++) {
                X[i] = scn.nextInt();
                Y[i] = scn.nextInt();
            }

            interruttori solver = new interruttori();
            String risposta = solver.solve(N, A, B, S, X, Y);


            prnt.format("Case #%d: %s\n", t, risposta);
            fout.flush();
        }

        prnt.close();
        scn.close();
    }
}