package algorithm.dynamicPrograming;

public class NumDecoding {
    public static void main(String[] args) {
        String s = "12";
        System.out.println(numDecoding1(s));
        System.out.println(numDecoding2(s));
    }

    // 递归实现
    public static int numDecoding1(String s) {
        char[] cs = s.toCharArray();
        return dfs(cs, 0);
    }

    public static int dfs(char[] cs, int idx) {
        if (idx == cs.length) return 1;
        if (cs[idx] == '0') return 0;
        int ans = dfs(cs, idx + 1);
        if (cs[idx] == '1' && idx + 1 < cs.length || cs[idx] == '2' && idx + 1 < cs.length && cs[idx + 1] >= '0' && cs[idx + 1] <= '6') {
            ans += dfs(cs, idx + 2);
        }
        return ans;
    }

    // 动态规划
    public static int numDecoding2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            // 当前遍历到的cs[i] 是否为0 ，是的话dp[i]置为0，
            // 否则 判断 i+1 是否越界 且当前遍历到的是否为1 或为2 且i + 1位置在0 ~ 6 范围内？ 是的话，可以两个字母构成一个解码答案 否的话 仅每个单独构成答案
            dp[i] = cs[i] == '0' ? 0 : i + 1 < n && (cs[i] == '1' || cs[i] == '2' && cs[i + 1] >= '0' && cs[i + 1] <= '6') ? dp[i + 1] + dp[i + 2] : dp[i + 1];
        }
        return dp[0];
    }

}
