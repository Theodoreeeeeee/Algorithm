package algorithm;

public class PenaltyNumber {
    public static int calculatePenaltyNumber(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int num = i * i;
            String numStr = Integer.toString(num);
            int[] sums = new int[numStr.length() + 1];
            for (int j = 0; j < numStr.length(); j++) {
                int digit = Character.getNumericValue(numStr.charAt(j));
                sums[j + 1] = sums[j] + digit;
            }
            dp[i] = dp[i - 1];
            for (int j = 1; j <= numStr.length(); j++) {
                if (sums[j] <= i && sums[j] == i) {
                    dp[i] += num;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 37;
        int penaltyNumber = calculatePenaltyNumber(n);
        System.out.println("惩罚数: " + penaltyNumber);
    }
}
