package algorithm.dynamicPrograming;

import java.util.Arrays;

public class CoinsWays {
    public static void main(String[] args) {
        // 给定一个coins数组 求能组合出sum共有多少种可能性，coins[i]无限次使用
        int[] coins = {5, 10, 50, 100};
        int sum = 2200;
        int n = coins.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] arr : dp) Arrays.fill(arr, -1);
        System.out.println(ways1(coins, 0, sum));
        System.out.println(ways2(coins, 0, sum, dp));
        System.out.println(ways3(coins, sum));
        System.out.println(ways4(coins, sum));
    }

    // idx 从 idx位置开始组合出rest(剩余钱数)的方法
    public static int ways1(int[] coins, int idx, int rest) {
        if (idx == coins.length) return rest == 0 ? 1 : 0;
        int ans = 0;
        for (int sheet = 0; sheet * coins[idx] <= rest; sheet++) {
            ans += ways1(coins, idx + 1, rest - sheet * coins[idx]);
        }
        return ans;
    }

    // 记忆化搜索
    public static int ways2(int[] coins, int idx, int rest, int[][] dp) {
        if (dp[idx][rest] != -1) return dp[idx][rest];
        if (idx == coins.length) {
            dp[idx][rest] = rest == 0 ? 1 : 0;
            return dp[idx][rest];
        }
        int ans = 0;
        for (int sheet = 0; sheet * coins[idx] <= rest; sheet++) {
            ans += ways2(coins, idx + 1, rest - sheet * coins[idx], dp);
        }
        dp[idx][rest] = ans;
        return dp[idx][rest];
    }

    // 经典动态规划
    public static int ways3(int[] coins, int sum) {
        int n = coins.length;
        // dp[idx][rest]
        int[][] dp = new int[n + 1][sum + 1];
        dp[n][0] = 1;
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= sum; rest++) {
                int ans = 0;
                for (int sheet = 0; sheet * coins[idx] <= rest; sheet++) {
                    ans += dp[idx + 1][rest - sheet * coins[idx]];
                }
                dp[idx][rest] = ans;
            }
        }
        return dp[0][sum];
    }

    // 优化后的动态规划
    public static int ways4(int[] coins, int sum) {
        int n = coins.length;
        int[][] dp = new int[n + 1][sum + 1];
        dp[n][0] = 1;
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= sum; rest++) {
                dp[idx][rest] = dp[idx + 1][rest];
                if (rest - coins[idx] >= 0) {
                    dp[idx][rest] += dp[idx][rest - coins[idx]];
                }
//                dp[idx][rest] = rest - coins[idx] >= 0 ? dp[idx + 1][rest] + dp[idx][rest - coins[idx]] : dp[idx + 1][rest];
            }
        }
        return dp[0][sum];
    }


}
