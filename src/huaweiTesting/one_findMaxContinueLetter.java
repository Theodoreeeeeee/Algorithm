package huaweiTesting;

import java.util.Arrays;

// abccccasdaabsdabdddddajklhdsssddadcaabb
// 找出每个字母连续出现的最大个数
public class one_findMaxContinueLetter {
    public static void main(String[] args) {
        String str = "abccccaabbddddcdsadivuoxnjcxnafasfffaabb";
        System.out.println(Arrays.toString(findMaxContinueLetter(str)));
    }

    // 滑动窗口
    public static int[] findMaxContinueLetter(String str) {
        int[] res = new int[26];
        char[] chars = new char[26];
        for (int i = 0; i < 26; i++) chars[i] = (char) ('a' + i);
        int len = str.length();
        for (int i = 0; i < 26; i++) {
            int cnt = 0, idx = 0, ans = 0;
            while (idx < len) {
                if (str.charAt(idx) == chars[i]) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                ans = Math.max(ans, cnt);
                idx++;
            }
            res[i] = ans;
        }
        return res;
    }
}
