import java.util.*;
import java.io.*;

public class palindromoGPT {
    static Nodo grafo[];

    static class Nodo implements Comparable<Nodo> {
        int id;
        int costoPercorso;
        String parolaPalindroma="";
        Nodo padre;
        ArrayList<Arco> archi;

        public Nodo(int id) {
            this.id = id;
            archi = new ArrayList<>();
        }

        @Override
        public int compareTo(Nodo o) {
            return this.costoPercorso - o.costoPercorso;
        }
    }

    static class Arco {
        Nodo a;
        int peso = 1;
        char lettera;

        public Arco(int da, int a, char lettera) {
            this.a = grafo[a];
            this.lettera = lettera;
        }
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public int solve(int N, int M, int X, int Y, int[] A, int[] B, char[] L) {

        grafo = new Nodo[N];
        for (int i = 0; i < N; i++) {
            grafo[i] = new Nodo(i);
        }

        for (int i = 0; i < M; i++) {
            grafo[A[i]].archi.add(new Arco(A[i], B[i], L[i]));
            grafo[B[i]].archi.add(new Arco(B[i], A[i], L[i]));
        }

        camminiMinimi(grafo[X]);


        if (isPalindrome("aacaa")) {
            return grafo[Y].costoPercorso;
        } else {
            return -1;
        }
    }
    String parola="";
    void camminiMinimi(Nodo x){
        for(Nodo n: grafo){
            n.costoPercorso = Integer.MAX_VALUE;
            n.padre = null;
        }

        x.costoPercorso = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(x);
        
        while(!s.isEmpty()){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                Nodo vicino = a.a;
                vicino.parolaPalindroma=vicino.parolaPalindroma+a.lettera;
                int nDist = attuale.costoPercorso + a.peso;
                if(nDist < vicino.costoPercorso){
                    vicino.costoPercorso = nDist;
                    vicino.padre = attuale;
                    s.add(vicino);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if (input_from_file) {
            fin = new FileInputStream("input.txt");
            fout = new FileOutputStream("output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();
            int X = scn.nextInt();
            int Y = scn.nextInt();

            int[] A = new int[M];
            int[] B = new int[M];
            char[] L = new char[M];
            for (int i = 0; i < M; i++) {
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
                L[i] = scn.next().charAt(0);
            }

            palindromoGPT solver = new palindromoGPT();
            int risposta = solver.solve(N, M, X, Y, A, B, L);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}