package algorithm;

// 假设有排成一行的N个位置，1 ~ N N >= 2
// 开始机器人在其中的M位置是哪个， M 在 1 ~ N 中
// 如果机器人来到1位置，那么下一步只能右来到2位置， 如果来到N位置，只能往左来到N-1位置
// 如果机器人来到中间位置，那么下一步可以往左或往右走
// 规定机器人必须走K步，最终能来到的P位置（P 也在 1 ~ N） 的方法有多少种
// 给定四个参数 N, M, K, P 返回方法数
public class RobotWalk {
    // [1,2,3,4]
    public static void main(String[] args) {
        System.out.println(walk1(4, 3, 4, 3));
    }

    // n 总共的位置  cur 当前的位置  rest 剩余可走的步数  target 想要到达的位置
    public static int walk1(int n, int cur, int rest, int target) {
        if (rest == 0) return cur == target ? 1 : 0;
        if (cur == 1) return walk1(n, 2, rest - 1, target);
        if (cur == n) return walk1(n, n - 1, rest - 1, target);
        return walk1(n, cur + 1, rest - 1, target) + walk1(n, cur - 1, rest - 1, target);
    }
}
