package algorithm;

import java.util.HashMap;

public class StickersToSpellTheWord {
    // String[] stickers 贴纸可把每个单剪出来单个使用, 每个arr[i] 无限次使用
    // 尝试去拼出 target 目标字符串
    public static void main(String[] args) {
        String[] stickers = {"aaaa", "bbaa", "ccddd"};
        String target = "abccccbaaaaa";
        System.out.println(minStickers1(stickers, target));
    }

    public static int minStickers1(String[] stickers, String target) {
        int n = stickers.length;
        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] cs = stickers[i].toCharArray();
            for (char c : cs) map[i][c - 'a']++;
        }
        HashMap<String, Integer> cache = new HashMap<>();
        cache.put("", 0);
        return process1(map, target, cache);
    }

    private static int process1(int[][] map, String target, HashMap<String, Integer> cache) {
        if (cache.containsKey(target)) return cache.get(target);
        int ans = Integer.MAX_VALUE;
        char[] cs = target.toCharArray(); // map  [[4], [2,2], [0,0,2,3]] -> stickers[] {"aaaa", "bbaa", "ccddd"}
        int[] map2 = new int[26];         // map2 [6,2,4]  ->  {"aaaa", "bbaa", "ccddd"}
        for (char c : cs) map2[c - 'a']++;
        for (int i = 0; i < map.length; i++) {
            // 如果当前遍历到的stickers[i] 不存在target字符串首字母则跳过 避免target = "abc" stickers[i] = "uyi"死循环
            // 贪心也可加快判断？
            if (map[i][cs[0] - 'a'] == 0) continue;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                // 当遍历到的target.charAt(j)仍然还有剩余字符需要填充
                if (map2[j] > 0) sb.append(String.valueOf((char) ('a' + j)).repeat(Math.max(0, map2[j] - map[i][j])));
            }
            String rest = sb.toString();
            int tmp = process1(map, rest, cache);
            if (tmp != -1) ans = Math.min(ans, tmp + 1);
        }
        cache.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
        return cache.get(target);
    }
}
