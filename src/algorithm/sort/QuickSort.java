package algorithm.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        // 快速排序
        int[] nums = {9, 7, 2, 1, 4, 7, 2, 3, 5, 4, 6};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r); // 随机选出一个值和最后一个值作交换 如果不做交换最差情况下原数组有序此时时间复杂度为O(N^2)
            int[] p = partition(arr, l, r); // 做完 < = > 三区域的划分后，中间等于的区域的第一和最后一个下标 p[0], p[1]
            quickSort(arr, l, p[0] - 1); // 在左右子区间做递归
            quickSort(arr, p[1] + 1, r);
        }
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1; // < 区的右边界
        int more = r; // 先把最后一个元素包在 > 区
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        } // while循环结束后，[l,less]<,  [less+1,more-1]=,  [more,r-1]>,  r=
        swap(arr, more, r); // 将r和more位置交换，划分完成 [l,less]<,  [less+1,more]=,  [more+1,r]>
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
