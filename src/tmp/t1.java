//import DataStructure.*;
//
//import java.util.*;
//
// 牵手二面
//public class Main {
//    public static void main(String[] args) {
////        int[] values = {1, 3, 2, 1, 1}; // 定义0索引是1层，n-1索引是最高层
//        int[] values = {1, 1, 1, 1, 1}; // 定义0索引是1层，n-1索引是最高层
////        int[] values = {1, 0, 0, 0, 1}; // 定义0索引是1层，n-1索引是最高层
//        System.out.println(new Solution2().minSteps(1, 4, values));
//    }
//}
//
//
//class Solution2{
//    int end;
//    int[] values;
//    Map<Integer, Integer> cache = new HashMap<>();
//
//    public int minSteps(int start, int end, int[] values) {
//        this.end = end;
//        this.values = values;
//        // 递归
//        int ans = dfs(start);
//        return ans >= Integer.MAX_VALUE / 2 ? -1 : ans;
//    }
//
//    private int dfs(int cur) {
//        if (cache.containsKey(cur)) return cache.get(cur);
//        // 递归的终止条件？
//        if (cur == end) return 0;
//        // 当当前位置加减step也不可能到达cur，return
//        int step = values[cur];
//        if (cur + step >= values.length) return Integer.MAX_VALUE / 2;
//        int res = dfs(cur + step) + 1;
//        if (cur - step < 0) return Integer.MAX_VALUE / 2;
//        res = Math.min(res, dfs(cur - step) + 1);
//        // 向上
////        int res = 0;
////        res += Math.min(cur + step >= values.length ? Integer.MAX_VALUE / 2 : dfs(cur + step) + 1, cur - step < 0 ? Integer.MAX_VALUE / 2 : dfs(cur - step) + 1);
//        // 向下
//        cache.put(cur, res);
//        return res;
//    }
//}