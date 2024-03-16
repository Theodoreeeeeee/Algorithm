package DataStructure;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

// 单调栈
public class MonotonicStack {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 6, 4, 2};
        System.out.println(maxSumMinProduct(nums));
    }

    // ex. 数组中累积和于最小值的乘积，叫做指标A  Leetcode 1856 -> 难度分2051
    // 给定一个数组，请返回子数组中，指标A最大值
    public static int maxSumMinProduct(int[] nums) {
        // 单调栈
        int n = nums.length;
        int mod = (int) 1e9 + 7;
        Deque<Integer> q = new ArrayDeque<>();
        long ans = nums[0];
        long[] sum = new long[n]; // 前缀和数组
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) sum[i] = sum[i - 1] + nums[i];
        for (int i = 0; i < n; i++) {
            // 维护一个单调递减，尾部为最小值的的双端队列(栈结构)
            while (!q.isEmpty() && nums[i] <= nums[q.peekLast()]) {
                int j = q.pollLast();
                ans = Math.max(ans, (q.isEmpty() ? sum[i - 1] : sum[i - 1] - sum[q.peekLast()]) * nums[j]);
            }
            q.addLast(i);
        }
        // 此时的q就是一个单调递减，尾部为最小值的的双端队列(栈结构)
        while (!q.isEmpty()) {
            int j = q.pollLast();
            ans = Math.max(ans, (q.isEmpty() ? sum[n - 1] : sum[n - 1] - sum[q.peekLast()]) * nums[j]);
        }
        return (int) (ans % mod);
    }
}
