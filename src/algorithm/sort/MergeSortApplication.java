package algorithm.sort;

import java.util.Arrays;

public class MergeSortApplication {
    public static void main(String[] args) {
        // 数组的每一个元素左侧小于该元素的为一个小和，求出数组的小和总值
        // (1)+(1+3)+(1)+(1+3+4+2) = 1+4+1+10 = 16
        int[] nums = {1, 3, 4, 2, 5};
        System.out.println(getLittleSum(nums));
        System.out.println("nums: " + Arrays.toString(nums));
    }

    private static int getLittleSum(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    private static int process(int[] nums, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return process(nums, l, mid) + process(nums, mid + 1, r) + merge(nums, l, mid, r);
    }

    private static int merge(int[] nums, int l, int mid, int r) {
        int sum = 0;
        int[] help = new int[r - l + 1];
        int idx = 0, p1 = l, p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            sum += nums[p1] < nums[p2] ? nums[p1] * (r - p2 + 1) : 0;
            help[idx++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= mid) help[idx++] = nums[p1++];
        while (p2 <= r) help[idx++] = nums[p2++];
        System.arraycopy(help, 0, nums, l, r - l + 1);
        return sum;
    }
}
