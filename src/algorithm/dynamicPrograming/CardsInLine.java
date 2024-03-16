package algorithm.dynamicPrograming;

public class CardsInLine {
    public static void main(String[] args) {
        int[] cards = {1, 10, 7};
        int n = cards.length - 1;
        System.out.println(Math.max(f(cards, 0, n - 1), s(cards, 0, n - 1)));
        System.out.println(dpWay(cards));
    }

    // 先手的最大值
    public static int f(int[] cards, int l, int r) {
        if (l == r) return cards[l];
        return Math.max(s(cards, l + 1, r) + cards[l], s(cards, l, r - 1) + cards[r]);
    }

    // 后手的最大值
    public static int s(int[] cards, int l, int r) {
        if (l == r) return 0;
        // 对手扔过来的最差情况
        return Math.min(f(cards, l + 1, r), f(cards, l, r - 1));
    }

    public static int dpWay(int[] cards) {
        int n = cards.length;
        int[][] f = new int[n][n], s = new int[n][n];
        // 默认左下对角线 即 f/s[l][r] l > r   ex.l = 3, r = 0 时不合法 为0
        // s[][] 中的主对角线 l == r 也为0 Java 中默认初始化为0 不用管
        // f[][] 中的主对角线 l == r 为 cards[l]
        for (int i = 0; i < n; i++) f[i][i] = cards[i];
        for (int i = 1; i < n; i++) {
            // 主对角线上方的'对角线'  [0, 1] [1, 2] [2, 3] ...
            int l = 0;
            int r = i;
            // 可以省掉判断 l < n
            while (r < n) {
                // 依照上面的递归写法
                f[l][r] = Math.max(cards[l] + s[l + 1][r], cards[r] + s[l][r - 1]);
                s[l][r] = Math.min(f[l + 1][r], f[l][r - 1]);
                l++;
                r++;
            }
        }
        // 返回先手 后手从l = 0, r = n - 1 范围内的最大值
        return Math.max(f[0][n - 1], s[0][n - 1]);
    }

}
