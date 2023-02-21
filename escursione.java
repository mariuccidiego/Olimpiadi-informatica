import java.util.*;

import javafx.beans.property.FloatPropertyBase;

import java.io.*;
//import java.lang.*;

public class escursione {
    public int solve(int X, int Y, int[][] V) {

        // aggiungi codice...
        int risposta = 42;

        Vertice[][] Grafo = new Vertice[X][Y];

        Vertice[]GrafNonMat = new Vertice[X*Y];

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                Vertice g = new Vertice(V[i][j]);
                Grafo[i][j] = g;
            }
        }

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (j < Y - 1) {
                    Grafo[i][j].adiacenti.add(Grafo[i][j + 1]);
                    Grafo[i][j + 1].adiacenti.add(Grafo[i][j]);

                }
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (j < X - 1) {
                    // System.out.println(j+"<"+X+"----"+i);
                    Grafo[j][i].adiacenti.add(Grafo[j + 1][i]);
                    Grafo[j + 1][i].adiacenti.add(Grafo[j][i]);
                    // System.out.println(Grafo[j+1][i].id);
                }
            }
        }

        /*
         * dump
         * for (int i = 0; i < X; i++) {
         * for(int j=0;j<Y;j++){
         * for(int k=0;k<Grafo[i][j].adiacenti.size();k++){
         * System.out.println("riga "+j+" colonna: "+i+" adiacente: "+
         * Grafo[i][j].id+" con "+Grafo[i][j].adiacenti.get(k).id);
         * }
         * }
         * }
         */

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                for (int k = 0; k < Grafo[i][j].adiacenti.size(); k++) {
                    // System.out.println("riga "+j+" colonna: "+i+" adiacente: "+ Grafo[i][j].id+"
                    // con "+Grafo[i][j].adiacenti.get(k).id);
                    Grafo[i][j].pesi.add(Math.abs(Grafo[i][j].id - Grafo[i][j].adiacenti.get(k).id));
                    // System.out.println("riga "+j+" colonna: "+i+" adiacente: "+ Grafo[i][j].id+"
                    // con "+Grafo[i][j].adiacenti.get(k).id+" peso: "+Grafo[i][j].pesi.get(k));
                }
            }
        }
        int c=0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {

                GrafNonMat[c]=Grafo[i][j];
                GrafNonMat[c].posizione=c;
                c++;
            }
        }
        System.out.println(dijkstra(GrafNonMat));

        return risposta;
    }

    int dijkstra(Vertice[]Grafo){
        

        int dist[] = new int[Grafo.length];
        boolean visited[] = new boolean[Grafo.length];
        for (int i = 0; i < Grafo.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[0]=0;

        int[] priorita = new int[dist.length];
        for(int i=0;i<dist.length;i++){
            priorita[i]=dist[i];
        }
        int u=0;
        int cont=0;
        boolean fine=false;
        while(cont<dist.length){
            //Arrays.min(priorita);
            int ind=indexMin(priorita);
            /*if(visited[indexMin(priorita)]==false){
                ind =indexMin(priorita);
            }*/
            boolean io=false;
            while(io){
                if(visited[indexMin(priorita)]==false){
                    ind =indexMin(priorita);
                }
            }
            visited[ind]=true;
            //int ind=priorita.indexOf(Collections.min(priorita));
            //priorita.remove(Collections.min(priorita));
            priorita[ind]=Integer.MAX_VALUE-1;
            System.out.println(ind+" "+Grafo[ind].id);
            System.out.println("--------");
            System.out.println("ind = "+priorita[ind]);

            for (int v = 0; v < Grafo[ind].adiacenti.size(); v++) {
                int alt=0;
                if(dist[ind]!=Integer.MAX_VALUE){
                    alt=dist[ind]+Grafo[ind].pesi.get(v);
                }else{
                    alt=Integer.MAX_VALUE;
                }
                
                System.out.println(v+" adiacenti = "+Grafo[ind].adiacenti.get(v).id+"   "+alt+"  -   "+dist[v]);
                if(alt<dist[v]){
                    dist[Grafo[ind].adiacenti.get(v).posizione]=alt;
                    priorita[Grafo[ind].adiacenti.get(v).posizione]=alt;
                    System.out.println("alt = "+alt);
                    //cont+=1;
                    System.out.println(cont);
                }
            }
            cont+=1;
        }
        /* 
        for (int count = 0; count < Grafo.length - 1; count++) {
            int u = minDistanza(dist, visited);
            visited[u] = true;
            System.out.println("--------");
            for(int i=0;i<dist.length;i++){
                //System.out.println(i+" - "+dist[i]);
                //System.out.println(Grafo[u].adiacenti.size());
            }
            
    
             Aggiorna le distanze dei nodi adiacenti al nodo scelto
            for (int v = 0; v < Grafo[u].adiacenti.size(); v++) {
                
                if (!visited[v] && Grafo[u].adiacenti.get(v).id != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + Grafo[u].adiacenti.get(v).id < dist[v])
                    for(int i=0;i<Grafo[u].adiacenti.size();i++){
                        if()
                    }
                    System.out.println(Grafo[u].adiacenti.get(v).id+" "+dist[u]+"#############");    
                    dist[v] = dist[u] + Grafo[u].adiacenti.get(v).id;
                    
            }
        }*/
        for(int i=0;i<Grafo.length;i++){
            System.out.println("-  "+dist[i]);
        }

        return dist[Grafo.length-1];
    }
    int minDistanza(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        //System.out.println("lung="+visited.length);
    
        for (int v = 0; v < visited.length; v++) {
            if (visited[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
                //System.out.println("min = "+min);
            }
        }
        //System.out.println(min_index);
        return min_index;
    }
    public static int indexMin(int[] array){

        // add this
        if (array.length == 0)
            return -1;
    
        int index = 0;
        int min = array[index];
    
        for (int i = 1; i < array.length; i++){
            if (array[i] <= min){
            min = array[i];
            index = i;
            }
        }
        return index;
    }

    class Vertice {
        int id;
        boolean visited = false;
        ArrayList<Vertice> adiacenti = new ArrayList<>();
        ArrayList<Integer> pesi = new ArrayList<>();
        int posizione;
        
        
        public Vertice(int i) {
            id = i;
        }
    }

    void dump(String desc, Vertice grafo[]) {
        System.out.println("=====" + desc + "===============");
        for (int i = 0; i < grafo.length; i++) {
            // System.out.print( " %2d %s ".formatted(i, grafo[i].singolo ? "s" : " ") );
            for (Vertice l : grafo[i].adiacenti) {
                System.out.print(" " + l.id);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // true = lettura file
        // false = input da tastiera
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

        // T = numero di casi
        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int X = scn.nextInt();
            int Y = scn.nextInt();

            // inserisce tutti i dati di una riga in un array
            int[][] V = new int[X][Y];

            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    V[i][j] = scn.nextInt();
                }
            }

            escursione solver = new escursione();
            int risposta = solver.solve(X, Y, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}