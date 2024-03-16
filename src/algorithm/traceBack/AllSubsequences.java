package algorithm.traceBack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AllSubsequences {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(getAllSubsequences(s));
        System.out.println(getAllSubsequences_noRepeat(s));
        System.out.println(permutation(s));
    }

    // [, c, b, bc, a, ac, ab, abc]
    public static List<String> getAllSubsequences(String s) {
        char[] cs = s.toCharArray();
        List<String> ans = new ArrayList<>();
        String path = "";
        dfs1(cs, ans, path, 0);
        return ans;
    }

    private static void dfs1(char[] cs, List<String> ans, String path, int idx) {
        if (idx == cs.length) {
            ans.add(path);
            return;
        }
        dfs1(cs, ans, path, idx + 1);
        path += String.valueOf(cs[idx]);
        dfs1(cs, ans, path, idx + 1);
    }

    public static List<String> getAllSubsequences_noRepeat(String s) {
        char[] cs = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        String path = "";
        dfs2(cs, set, path, 0);
        return new ArrayList<>(set);
    }

    private static void dfs2(char[] cs, HashSet<String> set, String path, int idx) {
        if (idx == cs.length) {
            set.add(path);
            return;
        }
        dfs2(cs, set, path, idx + 1);
        path += String.valueOf(cs[idx]);
        dfs2(cs, set, path, idx + 1);
    }

    // 全排列
    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();
        String path = "";
        char[] cs = s.toCharArray();
        boolean[] vis = new boolean[26];
        dfs3(cs, ans, path, 0, vis);
        return ans;
    }

    private static void dfs3(char[] cs, List<String> ans, String path, int idx, boolean[] vis) {
        if (idx == cs.length) {
            ans.add(path);
            return;
        }
        for (int i = 0; i < cs.length; i++) {
            if (!vis[cs[i] - 'a']) {
                path += cs[i];
                vis[cs[i] - 'a'] = true;
                dfs3(cs, ans, path, idx + 1, vis);
                vis[cs[i] - 'a'] = false;
                path = path.substring(0, path.length() - 1);
            }
        }
    }
}
