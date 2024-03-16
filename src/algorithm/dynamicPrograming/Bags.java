package algorithm.dynamicPrograming;

// 01背包问题
public class Bags {
    public static void main(String[] args) {
        int[] weight = {2, 3, 7, 4, 1};
        int[] values = {4, 2, 8, 6, 5};
        System.out.println(findMaxValue1(weight, values, 0, 0, 10));
        System.out.println(findMaxValue2(weight, values, 0, 10));
        System.out.println(findMaxValue3(weight, values, 10));
    }

    // 递归实现
    public static int findMaxValue1(int[] w, int[] v, int idx, int alreadyW, int bag) {
        // 当前中量大于背包可装总量 返回 -1 代表此方案不可行
        if (alreadyW > bag) return -1;
        // 下标超过w.length - 1 说明之后再取也没有价值了
        if (idx == w.length) return 0;
        // 不选择当前的货物
        int p1 = findMaxValue1(w, v, idx + 1, alreadyW, bag);
        // 第二种方案，默认不可行为 -1
        int p2 = -1;
        // 选择当前的货物
        int p2next = findMaxValue1(w, v, idx + 1, alreadyW + w[idx], bag);
        if (p2next != -1) {
            p2 = v[idx] + p2next;
        }
        // 返回两种方案的最大值
        return Math.max(p1, p2);
    }

    // 简化递归
    public static int findMaxValue2(int[] w, int[] v, int idx, int rest) {
        if (rest < 0) return -1;
        if (idx == w.length) return 0;
        int p1 = findMaxValue2(w, v, idx + 1, rest);
        int p2 = v[idx] + findMaxValue2(w, v, idx + 1, rest - w[idx]);
        return Math.max(p1, p2);
    }

    // 动态规划
    public static int findMaxValue3(int[] w, int[] v, int bag) {
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int i = w.length - 1; i >= 0; i--) {
            for (int r = 0; r <= bag; r++) {
                dp[i][r] = Math.max(dp[i + 1][r], r - w[i] >= 0 ? v[i] + dp[i + 1][r - w[i]] : -1);
            }
        }
        return dp[0][bag];
    }
}
