package algorithm.sort;

import java.util.Arrays;

// 堆排序
// 堆，完全二叉树结构，对于一颗完全二叉树，用数组表示
// [0,1,2,3,4,5,...] i位置的元素， 其左孩子2*i+1, 右孩子2*i+2, 父节点(i-1)/2
// 或者
// [0,1,2,3,4,5,...] 舍弃掉0位置,从1位置开始为根节点, 其左孩子2*i, 右孩子2*i+1, 父节点i/2
// 因为，这样可以使用位运算加快计算速度，左孩子(i << 1) 右孩子(i << 1 | 1) 父节点(i >> 1)
public class HeapSort {
    public static void main(String[] args) {
        int[] nums = {7, 8, 4, 1, 5, 4, 6, 1, 7, 9, 2, 3, 0, 5};
        heapSort(nums);
        System.out.println("nums after HeapSorted: " + Arrays.toString(nums));
    }

    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i); // 时间复杂度为O(n*logn)
//        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length); // 时间复杂度为O(n)
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 上浮
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 下沉
    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}


