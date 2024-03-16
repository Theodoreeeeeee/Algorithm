package algorithm.traceBack;

import java.util.PriorityQueue;

public class NQueens2 {
    public static void main(String[] args) {
        int n = 14;
//        System.out.println(countSolutions(n));
        System.out.println(countSolutions2(n));
    }

    public static int countSolutions(int n) {
        int[] record = new int[n];
        return dfs(record, 0);
    }

    public static int dfs(int[] record, int r) {
        // 到了终止位置，以上状况算是一种
        if (r == record.length) return 1;
        int ans = 0;
        for (int c = 0; c < record.length; ++c) {
            if (isValid(record, r, c)) {
                record[r] = c;
                ans += dfs(record, r + 1);
            }
        }
        return ans;
    }

    // 3,4   4,3  5,2             2. 4,5
    public static boolean isValid(int[] record, int r, int c) {
        // 两个皇后行列所表示位置分别为:[r, c] [idx, record[idx]]
        // 大枚举是枚举行，所以必然不同行, 1 列相等 2 对角线相等
        // 1. c == record[idx] 2. abs(r - idx) == abs(c - record[idx])
        for (int idx = 0; idx < r; idx++) {
            if (c == record[idx] || Math.abs(r - idx) == Math.abs(c - record[idx])) {
                return false;
            }
        }
        return true;
    }

    public static int countSolutions2(int n) {
        if (n < 3 || n > 32) {
            return 0;
        }
        // 技巧1 ： 获取最右侧的多个1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return dfs2(limit, 0, 0, 0);
    }

    private static int dfs2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // 技巧2 ： limit 永远不变 截取后面有用的n位   ~ 取反符
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int ans = 0;
        while (pos != 0) {
            // 技巧3 ： 取出pos中最右侧的1，剩下位置都是0
            mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            ans += dfs2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return ans;
    }

}
