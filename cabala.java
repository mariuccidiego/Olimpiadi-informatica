import java.util.*;
import java.io.*;
//import java.lang.*;

public class cabala {
    static ArrayList<String> nums = new ArrayList<String>();
    static long massimo=0;
    public long solve(int N, int V) {
        massimo=0;
        // aggiungi codice...
        //int risposta = 0;
        ric(nums, "", N, '3', V);
        ric(nums, "", N, '6', V);
        ric(nums, "", N, '9', V);
        return massimo;
    }
    static void ric(ArrayList<String> nums, String stringa, int N, char C, int V){
        stringa+=C;
        //System.out.println(stringa);
        if(stringa.length() == N){
            //nums.add(stringa);
            //System.out.println(stringa);
            finale(stringa, V);
            stringa="";
            
        }else{
            
            if(stringa.charAt(stringa.length()-1)=='3'){
                ric(nums,stringa,N,'6', V);
                ric(nums,stringa,N,'9', V);
            }else if(stringa.charAt(stringa.length()-1)=='6'){
                ric(nums,stringa,N,'3', V);
                ric(nums,stringa,N,'9', V);
            }else{
                ric(nums,stringa,N,'3', V);
                ric(nums,stringa,N,'6', V);
            }
        }
    }    
    static void finale(String stringa, int V){
        long num=Integer.parseInt(stringa);
        //System.out.println("num = "+num+"    "+V);
        long resto=num%V;
        if(resto>massimo){
            massimo=resto;
        }
        //System.out.println("max = "+num+" / "+ V+" = "+resto);
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
            int V = scn.nextInt();

            cabala solver = new cabala();
            long risposta = solver.solve(N, V);

            prnt.print(risposta+" ");
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}