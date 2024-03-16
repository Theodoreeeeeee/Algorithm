package Graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class DFS {
    // 图的深度优先遍历
    public static void dfs(Node node) {
        if (node == null) return;
        Deque<Node> stack = new ArrayDeque<>();
        HashSet<Node> has = new HashSet<>();
        stack.push(node);
        has.add(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println("cur.value: " + cur.value);
            for (Node next : cur.nexts) {
                if (!has.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    has.add(next);
                    System.out.println("next.value: " + next.value);
                    break;
                }
            }
        }
    }
}
