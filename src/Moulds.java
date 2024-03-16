import DataStructure.TreeNode;

import java.util.*;

public class Moulds {
    // 判断是否为质数
    public static boolean isPrime(int num) {
        if (num <= 1 || num > 2 && num % 2 == 0) {
            return false;
        } else if (num == 2) {
            return true;
        }
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    // 找到第一个 >= target 的下标，若不存在则返回-1
//    public static int lowerBound(int[] nums, int target) {
//        int l = 0, r = nums.length - 1;
//        while (l <= r) {
//            int mid = l + (r - l) / 2;
//            if (nums[mid] < target) l = mid + 1;
//            else r = mid - 1;
//        }
//        return l >= nums.length || nums[l] < target ? -1 : l;
//    }

    // 找到第一个 >= target 的下标，若不存在则返回-1
    private int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target)
                left = mid;
            else
                right = mid;
        }
        return right;
    }


    // 二叉树层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return List.of();
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            List<Integer> val = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = deque.poll();
                val.add(node.val);
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }
            ans.add(val);
        }
        return ans;
    }


    // 单调栈 可重复元素 取两侧最近的小/大元素
    // 获取某一个位置的两侧距离最短且小于其值的下标  (小于或大于仅需改变维护栈的单调性)
    // (小于维护 栈底小栈顶大的单增栈； 大于维护 栈底大栈顶小的单减栈)
    public static int[][] getNearLess(int[] nums) {
        int n = nums.length;
        int[][] ans = new int[n][2];
        Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 栈不为空 且新遍历到的元素 < 栈顶下标所代表的元素时
            while (!stack.isEmpty() && nums[i] < nums[stack.peek().get(0)]) {
                List<Integer> popList = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int idx : popList) ans[idx] = new int[]{leftLessIndex, i};
            }
            // 栈不为空， 且新遍历到的元素 == 栈顶下标所代表的元素时
            if (!stack.isEmpty() && nums[i] == nums[stack.peek().get(0)]) {
                stack.peek().add(i);
            }
            // 栈为空 或 栈顶所代表元素 > 新遍历的元素
            else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                stack.push(newList);
            }
        }
        // 一次遍历结束后，结算栈内的剩余元素
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int idx : popList) ans[idx] = new int[]{leftLessIndex, -1};
        }
        return ans;
    }

    // gcd
    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    // 筛质数， 埃氏筛


    // 求组合数C(n,k)   C(4,2) = 6
    private static int combination(int n, int k) {
        int mod = (int) 1e9 + 7;
        long[][] f = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = 1;
            for (int j = 1; j <= i; j++) f[i][j] = (f[i - 1][j] + f[i - 1][j - 1]) % mod;
        }
        return (int) (f[n][k] % mod);
    }

    // 快速幂
    public static long pow(long x, int n) {
        int mod = (int) 1e9 + 7;
        if (n == 0) return 1;
        if (n == 1) return x % mod;
        if (n % 2 == 0) return pow(x * x % mod, n / 2);
        else return x * pow(x, n - 1) % mod;
    }

    private long pow(long x, int n, int mod) {
        if (n == 0) return 1;
        if (n == 1) return x % mod;
        if (n % 2 == 0) return pow(x * x % mod, n / 2, mod);
        return x * pow(x, n - 1, mod) % mod;
    }


    // lca 最近公共祖先, 给定 root, p, q

    /**
     * // 分类讨论
     * // 1.当前节点是空节点
     * // 2.当前节点是p
     * // 3.当前节点是q
     * // 4.其他: 是否找到p或q: 1.左右子树都找到 2.只有左子树找到 3.只有右子树找到 4.左右子树都没有找到
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }

    // lowbit(x)是x的二进制表达式中最低位的1所对应的值
    public static int lowBit(int x) {
        return x & (-x);
    }


    // KMP -返回在text串中pattern串的出现下标起始位置的列表
    private static List<Integer> kmp(char[] text, char[] pattern) {
        int m = pattern.length;
        int[] pi = new int[m];
        int c = 0;
        for (int i = 1; i < m; i++) {
            char v = pattern[i];
            while (c > 0 && pattern[c] != v) {
                c = pi[c - 1];
            }
            if (pattern[c] == v) {
                c++;
            }
            pi[i] = c;
        }

        List<Integer> res = new ArrayList<>();
        c = 0;
        for (int i = 0; i < text.length; i++) {
            char v = text[i];
            while (c > 0 && pattern[c] != v) {
                c = pi[c - 1];
            }
            if (pattern[c] == v) {
                c++;
            }
            if (c == m) {
                res.add(i - m + 1);
                c = pi[c - 1];
            }
        }
        return res;
    }
}
