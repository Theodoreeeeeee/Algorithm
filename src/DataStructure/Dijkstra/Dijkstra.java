package DataStructure.Dijkstra;

import java.util.Arrays;

public class Dijkstra {

    class Graph {
        private static final int INF = Integer.MAX_VALUE / 2; // 防止更新最短路时加法溢出

        private int[][] g;

        public Graph(int n, int[][] edges) {
            g = new int[n][n]; // 邻接矩阵（初始化为无穷大，表示 i 到 j 没有边）
            for (int i = 0; i < n; ++i)
                Arrays.fill(g[i], INF);
            for (var e : edges)
                g[e[0]][e[1]] = e[2]; // 添加一条边（输入保证没有重边）
        }

        public void addEdge(int[] e) {
            g[e[0]][e[1]] = e[2]; // 添加一条边（输入保证这条边之前不存在）
        }

        // 朴素 Dijkstra 算法
        public int shortestPath(int start, int end) {
            int n = g.length;
            var dis = new int[n]; // 从 start 出发，到各个点的最短路，如果不存在则为无穷大
            Arrays.fill(dis, INF);
            dis[start] = 0;
            var vis = new boolean[n];
            for (;;) {
                // 找到当前最短路，去更新它的邻居的最短路
                // 根据数学归纳法，dis[x] 一定是最短路长度
                int x = -1;
                for (int i = 0; i < n; ++i)
                    if (!vis[i] && (x < 0 || dis[i] < dis[x]))
                        x = i;
                if (x < 0 || dis[x] == INF) // 所有从 start 能到达的点都被更新了
                    return -1;
                if (x == end) // 找到终点，提前退出
                    return dis[x];
                vis[x] = true; // 标记，在后续的循环中无需反复更新 x 到其余点的最短路长度
                for (int y = 0; y < n; ++y)
                    dis[y] = Math.min(dis[y], dis[x] + g[x][y]); // 更新最短路长度
            }
        }
    }
}
