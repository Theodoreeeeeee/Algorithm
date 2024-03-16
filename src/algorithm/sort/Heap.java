package algorithm.sort;

public class Heap {
    private final int[] heap;
    private final int limit;
    private int heapSize;

    public Heap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    public void push(int value) {
        if (heapSize == limit) throw new RuntimeException("heap is full");
        heap[heapSize] = value;
        heapInsert(heapSize++);
    }


    public int peek() {
        return heap[0];
    }

    public int pop() {
        int ans = heap[0];
        swap(0, --heapSize);
        heapify();
        return ans;
    }

    // 上浮
    private void heapInsert(int idx) {
        while (heap[idx] > heap[(idx - 1) / 2]) {
            swap(idx, (idx - 1) / 2);
            idx = (idx - 1) / 2;
        }
    }

    // 下沉
    private void heapify() {
        int idx = 0, left = 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && heap[left] < heap[left + 1] ? left + 1 : left;
            largest = heap[largest] < heap[idx] ? idx : largest;
            if (largest == idx) break;
            swap(largest, idx);
            idx = largest;
            left = idx * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }


    public static void main(String[] args) {
        Heap heap = new Heap(5);
        for (int v : new int[]{7, 8, 2, 6, 5}) heap.push(v);
        while (!heap.isEmpty()) System.out.println(heap.pop());
    }
}

