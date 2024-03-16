package algorithm.dynamicPrograming;

import java.lang.management.MemoryNotificationInfo;

// 给定一个数组代表每个员工喝完咖啡的时间节点，可以选择挥发 a分钟或者放入串行的咖啡机清洗b分钟
// 最短的时间 ?
public class Coffee {
    public static void main(String[] args) {
        int[] coffee = {1, 3, 4, 6, 6, 7, 8, 9, 11, 15, 15};
        int a = 3, b = 10;
        System.out.println(process(coffee, a, b, 0, 0));
        System.out.println(dpWays(coffee, a, b));
    }


    // a 洗咖啡杯机洗一杯的时间
    // b 咖啡杯自己挥发的时间
    // coffee 每一个员工喝完的时间固定变量
    // coffee[0..index-1] 已经变干净
    // coffee[index..] 都需要变干净，washLine表示洗咖啡杯机何时可用
    // coffee[index..] 变干净，最少的时间返回
    private static int process(int[] coffee, int a, int b, int index, int washLine) {
        // 只剩最后一杯时, 选择挥发或放入咖啡杯洗，返回最小值
        if (index == coffee.length - 1) {
            return Math.min(Math.max(coffee[index], washLine) + a, coffee[index] + b);
        }
        // wash 洗完当前咖啡杯所需的时间
        int wash = Math.max(coffee[index], washLine) + a;
        // 洗完 index + 1... 后续杯子的最短时间
        int next1 = process(coffee, a, b, index + 1, wash);
        // 当前的咖啡杯洗完时间和后续咖啡杯洗完时间取最大值，都洗完才算洗完
        int p1 = Math.max(wash, next1);

        // dry 挥发当前咖啡杯的选择
        int dry = coffee[index] + b;
        int next2 = process(coffee, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);
        return Math.min(p1, p2);

    }


    // 动态规划
    private static int dpWays(int[] coffee, int a, int b) {
        int n = coffee.length;
        // 洗咖啡杯杯机最大可用时间 a: 洗一杯 b: 挥发一杯
        int maxWait = 0;
        for (int time : coffee) maxWait = Math.max(time, maxWait) + a;
        int[][] dp = new int[n][maxWait + 1];
        for (int i = 0; i <= maxWait; i++) dp[n - 1][i] = Math.min(Math.max(coffee[n - 1], i) + a, coffee[n - 1] + b);
        for (int idx = n - 2; idx >= 0; idx--) {
            for (int washLine = 0; washLine <= maxWait; washLine++) {
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(coffee[idx], washLine) + a;
                if (wash <= maxWait) p1 = Math.max(wash, dp[idx + 1][wash]);
                int dry = coffee[idx] + b;
                int p2 = Math.max(dry, dp[idx + 1][washLine]);
                dp[idx][washLine] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }
}
