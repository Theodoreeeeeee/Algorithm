package huaweiTesting;

//  leetcode487 bits = [0,1,1,0,1,0,0,1,1,0] 求可翻转一个后的最大值
public class two_continueTargetNumberExceptOne {
    public static void main(String[] args) {
        int[] bits = {0, 1, 1, 0, 1, 0, 1};
        int target = 1;
        System.out.println(continueTargetNumberExceptOne(target, bits));
    }

    public static int continueTargetNumberExceptOne(int target, int[] bits) {
        int n = bits.length;
        int left = 0, right = 0, cnt = 0, ans = 0;
        while (right < n) {
            while (right < n && cnt <= 1) {
                if (bits[right] != target) cnt++;
                if (cnt <= 1) ans = Math.max(ans, right - left + 1);
                right++;
            }
            while (left < right && cnt == 2) {
                if (bits[left] != target) cnt--;
                left++;
            }
        }
        return ans;
    }
}
