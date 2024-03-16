package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    // 图的广度优先遍历
    public static void bfs(Node node) {
        if (node == null) return;
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> has = new HashSet<>();
        queue.add(node);
        has.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println("cur.value: " + cur.value);
            for (Node next : node.nexts) {
                if (!has.contains(next)) {
                    has.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
