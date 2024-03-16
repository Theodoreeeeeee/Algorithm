public class DataStream {

    int kk;
    int v;
    boolean[] res = new boolean[100000];
    int idx = 0;
    int[] arr;
    int cnt = 0;
    // cnt 记录调用次数

    public DataStream(int value, int k) {
        kk = k;
        v = value;
    }

    public boolean consec(int num) {
        cnt++;
        // 数据量不够时
        if (cnt < kk) {
            if (num == v) res[idx++] = true;
            return false;
        }
        // 从idx-1 到 idx - 1- kk个
        else {
            if (num != v) {
                res[idx++] = false;
            } else {
                for (int i = idx - 1; i >= idx - 1 - kk; i--) {
                    if (!res[i]) res[idx++] = false;
                }
                res[idx++] = true;
            }
        }
        return res[idx - 1];
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */