import java.util.*;

public class Main {
    // Mọi dynamic programming algorithm đều bắt đầu bằng một grid.

    static int[][] knapsack(int[] costs, int[] capacities, int available, int start, int step) {
        int[][] grid = new int[costs.length + 1][available + 1];
        for (int i = 1; i < grid.length; i++) {
            int cost = costs[i - 1];
            int capacity = capacities[i - 1];
            for (int j = start; j <= available; j += step) {
                if (capacity <= j) {
                    grid[i][j] = Math.max(cost + grid[i - 1][j - capacity], grid[i-1][j]);
                } else {
                    grid[i][j] = grid[i-1][j];
                }
            }
        }

        return grid;
    }

    static String common_sub_string(String strA, List<String> inputs) {
        Map<String, Integer> output = new Hashtable<>();

        for (String input : inputs) {
            int maxLength = 0;
            int[][] grid = new int[strA.length() + 1][input.length() + 1];
            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[i].length; j++) {
                    if (strA.charAt(i - 1) == input.charAt(j - 1)) {
                        grid[i][j] = grid[i - 1][j - 1] + 1;
                        if (grid[i][j] > maxLength) maxLength = grid[i][j];
                    } else {
                        grid[i][j] = 0;
                    }
                }
            }
            output.put(input, maxLength);
        }
        return output.keySet().stream().max(Comparator.comparingInt(output::get))
                .orElse("nothing");
    }

    static String common_sub_sequence(String strA, List<String> inputs) {
        Map<String, Integer> output = new Hashtable<>();

        for (String input : inputs) {
            int maxLength = 0;
            int[][] grid = new int[strA.length() + 1][input.length() + 1];
            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[i].length; j++) {
                    if (strA.charAt(i - 1) == input.charAt(j - 1)) {
                        grid[i][j] = grid[i - 1][j - 1] + 1;
                        if (grid[i][j] > maxLength) maxLength = grid[i][j];
                    } else {
                        grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]);
                    }
                }
            }
            output.put(input, maxLength);
        }
        return output.keySet().stream().max(Comparator.comparingInt(output::get))
                .orElse("nothing");
    }

    public static void main(String[] args) {

        // You have a bag 4lbs.
        // stereo: $3000, 4lbs
        // laptop: $2000, 3lbs
        // guitar: $1500, 1lbs

        int[] costs = new int[]{3000, 2000, 1500};
        int[] capacities = new int[]{4, 3, 1};
        int[][] knapsack = knapsack(costs, capacities, 4, 1, 1);
        print(knapsack);

        // common substring
        String strA = "hist";
        System.out.println(common_sub_string(strA, List.of("fish", "vista", "hihi", "hist")));

        String strA_1 = "narotu";
        System.out.println(common_sub_sequence(strA_1, List.of("naruto"/*5: nar .. to*/, "boroto" /*3: rot*/, "nariew"/*3: nar*/)));
    }

    static void print(int[][] dp) {
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j ++) {
                System.out.print(dp[i][j] + "|");
            }
            System.out.println();
        }
    }

}