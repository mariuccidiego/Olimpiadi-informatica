import java.util.*;
import java.io.*;
//import java.lang.*;

public class stack {
    public String solve(int N, int M, int Q, String S, char[] type, int[] A, int[] B) {
        // Aggiungi codice se vuoi

        String risposta="";
        ArrayList<Stack<String>> cestini = new ArrayList<Stack<String>>();
        
        for(int i=0;i<M;i++){
            Stack<String> cesto = new Stack<String>();
            cestini.add(cesto);
        }
        
        for(int i=0;i<N;i++){
            cestini.get(0).push(S.charAt(i)+"");
            //System.out.println(cestini.get(0).get(i));
        }
        
        for (int i = 0; i < Q; i++) {
            if (type[i] == 's') {
                //A[i]
                //B[i]
                String ele = cestini.get(A[i]).peek();
                cestini.get(B[i]).push(ele);
                cestini.get(A[i]).pop();
                //System.out.println(cestini.get(B[i]).peek());

            } else if (type[i] == 'c') {
                // Aggiungi altro codice...

                String ele = cestini.get(A[i]).get(B[i]);

                risposta=risposta+ele;
            }
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
            int M = scn.nextInt();
            int Q = scn.nextInt();

            String S = scn.next();

            char[] type = new char[Q];
            int[] A = new int[Q];
            int[] B = new int[Q];

            for (int i = 0; i < Q; i++) {
                type[i] = scn.next().charAt(0);
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
                //System.out.println(type[i] + " " + A[i] + " " + B[i]);
            }
            System.out.println("------");
            stack solver = new stack();
            String risposta = solver.solve(N, M, Q, S, type, A, B);

            prnt.format("Case #%d: %s", t, risposta);
            prnt.println();
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}