package algorithm.sort;

import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {7, 1, 4, 2, 3, 5, 6, 1, 8, 4, 2};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    // Merge sort 归并排序
    private static void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        mergeSortProcess(nums, 0, nums.length - 1);
    }

    private static void mergeSortProcess(int[] nums, int l, int r) {
        if (l == r) return; // 递归终止条件
        int mid = l + ((r - l) >> 1);
        mergeSortProcess(nums, l, mid); // 递归左右区间
        mergeSortProcess(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    private static void merge(int[] nums, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l, p2 = mid + 1, idx = 0; // 归并时的双指针
        while (p1 <= mid && p2 <= r) help[idx++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        while (p1 <= mid) help[idx++] = nums[p1++];
        while (p2 <= r) help[idx++] = nums[p2++];
        System.arraycopy(help, 0, nums, l, r - l + 1);
    }

}
