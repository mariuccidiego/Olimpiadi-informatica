import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main1 {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            int t = Integer.parseInt(reader.readLine());
            for (int i = 1; i <= t; i++) {
                int n = Integer.parseInt(reader.readLine());
                int[][] windows = new int[n][2];
                for (int j = 0; j < n; j++) {
                    String[] line = reader.readLine().split(" ");
                    windows[j][0] = Integer.parseInt(line[0]);
                    windows[j][1] = Integer.parseInt(line[1]);
                }
                int minEnergy = minEnergyRequired(windows);
                writer.println("Case #" + i + ": " + minEnergy);
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int minEnergyRequired(int[][] windows) {
        int[][] dp = new int[windows.length][2];
        for (int i = 0; i < windows.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = windows[0][0];
        dp[0][1] = windows[0][1];

        for (int i = 1; i < windows.length; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    int energy = windows[i][j] + dp[i - 1][k];
                    if (j != k) {
                        dp[i][j] = Math.min(dp[i][j], energy);
                    }
                }
            }
        }

        return Math.min(dp[windows.length - 1][0], dp[windows.length - 1][1]);
    }
}