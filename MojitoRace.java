import java.util.*;
import java.io.*;

public class MojitoRace {
    static class Obstacle {
        int t, x, p;

        public Obstacle(int t, int x, int p) {
            this.t = t;
            this.x = x;
            this.p = p;
        }
    }

    public static int getMaxScore(int idx, int currentTime, int currentX, Obstacle[] obstacles, int[][] memo) {
        if (idx == obstacles.length) {
            return 0;
        }
        if (memo[idx][currentX] != -1) {
            return memo[idx][currentX];
        }

        int score = 0;
        if (Math.abs(currentX - obstacles[idx].x) <= (obstacles[idx].t - currentTime)) {
            score = getMaxScore(idx + 1, obstacles[idx].t, obstacles[idx].x, obstacles, memo) + obstacles[idx].p;
        }
        score = Math.max(score, getMaxScore(idx + 1, currentTime, currentX, obstacles, memo));

        memo[idx][currentX] = score;
        return score;
    }

    public static void main(String[] args) throws FileNotFoundException {
        /*File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);
        PrintWriter writer = new PrintWriter("output.txt");*/

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
        for (int t = 1; t <= T; t++) {
            //System.out.println(" vds"+scanner.nextInt()+scanner.nextInt());
            int n = scn.nextInt();
            int x = scn.nextInt();
            int m = scn.nextInt();
            Obstacle[] obstacles = new Obstacle[m];
            for (int i = 0; i < m; i++) {
                int ti = scn.nextInt();
                int xi = scn.nextInt();
                int pi = scn.nextInt();
                obstacles[i] = new Obstacle(ti, xi, pi);
            }
            Arrays.sort(obstacles, (a, b) -> a.t - b.t);

            int[][] memo = new int[m][n + 1];
            for (int i = 0; i < m; i++) {
                Arrays.fill(memo[i], -1);
            }

            int maxScore = getMaxScore(0, 0, x, obstacles, memo);
            prnt.format("Case #" + t + ": " + maxScore);
        }
        scn.close();
        prnt.close();
    }
}